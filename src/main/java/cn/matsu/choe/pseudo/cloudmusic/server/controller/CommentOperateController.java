package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentOperateController extends BaseController {

  private static final Map<Integer, String> tMap = new HashMap<>();

  private static final Map<Integer, String> typeMap = new HashMap<>();

  static {
    tMap.put(1, "add");
    tMap.put(0, "delete");
    tMap.put(2, "reply");

    typeMap.put(0, "R_SO_4_");
    typeMap.put(1, "R_MV_5_");
    typeMap.put(2, "A_PL_0_");
    typeMap.put(3, "R_AL_3_");
    typeMap.put(4, "A_DJ_1_");
    typeMap.put(5, "R_VI_62_");
    typeMap.put(6, "A_EV_2_");
  }

  @GetMapping
  public void comment(@RequestParam Integer t, @RequestParam Integer type, @RequestParam String id,
      @RequestParam String content, @RequestParam String commentId) {

    String url = "https://music.163.com/weapi/resource/comments/{tParam}";

    String tParam = tMap.get(t);

    String typeParam = typeMap.get(type);

    // const data = {
    // threadId: query.type + query.id
    // }

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("threadId", typeParam + id);

    writeResp(url, buildHttpEntity(apiParams), id);

  }

  @GetMapping("/like")
  public void music(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/api/v1/resource/comments/R_SO_4_{id}";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams), id);

  }

}
