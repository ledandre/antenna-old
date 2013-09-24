package br.com.ledtom.antenna.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "channels")
public class Channel {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Getter @Setter private Long id;
	
	@Column(name = "name", length = 30)
	@Getter @Setter private String name;
	
	@Column(name = "description")
	@Getter @Setter private String description;
	
	@OneToMany(mappedBy = "channel")
	@Getter @Setter private List<Schedule> schedules;
}
