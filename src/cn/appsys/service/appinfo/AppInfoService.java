package cn.appsys.service.appinfo;

import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.QueryAppInfoVO;
import cn.appsys.util.PageBean;

public interface AppInfoService {

	/**
	 * 根据条件进行分页查询
	 * @param pageBean
	 * @param queryAppInfoVO
	 */
	void getAppInfoList(PageBean<AppInfo> pageBean,QueryAppInfoVO queryAppInfoVO);
	
	/**根据id进行查询
	 * @param aid
	 * @return
	 */
	AppInfo getInfoById(Integer id);

	/**保存修改信息
	 * @param appInfo
	 */
	void uploadAppInfo(AppInfo appInfo);
 

}
