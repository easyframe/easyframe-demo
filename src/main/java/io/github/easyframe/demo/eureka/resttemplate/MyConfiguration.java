package io.github.easyframe.demo.eureka.resttemplate;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * Use RestTemplate to autocongiure using load balance Ribbon
 *
 * @author linzhaoming
 */
@Configuration
public class MyConfiguration {

    //RestTemplate using load balance
    @LoadBalanced
    @Bean
    RestTemplate loadBalanced() {
        return new RestTemplate();
    }

    //Normal RestTemplate
    @Primary
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}