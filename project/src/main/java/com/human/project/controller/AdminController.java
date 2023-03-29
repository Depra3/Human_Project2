package com.human.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.human.project.domain.Option;
import com.human.project.domain.Page;
import com.human.project.domain.Users;
import com.human.project.service.AdminService;
import com.human.project.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping({"", "/", "/index"})
	public String index(Model model, Option option, Page page) throws Exception {

		List<Users> usersList = adminService.userPage(page, option);
		model.addAttribute("usersList",usersList);
		model.addAttribute("page", page);
		
		return "/admin/index";
	}
	
	@GetMapping("/role")
	public String role(Model model, Option option, Page page) throws Exception {
		
		List<Users> usersList = adminService.userPage(page, option);
		model.addAttribute("usersList",usersList);
		model.addAttribute("page", page);
		
		return "/admin/role";
	}

	@GetMapping("/test")
	public String test() {
		log.info("테스트... (삭제 예정)");

		return "/admin/test";
	}
	
	// 유저 선택 삭제
	@PostMapping("/delete")
	public String userDel(@RequestParam(value="chkbox[]") List<String> userNoList) throws Exception{
		int result = adminService.delete(userNoList);
		
		if (result > 0) log.info("회원 삭제 성공");
		else 			log.info("회원 삭제 실패");
		
		return "redirect:/admin";
	}
	
	// 유저 권한 추가
	@PostMapping("/addauth")
	public String addAuth(@RequestParam(value="radio_val") String userNo, @RequestParam(value="auth_val") String authVal) throws Exception {
		int result = adminService.addAuth(userNo, authVal);
		
		if (result > 0) {
			log.info("회원 권한 추가 성공");
			adminService.modEna(userNo, 1);
		}
		else 			log.info("회원 권한 추가 실패");
		
		return "redirect:/admin";
	}
	
	// 유저 권한 삭제
	@PostMapping("/delauth")
	public String delAuth(@RequestParam(value="radio_val") String userNo, @RequestParam(value="auth_val") String authVal) throws Exception {
		int result = adminService.delAuth(userNo, authVal);
		if (result > 0) {
			log.info("회원 권한 삭제 성공");
			adminService.modEna(userNo, 0);
		}
		else 			log.info("회원 권한 삭제 실패");
		
		return "redirect:/admin";
	
	}
	
	// 불건전한 유저 닉네임 변경
	@PostMapping("/modnick")
	public String modNick(@RequestParam(value="chkbox[]") List<String> userNoList) throws Exception{
		int result = adminService.modNick(userNoList);
		if (result > 0) log.info("닉네임 변경 성공");
		else 			log.info("닉네임 변경 실패");
		return "redirect:/admin";
	}

}
