package kr.co.daou.api.service;

import kr.co.daou.api.vo.MessageVO;

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

public class SendKakaoService {
    MessageVO vo = new MessageVO();
    TokenTestService tok = new TokenTestService();
    String request;
    public String sendKakaoAT(String refkey, String to, String message, String senderkey, String templatecode, String button_name, String button_type, String url_pc, String url_mobile) {
        String input = null;
        StringBuffer result = new StringBuffer();
        String token = tok.getToken();
        URL url = null;
        try {
        	
        	//--------------------------------------------------------------s
            // SSL 인증서 무시 : 비즈뿌리오 API 운영을 접속하는 경우 해당 코드 필요 없음
        	//--------------------------------------------------------------s
        	///*
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }
            }};
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
           
            //개발
            url = new URL("https://dev-api.bizppurio.com:10443/v3/message");
            //*/
            
            //운영
            //url = new URL("https://api.bizppurio.com/v3/message");
            
            	
            /** Connection 설정 **/
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Authorization", "Bearer " + token);
            connection.addRequestProperty("Accept-Charset", "utf-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);

            /** Request **/
            OutputStream os = connection.getOutputStream();
            if (button_name.equals("null")){
                request = "{\"account\":\"" + vo.getAccount() + "\", \"refkey\":\"" + refkey + "\", \"type\":\"at\", \"from\":\"" + vo.getFrom() + "\", \"to\":\"" + to + "\", \"content\":{\"at\":{\"senderkey\":\"" + senderkey + "\", \"templatecode\":\"" + templatecode + "\", \"message\":\"" + message + "\"}}}";
            }else if (!button_name.equals("null")){
                request = "{\"account\":\"" + vo.getAccount() + "\", \"refkey\":\"" + refkey + "\", \"type\":\"at\", \"from\":\"" + vo.getFrom() + "\", \"to\":\"" + to + "\", \"content\":{\"at\":{\"senderkey\":\"" + senderkey + "\", \"templatecode\":\"" + templatecode + "\", \"message\":\"" + message + "\",\"button\":[{\"name\":\"" + button_name + ", \"type\":\"" + button_type + ", \"url_pc\":\"" + url_pc + ", \"url_mobile\"" + url_mobile + "}]}}}";
            }
            System.out.println("전송 json : " + request);
            os.write(request.getBytes("UTF-8"));
            os.flush();
            os.close();

            /** Response **/
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            while ((input = in.readLine()) != null) {
                result.append(input);
            }
            connection.disconnect();
            System.out.println(result.toString());

        } catch (IOException e) {
            // TODO Auto-generated catch block
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result.toString();
    }
}