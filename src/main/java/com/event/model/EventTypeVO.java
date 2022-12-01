package com.event.model;

import java.util.Arrays;

public class EventTypeVO {
	private Integer eventClassNumber;
	private String eventClassName;
	private byte[] eventClassState;

	@Override
	public String toString() {
		return "EventType [eventClassNumber=" + eventClassNumber + ", eventClassName=" + eventClassName
				+ ", eventClassState=" + Arrays.toString(eventClassState) + "]";
	}

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

	public byte[] getEventClassState() {
		return eventClassState;
	}

	public void setEventClassState(byte[] eventClassState) {
		this.eventClassState = eventClassState;
	}

}
