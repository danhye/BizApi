package kr.co.daou.api.service;

import kr.co.daou.api.vo.MessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class MultiMedeaUpload {
    private final static String boundary = "1234";
    private final static String LINE_FEED = "\r\n";
    private final static String charset = "utf-8";
    Logger logger = LoggerFactory.getLogger(getClass());
    public MessageVO vo = new MessageVO();
    public String uploadKey = null;
    
    public String id = "";

    public void upload(String attach) {
    	
    	if(!id.isEmpty()) {
        	vo.setAccount(id);
        }
    	
        OutputStream outputStream = null;
        PrintWriter writer = null;
        if (attach != null) {
            try {
            	//--------------------------------------------------------------s
                // SSL 인증서 무시 : 비즈뿌리오 API 운영을 접속하는 경우 해당 코드 필요 없음
            	//--------------------------------------------------------------s
            	//*
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
                
                //URL url = new URL("https://dev-api.bizppurio.com/v1/file");
                URL url = new URL("https://dev-api.bizppurio.com:10443/v1/file");
                //*/
            	//--------------------------------------------------------------e
            	
                File file = new File(attach);
                /* 운영URL */
                //URL url = new URL("https://api.bizppurio.com/v1/file");
                
                	
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Content-Type",
                        "multipart/form-data;charset=" + charset + ";boundary=" + boundary);
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);
                connection.setConnectTimeout(10000);

                outputStream = connection.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
                /* Account */
                addTextPart(writer, "account", vo.getAccount());
                /* File */
                addFilePart(writer, outputStream, "file", file);
                writer.append("--" + boundary + "--").append(LINE_FEED);
                writer.close();
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                    readResponse(new BufferedReader(new InputStreamReader(connection.getInputStream())));
                } else {
                    readResponse(new BufferedReader(new InputStreamReader(connection.getErrorStream())));
                }
            } catch (ConnectException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (outputStream != null)
                        outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (writer != null)
                    writer.close();
            }
        } else if (attach == null) {
            System.out.println("LMS 발송을 이용하세요");
        }
    }

    private void addTextPart(PrintWriter writer, String name, String value) throws IOException {
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append(LINE_FEED);
        writer.append("Content-Type: text/plain; charset=" + charset).append(LINE_FEED);
        writer.append("Content-Transfer-Encoding: 8bit").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.append(value).append(LINE_FEED);
        writer.flush();
    }

    private void addFilePart(PrintWriter writer, OutputStream outputStream, String name, File file) throws IOException {
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + file.getName() + "\"")
                .append(LINE_FEED);
        writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName())).append(LINE_FEED);
        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();

        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[(int) file.length()];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            writer.append(LINE_FEED);
            writer.flush();
        }
    }

    private String readResponse(BufferedReader in) throws IOException {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String datestr = format.format(Calendar.getInstance().getTime());
        datestr = format.format(new Date());
        LocalTime now = LocalTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeNow = now.format(timeFormat);
        String directoryPath = ".\\logs\\FileUploadKeyLog.";
        //File logPath = new File("C:\\Users\\DAOU\\Desktop\\API_LOG\\FileKey");
        MessageVO vo = new MessageVO();
        StringBuffer response = new StringBuffer();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        /* Response */
        String[] responseData = response.toString().split("\"");
        String filekey = responseData[3];
        logger.info("Response : " + response.toString());
        String[] key = response.toString().split("\"");
        uploadKey = key[3];
       
        /*
        if (!logPath.exists()) {
            try {
                logPath.mkdirs();
                logger.info("File Upload LOG 폴더 생성 됨.");
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            logger.info("File Upload LOG 폴더 생성 되어있음.");
        }*/
        
        //BufferedWriter bw = new BufferedWriter(
        //        new FileWriter(directoryPath + datestr, true));
        //bw.write("\n" + timeNow + " " + uploadKey + "\n");
        //bw.flush();
        //bw.close();
        logger.info(uploadKey + "\n");
        return "\n" + uploadKey + "\n";
    }
}