package cn.appsys.dao.datadictionary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.DataDictionary;

public interface DataDictionaryMapper {

	/**
	 * 
	 * 根据id来实现上架
	 * @param valueId
	 * @param valueName
	 * @return
	 */
	int upDataDictionaryByvalueId(@Param("id")Integer appinfoid,@Param("valueId")Integer valueId, @Param("valueName")String valueName);
	/**
	 * 根据类型编号查询字典信息
	 * @param typeCode
	 * @return
	 */
	List<DataDictionary> getDataDictionaryListByTypeCode(@Param("typeCode")String typeCode);

	

}
