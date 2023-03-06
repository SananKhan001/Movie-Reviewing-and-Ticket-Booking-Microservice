package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.NotificationMessage;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.TicketResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    private static Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    public void sendNotification(NotificationMessage notificationMessage){

        String message = createMessage(notificationMessage.getTicketResponse());

        try{
            sendEmailToUser(message,notificationMessage.getUserResponse().getEmail());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            sentSMSToUser(message,notificationMessage.getUserResponse().getMobile());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void sentSMSToUser(String message,String mobile) {
        LOGGER.info("SMS --> {} \n To this number --> {}",message,mobile);
    }

    private void sendEmailToUser(String message,String email) {

        LOGGER.info("Email --> {} \n To this email --> ",message,email);

        SimpleMailMessage prepareMessage = new SimpleMailMessage();
        prepareMessage.setFrom("sanan3359@gmail.com");
        prepareMessage.setTo(email);
        prepareMessage.setText(message);
        prepareMessage.setSubject("Ticket Booking Service");

        mailSender.send(prepareMessage);

    }

    private String createMessage(TicketResponse ticketResponse) {

        long ticketId = ticketResponse.getId();
        LocalDateTime timing = ticketResponse.getShowTime();
        String seats = ticketResponse.getAllottedSeats();
        double amount = ticketResponse.getAmount();
        String movie = ticketResponse.getMovieName();
        Date bookedAt = ticketResponse.getBookedAt();
        String theatre = ticketResponse.getTheaterName();
        String address = ticketResponse.getAddress();


        String message = "Your Ticket Is Booked\n" +
                "Ticket Id : " + ticketId + "\n" +
                "Ticket Booked At : " + bookedAt + "\n" +
                "Theatre : " + theatre + "\n" +
                "Movie : " + movie + "\n" +
                "Seats : " + seats + "\n" +
                "Timing : " + timing + "\n" +
                "Address : " + address + "\n" +
                "Amount Paid : " + amount;

        return message;

    }

}
