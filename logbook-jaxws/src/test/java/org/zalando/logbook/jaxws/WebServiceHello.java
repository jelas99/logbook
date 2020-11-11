package org.zalando.logbook.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "EndImpl")
public class WebServiceHello{

	
	@WebMethod
	public String say(String text) {
		return "OK";
	}
}
