package pseudo.cloudmusic.server;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

public class CommentTest extends AbstractIntegrationTest {

  @Test
  public void music() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/comment/music?id={id}"), JsonNode.class, "186016");

    // 周杰伦-晴天
    assertTrue(resp.getBody().get("total").asInt() > 9999);
  }

  @Test
  public void album() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/comment/album?id={id}"), JsonNode.class, "32311");

    // 专辑-神的游戏
    assertTrue(resp.getBody().get("total").asInt() > 999);
  }

  @Test
  public void playlist() {
    ResponseEntity<JsonNode> resp = testRestTemplate.getForEntity(url("/comment/playlist?id={id}"),
        JsonNode.class, "2635459782");

    // 歌单-徐佳莹十首
    assertTrue(resp.getBody().get("total").asInt() > 0);
  }

  @Test
  public void mv() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/comment/mv?id={id}"), JsonNode.class, "10892907");

    // mv-我和我的祖国
    assertTrue(resp.getBody().get("total").asInt() > 9999);
  }

  @Test
  public void video() {
    ResponseEntity<JsonNode> resp = testRestTemplate.getForEntity(url("/comment/video?id={id}"),
        JsonNode.class, "BA91283CAFECAFC355D040A4CF228235");

    // video-李荣浩&杨丞琳《年轮说》
    assertTrue(resp.getBody().get("total").asInt() > 999);
  }

  @Test
  public void event() {
    ResponseEntity<JsonNode> resp = testRestTemplate.getForEntity(
        url("/comment/event?threadId={threadId}"), JsonNode.class, "A_EV_2_7768773546_48353");

    // 丁磊动态-评论
    assertTrue(resp.getBody().get("hotComments").size() > 0);
  }

}
