package cn.appsys.service.backendUser;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appsys.dao.backendUser.BackendUserMapper;
import cn.appsys.pojo.BackendUser;

@Service
public class BackendUserServiceImpl implements BackendUserService{
	@Resource
	private  BackendUserMapper  backendUserMapper;

	@Override
	public BackendUser login(String userCode, String userPassword) {
		BackendUser  backendUser  = backendUserMapper.getBackendUserByuserCode(userCode);
		if(backendUser !=  null) {
			if(! backendUser.getUserPassword().equals(userPassword)) {
				backendUser = null;
			}
		}
		return   backendUser;
	}
	
	
	
	
}
