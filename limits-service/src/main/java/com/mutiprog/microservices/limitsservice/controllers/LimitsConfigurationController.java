package com.mutiprog.microservices.limitsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutiprog.microservices.limitsservice.beans.Configuration;
import com.mutiprog.microservices.limitsservice.beans.LimitConfiguration;

@RestController
public class LimitsConfigurationController {

   @Autowired
   private Configuration propsConfiguration;

   @GetMapping(path = "/limits")
   public LimitConfiguration retrieveLimitsFromConfigurations() {
      return LimitConfiguration.builder().minimum(propsConfiguration.getMinimum())
            .maximum(propsConfiguration.getMaximum()).build();
   }
}
