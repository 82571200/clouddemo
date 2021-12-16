package com.demo.springcloudloadblance.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>功能描述 :</p>
 *
 * @author wangbs
 * @date 2021/7/16
 */
@Configuration
@LoadBalancerClient(value = "test")
public class WebClientConfig {

}
