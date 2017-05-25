package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import model.Board;
import model.BoardFile;
import model.CommentsVo;
import model.Member;
import service.CommentsService;
import service.IBoardService;
import service.IMemberService;

@Controller
public class BoardController {

	@Autowired
	private IBoardService boardService;

	@Autowired
	private IMemberService memberService;

	@Autowired
	private CommentsService commentsService;

	@RequestMapping("list.do")
	public ModelAndView boardList(@RequestParam(defaultValue = "1") int page, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		HashMap<String, Object> list = boardService.getBoardList(page);
		String id = (String) session.getAttribute("id");
		// System.out.println(id);

		mav.addAllObjects(list);
		mav.setViewName("list.tiles");

		return mav;
	}

	@RequestMapping("writeForm.do")
	public ModelAndView boardWritePage(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		String id = (String) session.getAttribute("id");

		Member member = memberService.getMember(id);

		String name = member.getName();
		String email = member.getId();

		mav.addObject("name", name);
		mav.addObject("email", email);
		mav.setViewName("writeForm.tiles");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "write.do")
	public String boardWrite(Board board, @RequestParam("ufile") MultipartFile ufile) {
		boardService.writeBoard(board, ufile);

		return "redirect:view.do?num=" + board.getNum();
	}

	@RequestMapping("modifyForm.do")
	public ModelAndView boardModifyPage(int num) {
		ModelAndView mav = new ModelAndView();

		mav.addAllObjects(boardService.getBoard(num));
		mav.setViewName("modifyForm.tiles");

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "modify.do")
	public String boardUpdate(Board board) {
		boardService.updateBoard(board);

		return "redirect:view.do?num=" + board.getNum();
	}

	@RequestMapping("passChkForm.do")
	public String boardDeletePage(Model model, int num) {
		model.addAttribute("num", num);

		return "passChkForm.tiles";
	}

	@RequestMapping(method = RequestMethod.GET, value = "passSucc.do")
	public String boardDeleteSucc(int num, String pass) {
		Board board = new Board();

		board.setNum(num);
		board.setPass(pass);

		if (boardService.passChk(num, pass) == true) {
			return "passChk.tiles";
		} else {
			return "passChkForm.tiles";
		}
	}

	@RequestMapping("delete.do")
	public String boardDelete(int num, String pass) {
		boolean isDeleted = boardService.deleteBoard(num, pass);

		if (isDeleted) {
			return "redirect:list.do";
		} else {
			return "redirect:view.do?num=" + num;
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "view.do")
	public ModelAndView boardView(int num, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ModelAndView mav = new ModelAndView();
		String id = (String) (req.getSession().getAttribute("id"));

		mav.addAllObjects(boardService.readBoard(num));
		mav.addObject("userid", id);
		mav.addObject("comments", commentsService.selectComments(num));
		mav.setViewName("view.tiles");

		return mav;
	}

	@RequestMapping("fileView.do")
	public void imageView(int f_id, HttpServletResponse resp) throws IOException {
		BoardFile bfile = boardService.getBoardFile(f_id);

		resp.setContentType("images/jpg; utf-8");
		String originFile = (String) bfile.getOriginFileName();
		String filename = new String(originFile.getBytes("UTF-8"), "ISO-8859-1");
		resp.setHeader("Content-Disposition", "inline;filename=\"" + filename + "\";");
		resp.setHeader("Content-Transfer-Encoding", "binary");

		OutputStream outputStream = resp.getOutputStream();
		File file = new File((String) bfile.getUri());

		FileInputStream inputStream = new FileInputStream(file);

		IOUtils.copy(inputStream, outputStream);

		outputStream.flush();
		outputStream.close();
	}

	@RequestMapping("main.do")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main.tiles");
		return mav;
	}

	@RequestMapping("download.do")
	public View download(int f_id) {
		BoardFile boardFile = boardService.getBoardFile(f_id);
		File file = new File(boardFile.getUri());

		return new DownloadView(file, boardFile.getOriginFileName());
	}

	@RequestMapping("getBoardJson.do")
	public @ResponseBody Board getBoardJson(HttpServletResponse resp, int num) {
		return (Board) boardService.getBoard(num).get("board");
	}

	@RequestMapping(value = "commentsWrite.do")
	public ModelAndView commentsWrite(CommentsVo comments) {
		ModelAndView mav = new ModelAndView();
		commentsService.insertComments(comments);

		mav.setViewName("redirect:view.do?num=" + comments.getB_idx());
		return mav;

	}

	@RequestMapping("commentsUpdate.do")
	public ModelAndView commentsUpdate(CommentsVo comments) {
		ModelAndView mav = new ModelAndView();
		commentsService.updateComments(comments);
		mav.setViewName("redirect:view.do?num=" + comments.getB_idx());
		return mav;
	}

	@RequestMapping("commentsDelete.do")
	public ModelAndView commentsDelete(CommentsVo comments) {
		ModelAndView mav = new ModelAndView();
		commentsService.deleteComments(comments.getCm_idx());
		mav.setViewName("redirect:view.do?num=" + comments.getB_idx());
		return mav;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

}
