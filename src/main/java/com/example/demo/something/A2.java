package com.example.demo.something;

/**
 * @author chuliuhuan
 * @date 2022-11-16 13:40
 */
public class A2 {
    String str=new String("tarena");
    char[]ch={'a','b','c'};
    public static void main(String args[]){
        A2 ex=new A2();
        ex.change(ex.str,ex.ch);
        System.out.print(ex.str+" and ");
        System.out.print(ex.ch);
    }

    public void change(String str,char ch[]){
        str="test ok";
        ch[0]='g';
    }
}