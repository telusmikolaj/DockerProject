package org.telusmikolaj.producer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProducerController {

    private final RabbitTemplate rabbitTemplate;
    @Autowired
    public ProducerController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @PostMapping("/message")
    public void postMessage(@RequestParam String message) {
        log.info("Sending message: {}", message);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "routingKey", message);
    }
}
