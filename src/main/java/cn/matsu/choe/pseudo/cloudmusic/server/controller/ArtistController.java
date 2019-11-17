package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController extends BaseController {

  @GetMapping("/artists")
  public void info(@RequestParam String id) {

    String url = "https://music.163.com/weapi/v1/artist/{id}";

    writeResp(url, buildHttpEntity(null), id);

  }

  @GetMapping("/artist/list")
  public void artistList(@RequestParam(required = false, defaultValue = "1001") String cat,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/artist/list";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("categoryCode", cat);
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(apiParams));
  }

  @GetMapping("/artist/mv")
  public void mv(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/artist/mvs";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("artistId", id);
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(apiParams), id);
  }

  @GetMapping("/artist/album")
  public void album(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/artist/albums/{id}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(apiParams), id);

  }

  @GetMapping("/artist/desc")
  public void desc(@RequestParam String id) {

    String url = "https://music.163.com/weapi/artist/introduction";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", id);

    writeResp(url, buildHttpEntity(apiParams), id);

  }

}
