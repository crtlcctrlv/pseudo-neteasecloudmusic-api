package pseudo.cloudmusic.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

public class RadioTest extends AbstractIntegrationTest {

  @Test
  public void detail() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/dj/detail?rid={rid}"), JsonNode.class, "336355127");

    assertEquals("代码时间", resp.getBody().get("djRadio").get("name").asText());
  }

  @Test
  public void programToplist() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/dj/program/toplist"), JsonNode.class);

    assertTrue(resp.getBody().get("toplist").size() == 100);
  }

  @Test
  public void toplist() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/dj/toplist"), JsonNode.class);

    assertTrue(resp.getBody().get("toplist").size() > 0);
  }

  @Test
  public void programList() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/dj/program?rid={rid}"), JsonNode.class, "336355127");

    assertTrue(resp.getBody().get("programs").size() > 0);
  }

  @Test
  public void programDetail() {
    ResponseEntity<JsonNode> resp = testRestTemplate.getForEntity(url("/dj/program/detail?id={id}"),
        JsonNode.class, "794491090");

    assertEquals("软技能 - 王小刚", resp.getBody().get("program").get("name").asText());
  }

  @Test
  public void categories() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/dj/catelist"), JsonNode.class);

    assertTrue(resp.getBody().get("categories").size() > 0);
  }

  @Test
  public void categoryExcludeHot() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/dj/category/excludehot"), JsonNode.class);

    assertTrue(resp.getBody().get("data").size() > 0);
  }

  @Test
  public void paygift() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/dj/paygift?limit={limit}"), JsonNode.class, 10);

    assertTrue(resp.getBody().get("data").get("list").size() == 10);
  }

  @Test
  public void categoryRecommend() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/dj/category/recommend"), JsonNode.class);

    assertTrue(resp.getBody().get("data").size() > 0);
  }


  @Test
  public void recommend() {
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/dj/recommend"), JsonNode.class);

    assertTrue(resp.getBody().get("djRadios").size() > 0);
  }

  @Test
  public void recommendByType() {
    // 有声书
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/dj/recommend/type?type=10001"), JsonNode.class);

    assertTrue(resp.getBody().get("djRadios").size() > 0);
  }

  @Test
  public void todayPerfered() {
    // 今日优选
    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/dj/today/perfered"), JsonNode.class);

    assertTrue(resp.getBody().get("data").size() > 0);
  }

}
