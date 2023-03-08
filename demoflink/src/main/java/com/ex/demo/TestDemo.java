package com.ex.demo;

/**
 * @Description
 * @Author zhaolei
 * @Date 3/6/23 1:48 PM
 * @Version 1.0
 */
public class TestDemo {
    public static void main(String[] args) {
        try {
            System.out.println("try");
            return;
        }catch (Exception e){
            System.out.println("catch");
        }finally {
            System.out.println("finally");
        }
    }
}
