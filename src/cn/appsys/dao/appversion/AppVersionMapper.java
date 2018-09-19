package cn.appsys.dao.appversion;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.AppVersion;

public interface AppVersionMapper {
	/**
	 * 
	 * 根据id来获取版本信息的修改列表
	 * @param id
	 * @return
	 */
	List<AppVersion> getAppVersionListById(@Param("id")Integer id);
	
	/**
	 * 
	 * 修改版本信息
	 * @param id
	 * @param versionSize 
	 * @param versionInfo
	 * @return
	 */
	int updateVersionInfo(@Param("id")Integer id,@Param("versionSize")double versionSize, @Param("versionInfo")String versionInfo);

	

	
	
}
