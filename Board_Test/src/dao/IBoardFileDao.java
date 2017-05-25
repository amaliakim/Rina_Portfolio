package dao;

import java.util.List;

import model.BoardFile;

public interface IBoardFileDao {
	
	public int insertBoardFile(BoardFile boardFile);
	
	public int updateBoardFile(BoardFile boardFile);
	
	public int deleteBoardFile(int id);
	
	public BoardFile selectOne(int id);
	
	public List<BoardFile> selectAll();
	
}
