package cn.matsu.choe.pseudo.cloudmusic.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.matsu.choe.pseudo.cloudmusic.server.util.CryptoUtil;

public abstract class BaseController {

  @Autowired
  HttpServletRequest request;

  @Autowired
  HttpServletResponse response;

  @Autowired
  RestTemplate restTemplate;

  private String buildCookie(HttpServletRequest request, Object... cookies) {

    String cookie = request == null ? StrUtil.EMPTY : request.getHeader(HttpHeaders.COOKIE);

    if (ArrayUtil.isEmpty(cookies)) {
      return cookie;
    }

    String addCookie = StrUtil.join(";", cookies);

    if (StrUtil.isEmpty(cookie)) {
      return addCookie;
    } else if (StrUtil.isNotEmpty(addCookie)) {
      return cookie + ";" + StrUtil.SPACE + addCookie;
    }

    return cookie;
  }

  protected HttpEntity<MultiValueMap<String, String>> buildHttpEntity(
      Map<String, Object> apiParams) {

    return buildHttpEntity(null, apiParams);
  }

  protected HttpEntity<MultiValueMap<String, String>> buildHttpEntity(HttpServletRequest request,
      Map<String, Object> apiParams, Object... cookies) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.set(HttpHeaders.REFERER, "https://music.163.com");

    // TODO
    headers.set(HttpHeaders.USER_AGENT,
        "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0");

    String cookie = buildCookie(request, cookies);

    if (StrUtil.isNotEmpty(cookie)) {
      headers.set(HttpHeaders.COOKIE, cookie);
    }

    ImmutablePair<String, String> pair = CryptoUtil
        .weapiEncrypt(MapUtil.isEmpty(apiParams) ? "{}" : JSONObject.toJSONString(apiParams));

    MultiValueMap<String, String> postParams = new LinkedMultiValueMap<>();
    postParams.add("params", pair.getLeft());
    postParams.add("encSecKey", pair.getRight());

    return new HttpEntity<>(postParams, headers);
  }

  protected HttpEntity<MultiValueMap<String, String>> buildMobileHttpEntity(String url,
      Map<String, Object> apiParams) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.set(HttpHeaders.REFERER, "https://music.163.com");

    String cookie = request.getHeader(HttpHeaders.COOKIE);

    if (StringUtils.isNotEmpty(cookie)) {
      headers.set(HttpHeaders.COOKIE, cookie);
    }

    Date now = new Date();

    Cookie csrf = ServletUtil.getCookie(request, "__csrf");
    Cookie music_u = ServletUtil.getCookie(request, "music_u");
    Cookie music_a = ServletUtil.getCookie(request, "music_a");

    Map<String, String> headerParam = new HashMap<>();
    headerParam.put("appver", "6.1.1");
    headerParam.put("versioncode", "140");
    headerParam.put("buildver", String.valueOf(now.toInstant().getEpochSecond()));
    headerParam.put("resolution", "1920x1080");
    headerParam.put("os", "android");

    String randomStr = "" + Double.valueOf(Math.floor(Math.random() * 1000)).intValue();
    headerParam.put("requestId",
        String.valueOf(now.toInstant().toEpochMilli()) + "_" + StrUtil.padPre(randomStr, 4, '0'));

    if (csrf != null) {
      headerParam.put("__csrf", csrf.getValue());
    }

    if (music_u != null) {
      headerParam.put("MUSIC_U", music_u.getValue());
    }

    if (music_a != null) {
      headerParam.put("MUSIC_A", music_a.getValue());
    }

    apiParams.put("header", headerParam);

    String params = CryptoUtil.eapiEncrypt(url, JSONObject.toJSONString(apiParams));

    MultiValueMap<String, String> postParams = new LinkedMultiValueMap<>();
    postParams.add("params", params);

    return new HttpEntity<>(postParams, headers);
  }

  protected HttpEntity<MultiValueMap<String, String>> buildLinuxEntity(String url,
      Map<String, Object> apiParams) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.set(HttpHeaders.REFERER, "https://music.163.com");

    Map<String, Object> linuxapiPrams = new HashMap<>();
    linuxapiPrams.put("method", "POST");
    linuxapiPrams.put("url", url);
    linuxapiPrams.put("params", apiParams);

    String eparams = CryptoUtil.linuxapiEncrypt(JSONObject.toJSONString(linuxapiPrams));

    MultiValueMap<String, String> postParams = new LinkedMultiValueMap<>();
    postParams.add("eparams", eparams);

    return new HttpEntity<>(postParams, headers);
  }

  protected void writeResp(String url, HttpEntity<MultiValueMap<String, String>> httpEntity,
      Object... uriVariables) {
    writeResp(url, HttpMethod.POST, httpEntity, uriVariables);
  }

  protected void writeResp(String url, HttpMethod method,
      HttpEntity<MultiValueMap<String, String>> httpEntity, Object... uriVariables) {
    ResponseEntity<String> resp =
        restTemplate.exchange(url, method, httpEntity, String.class, uriVariables);

    response.setStatus(resp.getStatusCodeValue());

    // login
    if ("https://music.163.com/weapi/login/cellphone".equals("url")) {
      List<String> setCookies = resp.getHeaders().get(HttpHeaders.SET_COOKIE);

      // set cookies
      if (CollUtil.isNotEmpty(setCookies)) {
        for (String setCookie : setCookies) {
          String nameValue = StrUtil.split(setCookie, ";")[0];
          String[] nvArray = StrUtil.split(nameValue, "=");

          ServletUtil.addCookie(response, nvArray[0], nvArray[1], -1);
        }
      }
    }

    // logout
    if ("https://music.163.com/weapi/logout".equals(url)) {
      ServletUtil.addCookie(response, "__csrf", "", 0);
      ServletUtil.addCookie(response, "__remember_me", "", 0);
      ServletUtil.addCookie(response, "MUSIC_U", "", 0);
    }

    ServletUtil.write(response, resp.getBody() == null ? "" : resp.getBody(),
        MediaType.APPLICATION_JSON_UTF8_VALUE);
  }

  protected ResponseEntity<String> getResp(String url, HttpMethod method,
      HttpEntity<MultiValueMap<String, String>> httpEntity, Object... uriVariables) {
    return restTemplate.exchange(url, method, httpEntity, String.class, uriVariables);
  }

}
