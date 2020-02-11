/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CMS.model;

/**
 *
 * @author user
 */
public class mKonten {
    private String kdkonten;
    private String tipekonten;
    private String userid;
    private String time;
    private String konten;
    private String picture;
    private int flag;
    
    public mKonten(){
        
    }

    public mKonten(String kdkonten, String tipekonten, String userid, String time, String konten, String picture, int flag) {
        this.kdkonten = kdkonten;
        this.tipekonten = tipekonten;
        this.userid = userid;
        this.time = time;
        this.konten = konten;
        this.picture = picture;
        this.flag = flag;
    }

    public String getKdkonten() {
        return kdkonten;
    }

    public void setKdkonten(String kdkonten) {
        this.kdkonten = kdkonten;
    }

    public String getTipekonten() {
        return tipekonten;
    }

    public void setTipekonten(String tipekonten) {
        this.tipekonten = tipekonten;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
    
    
}
