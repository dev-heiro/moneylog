package org.codenova.moneylog.entity;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Category {
    private int id;
    private String name;
    private int sort;
}
