package com.example.test1.other;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 员工实体模型
 *
 * @author JustryDeng
 * @date 2019/7/15 19:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Staff implements Serializable {

    /** 姓名 */
    private String name;

    /** 年龄 */
    private Integer age;

    /** 工号 */
    private String staffNo;

}
