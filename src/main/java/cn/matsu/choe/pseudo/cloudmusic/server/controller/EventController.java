package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController extends BaseController {

  @GetMapping
  public void event(@RequestParam(required = false, defaultValue = "20") Integer pagesize,
      @RequestParam(required = false, defaultValue = "-1") Long lasttime) {

    String url = "https://music.163.com/weapi/v1/event/get";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("pagesize", pagesize);
    apiParams.put("lasttime", lasttime);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

  @GetMapping("/forward")
  public void eventForward(@RequestParam String uid, @RequestParam String evId,
      @RequestParam String forwards) {

    String url = "https://music.163.com/weapi/event/forward";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("forwards", forwards);
    apiParams.put("id", evId);
    apiParams.put("eventUserId", uid);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

  @GetMapping("/del")
  public void eventDelete(@RequestParam String evId) {

    String url = "https://music.163.com/weapi/event/delete";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", evId);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

}
