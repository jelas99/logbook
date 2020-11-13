package org.zalando.logbook.jaxws;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zalando.logbook.jaxws.ClientAndServerTest.WebConfigure2;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WebConfigure2.class)
public class ClientAndServerTest {

	
	@Test
	public void test() {
		  JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		    factory.setAddress("http://localhost:8080/Hello");
		    factory.setServiceClass(WebServiceHello.class);
		  //  factory.getHandlers().add(e); //-- Lgoging handler
		    Hello client = (Hello) factory.create();
		    
		    String ret = client.say("Joel");
		    
		    System.out.println(ret);
		    
	}

	@SpringBootApplication
	static class WebConfigure2{
		 public static void main(String[] args) throws Exception {
		        SpringApplication.run(WebConfigure2.class, args);
		    }
	}
	
	@Configuration
	static class WebConfigure {

		@Autowired
		private Bus bus;

		@Bean
		public Endpoint endpoint() {
			EndpointImpl endpoint = new EndpointImpl(bus, new WebServiceHello());
			endpoint.publish("/Hello");
			return endpoint;
		}
	}
}
