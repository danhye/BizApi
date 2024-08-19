package kr.co.daou.api.controller;

import io.swagger.annotations.*;
import kr.co.daou.api.service.RequestService;
import kr.co.daou.api.service.SendMessageAPI;
import kr.co.daou.api.vo.ButtonVO;
import kr.co.daou.api.vo.MessageVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BizppurioKakaoAtAPI {

    @PostMapping(value = "/sendAlimtalk")
    @ApiOperation(value = "알림톡, 알림톡 이미지 발송 API", notes = "알림톡, 이미지 알림톡 발송을 하기 위한 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Request 성공이지만 Response 가 있는지 확인하세요."),
            @ApiResponse(code = 403, message = "접근 거부"),
            @ApiResponse(code = 400, message = "Request 오류 파라미터 값 확인")
    })
    public String sendAlimtalk(@ApiParam(value = "알림톡 타입 at or ai", required = true) @RequestParam String a_type,
                               @ApiParam(value = "직접 부여하는 키 값", required = true) @RequestParam String b_refkey,
                               @ApiParam(value = "수신 번호", required = true) @RequestParam String c_to,
                               @ApiParam(value = "발신 번호", required = true) @RequestParam String d_from,
                               @ApiParam(value = "메세지 내용", required = true) @RequestParam String e_message,
                               @ApiParam(value = "카카오 발신 프로필 키", required = true) @RequestParam String f_senderkey,
                               @ApiParam(value = "템플릿 코드", required = true) @RequestParam String g_templatecode,
                               @ApiParam(value = "버튼 이름", required = false) @RequestParam String h_button_type,
                               @ApiParam(value = "버튼 타입", required = false) @RequestParam String i_button_name,
                               @ApiParam(value = "url_pc (WL 타입일 경우 필수)", required = false) @RequestParam(required = false) String j_url_pc,
                               @ApiParam(value = "url_mobile (WL 타입일 경우 필수)", required = false) @RequestParam(required = false) String k_url_mobile,
                               @ApiParam(value = "scheme_android (AL 타입일 경우 필수)", required = false) @RequestParam(required = false) String l_scheme_android,
                               @ApiParam(value = "scheme_ios (AL 타입일 경우 필수)", required = false) @RequestParam(required = false) String m_scheme_ios
                               //@ApiParam(value = "추가적인 상담톡 정보 (BC, BT 타입일 경우 필수)", required = false) @RequestParam(required = false) String chat_extra,
                               //@ApiParam(value = "봇 전환 이벤트 정보 (BT 타입일 경우 필수)", required = false) @RequestParam(required = false) String chat_event
    ) {
        MessageVO vo = new MessageVO();
        RequestService rq = new RequestService();
        SendMessageAPI send = new SendMessageAPI();
        vo.setType(a_type);
        vo.setRefkey(b_refkey);
        vo.setTo(c_to);
        vo.setFrom(d_from);
        vo.setMessage(e_message);
        vo.setSenderkey(f_senderkey);
        vo.setTemplatecode(g_templatecode);

        ButtonVO buttonVO = new ButtonVO();
        buttonVO.setName(i_button_name);
        buttonVO.setType(h_button_type);

        switch (h_button_type) {
            case "WL":
                buttonVO.setUrl_pc(j_url_pc);
                buttonVO.setUrl_mobile(k_url_mobile);
                break;
            case "AL":
                buttonVO.setScheme_android(l_scheme_android);
                buttonVO.setScheme_ios(m_scheme_ios);
                break;
        }

        // 단일 버튼을 리스트에 추가
        vo.setButtons(List.of(buttonVO));

        return send.sendMessage(rq.makeAtRequest(vo));
    }
    }

    /*
    @PostMapping(value = "/sendAlimtalkItem")
    @ApiOperation(value = "알림톡 아리템 LIST 발송 API", notes = "알림톡 ITEM LIST 발송을 하기 위한 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Request 성공이지만 Response 가 있는지 확인하세요."),
            @ApiResponse(code = 403, message = "접근 거부"),
            @ApiResponse(code = 400, message = "Request 오류 파라미터 값 확인")
    })
    public String sendAlimtalkItem(@ApiParam(value = "알림톡 아이템리스트 타입", required = true) @RequestParam String a_type,
                                   @ApiParam(value = "직접 부여하는 키 값", required = true) @RequestParam String b_refkey,
                                   @ApiParam(value = "수신자 번호", required = true) @RequestParam String c_to,
                                   @ApiParam(value = "메세지 내용", required = true) @RequestParam String d_message,
                                   @ApiParam(value = "카카오 발신 프로필 키", required = true) @RequestParam String e_senderkey,
                                   @ApiParam(value = "템플릿 코드", required = true) @RequestParam String f_templatecode,
                                   @ApiParam(value = "버튼 1", required = true) @RequestParam(defaultValue = "null") String g_button1,
                                   @ApiParam(value = "버튼 2", required = true) @RequestParam(defaultValue = "null") String h_button2,
                                   @ApiParam(value = "버튼 3", required = true) @RequestParam(defaultValue = "null") String i_button3,
                                   @ApiParam(value = "버튼 4", required = true) @RequestParam(defaultValue = "null") String j_button4,
                                   @ApiParam(value = "버튼 5", required = true) @RequestParam(defaultValue = "null") String k_button5,
                                   @ApiParam(value = "아이템 List 1", required = true) @RequestParam(defaultValue = "null") String l_list1,
                                   @ApiParam(value = "아이템 List 2", required = true) @RequestParam(defaultValue = "null") String m_list2,
                                   @ApiParam(value = "아이템 List 3", required = true) @RequestParam(defaultValue = "null") String n_list3,
                                   @ApiParam(value = "아이템 List 4", required = true) @RequestParam(defaultValue = "null") String o_list4,
                                   @ApiParam(value = "아이템 List 5", required = true) @RequestParam(defaultValue = "null") String p_list5,
                                   @ApiParam(value = "아이템 List 6", required = true) @RequestParam(defaultValue = "null") String q_list6,
                                   @ApiParam(value = "아이템 List 7", required = true) @RequestParam(defaultValue = "null") String r_list7,
                                   @ApiParam(value = "아이템 List 8", required = true) @RequestParam(defaultValue = "null") String s_list8,
                                   @ApiParam(value = "아이템 List 9", required = true) @RequestParam(defaultValue = "null") String t_list9,
                                   @ApiParam(value = "아이템 List 10",required = true) @RequestParam(defaultValue = "null") String u_list10
                                   ) {
        MessageVO vo = new MessageVO();
        RequestService rq = new RequestService();
        SendMessageAPI send = new SendMessageAPI();
        vo.setType(a_type);
        vo.setRefkey(b_refkey);
        vo.setTo(c_to);
        vo.setMessage(d_message);
        vo.setSenderkey(e_senderkey);
        vo.setTemplatecode(f_templatecode);
        vo.setFirstButton(g_button1);
        vo.setSecondButton(h_button2);
        vo.setThirdButton(i_button3);
        vo.setFourthButton(j_button4);
        vo.setFifthButton(k_button5);
        return "";
        //return send.sendAllMessage(rq.makeAtRequest(vo));
    }
*/
