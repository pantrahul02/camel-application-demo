package com.simplejava.camelmicroservice.routes.a;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimerRouterApp extends RouteBuilder{
	
	@Autowired
	private GetCurrentTimeBean getCurrentTimeBean;

	@Override
	public void configure() throws Exception {

		/*from("timer:first-timer")
		.transform().constant("Time now is" + LocalDateTime.now())
		.to("log:first-timer");*/
		
		//transformation using bean...
		
		from("timer:first-timer")
		//.bean("getCurrentTimeBean")
		.bean(getCurrentTimeBean, "getCurrentTime")
		.to("log:first-timer");//database
	}

}


@Component
class GetCurrentTimeBean{
	public String getCurrentTime() {
		return "Time now is" + LocalDateTime.now();
	}
}

@Component
class SimpleLoggingProcessingComponent{
	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
	
	public void process(String message) {
		logger.info("SimpleLoggingProcessingComponent {}", message);
	}
}

