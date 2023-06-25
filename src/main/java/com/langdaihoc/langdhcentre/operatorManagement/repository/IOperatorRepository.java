package com.langdaihoc.langdhcentre.operatorManagement.repository;

import com.langdaihoc.langdhcentre.operatorManagement.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOperatorRepository extends JpaRepository<Operator, Integer> {

    Optional<Operator> findByEmail(String email);
}
