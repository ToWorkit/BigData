package com.company;

public class Main {

    public static void main(String[] args) {
        int i = 10;
        double b = 0;
        b = i / 4.0;
	    System.out.println(b);

	    Dog dog = new Dog();
	    dog.name = 1;
	    System.out.println(dog.name);
    }
}

class Dog {
    int name;
}