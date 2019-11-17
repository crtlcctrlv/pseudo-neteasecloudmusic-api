package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController extends BaseController {

  @GetMapping("/like")
  public void like(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "true") Boolean like) {

    String url = "https://music.163.com/weapi/radio/like?alg=itembased&trackId={trackId}&time=25";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("trackId", id);
    apiParams.put("like", like);

    writeResp(url, buildHttpEntity(request, apiParams), id);

  }

  @GetMapping("/likelist")
  public void likelist(@RequestParam String uid) {

    String url = "https://music.163.com/weapi/song/like/get";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("uid", uid);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

}
