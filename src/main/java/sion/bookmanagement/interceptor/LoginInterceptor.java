package sion.bookmanagement.interceptor;

import java.lang.reflect.Method;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sion.bookmanagement.auth.BookManagementUser;
import sion.bookmanagement.service.member.Member;
import sion.bookmanagement.service.member.MemberService;
import sion.bookmanagement.util.NumberUtils;
import sion.mvc.auth.User;
import sion.mvc.auth.UserContext;
import sion.mvc.dispatcher.Controller;
import sion.mvc.dispatcher.DispatcherException;
import sion.mvc.dispatcher.ForbiddenException;
import sion.mvc.dispatcher.Interceptor;
import sion.mvc.dispatcher.Login;
import sion.mvc.support.AES256Util;
import sion.mvc.support.CookieUtils;

@Slf4j
public class LoginInterceptor implements Interceptor {
	MemberService memberService = MemberService.getInstance();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Controller controller) {
		// dispatcher 의 precommand 로직 다 옮겨옴 
		userSetting(request);
		loginCheck(controller);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Controller controller) {
//		response.addHeader("_user", value);
		//TODO
//		response.getModelAndView().getModel().put("_user", UserContext.get());
	}

	/*
	 * 쿠키에 저장된 sid값을 읽어와서, threadLocal에 user 저장
	 */
	private void userSetting(HttpServletRequest request) {
		String encryptedSid = CookieUtils.getValue(request, "sid");

		if (Objects.isNull(encryptedSid)) {
			UserContext.set(BookManagementUser.newLogoutUser(request.getRemoteAddr()));
			return;
		}
		
		try {
			AES256Util encryptUtil = new AES256Util();
			String decryptedSid = encryptUtil.decrypt(encryptedSid);
			Integer memberId = NumberUtils.parseInt(decryptedSid);
			
			if (Objects.isNull(memberId)) {
				UserContext.set(BookManagementUser.newLogoutUser(request.getRemoteAddr()));
				return;
			}
			
			Member member = memberService.findOneById(memberId);
			User loginUser = BookManagementUser.newLoginUser(memberId, member.getEmail(), member.getName(), request.getRemoteAddr()); 
			UserContext.set(loginUser); // threadLocal에 user 저장 
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			throw new DispatcherException(e.getMessage(), e); //e도 함께 넘겨야 디버깅 가능
		} 
	}

	private void loginCheck(Controller controller) {
		//로그인 체크 -> 필요한가(=@Login이 있는가)? 
		// 1) 필요하다면, 
		//   1-1) 로그인이 되었는가? 확인 후, controller.command 실행 
		//   1-1) 로그인이 안 된 상태면, 로그인 페이지로 redirection
		Login login = null;
		
		try {
			//getClass()로 controller 클래스의 메타정보를 가져와서 -> 그 중에서도 method 이름이 command이고 파라미터가 httpRequest인 것을 가져와라 
			Method method = controller.getClass().getMethod("command", HttpServletRequest.class, HttpServletResponse.class); 
			//그 메서드에서 선언된 어노테이션 정보를 가져와라
			 login = method.getDeclaredAnnotation(Login.class);
		}  catch (Exception e) { 
	   	throw new DispatcherException(e);
	   }
	   
		// 1) 로그인이 필요한 작업이라면 
		if (needLogin(login)) {
			if (UserContext.isNotLogin()) {
				throw new ForbiddenException("권한이 없는 페이지입니다.");
			}
			
	   	// 1-1) 로그인 되었는지 체크
			// 쿠키에 sid 값이 있으면 로그인이 된 것이고, 없으면 로그인이 필요하지만 안 된 것으로 판단-> 예외던짐(403 forbidden 접근금지)  
//			String sid = CookieUtils.getValue(httpRequest.getHeaders(), "sid");
//			
//			//TODO sid에 넣어준 아이디값이 db에 정말 있는 회원인지 체크해줘야 함  
//			
//			//sid 값이 없으면 예외를 던진다. (위에 dispatch 메서드가 예외를 받아서, 에러 페이지 띄운다) 
//			if (StringUtils.isEmpty(sid)) {
//				throw new ForbiddenException("권한이 없는 페이지입니다.");
//			} 
	   }
	}
	
	private boolean needLogin(Login login) {
		return login != null;
	}
}
