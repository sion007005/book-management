package sion.bookmanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sion.bookmanagement.util.StringUtils;
import sion.mvc.ModelAndView;
import sion.mvc.dispatcher.ControllerAware;
import sion.mvc.dispatcher.GetMapping;

@Controller
public class LoginFormController implements ControllerAware {
	
	@Override
	@GetMapping("/login/form")
	public ModelAndView command(HttpServletRequest request, HttpServletResponse response) {
//		TODO 불필요한 코드가 아닌가 ? interceptor에서 체크하고 넘어오니까  
//		if (UserContext.isLogin()) {
//			return new ModelAndView(HttpResponse.REDIRECT_NAME + "/members/list");
//		}
		String returnUrl = request.getParameter("returnUrl");
		ModelAndView mav = new ModelAndView("login_form");
		mav.addObject("returnUrl", returnUrl);

		String email = (String) request.getParameter("email");
		if (!StringUtils.isEmpty(email)) {
			mav.addObject("email", email);
			mav.addObject("message", "아이디와 패스워드가 맞지 않습니다.");
		}
		
		return mav;
	}
}
