package Base.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.net.URLEncoder;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

/**
 * RSA非对称加密
 * RSA加密算法是一种非对称加密算法，公钥加密私钥解密。RSA是1977年由罗纳德·李维斯特（Ron Rivest）、阿迪·萨莫尔（Adi Shamir）和伦纳德·阿德曼（Leonard Adleman）一起提出的
 *
 * Base64编码
 * 为了解决ASCII中一些字符在IDEA中不可打印的问题，使用Base64编码
 *
 * Cipher提供加解密API，其中RSA非对称加密解密内容长度是有限制的，加密长度不超过117Byte，解密长度不超过128Byte
 * * * RSA本就是一种针对少量内容进行加密的方式，因此会存在长度限制，若存在必须调整的场景，有两种方式
 * * * 1、进行分段处理，将加密内容转换为Byte，再分段进行加密和拼接，每117字符分割一次；解密也是相同，每128字符解密一次，再拼接起来
 * * * * * https://blog.csdn.net/weixin_41760738/article/details/109449124
 * * * 2、调整公钥与私钥的创建时的密钥大小，实际加密内容是必须小于密钥长度的，因此将密钥长度调大即可。   -- 这个类中的创建密钥的方法，就将密钥大小从 1024 加到了 2048
 *
 * @Author: xiongying
 * @Date: 2024/2/1 11:26
 */
public class RSAKeyDemo {

    /**
     * 公钥加密方法
     *
     * @param publicKeyText 公钥
     * @param text          将要加密的信息
     * @return Base64编码的字符串
     * @throws Exception
     */

    public static String encryptByPublicKey(String publicKeyText, String text) throws Exception {
        // 返回按照 X.509 标准进行编码的密钥的字节
        // x509EncodedKeySpec2 是一种规范、规格
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 让密钥工厂按照指定的 规范 生成公钥
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
        Cipher cipher = Cipher.getInstance("RSA");
        // 对加密初始化，使用加密模式，公钥加密
        // Cipher.ENCRYPT_MODE 可以用 1 代替
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] result = cipher.doFinal(text.getBytes());
        // 返回 Base64编码的字符串
        return Base64.encodeBase64String(result);
    }

    /**
     * 私钥加密方法
     * 过程与encryptByPublicKey()方法类似
     *
     * @param privateKeyText 私钥
     * @param text           将要加密的信息
     * @return Base64编码的字符串
     * @throws Exception
     */
    public static String encryptByPrivateKey(String privateKeyText, String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }

    /**
     * 公钥解密方法
     * Base64 解密
     *
     * @param publicKeyText 公钥
     * @param text          待解密的信息
     * @return 字符串
     * @throws Exception
     */

    public static String decryptByPublicKey(String publicKeyText, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 私钥解密方法
     * 过程与encryptByPublicKey()方法类似
     *
     * @param privateKeyText 私钥
     * @param text           待解密的信息
     * @return 字符串
     * @throws Exception
     */

    public static String decryptByPrivateKey(String privateKeyText, String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 创建RSA公私钥对
     *
     * @return new RsaKeyPair(publicKeyString, privateKeyString)
     * @throws NoSuchAlgorithmException
     */
    public static HashMap<String, String> generateKeyPair() throws NoSuchAlgorithmException {
        // 创建公私钥对，指定算法 - RSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        // 设置密钥大小 2048 bits， >=512 这个参数内容直接影响加密内容的大小
        keyPairGenerator.initialize(2048);
        // 创建KeyPair对象，用于接收公私钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 生成随机的公私钥对
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 把密钥转换成 Base64编码的字符串
        String publicKeyString = Base64.encodeBase64String(rsaPublicKey.getEncoded());
        String privateKeyString = Base64.encodeBase64String(rsaPrivateKey.getEncoded());

        HashMap<String, String> key = new HashMap<>();
        key.put("public", publicKeyString);
        key.put("private", privateKeyString);
        return key;
    }

    public static void main(String[] args) {
        String token = "{" +
                "\"platformCode\":\"desc23024\"," +
                "\"phone\":\"15255555555\"," +
                "\"name\":\"张三\"," +
                "\"timestamp\":\"2024-02-20 18:21:20.213\"," +
                "\"url\":\"/client\"" +
                "}";

        try {
//            Map<String, String> key = generateKeyPair();
//            String publicKey = key.get("public");
//            String privateKey = key.get("private");
//            System.out.println("key：" + key);
//            System.out.println(publicKey + "     :     " + privateKey);
            String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjaSpr8Mhf+YHQ44Fzv3lnevQhB+hkmEDlTLA8NJYgoBwOEskVwIPa9E4sETF4ljyhGfiNUdiTNQ44wkWfCWo/QkSd+CTpU4O2P39L0gxnCFO7xn1u7V3ghETr2yTDR6GSbcErQBnHcJGX/hif+xxJx7DCsuHYngXwx46rJZYgL1kMwKJPsblVjo8GPIEIPKrvmAouShpzm5GnKr15er73t2qpq8AMgY//pjZxuku9l6TMFg5oxCuahaftaGb68/G9AW1W1Ypln1p5x5TnEok3Ro7TKl1+xi90CvXrM4yx9O00pr8N5vSA2h6BW/ek/PzYyiPkGAlDvoZtZ1VL8NArQIDAQAB";
            String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCNpKmvwyF/5gdDjgXO/eWd69CEH6GSYQOVMsDw0liCgHA4SyRXAg9r0TiwRMXiWPKEZ+I1R2JM1DjjCRZ8Jaj9CRJ34JOlTg7Y/f0vSDGcIU7vGfW7tXeCEROvbJMNHoZJtwStAGcdwkZf+GJ/7HEnHsMKy4dieBfDHjqslliAvWQzAok+xuVWOjwY8gQg8qu+YCi5KGnObkacqvXl6vve3aqmrwAyBj/+mNnG6S72XpMwWDmjEK5qFp+1oZvrz8b0BbVbVimWfWnnHlOcSiTdGjtMqXX7GL3QK9eszjLH07TSmvw3m9IDaHoFb96T8/NjKI+QYCUO+hm1nVUvw0CtAgMBAAECggEAbZ2G7EyYekBsUq1wdVYsAuDiB73q7jtB6I7UngpLEQOQ1R/hyawAfzH1KwSuF5M6a5lxiEXsHwUF2T3JNt52PwxsTHVyDbWw6+DztUmriuaEDaoLb+7pw7CviUz/KGH9DsMiTThIlNEqYU75+4Hht6Ko0ovcBCpoSUn1ombfqxC/PuzrJEXt4sgf11BC/ZywPiYFFDKPzbJlNSw6mAOs1cRUwRl0qlzHesIdAIGL13Mnq5QvZXst0UTgCvPk2mkWH5PBiUk0yrQHA6TsjNM54pZru+Eg//LihfrE4//OeL5qLMhONA6rrIyCRa68J7XcZtX9lcxO9LfMpz6e6JfGSQKBgQDLdyYc22Eo8lhjquIOUgdgjvKIfatb7vMN0A7On1LZ0O5mfvBC5Jfd89zTQoFOFeuBe/5xPla7FgakjgIpxtsMXJ0wtvleKKp0urRgTMIfMx3tJzjmoVGZRutAhUINq2WyzIqgKDZtgmaBmqLvL9uCV02cDwRSw7foCnOkdMEfLwKBgQCyNyFz8YUe3Rfe9EPlycCIJ7GwxuxFbLW6ZsjBJJ+Er4zeBtL4mgPSEaGl+aq8cFGjizQ+mRDz+3cDtMWdvkeYqJ70fWG9plT5HLn7nsIgJOvecXNxVSzWdO9AF00PB8p+dYp7/76NpSg3YQNrOXv3zSnC0WmwMBGesJE+lG6G4wKBgQCUhTn18JHA0P6Caneu2mP2MQKLbaiLaCZVm91jKFvTEbckbF63haPx6ZP1RmOa+1c44qj+QvIOiOp6bgrYMgctw+EecUIgYHDk2nDWptFA8xBST8IyX+lviJwrMdrgnY+T78j1VcU9NOXcj2OGU5nZUB8f3rpaK/Hv2MlHb86VEwKBgGaoy2R2pDB7O9z66IHeDkJgW0Nx03jKfoS54Lsh1aIhXgwn8i+yyLCh53QsteWG7gA/mADXO6TQHmbMI2oSyjtuJuK/tyYAQsKdgaUodrr9icHBqLaIUiPXiFprEcCuxD7EhtmXILWHhfsgr0989aRxUrUe3LrZdczr+T8LUf/xAoGBAL2VveR/e2SIdPm0U4lHPzrt3eMzrUPGGluv9ewTFSX7GJ0spSLVcXpJuZfYuejgqk3OihciXHG8/E9OG4jm86FXWf5YDfWene98N6bEEiZFqFtebjAbt8tQhzyQW0qYXD854Xkkh3m8+247DHAyq2EKzvaoHAqOwNbEDdGY/mrL";

            // 私钥加密
            String decode = encryptByPrivateKey(privateKey, token);
            System.out.println("decode：" + decode);
            // 公钥解密
            String encode = decryptByPublicKey(publicKey, decode);
            System.out.println("encode：" + encode);

            // URL编码  -- accessToken 需要将特殊符号转义才能在 get 请求中使用
            String encodedParam = URLEncoder.encode(decode, "UTF-8");
            System.out.println("Encoded parameter: " + encodedParam);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
