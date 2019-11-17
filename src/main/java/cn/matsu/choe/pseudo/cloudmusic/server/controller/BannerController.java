package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.matsu.choe.pseudo.cloudmusic.server.constants.CommonConst;

@RestController
public class BannerController extends BaseController {

  private static Map<Integer, String> deviceTypeMap = new HashMap<>();

  static {
    deviceTypeMap.put(0, "pc");
    deviceTypeMap.put(1, "android");
    deviceTypeMap.put(2, "iphone");
    deviceTypeMap.put(3, "ipad");
  }

  @GetMapping("/banner")
  public void banner(@RequestParam(required = false, defaultValue = "0") Integer type) {

    String url = "https://music.163.com/api/v2/banner/get";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("clientType", deviceTypeMap.get(type));

    writeResp(CommonConst.LINUX_FORWARD_URL, buildLinuxEntity(url, apiParams));

  }

  @GetMapping("/dj/banner")
  public void banner() {

    String url = "http://music.163.com/weapi/djradio/banner/get";

    writeResp(url, buildHttpEntity(request, null, CommonConst.COOKIE_OS_PC));

  }
}
