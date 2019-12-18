package com.mutiprog.microservices.currencyconversionservice.entities;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyConversion {
   @Id
   @GeneratedValue
   private Long id;
   private String source;
   private String target;
   private BigDecimal rate;
   private BigDecimal quantity;
   private int port;
   private BigDecimal convertedQuantity;
}