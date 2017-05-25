package service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.IBoardDao;
import dao.IBoardFileDao;
import model.Board;
import model.BoardFile;

@Service
public class BoardService implements IBoardService {

	@Autowired
	private IBoardDao boardDao;

	@Autowired
	private IBoardFileDao fileDao;

	@Override
	public void writeBoard(Board board, MultipartFile file) {

		// 저장 할 경로
		String path = "c:/Users/student/Upload/"; // ~/Upload로 쓰면 현재 경로

		// 원본 파일명
		String filename = file.getOriginalFilename();

		// 파일의 확장자 저장
		String[] fileSplit = filename.toString().split("[.]");
		String fileSub = fileSplit[1];

		// UUID를 이용해 파일명 저장
		String UUIDname = UUID.randomUUID().toString().replace("-", "");

		// 파일의 경로
		String fileuri = path + UUIDname + "." + fileSub;
		File localFile = new File(fileuri);

		// 파일의 크기
		int fileSize = (int) file.getSize();

		// BoardFile에 저장
		BoardFile boardFile = new BoardFile();

		boardFile.setOriginFileName(filename);
		boardFile.setSize(fileSize);
		boardFile.setUri(fileuri);

		try {
			file.transferTo(localFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// board.setFileuri(fileuri);

		// BoardFile테이블에 준비한 boardFile 데이터 삽입
		fileDao.insertBoardFile(boardFile);

		// 삽입 된 레코드의 파일 아이디를
		int fileid = boardFile.getF_id();

		// board의 fileid 컬럼에 넣어서 board 데이터 삽입
		board.setFileid(fileid);
		board.setFileuri(fileuri);

		boardDao.insertBoard(board);

	}

	@Override
	public boolean updateBoard(Board board) {
		String originPass = boardDao.selectOne(board.getNum()).getPass();

		if (board.getPass().equals(originPass)) {
			boardDao.updateBoard(board);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteBoard(int num, String pass) {
		String originPass = boardDao.selectOne(num).getPass();

		if (pass.equals(originPass)) {
			int f_id = boardDao.selectOne(num).getFileid();
			String fileUri = fileDao.selectOne(f_id).getUri();

			File file = new File(fileUri);
			file.delete();

			fileDao.deleteBoardFile(f_id);
			boardDao.deleteBoard(num);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public HashMap<String, Object> readBoard(int num) {
		Board board = boardDao.selectOne(num);

		board.setReadcount(board.getReadcount() + 1);
		boardDao.updateBoard(board);

		BoardFile boardFile = fileDao.selectOne(board.getFileid());
		HashMap<String, Object> attr = new HashMap<>();
		attr.put("board", board);
		attr.put("boardFile", boardFile);

		return attr;
	}

	@Override
	public HashMap<String, Object> getBoard(int num) {
		Board board = boardDao.selectOne(num);

		BoardFile boardFile = fileDao.selectOne(board.getFileid());
		HashMap<String, Object> attr = new HashMap<>();
		attr.put("board", board);
		attr.put("boardFile", boardFile);

		return attr;
	}

	@Override
	public HashMap<String, Object> getBoardList(int page) {

		// 시작 페이지와 끝 페이지 계산
		int startPage = ((page - 1) / 10) * 10 + 1;
		int endPage = (((page - 1) / 10) + 1) * 10;

		// 첫 페이지와 마지막 페이지 계산
		int firstPage = 1;
		int lastPage = (boardDao.getBoardCount() - 1) / 10 + 1;

		// 끝 페이지 계산
		endPage = lastPage < endPage ? lastPage : endPage;

		// 해당 페이지의 게시물을 쿼리하기 위한 skip과 count
		int skip = (page - 1) * 10;
		int count = 10;
		HashMap<String, Object> params = new HashMap<>();
		params.put("skip", skip);
		params.put("count", count);
		List<Board> list = boardDao.selectBoardLimit(params);

		HashMap<String, Object> result = new HashMap<>();
		result.put("start", startPage);
		result.put("first", firstPage);
		result.put("end", endPage);
		result.put("last", lastPage);
		result.put("current", page);
		result.put("boardList", list);

		return result;
	}

	@Override
	public BoardFile getBoardFile(int f_id) {
		return fileDao.selectOne(f_id);
	}

	@Override
	public boolean passChk(int num, String pass) {
		Board board = boardDao.selectOne(num);
		
		if (board.getPass().equals(pass))
			return true;
		else
			return false;
	}

}
