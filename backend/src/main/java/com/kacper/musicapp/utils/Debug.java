package com.kacper.musicapp.utils;

public class Debug
{
    public static void dd(Object obj) {
        System.out.println("\n\n");
        System.out.println("Debugging: ");
        System.out.println(obj);
        System.out.println("\n\n");
        System.exit(0);
    }
}
