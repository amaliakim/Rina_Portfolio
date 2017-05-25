package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ICommentsDao;
import model.CommentsVo;

@Service
public class CommentsService implements ICommentsService {

	@Autowired
	private ICommentsDao commentsDao;

	@Override
	public void insertComments(CommentsVo comments) {
		// TODO Auto-generated method stub

		if (comments.getCm_parent() == 0) {
			int pre_cm_order = commentsDao.selectMaxBynoParent(comments.getB_idx());
			comments.setCm_order(pre_cm_order + 1);
		} else {
			commentsDao.updateOrder(comments);
			comments.setCm_order(comments.getCm_order() + 1);
			comments.setCm_depth(comments.getCm_depth() + 1);
		}

		commentsDao.insertComments(comments);
	}

	@Override
	public boolean deleteComments(int cm_idx) {
		// TODO Auto-generated method stub
		commentsDao.deleteComments(cm_idx);
		return true;
	}

	@Override
	public List<CommentsVo> selectComments(int b_idx) {
		// TODO Auto-generated method stub
		return commentsDao.selectComments(b_idx);
	}

	@Override
	public void updateComments(CommentsVo comments) {
		commentsDao.updateCommentsContent(comments);
	}

}
