package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController extends BaseController {

  @GetMapping("/video/detail")
  public void relatedVideos(@RequestParam String id) {

    String url = "https://music.163.com/weapi/cloudvideo/v1/video/detail";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", id);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/video/url")
  public void url(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "1080") Integer res) {

    String url = "https://music.163.com/weapi/cloudvideo/playurl";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("ids", "[\"" + id + "\"]");
    apiParams.put("resolution", res);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/video/group/list")
  public void groupList() {

    String url = "https://music.163.com/weapi/cloudvideo/group/list";

    writeResp(url, buildHttpEntity(null));

  }

  @GetMapping("/video/group")
  public void group(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "0") Integer offset,
      @RequestParam(required = false, defaultValue = "1080") Integer res) {

    String url = "https://music.163.com/weapi/videotimeline/videogroup/get";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("groupId", id);
    apiParams.put("offset", offset);
    apiParams.put("needUrl", true);
    apiParams.put("resolution", res);

    writeResp(url, buildHttpEntity(apiParams));

  }

}
