package cn.matsu.choe.pseudo.cloudmusic.server.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.AES;

public class CryptoUtil {

  private static final String BASE62 =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  private static final String IV = "0102030405060708";

  private static final String PRESET_KEY = "0CoJUm6Qyw8W8jud";

  private static final String EAPI_KEY = "e82ckenh8dichen8";

  private static final String LINUXAPI_KEY = "rFgB&h#%2?^eDg:Q";

  private static final String PUBLIC_KEY =
      "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgtQn2JZ34ZC28NWYpAUd98iZ37BUrX/aKzmFbt7clFSs6sXqHauqKWqdtLkF2KexO40H1YTX8z2lSgBBOAxLsvaklV8k4cBFK9snQXE9/DDaFt6Rr7iVZMldczhC0JNgTz+SHXT6CBHuX3e9SdB1Ua44oncaTWz7OBGLbCiK45wIDAQAB";

  private static final AES PRE_AES =
      new AES(Mode.CBC, Padding.PKCS5Padding, PRESET_KEY.getBytes(), IV.getBytes());

  private static final AES EAPI_AES = new AES(Mode.ECB, Padding.PKCS5Padding, EAPI_KEY.getBytes());

  private static final AES LINUXAPI_AES =
      new AES(Mode.ECB, Padding.PKCS5Padding, LINUXAPI_KEY.getBytes());

  private static String randomSecretKey() {
    char[] cArray = new char[16];

    for (int i = 0; i < 16; i++) {
      cArray[i] = BASE62.charAt(RandomUtil.randomInt(0, 62) % 62);
    }

    return new String(cArray);
  }

  public static String eapiEncrypt(String url, String text) {

    String message = StrUtil.format("nobody{}use{}md5forencrypt", url, text);

    String digest = MD5.create().digestHex(message);

    String data = StrUtil.format("{}-36cd479b6b5-{}-36cd479b6b5-{}", url, text, digest);

    return EAPI_AES.encryptHex(data).toUpperCase();

  }

  private static String weapiRsaEncrypt(String data) {

    // The Oracle provider (SunJCE) only supports "ECB" mode (which is the same as
    // "NONE").
    AsymmetricCrypto rsa =
        new AsymmetricCrypto("RSA/ECB/NoPadding", null, Base64.decode(PUBLIC_KEY));

    return HexUtil.encodeHexStr(rsa.encrypt(data, KeyType.PublicKey));

  }

  // private static String weapiAESDecrypt(String data, String secretKey) {
  //
  // AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, secretKey.getBytes(), IV.getBytes());
  //
  // String preDecrypt = aes.decryptStr(Base64.decode(data));
  //
  // return PRE_AES.decryptStr(Base64.decode(preDecrypt));
  //
  // }

  private static ImmutablePair<String, String> weapiAESEncrypt(String jsonStr) {

    String preResult = Base64.encode(PRE_AES.encrypt(jsonStr));

    String secretKey = randomSecretKey();

    AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, secretKey.getBytes(), IV.getBytes());

    String result = Base64.encode(aes.encrypt(preResult));

    return ImmutablePair.of(secretKey, result);
  }

  public static ImmutablePair<String, String> weapiEncrypt(String jsonStr) {
    ImmutablePair<String, String> pair = weapiAESEncrypt(jsonStr);

    String params = pair.getRight();
    String encSecKey = weapiRsaEncrypt(StringUtils.reverse(pair.getLeft()));

    return ImmutablePair.of(params, encSecKey);
  }

  public static String linuxapiEncrypt(String jsonStr) {
    return LINUXAPI_AES.encryptHex(jsonStr).toUpperCase();
  }

}
