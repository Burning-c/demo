package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 故障逆变器信息表(FaultInverter)实体类
 *
 * @author makejava
 * @since 2022-01-19 10:00:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaultInverter implements Serializable {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 派单号
     */
    private String orderNo;

    /**
     * 逆变器sn
     */
    private String inverterSn;

    /**
     * 逆变器功率
     */
    private Integer inverterPower;

    /**
     * 逆变器维保截止时间
     */
    private Date inverterMaintainEndDate;

}