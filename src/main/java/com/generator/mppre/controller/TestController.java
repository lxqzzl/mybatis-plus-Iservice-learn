package com.generator.mppre.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.generator.mppre.service.TestService;
import com.generator.mppre.dao.TableDao;
import com.generator.mppre.entity.TestDO;

import io.swagger.annotations.Api;

/**
 * @Description TestController前端控制器
 * @author lxq
 * @since 2020-09-20 18:19:08
 */

@RestController
@RequestMapping("${global.version}/tests")
@Api(tags = "user_test表操作API")
public class TestController {

	private TestService testService;
	
	

	@Autowired
	public TestController(@Qualifier("TestService") TestService testService) {
		this.testService = testService;
	}	
	
	@Autowired
	private TableDao tableDao;
	
	// <以下IService接口为测试------------------------------------------------------------->
	// <插入数据部分--------------------------------->
	/**
	 * 保存(单个)
	 */
	@PostMapping("/savesingle")
	public void saveSingle(@RequestBody TestDO testDO) {
		testService.save(testDO);
	}

	/**
	 * 保存(批量)
	 */
	@PostMapping("/save")
	public void save(@RequestBody List<TestDO> testDOs) {
		testService.saveBatch(testDOs);
	}
	
	/**
	 * 保存(批量)
	 */
	@PostMapping("/savelimit")
	public void saveLimit(@RequestBody List<TestDO> testDOs) {
		testService.saveBatch(testDOs, 2);
	}

	/**
	 * 更新，若没有就保存(单个)
	 */
	@PostMapping("/saveorupdatesingle")
	public void saveOrUpdateSingle(@RequestBody TestDO testDO) {
		testService.saveOrUpdate(testDO);
	}

	/**
	 * 更新(批量)
	 */
	@PostMapping("/saveorupdate")
	public void saveOrUpdate(@RequestBody List<TestDO> testDOs) {
		testService.saveOrUpdateBatch(testDOs);
	}

	// <获取一条数据部分--------------------------------->
	/**
	 * 根据id获取一条数据
	 */
	@GetMapping("/get/{id}")
	public TestDO getById(@PathVariable Long id) {
		return testService.getById(id);
	}

	/**
	 * 获取map
	 */
	@GetMapping("/getmap")
	public Map<String, Object> getMap() {
		QueryWrapper<TestDO> queryWrapper = new QueryWrapper<TestDO>();
		queryWrapper.eq("test_age", 12);
		return testService.getMap(queryWrapper);
	}

	/**
	 * 获取一条数据
	 */
	@GetMapping("/getone")
	public TestDO getOne() {
		QueryWrapper<TestDO> queryWrapper = new QueryWrapper<TestDO>();
		queryWrapper.eq("test_age", 12);
		return testService.getOne(queryWrapper);
	}

	/**
	 * 获取一条数据（抛出异常）
	 */
	@GetMapping("/getonethrowe")
	public TestDO getOneThrowE() {
		QueryWrapper<TestDO> queryWrapper = new QueryWrapper<TestDO>();
		queryWrapper.eq("test_age", 12);
		return testService.getOne(queryWrapper, true);
	}

	/**
	 * 获取obj
	 */
	@GetMapping("/getobj")
	public Object getObj() {
		QueryWrapper<TestDO> queryWrapper = new QueryWrapper<TestDO>();
		queryWrapper.eq("test_age", 12);
		return testService.getObj(queryWrapper);
	}

	// <获取多条数据部分--------------------------------->
	// <列表----------------------------------------->
	/**
	 * 获取指定条件的多条数据
	 */
	@GetMapping("/list")
	public List<TestDO> list() {
		QueryWrapper<TestDO> queryWrapper = new QueryWrapper<TestDO>();
		queryWrapper.eq("test_age", 12);
		return testService.list(queryWrapper);
	}
	
	/**
	 * 根据id获取多条数据
	 */
	@GetMapping("/listbyid")
	public List<TestDO> listById(@RequestParam(value = "ids", required = true) String idsString) {	
        idsString = "["+idsString+"]";   
		List<String> ids = Arrays.asList(idsString.split(","));
		return (List<TestDO>) testService.listByIds(ids);
	}
	
	/**
	 * 获取指定条件map的多条数据
	 */
	@GetMapping("/listbymap")
	public List<TestDO> listByMap() {
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("test_name", "测试名字-3");
		return (List<TestDO>) testService.listByMap(columnMap);
	}
	
	/**
	 * 获取指定条件的多条数据map
	 */
	@GetMapping("/listmap")
	public List<Map<String, Object>> listMapByQuery() {
		QueryWrapper<TestDO> queryWrapper = new QueryWrapper<TestDO>();
		queryWrapper.eq("test_age", 12);
		return testService.listMaps(queryWrapper);
	}
	
	/**
	 * 获取指定条件的多条数据
	 */
	@GetMapping("/listObj")
	public List<Object> listObj() {
		QueryWrapper<TestDO> queryWrapper = new QueryWrapper<TestDO>();
		queryWrapper.eq("test_age", 12);
		return testService.listObjs(queryWrapper);
	}
	
	// <分页----------------------------------------->
	/**
	 * 分页获取指定条件的多条数据
	 */
	@GetMapping("/page")
	public IPage<TestDO> page() {
		QueryWrapper<TestDO> queryWrapper = new QueryWrapper<TestDO>();
		queryWrapper.eq("test_age", 12);
		Page<TestDO> page = new Page<>(Long.valueOf("1"), Long.valueOf("10"));
		return testService.page(page, queryWrapper);
	}
	
	/**
	 * 分页获取指定条件的多条数据map
	 */
	@GetMapping("/pagemap")
	public IPage<Map<String, Object>> pageMap() {
		QueryWrapper<TestDO> queryWrapper = new QueryWrapper<TestDO>();
		queryWrapper.eq("test_age", 12);
		Page<TestDO> page = new Page<>(Long.valueOf("1"), Long.valueOf("10"));
		return testService.pageMaps(page, queryWrapper);
	}
	
	// <更新数据部分--------------------------------->
	/**
	 * 跟新数据 （通过Query条件）
	 */
	@PatchMapping("/update")
	public Boolean update(@RequestBody TestDO testDO) {
		QueryWrapper<TestDO> queryWrapper = new QueryWrapper<TestDO>();
		queryWrapper.eq("test_age", 13);
		return testService.update(testDO, queryWrapper);
	}	
	
	/**
	 * 批量更新
	 */
	@PatchMapping("/updatemany")
	public Boolean updateMany(@RequestBody List<TestDO> testDOs) {
		return testService.updateBatchById(testDOs);
	}
	
	/**
	 * 根据id更新
	 */
	@PatchMapping("/updatebyid")
	public Object updateById(@RequestBody TestDO testDO) {
		return testService.updateById(testDO);
	}

	// <删除数据部分--------------------------------->
	/**
	 * 根据id删除一条数据
	 */
	@DeleteMapping("/delete/{id}")
	public Boolean deletebyid(@PathVariable Long id) {
		return testService.removeById(id);
	}
	
	/**
	 * 根据id批量删除
	 */
	@DeleteMapping("/deleteids")
	public Object deleteIds(@RequestBody List<Long> ids) {
		return testService.removeByIds(ids);
	}
	
	/**
	 * 删除数据 byQuery
	 */
	@DeleteMapping("/deletebyquery")
	public Boolean deleteByQuery() {
		QueryWrapper<TestDO> queryWrapper = new QueryWrapper<TestDO>();
		queryWrapper.eq("test_name", "测试名字-4");
		return testService.remove(queryWrapper);
	}
	
	/**
	 * 删除数据，byMap
	 */
	@DeleteMapping("/deletebymap")
	public Object deleteByMap() {
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("test_name", "测试名字-3");
		return testService.removeByMap(columnMap);
	}
	
	// <其他部分---------------------------------> 
	/**
	 * 根据id获取一条数据
	 */
	@GetMapping("/count")
	public int getCount() {
		QueryWrapper<TestDO> queryWrapper = new QueryWrapper<TestDO>();
		queryWrapper.eq("test_name", "测试名字-3");
		return testService.count(queryWrapper);
	}
	
    @GetMapping("/table/list")
    public List<Map<String, Object>> list(String id) {       
        return tableDao.listTable();
    }
    @RequestMapping("/table/columns")
    public List<Map<String, Object>> info(String tableName) {
        return tableDao.listTableColumn(tableName);
    }
	
}
