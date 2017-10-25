package com.robert.spring.jpa.rest.service;

import com.robert.spring.jpa.rest.dto.PermissionDTO;

import java.util.List;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 23/10/17
 **/
public interface PermissionService {

    PermissionDTO getByCode(String code) throws Exception;

    List<PermissionDTO> getAll() throws Exception;

    List<PermissionDTO> filterByCode(String code) throws Exception;

}