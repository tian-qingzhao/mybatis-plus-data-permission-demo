package com.tqz.controller.v1;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tqz.entity.Dept;
import com.tqz.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tian
 * @since 2019-12-28
 */
@Api(value = "部门接口。", tags = "部门接口")
@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ApiOperation(value = "分页", notes = "分页")
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public IPage<Dept> page(@RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询所有的接口执行了");

        return deptService.page(new Page<>(pageNumber, pageSize));
    }

}
