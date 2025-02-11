package com.tqz.datapermission.v2.core.vo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息视图对象 sys_role
 *
 * @author Michelle.Chung
 */
@Data
public class SysRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 显示顺序
     */
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    private String dataScope;

    /**
     * 菜单树选择项是否关联显示
     */
    private Boolean menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示
     */
    private Boolean deptCheckStrictly;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户是否存在此角色标识 默认不存在
     */
    private boolean flag = false;

    Long SUPER_ADMIN_ID = 1L;

    public boolean isSuperAdmin() {
        return SUPER_ADMIN_ID.equals(this.roleId);
    }

}
