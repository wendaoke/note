package com.very.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TodoList {
    private int id;
    private int categoryId;
    private String categoryName;
    private String title;
    private String content;
    private String director;
    private Date deadline;
    private int quadrant;
    private String remark;

}
