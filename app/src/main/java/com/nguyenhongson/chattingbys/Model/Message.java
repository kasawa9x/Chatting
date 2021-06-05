package com.nguyenhongson.chattingbys.Model;

public class Message {
    String uId, message, messageId, imPro;

    Long timestamp;

    public Message(String uId, String message, Long timestamp, String imPro) {
        this.uId = uId;
        this.message = message;
        this.timestamp = timestamp;
        this.imPro = imPro;
    }

    public Message(String uId, String message,String imPro) {
        this.uId = uId;
        this.message = message;
        this.imPro = imPro;
    }
    public Message(String uId, String message) {
        this.uId = uId;
        this.message = message;

    }

    public Message() {
    }

    public String getImPro() {
        return imPro;
    }

    public void setImPro(String imPro) {
        this.imPro = imPro;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
