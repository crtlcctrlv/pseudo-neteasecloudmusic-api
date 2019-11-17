package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgController extends BaseController {

  @GetMapping("/msg/notices")
  public void msgNotices(@RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/api/msg/notices";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("total", "true");

    writeResp(url, buildHttpEntity(request, apiParams));

  }

  @GetMapping("/msg/comments")
  public void msgComments(@RequestParam String uid,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "-1") Long before) {

    String url = "https://music.163.com/api/msg/notices";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("uid", uid);
    apiParams.put("limit", limit);
    apiParams.put("beforeTime", before);
    apiParams.put("total", "true");

    writeResp(url, buildHttpEntity(request, apiParams));

  }

  @GetMapping("/msg/private")
  public void msgPrivate(@RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/msg/private/users";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("total", "true");

    writeResp(url, buildHttpEntity(request, apiParams));

  }

  @GetMapping("/msg/private/history")
  public void msgPrivateHistory(@RequestParam String uid,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/msg/private/history";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("userId", uid);
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("total", "true");

    writeResp(url, buildHttpEntity(request, apiParams));

  }

  @GetMapping("/msg/forwards")
  public void msgForwards(@RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/api/forwards/get";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("total", "true");

    writeResp(url, buildHttpEntity(request, apiParams));

  }

}
