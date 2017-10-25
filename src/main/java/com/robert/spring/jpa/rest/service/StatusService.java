package com.robert.spring.jpa.rest.service;

import com.robert.spring.jpa.rest.dto.StatusDTO;

import java.util.List;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 23/10/17
 **/
public interface StatusService {

    StatusDTO getByCode(String code) throws Exception;

    List<StatusDTO> getAll() throws Exception;

    List<StatusDTO> filterByCode(String code) throws Exception;

}