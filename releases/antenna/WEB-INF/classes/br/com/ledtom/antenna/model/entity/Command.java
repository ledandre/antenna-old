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

import lombok.Getter;
import lombok.Setter;
import br.com.ledtom.antenna.model.enums.CommandStatus;
import br.com.ledtom.antenna.model.enums.MachineCommand;

@Entity
@Table(name = "commands")
public class Command {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Getter @Setter private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "command", nullable = false)
	@Getter @Setter private MachineCommand command;
	
	@Column(name = "argument", nullable = true)
	@Getter @Setter private String argument;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "requested", nullable = false)
	@Getter @Setter private Date requested;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "executed", nullable = false)
	@Getter @Setter private Date executed;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = true)
	@Getter @Setter private CommandStatus status;

	@ManyToOne
	@JoinColumn(name = "machine")
	@Getter @Setter private Machine machine;
}
