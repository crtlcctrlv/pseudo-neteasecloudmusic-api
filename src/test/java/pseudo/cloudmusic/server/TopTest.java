package pseudo.cloudmusic.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

public class TopTest extends AbstractIntegrationTest {

  @Test
  public void toplistArtist() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/toplist/artist"), JsonNode.class, 0);

    assertTrue(resp.getBody().get("list").get("artists").size() > 0);

  }

  @Test
  public void toplistDetail() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/toplist/detail"), JsonNode.class, 0);

    assertTrue(resp.getBody().get("list").size() > 0);

  }

  @Test
  public void song() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/top/song?type={type}"), JsonNode.class, 0);

    assertTrue(resp.getBody().get("data").size() > 0);

  }

  @Test
  public void album() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/top/album?limit={limit}"), JsonNode.class, 10);

    assertTrue(resp.getBody().get("albums").size() == 10);

  }

  @Test
  public void mv() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/top/mv?limit={limit}"), JsonNode.class, 10);

    assertTrue(resp.getBody().get("data").size() == 10);
  }

  @Test
  public void artist() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/top/artists?limit={limit}"), JsonNode.class, 10);

    assertTrue(resp.getBody().get("artists").size() == 10);
  }

  @Test
  public void list() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/top/list?idx={idx}"), JsonNode.class, 23);

    assertEquals("云音乐说唱榜", resp.getBody().get("playlist").get("name").asText());
  }

  @Test
  public void playlist() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/top/playlist?limit=10"), JsonNode.class);

    assertTrue(resp.getBody().get("playlists").size() == 10);
  }

  @Test
  public void playlistHighquality() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/top/playlist/highquality?limit=10"), JsonNode.class);

    assertTrue(resp.getBody().get("playlists").size() == 10);
  }

}
