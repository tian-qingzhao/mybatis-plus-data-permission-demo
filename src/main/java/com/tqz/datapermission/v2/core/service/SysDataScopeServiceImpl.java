package com.tqz.datapermission.v2.core.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tqz.datapermission.v2.core.entity.SysDept;
import com.tqz.datapermission.v2.core.entity.SysRoleDept;
import com.tqz.datapermission.v2.core.mapper.SysDeptMapper;
import com.tqz.datapermission.v2.core.mapper.SysRoleDeptMapper;
import com.tqz.util.StreamUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据权限 实现
 * <p>
 * 注意: 此Service内不允许调用标注`数据权限`注解的方法
 * 例如: deptMapper.selectList 此 selectList 方法标注了`数据权限`注解 会出现循环解析的问题
 *
 * @author Lion Li
 */
public class SysDataScopeServiceImpl {

    @Resource
    private SysRoleDeptMapper roleDeptMapper;

    @Resource
    private SysDeptMapper deptMapper;

    /**
     * 获取角色自定义权限
     *
     * @param roleId 角色Id
     * @return 部门Id组
     */
    public String getRoleCustom(Long roleId) {
        if (ObjectUtil.isNull(roleId)) {
            return "-1";
        }
        List<SysRoleDept> list = roleDeptMapper.selectList(
                new LambdaQueryWrapper<SysRoleDept>()
                        .select(SysRoleDept::getDeptId)
                        .eq(SysRoleDept::getRoleId, roleId));
        if (CollUtil.isNotEmpty(list)) {
            return StreamUtils.join(list, rd -> Convert.toStr(rd.getDeptId()));
        }
        return "-1";
    }

    /**
     * 获取部门及以下权限
     *
     * @param deptId 部门Id
     * @return 部门Id组
     */
    public String getDeptAndChild(Long deptId) {
        if (ObjectUtil.isNull(deptId)) {
            return "-1";
        }
        List<SysDept> deptList = deptMapper.selectListByParentId(deptId);
        List<Long> ids = StreamUtils.toList(deptList, SysDept::getDeptId);
        ids.add(deptId);
        if (CollUtil.isNotEmpty(ids)) {
            return StreamUtils.join(ids, Convert::toStr);
        }
        return "-1";
    }

}
