package com.skiply.receipt.receipt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.skiply.receipt.receipt.feign.CustomFeignErrorDecoder;

import feign.Request;
import feign.Retryer;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {
	
	@Bean
	public ErrorDecoder errorDecoder() {
		return new CustomFeignErrorDecoder();
	}
	
	@Bean
	public Request.Options timeoutConfigurations(){
		return new Request.Options(5000,10000);
	}
	
	@Bean
	public Retryer retryer() {
		return new Retryer.Default(1000, 3000, 2);
	}
	
}