package com.robert.spring.jpa.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 20/10/17
 **/
@Data
@ApiModel(value = "User", description = "Datos del usuario")
public class UserDTO implements java.io.Serializable {

    @NotEmpty
    @Size(min = 1, max = 40)
    @Pattern(regexp = "[\\p{L}\\s]+")
    private String name;

    @NotEmpty
    @Size(min = 1, max = 40)
    @Pattern(regexp = "[\\p{L}\\s]+")
    private String lastName;

    @Size(max = 40)
    private String secondLastName;

    @NotEmpty
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;

    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Mexico_City")
    private String dateOfBirth;

    @NotEmpty
    @Pattern(regexp = "[MH]")
    private String gender;

}
