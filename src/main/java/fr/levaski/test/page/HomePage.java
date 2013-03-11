package fr.levaski.test.page;

import java.io.Serializable;
import java.util.Date;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.atmosphere.EventBus;
import org.apache.wicket.atmosphere.Subscribe;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import fr.levaski.test.model.event.AlertEvent;
import fr.levaski.test.model.event.DateEvent;
import fr.levaski.test.panel.ModalAlertPanel;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	private final Component timeLabel;
	private TextField<String> message;
	private final ModalWindow modal;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		add(timeLabel = new Label("time", new Model<Serializable>(
				new Date().toString())).setOutputMarkupId(true));
		Form<Void> form = new Form<Void>("form");
		add(form);
		form.add(message = new TextField<String>("message", Model.of("")));
		form.add(new AjaxSubmitLink("send", form) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				EventBus.get().post(new AlertEvent(message.getModelObject()));
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
			}
		});
		setVersioned(false);
		modal = new ModalWindow("alert");
		add(modal);
		modal.setCookieName("modal-alert");
		modal.setResizable(false);
		modal.setInitialWidth(30);
		modal.setInitialHeight(15);
		modal.setWidthUnit("em");
		modal.setHeightUnit("em");
		modal.setTitle("ALERT");
		modal.setCssClassName(ModalWindow.CSS_CLASS_GRAY);
	}

	@Subscribe()
	public void updateTime(AjaxRequestTarget target, DateEvent event) {
		timeLabel.setDefaultModelObject(event.getDate().toString());
		target.add(timeLabel);
	}

	@Subscribe()
	public void displayMessage(AjaxRequestTarget target, AlertEvent event) {
		modal.addOrReplace(new ModalAlertPanel(modal.getContentId(), Model
				.of(event.getAlert())));
		modal.show(target);
	}
}
