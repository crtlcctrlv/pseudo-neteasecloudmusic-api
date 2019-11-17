package pseudo.cloudmusic.server;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import cn.hutool.core.util.StrUtil;

public class LyricTest extends AbstractIntegrationTest {

  @Test
  public void lyric() {

    ResponseEntity<JsonNode> resp =
        testRestTemplate.getForEntity(url("/lyric?id={id}"), JsonNode.class, "33894312");

    String lyric = resp.getBody().get("lrc").get("lyric").asText();

    assertThat(StrUtil.split(lyric, '\n').get(1), containsString("难以忘记初次见你"));

  }

}
