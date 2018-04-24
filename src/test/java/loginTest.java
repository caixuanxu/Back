import com.oa.common.config.JedisUtil;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class loginTest {
    private String url = "http://localhost:28080";
    @Test
    public void login(){
        String username = "qq";
        String password = "123";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username",username);
        params.add("password",password);
        url = url + "/user/login.do";
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate.postForObject(url, params, JSONObject.class));
    }

    @Test
    public void testJedis(){
        JedisUtil.setString("aaa","aaaa");
        System.out.println(JedisUtil.getString("aaa"));
    }
}
