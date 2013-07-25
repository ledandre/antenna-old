
@Entity
@Table(name = "schedules")
@AllArgsConstructor
public class Schedule {
	@Id @GeneratedValue
	@Getter @Setter private Long id;
	
	@ManyToOne
	@JoinColumn(name = "channel")
	@Getter @Setter private Channel channel;
	
	@OneToMany
	@Getter @Setter List<VideoList> videoList;
}
