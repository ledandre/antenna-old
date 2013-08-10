package br.com.ledtom.antenna.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "schedules")
public class Schedule {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Getter @Setter private Long id;
	
	@Column(name = "period")
	@Getter @Setter private int period;
	
	@ManyToOne
	@JoinColumn(name = "channel")
	@Getter @Setter private Channel channel;
	
	@OneToMany(cascade = CascadeType.ALL)
	@OrderBy("position")
	@Getter @Setter List<VideoList> videoList;
}
