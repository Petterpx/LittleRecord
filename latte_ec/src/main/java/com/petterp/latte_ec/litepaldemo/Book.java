package com.petterp.latte_ec.litepaldemo;

import org.litepal.crud.LitePalSupport;

/**
 * @author Petterp on 2019/6/22
 * Summary:
 * -> 多表连接，与News存在关联,存在多对一
 * 邮箱：1509492795@qq.com
 */
public class Book extends LitePalSupport {
    private int age;
    private String bookname;
    private String belong;
    //与BookRack存在多对一关系，写法如下
    private BookRack bookRack;

    public Book(int age, String bookname, String belong) {
        this.age = age;
        this.bookname = bookname;
        this.belong = belong;
    }
    public Book(){}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public BookRack getBookRack() {
        return bookRack;
    }

    public void setBookRack(BookRack bookRack) {
        this.bookRack = bookRack;
    }
}
