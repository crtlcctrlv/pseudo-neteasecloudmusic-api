package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.ReUtil;

@RestController
public class RelateController extends BaseController {

  @GetMapping("/related/allvideo")
  public void video(@RequestParam String id) {

    String url = "https://music.163.com/weapi/cloudvideo/v1/allvideo/rcmd";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", id);
    apiParams.put("type", ReUtil.isMatch(PatternPool.NUMBERS, id) ? 0 : 1);

    writeResp(url, buildHttpEntity(apiParams));

  }

  // @GetMapping("/related/playlist")
  // public void playlist(@RequestParam String id) {
  //
  // String url = "https://music.163.com/weapi/cloudvideo/v1/allvideo/rcmd";
  //
  // Map<String, Object> apiParams = new HashMap<>();
  // apiParams.put("id", id);
  // apiParams.put("type", ReUtil.isMatch(PatternPool.NUMBERS, id) ? 0 : 1);
  //
  // writeResp(url, buildHttpEntity(apiParams));
  //
  // }

}
