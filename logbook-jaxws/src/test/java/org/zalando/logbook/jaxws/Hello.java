package org.zalando.logbook.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://service.ws.sample/", name = "Hello")
public interface Hello {
	
	@WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "sayHello",
                    targetNamespace = "http://service.ws.sample/",
                    className = "org.zalando.logbook.jaxws.SayHello")
    @WebMethod(action = "urn:SayHello")
    @ResponseWrapper(localName = "sayHelloResponse",
                     targetNamespace = "http://service.ws.sample/",
                     className = "org.zalando.logbook.jaxws.SayHelloResponse")
	String say(String name);
}
