package com.robert.spring.jpa.rest.service.impl;

import com.google.common.base.Strings;
import com.robert.spring.jpa.rest.domain.User;
import com.robert.spring.jpa.rest.dto.UserDTO;
import com.robert.spring.jpa.rest.repository.StatusRepository;
import com.robert.spring.jpa.rest.repository.UserRepository;
import com.robert.spring.jpa.rest.service.UserService;
import com.robert.spring.jpa.rest.util.Utils;
import lombok.AccessLevel;
import lombok.Getter;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 20/10/17
 **/
@Service("srvUser")
public class UserServiceImpl implements UserService {

    @Autowired
    @Getter(AccessLevel.PRIVATE)
    private UserRepository userRepository;

    @Autowired
    @Getter(AccessLevel.PRIVATE)
    private StatusRepository statusRepository;

    @Autowired
    @Getter(AccessLevel.PRIVATE)
    private DozerBeanMapperFactoryBean dozerBean;

    private static final String MAPPING_USER_DTO = "userDTO";

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Long saveUser(UserDTO userDTO) throws Exception {
        User user = new User();
        dozerBean.getObject().map(userDTO, user, MAPPING_USER_DTO);
        String username = "";
        for (int i = 1; i <= 3; i = i + 1) {
            username = Utils.generateUserName(userDTO.getName(), userDTO.getLastName(), i);
            if (!this.exist(username)) {
                break;
            }
            username = "";
        }
        if (Strings.isNullOrEmpty(username)) {
            throw new RuntimeException("Error al guardar el usuario");
        }
        user.setUsername(username);
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        user.setStatus(this.getStatusRepository().findByCode("LOCK"));
        LOGGER.debug("{}", user);
        user = this.getUserRepository().save(user);
        LOGGER.info("{}", user);


        return user.getId();
    }

    @Override
    public UserDTO getByUsername(String username) throws Exception {
        UserDTO userDTO = null;
        try {
            final User user = this.getUserRepository().findByUsername(username);
            if (null != user) {
                userDTO = new UserDTO();
                dozerBean.getObject().map(user, userDTO, MAPPING_USER_DTO);
            }
        } catch (Exception ex) {
            LOGGER.error("{}", ex.getMessage());
            throw ex;
        }

        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() throws Exception {
        List<UserDTO> lstUserDTO;
        try {
            final List<User> lstUser = this.getUserRepository().findAll();
            lstUserDTO = new ArrayList<>();
            for (final User user : lstUser) {
                final UserDTO userDTO = new UserDTO();
                dozerBean.getObject().map(user, userDTO, MAPPING_USER_DTO);
                lstUserDTO.add(userDTO);
            }
        } catch (Exception ex) {
            LOGGER.error("{}", ex.getMessage());
            throw ex;
        }

        return lstUserDTO;
    }

    @Override
    public List<UserDTO> filterByUsername(String username) throws Exception {
        List<UserDTO> lstUsersDTO;
        try {
            final List<User> lstUsers = this.getUserRepository().filterByUsername("%" + (null != username ? username : "") + "%");
            lstUsersDTO = new ArrayList<>();
            for (final User user : lstUsers) {
                final UserDTO userDTO = new UserDTO();
                dozerBean.getObject().map(user, userDTO, MAPPING_USER_DTO);
                lstUsersDTO.add(userDTO);
            }
        } catch (Exception ex) {
            LOGGER.error("{}", ex.getMessage());
            throw ex;
        }

        return lstUsersDTO;
    }

    @Override
    public Boolean exist(String username) throws Exception {
        try {
            final User user = this.getUserRepository().findByUsername(username);

            return null != user;
        } catch (Exception ex) {
            LOGGER.error("{}", ex.getMessage());
            throw ex;
        }
    }

}