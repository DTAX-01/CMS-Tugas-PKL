/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CMS.repository;

import com.CMS.json.JSONObject;
import com.CMS.model.mKonten;
import com.CMS.model.mMerchant;
import com.CMS.model.mUser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Repository
public class RepositoryData {

    @Autowired
    JdbcTemplate jdbc;

    @Transactional

    public List<mUser> tampilDataUser() {
        List<mUser> model = jdbc.query("SELECT * FROM MGUSRBANK",
                new Object[]{}, new dataUserMapper());
        return model;
    }

    public long AddContent(JSONObject jsn) {
        return jdbc.update("INSERT INTO MGKONTEN(kdkonten, userid, time, tpkonten, konten, picture, flag) VALUES(?,?,?,?,?,?,?)",
                new Object[]{
                    jsn.get("kdkonten"),
                    jsn.get("userid"),
                    jsn.get("time"),
                    jsn.get("tpkonten"),
                    jsn.get("konten"),
                    jsn.get("picture"),
                    jsn.get("flag")
                });
    }

    public long AddMerchant(JSONObject jsn) {
        return jdbc.update("INSERT INTO TOFMCHN(kdmchn,nmmchn,kdbank,nmpemilik,alamat,kelurahan,kecamatan,kota,kdpos,pic,nohp,notelp,email,norek,nmnorek,stsrec) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                new Object[]{
                    jsn.get("kdmchn"),
                    jsn.get("nmMchn"),
                    jsn.get("kdbank"),
                    jsn.get("nmPmlk"),
                    jsn.get("almt"),
                    jsn.get("klrhn"),
                    jsn.get("kcmtn"),
                    jsn.get("kota"),
                    jsn.get("pos"),
                    jsn.get("pic"),
                    jsn.get("hp"),
                    jsn.get("tlp"),
                    jsn.get("mail"),
                    jsn.get("norek"),
                    jsn.get("nmrek"),
                    jsn.get("sts")
                });
    }
    
    public long EditContent(JSONObject jsn) {
        return jdbc.update("UPDATE MGKONTEN SET tpkonten = ?, userid = ?, konten = ?, picture = ?, flag = ? WHERE kdkonten = ?",
                new Object[]{
                    jsn.get("tpkonten"),
                    jsn.get("userid"),
                    jsn.get("konten"),
                    jsn.get("picture"),
                    jsn.get("flag"),
                    jsn.get("kdkonten")
                });
    }

    public long EditMerchant(JSONObject jsn) {
        return jdbc.update("UPDATE TOFMCHN SET nmmchn = ?, kdbank = ?, nmpemilik = ?, alamat = ?, kelurahan = ?, kecamatan = ?, kota = ?, kdpos = ?, pic = ?, nohp = ?, notelp = ?, email = ?, norek = ?, nmnorek = ?, stsrec = ? WHERE kdmchn=?",
                new Object[]{
                    jsn.get("nmMchn"),
                    jsn.get("kdbank"),
                    jsn.get("nmPmlk"),
                    jsn.get("almt"),
                    jsn.get("klrhn"),
                    jsn.get("kcmtn"),
                    jsn.get("kota"),
                    jsn.get("pos"),
                    jsn.get("pic"),
                    jsn.get("hp"),
                    jsn.get("tlp"),
                    jsn.get("mail"),
                    jsn.get("norek"),
                    jsn.get("nmrek"),
                    jsn.get("sts"),
                    jsn.get("kdmchn")
                });
    }

    public List<mKonten> tampilDataKonten(String id) {
        List<mKonten> model = jdbc.query("SELECT * FROM MGKONTEN WHERE flag <> '0' AND SUBSTRING(kdkonten, 0, 7) = ?",
                new Object[]{id}, new dataKontenMapper());
        return model;
    }
    
    public List<mKonten> tampilDataKontenAdmin() {
        List<mKonten> model = jdbc.query("SELECT * FROM MGKONTEN WHERE flag <> '0'",
                new Object[]{}, new dataKontenMapper());
        return model;
    }

    public List<mMerchant> tampilDataMerchant(String id) {
        List<mMerchant> model = jdbc.query("SELECT * FROM TOFMCHN WHERE stsrec <> 'Z' AND kdbank=?",
                new Object[]{id}, new dataMerchantMapper());
        return model;
    }

    public List<mMerchant> tampilDataMerchantAdmin() {
        List<mMerchant> model = jdbc.query("SELECT * FROM TOFMCHN WHERE stsrec <> 'Z'",
                new Object[]{}, new dataMerchantMapper());
        return model;
    }
    
    public mKonten tampilEditContent(String id) {
        List<mKonten> model = jdbc.query("SELECT * FROM MGKONTEN WHERE kdkonten=?",
                new Object[]{id}, new dataKontenMapper());
        if (model.isEmpty()) {
            return null;
        }
        return model.get(0);
    }

    public mMerchant tampilEditMerchant(String id) {
        List<mMerchant> model = jdbc.query("SELECT * FROM TOFMCHN WHERE kdmchn=?",
                new Object[]{id}, new dataMerchantMapper());
        if (model.isEmpty()) {
            return null;
        }
        return model.get(0);
    }

    public mKonten GetLastKdKonten(int kd) {
        List<mKonten> model = jdbc.query("SELECT * FROM MGKONTEN WHERE substring(kdkonten, 1, 6) = ? ORDER BY kdkonten DESC",
                new Object[]{kd}, new dataKontenMapper());
        if (model.isEmpty()) {
            return null;
        }
        return model.get(0);
    }

    public mMerchant GetLastKdMerchant(int kd) {
        List<mMerchant> model = jdbc.query("SELECT * FROM TOFMCHN WHERE substring(kdmchn, 1, 6) = ? ORDER BY kdmchn DESC",
                new Object[]{kd}, new dataMerchantMapper());
        if (model.isEmpty()) {
            return null;
        }
        return model.get(0);
    }

    public long delKdContentId(String id) {
        return jdbc.update("UPDATE MGKONTEN SET flag='0' WHERE kdkonten=?",
                new Object[]{id});
    }

    public long delKdMerchantId(String id) {
        return jdbc.update("UPDATE TOFMCHN SET stsrec='Z' WHERE kdmchn=?",
                new Object[]{id});
    }

    public long changeStatus(String[] str) {
        return jdbc.update("UPDATE TOFMCHN SET stsrec=? WHERE kdmchn=?",
                new Object[]{
                    str[0].toString(),
                    str[1].toString()
                });
    }

    public mUser prosesLogin(JSONObject jsn) {
        List<mUser> model = jdbc.query("SELECT * FROM MGUSRBANK WHERE kdbank=? AND userid=? AND pass=?",
                new Object[]{
                    jsn.get("bank"),
                    jsn.get("usr"),
                    jsn.get("pass")
                }, new dataUserMapper());
        if (model.isEmpty()) {
            return null;
        }
        return model.get(0);
    }

    private static final class dataUserMapper implements RowMapper<mUser> {

        @Override
        public mUser mapRow(ResultSet rs, int i) throws SQLException {
            mUser data = new mUser();
            data.setKdbank(rs.getString("kdbank"));
            data.setUserid(rs.getString("userid"));
            data.setPass(rs.getString("pass"));
            data.setAkses(rs.getInt("akses"));
            data.setRole(rs.getInt("role"));
            return data;
        }
    }

    private static final class dataKontenMapper implements RowMapper<mKonten> {

        @Override
        public mKonten mapRow(ResultSet rs, int i) throws SQLException {
            mKonten data = new mKonten();
            data.setKdkonten(rs.getString("kdkonten"));
            data.setTipekonten(rs.getString("tpkonten"));
            data.setUserid(rs.getString("userid"));
            data.setTime(rs.getString("time"));
            data.setKonten(rs.getString("konten"));
            data.setPicture(rs.getString("picture"));
            data.setFlag(rs.getInt("flag"));
            return data;
        }
    }

    private static final class dataMerchantMapper implements RowMapper<mMerchant> {

        @Override
        public mMerchant mapRow(ResultSet rs, int i) throws SQLException {
            mMerchant data = new mMerchant();
            data.setKdMerchant(rs.getString("kdmchn"));
            data.setKdBank(rs.getString("kdbank"));
            data.setNamaMerchant(rs.getString("nmmchn"));
            data.setNamaPemilik(rs.getString("nmpemilik"));
            data.setAlamat(rs.getString("alamat"));
            data.setKelurahan(rs.getString("kelurahan"));
            data.setKecamatan(rs.getString("kecamatan"));
            data.setKota(rs.getString("kota"));
            data.setKodePos(rs.getInt("kdpos"));
            data.setNoHp(rs.getInt("nohp"));
            data.setNoTlpn(rs.getInt("notelp"));
            data.setNoRekening(rs.getInt("norek"));
            data.setNamaRekening(rs.getString("nmnorek"));
            data.setPic(rs.getString("pic"));
            data.setStsrec(rs.getString("stsrec"));
            data.setEmail(rs.getString("email"));
            return data;
        }
    }

}
