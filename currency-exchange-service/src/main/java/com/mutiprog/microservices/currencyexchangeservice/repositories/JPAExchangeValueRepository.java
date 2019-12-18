package com.mutiprog.microservices.currencyexchangeservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutiprog.microservices.currencyexchangeservice.entities.ExchangeValue;

@Repository
public interface JPAExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

   public Optional<ExchangeValue> findBySourceAndTarget(String source, String target);

}
