package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IMemberDao;
import model.Member;

@Service
public class MemberService implements IMemberService{

	@Autowired
	private IMemberDao memberDao;

	@Override
	public void join(Member member) {
		// TODO Auto-generated method stub
		memberDao.insertMember(member);
	}

	@Override
	public boolean checkId(String id) {
		// TODO Auto-generated method stub
		Member member = memberDao.selectOne(id);
		
		if(member == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean checkPw(String id, String pw) {
		// TODO Auto-generated method stub
		Member member = memberDao.selectOne(id);
		if(member.getPw().equals(pw))
			return true;
		else
			return false;
	}

	@Override
	public Member getMember(String id) {
		return memberDao.selectOne(id);
	}
	
}







