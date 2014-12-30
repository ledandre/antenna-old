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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ledtom.antenna.configuration.Config;
import br.com.ledtom.antenna.model.enums.MachineStatus;

@Entity
@Table(name = "machines")
public class Machine {
    
    public Machine(){}

    public static Machine create(String name, String hash) {
        Machine machine = new Machine();
        machine.name = name;
        machine.hash = hash;
        machine.status = MachineStatus.PENDING;
        
        return machine;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "hash", unique = true)
    private String hash;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private MachineStatus status;
    
    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
    
    @JoinColumn(name = "channel", nullable = true)
    @OneToOne
    private Channel channel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public MachineStatus getStatus() {
        return status;
    }

    public void setStatus(MachineStatus status) {
        this.status = status;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getStatusDescription() {
        if (this.status.equals(MachineStatus.INEXISTENT)) {
            return Config.getMachineStatusInexistentDescription();
            
        } else if (this.status.equals(MachineStatus.ACCEPTED)) {
            return Config.getMachineStatusAceptedDescription();
        
        } else if (this.status.equals(MachineStatus.OFF)) {
            return Config.getMachineStatusOffDescription();
        
        } else if (this.status.equals(MachineStatus.PENDING)) {
            return Config.getMachineStatusPendingDescription();
        
        } else if (this.status.equals(MachineStatus.SYNCHRONIZED)) {
            return Config.getMachineStatusSynchronizedDescription();
        
        } else if (this.status.equals(MachineStatus.UNSYNCHRONIZED)) {
            return Config.getMachineStatusUnsynchronizedDescription();
        
        } else if (this.status.equals(MachineStatus.UPDATING)) {
            return Config.getMachineStatusUpdatingDescription();
        
        } else {
            return Config.getMachineStatusUnknownDescription();
        }
    }
}
