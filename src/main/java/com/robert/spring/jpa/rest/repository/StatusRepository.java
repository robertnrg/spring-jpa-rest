package com.robert.spring.jpa.rest.repository;

import com.robert.spring.jpa.rest.domain.Status;
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
public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query("SELECT s FROM Status s WHERE UPPER(s.code) = UPPER(:code)")
    Status findByCode(@Param("code") String code);

    @Query("SELECT s FROM Status s WHERE UPPER(s.code) LIKE UPPER(:code)")
    List<Status> filterByCode(@Param("code") String code);

}