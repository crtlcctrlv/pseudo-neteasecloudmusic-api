package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.matsu.choe.pseudo.cloudmusic.server.constants.CommonConst;

@RestController
public class PlayListController extends BaseController {

  @GetMapping("/playlist/catlist")
  public void categories() {

    String url = "https://music.163.com/weapi/playlist/catalogue";

    writeResp(url, buildHttpEntity(null));

  }

  @GetMapping("/playlist/hot")
  public void hot() {

    String url = "https://music.163.com/weapi/playlist/hottags";

    writeResp(url, buildHttpEntity(null));

  }

  @GetMapping("/playlist/detail")
  public void detail(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "8") Integer s) {

    String url = "https://music.163.com/api/v3/playlist/detail";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", id);
    apiParams.put("n", 100000);
    apiParams.put("s", s);

    writeResp(CommonConst.LINUX_FORWARD_URL, buildLinuxEntity(url, apiParams));

  }

  @GetMapping("/playlist/subscribers")
  public void subscribers(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/playlist/subscribers";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", id);
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams));

  }

}
