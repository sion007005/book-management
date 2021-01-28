package sion.bookmanagement.repository.member;


import java.util.Date;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.service.member.Member;
import sion.mvc.ApplicationContext;
import sion.mvc.util.PropertiesLoader;
import sion.mvc.util.SHA256Util;

@Slf4j
public class MemberRepositoryTest {
	MemberRepository repository;

	// 테스트 실행 전에 setUp을 한번 실행하도록 함 
	@Before
	public void setUp() {
		repository = new MemberRepository();
		Properties properties = new PropertiesLoader().load();
		ApplicationContext.addProperties(properties);
	}
	
	@Test
	public void test_crateMemberTest() {
		for (int i = 0; i < 500; i++) {
			String salt  = SHA256Util.generateSalt();
			Member member = new Member("배수지"+i, (i%2==0) ? "F" : "M", "sooji94" + i +"@naver.com", 27, "01011112222", "1234"+i);
			member.setCreatedAt(new Date());
			member.setUpdatedAt(new Date());
			member.setSalt(salt);
			int memberId = repository.create(member);
			log.info("memberId, i : {} {}", memberId, i);
		}
//		assertEquals(268, repository.create(member));
	}

}
