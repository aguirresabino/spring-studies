package io.github.aguirresabino.studies.kafkaspringcloudproducerexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableBinding(GreetingsStreams.class)
@RestController
@RequestMapping(value = "/producer")
public class KafkaSpringCloudProducerExampleApplication {

    @Autowired
    private GreetingsStreams greetingsStreams;

    public static void main(String[] args) {
        SpringApplication.run(KafkaSpringCloudProducerExampleApplication.class, args);
    }

    @PostMapping(value = "/publish")
    public String sendMessage(@RequestParam("message") String message) {
        MessageChannel messageChannel = greetingsStreams.outboundGreetings();

        System.out.println("alô, foi enviado!!!!");

        messageChannel.send(MessageBuilder
                .withPayload(message)
                .build());
        return message;
    }

    @StreamListener(GreetingsStreams.INPUT)
    public void handleGreetings(String message) {
        System.out.println(message);
    }

}
