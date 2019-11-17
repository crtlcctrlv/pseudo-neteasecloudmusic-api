package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaylistOperateController extends BaseController {

  @GetMapping("/playlist/create")
  public void create(@RequestParam String name, @RequestParam String privacy) {

    String url = "https://music.163.com/weapi/playlist/create";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("name", name);
    apiParams.put("privacy", privacy);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

  @GetMapping("/playlist/delete")
  public void delete(@RequestParam String id) {

    String url = "https://music.163.com/weapi/playlist/delete";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("pid", id);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

  @GetMapping("/playlist/desc/update")
  public void descUpdate(@RequestParam String id, @RequestParam String desc) {

    String url = "http://interface3.music.163.com/eapi/playlist/desc/update";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", id);
    apiParams.put("desc", desc);

    writeResp(url, buildMobileHttpEntity("/api/playlist/desc/update", apiParams));

  }

  @GetMapping("/playlist/tags/update")
  public void tagsUpdate(@RequestParam String id, @RequestParam String tags) {

    String url = "http://interface3.music.163.com/eapi/playlist/tags/update";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", id);
    apiParams.put("tags", tags);

    writeResp(url, buildMobileHttpEntity("/api/playlist/tags/update", apiParams));

  }

  @GetMapping("/playlist/name/update")
  public void nameUpdate(@RequestParam String id, @RequestParam String name) {

    String url = "http://interface3.music.163.com/eapi/playlist/update/name";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", id);
    apiParams.put("name", name);

    writeResp(url, buildMobileHttpEntity("/api/playlist/update/name", apiParams));

  }

}
