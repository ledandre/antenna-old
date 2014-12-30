package br.com.ledtom.antenna.domain.periodic;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import br.com.ledtom.antenna.configuration.Config;
import br.com.ledtom.antenna.domain.service.MachineService;
import br.com.ledtom.antenna.model.entity.Machine;
import br.com.ledtom.antenna.model.enums.MachineStatus;

public class Synchronizer extends TimerTask {
    private MachineService machineService;

    private Synchronizer() {}

    public MachineService getMachineService() {
        return machineService;
    }

    public void setMachineService(MachineService machineService) {
        this.machineService = machineService;
    }

    public static Synchronizer getInstance() {
         Synchronizer synchronizer = new Synchronizer();
         synchronizer.machineService = new MachineService();
         
         return synchronizer;
    }
    
    /**
     * Checks all machine's status updating it for the 
     * machines with no sync within 5 minutes after last sync.
     */
    private void checkMachineStatus() {
        List<Machine> machines = machineService.list();
        
        for (Machine machine : machines) {
            if (outOfSync(machine.getLastUpdated())) {
                machine.setStatus(MachineStatus.UNSYNCHRONIZED);
            } else {
                machine.setStatus(MachineStatus.SYNCHRONIZED);
            }
            machineService.save(machine);
        }
    }
    
    private boolean outOfSync(Date lastSync) {
        Date now = Calendar.getInstance().getTime();
        long diff = now.getTime() - lastSync.getTime();
        
        return ((diff / 1000 / 60) >= Config.getMachineMaxSyncTime());
    }

    @Override
    public void run() {
        checkMachineStatus();
    }
}
