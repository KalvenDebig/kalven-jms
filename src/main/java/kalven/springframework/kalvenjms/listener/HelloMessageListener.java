package kalven.springframework.kalvenjms.listener;

import jakarta.jms.Message;
import kalven.springframework.kalvenjms.config.JmsConfig;
import kalven.springframework.kalvenjms.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @Project kalven-jms
 * @Author kalvens on 2/19/23
 */
@Component
public class HelloMessageListener {
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       MessageHeaders headers, Message message) {

        System.out.println("Message received");
        System.out.println(helloWorldMessage);
    }
}
