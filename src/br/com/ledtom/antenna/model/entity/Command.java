
@Entity
@Table(name = "command")
public final class Command {

  @Enumerated(EnumType.STRING)
  @Column(name = "command", nullable = false)
  @Getter @Setter private final MachineCommand command;
  
  @Column(name = "argument", nullable = true)
  @Getter @Setter private final String argument;
  
  @Temporal(TemporalType.TIMESTAMP, nullable = false)
  @Column(name = "requested", nullable = false)
  @Getter @Setter private final Date requested;

  @Temporal(TemporalType.TIMESTAMP, nullable = true)
  @Getter @Setter private Date executed;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = true)
  @Getter @Setter private CommandStatus status;
  
  @JoinColumn(name = "machine", nullable = false)
  @OneToOne
  @Getter @Setter private final Machine machine;

}
