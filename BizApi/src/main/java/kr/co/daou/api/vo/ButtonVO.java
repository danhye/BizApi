package kr.co.daou.api.vo;


public class ButtonVO {
    private String name;
    private String type;
    private String url_pc;
    private String url_mobile;
    private String scheme_ios;
    private String scheme_android;
    private String chat_extra;
    private String chat_event;
    private String plugin_id;
    private String relay_id;
    private String oneclick_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl_pc() {
        return url_pc;
    }

    public void setUrl_pc(String url_pc) {
        this.url_pc = url_pc;
    }

    public String getUrl_mobile() {
        return url_mobile;
    }

    public void setUrl_mobile(String url_mobile) {
        this.url_mobile = url_mobile;
    }

    public String getScheme_ios() {
        return scheme_ios;
    }

    public void setScheme_ios(String scheme_ios) {
        this.scheme_ios = scheme_ios;
    }

    public String getScheme_android() {
        return scheme_android;
    }

    public void setScheme_android(String scheme_android) {
        this.scheme_android = scheme_android;
    }

    public String getChat_extra() {
        return chat_extra;
    }

    public void setChat_extra(String chat_extra) {
        this.chat_extra = chat_extra;
    }

    public String getChat_event() {
        return chat_event;
    }

    public void setChat_event(String chat_event) {
        this.chat_event = chat_event;
    }

    public String getPlugin_id() {
        return plugin_id;
    }

    public void setPlugin_id(String plugin_id) {
        this.plugin_id = plugin_id;
    }

    public String getRelay_id() {
        return relay_id;
    }

    public void setRelay_id(String relay_id) {
        this.relay_id = relay_id;
    }

    public String getOneclick_id() {
        return oneclick_id;
    }

    public void setOneclick_id(String oneclick_id) {
        this.oneclick_id = oneclick_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    private String product_id;

}