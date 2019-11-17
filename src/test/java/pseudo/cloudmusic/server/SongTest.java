package pseudo.cloudmusic.server;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

public class SongTest extends AbstractIntegrationTest {

  @Test
  public void detail() throws Exception {

    ResponseEntity<JsonNode> resp = testRestTemplate.getForEntity(url("/song/detail?ids={id}"),
        JsonNode.class, "347230,210049");

    String name1 = resp.getBody().get("songs").get(0).get("name").asText();
    String name2 = resp.getBody().get("songs").get(1).get("name").asText();

    assertEquals("海阔天空", name1);
    assertEquals("布拉格广场", name2);

  }

}
