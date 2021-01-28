package sion.bookmanagement.service.survey;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Survey {
	private int idx;
	private Date startDate;
	private Date endDate;
	private String subject;
	private String target;
	private int joinMemberCount;
	private int isClosed;
	
	public Survey() {}
	
	public Survey(Date startDate, Date endDate, String subject, String target, int isClosed) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.subject = subject;
		this.target = target;
		this.isClosed = isClosed;
	}
}
