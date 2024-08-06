package kr.co.daou.api.vo;

import lombok.Data;

@Data
public class MessageVO {
    public String account = "";
    public String password = "";
    private String type;
    private String from;
    private String to;
    private String country;
    private String refkey;
    private String userinfo;
    private String resend;
    private String recontent;
    private String message;
    //LMS, MMS
    private String subject;
    private String attach1;
    private String attach2;
    private String attach3;
    private String fileKey1;
    private String fileKey2;
    private String fileKey3;
    // AT/FT
    private String senderkey;
    private String templatecode;
    //button
    private String firstButton;
    private String secondButton;
    private String thirdButton;
    private String fourthButton;
    private String fifthButton;

    //알림톡 표기
    private String title;

    //알림톡 아이템리스트
    private String header;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String item5;
    private String item6;
    private String item7;
    private String item8;
    private String item9;
    private String item10;
    //아이템
    private String item;
    private String i_title;
    private String i_description;
    private String s_title;
    private String s_description;
    //아이템 하이라이트
    private String itemhighlight;
    private String ih_title;
    private String ih_description;
    private String ih_imageUrl;

    //이미지
    private String img_url;
    private String img_link;

}