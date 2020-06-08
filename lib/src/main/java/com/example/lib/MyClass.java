package com.example.lib;

public class MyClass {
    public static void main(String[] args) {
    }

    private static int percent2int(float percent){
        if(percent == 0f){
            return 0;
        }else{
            return (int)((percent+0.1f)*10);
        }
    }

}
