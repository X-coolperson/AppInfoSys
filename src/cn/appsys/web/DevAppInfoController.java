package cn.appsys.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.appsys.pojo.AppCategory;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.AppVersion;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.pojo.QueryAppInfoVO;
import cn.appsys.service.appcategory.AppCategoryService;
import cn.appsys.service.appinfo.AppInfoService;
import cn.appsys.service.appversionService.AppVersionService;
import cn.appsys.service.datadictionary.DataDictionaryService;
import cn.appsys.util.PageBean;

@Controller
@RequestMapping("/dev/app")
public class DevAppInfoController {
	
	@Resource
	private AppInfoService appInfoService;
	@Resource
	private DataDictionaryService dataDictionaryService;
	@Resource
	private AppCategoryService appCategoryService;
	@Resource
	private AppVersionService  appVersionService;
	
	
	/**
	 * 
	 * 修改版本信息
	 */
	@RequestMapping("/updateVersion")
	public String  updateVersion(Model  model,@RequestParam Integer id,@RequestParam double versionSize,@RequestParam String   versionInfo) {
		boolean appVersionInfoList = appVersionService.updateVersionInfo(id,versionSize,versionInfo);
		return "redirect:/dev/app/list";
	}
	
	/**
	 * 
	 * 版本信息修改页面的跳转
	 * @param model
	 * @param id
	 * @param versionInfo
	 * @return
	 */
	@RequestMapping("/appversionmodify")
	public  String  modityversion(Model  model, @RequestParam Integer id) { 
		List<AppVersion>  appVersionList  = appVersionService.getAppVersionListById(id);
		model.addAttribute("appVersionList",appVersionList);
		model.addAttribute("id",id);
		return  "/developer/appversionmodify";
	}
	
	/**
	 * 上下架操作
	 * @param model
	 * @param appdataId
	 * @param appinfoid
	 * @param saleSwitch
	 * @return
	 */
	@RequestMapping("/odd/{appdataId}/{appinfoid}/{saleSwitch}")
	public  String  updateValueName(Model model,@PathVariable  Integer appdataId,@PathVariable Integer appinfoid, @PathVariable String  saleSwitch) {
		HashMap<String,Object>  hashMap = new  HashMap<>();
		boolean flag = dataDictionaryService.upDataDictionaryByvalueId(appdataId,appinfoid,saleSwitch);
		String  resultMsg = "success";
		if(!flag) {
			resultMsg = "failed";
		}
		hashMap.put("resultMsg", resultMsg);
		
		return JSON.toJSONString(hashMap); 
	}
	
	
	
	/**
	 * 三级联动 分类查询
	 * @param pid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getclist/{pid}")
	public String getCategoryList(@PathVariable Integer pid) {
		List<AppCategory> appCategoryList = appCategoryService.getAppCategoryListByParentId(pid);
		return JSON.toJSONString(appCategoryList);
	}
	
	@RequestMapping("/list")
	public String appList(Model model,@ModelAttribute QueryAppInfoVO queryAppInfoVO) {
		
		Integer currentPageNo = 1;
		if(queryAppInfoVO.getPageIndex() != null) {
			currentPageNo = queryAppInfoVO.getPageIndex();
		}
		Integer pageSize = 5;
		PageBean<AppInfo> pageBean = new PageBean<AppInfo>();
		pageBean.setCurrentPageNo(currentPageNo);
		pageBean.setPageSize(pageSize);
		
		appInfoService.getAppInfoList(pageBean,queryAppInfoVO);
		
		// 查询状态列表 statusList
		List<DataDictionary> statusList = dataDictionaryService.getDataDictionaryListByTypeCode("APP_STATUS");
		// 查询平台列表 flatFormList
		List<DataDictionary> flatFormList = dataDictionaryService.getDataDictionaryListByTypeCode("APP_FLATFORM");
		
		// 查询所有的1级分类 categoryLevel1List
		List<AppCategory> categoryLevel1List = appCategoryService.getAppCategoryListByParentId(null);
		
		// 完善分类的回显
		// 如果传了一级分类  说明你选择过  所以肯定触发过三级联动  认为应该将二级分类全部查询
		if(queryAppInfoVO.getQueryCategoryLevel1() != null) {
			List<AppCategory> categoryLevel2List = appCategoryService.getAppCategoryListByParentId(queryAppInfoVO.getQueryCategoryLevel1());
			model.addAttribute("categoryLevel2List", categoryLevel2List);
		}
		if(queryAppInfoVO.getQueryCategoryLevel2() != null) {
			List<AppCategory> categoryLevel3List = appCategoryService.getAppCategoryListByParentId(queryAppInfoVO.getQueryCategoryLevel2());
			model.addAttribute("categoryLevel3List", categoryLevel3List);
		}
		
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("statusList", statusList);
		model.addAttribute("flatFormList", flatFormList);
		model.addAttribute("categoryLevel1List", categoryLevel1List);
		model.addAttribute("queryAppInfoVO", queryAppInfoVO);
		return "developer/appinfolist";
	}
	
}
