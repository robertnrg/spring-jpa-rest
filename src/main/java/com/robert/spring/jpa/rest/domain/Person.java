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
@Table(name = "t_person")
public class Person implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "last_name", nullable = false, length = 40)
    private String lastName;

    @Column(name = "second_last_name", length = 40)
    private String secondLastName;

    @Column(name = "email", length = 50)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "gender", nullable = false, length = 1)
    private String gender;


}
