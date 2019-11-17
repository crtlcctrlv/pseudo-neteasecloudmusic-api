package pseudo.cloudmusic.server;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

public class PersonalizedTest extends AbstractIntegrationTest {

  @Test
  public void playlist() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/personalized"), JsonNode.class);

    assertTrue(resp.getBody().get("result").size() == 20);
  }

  @Test
  public void newSong() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/personalized/newsong"), JsonNode.class);

    assertTrue(resp.getBody().get("result").size() > 0);
  }

  @Test
  public void mv() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/personalized/mv"), JsonNode.class);

    assertTrue(resp.getBody().get("result").size() > 0);
  }

  @Test
  public void djprogram() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/personalized/djprogram"), JsonNode.class);

    assertTrue(resp.getBody().get("result").size() > 0);
  }

}
