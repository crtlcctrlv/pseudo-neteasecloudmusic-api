package pseudo.cloudmusic.server;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

public class BannerTest extends AbstractIntegrationTest {

  @Test
  public void banner() {

    ResponseEntity<JsonNode> resp = testRestTemplate.getForEntity(url("/banner"), JsonNode.class);

    assertTrue(resp.getBody().get("banners").size() > 0);

  }

  @Test
  public void radioBanner() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/dj/banner"), JsonNode.class);

    assertTrue(resp.getBody().get("data").size() > 0);

  }

}
