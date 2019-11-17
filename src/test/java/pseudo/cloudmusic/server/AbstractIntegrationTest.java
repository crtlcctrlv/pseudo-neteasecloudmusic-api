package pseudo.cloudmusic.server;

import javax.annotation.PostConstruct;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.matsu.choe.pseudo.cloudmusic.server.PseudoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {PseudoApplication.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest {

  static int PORT;

  static TestRestTemplate TEST_REST_TEMPLATE;

  @LocalServerPort
  int port;

  @Autowired
  TestRestTemplate testRestTemplate;

  @PostConstruct
  private void postConstruct() {
    PORT = port;
    TEST_REST_TEMPLATE = testRestTemplate;
  }

  protected String url(String path) {
    return "http://localhost:" + port + path;
  }

}
