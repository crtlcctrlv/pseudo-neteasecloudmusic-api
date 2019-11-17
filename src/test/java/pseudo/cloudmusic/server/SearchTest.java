package pseudo.cloudmusic.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import cn.hutool.core.util.StrUtil;

public class SearchTest extends AbstractIntegrationTest {

  // @Test
  // public void search() {
  //
  // ResponseEntity<String> resp =
  // testRestTemplate.getForEntity(url("/search?keywords={}"), String.class, "6452");
  //
  // }
  @Test
  public void searchDefault() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/search/default"), JsonNode.class);

    String realKeyword = resp.getBody().get("data").get("realkeyword").asText();

    assertTrue(StrUtil.isNotEmpty(realKeyword));
  }

  @Test
  public void searchSuggest() {

    ResponseEntity<JsonNode> resp = testRestTemplate
        .getForEntity(url("/search/suggest?keywords={keywords}"), JsonNode.class, "叶惠美");

    String albumName = resp.getBody().get("result").get("albums").get(0).get("name").asText();

    assertEquals("叶惠美", albumName);
  }

  @Test
  public void searchMultimatch() {

    ResponseEntity<JsonNode> resp = testRestTemplate
        .getForEntity(url("/search/multimatch?keywords={keywords}"), JsonNode.class, "周杰伦");

    String artistName = resp.getBody().get("result").get("artist").get(0).get("name").asText();

    assertEquals("周杰伦", artistName);
  }

  @Test
  public void searchHot() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/search/hot"), JsonNode.class);

    assertTrue(resp.getBody().get("result").get("hots").size() > 0);
  }

  @Test
  public void searchHotDetail() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/search/hot/detail"), JsonNode.class);

    assertTrue(resp.getBody().get("data").size() > 0);
  }

}
