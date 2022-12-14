package com.Service;

import com.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//1.写公共接口
//2.绑定服务提供者在eureka的名称


@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER-1")
public interface UserService {
    @RequestMapping("/user/query")
    List<User> queryAll();

    @RequestMapping("/testFeign1")
    String testFeign();
}
