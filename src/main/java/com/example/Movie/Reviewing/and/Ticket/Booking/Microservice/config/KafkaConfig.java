package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.config;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.TicketResponse;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;

import java.util.Properties;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("notification")
                .build();
    }

}
