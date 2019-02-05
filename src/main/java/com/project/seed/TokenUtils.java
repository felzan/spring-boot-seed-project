package com.project.seed;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
@PropertySource("token.yaml")
public class TokenUtils extends Base{

  @Value("${secret}")
  private String key;
  @Value("${init_vector}")
  private String initVector;

  public String encrypt(final String value) {
    try {
      final IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
      final SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

      final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

      final byte[] encrypted = cipher.doFinal(value.getBytes());

      return Base64.encodeBase64String(encrypted);
    } catch (final Exception e) {
      logger.error(e.getMessage(), e);
    }

    return null;
  }

  public String decrypt(final String encrypted) {
    try {
      final IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
      final SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

      final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

      final byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

      return new String(original);
    } catch (final Exception e) {
      logger.error(e.getMessage(), e);
    }

    return null;
  }
}


