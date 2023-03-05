import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class ApiServiceTest {

    @Test
    public void apiService(){

        String newApiInfo = "[{\"key\":\"root\", \"children\":[{\"key\":\"001\", \"name\":\"age\", \"type\":\"Integer\",\"mockValue\":\"18\"},{\"key\":\"002\", \"name\":\"name\", \"type\":\"string\", \"mockValue\":\"dyl\"},{\"key\":\"003\", \"name\":\"sex\", \"type\":\"string\", \"mockValue\":\"female\"}]}]";
        String oldApiInfo = "[{\"key\":\"root\", \"children\":[{\"key\":\"001\", \"name\":\"age\",  \"type\":\"Integer\", \"mockValue\":\"20\"},{\"key\":\"002\", \"name\":\"name\",  \"type\":\"string\",\"mockValue\":\"whq\"}]}]";
        JSONObject newRequestBody = JSON.parseArray(newApiInfo).getJSONObject(0);
        JSONObject oldRequestBody = JSON.parseArray(oldApiInfo).getJSONObject(0);

        List<JSONObject> newJsonObjectList = (List<JSONObject>) newRequestBody.get("children");
        List<JSONObject> oldJsonObjectList = (List<JSONObject>) oldRequestBody.get("children");

        for(int i = 0; i < newJsonObjectList.size(); ++i){
            JSONObject newJsonObj = newJsonObjectList.get(i);
            String name = newJsonObj.getString("name");
            String key = newJsonObj.getString("key");

            if(StringUtils.isBlank(name)){continue;}
            JSONObject oldJsonObj = null;
            oldJsonObj = oldJsonObjectList.stream().filter(x->key.equalsIgnoreCase(x.getString("key"))).findFirst().orElse(null);

            if(oldJsonObj == null){
                long count = oldJsonObjectList.stream().filter(x->name.equalsIgnoreCase(x.getString("name"))).count();
                if(count == 0){continue;}
                else if(count == 1){oldJsonObj = oldJsonObjectList.stream().filter(x->name.equalsIgnoreCase(x.getString("name"))).findFirst().get();}
                else{
                    System.out.println("error");
                }
            }
           resolveTwo(newJsonObj, oldJsonObj);
        }

        System.out.println(newRequestBody);
    }

    public void resolveTwo(JSONObject newJsonObj, JSONObject oldJsonObj){
        if(!newJsonObj.getString("type").equalsIgnoreCase("object") || !newJsonObj.getString("type").equalsIgnoreCase("array")){
            if(oldJsonObj !=null && StringUtils.isNotBlank(oldJsonObj.getString("mockValue"))){
                newJsonObj.replace("mockValue", oldJsonObj.getString("mockValue"));
            }
        }else{
            if(StringUtils.isNotBlank(newJsonObj.getString("children")) && StringUtils.isNotBlank(oldJsonObj.getString("children"))){
                resolveTwo(newJsonObj.getJSONObject("children"), oldJsonObj.getJSONObject("children"));
            }
        }
    }
}
