package br.com.ledtom.antenna.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "videos")
public class Video {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Getter @Setter private Long id;
	
	@Column(name = "name", nullable = false, length = 100)
	@Getter @Setter private String name;
	
	@Column(name = "file", nullable = false, length = 100)
	@Getter @Setter private String file;
	
	@Column(name = "description", nullable = true)
	@Getter @Setter private String description;
}