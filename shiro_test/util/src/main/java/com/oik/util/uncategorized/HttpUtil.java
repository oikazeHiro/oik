package com.oik.util.uncategorized;

import com.alibaba.fastjson2.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    private HttpUtil(){}

    private static Log logger = LogFactory.getLog(HttpUtil.class);


    /**
     * 发送HTTP_GET请求
     *  该方法会自动关闭连接,释放资源
     * @param reqURL    请求地址(含参数)
     * @param decodeCharset 解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
     * @return 远程主机响应正文
     */
    public static String sendGetRequest(String reqURL, String decodeCharset){
        long responseLength = 0;       //响应长度
        String responseContent = null; //响应内容
        HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例
        HttpGet httpGet = new HttpGet(reqURL);           //创建org.apache.http.client.methods.HttpGet
        try{
            HttpResponse response = httpClient.execute(httpGet); //执行GET请求
            HttpEntity entity = response.getEntity();            //获取响应实体
            if(null != entity){
                responseLength = entity.getContentLength();
                responseContent = EntityUtils.toString(entity, decodeCharset==null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity); //Consume response content
            }
            System.out.println("请求地址: " + httpGet.getURI());
            System.out.println("响应状态: " + response.getStatusLine());
            System.out.println("响应长度: " + responseLength);
            System.out.println("响应内容: " + responseContent);
        }catch(ClientProtocolException e){
            logger.debug("该异常通常是协议错误导致,比如构造HttpGet对象时传入的协议不对(将'http'写成'htp')或者服务器端返回的内容不符合HTTP协议要求等,堆栈信息如下", e);
        }catch(ParseException e){
            logger.debug(e.getMessage(), e);
        }catch(IOException e){
            logger.debug("该异常通常是网络原因引起的,如HTTP服务器未启动等,堆栈信息如下", e);
        }finally{
            httpClient.getConnectionManager().shutdown(); //关闭连接,释放资源
        }
        return responseContent;
    }


    /**
     * 发送HTTP_POST请求
     * 该方法为sendPostRequest(String,String,boolean,String,String) 的简化方法该方法在对请求数据的编码和响应数据的解码时,所采用的字符集均为UTF-8
     * 当 isEncoder=true时,其会自动对sendData中的[中文][|][ ]等特殊字符进行URLEncoder.encode(string,"UTF-8")
     * @param isEncoder 用于指明请求数据是否需要UTF-8编码,true为需要
     */
    public static String sendPostRequest(String reqURL, String sendData, boolean isEncoder){
        return sendPostRequest(reqURL, sendData, isEncoder, null, null);
    }


    /**
     * 发送HTTP_POST请求
     *  该方法会自动关闭连接,释放资源
     *  当isEncoder=true时,其会自动对sendData中的[中文][|][ ]等特殊字符进行URLEncoder.encode(string,encodeCharset)
     * @param reqURL        请求地址
     * @param sendData      请求参数,若有多个参数则应拼接成param11=value11&22=value22&33=value33的形式后,传入该参数中
     * @param isEncoder     请求数据是否需要encodeCharset编码,true为需要
     * @param encodeCharset 编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
     * @param decodeCharset 解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
     * @return 远程主机响应正文
     */
    public static String sendPostRequest(String reqURL, String sendData, boolean isEncoder, String encodeCharset, String decodeCharset){
        String responseContent = null;
        HttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(reqURL);
        //httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
        try{
            if(isEncoder){
                List formParams = new ArrayList();
                for(String str : sendData.split("&")){
                    formParams.add(new BasicNameValuePair(str.substring(0,str.indexOf("=")), str.substring(str.indexOf("=")+1)));
                }
                httpPost.setEntity(new StringEntity(URLEncodedUtils.format(formParams, encodeCharset==null ? "UTF-8" : encodeCharset)));
            }else{
                httpPost.setEntity(new StringEntity(sendData));
            }

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, decodeCharset==null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity);
            }
        }catch(Exception e){
            logger.debug("与[" + reqURL + "]通信过程中发生异常,堆栈信息如下", e);
        }finally{
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }


    /**
     * 发送HTTP_POST请求
     * 该方法会自动关闭连接,释放资源
     * 该方法会自动对 params中的[中文][|][ ]等特殊字符进行URLEncoder.encode(string,encodeCharset)
     * @param reqURL        请求地址
     * @param params        请求参数
     * @param encodeCharset 编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
     * @param decodeCharset 解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
     * @return 远程主机响应正文
     */
    public static String sendPostRequest(String reqURL, Map<Object,Map.Entry> params, String encodeCharset, String decodeCharset){
        String responseContent = null;
        HttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(reqURL);
        List formParams = new ArrayList(); //创建参数队列
        for(Map.Entry entry : params.entrySet()){
            formParams.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
        }
        try{
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset==null ? "UTF-8" : encodeCharset));

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, decodeCharset==null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity);
            }
        }catch(Exception e){
            logger.debug("与[" + reqURL + "]通信过程中发生异常,堆栈信息如下", e);
        }finally{
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }


    /**
     * 发送HTTPS_POST请求
     * 该方法为sendPostSSLRequest(String,Map,String,String)方法的简化方法
     * 该方法在对请求数据的编码和响应数据的解码时,所采用的字符集均为UTF-8
     * 该方法会自动对params中的[中文][|][ ]等特殊字符进行URLEncoder.encode(string,"UTF-8")
     */
    public static String sendPostSSLRequest(String reqURL, Map params){
        return sendPostSSLRequest(reqURL, params, null, null);
    }


    /**
     * 发送HTTPS_POST请求
     * 该方法会自动关闭连接,释放资源
     * 该方法会自动对 params中的[中文][|][ ]等特殊字符进行URLEncoder.encode(string,encodeCharset)
     * @param reqURL        请求地址
     * @param params        请求参数
     * @param encodeCharset 编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
     * @param decodeCharset 解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
     * @return 远程主机响应正文
     */
    public static String sendPostSSLRequest(String reqURL, Map<Object,Map.Entry> params, String encodeCharset, String decodeCharset){
        String responseContent = "";
        HttpClient httpClient = new DefaultHttpClient();
        X509TrustManager xtm = new X509TrustManager(){
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() {return null;}
        };
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{xtm}, null);
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));

            HttpPost httpPost = new HttpPost(reqURL);
            List formParams = new ArrayList();
            for(Map.Entry entry : params.entrySet()){
                formParams.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset==null ? "UTF-8" : encodeCharset));

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, decodeCharset==null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            logger.debug("与[" + reqURL + "]通信过程中发生异常,堆栈信息为", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }


    /**
     * 发送HTTP_POST请求
     *  若发送的params 中含有中文,记得按照双方约定的字符集将中文URLEncoder.encode(string,encodeCharset)
     * 本方法默认的连接超时时间为30秒,默认的读取超时时间为30秒
     * @param reqURL 请求地址
     * @param params 发送到远程主机的正文数据,其数据类型为
    java.util.Map
     * @return 远程主机响应正文`HTTP状态码,如
    "SUCCESS`200"
    若通信过程中发生异常则返回"Failed`HTTP状态码",如
    "Failed`500"
     */
    public static String sendPostRequestByJava(String reqURL, Map<Object,Map.Entry> params)throws Exception{
        StringBuilder sendData = new StringBuilder();
        for(Map.Entry entry : params.entrySet()){
            sendData.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        if(sendData.length() > 0){
            sendData.setLength(sendData.length() - 1); //删除最后一个&符号
        }
        return sendPostRequestByJava(reqURL, sendData.toString());
    }

    /**
     * 发送HTTP_POST请求
     * 若发送的  params中含有中文,记得按照双方约定的字符集将中文 URLEncoder.encode(string,encodeCharset)
     * 本方法默认的连接超时时间为30秒,默认的读取超时时间为30秒
     * @param reqURL 请求地址
     * @param params 发送到远程主机的正文数据,其数据类型为
    java.util.Map
     * @return 远程主机响应正文`HTTP状态码,如
    "SUCCESS`200"
    若通信过程中发生异常则返回"Failed`HTTP状态码",如
    "Failed`500"
     */
    public static String sendPostRequest(String reqURL, Map<String,Object> params)throws Exception{
        StringBuilder sendData = new StringBuilder();
        try {
            for(Map.Entry entry : params.entrySet()){
                sendData.append( URLEncoder.encode((String) entry.getKey(),"UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(null == entry.getValue()?"":entry.getValue().toString(),"UTF-8")).append("&");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw e ;
        }
        if(sendData.length() > 0){
            sendData.setLength(sendData.length() - 1); //删除最后一个&符号
        }
        return sendPostRequestByJava(reqURL, sendData.toString());
    }


    /**
     * 发送HTTP_POST请求 若发送的sendData中含有中文,记得按照双方约定的字符集将中文URLEncoder.encode(string,encodeCharset)
     * 本方法默认的连接超时时间为30秒,默认的读取超时时间为30秒
     * @param reqURL   请求地址
     * @param sendData 发送到远程主机的正文数据
     * @return 远程主机响应正文`HTTP状态码,如
    "SUCCESS`200"
    若通信过程中发生异常则返回"Failed`HTTP状态码",如
    "Failed`500"
     */
    public static String sendPostRequestByJava(String reqURL, String sendData) throws Exception{
        HttpURLConnection httpURLConnection = null;
        OutputStream out = null; //写
        InputStream in = null;   //读
        int httpStatusCode = 0;  //远程主机响应的HTTP状态码
        try{
            URL sendUrl = new URL(reqURL);
            httpURLConnection = (HttpURLConnection)sendUrl.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);        //指示应用程序要将数据写入URL连接,其值默认为false
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(30000); //30秒连接超时
            httpURLConnection.setReadTimeout(30000);    //30秒读取超时

            out = httpURLConnection.getOutputStream();
            out.write(sendData.toString().getBytes());

            //清空缓冲区,发送数据
            out.flush();

            //获取HTTP状态码
            httpStatusCode = httpURLConnection.getResponseCode();

            in = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in, "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String readLine = "";
            StringBuilder sb = new StringBuilder();
            while((readLine=br.readLine())!=null){
                sb.append(readLine);
            }
//            byte[] byteDatas = new byte[in.available()];
//            in.read(byteDatas);
            return sb.toString();
        }catch(Exception e){
            //logger.debug(e.getMessage());
            throw e ;
            //return "Failed`" + httpStatusCode;
        }finally{
            if(out != null){
                try{
                    out.close();
                }catch (Exception e){
                    logger.debug("关闭输出流时发生异常,堆栈信息如下", e);
                }
            }
            if(in != null){
                try{
                    in.close();
                }catch(Exception e){
                    logger.debug("关闭输入流时发生异常,堆栈信息如下", e);
                }
            }
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
                httpURLConnection = null;
            }
        }
    }

    /**
     * https posp请求，可以绕过证书校验
     * @param url
     * @param params
     * @return
     */
    public static final String sendHttpsRequestByPost(String url, Map<String,String> params) {
        String responseContent = null;
        HttpClient httpClient = new DefaultHttpClient();
        //创建TrustManager
        X509TrustManager xtm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        //这个好像是HOST验证
        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
            public void verify(String arg0, SSLSocket arg1) throws IOException {}
            public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
            public void verify(String arg0, X509Certificate arg1) throws SSLException {}
        };
        try {
            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");
            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[] { xtm }, null);
            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            socketFactory.setHostnameVerifier(hostnameVerifier);
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
            HttpPost httpPost = new HttpPost(url);
            List<BasicNameValuePair> formParams = new ArrayList<BasicNameValuePair>(); // 构建POST请求的表单参数
            for (String key : params.keySet()) {
                formParams.add(new BasicNameValuePair((String)key, params.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
            //
            //CCLogUtil.print("post调用="+url);
            //CCLogUtil.print("参数="+ JSON.toJSONString(params));
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity(); // 获取响应实体
            if (entity != null) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
                //CCLogUtil.print("返回="+responseContent);
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }

    /**
     * https posp请求，可以绕过证书校验
     * @param url
     * @param params
     * @return
     */
    public static final String sendHttpsRequestByGet(String url, Map<String,String> params) {
        String responseContent = null;
        HttpClient httpClient = new DefaultHttpClient();
        //创建TrustManager
        X509TrustManager xtm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        //这个好像是HOST验证
        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
            public void verify(String arg0, SSLSocket arg1) throws IOException {}
            public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
            public void verify(String arg0, X509Certificate arg1) throws SSLException {}
        };
        try {

            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");
            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[] { xtm }, null);
            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            socketFactory.setHostnameVerifier(hostnameVerifier);
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));

            /*
             * 由于GET请求的参数都是拼装在URL地址后方，所以我们要构建一个URL，带参数
             */
            URIBuilder uriBuilder = new URIBuilder(url);
            /** 第一种添加参数的形式 */
            if(null != params){
                for (String key : params.keySet()) {
                    uriBuilder.addParameter(key,params.get(key));
                }
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            //CCLogUtil.print("get调用="+url);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity(); // 获取响应实体
            if (entity != null) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
                //CCLogUtil.print("返回="+responseContent);
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }



//    /**
//     * https posp请求，可以绕过证书校验
//     * @param url
//     * @param params
//     * @return
//     */
//    public static final String sendHttpsRequestByPost(String url, Map<Object,Map.Entry> params) {
//		String responseContent = null;
//		HttpClient httpClient = new DefaultHttpClient();
//		//创建TrustManager
//		X509TrustManager xtm = new X509TrustManager() {
//			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
//			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
//			public X509Certificate[] getAcceptedIssuers() {
//				return null;
//			}
//		};
//		//这个好像是HOST验证
//		X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
//			public boolean verify(String arg0, SSLSession arg1) {
//				return true;
//			}
//			public void verify(String arg0, SSLSocket arg1) throws IOException {}
//			public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
//			public void verify(String arg0, X509Certificate arg1) throws SSLException {}
//		};
//		try {
//			//TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
//			SSLContext ctx = SSLContext.getInstance("TLS");
//			//使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
//			ctx.init(null, new TrustManager[] { xtm }, null);
//			//创建SSLSocketFactory
//			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
//			socketFactory.setHostnameVerifier(hostnameVerifier);
//			//通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
//			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
//			HttpPost httpPost = new HttpPost(url);
//			List formParams = new ArrayList(); // 构建POST请求的表单参数
//			for (Map.Entry entry : params.entrySet()) {
//				formParams.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
//			}
//			httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
//			HttpResponse response = httpClient.execute(httpPost);
//			HttpEntity entity = response.getEntity(); // 获取响应实体
//			if (entity != null) {
//				responseContent = EntityUtils.toString(entity, "UTF-8");
//			}
//		} catch (KeyManagementException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			// 关闭连接,释放资源
//			httpClient.getConnectionManager().shutdown();
//		}
//		return responseContent;
//	}


    /**
     * 发送HTTP_POST请求,json格式数据
     * @param url
     * @param body
     * @return
     * @throws Exception
     */
    public static String sendPostByJson(String url, String body) throws Exception {
        CloseableHttpClient httpclient = HttpClients.custom().build();
        HttpPost post = null;
        String resData = null;
        CloseableHttpResponse result = null;
        try {
            post = new HttpPost(url);
            HttpEntity entity2 = new StringEntity(body, Consts.UTF_8);
            post.setConfig(RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build());
            post.setHeader("Content-Type", "application/json");
            post.setEntity(entity2);
            result = httpclient.execute(post);
            if (HttpStatus.SC_OK == result.getStatusLine().getStatusCode()) {
                resData = EntityUtils.toString(result.getEntity());
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (post != null) {
                post.releaseConnection();
            }
            httpclient.close();
        }
        return resData;
    }

    /***
     * 获取ip地址
     * @param request
     * @return
     */
    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static String localIp = null ;

    /**
     * 获取本机ip
     * @return
     * @throws UnknownHostException
     */
    public static String getLocalIp() throws UnknownHostException{
        if(null == localIp){
            InetAddress addr = InetAddress.getLocalHost();
            String ip=addr.getHostAddress().toString(); //获取本机ip
            localIp = ip ;
        }
        return localIp ;
    }

    /**
     * 记录http请求相关内容
     * @param request
     * @return
     */
    public static String getHttpLog(HttpServletRequest request){
        StringBuffer sb = new StringBuffer();
        String getContextPath = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+getContextPath+"/";
        String getRemoteAddress=request.getRemoteAddr();
        String getServletPath =request.getServletPath();
        String getServletContext_getRealPath =request.getServletContext().getRealPath("/");
        String getRequestURL =request.getRequestURL().toString();
        String getRequestURI =request.getRequestURI();
        String getQueryString =request.getQueryString();
        String getRemoteUser =request.getRemoteUser();
        sb.append("getContextPath:"+ getContextPath +"<br>");
        sb.append("basePath:"+basePath+"<br>");
        sb.append("getRemoteAddress:"+ getRemoteAddress +"<br>");
        sb.append("getServletPath:"+ getServletPath +"<br>");
        sb.append("getServletContext_getRealPath:"+ getServletContext_getRealPath +"<br>");
        sb.append("getRequestURL:"+ getRequestURL +"<br>");
        sb.append("getRequestURI:"+ getRequestURI +"<br>");
        sb.append("getQueryString:"+ getQueryString +"<br>");
        sb.append("getRemoteUser:"+ getRemoteUser +"<br>");
        return sb.toString();
    }
}
