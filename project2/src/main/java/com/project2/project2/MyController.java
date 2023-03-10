package com.project2.project2;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.GroupLayout.Group;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
    
    @GetMapping("/login")
    public String login(){ return "login"; }
    @GetMapping("/user")
    public String userChk(){ return "user"; }

    @PostMapping("/login")
    public String doLogin(MemberDTO mem, HttpSession ses){
        
        MemberDTO eDto = mDao.findByUid(mem.getUid());
        if(eDto != null && eDto.getPw().equals(mem.getPw())){
            ses.setAttribute("gUserid", mem.getUid());
            ses.setAttribute("admin", eDto.getAdmin());
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
    public String listUsers(Model m) {
        List<MemberDTO> mList = mDao.findAll();
        m.addAttribute("mList", mList);
        return "users";
    }

    @GetMapping("/register")
    public String register(Model m){
        m.addAttribute("member", new MemberDTO());
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
    public String board(Model m){
        m.addAttribute("cDao", new CommunityDTO());
        return "board";
    }
    @PostMapping("/board")
    public String doBoard(CommunityDTO cDto, HttpServletRequest req){
        HttpSession ses = req.getSession();
        String author = (String) ses.getAttribute("gUserid");
        List<CommunityDTO> cDto2 = cDao.findAll();
        // int a = cDao.find;
        cDto.setReg_date(new Date());
        cDto.setMod_date(null);
        cDto.setJoin(0);
        cDto.setAuthor(author);
        cDao.save(cDto);
        return "redirect:/";
    }
    @ResponseBody
    @RequestMapping("/boardView")
    public String addJoin(HttpServletRequest req, Model m){
        String id = req.getParameter("id");
        CommunityDTO cDto = cDao.findby_Id(id);
        // System.out.println(a);
        m.addAttribute("comm", cDto);
        return "boardView";
    }

    @GetMapping("/mg")
    public String doManage(Model m){
        return "management";
    }

    @ResponseBody
    @RequestMapping("/memInfo")
    public String searchMem() {
        List<MemberDTO> m_List = mDao.findAll();
        JSONArray ja = new JSONArray();
        for(int i = 0; i < m_List.size(); i++){
            JSONObject jo = new JSONObject();
            jo.put("uid", m_List.get(i).getUid());
            jo.put("pw", m_List.get(i).getPw());
            jo.put("nickname", m_List.get(i).getNickname());
            jo.put("name", m_List.get(i).getName());
            jo.put("email", m_List.get(i).getEmail());
            jo.put("mobile", m_List.get(i).getMobile());
            jo.put("admin", m_List.get(i).getAdmin());
            jo.put("sort", m_List.get(i).getSort());
            ja.add(jo);
        }
        return ja.toString();
    }

    @ResponseBody
    @RequestMapping("/memMod")
    public String upMem(HttpServletRequest req){
        String uid = req.getParameter("list");
        MemberDTO mDto = mDao.findByUid(uid);
        if(mDto != null && req.getParameter("optype").equals("delete")){
            if(mDto.getAdmin().equals("False")){
                mDto.setAdmin("True");
            }else if(mDto.getAdmin().equals("True")){
                mDto.setAdmin("False");
            }
            mDao.save(mDto);
        }
        return "";
    }
}
        

