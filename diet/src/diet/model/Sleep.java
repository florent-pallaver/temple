package diet.model;

public class Sleep {
	
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private User sleeper;

	@Column(nullable = false)
	private Date day;

	private int duration;
	
}
