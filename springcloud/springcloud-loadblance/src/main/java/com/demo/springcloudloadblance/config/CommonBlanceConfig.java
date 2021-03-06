package com.demo.springcloudloadblance.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.loadbalancer.cache.LoadBalancerCacheManager;
import org.springframework.cloud.loadbalancer.config.LoadBalancerZoneConfig;
import org.springframework.cloud.loadbalancer.core.CachingServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.DiscoveryClientServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ZonePreferenceServiceInstanceListSupplier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

/**
 * <p>功能描述 :</p>
 *
 * @author wangbs
 * @date 2021/7/16
 */
public class CommonBlanceConfig {
    /**
     * 同步环境下的ServiceInstanceListSupplier
     * SameZoneOnlyServiceInstanceListSupplier限制仅返回同一个zone下的实例（注意）
     * CachingServiceInstanceListSupplier启用缓存，不每次访问eureka请求实例列表
     *
     * @param discoveryClient
     * @param env
     * @param zoneConfig
     * @param context
     * @return
     */
    @Bean
    @Order(Integer.MIN_VALUE)
    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
            DiscoveryClient discoveryClient, Environment env,
            LoadBalancerZoneConfig zoneConfig,
            ApplicationContext context) {
        ServiceInstanceListSupplier delegate = new ZonePreferenceServiceInstanceListSupplier(
                new DiscoveryClientServiceInstanceListSupplier(discoveryClient, env),
                zoneConfig
        );
        ObjectProvider<LoadBalancerCacheManager> cacheManagerProvider = context
                .getBeanProvider(LoadBalancerCacheManager.class);
        if (cacheManagerProvider.getIfAvailable() != null) {
            return new CachingServiceInstanceListSupplier(
                    delegate,
                    cacheManagerProvider.getIfAvailable()
            );
        }
        return delegate;
    }
}
