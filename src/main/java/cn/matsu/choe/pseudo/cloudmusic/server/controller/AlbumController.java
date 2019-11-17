package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/album")
public class AlbumController extends BaseController {

  @GetMapping
  public void detail(@RequestParam String id) {

    String url = "https://music.163.com/weapi/v1/album/{id}";

    writeResp(url, buildHttpEntity(null), id);

  }

  @GetMapping("/newest")
  public void newest() {

    String url = "https://music.163.com/api/discovery/newAlbum";

    writeResp(url, buildHttpEntity(null));

  }

  @GetMapping("/detail/dynamic")
  public void dynamicDetail(@RequestParam String id) {

    String url = "https://music.163.com/weapi/album/detail/dynamic";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", id);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

}
