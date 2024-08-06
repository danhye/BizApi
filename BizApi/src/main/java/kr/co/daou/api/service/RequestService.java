package kr.co.daou.api.service;

import kr.co.daou.api.vo.MessageVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class RequestService {
    String request = null;
    StringBuffer sb = new StringBuffer();
    JSONObject requiredObject = new JSONObject();
    Logger logger = LoggerFactory.getLogger(getClass());

    public String makeMessageRequest(MessageVO vo) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String datestr = format.format(Calendar.getInstance().getTime());
        datestr = format.format(new Date());
        String directoryPath = ".\\logs\\SendRequestLog.";
        //File logPath = new File("D:\\My Project_GIT\\api\\logs\\RequestLog");
        LocalTime now = LocalTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeNow = now.format(timeFormat);
        JSONObject fileObject = new JSONObject();
        JSONObject fileObject2 = new JSONObject();
        JSONObject fileObject3 = new JSONObject();
        JSONArray fileArray = new JSONArray();
        requiredObject.put("account", vo.getAccount());
        requiredObject.put("refkey", vo.getRefkey());
        requiredObject.put("type", vo.getType());
        requiredObject.put("from", vo.getFrom());
        requiredObject.put("to", vo.getTo());
        if (vo.getType().equals("sms")) {
            sb.append("{\"" + vo.getType() + "\":{\"message\":\"" + vo.getMessage() + "\"}}");
            requiredObject.put("content", sb);
            request = requiredObject.toJSONString();
        } else if (vo.getType().equals("lms")) {
            sb.append("{\"" + vo.getType() + "\":{\"subject\":\"" + vo.getSubject() + "\", \"message\":\"" + vo.getMessage() + "\"}}");
            requiredObject.put("content", sb);
            request = requiredObject.toJSONString();
        } else if (vo.getType().equals("mms")) {
            if(vo.getSubject().equals("null")){
                vo.setSubject("");
            }
            MultiMedeaUpload mmUpload = new MultiMedeaUpload();
            if (!vo.getAttach1().equals("null")) {
                mmUpload.upload(vo.getAttach1());
                vo.setFileKey1(mmUpload.uploadKey);
                fileObject.put("type", "IMG");
                fileObject.put("key", vo.getFileKey1());
                fileArray.add(fileObject);
                if (!vo.getAttach2().equals("null")) {
                    mmUpload.upload(vo.getAttach2());
                    vo.setFileKey2(mmUpload.uploadKey);
                    fileObject2.put("type", "IMG");
                    fileObject2.put("key", vo.getFileKey2());
                    fileArray.add(fileObject2);
                    if (!vo.getAttach3().equals("null")) {
                        mmUpload.upload(vo.getAttach3());
                        vo.setFileKey3(mmUpload.uploadKey);
                        fileObject3.put("type", "IMG");
                        fileObject3.put("key", vo.getFileKey3());
                        fileArray.add(fileObject3);
                    }
                }
            } else {
                logger.info("첨부된 파일이 없어 실패되었습니다.");
            }
            sb.append("{\"" + vo.getType() + "\":{\"subject\":\"" + vo.getSubject() + "\", \"message\":\"" + vo.getMessage() + "\", \"file\": " + fileArray.toJSONString() + "}}");
            requiredObject.put("content", sb);
            request = requiredObject.toJSONString();
        }
        
        try {
        	
        	/*
            if (!logPath.exists()) {
                try {
                    logPath.mkdirs();
                    logger.info("Request LOG 폴더 생성 됨.");
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                logger.info("Request LOG 폴더 생성 되어있음.");
            }*/
        	
            //BufferedWriter bw = new BufferedWriter(
            //        new FileWriter(directoryPath + datestr, true));
            //bw.write("\n" + timeNow + " " + request + "\n");
            //bw.flush();
            //bw.close();
        	
            logger.info("request 전문 내용 : " + request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    public String makeAtRequest(MessageVO vo) {
        JSONArray buttonArray = new JSONArray();
        requiredObject.put("account", vo.getAccount());
        requiredObject.put("type", vo.getType());
        requiredObject.put("refkey", vo.getRefkey());
        requiredObject.put("from", vo.getFrom());
        requiredObject.put("to", vo.getTo());

        if(!vo.getFirstButton().equals("null")){
            buttonArray.add(vo.getFirstButton());
            if(!vo.getSecondButton().equals("null")){
                buttonArray.add(vo.getSecondButton());
                if(!vo.getThirdButton().equals("null")){
                    buttonArray.add(vo.getThirdButton());
                    if(!vo.getFourthButton().equals("null")){
                        buttonArray.add(vo.getFourthButton());
                        if(!vo.getFifthButton().equals("null")){
                            buttonArray.add(vo.getFifthButton());
                        }
                    }
                }
            }
            sb.append("{\"" + vo.getType() + "\":{\"senderkey\":\"" + vo.getSenderkey() + "\", \"templatecode\":\"" + vo.getTemplatecode() + "\", \"message\":\"" + vo.getMessage() + "\", \"button\": " + buttonArray.toJSONString() + "}}");
        }else{
            sb.append("{\"" + vo.getType() + "\":{\"senderkey\":\"" + vo.getSenderkey() + "\", \"templatecode\":\"" + vo.getTemplatecode() + "\", \"message\":\"" + vo.getMessage() + "\"}}");
        }
        requiredObject.put("content", sb);
        request = requiredObject.toJSONString();
        logger.info("Request : " + request);
        return request;
    }
}