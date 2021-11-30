package com.demo.springcloudfeign.service.impl;

import com.demo.springcloudfeign.service.SchedualServiceHi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>功能描述 :</p>
 *
 * @author wangbs
 * @date 2021/7/16
 */
@Component
public class SchedualServiceHiImpl implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(@RequestParam(value = "name") String name){
        return "熔断中:"+name;
    }
}
