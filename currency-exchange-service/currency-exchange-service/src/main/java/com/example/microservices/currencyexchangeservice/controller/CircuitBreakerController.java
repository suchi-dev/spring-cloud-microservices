package com.example.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/sample-api")
	public String sampleAPI() {
		return "Sample API";
	}

	@GetMapping("/sample-api-breaker")
	@Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
	public String sampleAPIBreaker() {

		logger.info("sample-api breaker call received");
		ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",
				String.class);
		return response.getBody();
	}

	
	@GetMapping("/sample-api-circuit-breaker")
	@CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
	public String sampleAPICircuitBreaker() {

		logger.info("sample-api circuit breaker call received");
		ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",
				String.class);
		return response.getBody();
	}
	public String hardCodedResponse(Exception ex) {
		return "Fall back Response";
	}
	
	
	@GetMapping("/sample-api-rate-limiter")
	@RateLimiter(name = "sample-api-rate")
	public String getSampleString() {
		return "Rate Limiter Example";
	}
	
	
	@GetMapping("/sample-api-bulkhead")
	@RateLimiter(name = "sample-api-bulkhead")
	public String  testBulkHead() {
		return "Bulk Head Sample";
	}
	

}
