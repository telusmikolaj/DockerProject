package org.telusmikolaj.consumer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class MessageConsumer {

    private final MessageRepository messageRepository;

    @RabbitListener(queues = "messageQueue")
    public void receiveMessage(String message) {
        log.info("Received message: {}", message);
        Message messageEntity = new Message();
        messageEntity.setContent(message);
        messageRepository.save(messageEntity);
    }
}