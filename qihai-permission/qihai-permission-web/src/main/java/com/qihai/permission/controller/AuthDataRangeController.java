package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qihai.commerce.framework.exception.BaseException;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.dto.AuthDataRangeDTO;
import com.qihai.permission.entity.AuthDataRangeEntity;
import com.qihai.permission.service.AuthDataRangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@Api("数据范围")
@RestController
@RequestMapping("permission/authdatarange")
public class AuthDataRangeController {
    @Autowired
    private AuthDataRangeService authDataRangeService;

    /**
     * 列表
     */
    @ApiOperation(value = "分页查询数据范围",httpMethod ="POST", response = AuthDataRangeEntity.class,notes = "返回查询结果集以及分页")
    @ApiImplicitParam(name = "params", value = "分页请求参数，请放到请求url路径后用page=1&limit=10来表示请求第1页，每页显示10条，此值为默认", required = false)
    @PostMapping("/list")
    @RequiresPermissions("permission:authdatarange:list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params,@RequestBody(required=false) AuthDataRangeEntity authDataRange){
        PageUtils page = authDataRangeService.queryPage(params,authDataRange);

        return new R<PageUtils>().ok(page);
    }
    
    @ApiOperation(value = "查询所有的数据范围",httpMethod ="GET", response = List.class,notes = "返回查询结果集以及分页")
    @ApiImplicitParam(name = "params", value = "分页请求参数，请放到请求url路径后用page=1&limit=10来表示请求第1页，每页显示10条，此值为默认", required = false)
    @GetMapping("/listAll")
    @RequiresPermissions("permission:authdatarange:listAll")
    public R<List<AuthDataRangeEntity>> listAll(){
    	List<AuthDataRangeEntity> list = authDataRangeService.selectList(new EntityWrapper<>());
        return new R<List<AuthDataRangeEntity>>().ok(list);
    }

    /**
     * 信息
     */
    @ApiOperation(value = "按id查询数据范围",httpMethod ="GET", response = R.class,notes = "返回查询结果集以及分页")
    @ApiImplicitParam(name = "id", value = "数据范围的id",dataType="Long",paramType="path", required = true)
    @GetMapping("/info/{id}")
    @RequiresPermissions("permission:authdatarange:info")
    public R<AuthDataRangeDTO> info(@PathVariable("id") Long id){
    	
    	//return new AuthDataRangeDTO();
    	
    	AuthDataRangeDTO authDataRangeDTO = authDataRangeService.selectDataRangeById(id);

        return new R<AuthDataRangeDTO>().ok(authDataRangeDTO);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "添加数据范围或修改数据范围",httpMethod ="POST",response =R.class)
    @PostMapping("/saveOrUpdate")
    @RequiresPermissions("permission:authdatarange:save")
    public R<Object> saveOrUpdate(@RequestBody AuthDataRangeDTO authDataRangeDTO){
    	ValidatorUtils.validateEntity(authDataRangeDTO);
        authDataRangeService.saveOrUpdate(authDataRangeDTO);
        return new R<Object>().ok(null);
    }

	// /**
	// * 修改
	// */
	// @ApiOperation(value = "修改数据范围",httpMethod ="POST",response =R.class)
	// @PostMapping("/update")
	// @RequiresPermissions("permission:authdatarange:update")
	// public R<Object> update(@RequestBody AuthDataRangeEntity authDataRange){
	// if(authDataRange==null||authDataRange.getId()==null) {
	// throw new BaseException("修改时id为必传参数");
	// }
	// ValidatorUtils.validateEntity(authDataRange);
	// authDataRangeService.updateById(authDataRange);
	// return new R<Object>().ok(null);
	// }

    /**
     * 删除
     */
    @ApiOperation(value = "按id删除数据范围信息，逻辑删除",httpMethod ="POST",response=R.class)
    @ApiImplicitParam(name = "ids", value = "数据范围ID数组，批量删除", required = true, dataType = "Long[]")
    @PostMapping("/delete")
    @RequiresPermissions("permission:authdatarange:delete")
    public R<Object> delete(@RequestBody Long[] ids){
    	if (ids == null || ids.length == 0) {
			throw new BaseException("请传入需要删除的数据的id");
		}
        authDataRangeService.deleteBatchIds(Arrays.asList(ids));
        return new R<Object>().ok(null);
    }

}
