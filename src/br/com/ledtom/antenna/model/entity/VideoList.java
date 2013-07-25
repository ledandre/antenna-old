
@Entity
@Table(name = "videoLists")
@AllArgsConstructor
public class VideoList {
	@Id @GeneratedValue
	@Column(name = "id")
	@Getter @Setter private Long id;
	
	@Column(name = "video")
	@Getter @Setter private Video video;
}
