package org.example.binary;

public class Masks {
    public static final byte MOST_SIGNIFICANT_BIT_OF_BYTE = (byte) 0x80;
    public static final byte TWO_MOST_SIGNIFICANT_BITS_OF_BYTE = (byte) (0x80 | 0x40);
    public static final byte THREE_MOST_SIGNIFICANT_BITS_OF_BYTE = (byte) (0x80 | 0x40 | 0x20);
    public static final byte FOUR_MOST_SIGNIFICANT_BITS_OF_BYTE = (byte) (0x80 | 0x40 | 0x20 | 0x10);
    public static final byte FIVE_MOST_SIGNIFICANT_BITS_OF_BYTE = (byte) (0x80 | 0x40 | 0x20 | 0x10 | 0x08);

}
