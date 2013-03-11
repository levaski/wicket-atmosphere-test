package fr.levaski.test.panel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class ModalAlertPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7608917302004233198L;

	public ModalAlertPanel(String id, Model<String> alert) {
		super(id);
		Label info = new Label("alert", alert);
		add(info);
	}
}
