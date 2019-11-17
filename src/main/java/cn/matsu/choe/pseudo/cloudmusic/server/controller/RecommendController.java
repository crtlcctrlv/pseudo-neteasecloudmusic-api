package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendController extends BaseController {

  @GetMapping("/recommend/resource")
  public void resource() {

    String url = "https://music.163.com/weapi/v1/discovery/recommend/resource";

    writeResp(url, buildHttpEntity(request, null));

  }

  @GetMapping("/recommend/songs")
  public void songs() {

    String url = "https://music.163.com/weapi/v1/discovery/recommend/songs";

    writeResp(url, buildHttpEntity(request, null));

  }

}
