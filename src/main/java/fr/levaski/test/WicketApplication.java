package fr.levaski.test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.wicket.atmosphere.EventBus;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import fr.levaski.test.model.event.DateEvent;
import fr.levaski.test.page.HomePage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see fr.levaski.test.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	private EventBus eventBus;

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		eventBus = new EventBus(this);

		ScheduledExecutorService scheduler = Executors
				.newScheduledThreadPool(1);
		final Runnable beeper = new Runnable() {
			@Override
			public void run() {
				try {
					eventBus.post(new DateEvent(new Date()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		scheduler.scheduleWithFixedDelay(beeper, 1, 1, TimeUnit.SECONDS);
	}
}
