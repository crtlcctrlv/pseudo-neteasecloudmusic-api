package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FmController extends BaseController {

  @GetMapping("/personal_fm")
  public void personalFm() {

    String url = "https://music.163.com/weapi/v1/radio/get";

    writeResp(url, buildHttpEntity(request, null));

  }

}
