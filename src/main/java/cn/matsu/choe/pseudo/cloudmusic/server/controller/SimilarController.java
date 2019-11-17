package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simi")
public class SimilarController extends BaseController {

  @GetMapping("/artist")
  public void artist(@RequestParam String id) {

    String url = "https://music.163.com/weapi/discovery/simiArtist";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("artistid", id);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

  @GetMapping("/mv")
  public void mv(@RequestParam String mvid) {

    String url = "https://music.163.com/weapi/discovery/simiMV";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("mvid", mvid);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/playlist")
  public void playlist(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/discovery/simiPlaylist";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("songid", id);
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/song")
  public void song(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/v1/discovery/simiSong";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("songid", id);
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/user")
  public void user(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/discovery/simiUser";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("songid", id);
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams));

  }
}
