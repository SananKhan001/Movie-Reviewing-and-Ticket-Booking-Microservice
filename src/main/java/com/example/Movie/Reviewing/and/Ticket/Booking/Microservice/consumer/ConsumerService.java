package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.consumer;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.NotificationMessage;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.TicketResponse;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@EnableKafka
@Service
@Configuration
public class ConsumerService {

    @Autowired
    NotificationService notificationService;

    @KafkaListener(topics = "notification"
            ,groupId = "notify")
    public void consume(NotificationMessage notificationMessage){

        notificationService.sendNotification(notificationMessage);

    }

}
