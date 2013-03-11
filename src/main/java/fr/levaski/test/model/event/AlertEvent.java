package fr.levaski.test.model.event;

import java.io.Serializable;

public class AlertEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2941588758442560251L;

	private String alert;

	public AlertEvent(String alert) {
		this.alert = alert;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

}
