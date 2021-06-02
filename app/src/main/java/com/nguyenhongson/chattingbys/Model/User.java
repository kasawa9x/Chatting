package com.nguyenhongson.chattingbys.Model;

public class User {
    String profileepic;
    String userName;
    String mail;
    String password;
    String userId;
    String lastMessage;
    public User(String profileepic, String userName, String mail, String password, String userId, String lastMessaga) {
        this.profileepic = profileepic;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastMessage = lastMessage;
    }
    public User(){

    }
    public User(String userName, String mail, String password) {

        this.userName = userName;
        this.mail = mail;
        this.password = password;

    }


    public String getProfileepic() {
        return profileepic;
    }

    public void setProfileepic(String profileepic) {
        this.profileepic = profileepic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessaga(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
