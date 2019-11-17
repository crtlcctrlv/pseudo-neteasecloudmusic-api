package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dj")
public class RadioController extends BaseController {

  @GetMapping("/program/toplist")
  public void programToplist(@RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/api/program/toplist/v1";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/toplist")
  public void toplist(@RequestParam(required = false, defaultValue = "new") String type,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/api/djradio/toplist";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    // 0为新晋,1为热门
    apiParams.put("type", "new".equals(type) ? 0 : 1);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/detail")
  public void detail(@RequestParam String rid) {

    String url = "https://music.163.com/weapi/djradio/get";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", rid);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/program")
  public void programList(@RequestParam String rid,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset,
      @RequestParam(required = false, defaultValue = "false") Boolean asc) {

    String url = "https://music.163.com/weapi/dj/program/byradio";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("radioId", rid);
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("asc", asc);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/program/detail")
  public void programDetail(@RequestParam String id) {

    String url = "https://music.163.com/weapi/dj/program/detail";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", id);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/category/recommend")
  public void categoryRecommend() {

    String url = "http://music.163.com/weapi/djradio/home/category/recommend";

    writeResp(url, buildHttpEntity(request, null));

  }

  @GetMapping("/recommend")
  public void recommend() {

    String url = "https://music.163.com/weapi/djradio/recommend/v1";

    writeResp(url, buildHttpEntity(request, null));

  }

  @GetMapping("/recommend/type")
  public void recommendByType(@RequestParam String type) {

    String url = "https://music.163.com/weapi/djradio/recommend";

    // 有声书 10001
    // 知识技能 453050
    // 商业财经 453051
    // 人文历史 11
    // 外语世界 13
    // 亲子宝贝 14
    // 创作|翻唱 2001
    // 音乐故事 2
    // 3D|电子 10002
    // 相声曲艺 8
    // 情感调频 3
    // 美文读物 6
    // 脱口秀 5
    // 广播剧 7
    // 二次元 3001
    // 明星做主播 1
    // 娱乐|影视 4
    // 科技科学 453052
    // 校园|教育 4001
    // 旅途|城市 12

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("cateId", type);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

  @GetMapping("/catelist")
  public void categories() {

    String url = "https://music.163.com/weapi/djradio/category/get";

    writeResp(url, buildHttpEntity(null));

  }

  @GetMapping("/category/excludehot")
  public void categoryExcludeHot() {

    String url = "http://music.163.com/weapi/djradio/category/excludehot";

    writeResp(url, buildHttpEntity(null));

  }

  @GetMapping("/paygift")
  public void paygift(@RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/djradio/home/paygift/list?_nmclfl=1";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/today/perfered")
  public void todayPrefer(@RequestParam(required = false, defaultValue = "0") Integer page) {

    String url = "http://music.163.com/weapi/djradio/home/today/perfered";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("page", page);

    writeResp(url, buildHttpEntity(request, apiParams));

  }

}
