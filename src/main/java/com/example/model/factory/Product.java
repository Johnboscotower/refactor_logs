package com.example.model.factory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public abstract class Product {

    //ID cannot be changed.
    @Setter(AccessLevel.NONE)
    private int id;

    private String name;
    private Double price;
    private String type;

    protected Product(int id) {
        this.id = id;
    }

    public abstract String[] getAttributes();

    public abstract String[] getAttributesHeader();

    @Override
    public String toString() {
        return "Product {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
