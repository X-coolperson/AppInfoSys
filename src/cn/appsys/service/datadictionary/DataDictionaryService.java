package cn.appsys.service.datadictionary;

import java.util.List;

import cn.appsys.pojo.DataDictionary;

public interface DataDictionaryService {
	/**
	 * 
	 * 修改上下架
	 * @param valueId
	 * @param valueName
	 * @return
	 */
	boolean upDataDictionaryByvalueId(Integer appdataId,Integer appinfoid, String  saleSwitch);

	/**
	 * 根据类型编码查询数据字典
	 * @param typeCode
	 * @return
	 */
	List<DataDictionary> getDataDictionaryListByTypeCode(String typeCode);

	

}
