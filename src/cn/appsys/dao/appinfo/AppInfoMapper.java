package cn.appsys.dao.appinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.QueryAppInfoVO;

public interface AppInfoMapper {

/*	int getAppInfoCount();
	
	List<AppInfo> getAppInfoList(@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize);
*/	
	int getAppInfoCount(QueryAppInfoVO queryAppInfoVO);

	List<AppInfo> getAppInfoList(QueryAppInfoVO queryAppInfoVO);
	/**
	 * 
	 * 根据id修改status
	 * @param appinfoid
	 * @param valueId
	 */
	void updateStatusByInfoId(@Param("appinfoid1")Integer appinfoid, @Param("status")Integer status);

	/*根据id查询信息*/
	AppInfo getInfoById(@Param("id")Integer id);
	/*保存修改信息*/
	void uploadAppInfo(AppInfo appInfo);
	
	
}
