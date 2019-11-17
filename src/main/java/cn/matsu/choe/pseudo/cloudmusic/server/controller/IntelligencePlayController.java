package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.core.util.StrUtil;

@RestController
public class IntelligencePlayController extends BaseController {

  @GetMapping("/playmode/intelligence/list")
  public void music(@RequestParam String id, @RequestParam String pid,
      @RequestParam(required = false) String sid,
      @RequestParam(required = false, defaultValue = "1") Integer count) {

    String url = "http://music.163.com/weapi/playmode/intelligence/list";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("songId", id);
    apiParams.put("playlistId", pid);
    apiParams.put("startMusicId", StrUtil.isNotEmpty(sid) ? sid : id);
    apiParams.put("type", "fromPlayOne");
    apiParams.put("count", count);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

}
