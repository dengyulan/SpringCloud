package com.controller;

import com.Service.UserService;
import com.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//测试多个文件提交
//测试git
//测试dengyulan分支
@RestController
public class UserController {

    @Autowired

    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    //调用公共接口，通过feign来远程访问
    @Autowired
    private UserService userService;

    @RequestMapping("/consumer/get5")
    public List<User> get5(){
        return userService.queryAll();
    }

    @RequestMapping("/consumer/get4")
    public List<User> get4(){
        String url = "http://SPRINGCLOUD-PROVIDER-1/user/query";
        return restTemplate.getForObject(url,List.class);
    }


    @RequestMapping("/consumer/get3")
    public List<User> get3(){

        ServiceInstance serviceInstance = loadBalancerClient.choose("SPRINGCLOUD-PROVIDER-1");
        String url = "http://" + serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/query";

        return restTemplate.getForObject(url,List.class);
    }


    @RequestMapping("/consumer/get2")
    public List<User> get2(){
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PROVIDER-1");
        ServiceInstance serviceInstance = instances.get(0);
        String url = "http://" + serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/query";

        return restTemplate.getForObject(url,List.class);
    }

    @RequestMapping("/consumer/get")
    public List<User> get(){
        return restTemplate.getForObject("http://localhost:8001/user/query",List.class);
    }
}
