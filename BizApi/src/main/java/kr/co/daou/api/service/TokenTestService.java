package kr.co.daou.api.service;

import kr.co.daou.api.vo.MessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class TokenTestService {
		public MessageVO vo = new MessageVO();
    public String getToken() {
    	String input = null;
        StringBuffer result = new StringBuffer();
        String token = null;
        URL url = null;
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String datestr = format.format(Calendar.getInstance().getTime());
        LocalTime now = LocalTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeNow = now.format(timeFormat);
        //datestr = format.format(new Date());
        String directoryPath = ".\\logs\\TokenLog.";
        Logger logger = LoggerFactory.getLogger(getClass());
        //File logPath = new File("D:\\My Project_GIT\\api\\logs\\Token");
        
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
				try {
					sc.init(null, trustAllCerts, new java.security.SecureRandom());
				} catch (KeyManagementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            
            //url = new URL("https://dev-api.bizppurio.com/v1/token");
            //url = new URL("https://dev-api.bizppurio.com:10443/v1/token");
            
            //*/
        	//--------------------------------------------------------------e

            //운영
            url = new URL("https://api.bizppurio.com/v1/token");
        	
                        
            /** Connection 설정 **/
            String access = vo.getAccount() + ":" + vo.getPassword();
            byte[] idBytes = access.getBytes();
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] encodedBytes = encoder.encode(idBytes);
            String basic_encoding = new String(encodedBytes);

            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Authorization", "Basic " + basic_encoding);
            connection.addRequestProperty("Accept-Charset", "utf-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);
            /** Request **/

            /** Response **/
            if (connection.getResponseCode() == 200) {
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	            while ((input = in.readLine()) != null) {
	                result.append(input);
	            }
	            
	            System.out.println(result.toString());
	            
	            /* Get Token*/
	            String[] response = result.toString().split("\"");
	            token = response[3];
	            
	            /*
	            if(!logPath.exists()){
	                try{
	                    logPath.mkdirs();
	                    logger.info("Token LOG 폴더 생성 됨.");
	                }catch (Exception e){
	                    e.getMessage();
	                }
	            }else{
	                logger.info("Token LOG 폴더 생성 되어있음.");
	            }
	            */
	            
	            //발급받은 트큰 기록
	            //BufferedWriter bw = new BufferedWriter(
	            //        new FileWriter(directoryPath + datestr, true));
	            //bw.write(timeNow+" "+ token + "\n");
	            //bw.flush();
	            //bw.close();
	            
	            
	            
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
        } finally {
        	if (connection != null) connection.disconnect();
        }
        
        logger.info("Token 값 : " + token);
        return token;
    }
    
    public String getRCSToken(String id, String apikey) {
    	String input = null;
        StringBuffer result = new StringBuffer();
        String token = null;
        URL url = null;
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String datestr = format.format(Calendar.getInstance().getTime());
        LocalTime now = LocalTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeNow = now.format(timeFormat);
        //datestr = format.format(new Date());
        String directoryPath = ".\\logs\\TokenLog.";
        Logger logger = LoggerFactory.getLogger(getClass());
        //File logPath = new File("D:\\My Project_GIT\\api\\logs\\Token");
        
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
				try {
					sc.init(null, trustAllCerts, new java.security.SecureRandom());
				} catch (KeyManagementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            
            //url = new URL("https://dev-api.bizppurio.com/v1/token");
            //url = new URL("https://dev-api.bizppurio.com:10443/v1/token");
            
            //*/
        	//--------------------------------------------------------------e

            //운영
            url = new URL("https://rapi.bizppurio.com/rcsapi/v1/token");
        	
                        
            /** Connection 설정 **/
            String request ="{";
            request += "\"bizId\": \""+ id + "\",";
            request += "\"apiKey\": \"" + apikey +"\"";
            request += "}";

            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Accept-Charset", "utf-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);
            /** Request **/                       
            OutputStream os = connection.getOutputStream();
            System.out.println(request);
            os.write(request.getBytes("UTF-8"));
            os.flush();
            os.close();

            /** Response **/
            if (connection.getResponseCode() == 200) {
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	            while ((input = in.readLine()) != null) {
	                result.append(input);
	            }
	            
	            System.out.println(result.toString());
	            
	            /* Get Token*/
	            String[] response = result.toString().split("\"");
	            token = response[9];
	            
	            /*
	            if(!logPath.exists()){
	                try{
	                    logPath.mkdirs();
	                    logger.info("Token LOG 폴더 생성 됨.");
	                }catch (Exception e){
	                    e.getMessage();
	                }
	            }else{
	                logger.info("Token LOG 폴더 생성 되어있음.");
	            }
	            */
	            
	            //발급받은 트큰 기록
	            //BufferedWriter bw = new BufferedWriter(
	            //        new FileWriter(directoryPath + datestr, true));
	            //bw.write(timeNow+" "+ token + "\n");
	            //bw.flush();
	            //bw.close();
	            
	            
	            
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
        } finally {
        	if (connection != null) connection.disconnect();
        }
        
        logger.info("Token 값 : " + token);
        return token;
    }    
}
