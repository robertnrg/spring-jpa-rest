package com.robert.spring.jpa.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 23/10/17
 **/
@Data
public class CatalogDTO implements java.io.Serializable {

    @NotEmpty
    @Size(min = 1, max = 20)
    private String code;

    @Size(min = 1, max = 70)
    private String description;

    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Mexico_City")
    private String createdDate;

}