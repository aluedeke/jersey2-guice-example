package net.ludeke.rest.guice;

import com.google.inject.servlet.ServletModule;

public class GuiceModule extends ServletModule {

	@Override
	protected void configureServlets() {
		bind(Service.class);
	}

}
