package service;

import model.Member;

public interface IMemberService {
	public void join(Member member);

	public boolean checkId(String id);

	public boolean checkPw(String id, String pw);

	public Member getMember(String id);
}
