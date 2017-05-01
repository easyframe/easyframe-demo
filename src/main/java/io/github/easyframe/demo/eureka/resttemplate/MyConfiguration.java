package io.github.easyframe.demo.eureka.resttemplate;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate可自动配置为使用负载均衡Ribbon
 *
 * @Date: 2017-01-06
 * @Author: linzhaoming
 */
@Configuration
public class MyConfiguration {

    //使用负载均衡的RestTemplate
    @LoadBalanced
    @Bean
    RestTemplate loadBalanced() {
        return new RestTemplate();
    }

    //普通的RestTemplate
    @Primary
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}