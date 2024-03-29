package br.com.ledtom.antenna.domain.controller;

import java.util.Arrays;
import java.util.Iterator;

import javax.persistence.NoResultException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.ledtom.antenna.core.security.Restricted;
import br.com.ledtom.antenna.domain.service.ChannelService;
import br.com.ledtom.antenna.domain.service.CommandService;
import br.com.ledtom.antenna.domain.service.MachineService;
import br.com.ledtom.antenna.domain.structs.CheckMachineStatusResponse;
import br.com.ledtom.antenna.domain.structs.GetCommandsResponse;
import br.com.ledtom.antenna.domain.structs.GetVideoListResponse;
import br.com.ledtom.antenna.domain.structs.NotifyResponse;
import br.com.ledtom.antenna.domain.structs.RequestSyncResponse;
import br.com.ledtom.antenna.model.entity.Channel;
import br.com.ledtom.antenna.model.entity.Command;
import br.com.ledtom.antenna.model.entity.Machine;
import br.com.ledtom.antenna.model.entity.NewsList;
import br.com.ledtom.antenna.model.enums.MachineCommand;
import br.com.ledtom.antenna.model.enums.MachineStatus;
import br.com.ledtom.antenna.sessioncomponents.ApplicationInfo;
import br.com.orangescript.antenna.news.core.Source;
import br.com.orangescript.antenna.news.core.publisher.PublisherService;

@Resource
public class MachineController {
    private final Result result;
    private final MachineService service;
    private final ChannelService channelService;
    private final CommandService commandService;
    private final PublisherService publisherService;

    @SuppressWarnings("unused")
    private ApplicationInfo appInfo;
    
    public MachineController(
            Result result,
            MachineService service,
            ChannelService channelService,
            CommandService commandService,
            PublisherService publisherService,
            ApplicationInfo appInfo) {

        this.result = result;
        this.service = service;
        this.channelService = channelService;
        this.commandService = commandService;
        this.publisherService = publisherService;
        this.appInfo = appInfo;
    }

    @Restricted
    @Get @Path("/machines")
    public void list() {
        result.include("channels", channelService.list());
        result.include("machines", service.list());
        result.include("pending", service.listPending());
    }
    
    @Restricted
    @Get @Path("/machines/pending")
    public void listPending() {
        result.include("machines", service.listPending());
    }
    
    @Get @Path("/machines/requestSync/{name}/{hash}")
    public void requestSync(String name, String hash) {
        Machine machine = Machine.create(name, hash);
        service.save(machine);
        RequestSyncResponse response = new RequestSyncResponse(0);

        result.use(Results.json()).withoutRoot().from(response).serialize();
    }

    @Restricted
    @Put @Path("/machines/accept/{id}")
    public void accept(Machine machine) {
        service.accept(service.find(machine.getId()));
        result.redirectTo(this).list();
    }

    @Get @Path("/machines/notify/{hash}")
    public void notify(String hash) {
        NotifyResponse response = null;
        try {
            service.notify(hash);
            response = new NotifyResponse(0);
        } catch(NoResultException e) {
            response = new NotifyResponse(1);
        } catch(IllegalStateException e) {
            response = new NotifyResponse(1);
        }
        
        result.use(Results.json()).withoutRoot().from(response).serialize();
    }

    @Get @Path("/machines/checkStatus/{hash}")
    public void checkStatus(String hash) {
        CheckMachineStatusResponse response = null;

        try {
            response = new CheckMachineStatusResponse(service.checkStatus(hash));

        } catch(NoResultException e) {
            response = new CheckMachineStatusResponse(MachineStatus.INEXISTENT);
        }
        
        result.use(Results.json()).withoutRoot().from(response).serialize();
    }

    @Restricted
    @Put @Path("/machines/setChannel/{machine}/{channel}")
    public void setChannel(Machine machine, Channel channel) {
        machine = service.find(machine.getId());
        service.setChannel(machine, channelService.find(channel.getId()));
        result.redirectTo(CommandController.class).create(MachineCommand.CHANGE_CHANNEL, machine.getChannel().getId().toString(), machine);
    }

    @Get @Path("/machines/getSchedule/{hash}")
    public void getSchedule(String hash) {
        GetVideoListResponse response = new GetVideoListResponse(service.getVideoList(hash));
        System.out.println("im on the getschedule > " + response.getVideos().toString());
        result.use(Results.json()).withoutRoot().from(response).include("videos").serialize();
    }

    @Get @Path("/machines/getCommandQueue/{hash}")
    public void getCommandQueue(String hash) {
        GetCommandsResponse response = new GetCommandsResponse(service.getCommands(hash));
        result.use(Results.json()).withoutRoot().from(response).include("commands").serialize();
    }

    @Get @Path("/machines/getNextCommand/{hash}")
    public void getNextCommand(String hash) {
        GetCommandsResponse response = new GetCommandsResponse(service.getCommands(hash));
        Iterator<Command> iterator = response.getCommands().iterator();
        Command command = iterator.hasNext() ? iterator.next() : null;
        if (command != null) {
            commandService.markAsExecuting(command);
            result.use(Results.json()).withoutRoot().from(command).exclude("status").exclude("requested").serialize();
        } else {
            result.use(Results.http()).body("{}");
        }
    }

    @Get @Path("/machines/notifyCommandExecution/{commandId}")
    public void commandExecutedNotify(long commandId) {
        result.forwardTo(CommandController.class).commandExecutedNotify(commandId);
    }

    @Get @Path("/machines/getNews/{hash}")
    public void getNews(String hash) {
        try {
            NewsList newsList = new NewsList();
            newsList.setNews(publisherService.getNews(Arrays.asList(Source.UOL)));
            result.use(Results.json()).withoutRoot().from(newsList).recursive().serialize();

        } catch (Exception e) {
            result.use(Results.http()).body("{}");
        }
    }
}
