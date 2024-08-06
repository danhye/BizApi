package kr.co.daou.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.co.daou.api.service.MultiMedeaUpload;
import kr.co.daou.api.service.RequestService;
import kr.co.daou.api.service.SendMessageAPI;
import kr.co.daou.api.vo.MessageVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class BizppurioMessageAPI {    
    @PostMapping(value = "/sendFile")
    @ApiOperation(value = "첨부파일 업로드API", notes = "MMS 발송을 위한 첨부파일 업로드 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공 코드이나, Response 를 확인해 주세요."),
            @ApiResponse(code = 400, message = "Request 오류 파라미터 값을 확인하세요...")
    })	
    public String sendFile(
    		 @ApiParam(value = "비즈뿌리오 계정", required = true)@RequestParam String a_id,
            @ApiParam(value = "첨부 이미지 절대 경로로 입력.", required = true)@RequestParam(defaultValue = "C:\\Users\\daou\\Pictures\\") String b_attach
    ){
    	String strResult = "";
    	
    	MultiMedeaUpload mmUpload = new MultiMedeaUpload();
    	
    	mmUpload.id = a_id;
    	
        if (!b_attach.isEmpty() || !b_attach.equals("null")) {
        	mmUpload.upload(b_attach);
        	
        	strResult = mmUpload.uploadKey;
        }
        else
        	strResult = "b_attach is empty or \"null\".";
            
        return strResult;
    }	
    @PostMapping(value = "/sendMessage")
    @ApiOperation(value = "※Jason전문 발송 API", notes = "Jason전문을 그대로 발송하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공 코드이나, Response 를 확인해 주세요."),
            @ApiResponse(code = 400, message = "Request 오류 파라미터 값을 확인하세요...")
    })    
    public String sendMessage(
    		@ApiParam(value = "비즈뿌리오 계정", required = true)@RequestParam String a_id,
    		@ApiParam(value = "비즈뿌리오 비밀번호", required = true)@RequestParam String b_passwd,
            @ApiParam(value = "메세지 Jason타입", required = true)@RequestBody String c_message
    ){
        SendMessageAPI send = new SendMessageAPI();
        
        send.id = a_id;
        send.passwd = b_passwd;
               
        return send.sendMessage(c_message);
    }    
}


/*
SMS
---------------------------------------------------------------

{
	"account": "test12",
	"refkey": "test_1",
	"type": "sms",
	"from": "07087071402",
	"to": "01036794020",
	"content": {
	"sms": {
		"message": "SMS 전송\n111111\n22222\n33333333"
	           }
	}
}
---------------------------------------------------------------


LMS
---------------------------------------------------------------
{
	"account": "test12",
	"refkey": "test_3",
	"type": "lms",
	"from": "07087071402",
	"to": "01036794020",
	"content": {
	"lms": {
	      "subject": "제목",
	      "message": "LMS 전송"
	    }
	}
}
---------------------------------------------------------------



MMS
---------------------------------------------------------------
{
	"account": "test12",
	"refkey": "test_2",
	"type": "mms",
	"from": "07087071402",
	"to": "01036794020",
	"content": {
		"mms": {
		"subject": "제목",
		"message": "MMS 전송",
		"file": [
				{
				"type": "IMG",
				"key": "1665447329_BD4684854732900005289.jpg"
				}
			]
		 }
	}
}
---------------------------------------------------------------

알림톡
---------------------------------------------------------------
{
	"account": "test12",
	"refkey": "test_1",
	"type": "at",
	"from": "07087071402",
	"to": "01036794020",
	"content": {
	"at": {
                "senderkey":"68ef2051358803b904049fd19534c921441b0173",
                "templatecode":"bizp_2022082910473012115638477",
                "message": "[#{병원} 진료예약 취소 안내]\n\n▣ #{이름}님 (등록번호 : #{차트번호}) \n    진료예약 취소 되었습니다.\n    1. 진료예약일자 : #{날짜} #{시간}\n    2. 진료과 : #{진료과}\n    3. 담당 진료과장 : #{의사명}\n\n▣ 예약 관련 콜센터 연락처 안내 : #{콜센터번호}",
                "button": [
        		{
		"name": "오시는 길",
                        "type": "WL",
                        "url_pc": "http://106.248.126.61/hyosung/aboutus.cfm#contact",
                        "url_mobile": "http://106.248.126.61/hyosung/aboutus.cfm#contact"
                        },
                       {
                       "name": "주차장 안내",
                       "type": "WL",
                       "url_pc": "https://www.youtube.com/watch?v=lbcqASE-Eto",
                       "url_mobile": "https://www.youtube.com/watch?v=lbcqASE-Eto"
                       }
                ]
            } 
        }
}


----------------------------------------------------------------
알림톡 대체발송
---------------------------------------------------------------
{
	"account": "test12",
	"refkey": "test_1",
	"type": "at",
	"from": "07087071402",
	"to": "01036794020",
	"content": {
	"at": {
                "senderkey":"68ef2051358803b904049fd19534c921441b0173",
                "templatecode":"12321313",
                "message": "[#{병원} 진료예약 취소 안내]\n\n▣ #{이름}님 (등록번호 : #{차트번호}) \n    진료예약 취소 되었습니다.\n    1. 진료예약일자 : #{날짜} #{시간}\n    2. 진료과 : #{진료과}\n    3. 담당 진료과장 : #{의사명}\n\n▣ 예약 관련 콜센터 연락처 안내 : #{콜센터번호}",
                "button": [
        		{
		"name": "오시는 길",
                        "type": "WL",
                        "url_pc": "http://106.248.126.61/hyosung/aboutus.cfm#contact",
                        "url_mobile": "http://106.248.126.61/hyosung/aboutus.cfm#contact"
                        },
                       {
                       "name": "주차장 안내",
                       "type": "WL",
                       "url_pc": "https://www.youtube.com/watch?v=lbcqASE-Eto",
                       "url_mobile": "https://www.youtube.com/watch?v=lbcqASE-Eto"
                       }
                ]
            } 
    },
	"resend": {
	 "first": "mms"
	},
	"recontent": {
	 "mms": {
	 "message": "LMS 대체 발송"
	 }
	}       
}



---------------------------------------------------------------

RCS SMS일반
---------------------------------------------------------------
{
"account": "test12",
"refkey": "test_2",
"type": "rcs",
"from": "15999782",
"to": "01034134563",
"content": {
    "rcs": {
      "messagebaseid": "SS000000",
      "chatbotid": "15999782",
      "header": "0",
      "footer": "",
      "copyallowed": "Y",
      "message": {
        "description": "안녕하세요! - RCS SMS"
      }
    }
  }
 }
---------------------------------------------------------------

RCS 버튼샘플
---------------------------------------------------------------
{
   "account":"test12",
   "refkey":"test_5",
   "type":"rcs",
   "from":"15885245",
   "to":"01052592470",
   "content":{
      "rcs":{
         "messagebaseid":"SL000000",
         "chatbotid":"15885245",
         "header":"0",
         "copyallowed":"Y",
         "message":{
            "description":"* 결제비밀번호 12345\n\n사용처 결제창 비밀번호 입력란에 결제비밀번호를 입력해 주세요!\n\n결제비밀번호는 1시간 유효합니다. \n시간 경과 시  재확인 해주세요."
         },
         "button":[
            {
               "suggestions":[
                  {
                     "action":{
                        "clipboardAction":{
                           "copyToClipboard":{
                              "text":"123456"
                           }
                        },
                        "displayText":"복사하기"
                     }
                  }
               ]
            }
         ]
      }
   }
}
*/