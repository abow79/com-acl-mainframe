package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.message.api;

import javax.jms.Session;
import org.springframework.jms.support.JmsMessageHeaderAccessor;

public interface MessageListenerApi {

	String receiveMessage(String message, JmsMessageHeaderAccessor jmsMessageHeaderAccessor,
			Session session);
}
