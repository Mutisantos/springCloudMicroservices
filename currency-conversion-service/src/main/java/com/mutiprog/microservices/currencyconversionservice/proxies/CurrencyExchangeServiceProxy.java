package com.mutiprog.microservices.currencyconversionservice.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mutiprog.microservices.currencyconversionservice.entities.CurrencyConversion;

// Without Ribbon, it's required to set the URL also.
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")

//Without API Gateways, the other microservice can be called directly by service name  
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "netflix-zuul-api-gateway")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
   // Without the API Gateway, the exposed enpoint can be called directly
   // @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")

   // With ZUUL, calling the service requires to add the Service name to the URL
   @GetMapping(path = "/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
   public CurrencyConversion retrieveExchangeValues(@PathVariable String from, @PathVariable String to);

}
