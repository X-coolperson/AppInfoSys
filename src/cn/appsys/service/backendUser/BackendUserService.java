package cn.appsys.service.backendUser;

import cn.appsys.pojo.BackendUser;

public interface BackendUserService {
	/**
	 * 
	 * 后台管理员登录
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	BackendUser login(String userCode, String userPassword);

}
