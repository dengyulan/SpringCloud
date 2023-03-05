package dyl.controller;

import com.alibaba.fastjson.JSONObject;
import dyl.feign.ProviderFeign;
import feign.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Resource
    private ProviderFeign providerFeign;

    @PostMapping("test/formData")
    public String testForm(@RequestBody JSONObject jsonObject){
        String msg = jsonObject.getString("msg");
        if(msg == null){
            return "no msg";
        }else {
            Map<String, String> map = new HashMap<>();
            map.put("msg", msg);
            Response response = providerFeign.testFormData(map);
            return "has msg";
        }
    }
}
