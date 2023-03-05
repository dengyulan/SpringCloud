package dyl.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class ApiService {

    private void updateApi(String newApiInfo, String oldApiInfo){
        JSONObject newRequestBody = JSON.parseArray(newApiInfo).getJSONObject(0);
        JSONObject oldRequestBody = JSON.parseArray(oldApiInfo).getJSONObject(0);
        List<JSONObject> newJsonObjectList = (List<JSONObject>) newRequestBody.get("children");

        System.out.println(oldApiInfo);
    }
}
