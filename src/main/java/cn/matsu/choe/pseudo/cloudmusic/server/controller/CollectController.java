package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectController extends BaseController {

  @GetMapping("/artist/sub")
  public void artistSub(@RequestParam String id, @RequestParam String t) {

    String subOrUnsub = "1".equals(t) ? "sub" : "unsub";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("artistId", id);
    apiParams.put("artistIds", "[" + id + "]");

    String url = "https://music.163.com/weapi/artist/{subOrUnsub}";

    writeResp(url, buildHttpEntity(request, apiParams), subOrUnsub);

  }

  @GetMapping("/artist/sublist")
  public void artistSubList(@RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/artist/sublist";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("offset", offset);
    apiParams.put("limit", limit);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

  @GetMapping("/dj/sub")
  public void djSub(@RequestParam String rid, @RequestParam String t) {

    String subOrUnsub = "1".equals(t) ? "sub" : "unsub";

    String url = "https://music.163.com/weapi/djradio/{subOrUnsub}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", rid);

    writeResp(url, buildHttpEntity(request, apiParams), subOrUnsub);

  }

  @GetMapping("/dj/sublist")
  public void djSubList(@RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/djradio/get/subed";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

  @GetMapping("/mv/sub")
  public void mvSub(@RequestParam String mvid, @RequestParam String t) {

    String subOrUnsub = "1".equals(t) ? "sub" : "unsub";

    String url = "https://music.163.com/weapi/mv/{subOrUnsub}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("mvId", mvid);
    apiParams.put("mvIds", "[" + mvid + "]");

    writeResp(url, buildHttpEntity(request, apiParams), subOrUnsub);

  }

  @GetMapping("/mv/sublist")
  public void mvSubList(@RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/cloudvideo/allvideo/sublist";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

  @GetMapping("/album/sub")
  public void albumSub(@RequestParam String id, @RequestParam String t) {

    String subOrUnsub = "1".equals(t) ? "sub" : "unsub";

    String url = "https://music.163.com/weapi/album/{subOrUnsub}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", id);

    writeResp(url, buildHttpEntity(request, apiParams), subOrUnsub);

  }

  @GetMapping("/album/sublist")
  public void albumSubList(@RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/album/sublist";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(request, apiParams));

  }


}
