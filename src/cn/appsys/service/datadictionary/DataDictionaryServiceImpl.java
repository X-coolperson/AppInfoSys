package cn.appsys.service.datadictionary;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.appinfo.AppInfoMapper;
import cn.appsys.dao.datadictionary.DataDictionaryMapper;
import cn.appsys.pojo.DataDictionary;

@Service
@Transactional
public class DataDictionaryServiceImpl implements DataDictionaryService {

	@Resource
	private DataDictionaryMapper dataDictionaryMapper;
	@Resource
	private AppInfoMapper  appInfoMapper;
	

	@Override
	public List<DataDictionary> getDataDictionaryListByTypeCode(String typeCode) {
		return dataDictionaryMapper.getDataDictionaryListByTypeCode(typeCode);
	}


	@Override
	public boolean upDataDictionaryByvalueId(Integer appdataId,Integer appinfoid, String  saleSwitch) {
		boolean flag = false;
		Integer valueId = 0;
		String  valueName = "";
		if(saleSwitch.equals("close")) {
			valueId = 5;
			valueName = "已下架";
		}else if(saleSwitch.equals("open")) {
			valueId = 4;
			valueName = "已上架";
		}
		if(dataDictionaryMapper.upDataDictionaryByvalueId(appdataId,valueId,valueName) > 0){
			flag  = true;
		}
		appInfoMapper.updateStatusByInfoId(appinfoid,valueId);
		return flag;
	}

	
}
