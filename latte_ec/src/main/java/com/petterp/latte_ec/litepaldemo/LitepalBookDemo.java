package com.petterp.latte_ec.litepaldemo;

import android.util.Log;

import org.litepal.LitePal;

import java.util.List;

/**
 * @author Petterp on 2019/6/22
 * Summary:litepal简单demo,以免忘记了去查笔记
 * 定义数据类继承自DataSupport，在asstes中记得添加
 * 邮箱：1509492795@qq.com
 */
public class LitepalBookDemo {

    /**
     * 数据添加
     */
    private void add() {
        Book book1 = new Book(66, "Android进阶光", "IT");
        Book book2 = new Book(66, "第一行代码", "IT");
        Book book3 = new Book(66, "三国演义", "文学");
        book2.save();
        book3.save();

        BookRack bookRack1=new BookRack();
        bookRack1.setName("A1");
        //建立连接
        bookRack1.getBooks().add(book1);
        bookRack1.getBooks().add(book2);
        bookRack1.save();

        BookRack bookRack2 = new BookRack();
        bookRack2.getBooks().add(book3);
        bookRack2.setName("A3");
        bookRack2.setBelong("文学");
        bookRack2.save();
        //异步保存，数据库的操作为耗时，一般情况下，少量数据并不会有什么问题
//        book.saveAsync();
    }

    /**
     * 数据修改
     */
    private void update() {
        Book book = new Book();
        book.setBookname("Android");
        book.setAge(88);
        book.updateAll("name=? and age=?", "Android进阶之光", "66");

        //修改为默认值,没有条件的话，默认全部
        book.setToDefault("age");
        book.setToDefault("demo");
        book.updateAll();
    }

    /**
     * 数据删除
     */
    private void remove() {
        //删除Book表中，age大于999的字段
        LitePal.deleteAll(Book.class, "age>?", "999");
        //删除Book表中,id为1的字段
        LitePal.delete(Book.class, 1);
    }

    /**
     * 查询数据
     */
    private void query() {
        //查询全部数据
        List<Book> list = LitePal.findAll(Book.class);
        //findFirst() 查询表中第一条数据
        Book fritsBook = LitePal.findFirst(Book.class);
        //findLast() 查询表中最后一条数据
        Book lastBook = LitePal.findLast(Book.class);

        //select() 用于查询指定列的数据
        List<Book> books = LitePal.select("name", "age").find(Book.class);

        //where() 用于查询符合指定条件的数据
        List<Book> books1 = LitePal.where("age>?", "100").find(Book.class);

        //order() 用于指定查询数据的排列方式 -> 指定字段排列方式,desc表示降序,asc或者不写表示升序
        List<Book> booksOrder = LitePal.order("price desc").find(Book.class);

        //limit() 用于指定查询结果的数量   ->只查前三条数据
        List<Book> booklimit = LitePal.limit(3).find(Book.class);

        //offset() 用于指定查询结果的偏移量  ->从第二条数据开始，前三条数据
        List<Book> bookoffset = LitePal.limit(3).offset(1).find(Book.class);

        //方法连缀组合
        List<Book> booksgroup = LitePal
                .select("name")
                .order("age")
                .find(Book.class);

        //多表关联查询,查询关联表中的数据,Litepal不支持map,所以，多表查询只能重复遍历list达到效果
        //郭大神的建议是在getBooks()方法里再去查询，这样的话效率会更好
        BookRack bookRacks = LitePal.find(BookRack.class, 1, true);
        List<Book> bookList = bookRacks.getBooks();
        Log.e("Demo", bookList.size() + "");
    }

}
