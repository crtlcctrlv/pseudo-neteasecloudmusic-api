package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import cn.matsu.choe.pseudo.cloudmusic.server.constants.CommonConst;
import cn.matsu.choe.pseudo.cloudmusic.server.util.JacksonUtil;

@RestController
public class LoginController extends BaseController {

  @Autowired
  private ObjectMapper objectMapper;

  @GetMapping("/login")
  public void email(@RequestParam String email, @RequestParam String password) {
    String url = "https://music.163.com/weapi/login";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("username", email);
    apiParams.put("password", MD5.create().digestHex(password));
    apiParams.put("rememberLogin", "true");

    writeResp(url, buildHttpEntity(request, apiParams, CommonConst.COOKIE_OS_PC));

  }

  @GetMapping("/login/cellphone")
  public void cellphone(@RequestParam String phone, @RequestParam String password,
      @RequestParam(required = false) String countrycode) {

    String url = "https://music.163.com/weapi/login/cellphone";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("phone", phone);
    apiParams.put("password", MD5.create().digestHex(password));
    apiParams.put("rememberLogin", "true");

    writeResp(url, buildHttpEntity(request, apiParams, CommonConst.COOKIE_OS_PC));

  }

  @GetMapping("/login/status")
  public ObjectNode status() {

    String url = "https://music.163.com";

    ResponseEntity<String> resp = getResp(url, HttpMethod.GET, buildHttpEntity(request, null));

    JsonNodeFactory factory = new JsonNodeFactory(false);
    ObjectNode node = factory.objectNode();
    node.put("code", 200);

    Document doc = Jsoup.parse(resp.getBody());
    Elements scripts = doc.getElementsByTag("script");

    for (int i = 0; i < scripts.size(); i++) {
      String guser = ReUtil.getGroup0("GUser\\s*=\\s*([^;]+);", scripts.get(i).data());
      if (StrUtil.isNotEmpty(guser)) {

        String profile = ReUtil.getGroup0("\\{\\S*\\}", guser);
        node.set("profile", JacksonUtil.readValue(objectMapper, profile));
        break;
      }
    }

    return node;
  }

  @GetMapping("/logout")
  public void detail() {

    String url = "https://music.163.com/weapi/logout";

    writeResp(url, buildHttpEntity(request, null));

  }

  @GetMapping("/cellphone/existence/check")
  public void cellphoneCheck(@RequestParam String phone,
      @RequestParam(required = false) String countrycode) {

    String url = "http://music.163.com/eapi/cellphone/existence/check";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("cellphone", phone);

    writeResp(url, buildMobileHttpEntity("/api/cellphone/existence/check", apiParams));

  }

  @GetMapping("/captcha/sent")
  public void captchaSend(@RequestParam String phone,
      @RequestParam(required = false, defaultValue = "86") String ctcode) {

    String url = "https://music.163.com/weapi/sms/captcha/sent";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("cellphone", phone);
    apiParams.put("ctcode", ctcode);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/captcha/verify")
  public void captchaVerify(@RequestParam String phone, @RequestParam String captcha,
      @RequestParam(required = false, defaultValue = "86") String ctcode) {

    String url = "https://music.163.com/weapi/sms/captcha/verify";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("cellphone", phone);
    apiParams.put("captcha", captcha);
    apiParams.put("ctcode", ctcode);

    writeResp(url, buildHttpEntity(apiParams));

  }

}
