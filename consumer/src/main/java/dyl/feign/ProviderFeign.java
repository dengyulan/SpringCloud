package dyl.feign;

import com.alibaba.fastjson.JSONObject;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name = "provider", url = "http://localhost:9000")
public interface ProviderFeign {

    @PostMapping("/query/user")
    Response queryUser(JSONObject jsonObject);

    @PostMapping( value = "test/formData", consumes = {"application/x-www-form-urlencoded"})
    Response testFormData(Map<String, String> map);
}
