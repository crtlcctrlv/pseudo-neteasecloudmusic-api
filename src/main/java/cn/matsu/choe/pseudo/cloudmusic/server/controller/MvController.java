package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;

@RestController
public class MvController extends BaseController {

  @GetMapping("/mv/all")
  public void all(@RequestParam(required = false, defaultValue = "全部") String area,
      @RequestParam(required = false, defaultValue = "全部") String type,
      @RequestParam(required = false, defaultValue = "上升最快") String order,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://interface.music.163.com/weapi/mv/all";

    JSONObject tags = new JSONObject();
    tags.put("地区", area);
    tags.put("类型", type);
    tags.put("排序", order);

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("tags", tags.toJSONString());
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("total", "true");

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/mv/detail")
  public void detail(@RequestParam String mvid) {

    String url = "https://music.163.com/weapi/mv/detail";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", mvid);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/mv/url")
  public void mvUrl(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "1080") String res) {

    String url = "https://music.163.com/weapi/song/enhance/play/mv/url";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", id);
    apiParams.put("r", res);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/mv/first")
  public void first(@RequestParam(required = false, defaultValue = "") String area,
      @RequestParam(required = false, defaultValue = "30") Integer limit) {

    String url = "https://interface.music.163.com/weapi/mv/first";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("area", area);
    apiParams.put("limit", limit);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/mv/exclusive/rcmd")
  public void exclusive(@RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://interface.music.163.com/api/mv/exclusive/rcmd";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("offset", offset);
    apiParams.put("limit", limit);

    writeResp(url, buildHttpEntity(apiParams));

  }

}
