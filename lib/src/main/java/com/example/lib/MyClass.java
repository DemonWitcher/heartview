package com.example.lib;

public class MyClass {
    public static void main(String[] args) {
        System.out.println(percent2int(0.99f));
    }

    private static int percent2int(float percent){
        if(percent == 0f){
            return 0;
        }else{
            return (int)((percent+0.1f)*10);
        }
    }

}
