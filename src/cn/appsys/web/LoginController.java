package cn.appsys.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.appsys.pojo.BackendUser;
import cn.appsys.pojo.DevUser;
import cn.appsys.service.backendUser.BackendUserService;
import cn.appsys.service.devuser.DevUserService;

@Controller
public class LoginController {

	@Resource
	private DevUserService devUserService;
	@Resource
	private BackendUserService    backendUserService;
	
	
	@RequestMapping("/manager/logout")
	public  String  domanagerlogout(HttpSession  session) {
		session.invalidate();
		return "redirect:/manager/login";
	}
	
	@RequestMapping("/manager/main")
	public  String   tomanager() {
		return  "backend/main";
	}
	
	@RequestMapping("/dev/dobacklogin")
	public  String  dobacklogin(HttpServletRequest  request, @RequestParam String  userCode, @RequestParam String  userPassword) {
		BackendUser  backendUser =  backendUserService.login(userCode,userPassword);
		if(backendUser == null) {
			request.setAttribute("error", "管理员的用户名或者密码错误!");
			return  "backendlogin";
		}
		request.getSession().setAttribute("userSession", backendUser);
		return "redirect:/manager/main";
		
	}
	
	/**
	 *  开发者注销
	 * @param session
	 * @return
	 */
	@RequestMapping("/dev/logout")
	public String doDevLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/dev/login";
	}
	
	@RequestMapping("/dev/main")
	public String toDevMain() {
		return "developer/main";
	}
	
	/**
	 *  开发者登录
	 * @param request
	 * @param devCode
	 * @param devPassword
	 * @return
	 */
	@RequestMapping("/dev/dologin")
	public String doDevLogin(HttpServletRequest request,@RequestParam String devCode,@RequestParam String devPassword) {
		DevUser loginUser = devUserService.login(devCode,devPassword);
		if(loginUser == null) {
			request.setAttribute("error", "开发者用户名或密码错误！");
			return "devlogin";
		}
		request.getSession().setAttribute("devLoginUser", loginUser);
		return "redirect:/dev/main";
	}
	
	// 后台管理和开发者平台登录入口跳转
	@RequestMapping("/manager/login")
	public String toManagerLogin() {
		return "backendlogin";
	}
	
	@RequestMapping("/dev/login")
	public String toDevLogin() {
		return "devlogin";
	}
	
}
