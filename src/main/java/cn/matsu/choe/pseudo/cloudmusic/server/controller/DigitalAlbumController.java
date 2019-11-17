package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DigitalAlbumController extends BaseController {

  @GetMapping("/digitalAlbum/purchased")
  public void detail(@RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/digitalAlbum/purchased";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

}
