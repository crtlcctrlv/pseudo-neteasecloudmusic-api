package pseudo.cloudmusic.server;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

public class RelateTest extends AbstractIntegrationTest {

  @Test
  public void video() {

    ResponseEntity<JsonNode> resp = testRestTemplate.getForEntity(url("/related/allvideo?id={id}"),
        JsonNode.class, "BA91283CAFECAFC355D040A4CF228235");

    // 李荣浩&杨丞琳《年轮说》相关视频
    assertTrue(resp.getBody().get("data").size() > 0);

  }

}
