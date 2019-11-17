package cn.matsu.choe.pseudo.cloudmusic.server.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cn.hutool.core.exceptions.UtilException;

public class JacksonUtil {

  public static ObjectNode readValue(ObjectMapper objectMapper, String content) {
    try {
      return objectMapper.readValue(content, ObjectNode.class);
    } catch (Exception e) {
      throw new UtilException(e);
    }
  }

}
