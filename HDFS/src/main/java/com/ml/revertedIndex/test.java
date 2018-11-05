package com.ml.revertedIndex;

public class test {
    public static void main(String[] args) {
        String str = "/Love:data01.txt/test.txt";
        String[] strings = str.split(":");
        System.out.println(strings[0] + " " + strings[1]);

        String str_1 = "data01.txt/test.txt";

        String substring = str_1.substring(str_1.lastIndexOf("/") + 1);
        System.out.println(str_1.lastIndexOf("/"));
        System.out.println(str_1.substring(1, 2));
        System.out.println(substring);
    }
}
