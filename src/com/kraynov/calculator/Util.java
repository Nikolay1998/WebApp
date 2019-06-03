package com.kraynov.calculator;

public class Util {

    public static boolean isEmpty(String str){
        if (str == null) return true;
        return "".equals(str);
    }

    public static String standartize(String str) {
        if (str == null) str = "";
        return str;
    }
}
