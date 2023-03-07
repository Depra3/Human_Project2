package com.project2.project2;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
    
    @Autowired
    private UserRepository userRepository;
    private CommRepository commRepository;

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(User user, HttpSession session) {
        User existingUser = userRepository.findByUid(user.getUid());
        if (existingUser != null && existingUser.getPw().equals(user.getPw())) {
            session.setAttribute("gUserid", user.getUid());
            return "redirect:/";
        } else {
            System.out.println("aaa");
            return "login";            
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        user.setAdmin("False");
        user.setSort("o");
        userRepository.save(user);
        return "redirect:/login";
    }

    // 회원정보 목록 출력
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "users";
    }

    @RequestMapping("/loginchk")
    @ResponseBody
    public String loginok(HttpServletRequest req){
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
    public String doSignout(HttpServletRequest req){
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

    @GetMapping("/board")
    public String board(Model model){
        model.addAttribute("comm", new Community());
        return "board";
    }


    @PostMapping("/board")   
    public String boardUp(Community comm, HttpServletRequest req) {
        Community existingComm = commRepository.findByNum(comm.getNum());
        HttpSession session = req.getSession();
        String author = (String) session.getAttribute("gUserid");
        System.out.println(author);
        String title = (String) req.getParameter("title");
        String content = (String) req.getParameter("content");
        
        int Comm_num = 0;
        // if(existingComm.getNum() != null){
        //     Comm_num = existingComm.getNum().MAX_VALUE+1;
        // }

        comm.setNum(Comm_num);
        comm.setTitle(title);
        comm.setContent(content);
        comm.setReq_date(new Date());
        comm.setAuthor(author);
        commRepository.save(comm);
        return "redirect:/";
    }

    @RequestMapping("/boardMod")
    @ResponseBody
    public String boardMod(Integer num, Community comm) {
        Community existingComm = commRepository.findByNum(num);
        if (existingComm != null) {
            existingComm.setMod_date(new Date());
            commRepository.save(comm);
            return "redirect:/";
        }else{
            return "fail";
        }
    }
}
