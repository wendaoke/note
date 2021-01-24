package com.very.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Select {
    private Integer value;
    private String name;

    public Select(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
