package com.robert.spring.jpa.rest.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 20/10/17
 **/
@Data
@Entity
@Table(name = "t_user")
public class User implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "t_person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "t_status_id")
    private Status status;

}