package com.example.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.currencyexchangeservice.model.CurrencyExchange;

@RestController
public class CurrencyExchangeController {
	
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retreiveExchange(@PathVariable String from, @PathVariable String to) {
		return new CurrencyExchange(1001L, from, to, BigDecimal.valueOf(50));
		
		
	}
	

}
