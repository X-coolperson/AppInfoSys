package cn.appsys.service.appinfo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.appinfo.AppInfoMapper;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.QueryAppInfoVO;
import cn.appsys.util.PageBean;

@Service
@Transactional
public class AppInfoServiceImpl implements AppInfoService {

	@Resource
	private AppInfoMapper appInfoMapper;
	
	/* 保存修改信息
	 * @see cn.appsys.service.appinfo.AppInfoService#uploadAppInfo(cn.appsys.pojo.AppInfo)
	 */
	@Override
	public void uploadAppInfo(AppInfo appInfo) {
		appInfoMapper.uploadAppInfo(appInfo);
		
	}

	/* 根据id查询
	 * @see cn.appsys.service.appinfo.AppInfoService#getInfoById(java.lang.Integer)
	 */
	@Override
	public AppInfo getInfoById(Integer id) {
		return appInfoMapper.getInfoById(id);
	}

	@Override
	public void getAppInfoList(PageBean<AppInfo> pageBean,QueryAppInfoVO queryAppInfoVO) {

		int totalCount = appInfoMapper.getAppInfoCount(queryAppInfoVO);
		pageBean.setTotalCount(totalCount);
		
		queryAppInfoVO.setStartIndex(pageBean.getStartIndex());
		queryAppInfoVO.setPageSize(pageBean.getPageSize());
		List<AppInfo> result = appInfoMapper.getAppInfoList(queryAppInfoVO);
		pageBean.setResult(result);
	}
	
	
	
}
