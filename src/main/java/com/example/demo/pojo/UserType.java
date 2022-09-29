package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author haojie.cui
 * @since 2021/12/27 14:16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserType implements Serializable, Cloneable {

    private int code;
    private String value;
    private User user;

    @Override
    public UserType clone() {
        try {
            return (UserType) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
