package kr.co.daou.api.service;

import kr.co.daou.api.vo.MessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class SendRcsServiceAPI {
	
	public String id = "";
	public String apikey = "";
	public String brandid = "";
	public String baseid = "";
/*	
    public String sendAllMessage(String request) {
        MessageVO vo = new MessageVO();
        TokenTestService tokservice = new TokenTestService();
        
        if(id !=null && passwd !=null) {
        	tokservice.vo.setAccount(id);
        	tokservice.vo.setPassword(passwd);
        }
        
        String token = tokservice.getToken();
        
        if(token == null) return ("\ntoken is null");
        
        String input = null;
        StringBuffer result = new StringBuffer();
        URL url = null;
        Logger logger = LoggerFactory.getLogger(getClass());
        
        HttpsURLConnection connection = null;
        BufferedReader in = null;
        
        try {
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }
            } };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            url = new URL("https://api.bizppurio.com/v3/message");
            /** Connection 설정 **
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Authorization", "Bearer " + token);
            connection.addRequestProperty("Accept-Charset", "utf-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);
            /** Request **
            OutputStream os = connection.getOutputStream();
            System.out.println(request);
            os.write(request.getBytes("UTF-8"));
            os.flush();
            os.close();

            /** Response **
            if (connection.getResponseCode() == 200) {
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	            while ((input = in.readLine()) != null) {
	                result.append(input);
	            }
	            System.out.println(result.toString());
            } else {
            	in = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"));
	            while ((input = in.readLine()) != null) {
	                result.append(input);
	            }
            }
            
            connection.disconnect();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	e.printStackTrace();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.info("Response : " + result.toString());
        return "\n" + result.toString();
    }
*/
	
    public String getMsgbaseInfo() {
 
        TokenTestService tokservice = new TokenTestService();
        
        if(id.isEmpty()) {
        	return "id is Emty";
        }
        
        if(apikey.isEmpty()) {
        	return "apikey is Emty";
        }        
        
        String token = tokservice.getRCSToken(id, apikey);
        
        if(token == null) return ("\ntoken is null");
        
        String input = null;
        StringBuffer result = new StringBuffer();
        URL url = null;
        Logger logger = LoggerFactory.getLogger(getClass());
        
        HttpsURLConnection connection = null;
        BufferedReader in = null;
        
        try {
        	//--------------------------------------------------------------s
            // SSL 인증서 무시 : 비즈뿌리오 API 운영을 접속하는 경우 해당 코드 필요 없음
        	//--------------------------------------------------------------s
        	//*
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }
            } };
            
            
            SSLContext sc = null;
			try {
				sc = SSLContext.getInstance("SSL");
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            try {
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            
            //url = new URL("https://dev-api.bizppurio.com/v3/message");
            //url = new URL("https://dev-api.bizppurio.com:10443/v3/message");
            
            //*/
        	//--------------------------------------------------------------e            

            //운영
            url = new URL("https://rapi.bizppurio.com/rcsapi/v1/brand/msgbase/info?brandId="+ this.brandid + "&messagebaseId=" + this.baseid );
                   	
            /** Connection 설정 **/
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Authorization", "Bearer " + token);
            connection.addRequestProperty("Accept-Charset", "utf-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);
            /** Request **
            OutputStream os = connection.getOutputStream();
            System.out.println(request);
            os.write(request.getBytes("UTF-8"));
            os.flush();
            os.close(); */

            /** Response **/
            if (connection.getResponseCode() == 200) {
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	            while ((input = in.readLine()) != null) {
	                result.append(input);
	            }
	            System.out.println(result.toString());
            } else {
            	in = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"));
	            while ((input = in.readLine()) != null) {
	                result.append(input);
	            }
	            System.out.println(result.toString());
            }
            
            connection.disconnect();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	e.printStackTrace();
/*        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
*/          
        }

        logger.info("Response : " + result.toString());
        return "\n" + result.toString();
        
    }    
}
