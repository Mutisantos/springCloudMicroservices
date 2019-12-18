package com.mutiprog.microservices.limitsservice.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ConfigurationProperties("limits-service")
@Component
public class Configuration {
   private int maximum;
   private int minimum;
}
