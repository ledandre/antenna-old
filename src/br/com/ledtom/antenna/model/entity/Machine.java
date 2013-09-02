package br.com.ledtom.antenna.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
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
	@Getter @Setter private Long id;

	@Column(name = "name")
	@Getter @Setter private String name;

	@Column(name = "hash")
	@Getter @Setter private String hash;

	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	@Getter @Setter private MachineStatus status;
	
	@Column(name = "last_updated")
	@Temporal(TemporalType.DATE)
	@Getter @Setter private Date lastUpdated;
}
