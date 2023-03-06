package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin_table")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "admin_name",nullable = false)
    private String name;

    @Column(name = "mobile",nullable = false)
    private String mobile;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"customer","admin"})
    private User myUser;

}
