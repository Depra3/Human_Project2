package com.project2.project2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
    
    @Autowired
    private MemberDAO mDao;
    @Autowired
    private CommunityDAO cDao;

    @GetMapping("/")
    public String index(Model m){
        List<CommunityDTO> cList = cDao.findAll();
        m.addAttribute("cList",cList);
        return "home";
    }
    // @GetMapping("/users")
    // public String listUsers(Model model) {
    //     List<MemberDTO> mList = mDao.findAll();
    //     model.addAttribute("mList", mList);
    //     return "users";
    // }
    
    @GetMapping("/login")
    public String login(){ return "login"; }
    @GetMapping("/user")
    public String userChk(){ return "user"; }

    @PostMapping("/login")
    public String doLogin(MemberDTO mem, HttpSession ses){
        MemberDTO eDto = mDao.findByUid(mem.getUid());
        if(eDto != null && eDto.getPw().equals(eDto.getPw())){
            ses.setAttribute("gUserid", mem.getUid());
            return "redirect:/";
        } else {
            return "login";
        }
    }
    @RequestMapping("/loginchk")
    @ResponseBody
    public String loginChk(HttpServletRequest req){
        String str ="";
        HttpSession session = req.getSession();
        String uid = (String) session.getAttribute("gUserid");
        if(uid == null || uid.equals("")){
            str ="";
        } else {
            str = uid;
        }
        return str;
    }
    @RequestMapping("/signout")
    @ResponseBody
    public String doSignOut(HttpServletRequest req){
        String retval = "";
        try {
            HttpSession session = req.getSession();
            session.invalidate();
            retval = "ok";
        } catch (Exception e) {
            retval = "fail";
        }
        return retval;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<MemberDTO> mList = mDao.findAll();
        model.addAttribute("mList", mList);
        return "users";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("member", new MemberDTO());
        return "register";
    }
    @PostMapping("/register")
    public String doRegister(MemberDTO mDto){
        mDto.setAdmin("False");
        mDto.setSort("o");
        mDao.save(mDto);
        return "redirect:/login";
    }

    @GetMapping("/board")
    public String board(Model model){
        model.addAttribute("cDao", new CommunityDTO());
        return "board";
    }
    @PostMapping("/board")
    public String doBoard(CommunityDTO cDto, HttpServletRequest req){
        HttpSession ses = req.getSession();
        String author = (String) ses.getAttribute("gUserid");
        // cDto.setNum();
        // int maxValue = cDto.getNum().getInteger("maxValue");
        // if (maxValue != 1){
        //     maxValue = 1;
        // }
        // SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // Date date = new Date();
        // String iDate = sDate.format(date);
        // cDto.setReg_date(iDate);
        cDto.setReg_date(new Date());
        cDto.setMod_date(null);
        cDto.setAuthor(author);
        cDao.save(cDto);
        return "redirect:/";
    }

    @GetMapping("/mg")
    public String doManage(){
        return "management";
    }


}
