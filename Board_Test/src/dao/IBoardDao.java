package dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import model.Board;

public interface IBoardDao {
	public int insertBoard(Board board);

	public int updateBoard(Board board);

	public int deleteBoard(int num);

	public Board selectOne(int num);
	
	public List<Board> selectBoardLimit(HashMap<String, Object> params);
	
	public int getBoardCount();
}
