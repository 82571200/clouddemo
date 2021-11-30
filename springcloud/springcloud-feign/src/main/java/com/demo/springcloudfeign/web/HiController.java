package com.demo.springcloudfeign.web;

import com.demo.springcloudfeign.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>功能描述 :</p>
 *
 * @author wangbs
 * @date 2021/7/15
 */
@RestController
public class HiController {
    @Autowired(required = false)
    SchedualServiceHi schedualServiceHi;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return schedualServiceHi.sayHiFromClientOne( name );
    }

}
