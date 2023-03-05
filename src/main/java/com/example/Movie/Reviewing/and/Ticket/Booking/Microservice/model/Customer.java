package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customer_table")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "customer_name",nullable = false)
    private String name;

    @Column(name = "mobile",nullable = false)
    private String mobile;

    @Column(name = "email",nullable = false)
    private String email;

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    // @JsonIgnoreProperties("customer")
    private List<Ticket> ticketList;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"customer","admin"})
    private User myUser;

}
