package br.com.ledtom.antenna.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ledtom.antenna.model.enums.CommandStatus;
import br.com.ledtom.antenna.model.enums.MachineCommand;

@Entity
@Table(name = "commands")
public class Command {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "command", nullable = false)
    private MachineCommand command;

    @Column(name = "argument", nullable = true)
    private String argument;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "requested", nullable = false)
    private Date requested;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "executed", nullable = true)
    private Date executed;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true)
    private CommandStatus status;

    @ManyToOne
    @JoinColumn(name = "machine")
    private Machine machine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MachineCommand getCommand() {
        return command;
    }

    public void setCommand(MachineCommand command) {
        this.command = command;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public Date getRequested() {
        return requested;
    }

    public void setRequested(Date requested) {
        this.requested = requested;
    }

    public Date getExecuted() {
        return executed;
    }

    public void setExecuted(Date executed) {
        this.executed = executed;
    }

    public CommandStatus getStatus() {
        return status;
    }

    public void setStatus(CommandStatus status) {
        this.status = status;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
