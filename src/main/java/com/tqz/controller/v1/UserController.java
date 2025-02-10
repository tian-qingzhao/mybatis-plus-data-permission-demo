package com.tqz.controller.v1;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tqz.datapermission.v1.core.entity.User;
import com.tqz.datapermission.v1.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tian
 * @since 2019-12-28
 */
@Api(value = "用户接口。", tags = "用户接口")
@RequestMapping("/user")
@Slf4j
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "分页", notes = "分页")
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public IPage<User> page(@RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询所有的接口执行了");
        Page<User> page = userService.page(new Page<>(pageNumber, pageSize));
        log.info("用户分页查询结果：{}", page);
        return page;
    }

}
