package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController extends BaseController {

  @GetMapping
  public void search(@RequestParam String keywords,
      @RequestParam(required = false, defaultValue = "1") Integer type,
      @RequestParam(required = false, defaultValue = "20") String limit,
      @RequestParam(required = false, defaultValue = "20") String offset) {

    String url = "https://music.163.com/weapi/search/get";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("s", keywords);
    apiParams.put("type", type);
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/default")
  public void defaultKeywords() {

    String url = "http://interface3.music.163.com/eapi/search/defaultkeyword/get";

    writeResp(url, buildMobileHttpEntity("/api/search/defaultkeyword/get", new HashMap<>()));

  }

  @GetMapping("/suggest")
  public void suggest(@RequestParam String keywords, @RequestParam(required = false) String type) {

    String typePathParam = "mobile".equals(type) ? "keyword" : "web";

    String url = "https://music.163.com/weapi/search/suggest/" + typePathParam;

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("s", keywords);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/multimatch")
  public void multimatch(@RequestParam String keywords) {

    String url = "https://music.163.com/weapi/search/suggest/multimatch";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("s", keywords);
    apiParams.put("type", 1);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/hot")
  public void hot() {

    String url = "https://music.163.com/weapi/search/hot";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("type", 1111);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/hot/detail")
  public void hotDetail() {

    String url = "https://music.163.com/weapi/hotsearchlist/get";

    writeResp(url, buildHttpEntity(null));

  }

}
