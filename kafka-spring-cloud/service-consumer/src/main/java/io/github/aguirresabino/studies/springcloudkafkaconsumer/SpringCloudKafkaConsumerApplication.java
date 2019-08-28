package io.github.aguirresabino.studies.springcloudkafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@SpringBootApplication
@EnableBinding(GreetingsStreams.class)
public class SpringCloudKafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudKafkaConsumerApplication.class, args);
    }

    @StreamListener(GreetingsStreams.INPUT)
    public void handleGreetings(String message) {
        System.out.println(message);
    }
}
