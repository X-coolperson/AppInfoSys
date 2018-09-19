package cn.appsys.service.appversionService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;


import cn.appsys.dao.appversion.AppVersionMapper;
import cn.appsys.pojo.AppVersion;

@Service
public  class AppVersionServiceImpl implements AppVersionService {
	@Resource
	private  AppVersionMapper  appVersionMapper;

	
	@Override
	public List<AppVersion> getAppVersionListById(Integer id) {
		
		return appVersionMapper.getAppVersionListById(id);
	}
	@Override
	public boolean updateVersionInfo(Integer id,double versionSize, String versionInfo) {
		boolean  flag = false;
		if(appVersionMapper.updateVersionInfo(id,versionSize,versionInfo)> 0) {
			flag = true;
		}
		return flag;
	}



	

	
	
}
