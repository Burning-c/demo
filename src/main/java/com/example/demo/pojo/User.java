package com.example.demo.pojo;

import cn.hutool.core.clone.Cloneable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author haojie.cui
 * @since 2021/12/23 14:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户实体")
public class User implements Cloneable<User>,Serializable{
    @NotBlank
    @Length(min = 2, max = 26)
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "用户类型")
    private Integer userType;
    /**
     *  比率
     */
    private BigDecimal ratio;
    /**
     *  数量
     */
    private Integer count;

    public User(String userName, int userType) {
        this.userName = userName;
        this.userType = userType;
    }

    @Override
    public User clone(){
        try {
            return (User)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
