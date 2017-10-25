package com.robert.spring.jpa.rest.controller;

import com.robert.spring.jpa.rest.dto.ErrorDetailDTO;
import com.robert.spring.jpa.rest.dto.UserDTO;
import com.robert.spring.jpa.rest.exception.NotFoundEntityException;
import com.robert.spring.jpa.rest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AccessLevel;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.List;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 20/10/17
 **/
@RestController
@RequestMapping("/user")
@Api(value = "UserController")
public class UserController {

    @Autowired
    @Getter(AccessLevel.PRIVATE)
    private UserService srvUser;

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Register user", notes = "The newly created Limit Id will be sent in the location response header")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Limit Created Successfully"),
            @ApiResponse(code = 400, message = "Error creating limit", response = ErrorDetailDTO.class)})
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
        LOGGER.info("{}", userDTO);
        Long id = this.getSrvUser().saveUser(userDTO);

        HttpHeaders responseHeaders = new HttpHeaders();
        final URI newLimitUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
                .buildAndExpand(id).toUri();
        responseHeaders.setLocation(newLimitUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves one User", response = UserDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = UserDTO.class),
            @ApiResponse(code = 404, message = "Unable to find User")})
    public ResponseEntity<UserDTO> getByUserName(@PathVariable String username) throws Exception {
        LOGGER.info("username={}", username);
        final UserDTO userDTO = this.getSrvUser().getByUsername(username);
        LOGGER.info("{}", userDTO);
        if (null == userDTO) {
            throw new NotFoundEntityException("User not found");
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves all the User's", response = UserDTO.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = UserDTO.class),
            @ApiResponse(code = 404, message = "Unable to get User's")})
    public ResponseEntity<List<UserDTO>> getAll() throws Exception {
        final List<UserDTO> lstUserDTO = this.getSrvUser().getAll();
        LOGGER.debug("{}", lstUserDTO);

        return new ResponseEntity<>(lstUserDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/filter/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves all the User's", response = UserDTO.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = UserDTO.class),
            @ApiResponse(code = 404, message = "Unable to get User's"
            )})
    public ResponseEntity<List<UserDTO>> filterByUserName(@PathVariable String username) throws Exception {
        final List<UserDTO> lstUserDTO = this.getSrvUser().filterByUsername(username);
        LOGGER.debug("{}", lstUserDTO);

        return new ResponseEntity<>(lstUserDTO, HttpStatus.OK);
    }

}