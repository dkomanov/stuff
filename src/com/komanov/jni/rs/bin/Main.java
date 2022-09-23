package com.komanov.jni.rs.bin;

public class Main {
    public static void main(String[] args) {
        String greeting = Native.greet(args.length == 1 ? args[0] : "Stranger");
        System.out.println(greeting);
    }
}
