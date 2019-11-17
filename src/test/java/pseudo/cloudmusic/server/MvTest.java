package pseudo.cloudmusic.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

public class MvTest extends AbstractIntegrationTest {

  @Test
  public void all() {
    ResponseEntity<JsonNode> resp = testRestTemplate.getForEntity(url("/mv/all"), JsonNode.class);

    assertTrue(resp.getBody().get("data").size() == 20);
  }

  @Test
  public void detail() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/mv/detail?mvid={mvid}"), JsonNode.class, "10892907");

    assertEquals("我和我的祖国", resp.getBody().get("data").get("name").asText());
    assertEquals("王菲", resp.getBody().get("data").get("artistName").asText());
  }

  @Test
  public void url() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/mv/url?id={id}"), JsonNode.class, "10892907");

    assertEquals("10892907", resp.getBody().get("data").get("id").asText());
  }

  @Test
  public void exclusive() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/mv/exclusive/rcmd"), JsonNode.class);

    assertTrue(resp.getBody().get("data").size() > 0);
  }

  @Test
  public void first() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/mv/first?limit={limit}"), JsonNode.class, 10);

    assertTrue(resp.getBody().get("data").size() == 10);
  }

}
