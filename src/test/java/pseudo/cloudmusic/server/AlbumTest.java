package pseudo.cloudmusic.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

public class AlbumTest extends AbstractIntegrationTest {

  @Test
  public void info() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/album?id={id}"), JsonNode.class, "32311");

    assertEquals("神的游戏", resp.getBody().get("album").get("name").asText());

    assertEquals("张悬", resp.getBody().get("album").get("artist").get("name").asText());

  }

  @Test
  public void newest() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/album/newest"), JsonNode.class);

    assertTrue(resp.getBody().get("albums").size() > 0);

  }

  @Test
  public void detailDynamic() {

    ResponseEntity<JsonNode> resp = testRestTemplate
        .getForEntity(url("/album/detail/dynamic?id={id}"), JsonNode.class, "32311");

    int commentCount = resp.getBody().get("commentCount").asInt();
    int shareCount = resp.getBody().get("shareCount").asInt();

    assertTrue(commentCount > 0);
    assertTrue(shareCount > 0);

  }

}
