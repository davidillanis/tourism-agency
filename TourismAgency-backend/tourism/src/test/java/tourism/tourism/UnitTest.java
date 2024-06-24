package tourism.tourism;

import com.tourism.service.mapped.implementation.CustomerServiceImpl;
import com.tourism.service.other.implementation.TestServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class UnitTest {

    @InjectMocks
    private TestServiceImpl testService;
    @Test
    public void test() {
        String apiURL="http://localhost:8080/realms/spring-boot-realm-dev/protocol/openid-connect/token";
        Map<String, String> json=new HashMap<>();
        json.put("client_id", "spring-client-api-rest");
        json.put("grant_type","password");
        json.put("username","all");
        json.put("password","all1234");
        json.put("client_secret","MczItIj8xDX55sOCmviM3QWRTdPqNWmW");
        System.out.println(json);
        //System.out.println(testService.getFromDataAPI(apiURL,json.toString()));
    }

    @Test
    public void JsonTest() throws JSONException {
        String resource="{spring-client-api-rest={roles=[CUSTOMER]}, account={roles=[manage-account, manage-account-links, view-profile]}}";

        JSONArray rolesArray = new JSONObject(resource)
                .getJSONObject("spring-client-api-rest")
                .getJSONArray("roles");

        Set<String> roles=new HashSet<>();
        for(int i=0; i<rolesArray.length(); i++){
            roles.add(rolesArray.getString(i));
        }
        System.out.println(roles);
    }
}

