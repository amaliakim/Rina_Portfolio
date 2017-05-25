package dao;

import model.Member;

public interface IMemberDao {
	public int insertMember(Member member);

	public int updatePw(Member member);

	public int deleteMember(String id);

	public Member selectOne(String id);
}
