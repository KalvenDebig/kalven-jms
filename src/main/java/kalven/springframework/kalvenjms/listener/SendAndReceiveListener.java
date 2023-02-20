package kalven.springframework.kalvenjms.listener;

import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import kalven.springframework.kalvenjms.config.JmsConfig;
import kalven.springframework.kalvenjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Project kalven-jms
 * @Author kalvens on 2/19/23
 */

@Component
@RequiredArgsConstructor
public class SendAndReceiveListener {
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listenOnSendAndReceive(@Payload HelloWorldMessage helloWorldMessage,
                                       MessageHeaders headers, Message message) throws JMSException {
        HelloWorldMessage payLoadMessage = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("test send and receive")
                .build();
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payLoadMessage);
    }
}
