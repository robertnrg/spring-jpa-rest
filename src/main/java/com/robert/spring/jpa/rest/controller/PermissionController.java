package com.robert.spring.jpa.rest.controller;

import com.robert.spring.jpa.rest.dto.PermissionDTO;
import com.robert.spring.jpa.rest.exception.NotFoundEntityException;
import com.robert.spring.jpa.rest.service.PermissionService;
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
 * @since 24/10/17
 **/
@RestController
@RequestMapping("/permission")
@Api(value = "PermissionController")
public class PermissionController {


    @Autowired
    @Getter(AccessLevel.PRIVATE)
    private PermissionService srvPermission;

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves one Permission", response = PermissionDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = PermissionDTO.class),
            @ApiResponse(code = 404, message = "Unable to find Permission")})
    public ResponseEntity<PermissionDTO> getByCode(@PathVariable String code) throws Exception {
        LOGGER.info("code={}", code);
        final PermissionDTO permissionDTO = this.getSrvPermission().getByCode(code);
        LOGGER.info("{}", permissionDTO);
        if (null == permissionDTO) {
            throw new NotFoundEntityException("Permission not found");
        }

        return new ResponseEntity<>(permissionDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves all the Permission's", response = PermissionDTO.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = PermissionDTO.class),
            @ApiResponse(code = 404, message = "Unable to get Permission's")})
    public ResponseEntity<List<PermissionDTO>> getAll() throws Exception {
        final List<PermissionDTO> lstPermissionsDTO = this.getSrvPermission().getAll();
        LOGGER.debug("{}", lstPermissionsDTO);

        return new ResponseEntity<>(lstPermissionsDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/filter/{code}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves all the Permission's", response = PermissionDTO.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = PermissionDTO.class),
            @ApiResponse(code = 404, message = "Unable to get Permission's")})
    public ResponseEntity<List<PermissionDTO>> filterByCode(@PathVariable String code) throws Exception {
        LOGGER.info("code={}", code);
        final List<PermissionDTO> lstPermissionsDTO = this.getSrvPermission().filterByCode(code);
        LOGGER.debug("{}", lstPermissionsDTO);

        return new ResponseEntity<>(lstPermissionsDTO, HttpStatus.OK);
    }

}