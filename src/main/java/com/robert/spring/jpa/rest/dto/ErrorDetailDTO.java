package com.robert.spring.jpa.rest.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 23/10/17
 **/
@Data
@AllArgsConstructor
@JsonPropertyOrder({"title","detail","status"})
public class ErrorDetailDTO implements java.io.Serializable {

    private String title;
    private int status;
    private String detail;

}