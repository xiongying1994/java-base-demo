package Base.httpUrl;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * JSON格式入参的HTTP请求
 * 1、HttpURLConnection版本，比较底层，没有什么封装，拿了直接去请求
 * 2、HttpClient，主流版本，封装好的工具，直接使用HttpPost与HttpGet，模拟请求即可，轻松方便
 * *    各种 setHeader()什么的方法也比较齐全
 *
 * @Author: xiongying
 * @Date: 2022/11/22 14:36
 */
public class HttpByJsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpByJsonUtil.class);

    /**
     * 使用HttpClient调用http访问
     * *    最为常用的http调用方式
     * *    Post使用httpPost，GET使用httpGet，存在区分
     *
     * @param url
     * @param jsonInput
     * @param appID
     * @param appKey
     * @return
     */
    public static String HttpClientPostNew(String url, JSONObject jsonInput, String appID, String appKey) {
        String result = "";
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
            httpPost.setHeader("X-APP-ID", appID);
            httpPost.setHeader("X-APP-KEY", appKey);

            // 旧版直接生成entity的方法弃用了，现在都使用StringEntity.setContentType()
//            HttpEntity entity = new StringEntity(jsonInput.toString(), "application/json", "UTF-8");
            StringEntity entity = new StringEntity(jsonInput.toString(), "UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(2000)
                    .setConnectTimeout(2000)
                    .build();
            httpPost.setConfig(requestConfig);

            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                logger.info("发送http请求成功................");
                if (null != httpEntity) {
                    result = EntityUtils.toString(httpEntity, "UTF-8");
                }
            } else {
                throw new Exception("HttpClient Error : statusCode = " + status + " where visit url :" + url);
            }
        } catch (Exception e) {
            logger.error("调用接口异常--url:{}----json:{}-----errInfo:{}", url, jsonInput, e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 陈旧的版本，使用HttpURLConnection创建http链接
     * *    针对一些比较老的服务，可能新版本HttpClient无法使用的场景，直接使用HttpURLConnection进行http链接创建
     * *    POST与GET也是直接写在connection服务配置中，无需区分
     * *    入参与出参均直接使用字符流的方式进行传输，调用connection中的流读取方法
     *
     * @param url
     * @param jsonInput
     * @param appID
     * @param appKey
     * @return
     */
    public static String HttpClientPost(String url, JSONObject jsonInput, String appID, String appKey) {
        String result = "";
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            // 创建POST请求
            connection.setRequestMethod("POST");
            //设置参数类型是json格式
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("X-APP-ID", appID);
            connection.setRequestProperty("X-APP-KEY", appKey);

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // 设置请求方式
            connection.setRequestMethod("POST");
            // 设置是否向HttpURLConnection输出
            connection.setDoOutput(true);
            // 设置是否从httpUrlConnection读入
            connection.setDoInput(true);
            // 设置是否使用缓存
            connection.setUseCaches(false);

            // 创建链接
            connection.connect();
            String body = jsonInput.toString();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(body);
            writer.close();

            // 在这里进行正式的HTTP请求操作
            int responseCode = connection.getResponseCode();
            InputStream inputStream;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                logger.info("发送http请求成功................");
                inputStream = connection.getInputStream();
            } else {
                logger.info("发送http请求失败................");
                inputStream = connection.getErrorStream();
            }
            //定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
