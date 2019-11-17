package pseudo.cloudmusic.server;

import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;

@Ignore
public class NeedLoginAPITest extends AbstractIntegrationTest {

  @BeforeClass
  public static void beforeClass() {
    TEST_REST_TEMPLATE.getForEntity(
        "http://localhost:" + PORT + "/login/cellphone?phone={phone}&password={password}",
        String.class, "15950512611", "naruto@1988");
  }

  @AfterClass
  public static void afterClass() {
    TEST_REST_TEMPLATE.getForEntity("http://localhost:" + PORT + "/logout", String.class);
  }

  @Test
  public void recommendResource() {

    ResponseEntity<String> resp =
        testRestTemplate.getForEntity(url("/recommend/resource"), String.class);

    assertTrue(JSONObject.parseObject(resp.getBody()).getJSONArray("recommend").size() > 0);

  }

  @Test
  public void recommendSong() {

    ResponseEntity<String> resp =
        testRestTemplate.getForEntity(url("/recommend/songs"), String.class);

    assertTrue(JSONObject.parseObject(resp.getBody()).getJSONArray("recommend").size() > 0);

  }

  @Test
  public void artistSublist() {

    ResponseEntity<String> resp =
        testRestTemplate.getForEntity(url("/artist/sublist"), String.class);

    assertTrue(JSONObject.parseObject(resp.getBody()).getIntValue("count") >= 0);

  }

  @Test
  public void djSublist() {

    ResponseEntity<String> resp = testRestTemplate.getForEntity(url("/dj/sublist"), String.class);

    assertTrue(JSONObject.parseObject(resp.getBody()).getIntValue("count") >= 0);

  }

  @Test
  public void mvSublist() {

    ResponseEntity<String> resp = testRestTemplate.getForEntity(url("/mv/sublist"), String.class);

    assertTrue(JSONObject.parseObject(resp.getBody()).getIntValue("count") >= 0);

  }

  @Test
  public void albumSublist() {

    ResponseEntity<String> resp =
        testRestTemplate.getForEntity(url("/album/sublist"), String.class);

    assertTrue(JSONObject.parseObject(resp.getBody()).getIntValue("count") >= 0);

  }

  @Test
  public void simiArtist() {

    // 对应和周杰伦相似歌手
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/simi/artist?id=6452"), JsonNode.class);

    assertTrue(resp.getBody().get("artists").size() >= 0);

  }

  @Test
  public void digitalAlbumPruchased() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/digitalAlbum/purchased"), JsonNode.class);

    assertTrue(resp.getBody().get("code").asInt() == 200);

  }

  @Test
  public void msgForwards() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/msg/forwards"), JsonNode.class);

    assertTrue(resp.getBody().get("code").asInt() == 200);

  }

  @Test
  public void msgNotices() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/msg/notices"), JsonNode.class);

    assertTrue(resp.getBody().get("code").asInt() == 200);

  }

  @Test
  public void msgPrivate() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/msg/private"), JsonNode.class);

    assertTrue(resp.getBody().get("code").asInt() == 200);

  }

  @Test
  public void msgPrivateHistory() {

    // 网易云音乐 官方账号私信
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/msg/private/history?uid=1"), JsonNode.class);

    assertTrue(resp.getBody().get("code").asInt() == 200);

  }

  @Test
  public void msgComments() {

    // 网易云音乐 官方账号私信
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/msg/comments?uid={uid}"), JsonNode.class, "1");

    assertTrue(resp.getBody().get("code").asInt() == 200);

  }

  @Test
  public void personalFm() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("personal_fm"), JsonNode.class);

    assertTrue(resp.getBody().get("data").size() > 0);

  }

  @Test
  public void likeList() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/likelist?uid={uid}"), JsonNode.class, "48353");

    // 丁磊-喜欢音乐列表
    assertTrue(resp.getBody().get("ids").size() > 0);

  }

}
