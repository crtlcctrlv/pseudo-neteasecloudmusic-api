package pseudo.cloudmusic.server;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;


public class ArtistTest extends AbstractIntegrationTest {

  @Test
  public void info() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/artists?id={id}"), JsonNode.class, "6452");

    assertEquals("周杰伦", resp.getBody().get("artist").get("name").asText());

  }

  @Test
  public void list() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/artist/list?limit=10"), JsonNode.class);

    assertTrue(resp.getBody().get("artists").size() == 10);

  }

  @Test
  public void mv() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/artist/mv?id={id}"), JsonNode.class, "6452");

    String artistName = resp.getBody().get("mvs").get(0).get("artistName").asText();

    assertEquals("周杰伦", artistName);

  }

  @Test
  public void album() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/artist/album?id={id}"), JsonNode.class, "6452");

    assertEquals("周杰伦", resp.getBody().get("artist").get("name").asText());
    assertTrue(resp.getBody().get("hotAlbums").size() > 0);

  }

  @Test
  public void desc() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/artist/desc?id={id}"), JsonNode.class, "6452");

    assertThat(resp.getBody().get("briefDesc").asText(), containsString("亚洲流行天王"));

  }
}
