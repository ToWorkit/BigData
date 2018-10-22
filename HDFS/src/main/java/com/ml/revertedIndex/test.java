package com.ml.revertedIndex;

public class test {
    public static void main(String[] args) {
        String str = "Love:data01.txt/test.txt";
        String[] strings = str.split(":");
        System.out.println(strings[0] + " " + strings[1]);

        String substring = str.substring(str.lastIndexOf("\\/") + 1);
        System.out.println(str.lastIndexOf("/"));
    }
}
