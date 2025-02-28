package com.skiply.studentManagement.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.skiply.studentManagement.feign.CustomFeignErrorDecoder;

import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {
	
	@Bean
	public ErrorDecoder errorDecoder() {
		return new CustomFeignErrorDecoder();
	}

	@Bean
	public Retryer retryer() {
		return Retryer.NEVER_RETRY; // Disable Feign's default retry mechanism
	}

	/*
	 * @Bean public RequestInterceptor requestInterceptor() { return RequestTemplate
	 * -> { String credentials="Student:Student"; String encodedAuth=
	 * Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.
	 * UTF_8));
	 * RequestTemplate.header(org.springframework.http.HttpHeaders.AUTHORIZATION,
	 * "Basic "+encodedAuth); }; }
	 */
	
}