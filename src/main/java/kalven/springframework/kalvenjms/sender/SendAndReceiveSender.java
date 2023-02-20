package kalven.springframework.kalvenjms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import kalven.springframework.kalvenjms.config.JmsConfig;
import kalven.springframework.kalvenjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Project kalven-jms
 * @Author kalvens on 2/19/23
 */
@Component
@RequiredArgsConstructor
public class SendAndReceiveSender {
    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;
    @Scheduled(fixedRate = 2000)
    public void sendAndReceiveMessage() throws JMSException {
        HelloWorldMessage helloWorldMessage = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Send and Receive Class Message")
                .build();

        Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message sendAndReceiveMessage = null;
                try {
                    sendAndReceiveMessage = session.createTextMessage(objectMapper.writeValueAsString(helloWorldMessage));
                    sendAndReceiveMessage.setStringProperty("_type", "kalven.springframework.kalvenjms.model.HelloWorldMessage");
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Exception caugth in createMessage");
                }
                return sendAndReceiveMessage;
            }
        });
        System.out.println(receivedMessage.getBody(String.class));
    }
}
