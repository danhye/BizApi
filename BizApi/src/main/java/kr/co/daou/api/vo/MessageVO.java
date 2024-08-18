package kr.co.daou.api.vo;

import lombok.Data;
import java.util.List;


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
    //버튼 리스트
    private List<ButtonVO> buttons;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRefkey() {
        return refkey;
    }

    public void setRefkey(String refkey) {
        this.refkey = refkey;
    }

    public String getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }

    public String getResend() {
        return resend;
    }

    public void setResend(String resend) {
        this.resend = resend;
    }

    public String getRecontent() {
        return recontent;
    }

    public void setRecontent(String recontent) {
        this.recontent = recontent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttach1() {
        return attach1;
    }

    public void setAttach1(String attach1) {
        this.attach1 = attach1;
    }

    public String getAttach2() {
        return attach2;
    }

    public void setAttach2(String attach2) {
        this.attach2 = attach2;
    }

    public String getAttach3() {
        return attach3;
    }

    public void setAttach3(String attach3) {
        this.attach3 = attach3;
    }

    public String getFileKey1() {
        return fileKey1;
    }

    public void setFileKey1(String fileKey1) {
        this.fileKey1 = fileKey1;
    }

    public String getFileKey2() {
        return fileKey2;
    }

    public void setFileKey2(String fileKey2) {
        this.fileKey2 = fileKey2;
    }

    public String getFileKey3() {
        return fileKey3;
    }

    public void setFileKey3(String fileKey3) {
        this.fileKey3 = fileKey3;
    }

    public String getSenderkey() {
        return senderkey;
    }

    public void setSenderkey(String senderkey) {
        this.senderkey = senderkey;
    }

    public String getTemplatecode() {
        return templatecode;
    }

    public void setTemplatecode(String templatecode) {
        this.templatecode = templatecode;
    }

    public List<ButtonVO> getButtons() {
        return buttons;
    }

    public void setButtons(List<ButtonVO> buttons) {
        this.buttons = buttons;
    }
}