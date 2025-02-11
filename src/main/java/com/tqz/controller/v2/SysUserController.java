package com.tqz.controller.v2;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.tqz.datapermission.v2.core.bo.SysUserBo;
import com.tqz.datapermission.v2.core.mybatisplus.page.PageQuery;
import com.tqz.datapermission.v2.core.mybatisplus.page.TableDataInfo;
import com.tqz.datapermission.v2.core.service.SysUserServiceImpl;
import com.tqz.datapermission.v2.core.vo.SysUserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 用户信息
 *
 * @author Lion Li
 */
@ResponseBody
@RequestMapping("/system/user")
public class SysUserController {

    @Resource
    private SysUserServiceImpl userService;

    /**
     * 获取用户列表
     */
    @SaCheckPermission("system:user:page")
    @GetMapping("/page")
    public TableDataInfo<SysUserVo> list(SysUserBo user, PageQuery pageQuery) {
        return userService.selectPageUserList(user, pageQuery);
    }

}
