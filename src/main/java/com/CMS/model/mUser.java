/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CMS.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author user
 */
public class mUser {
    
    private String kdbank;
    private String userid;
    private String pass;
    private int akses;
    private int role;
    
    public mUser(){
        
    }

    public mUser(String kdbank, String userid, String pass, int akses, int role) {
        this.kdbank = kdbank;
        this.userid = userid;
        this.pass = pass;
        this.akses = akses;
        this.role = role;
    }

    public String getKdbank() {
        return kdbank;
    }

    public void setKdbank(String kdbank) {
        this.kdbank = kdbank;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getAkses() {
        return akses;
    }

    public void setAkses(int akses) {
        this.akses = akses;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    
    
}
