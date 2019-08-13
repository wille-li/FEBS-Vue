package cc.mrbird.febs.api.util;
import org.apache.commons.codec.binary.Base64;

public class WXCoreUtil {

    private static final String WATERMARK = "watermark";
    private static final String APPID = "appid";
    /**
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptedData, String sessionKey, String iv){
        String result = "";
        try {
            AESUtil aes = new AESUtil();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                result = new String(WxPKCS7EncoderUtil.decode(resultByte));
            }
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }
}
