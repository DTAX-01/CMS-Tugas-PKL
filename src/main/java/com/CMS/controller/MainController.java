/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CMS.controller;

import com.CMS.model.mKonten;
import com.CMS.model.mMerchant;
import com.CMS.repository.RepositoryData;
import com.CMS.Lib;
import com.CMS.json.JSONObject;
import com.CMS.model.mUser;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author user
 */
@Controller
public class MainController {

    @Autowired
    RepositoryData repo;

    public static boolean cekSession(Model m, HttpServletRequest request, String isi) {
        HttpSession session = request.getSession(true);
        session.setAttribute("user", isi);
        String status = (String) session.getAttribute("user");
//        String[] usr = isi.split("_");
        if (status != null) {
            m.addAttribute("status", isi);
            return true;
        }
        return false;
    }
    private String sts = null;

    public void statusLog(String sts) {
        this.sts = sts;
    }

    public String stsLog() {
        return sts;
    }

    public Lib lib = new Lib();

    public String getLastKodeKonten(int kode) {
        String awal = "00000";
        mKonten dt = repo.GetLastKdKonten(kode);
        if (dt == null) {
            return kode + "" + awal;
        }
        return dt.getKdkonten();
    }

    public String getLastKodeMerchant(int kode) {
        String awal = "90000";
        mMerchant dt = repo.GetLastKdMerchant(kode);
        if (dt == null) {
            return kode + "" + awal;
        }
        return dt.getKdMerchant();
    }

//    PERBATASAN WILAYAH \\
    @GetMapping("/")
    public String tampilLogin(Model m, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) != false) {
            m.addAttribute("data", status);
            return "redirect:dashboard";
        }

        return "frm_cms_login";
    }

    @GetMapping("/dashboard")
    public String tampilDashboard(Model m, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }
//        String[] nm = stsLog().split("_");
        m.addAttribute("coba", cekSession(m, request, status));
        return "frm_cms_beranda";
    }

    @GetMapping("/merchant")
    public String tampilMerchant(Model m, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }

        return "frm_cms_merchant";
    }

    @GetMapping("/content")
    public String tampilContent(Model m, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }
        m.addAttribute("coba", repo.tampilDataUser());
        return "frm_cms_content";
    }

    @GetMapping("/getTblContent")
    public String showTableContent(Model m, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }
        String[] sts = status.split("_");
        if(lib.cmpStr(sts[0], "admin") && lib.cmpStr(sts[1], "000001")){
            m.addAttribute("data", repo.tampilDataKontenAdmin());
        }else{
            m.addAttribute("data", repo.tampilDataKonten(sts[1]));
        }
        return "tbl_content";
    }

    @GetMapping("/getTblMerchant")
    public String showTableMerchant(Model m, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }
        
        String[] sts = status.split("_");
        if(lib.cmpStr(sts[0], "admin") && lib.cmpStr(sts[1], "000001")){
            m.addAttribute("data", repo.tampilDataMerchantAdmin());
        }else{
            m.addAttribute("data", repo.tampilDataMerchant(sts[1]));
        }
        
        return "tbl_merchant";
    }

    @PostMapping("/delContentId")
    public String delRowContent(Model m, HttpServletRequest request, @RequestBody String request_data) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }
        String[] sts = status.split("_");
        Map<String, String> dt = lib.parseHttp(request_data);
        repo.delKdContentId(dt.get("kode"));

        if(lib.cmpStr(sts[0], "admin") && lib.cmpStr(sts[1], "000001")){
            m.addAttribute("data", repo.tampilDataKontenAdmin());
        }else{
            m.addAttribute("data", repo.tampilDataKonten(sts[1]));
        }
        return "tbl_content";
    }

    @PostMapping("/delMerchantId")
    public String delRowMerchant(Model m, HttpServletRequest request, @RequestBody String request_data) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }
        String[] sts = status.split("_");
        Map<String, String> dt = lib.parseHttp(request_data);
        repo.delKdMerchantId(dt.get("kode"));

        if(lib.cmpStr(sts[0], "admin") && lib.cmpStr(sts[1], "000001")){
            m.addAttribute("data", repo.tampilDataMerchantAdmin());
        }else{
            m.addAttribute("data", repo.tampilDataMerchant(sts[1]));
        }
        
        return "tbl_merchant";
    }

    @PostMapping("/frmAddContent")
    public String sendDataContent(Model m, HttpServletRequest request, @ModelAttribute mKonten dForm, BindingResult bindingResult, @RequestParam Map<String, MultipartFile> files) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }
        String[] usr = status.split("_");
        String gbr = (lib.cmpStr(files.get("gbr").getOriginalFilename(), "") == true) ? dForm.getPicture() : files.get("gbr").getOriginalFilename();
        String path = "\\src\\main\\resources\\static\\dist\\img";
        String pattern = "ddMMyyyy HHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        int kdbank = Integer.parseInt(usr[1]);
        long kdKonten = Long.parseLong(getLastKodeKonten(kdbank)) + 1;

        try {
            String var = files.get("gbr").getOriginalFilename();
            byte[] var1 = files.get("gbr").getBytes();
            if (files.get("gbr").getOriginalFilename() != null || files.get("gbr").getOriginalFilename() == "") {
                lib.writeFile(var, var1, path);
            }
        } catch (IOException e) {
            System.out.println(String.format("Warning: cannot writefile %s", files.get("gbr").getOriginalFilename()));
        }

        JSONObject jsn = new JSONObject();
        jsn.put("kdkonten", kdKonten);
        jsn.put("userid", usr[0]);
        jsn.put("time", date.substring(0, 8) + date.substring(9, 13));
        jsn.put("tpkonten", (dForm.getTipekonten() == null) ? "" : dForm.getTipekonten());
        jsn.put("konten", (dForm.getKonten() == null) ? "" : dForm.getKonten());
        jsn.put("picture", gbr);
        jsn.put("flag", "1");

//        m.addAttribute("data", jsn);
        repo.AddContent(jsn);

//        m.addAttribute("data", date.substring(0, 8)+ date.substring(9, 13) + "| "+kdKonten+" |"+ kdbank+ usr+dForm.getTipekonten() + "" + dForm.getKonten() + " |" + files.get("picture").getOriginalFilename());
        if (bindingResult.hasErrors()) {
            return "redirect:content";
        }
        return "redirect:content";
    }

    @PostMapping("/frmAddMerchant")
    public String sendDataMerchant(Model m, HttpServletRequest request, @ModelAttribute mMerchant dForm, BindingResult bindingResult, @RequestParam Map<String, MultipartFile> files) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }
        String[] usr = status.split("_");
        String path = "\\src\\main\\resources\\static\\dist\\img";
//        String pattern = "ddMMyyyy HHmmss";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        String date = simpleDateFormat.format(new Date());
        int kdbank = Integer.parseInt(usr[1]);
        long kdMch = Long.parseLong(getLastKodeMerchant(kdbank)) + 1;

        try {
            String var = files.get("inGambar").getOriginalFilename();
            byte[] var1 = files.get("inGambar").getBytes();
            if (files.get("inGambar").getOriginalFilename() != null || files.get("inGambar").getOriginalFilename() == "") {
                lib.writeFile(var, var1, path);
            }
        } catch (IOException e) {
            System.out.println(String.format("Warning: cannot writefile %s", files.get("inGambar").getOriginalFilename()));
        }

        JSONObject jsn = new JSONObject();
        jsn.put("kdmchn", (dForm.getKdMerchant() == null) ? kdMch : dForm.getKdMerchant());
        jsn.put("kdbank", kdbank);
        jsn.put("nmMchn", (dForm.getNamaMerchant() == null) ? "" : dForm.getNamaMerchant());
        jsn.put("nmPmlk", (dForm.getNamaPemilik() == null) ? "" : dForm.getNamaPemilik());
        jsn.put("almt", (dForm.getAlamat() == null) ? "" : dForm.getAlamat());
        jsn.put("klrhn", (dForm.getKelurahan() == null) ? "" : dForm.getKelurahan());
        jsn.put("kcmtn", (dForm.getKecamatan() == null) ? "" : dForm.getKecamatan());
        jsn.put("kota", (dForm.getKecamatan() == null) ? "" : dForm.getKecamatan());
        jsn.put("pos", (dForm.getKodePos() == 0) ? 0 : dForm.getKodePos());
        jsn.put("pic", (dForm.getPic() == null) ? files.get("inGambar").getOriginalFilename() : files.get("inGambar").getOriginalFilename());
        jsn.put("hp", (dForm.getNoHp() == 0) ? 0 : dForm.getNoHp());
        jsn.put("tlp", (dForm.getNoTlpn() == 0) ? 0 : dForm.getNoTlpn());
        jsn.put("mail", (dForm.getEmail() == null) ? "" : dForm.getEmail());
        jsn.put("norek", (dForm.getNoRekening() == 0) ? 0 : dForm.getNoRekening());
        jsn.put("nmrek", (dForm.getNamaRekening() == null) ? "" : dForm.getNamaRekening());
        jsn.put("sts", "N");

        if (bindingResult.hasErrors()) {
            repo.AddMerchant(jsn);
//            m.addAttribute("data", jsn+" | "+date.substring(9) + "" + files.get("inGambar").getOriginalFilename()+" | "+kdMch);
            return "redirect:/merchant";
        }
        repo.AddMerchant(jsn);
//        m.addAttribute("data", date.substring(9) + "" + files.get("inGambar").getOriginalFilename() + " | ");
        return "redirect:/merchant";
    }

    @PostMapping("/frmEditContent")
    public String editDataContent(Model m, HttpServletRequest request, @ModelAttribute mKonten dForm, BindingResult bindingResult, @RequestParam Map<String, MultipartFile> files) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }
        String[] usr = status.split("_");
        String gbr = (lib.cmpStr(files.get("gbr").getOriginalFilename(), "") == true) ? dForm.getPicture() : files.get("gbr").getOriginalFilename();
        String path = "\\src\\main\\resources\\static\\dist\\img";
//        int kdbank = 620022;
        

//        m.addAttribute("data", gbr+" | "+dForm.getKdkonten());
        try {
            String var = files.get("gbr").getOriginalFilename();
            byte[] var1 = files.get("gbr").getBytes();
            if (lib.cmpStr(files.get("gbr").getOriginalFilename(), "") == false) {
                lib.writeFile(var, var1, path);
            }
        } catch (IOException e) {
            System.out.println(String.format("Warning: cannot writefile %s", files.get("gbr").getOriginalFilename()));
        }

        JSONObject jsn = new JSONObject();
        jsn.put("kdkonten", dForm.getKdkonten());
        jsn.put("userid", usr[0]);
        jsn.put("tpkonten", (dForm.getTipekonten() == null) ? "" : dForm.getTipekonten());
        jsn.put("konten", (dForm.getKonten() == null) ? "" : dForm.getKonten());
        jsn.put("picture", gbr);
        jsn.put("flag", "1");

        repo.EditContent(jsn);
//        m.addAttribute("data", repo.EditContent(jsn));
//        m.addAttribute("data", repo.EditContent(jsn));

        if (bindingResult.hasErrors()) {
            return "redirect:content";
        }
        return "redirect:content";
    }

    @PostMapping("/frmEditMerchant")
    public String editDataMerchant(Model m, HttpServletRequest request, @ModelAttribute mMerchant dForm, BindingResult bindingResult, @RequestParam Map<String, MultipartFile> files) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }
        String[] usr = status.split("_");
        String gbr = (lib.cmpStr(files.get("inGambar").getOriginalFilename(), "") == true) ? dForm.getPic() : files.get("inGambar").getOriginalFilename();
        String path = "\\src\\main\\resources\\static\\dist\\img";
        int kdbank = Integer.parseInt(usr[1]);

        try {
            String var = files.get("inGambar").getOriginalFilename();
            byte[] var1 = files.get("inGambar").getBytes();
            if (lib.cmpStr(files.get("inGambar").getOriginalFilename(), "") == false) {
                lib.writeFile(var, var1, path);
            }
        } catch (IOException e) {
            System.out.println(String.format("Warning: cannot writefile %s", files.get("inGambar").getOriginalFilename()));
        }

        JSONObject jsn = new JSONObject();
        jsn.put("kdmchn", (dForm.getKdMerchant() == null) ? "" : dForm.getKdMerchant());
        jsn.put("kdbank", kdbank);
        jsn.put("nmMchn", (dForm.getNamaMerchant() == null) ? "" : dForm.getNamaMerchant());
        jsn.put("nmPmlk", (dForm.getNamaPemilik() == null) ? "" : dForm.getNamaPemilik());
        jsn.put("almt", (dForm.getAlamat() == null) ? "" : dForm.getAlamat());
        jsn.put("klrhn", (dForm.getKelurahan() == null) ? "" : dForm.getKelurahan());
        jsn.put("kcmtn", (dForm.getKecamatan() == null) ? "" : dForm.getKecamatan());
        jsn.put("kota", (dForm.getKecamatan() == null) ? "" : dForm.getKecamatan());
        jsn.put("pos", (dForm.getKodePos() == 0) ? 0 : dForm.getKodePos());
        jsn.put("pic", gbr);
        jsn.put("hp", (dForm.getNoHp() == 0) ? 0 : dForm.getNoHp());
        jsn.put("tlp", (dForm.getNoTlpn() == 0) ? 0 : dForm.getNoTlpn());
        jsn.put("mail", (dForm.getEmail() == null) ? "" : dForm.getEmail());
        jsn.put("norek", (dForm.getNoRekening() == 0) ? 0 : dForm.getNoRekening());
        jsn.put("nmrek", (dForm.getNamaRekening() == null) ? "" : dForm.getNamaRekening());
        jsn.put("sts", "N");

        repo.EditMerchant(jsn);
        if (bindingResult.hasErrors()) {
            return "redirect:merchant";
        }

//        m.addAttribute("data", dForm.getNamaMerchant()+" | "+dForm.getKdMerchant()+" | "+" | "+gbr);
        return "redirect:merchant";
    }

    @PostMapping("/getContentId")
    public String showEditContent(Model m, HttpServletRequest request, @RequestBody String request_data) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }
        String[] sts = status.split("_");
        Map<String, String> dt = lib.parseHttp(request_data);
        mKonten data = repo.tampilEditContent(dt.get("kode"));

        JSONObject jsn = new JSONObject();
        jsn.put("kdkonten", (data.getKdkonten() == null) ? "" : data.getKdkonten());
        jsn.put("tpkonten", (data.getTipekonten() == null) ? "" : data.getTipekonten());
        jsn.put("konten", (data.getKonten() == null) ? "" : data.getKonten());
        jsn.put("picture", (data.getPicture() == null) ? "" : data.getPicture());

        m.addAttribute("data", jsn);
        return "emptyJSON";
    }

    @PostMapping("/getMerchantId")
    public String showEditMerchant(Model m, HttpServletRequest request, @RequestBody String request_data) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }
        String[] sts = status.split("_");
        Map<String, String> dt = lib.parseHttp(request_data);
        mMerchant data = repo.tampilEditMerchant(dt.get("kode"));

        JSONObject jsn = new JSONObject();
        jsn.put("kdmchn", (data.getKdMerchant() == null) ? "" : data.getKdMerchant());
        jsn.put("nmmchn", (data.getNamaMerchant() == null) ? "" : data.getNamaMerchant());
        jsn.put("nmpemilik", (data.getNamaPemilik() == null) ? "" : data.getNamaPemilik());
        jsn.put("alamat", (data.getAlamat() == null) ? "" : data.getAlamat());
        jsn.put("kelurahan", (data.getKelurahan() == null) ? "" : data.getKelurahan());
        jsn.put("kecamatan", (data.getKecamatan() == null) ? "" : data.getKecamatan());
        jsn.put("kota", (data.getKota() == null) ? "" : data.getKota());
        jsn.put("pos", (data.getKodePos() == 0) ? "" : data.getKodePos());
        jsn.put("hp", (data.getNoHp() == 0) ? "" : data.getNoHp());
        jsn.put("tlpn", (data.getNoTlpn() == 0) ? "" : data.getNoTlpn());
        jsn.put("email", (data.getEmail() == null) ? "" : data.getEmail());
        jsn.put("norek", (data.getNoRekening() == 0) ? "" : data.getNoRekening());
        jsn.put("nmrek", (data.getNamaRekening() == null) ? "" : data.getNamaRekening());
        jsn.put("gbr", (data.getPic() == null) ? "" : data.getPic());

        m.addAttribute("data", jsn.toString());
        return "emptyJSON";
    }

    @PostMapping("/changeSts")
    public String changeSts(Model m, HttpServletRequest request, @RequestBody String request_data) {
        HttpSession session = request.getSession(true);
        String status = (String) session.getAttribute("user");
        if (cekSession(m, request, status) == false) {
            m.addAttribute("data", status);
            return "redirect:/";
        }
        String[] sts = status.split("_");
        Map<String, String> dt = lib.parseHttp(request_data);
        String[] str = dt.get("kode").split("_");

        repo.changeStatus(str);
        if(lib.cmpStr(sts[0], "admin") && lib.cmpStr(sts[1], "000001")){
            m.addAttribute("data", repo.tampilDataMerchantAdmin());
        }else{
            m.addAttribute("data", repo.tampilDataMerchant(sts[1]));
        }
        
        return "tbl_merchant";
    }

    @PostMapping("/")
    public String prosesLogin(Model m, @ModelAttribute mUser dForm, BindingResult bindingResult, HttpServletRequest request) {

        JSONObject jsn = new JSONObject();
        jsn.put("usr", dForm.getUserid());
        jsn.put("pass", dForm.getPass());
        jsn.put("bank", dForm.getKdbank());
        
        mUser dt = repo.prosesLogin(jsn);
        
        if (dt == null) {
            m.addAttribute("gagal", "Gagal Login! Username atau Password Salah");
            m.addAttribute("data", jsn);
            return "frm_cms_login";
        }
//        statusLog(dt.getUserid()+"_"+dt.getKdbank()+"_"+dt.getRole());
        cekSession(m, request, dt.getUserid() + "_" + dt.getKdbank() + "_" + dt.getRole());

        return "redirect:dashboard";
    }

    @GetMapping("/logout")
    public String prosesLogout(Model m, HttpServletRequest request) {
//        statusLog(null);
        HttpSession session = request.getSession(true);
        session.setAttribute("user", null);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String prosesLog(Model m) {
        statusLog(null);
        return "redirect:/";
    }

}
