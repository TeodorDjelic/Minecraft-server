package org.example.structures;

import java.util.Arrays;

public class OffsetByteArray {

    private byte[] array;
    private int offset;

    public OffsetByteArray(byte[] _array){
        this(_array, 0);
    }

    public OffsetByteArray(byte[] _array, int _offset){
        this.array = _array;
        this.offset = _offset;
    }

    public OffsetByteArray(OffsetByteArray _array){
        this(_array, 0);
    }

    public OffsetByteArray(OffsetByteArray _array, int _offset){
        array = _array.array;
        offset = _array.offset + _offset;
    }

    public byte get(int i){
        return array[offset + i];
    }

    public void put(int i, byte element){
        array[offset + i]  = element;
    }

    public int length(){
        return array.length - offset;
    }

    public OffsetByteArray offset(int offset){
        return new OffsetByteArray(this, offset);
    }

    public byte[] copyArray(){
        return copyArray(array.length);
    }

    public byte[] copyArray(int length){
        return copyArray(length, 0);
    }

    public byte[] copyArray(int length, int offset){
        return Arrays.copyOfRange(array, this.offset + offset, this.offset + offset + length);
    }

}
