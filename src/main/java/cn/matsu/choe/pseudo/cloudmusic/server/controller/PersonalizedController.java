package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personalized")
public class PersonalizedController extends BaseController {

  @GetMapping
  public void playlist(@RequestParam(required = false, defaultValue = "20") Integer limit) {

    String url = "https://music.163.com/weapi/personalized/playlist";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("n", 1000);
    apiParams.put("limit", limit);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(apiParams));
  }

  @GetMapping("/newsong")
  public void newSong() {

    String url = "https://music.163.com/weapi/personalized/newsong";

    writeResp(url, buildHttpEntity(null));
  }

  @GetMapping("/mv")
  public void mv() {

    String url = "https://music.163.com/weapi/personalized/mv";

    writeResp(url, buildHttpEntity(null));
  }

  @GetMapping("/djprogram")
  public void djprogram() {

    String url = "https://music.163.com/weapi/personalized/djprogram";

    writeResp(url, buildHttpEntity(null));
  }

}
