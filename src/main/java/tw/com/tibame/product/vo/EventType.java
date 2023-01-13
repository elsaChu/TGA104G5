package tw.com.tibame.product.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT_TYPE")
public class EventType implements Serializable{
	private static final long serialVersionUID = 6023493822955193521L;

	@Id
	@Column(name = "eventClassNumber")
	private Integer eventClassNumber;
	
	@Column(name = "eventClassName")
	private String eventClassName;
	
	@Column(name = "eventClassState")
	private Boolean eventClassState;

	public Integer getEventClassNumber() {
		return eventClassNumber;
	}

	public void setEventClassNumber(Integer eventClassNumber) {
		this.eventClassNumber = eventClassNumber;
	}

	public String getEventClassName() {
		return eventClassName;
	}

	public void setEventClassName(String eventClassName) {
		this.eventClassName = eventClassName;
	}

	public Boolean getEventClassState() {
		return eventClassState;
	}

	public void setEventClassState(Boolean eventClassState) {
		this.eventClassState = eventClassState;
	}
	
	
}
