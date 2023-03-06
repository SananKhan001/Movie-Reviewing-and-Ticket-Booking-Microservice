package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.service;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.IdNotFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.exception.NoMatchFoundException;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model.*;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.CustomerRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.ShowRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.TicketRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.repository.UserRepository;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.request.BookingCreateRequest;
import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.TicketResponse;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    NewTopic topic;

    @Autowired
    KafkaTemplate<String,TicketResponse> kafkaTemplate;

    public TicketResponse bookTicket(BookingCreateRequest bookingCreateRequest, Customer customer) throws IdNotFoundException, NoMatchFoundException {

        Optional<Show> optionalShow = showRepository.findById(bookingCreateRequest.getShowId());

        if(optionalShow.isEmpty()){
            throw new IdNotFoundException("Given Show Id is Invalid");
        }

        HashSet<String> requestedSeats = bookingCreateRequest.getSeatsNumbers();
        List<ShowSeat> showSeatEntities = optionalShow.get().getSeats();

        showSeatEntities = showSeatEntities.stream()
                .filter(seat -> seat.getSeatType().equals(bookingCreateRequest.getSeatType())
                        && !seat.isBooked()
                        && requestedSeats.contains(seat.getSeatNumber()))
                .collect(Collectors.toList());

        if(showSeatEntities.size() != requestedSeats.size()){
            throw new NoMatchFoundException("Seats Not Available for Booking");
        }

        Ticket ticket = Ticket.builder()
                .customer(customer)
                .show(optionalShow.get())
                .seats(showSeatEntities)
                .build();

        double amount = 0.0;
        String allottedSeats = "";

        for(ShowSeat seatsEntity : showSeatEntities){
            seatsEntity.setBooked(true);
            seatsEntity.setBookedAt(new Date());
            seatsEntity.setTicket(ticket);

            amount += seatsEntity.getRate();
            allottedSeats += seatsEntity.getSeatNumber() + " ";
        }

        ticket.setAmount(amount);
        ticket.setAllotedSeats(allottedSeats);
        ticket.setShow(optionalShow.get());
        ticket.setCustomer(customer);

        ticket = ticketRepository.save(ticket);

        TicketResponse ticketResponse = ticket.to();

        // Notification Logic
        NotificationMessage notificationMessage = NotificationMessage.builder()
                .ticketResponse(ticketResponse)
                .userResponse(customer.getMyUser().to(customer))
                .build();

        Message<NotificationMessage> message = MessageBuilder
                .withPayload(notificationMessage)
                .setHeader(KafkaHeaders.TOPIC,topic.name())
                .build();

        kafkaTemplate.send(message);

        return ticketResponse;

    }

    public TicketResponse getTicket(long id) {

        return ticketRepository.findById(id).get().to();

    }

    public List<TicketResponse> getTickets(long customerId){

        Optional<Customer> customer = customerRepository.findById(customerId);

        return customer.get().getTicketList().stream().map(x -> x.to()).collect(Collectors.toList());

    }
}
