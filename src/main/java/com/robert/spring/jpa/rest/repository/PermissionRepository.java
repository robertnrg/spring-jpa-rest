package com.robert.spring.jpa.rest.repository;

import com.robert.spring.jpa.rest.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 23/10/17
 **/
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query("SELECT p FROM Permission p WHERE UPPER(p.code) = UPPER(:code)")
    Permission findByCode(@Param("code") String code);

    @Query("SELECT p FROM Permission p WHERE UPPER(p.code) LIKE UPPER(:code)")
    List<Permission> filterByCode(@Param("code") String code);

}