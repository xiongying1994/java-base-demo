package Base.httpUrl;


import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
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
public class HttpPostClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpPostClientUtil.class);
    private static String charset = "UTF-8";
    private CloseableHttpClient httpClient = null;
    private HttpPost httppost = null;

    public HttpPostClientUtil(String url) {
        try {
            if (null == this.httpClient) {
                this.httpClient = HttpClients.createDefault();
            }
            if (null == this.httppost) {
                this.httppost = new HttpPost(url);
            }
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(60000)
                    .setConnectTimeout(60000)
                    .setSocketTimeout(60000).build();
            httppost.setConfig(requestConfig);
        } catch (Exception e) {
            logger.error("网络连接失败 " + e.getMessage());
        }
    }

    /**
     * Post请求，Map入参
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String doPost(Map<String, Object> param) {
        CloseableHttpResponse response = null;
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
            httppost.setEntity(uefEntity);
            response = httpClient.execute(httppost);

            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                logger.info("发送http请求成功................");
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                    return result;
                } else {
                    //  + httppost.getURI() 我自己加的，没测过
                    throw new Exception("HttpClient Error : statusCode = " + status + " where visit url :" + httppost.getURI());
                }
            }
        } catch (Exception ex) {
            logger.error("调用接口异常--url:{}----param:{}-----errInfo:{}", httppost.getURI(), param, ex.getMessage());
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
     * 附加头信息的http调用
     *
     * @param param  请求入参
     * @param header 请求头信息
     * @return
     * @throws Exception
     */
    public String doPost(Map<String, Object> param, Map<String, String> header) {
        CloseableHttpResponse response = null;
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
            httppost.setEntity(uefEntity);
            for (String head : header.keySet()) {
                if (header.get(head) != null) {
                    httppost.setHeader(head, header.get(head));
                }
            }
            response = httpClient.execute(httppost);

            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                logger.info("发送http请求成功................");
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                    return result;
                } else {
                    //  + httppost.getURI() 我自己加的，没测过
                    throw new Exception("HttpClient Error : statusCode = " + status + " where visit url :" + httppost.getURI());
                }
            }
        } catch (Exception ex) {
            logger.error("调用接口异常--url:{}----param:{}-----errInfo:{}", httppost.getURI(), param, ex.getMessage());
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
     * 返回map类型，仅在结果多了一步JSON转map的处理
     *
     * @param param
     * @return
     * @throws Exception
     */
    public Map<String, Object> doPost_Map(Map<String, Object> param) {
        CloseableHttpResponse response = null;
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
            this.httppost.setEntity(uefEntity);

            response = httpClient.execute(httppost);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                logger.info("发送http请求成功................");
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                    Gson gson = new Gson();
                    return gson.fromJson(result, Map.class);
                } else {
                    //  + httppost.getURI() 我自己加的，没测过
                    throw new Exception("HttpClient Error : statusCode = " + status + " where visit url :" + httppost.getURI());
                }
            }
        } catch (Exception ex) {
            logger.error("调用接口异常--url:{}----param:{}-----errInfo:{}", httppost.getURI(), param, ex.getMessage());
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
     * 将入参的Map转为JSON进行请求，与隔壁类的方式就一样了
     *
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, Object> param) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;
        try {
            httpClient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            // http请求配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(180 * 1000)
                    .setConnectTimeout(120 * 1000)
                    .build();
            httppost.setConfig(requestConfig);

            // 将入参Map转为Json格式
            Gson gson = new Gson();
            String json = gson.toJson(param);
            StringEntity se = new StringEntity(json, "utf-8");
            se.setContentType("application/json");
            httppost.setEntity(se);

            response = httpClient.execute(httppost);
            HttpEntity resEntity = response.getEntity();
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                    return result;
                } else {
                    throw new Exception("HttpClient Error : statusCode = " + status + " where visit url :" + url);
                }
            }
        } catch (Exception e) {
            logger.error("调用接口异常--url:{}----param:{}-----errInfo:{}", url, param, e.getMessage());
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
