package com.mutiprog.microservices.currencyexchangeservice.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExchangeValue {
   @Id
   @GeneratedValue
   private Long id;
   private String source;
   private String target;
   private BigDecimal rate;
   private Long port;
}
