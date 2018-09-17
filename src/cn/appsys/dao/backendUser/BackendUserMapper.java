package cn.appsys.dao.backendUser;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.BackendUser;

public interface BackendUserMapper {
	/**
	 * 管理员登录
	 * @param userCode
	 * @return
	 */
	BackendUser getBackendUserByuserCode(@Param("userCode")String userCode);

}
