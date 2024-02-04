package Base.httpUrl;


import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 利用HttpClient进行get、post请求的工具类
 * 整体设计，httpClient在工具类初始化时进行初始化
 *
 * @author xiongying
 */
public class HttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private CloseableHttpClient httpClient = null;

    private static String charset = "UTF-8";
    private static int timeOut = 60000;
//    @Value("${interface.req.timeout}")
//    public void setReqTimeOut(int reqTimeOut) {
//        this.timeOut = reqTimeOut;
//    }

    public HttpClientUtil() {
        try {
            if (null == this.httpClient) {
                // 默认创建
                this.httpClient = HttpClients.createDefault();
                // 建造者模式
                this.httpClient = HttpClientBuilder.create().build();
            }
        } catch (Exception e) {
            logger.error("网络连接失败 " + e.getMessage());
        }
    }

    private HttpPost initPost(String url) {
        HttpPost httppost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeOut)
                .setConnectTimeout(timeOut)
                .setSocketTimeout(timeOut).build();
        httppost.setConfig(requestConfig);
        return httppost;
    }

    private HttpGet initGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeOut)
                .setConnectTimeout(timeOut)
                .setSocketTimeout(timeOut).build();
        httpGet.setConfig(requestConfig);
        return httpGet;
    }

    public String doGet(String url) {
        // 响应模型
        CloseableHttpResponse response = null;
        // 初始化Get请求
        HttpGet httpGet = initGet(url);
        String result;

        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                logger.info("发送http请求成功................");
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                    return result;
                } else {
                    //  + httpGet.getURI() 我自己加的，没测过
                    throw new Exception("HttpClient Get Error : statusCode = " + status + " where visit url :" + httpGet.getURI());
                }
            }
        } catch (Exception ex) {
            logger.error("调用Get接口异常--url:{}-----errInfo:{}", httpGet.getURI(), ex.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Post请求
     * Map入参
     * @param url
     * @param param
     * @return
     */
    public String doPost(String url, Map<String, Object> param) {
        CloseableHttpResponse response = null;
        HttpPost httpPost = initPost(url);
        String result;
        try {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> keySet = param.keySet();
            for (String key : keySet) {
                if (param.get(key) != null) {
                    nvps.add(new BasicNameValuePair(key, param.get(key).toString()));
                }
            }
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(nvps, charset);
            httpPost.setEntity(uefEntity);
            response = httpClient.execute(httpPost);

            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                logger.info("发送http请求成功................");
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                    return result;
                } else {
                    //  + httppost.getURI() 我自己加的，没测过
                    throw new Exception("HttpClient Post Error : statusCode = " + status + " where visit url :" + httpPost.getURI());
                }
            }
        } catch (Exception ex) {
            logger.error("调用Post接口异常--url:{}----param:{}-----errInfo:{}", httpPost.getURI(), param, ex.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Post请求
     * 请求map类型，仅在结果多了一步JSON转map的处理
     *
     * @param url
     * @param param
     * @return
     */
    public Map<String, Object> doPost_Map(String url, Map<String, Object> param) {
        CloseableHttpResponse response = null;
        HttpPost httpPost = initPost(url);
        String result;
        try {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> keySet = param.keySet();
            for (String key : keySet) {
                if (param.get(key) != null) {
                    nvps.add(new BasicNameValuePair(key, param.get(key).toString()));
                }
            }
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
            httpPost.setEntity(uefEntity);

            response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                logger.info("发送http请求成功................");
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                    Gson gson = new Gson();
                    return gson.fromJson(result, Map.class);
                } else {
                    //  + httpPost.getURI() 我自己加的，没测过
                    throw new Exception("HttpClient Post Error : statusCode = " + status + " where visit url :" + httpPost.getURI());
                }
            }
        } catch (Exception ex) {
            logger.error("调用Post接口异常--url:{}----param:{}-----errInfo:{}", httpPost.getURI(), param, ex.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Post请求
     * 附加头信息的http调用
     * @param url
     * @param param  请求入参
     * @param header 请求头信息
     * @return
     */
    public String doPost(String url, Map<String, Object> param, Map<String, String> header) {
        CloseableHttpResponse response = null;
        HttpPost httpPost = initPost(url);
        String result;
        try {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> keySet = param.keySet();
            for (String key : keySet) {
                if (param.get(key) != null) {
                    nvps.add(new BasicNameValuePair(key, param.get(key).toString()));
                }
            }
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
            httpPost.setEntity(uefEntity);
            for (String head : header.keySet()) {
                if (header.get(head) != null) {
                    httpPost.setHeader(head, header.get(head));
                }
            }
            response = httpClient.execute(httpPost);

            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                logger.info("发送http请求成功................");
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                    return result;
                } else {
                    //  + httpPost.getURI() 我自己加的，没测过
                    throw new Exception("HttpClient Post Error : statusCode = " + status + " where visit url :" + httpPost.getURI());
                }
            }
        } catch (Exception ex) {
            logger.error("调用Post接口异常--url:{}----param:{}-----errInfo:{}", httpPost.getURI(), param, ex.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Post请求
     * 自定义配置内容
     *
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public static String doPost(String url, int timeout, Map<String, Object> param) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;
        try {
            httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            // http请求配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(timeout)
                    .setConnectTimeout(timeout)
                    .build();
            httpPost.setConfig(requestConfig);

            // 将入参Map转为Json格式
            Gson gson = new Gson();
            String json = gson.toJson(param);
            StringEntity se = new StringEntity(json, "utf-8");
            se.setContentType("application/json");
            httpPost.setEntity(se);

            response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                    return result;
                } else {
                    throw new Exception("HttpClient Post Error : statusCode = " + status + " where visit url :" + url);
                }
            }
        } catch (Exception e) {
            logger.error("调用Post接口异常--url:{}----param:{}-----errInfo:{}", url, param, e.getMessage());
        } finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
