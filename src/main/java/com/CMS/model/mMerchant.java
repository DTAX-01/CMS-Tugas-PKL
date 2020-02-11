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
public class mMerchant {
    private String kdMerchant;
    private String kdBank;
    private String namaMerchant;
    private String namaPemilik;
    private String alamat;
    private String kelurahan;
    private String kecamatan;
    private String kota;
    private int kodePos;
    private int noHp;
    private int noTlpn;
    private String email;
    private int noRekening;
    private String namaRekening;
    private String pic;
    private String Stsrec;
    
    public mMerchant(){
        
    }

    public mMerchant(String kdMerchant, String kdBank, String namaMerchant, String namaPemilik, String alamat, String kelurahan, String kecamatan, String kota, int kodePos, int noHp, int noTlpn, String email, int noRekening, String namaRekening, String pic, String Stsrec) {
        this.kdMerchant = kdMerchant;
        this.kdBank = kdBank;
        this.namaMerchant = namaMerchant;
        this.namaPemilik = namaPemilik;
        this.alamat = alamat;
        this.kelurahan = kelurahan;
        this.kecamatan = kecamatan;
        this.kota = kota;
        this.kodePos = kodePos;
        this.noHp = noHp;
        this.noTlpn = noTlpn;
        this.email = email;
        this.noRekening = noRekening;
        this.namaRekening = namaRekening;
        this.pic = pic;
        this.Stsrec = Stsrec;
    }

    public String getKdMerchant() {
        return kdMerchant;
    }

    public void setKdMerchant(String kdMerchant) {
        this.kdMerchant = kdMerchant;
    }

    public String getKdBank() {
        return kdBank;
    }

    public void setKdBank(String kdBank) {
        this.kdBank = kdBank;
    }

    public String getNamaMerchant() {
        return namaMerchant;
    }

    public void setNamaMerchant(String namaMerchant) {
        this.namaMerchant = namaMerchant;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public int getKodePos() {
        return kodePos;
    }

    public void setKodePos(int kodePos) {
        this.kodePos = kodePos;
    }

    public int getNoHp() {
        return noHp;
    }

    public void setNoHp(int noHp) {
        this.noHp = noHp;
    }

    public int getNoTlpn() {
        return noTlpn;
    }

    public void setNoTlpn(int noTlpn) {
        this.noTlpn = noTlpn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(int noRekening) {
        this.noRekening = noRekening;
    }

    public String getNamaRekening() {
        return namaRekening;
    }

    public void setNamaRekening(String namaRekening) {
        this.namaRekening = namaRekening;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStsrec() {
        return Stsrec;
    }

    public void setStsrec(String Stsrec) {
        this.Stsrec = Stsrec;
    }


    
}
