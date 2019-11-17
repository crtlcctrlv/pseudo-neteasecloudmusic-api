package pseudo.cloudmusic.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

public class PlaylistTest extends AbstractIntegrationTest {

  @Test
  public void info() {
    ResponseEntity<JsonNode> resp = testRestTemplate.getForEntity(url("/playlist/detail?id={id}"),
        JsonNode.class, "2286110231");

    assertEquals("木一的深夜歌单vol.1", resp.getBody().get("playlist").get("name").asText());
  }

  @Test
  public void subscribers() {
    ResponseEntity<JsonNode> resp = testRestTemplate.getForEntity(
        url("/playlist/subscribers?id={id}&limit={limit}"), JsonNode.class, "2286110231", "10");

    assertTrue(resp.getBody().get("subscribers").size() == 10);
  }


  @Test
  public void categories() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/playlist/catlist"), JsonNode.class);

    assertEquals("全部歌单", resp.getBody().get("all").get("name").asText());
  }

  @Test
  public void hot() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/playlist/hot"), JsonNode.class);

    assertTrue(resp.getBody().get("tags").size() > 0);
  }

}
