package com.example.demo.dto;

import cn.hutool.core.collection.CollectionUtil;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 结算报表列表结果DTO
 *
 * @author haojie.cui
 * @since 2022/3/3 10:54
 */
@Data
@ApiModel("结算报表列表结果DTO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettleReportFormResultDTO implements Serializable {

    /**
     * 行合计(应结算-工单额)
     */
    private BigDecimal shouldSettleAmountSum;

    /**
     * 行合计(应结算-工单量)
     */
    private Long shouldSettleCountSum;



}