package com.tqz.controller.v2;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.tqz.datapermission.v2.core.LoginHelper;
import com.tqz.datapermission.v2.core.LoginUser;
import com.tqz.datapermission.v2.core.LoginVo;
import com.tqz.datapermission.v2.core.R;
import com.tqz.datapermission.v2.core.dto.RoleDTO;
import com.tqz.datapermission.v2.core.enums.DataScopeType;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 数据权限v2版本
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2025/2/10 13:57
 */
@Api(value = "用户接口。", tags = "用户接口")
@RequestMapping("/sa-token")
@Slf4j
@ResponseBody
public class SaTokenLoginController {

    @RequestMapping("login")
    public R<LoginVo> login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {

        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId("1L");
        loginUser.setUserId(4L);
        loginUser.setDeptId(102L);
        loginUser.setDeptCategory("000001");
        loginUser.setDeptName("软件开发部");
        loginUser.setToken("tqz");
        loginUser.setUserType("admin");
        loginUser.setLoginTime(new Date().getTime());
        loginUser.setIpaddr("127.0.0.1");
        loginUser.setLoginLocation("郑州");
        loginUser.setBrowser("IE");
        loginUser.setOs("IOS");

        Set<String> menuIds = new HashSet<>();
        menuIds.add("demo:demo:list");
        menuIds.add("demo:demo:query");
        menuIds.add("demo:demo:add");
        loginUser.setMenuPermission(menuIds);

        Set<String> roleIds = new HashSet<>();
        roleIds.add("rokeKey1");
        roleIds.add("rokeKey2");
        loginUser.setRolePermission(roleIds);

        loginUser.setUsername(username);
        loginUser.setNickname("田青钊");

        List<RoleDTO> roles = new ArrayList<>();
        RoleDTO roleDTO1 = new RoleDTO(1L, "工厂管理员", "rokeKey1", DataScopeType.DEPT_AND_CHILD_OR_SELF.getCode());
        roles.add(roleDTO1);
        loginUser.setRoles(roles);

        loginUser.setClientKey("client-key-1");
        loginUser.setDeviceType("device-type-1");

        SaLoginModel model = new SaLoginModel();
        model.setDevice("iPhone13Pro");
        // 自定义分配 不同用户体系 不同 token 授权时间 不设置默认走全局 yml 配置
        // 例如: 后台用户30分钟过期 app用户1天过期
        model.setTimeout(3600);
        model.setActiveTimeout(60);
        model.setExtra(LoginHelper.CLIENT_KEY, "web");

        LoginHelper.login(loginUser, model);

        LoginVo loginVo = new LoginVo();
        loginVo.setAccessToken(StpUtil.getTokenValue());
        loginVo.setExpireIn(StpUtil.getTokenTimeout());
        loginVo.setClientId("web");

        return R.ok(loginVo);
    }

    @RequestMapping("get-user-info")
    public R<LoginUser> getUserInfo() {
        return R.ok(LoginHelper.getLoginUser());
    }
}
