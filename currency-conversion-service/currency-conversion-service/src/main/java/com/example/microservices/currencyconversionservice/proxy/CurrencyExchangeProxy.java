package com.example.microservices.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.microservices.currencyconversionservice.model.CurrencyConversion;

@FeignClient(name = "currency-exchange", url="localhost:8000")
public interface CurrencyExchangeProxy {
	
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retreiveExchange(@PathVariable String from, @PathVariable String to);

}
