package com.tydic.bop.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author lihong
 * @Date 2019.3.10
 */
public class HttpClientUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);


    private static int timeOut;

    @Value("${interface.req.timeout}")
    public void setReqTimeOut(int reqTimeOut) {
        this.timeOut = reqTimeOut;
    }

    public static String requestGet(String url) {
        String resp = "";
        HttpClient httpclient = new HttpClient();
        httpclient.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        httpclient.getParams().setSoTimeout(60000);

        GetMethod getMethod = new GetMethod(url);
        try {
            int statusCode = httpclient.executeMethod(getMethod);
            resp = getMethod.getResponseBodyAsString();
        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        } finally {
            getMethod.releaseConnection();
        }
        return resp;
    }

    public static String requestGet(String url, int outTime) {
        String resp = "";
        HttpClient httpclient = new HttpClient();
        httpclient.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        httpclient.getParams().setSoTimeout(outTime);
        httpclient.setConnectionTimeout(outTime);
        httpclient.setTimeout(outTime);

        GetMethod getMethod = new GetMethod(url);
        try {
            int statusCode = httpclient.executeMethod(getMethod);
            resp = getMethod.getResponseBodyAsString();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            getMethod.releaseConnection();
        }
        return resp;
    }
    /**
     * 使用post方式调用
     * @param url 调用的URL
     * @param requestBody 传递的参数值List
     * @return 调用得到的字符串
     */
    public static String httpClientPost(String url,String requestBody) throws Exception {
        StringBuilder sb =new StringBuilder();
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        //将表单的值放入postMethod中
        log.debug(" http url :" + url);
        try {
            postMethod.setRequestEntity(new StringRequestEntity(requestBody,"text","UTF-8"));
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(5000);

            int statusCode = httpClient.executeMethod(postMethod);

            if(statusCode != 200){
                throw new Exception ("HttpClient Error : statusCode = " + statusCode + " where visit url :" + url);
            }
            //以流的行式读入，避免中文乱码
            InputStream is = postMethod.getResponseBodyAsStream();
            BufferedReader dis=new BufferedReader(new InputStreamReader(is,"utf-8"));
            String str ="";
            while((str =dis.readLine())!=null){
                sb.append(str);
                //sb.append("\r\n"); // 默认这里没有换行，而是让所有的字符出现在一行里面。如需要换行，请去掉前面的注释
            }
        } catch (Exception e) {
            throw e;
        }finally{
            postMethod.releaseConnection();
        }
        return sb.toString();
    }
    /**
     * 使用post方式调用
     * @param url 调用的URL
     * @param values 传递的参数值List
     * @return 调用得到的字符串
     */
    public static String httpClientPost(String url,List<NameValuePair[]> values){
        StringBuilder sb =new StringBuilder();
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        //将表单的值放入postMethod中
        log.debug(" http url :" + url);
        for (NameValuePair[] value : values) {
            for (NameValuePair nvp : value) {
                log.debug(" http param :" + nvp.getName() + " = " + nvp.getValue());
            }
            postMethod.addParameters(value);
        }

        try {
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(5000);

            int statusCode = httpClient.executeMethod(postMethod);

            if(statusCode != 200){
                log.error("HttpClient Error : statusCode = " + statusCode + " where visit url :" + url);
                return "";
            }
            //以流的行式读入，避免中文乱码
            InputStream is = postMethod.getResponseBodyAsStream();
            BufferedReader dis=new BufferedReader(new InputStreamReader(is,"utf-8"));
            String str ="";
            while((str =dis.readLine())!=null){
                sb.append(str);
                //sb.append("\r\n"); // 默认这里没有换行，而是让所有的字符出现在一行里面。如需要换行，请去掉前面的注释
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("HttpClientUtil.httpClientPost():httpClient调用远程出错;发生网络异常;url:" + url);
        }finally{
            postMethod.releaseConnection();
        }
        return sb.toString();
    }

    /**
     * 使用post方式调用
     * @param url 调用的URL
     * @param values 传递的参数值
     * @return 调用得到的字符串
     */
    public static String httpClientPost(String url,NameValuePair[] values){
        List<NameValuePair[]> list = new ArrayList<NameValuePair[]>();
        list.add(values);
        return httpClientPost(url, list);
    }

    /**
     * 使用get方式调用
     * @param url 调用的URL
     * @return 调用得到的字符串
     */
    public static String httpClientGet(String url) throws Exception{
        StringBuilder sb =new StringBuilder();
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
        try {
            //设置请求和传输超时时间
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeOut);
            // 执行getMethod
            int status =httpClient.executeMethod(getMethod);
            if(status != HttpStatus.SC_OK){
                throw new Exception("HttpClient Error : statusCode = " + status + " where visit url :" + url);
            }
            //以流的行式读入，避免中文乱码
            InputStream is = getMethod.getResponseBodyAsStream();
            BufferedReader dis=new BufferedReader(new InputStreamReader(is,"utf-8"));
            String str ="";
            while((str =dis.readLine())!=null){
                sb.append(str);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        } finally {
            // 关闭连接
            getMethod.releaseConnection();
        }
        return sb.toString();
    }

    /**
     * 使用get方式调用
     * @param url 调用的URL
     * @return 调用得到的字符串
     */
    public static String httpClientGet(String url,String charSet){
        StringBuilder sb =new StringBuilder();
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
        try {
            // 执行getMethod
            httpClient.executeMethod(getMethod);
            //以流的行式读入，避免中文乱码
            InputStream is = getMethod.getResponseBodyAsStream();
            BufferedReader dis=new BufferedReader(new InputStreamReader(is,charSet));
            String str ="";
            while((str =dis.readLine())!=null){
                sb.append(str);
            }
        } catch (Exception e) {
            // 发生网络异常
            log.error(e.getMessage());
            System.out.println("HttpClientUtil.httpClientGet():httpClient调用远程出错;发生网络异常");
        } finally {
            // 关闭连接
            getMethod.releaseConnection();
        }
        return sb.toString();
    }

    public static String httpClientGetByHead(String url, String param, Map<String, String> header) throws Exception{
        StringBuilder sb =new StringBuilder();
        HttpClient httpClient = new HttpClient();

        // 拼接 get请求入参
        if (!"".equals(param)) {
            url = new StringBuilder().append(url.split("\\?")[0]).append("?").append(param).toString();
        }
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
        try {
            for (String head : header.keySet()) {
                if (header.get(head) != null) {
                    getMethod.setRequestHeader(head, header.get(head));
                }
            }
            //设置请求和传输超时时间
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeOut);
            // 执行getMethod
            int status =httpClient.executeMethod(getMethod);
            if(status != HttpStatus.SC_OK){
                throw new Exception("HttpClient Error : statusCode = " + status + " where visit url :" + url);
            }
            //以流的行式读入，避免中文乱码
            InputStream is = getMethod.getResponseBodyAsStream();
            BufferedReader dis=new BufferedReader(new InputStreamReader(is,"utf-8"));
            String str ="";
            while((str =dis.readLine())!=null){
                sb.append(str);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        } finally {
            // 关闭连接
            getMethod.releaseConnection();
        }
        return sb.toString();
    }


    /**
     * 将MAP转换成HTTP请求参数
     * @param map
     * @return
     */
    public static NameValuePair[] praseParameterMap(Map<String, String> map){

        NameValuePair[] nvps = new NameValuePair[map.size()];
        Set<String> keys = map.keySet();
        int i=0;
        for(String key:keys){
            nvps[i] = new NameValuePair();
            nvps[i].setName(key);
            nvps[i].setValue(map.get(key));
            i++;
        }
        return nvps;
    }

    /**
     * 使用post方式调用
     * @param url 调用的URL
     * @param values 传递的参数值
     * @param xml 传递的xml参数
     * @return
     */
    public static String httpClientPost(String url, NameValuePair[] values, String xml){
        StringBuilder sb = new StringBuilder();
        HttpClient client = new HttpClient();
        client.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

        PostMethod method = new PostMethod(url);
        method.setQueryString(values);
        method.addRequestHeader("Content-Type", "text/xml;charset=UTF-8");
        RequestEntity reis = null;
        InputStream input = null;
        InputStream is = null;
        BufferedReader dis = null;
        try {
            input = new ByteArrayInputStream(xml.getBytes("utf-8"));
            reis = new InputStreamRequestEntity(input);
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler());

            method.setRequestEntity(reis);
            client.executeMethod(method);

            // 以流的行式读入，避免中文乱码
            is = method.getResponseBodyAsStream();
            dis = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String str = "";
            while ((str = dis.readLine()) != null) {
                sb.append(str);
            }
        } catch (HttpException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
            try {
                if(dis != null){
                    dis.close();
                }

                if (is != null){
                    is.close();
                }

                if (input != null){
                    input.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 发送参数为json的http post 请求
     * @param url
     * @param json
     * @return
     */
    public static String sendJsonHttpPost(String url, String json) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        String responseInfo = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            ContentType contentType = ContentType.create("application/json", CharsetUtils.get("UTF-8"));
            httpPost.setEntity(new StringEntity(json, contentType));

            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
            httpPost.setConfig(requestConfig);

            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                if (null != entity) {
                    responseInfo = EntityUtils.toString(entity);
                }
            }else{
                throw new Exception ("HttpClient Error : statusCode = " + status + " where visit url :" + url);
            }
        } catch (Exception e) {
            log.error("调用接口异常--url:{}----json:{}-----errInfo:{}",url,json,e.getMessage());
            throw e;
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseInfo;
    }

    /**
     * 发送参数为json的http post 请求 - 携带头信息
     * @param url
     * @param json
     * @param header
     * @return
     * @throws Exception
     */
    public static String sendJsonHttpPostByHead(String url, String json, Map<String, String> header) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        String responseInfo = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            for (String head : header.keySet()) {
                if (header.get(head) != null) {
                    httpPost.setHeader(head, header.get(head));
                }
            }
            ContentType contentType = ContentType.create("application/json", CharsetUtils.get("UTF-8"));
            httpPost.setEntity(new StringEntity(json, contentType));

            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
            httpPost.setConfig(requestConfig);

            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                if (null != entity) {
                    responseInfo = EntityUtils.toString(entity);
                }
            }else{
                throw new Exception ("HttpClient Error : statusCode = " + status + " where visit url :" + url);
            }
        } catch (Exception e) {
            log.error("调用接口异常--url:{}----json:{}-----errInfo:{}",url,json,e.getMessage());
            throw e;
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseInfo;
    }




    public static void main(String[] args){
        try {
            String url = "http://192.168.129.162:10967/diag/diagnose/scene/list";
            String json = "{\"limit\":10,\"page\":1,\"sceneName\":\"abcdefg\"}";
            String resp = sendJsonHttpPost(url,json);
            System.out.println("resp:" + resp);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
        }
    }
}

