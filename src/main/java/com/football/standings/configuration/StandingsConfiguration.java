package com.football.standings.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class StandingsConfiguration {
	@Value("${ext.apifootball.read.timeout}")
	private int readTimeout;
	@Value("${ext.apifootball.conn.timeout}")
	private final int connTimeout = 0;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate(getClientHttpRequestFactory());
	}

	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(readTimeout);
		factory.setReadTimeout(connTimeout);
		return factory;
	}
}
