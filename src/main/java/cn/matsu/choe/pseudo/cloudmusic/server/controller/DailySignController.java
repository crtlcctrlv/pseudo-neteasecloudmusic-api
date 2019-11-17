package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DailySignController extends BaseController {

  @GetMapping("/daily_signin")
  public void music(@RequestParam(required = false, defaultValue = "0") Integer type) {

    String url = "https://music.163.com/weapi/point/dailyTask";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("type", type);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

}
