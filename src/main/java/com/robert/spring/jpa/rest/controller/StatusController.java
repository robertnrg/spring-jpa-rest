package com.robert.spring.jpa.rest.controller;

import com.robert.spring.jpa.rest.dto.StatusDTO;
import com.robert.spring.jpa.rest.exception.NotFoundEntityException;
import com.robert.spring.jpa.rest.service.StatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AccessLevel;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 23/10/17
 **/
@RestController
@RequestMapping("/status")
@Api(value = "StatusController")
public class StatusController {

    @Autowired
    @Getter(AccessLevel.PRIVATE)
    private StatusService srvStatus;

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves one Status", response = StatusDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = StatusDTO.class),
            @ApiResponse(code = 404, message = "Unable to find Status")})
    public ResponseEntity<StatusDTO> getByCode(@PathVariable String code) throws Exception {
        LOGGER.info("code={}", code);
        final StatusDTO statusDTO = this.getSrvStatus().getByCode(code);
        LOGGER.info("{}", statusDTO);
        if (null == statusDTO) {
            throw new NotFoundEntityException("Status not found");
        }

        return new ResponseEntity<>(statusDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves all the Status", response = StatusDTO.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = StatusDTO.class),
            @ApiResponse(code = 404, message = "Unable to get Status")})
    public ResponseEntity<List<StatusDTO>> getAll() throws Exception {
        final List<StatusDTO> lstStatusDTO = this.getSrvStatus().getAll();
        LOGGER.debug("{}", lstStatusDTO);

        return new ResponseEntity<>(lstStatusDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/filter/{code}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves all the Status", response = StatusDTO.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = StatusDTO.class),
            @ApiResponse(code = 404, message = "Unable to get Status")})
    public ResponseEntity<List<StatusDTO>> filterByCode(@PathVariable String code) throws Exception {
        LOGGER.info("code={}", code);
        final List<StatusDTO> lstStatusDTO = this.getSrvStatus().filterByCode(code);
        LOGGER.debug("{}", lstStatusDTO);

        return new ResponseEntity<>(lstStatusDTO, HttpStatus.OK);
    }

}