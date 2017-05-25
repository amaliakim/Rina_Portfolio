package dao;

import java.util.List;

import model.CommentsVo;

public interface ICommentsDao {

	public int insertComments(CommentsVo comments);

	public int updateComments1(CommentsVo comments);

	public int updateComments2(CommentsVo comments);

	public CommentsVo selectOne(int cm_idx);

	public int selectMaxOrder1(int b_idx);

	public int selectMaxOrder2(CommentsVo comments);

	public int increaseOrder(CommentsVo comments);

	public List<CommentsVo> selectComments(int b_idx);

	public int deleteComments(int cm_idx);

	public int updateCommentsContent(CommentsVo comments);

	public int selectMaxBynoParent(int b_idx);

	public int updateOrder(CommentsVo comments);
}
