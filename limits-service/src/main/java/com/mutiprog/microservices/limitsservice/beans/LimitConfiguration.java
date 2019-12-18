package com.mutiprog.microservices.limitsservice.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LimitConfiguration {
   private int maximum;
   private int minimum;
}
