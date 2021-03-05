package com.example.lib;

public class IntUtil {

    public static int setSpecifiedBitToTrue(int originByte, int bitIndex) {
        return originByte |= (1 << bitIndex);
    }

    public static int setSpecifiedBitToFalse(int originByte, int bitIndex) {
        return originByte &= ~(1 << bitIndex);
    }

    public static int setSpecifiedBitToReverse(int originByte, int bitIndex) {
        return originByte ^= (1 << bitIndex);
    }

    public static int getSpecifiedBitValue(int originByte, int bitIndex) {
        return (byte) ((originByte) >> (bitIndex) & 1);
    }

    public static boolean getSpecifiedBitIsTrue(int originByte, int bitIndex){
        return (byte) ((originByte) >> (bitIndex) & 1) == 1;
    }

}
