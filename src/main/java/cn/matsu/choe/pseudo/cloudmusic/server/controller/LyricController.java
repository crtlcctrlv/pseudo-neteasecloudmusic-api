package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.matsu.choe.pseudo.cloudmusic.server.constants.CommonConst;

@RestController
public class LyricController extends BaseController {

  @GetMapping("/lyric")
  public void lyric(@RequestParam String id) {

    String url = "https://music.163.com/api/song/lyric?lv=-1&kv=-1&tv=-1";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", id);

    writeResp(CommonConst.LINUX_FORWARD_URL, buildLinuxEntity(url, apiParams));

  }

}
