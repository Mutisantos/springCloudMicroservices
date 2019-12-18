package com.mutiprog.microservices.currencyconversionservice.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mutiprog.microservices.currencyconversionservice.entities.CurrencyConversion;

@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
public interface CurrencyExchangeServiceProxy {

   @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
   public CurrencyConversion retrieveExchangeValues(@PathVariable String from, @PathVariable String to);
}
