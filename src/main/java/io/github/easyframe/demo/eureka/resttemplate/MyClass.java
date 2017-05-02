package io.github.easyframe.demo.eureka.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

/**
 * @author linzhaoming
 */
public class MyClass {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @LoadBalanced
    private RestTemplate loadBalanced;

    public String doOtherStuff() {
        //url需要为虚拟的host name,如service name,并非实际host name
        //Ribbon client用于创建全物理地址. 设置地方查看RibbonAutoConfiguration
        return loadBalanced.getForObject("http://stores/stores", String.class);
    }

    public String doStuff() {
        return restTemplate.getForObject("http://example.com", String.class);
    }
}