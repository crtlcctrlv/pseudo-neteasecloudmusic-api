package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingController extends BaseController {

  @GetMapping("/setting")
  public void detail() {

    String url = "https://music.163.com/weapi/user/setting";

    writeResp(url, buildHttpEntity(request, null));

  }

}
