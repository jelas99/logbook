package org.zalando.logbook.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(
		serviceName = "HelloService", portName = "HelloPort",
targetNamespace = "http://service.ws.sample/",
endpointInterface = "org.zalando.logbook.jaxws.Hello")
public class WebServiceHello implements Hello {

	
	@WebMethod
	public String say(String name) {
		return "Hello " + name;
	}
}
