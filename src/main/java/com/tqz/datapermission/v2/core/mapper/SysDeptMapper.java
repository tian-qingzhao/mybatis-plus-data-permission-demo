package com.tqz.datapermission.v2.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tqz.datapermission.v2.core.entity.SysDept;
import com.tqz.datapermission.v2.core.vo.SysDeptVo;
import com.tqz.datapermission.v2.core.annotation.DataColumn;
import com.tqz.datapermission.v2.core.annotation.DataPermission;
import com.tqz.datapermission.v2.core.helper.DataBaseHelper;
import com.tqz.datapermission.v2.core.mybatisplus.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门管理 数据层
 *
 * @author Lion Li
 */
public interface SysDeptMapper extends BaseMapperPlus<SysDept, SysDeptVo> {

    /**
     * 查询部门管理数据
     *
     * @param queryWrapper 查询条件
     * @return 部门信息集合
     */
    @DataPermission({
        @DataColumn(key = "deptName", value = "dept_id")
    })
    List<SysDeptVo> selectDeptList(@Param(Constants.WRAPPER) Wrapper<SysDept> queryWrapper);

    /**
     * 分页查询部门管理数据
     *
     * @param queryWrapper 查询条件
     * @return 部门信息集合
     */
    @DataPermission({
        @DataColumn(key = "deptName", value = "dept_id"),
    })
    Page<SysDeptVo> selectPageDeptList(@Param("page") Page<SysDeptVo> page, @Param(Constants.WRAPPER) Wrapper<SysDept> queryWrapper);

    /**
     * 统计指定部门ID的部门数量
     *
     * @param deptId 部门ID
     * @return 该部门ID的部门数量
     */
    @DataPermission({
        @DataColumn(key = "deptName", value = "dept_id")
    })
    long countDeptById(Long deptId);

    /**
     * 根据父部门ID查询其所有子部门的列表
     *
     * @param parentId 父部门ID
     * @return 包含子部门的列表
     */
    default List<SysDept> selectListByParentId(Long parentId) {
        return this.selectList(new LambdaQueryWrapper<SysDept>()
            .select(SysDept::getDeptId)
            .apply(DataBaseHelper.findInSet(parentId, "ancestors")));
    }

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId            角色ID
     * @param deptCheckStrictly 部门树选择项是否关联显示
     * @return 选中部门列表
     */
    List<Long> selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);

}
