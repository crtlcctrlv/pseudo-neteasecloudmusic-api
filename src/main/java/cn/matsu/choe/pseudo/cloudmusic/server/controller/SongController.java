package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.core.util.StrUtil;
import cn.matsu.choe.pseudo.cloudmusic.server.constants.CommonConst;

@RestController
public class SongController extends BaseController {

  @GetMapping("/song/detail")
  public void detail(@RequestParam String ids) {

    String url = "https://music.163.com/weapi/v3/song/detail";

    List<String> idList = StrUtil.split(ids, ',');

    // [{"id":1},{"id":2},{"id":3}]
    String cParam = "["
        + idList.stream().map(id -> "{\"id\":" + id + "}").collect(Collectors.joining(",")) + "]";
    String idsParam = "[" + ids + "]";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("c", cParam);
    apiParams.put("ids", idsParam);

    writeResp(url, buildHttpEntity(apiParams));

  }

  @GetMapping("/song/url")
  public void url(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "999000") Integer br) {

    String url = "https://music.163.com/api/song/enhance/player/url";

    String idsParam = "[" + id + "]";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("ids", idsParam);
    apiParams.put("br", br);

    writeResp(CommonConst.LINUX_FORWARD_URL, buildLinuxEntity(url, apiParams));

  }

  @GetMapping("/check/music")
  public void check(@RequestParam String id,
      @RequestParam(required = false, defaultValue = "999000") Integer br) {

    String url = "https://music.163.com/weapi/song/enhance/player/url";

    String idsParam = "[" + id + "]";

    Map<String, Object> apiParams = new HashMap<>();
    apiParams.put("ids", idsParam);
    apiParams.put("br", br);

    // TODO

    writeResp(url, buildHttpEntity(apiParams));

  }

}
