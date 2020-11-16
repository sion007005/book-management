package sion.bookmanagement.service.member;

import java.util.List;

import sion.bookmanagement.repository.member.MemberRepository;

public class MemberService {
	private static MemberService memberService = new MemberService();
	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	
	private MemberService() {
	}
	// 하나의 service객체만 쓰도록하는 싱글톤패턴! 
	public static MemberService getInstance() {
		return memberService;
	}
	
	public int create(Member member) {
		return memberRepository.create(member);
	};
	
	public List<Member> search(MemberSearchCondition condition) {
		List<Member> memberList = memberRepository.search(condition);
		return memberList;
	}
	
	public void update(Member member) {
		memberRepository.update(member);
	}
	
	public void remove(Integer memberId) {
		Member member = memberRepository.findOneById(memberId);
		
		if(member == null) {
			throw new RuntimeException("존재하지 않는 멤버");
		}
		
		memberRepository.deleteById(memberId);
	};
	
	public List<Member> findAll(MemberOrderType orderType) {
		List<Member> memberList = memberRepository.findAll(orderType);
		return memberList;
	}

	public Member findOneById(Integer memberId) {
		return memberRepository.findOneById(memberId);
	}
	
	public Member findOneByEmail(String email) {
		return memberRepository.findOneByEmail(email);
	}
}
