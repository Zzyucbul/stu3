package com.fc.v2.controller.gen;

import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.model.custom.Tablepar;
import com.fc.v2.model.auto.Major;
import com.fc.v2.model.auto.TsysUser;
import com.fc.v2.service.MajorService;
import com.fc.v2.util.ShiorUtil;
import com.fc.v2.util.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Controller
 * @ClassName: MajorController
 * @author fuce
 * @date 2023-11-08 18:02:57
 */
@Api(value = "")
@Controller
@RequestMapping("/MajorController")
public class MajorController extends BaseController{
	
	private String prefix = "gen/major";
	
	@Autowired
	private MajorService majorService;
	
	
	/**
	 * 页面展示
	 * @param model
	 * @return String
	 * @author fuce
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@RequiresPermissions("gen:major:view")
    public String view(ModelMap model)
    {
        return prefix + "/list";
    }
	
	/**
	 * list集合
	 * @param tablepar
	 * @param searchText
	 * @return
	 */
	//@Log(title = "", action = "111")
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/list")
	@RequiresPermissions("gen:major:list")
	@ResponseBody
	public ResultTable list(Tablepar tablepar,Major major){
		PageInfo<Major> page=majorService.list(tablepar,major) ; 
		return pageTable(page.getList(),page.getTotal());
	}
	
	/**
     * 新增跳转
     */
	@ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        return prefix + "/add";
    }
	
    /**
     * 新增保存
     * @param 
     * @return
     */
	//@Log(title = "新增", action = "111")
	@ApiOperation(value = "新增", notes = "新增")
	@PostMapping("/add")
	@RequiresPermissions("gen:major:add")
	@ResponseBody
	public AjaxResult add(Major major){
		int b=majorService.insertSelective(major);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	//@Log(title = "删除", action = "111")
	@ApiOperation(value = "删除", notes = "删除")
	@DeleteMapping("/remove")
	@RequiresPermissions("gen:major:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=majorService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error("专业已经被引用无法删除");
		}
	}
	
	
	/**
	 * 修改跳转
	 * @param id
	 * @param mmap
	 * @return
	 */
	@ApiOperation(value = "修改跳转", notes = "修改跳转")
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap map)
    {
        map.put("Major", majorService.selectByPrimaryKey(id));

        return prefix + "/edit";
    }
	
	/**
     * 修改保存
     */
    //@Log(title = "修改", action = "111")
	@ApiOperation(value = "修改保存", notes = "修改保存")
    @RequiresPermissions("gen:major:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Major major)
    {
        return toAjax(majorService.updateByPrimaryKeySelective(major));
    }
    
    
    /**
	 * 修改状态
	 * @param record
	 * @return
	 */
    @PutMapping("/updateVisible")
	@ResponseBody
    public AjaxResult updateVisible(@RequestBody Major major){
		int i=majorService.updateVisible(major);
		return toAjax(i);
	}

    
    

	
}
