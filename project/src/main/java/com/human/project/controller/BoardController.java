package com.human.project.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.human.project.domain.Board;
import com.human.project.domain.Comment;
import com.human.project.domain.Option;
import com.human.project.domain.Page;
import com.human.project.domain.Users;
import com.human.project.service.BoardService;
import com.human.project.service.CommentService;
import com.human.project.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board") // Mapping에서 /board생략해도 연결됨
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentService commentService;
	
	// 게시글 목록
	@GetMapping("/list")
	public String listG(Model model, Option option, Page page) throws Exception {
        
		// 게시글 목록 요청
		List<Board> boardList = boardService.list(page, option);
		
		log.info("boardList : " +boardList);
		
		// 게시글 목록 모델에 등록
		model.addAttribute("boardList", boardList);
		model.addAttribute("page", page);
		
		return "/board/list";
	}
	
	
	// 게시글 목록
	@PostMapping("/list")
	public String listP(Model model, Option option, Page page) throws Exception {
        
		// 게시글 목록 요청
		List<Board> boardList = boardService.list(page, option);
		
		log.info("boardList : " +boardList);
		
		// 게시글 목록 모델에 등록
		model.addAttribute("boardList", boardList);
		model.addAttribute("page", page);
		
		return "/board/list";
	}
	
	
	// 게시글 쓰기 - 화면
	@GetMapping("/insert")
	public String insert(Board board, Principal principal, Model model) throws Exception {
		
		// principal이 null값이 아닐 경우 userId를 불러옴
		if (principal != null) {
			
		String userId = principal.getName();
		model.addAttribute("userId", userId);
        
//        log.info("userId : "+userId);
		}
		
		return "/board/insert";
	}
	
	// 게시글 쓰기 - 처리
	// 요청파라미터 연결 : @RequestParam("title") String title
	// * 요청파라미터 이름과 실제 매개변수 이름이 같으면 어노테이션 생략 가능
	@PostMapping("/insert")
	public String insertPro(Board board, Users user, Model model, HttpServletRequest req) throws Exception {
		
		log.info("user : " +user);
		
		// 게시글 쓰기 요청
		int result = boardService.insert(board);
		
		if( result > 0 ) log.info("게시글 쓰기 성공...");
		else 			 log.info("게시글 쓰기 실패...");
		
		return "redirect:/board/list";
	}
	
	// 게시글 읽기 - 화면
	@GetMapping("/read")
	public String read(Model model, int boardNo) throws Exception {
		
		Board board = boardService.read(boardNo);
		model.addAttribute("board", board);
		
		List<Comment> commentList = commentService.list(boardNo);

		model.addAttribute("commentList", commentList);
		
		return "/board/read";
	}
	
	// 게시글 수정 - 화면
	@GetMapping("/update")
	public String update(Model model, int boardNo) throws Exception {
		
		Board board = boardService.read(boardNo);
		model.addAttribute("board", board);
		
		return "/board/update";
	}
	
	// 게시글 수정 - 처리
	@PostMapping("/update")
	public String updatePro(Board board) throws Exception {
		
		int result = boardService.update(board);
		
		if( result > 0 ) 	log.info("게시글 수정 성공...");
		else 				log.info("게시글 수정 실패...");
		
		return "redirect:/board/list";
	}
	
	// 게시글 삭제
	@PostMapping("/delete")
	public String delete(int boardNo) throws Exception {
		
		int result = boardService.delete(boardNo);
		
		if (result > 0) log.info("게시글 삭제 성공");
		else 			log.info("게시글 삭제 실패");
		
		return "redirect:/board/list";
	}
		

	
	

}
