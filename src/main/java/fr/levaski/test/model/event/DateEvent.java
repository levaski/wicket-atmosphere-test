package fr.levaski.test.model.event;

import java.io.Serializable;
import java.util.Date;

public class DateEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9115343061200212410L;

	private Date date;

	public DateEvent(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
