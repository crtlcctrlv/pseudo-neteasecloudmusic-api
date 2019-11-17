package cn.matsu.choe.pseudo.cloudmusic.server.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class PseudoConfig {

  @Bean
  public RestTemplate restTemplate() {
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    RestTemplate restTemplate = new RestTemplate();
    HttpComponentsClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory(httpClient);
    requestFactory.setConnectTimeout(3000);
    requestFactory.setReadTimeout(3000);

    restTemplate.setRequestFactory(requestFactory);

    return restTemplate;
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

}
