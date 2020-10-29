package sion.bookmanagement.service;

import java.util.List;

import sion.bookmanagement.repository.MemberRepository;

public class MemberService {
	private static MemberService memberService = new MemberService();
	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	
	private MemberService() {
	}
	
	public static MemberService getInstance() {
		return memberService;
	}
	
	public int create(Member member) {
		return memberRepository.create(member);
	};
	
	public List<Member> searchMemberByAge(int from, int to) {
		List<Member> memberList = memberRepository.searchByAge(from, to);
		return memberList;
	};
	
	//TODO 파라미터가 세개 이상 넘어가면 클래스로 만들 것
	public List<Member> search(MemberSearchCondition condition) {
		List<Member> memberList = memberRepository.search(condition);
		return memberList;
	}
	
	public void updateMember(Member member) {
		memberRepository.update(member);
	}
	
	public void removeMember(Integer memberId) {
		Member member = memberRepository.findOneById(memberId);
		
		if(member == null) {
			throw new RuntimeException("존재하지 않는 멤버");
		}
		
		memberRepository.deleteById(memberId);
	};
	
	public List<Member> findAll(MemberOrderType orderType) {
		List<Member> memberList = memberRepository.findByAll(orderType);
		return memberList;
	}

	public Member findOneById(Integer memberId) {
		return memberRepository.findOneById(memberId);
	}
	
}
