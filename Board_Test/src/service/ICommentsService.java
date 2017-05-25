package service;

import java.util.List;

import model.CommentsVo;

public interface ICommentsService {
	public void insertComments(CommentsVo comments);

	public List<CommentsVo> selectComments(int b_idx);

	public void updateComments(CommentsVo comments);

	public boolean deleteComments(int cm_idx);
}