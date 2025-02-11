package com.tqz.datapermission.v2.core.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tqz.datapermission.v2.core.bo.SysUserBo;
import com.tqz.datapermission.v2.core.entity.SysDept;
import com.tqz.datapermission.v2.core.entity.SysUser;
import com.tqz.datapermission.v2.core.mapper.SysDeptMapper;
import com.tqz.datapermission.v2.core.mapper.SysUserMapper;
import com.tqz.datapermission.v2.core.mybatisplus.page.PageQuery;
import com.tqz.datapermission.v2.core.mybatisplus.page.TableDataInfo;
import com.tqz.datapermission.v2.core.vo.SysUserVo;
import com.tqz.util.StreamUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户 业务层处理
 *
 * @author Lion Li
 */
@Slf4j
public class SysUserServiceImpl {

    @Resource
    private SysUserMapper baseMapper;

    @Resource
    private SysDeptMapper deptMapper;

    public TableDataInfo<SysUserVo> selectPageUserList(SysUserBo user, PageQuery pageQuery) {
        Page<SysUserVo> page = baseMapper.selectPageUserList(pageQuery.build(), this.buildQueryWrapper(user));
        return TableDataInfo.build(page);
    }


    private Wrapper<SysUser> buildQueryWrapper(SysUserBo user) {
        Map<String, Object> params = user.getParams();
        QueryWrapper<SysUser> wrapper = Wrappers.query();
        wrapper.eq("u.del_flag", "0")
                .eq(ObjectUtil.isNotNull(user.getUserId()), "u.user_id", user.getUserId())
                .like(StringUtils.isNotBlank(user.getUserName()), "u.user_name", user.getUserName())
                .eq(StringUtils.isNotBlank(user.getStatus()), "u.status", user.getStatus())
                .like(StringUtils.isNotBlank(user.getPhonenumber()), "u.phonenumber", user.getPhonenumber())
                .between(params.get("beginTime") != null && params.get("endTime") != null,
                        "u.create_time", params.get("beginTime"), params.get("endTime"))
                .and(ObjectUtil.isNotNull(user.getDeptId()), w -> {
                    List<SysDept> deptList = deptMapper.selectListByParentId(user.getDeptId());
                    List<Long> ids = StreamUtils.toList(deptList, SysDept::getDeptId);
                    ids.add(user.getDeptId());
                    w.in("u.dept_id", ids);
                }).orderByAsc("u.user_id");
        return wrapper;
    }

}
