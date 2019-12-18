package com.mutiprog.microservices.currencyexchangeservice.controllers;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mutiprog.microservices.currencyexchangeservice.entities.ExchangeValue;
import com.mutiprog.microservices.currencyexchangeservice.repositories.JPAExchangeValueRepository;

@RestController
public class CurrencyExchangeController {

   @Autowired
   private Environment currentEnvironment;

   @Autowired
   private JPAExchangeValueRepository exchangeValueRepository;

   @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
   public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
      ExchangeValue exchangeVal = exchangeValueRepository.findBySourceAndTarget(from, to).orElse(null);
      if (Objects.isNull(exchangeVal)) {
         return ExchangeValue.builder().rate(new BigDecimal("3351.1")).source(from).target(to)
               .port(Long.parseLong(currentEnvironment.getProperty("local.server.port"))).build();
      }
      else {
         exchangeVal.setPort(Long.parseLong(currentEnvironment.getProperty("local.server.port")));
         return exchangeVal;
      }
   }

}
