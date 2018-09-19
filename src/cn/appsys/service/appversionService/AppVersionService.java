package cn.appsys.service.appversionService;

import java.util.List;

import cn.appsys.pojo.AppVersion;

public interface AppVersionService {
	/**
	 * 
	 * 根据id来获取版本信息修改列表
	 * @param id
	 * @return
	 */
	List<AppVersion> getAppVersionListById(Integer id);
	/**
	 * 更改版本信息
	 * @param id
	 * @param versionSize 
	 * @param versionInfo
	 * @return 
	 */
	boolean updateVersionInfo(Integer id, double versionSize, String versionInfo);

	
	
	
	
	
	
}
