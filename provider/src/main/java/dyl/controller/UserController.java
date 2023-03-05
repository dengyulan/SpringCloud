package dyl.controller;


import com.alibaba.fastjson.JSONObject;
import dyl.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @PostMapping("/query/user")
    public User queryUser(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject);
        return new User("dyl", 18);
    }

    /**
     * 该接口用于测试openfeign中post的form-data接口
     */
    @PostMapping("test/formData")
    public String testForm(@RequestParam("msg") String msg){
        System.out.println(msg);
        return msg;
    }
}
