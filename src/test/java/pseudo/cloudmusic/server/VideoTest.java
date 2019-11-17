package pseudo.cloudmusic.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

public class VideoTest extends AbstractIntegrationTest {

  @Test
  public void detial() {
    ResponseEntity<JsonNode> resp = testRestTemplate
        .getForEntity(url("/video/detail?id=BA91283CAFECAFC355D040A4CF228235"), JsonNode.class);

    assertEquals("李荣浩&杨丞琳《年轮说》", resp.getBody().get("data").get("title").asText());
  }

  @Test
  public void url() {
    ResponseEntity<JsonNode> resp = testRestTemplate
        .getForEntity(url("/video/url?id=BA91283CAFECAFC355D040A4CF228235"), JsonNode.class);

    assertTrue(resp.getBody().get("urls").get(0).get("r").asInt() == 1080);
  }

  @Test
  public void groupList() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/video/group/list"), JsonNode.class);

    assertTrue(resp.getBody().get("data").size() > 0);
  }

}
