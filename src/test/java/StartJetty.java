import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.servlet.ServletContainer;

import com.google.inject.servlet.GuiceFilter;

public class StartJetty {

	//	<filter>
	//	<filter-name>guice-filter</filter-name>
	//	<filter-class>com.google.inject.servlet.GuiceFilter</filter-class>
	//	<async-supported>true</async-supported>
	//</filter>
	//
	//<filter-mapping>
	//	<filter-name>guice-filter</filter-name>
	//	<url-pattern>/*</url-pattern>
	//</filter-mapping>

	public static void main(String[] args) throws Exception {

		Server server = new Server(8080);

		WebAppContext bb = new WebAppContext();
		bb.setServer(server);

		bb.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));

		ServletHolder holder = bb.addServlet(ServletContainer.class, "/*");
		holder.setInitParameter("javax.ws.rs.Application", "net.ludeke.rest.jersey.MyApplication");

		bb.addServlet(holder, "/*");
		bb.setContextPath("/");
		bb.setWar("src/main/webapp");

		server.setHandler(bb);

		try {
			System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
			server.start();
			while (System.in.available() == 0) {
				Thread.sleep(5000);
			}
			server.stop();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}
}