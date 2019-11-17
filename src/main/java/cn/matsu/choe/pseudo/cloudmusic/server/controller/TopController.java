package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.matsu.choe.pseudo.cloudmusic.server.constants.CommonConst;

@RestController
public class TopController extends BaseController {

  private static final Map<String, String> topIndex = new HashMap<>();

  @GetMapping("/toplist/artist")
  public void toplistArtist(@RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/toplist/artist";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("type", 1);
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/toplist/detail")
  public void toplistDetail() {

    String url = "https://music.163.com/weapi/toplist/detail";

    writeResp(url, buildHttpEntity(null));

  }

  @GetMapping("/top/song")
  public void top(@RequestParam Integer type) {

    String url = "https://music.163.com/weapi/v1/discovery/new/songs";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("areaId", type);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/top/album")
  public void album(@RequestParam(required = false, defaultValue = "ALL") String type,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/album/new";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("area", type);
    apiParams.put("offset", offset);
    apiParams.put("limit", limit);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/top/artists")
  public void artists(@RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/artist/top";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("offset", offset);
    apiParams.put("limit", limit);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/top/mv")
  public void topMv(@RequestParam(required = false, defaultValue = "") String area,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/mv/toplist";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("area", area);
    apiParams.put("offset", offset);
    apiParams.put("limit", limit);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(apiParams));

  }

  // "0": 云音乐新歌榜,
  //
  // "1": 云音乐热歌榜,
  //
  // "2": 网易原创歌曲榜,
  //
  // "3": 云音乐飙升榜,
  //
  // "4": 云音乐电音榜,
  //
  // "5": UK排行榜周榜,
  //
  // "6": 美国Billboard周榜
  //
  // "7": KTV嗨榜,
  //
  // "8": iTunes榜,
  //
  // "9": Hit FM Top榜,
  //
  // "10": 日本Oricon周榜
  //
  // "11": 韩国Melon排行榜周榜,
  //
  // "12": 韩国Mnet排行榜周榜,
  //
  // "13": 韩国Melon原声周榜,
  //
  // "14": 中国TOP排行榜(港台榜),
  //
  // "15": 中国TOP排行榜(内地榜)
  //
  // "16": 香港电台中文歌曲龙虎榜,
  //
  // "17": 华语金曲榜,
  //
  // "18": 中国嘻哈榜,
  //
  // "19": 法国 NRJ EuroHot 30周榜,
  //
  // "20": 台湾Hito排行榜,
  //
  // "21": Beatport全球电子舞曲榜,
  //
  // "22": 云音乐ACG音乐榜,
  //
  // "23": 云音乐说唱榜
  //
  // "24": 云音乐古典音乐榜
  //
  // "25": 云音乐电音榜
  //
  // "26": 抖音排行榜
  //
  // "27": 新声榜
  //
  // "28": 云音乐韩语榜
  //
  // "29": 英国Q杂志中文版周榜
  //
  // "30": 电竞音乐榜
  //
  // "31": 云音乐欧美热歌榜
  //
  // "32": 云音乐欧美新歌榜
  //
  // "33": 说唱TOP榜
  static {
    topIndex.put("0", "3779629");
    topIndex.put("1", "3778678");
    topIndex.put("2", "2884035");
    topIndex.put("3", "19723756");
    topIndex.put("4", "10520166");
    topIndex.put("5", "180106");
    topIndex.put("6", "60198");
    topIndex.put("7", "21845217");
    topIndex.put("8", "11641012");
    topIndex.put("9", "120001");
    topIndex.put("10", "60131");
    topIndex.put("11", "3733003");
    topIndex.put("12", "60255");
    topIndex.put("13", "46772709");
    topIndex.put("14", "112504");
    topIndex.put("15", "64016");
    topIndex.put("16", "10169002");
    topIndex.put("17", "4395559");
    topIndex.put("18", "1899724");
    topIndex.put("19", "27135204");
    topIndex.put("20", "112463");
    topIndex.put("21", "3812895");
    topIndex.put("22", "71385702");
    topIndex.put("23", "991319590");
    topIndex.put("24", "71384707");
    topIndex.put("25", "1978921795");
    topIndex.put("26", "2250011882");
    topIndex.put("27", "2617766278");
    topIndex.put("28", "745956260");
    topIndex.put("29", "2023401535");
    topIndex.put("30", "2006508653");
    topIndex.put("31", "2809513713");
    topIndex.put("32", "2809577409");
    topIndex.put("33", "2847251561");
  }

  @GetMapping("/top/list")
  public void list(@RequestParam String idx) {

    String url = "https://music.163.com/api/v3/playlist/detail";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("id", topIndex.get(idx));
    apiParams.put("n", 10000);

    writeResp(CommonConst.LINUX_FORWARD_URL, buildLinuxEntity(url, apiParams));

  }

  @GetMapping("/top/playlist")
  public void top(@RequestParam(required = false, defaultValue = "全部") String cat,
      @RequestParam(required = false, defaultValue = "hot") String order,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    String url = "https://music.163.com/weapi/playlist/list";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("cat", cat);
    apiParams.put("order", order);
    apiParams.put("limit", limit);
    apiParams.put("offset", offset);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/top/playlist/highquality")
  public void topHighQuality(@RequestParam(required = false, defaultValue = "全部") String cat,
      @RequestParam(required = false, defaultValue = "20") Integer limit,
      @RequestParam(required = false, defaultValue = "0") Long before) {

    String url = "https://music.163.com/weapi/playlist/highquality/list";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("cat", cat);
    apiParams.put("limit", limit);
    apiParams.put("lasttime", before);
    apiParams.put("total", true);

    writeResp(url, buildHttpEntity(apiParams));

  }

}
