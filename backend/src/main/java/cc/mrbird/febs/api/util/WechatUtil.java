package cc.mrbird.febs.api.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.Security;

/**
 * 微信工具类
 */
public class WechatUtil {

    public static void main(String[] args) {
        String result = decryptData(
                "04cciGz45aj+RPBOdYNbaetp+4M2N76r0j7GFlXIG2D+PhqA7TvfK4Y6sgBEUP4c2LV8bEgWkYTO7hv3R1vAqxJo6WJTYp6BsO8c7zgPFvH+743ZQI9sti/OF+rzy2bmV9Stcn320e75pTaH1i5bhBTtqkHKXKxkPHMlAfQj1wgABCZJ+xumbe61kQJ+1exG/+YtN3YR9ruOMVVmnI9OtZ2++z2Pu3EaHVepNM85VqlRPp1jxp5UCpX5svlZMUy/vHPShPfmTzthtDGPOSsnpoCFZeMvfj7T5L+IkRzaLMgPdBjKmGjAUawc76B83isr110rGe+SapmgoST61IushExgfHQWjl6YSja/pHEJ5hRmG7ptBeKRG443z/M0IkN4AcLC8n1MjlnvdA6r/QAdXINatQokeEAhG9luVFE/4sRTzNbXDkhGggFWs544svpNDZAQcn00EvumhyLnpgf8tifYoN1LNEBk2ESWC0tajqg=",
                "d8a2727cd73ae7556ab00c7171b2b7378243bea9",
                "vokFo1h1RvOASFdjoJ5PJg=="
        );
        System.out.println("result = " + result);
    }

    public static String decryptData(String encryptDataB64, String sessionKeyB64, String ivB64) {
        return new String(
                decryptOfDiyIV(
                        Base64.decode(encryptDataB64),
                        Base64.decode(sessionKeyB64),
                        Base64.decode(ivB64)
                )
        );
    }

    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";
    private static Key key;
    private static Cipher cipher;

    private static void init(byte[] keyBytes) {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        System.out.println(key.getEncoded());
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(ALGORITHM_STR, "BC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param keyBytes      解密密钥
     * @param ivs           自定义对称解密算法初始向量 iv
     * @return 解密后的字节数组
     */
    private static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes, byte[] ivs) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedText;
    }

    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
