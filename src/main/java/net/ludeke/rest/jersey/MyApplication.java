package net.ludeke.rest.jersey;

import javax.inject.Inject;

import net.ludeke.rest.guice.GuiceModule;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import com.google.inject.Guice;

public class MyApplication extends ResourceConfig {

	@Inject
	public MyApplication(ServiceLocator serviceLocator) {
		packages("com.example.jersey");

		System.out.println("Registering injectables...");

		GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

		GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
		guiceBridge.bridgeGuiceInjector(Guice.createInjector(new GuiceModule()));
	}
}