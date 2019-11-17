package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {

  private static final Map<Integer, String> typeMap = new HashMap<>();

  static {
    // 0: 'R_SO_4_', 歌曲
    // 1: 'R_MV_5_', MV
    // 2: 'A_PL_0_', 歌单
    // 3: 'R_AL_3_', 专辑
    // 4: 'A_DJ_1_', 电台,
    // 5: 'R_VI_62_' 视频
    typeMap.put(0, "R_SO_4_");
    typeMap.put(1, "R_MV_5_");
    typeMap.put(2, "A_PL_0_");
    typeMap.put(3, "R_AL_3_");
    typeMap.put(4, "A_DJ_1_");
    typeMap.put(5, "R_VI_62_");
  }

  @GetMapping("/music")
  public void music(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/api/v1/resource/comments/R_SO_4_{id}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams), id);

  }

  @GetMapping("/album")
  public void album(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/v1/resource/comments/R_AL_3_{id}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams), id);

  }

  @GetMapping("/playlist")
  public void playlist(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/v1/resource/comments/A_PL_0_{id}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams), id);

  }

  @GetMapping("/mv")
  public void mv(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/v1/resource/comments/R_MV_5_{id}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams), id);

  }

  @GetMapping("/dj")
  public void dj(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/v1/resource/comments/A_DJ_1_{id}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams), id);

  }

  @GetMapping("/video")
  public void video(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/v1/resource/comments/R_VI_62_{id}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams), id);

  }

  @GetMapping("/hot")
  public void hot(@RequestParam String id, @RequestParam Integer type,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String typePathParam = typeMap.get(type);

    String url = "https://music.163.com/weapi/v1/resource/hotcomments/{typePathParam}{id}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams), typePathParam, id);

  }


  @GetMapping("/event")
  public void event(@RequestParam String threadId,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset,
      @RequestParam(required = false, defaultValue = "0") Long beforeTime) {

    String url = "https://music.163.com/weapi/v1/resource/comments/{threadId}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("beforeTime", beforeTime);

    writeResp(url, buildHttpEntity(apiParams), threadId);

  }

}
