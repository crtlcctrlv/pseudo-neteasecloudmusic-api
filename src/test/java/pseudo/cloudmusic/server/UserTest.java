package pseudo.cloudmusic.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

public class UserTest extends AbstractIntegrationTest {

  @Test
  public void detail() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/user/detail?uid={uid}"), JsonNode.class, "43510405");

    String nickName = resp.getBody().get("profile").get("nickname").asText();

    assertEquals("呆若木一", nickName);
  }

  @Test
  public void playlist() {
    ResponseEntity<JsonNode> resp = testRestTemplate.getForEntity(
        url("/user/playlist?uid={uid}&limit={limit}"), JsonNode.class, "43510405", 10);

    // 默认歌单-喜欢的音乐
    assertTrue(resp.getBody().get("playlist").size() == 11);
  }

  @Test
  public void follows() {
    ResponseEntity<JsonNode> resp = testRestTemplate
        .getForEntity(url("/user/follows?uid={uid}&limit={limit}"), JsonNode.class, "43510405", 10);

    assertTrue(resp.getBody().get("follow").size() == 10);
  }

  @Test
  public void followeds() {
    ResponseEntity<JsonNode> resp = testRestTemplate.getForEntity(
        url("/user/followeds?uid={uid}&limit={limit}"), JsonNode.class, "43510405", 10);

    assertTrue(resp.getBody().get("followeds").size() == 10);
  }

  @Test
  public void event() {
    ResponseEntity<JsonNode> resp = testRestTemplate
        .getForEntity(url("/user/event?uid={uid}&limit={limit}"), JsonNode.class, "43510405", 10);

    assertTrue(resp.getBody().get("events").size() == 10);
  }

  @Test
  public void audio() {

    // 电台-代码时间
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/user/audio?uid={uid}"), JsonNode.class, "289680033");

    assertTrue(resp.getBody().get("count").asInt() > 0);
  }

}
