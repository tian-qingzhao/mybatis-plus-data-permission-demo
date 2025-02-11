package com.tqz.datapermission.v2.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 岗位
 *
 * @author AprilWind
 */
@Data
@NoArgsConstructor
public class PostDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    private Long postId;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 岗位类别编码
     */
    private String postCategory;

}
