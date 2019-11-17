package pseudo.cloudmusic.server;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

public class SimilarTest extends AbstractIntegrationTest {

  @Test
  public void playlist() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/simi/playlist?id={id}"), JsonNode.class, "347230");

    // 对应 歌曲' 光辉岁月 ' 相似歌单
    assertTrue(resp.getBody().get("playlists").size() > 0);

  }

  @Test
  public void song() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/simi/song?id={id}"), JsonNode.class, "347230");

    // 对应 歌曲' 光辉岁月 ' 相似歌曲
    assertTrue(resp.getBody().get("songs").size() > 0);

  }

  @Test
  public void mv() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/simi/mv?mvid={mvid}"), JsonNode.class, "10892907");

    // 王菲-我和我的祖国 相似mv
    assertTrue(resp.getBody().get("mvs").size() > 0);

  }

}
