package com.sdi.business.integration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(
activationConfig = {
@ActivationConfigProperty(
propertyName = "destination",
propertyValue = "queue/NotaneitorQueue")
})
public class ChatListener implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		
	}

}
