package com.petterp.latte_ec.litepaldemo;

import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Petterp on 2019/6/22
 * Summary:简单栗子-> 书架与书
 * 与Book表关联，多对一关系
 * 邮箱：1509492795@qq.com
 */
public class BookRack extends LitePalSupport {
    private String belong;
    private String name;
    //与Book存在多对一关系  写法如下
    private List<Book> books=new ArrayList<>();

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
