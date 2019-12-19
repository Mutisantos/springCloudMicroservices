package com.mutiprog.microservices.currencyconversionservice.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mutiprog.microservices.currencyconversionservice.entities.CurrencyConversion;
import com.mutiprog.microservices.currencyconversionservice.proxies.CurrencyExchangeServiceProxy;

@RestController
public class ConverterController {

   private Logger logger = LoggerFactory.getLogger(this.getClass());

   @Autowired
   CurrencyExchangeServiceProxy exchangeProxy;

   // Feign-less method
   @GetMapping(path = "/currency-conversion/v1/from/{from}/to/{to}/quantity/{quantity}")
   public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to,
         @PathVariable BigDecimal quantity) {
      Map<String, String> requestMapping = new HashMap<String, String>();
      requestMapping.put("from", from);
      requestMapping.put("to", to);
      ResponseEntity<CurrencyConversion> forEntity = new RestTemplate().getForEntity(
            "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
            CurrencyConversion.class, requestMapping);
      CurrencyConversion body = forEntity.getBody();
      if (body == null) {
         return CurrencyConversion.builder().rate(new BigDecimal("3351.1")).source(from).target(to).quantity(quantity)
               .build();
      } else {
         BigDecimal conversionResult = body.getRate().multiply(quantity);
         return CurrencyConversion.builder().rate(body.getRate()).source(body.getSource()).target(body.getTarget())
               .port(body.getPort()).quantity(quantity).convertedQuantity(conversionResult).build();
      }
   }

   @GetMapping(path = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
   public CurrencyConversion retrieveExchangeValueWithFeign(@PathVariable String from, @PathVariable String to,
         @PathVariable BigDecimal quantity) {
      CurrencyConversion body = exchangeProxy.retrieveExchangeValues(from, to);
      BigDecimal conversionResult = body.getRate().multiply(quantity);
      logger.info("%%%{}%%%", conversionResult);
      return CurrencyConversion.builder().rate(body.getRate()).source(body.getSource()).target(body.getTarget())
            .port(body.getPort()).quantity(quantity).convertedQuantity(conversionResult).build();

   }

}