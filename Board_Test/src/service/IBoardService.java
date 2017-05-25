package service;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import model.Board;
import model.BoardFile;

public interface IBoardService {

	public void writeBoard(Board board, MultipartFile file);

	public boolean updateBoard(Board board);

	public boolean deleteBoard(int num, String pass);

	public HashMap<String, Object> readBoard(int num);
	
	public HashMap<String, Object> getBoard(int num);

	public HashMap<String, Object> getBoardList(int page);
	
	public BoardFile getBoardFile(int f_id);
	
	public boolean passChk(int num, String pass);
	
}
