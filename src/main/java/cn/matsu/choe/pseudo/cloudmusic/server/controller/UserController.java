package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.extra.servlet.ServletUtil;

@RestController
public class UserController extends BaseController {

  @GetMapping("/user/playlist")
  public void playlist(@RequestParam String uid,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/user/playlist";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("uid", uid);
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/user/dj")
  public void djList(@RequestParam String uid,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/dj/program/{uid}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams), uid);

  }

  @GetMapping("/user/record")
  public void playRecords(@RequestParam String uid,
      @RequestParam(required = false, defaultValue = "1") Integer type) {

    String url = "https://music.163.com/weapi/v1/play/record";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("uid", uid);
    apiParams.put("type", type);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/user/detail")
  public void detail(@RequestParam String uid) {

    String url = "https://music.163.com/weapi/v1/user/detail/{uid}";

    writeResp(url, buildHttpEntity(null), uid);

  }

  @GetMapping("/user/subcount")
  public void subcount() {

    String url = "https://music.163.com/weapi/subcount";

    writeResp(url, buildHttpEntity(request, null));

  }

  @GetMapping("/user/follows")
  public void follows(@RequestParam String uid,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/user/getfollows/{uid}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("order", true);

    writeResp(url, buildHttpEntity(apiParams), uid);

  }

  @GetMapping("/user/followeds")
  public void followeds(@RequestParam String uid,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "-1") Long lasttime) {

    String url = "https://music.163.com/eapi/user/getfolloweds/{uid}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("time", lasttime);
    apiParams.put("userId", uid);

    writeResp(url, buildMobileHttpEntity("/api/user/getfolloweds", apiParams), uid);

  }

  @GetMapping("/user/event")
  public void event(@RequestParam String uid,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "-1") Long lasttime) {

    String url = "https://music.163.com/weapi/event/get/{uid}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("time", lasttime);
    apiParams.put("getcounts", true);
    apiParams.put("total", false);

    writeResp(url, buildHttpEntity(apiParams), uid);

  }

  @GetMapping("/user/audio")
  public void audio(@RequestParam String uid) {

    String url = "https://music.163.com/weapi/djradio/get/byuser";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("userId", uid);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/user/update")
  public void update(@RequestParam String gender, @RequestParam String birthday,
      @RequestParam String nickname, @RequestParam String province, @RequestParam String city,
      @RequestParam String signature) {

    String url = "https://music.163.com/weapi/user/profile/update";

    Map<String, Object> apiParams = new HashMap<>();

    Cookie csrf = ServletUtil.getCookie(request, "__csrf");
    if (csrf != null) {
      apiParams.put("csrf_token", csrf.getValue());
    }

    apiParams.put("avatarImgId", "0");
    apiParams.put("birthday", birthday);
    apiParams.put("city", city);
    apiParams.put("gender", gender);
    apiParams.put("nickname", nickname);
    apiParams.put("province", province);
    apiParams.put("signature", signature);

    ResponseEntity<String> resp =
        restTemplate.exchange(url, HttpMethod.POST, buildHttpEntity(apiParams), String.class);

    response.setStatus(resp.getStatusCodeValue());
    ServletUtil.write(response, resp.getBody(), MediaType.APPLICATION_JSON_UTF8_VALUE);

  }

}
