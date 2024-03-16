package org.example.primitives;

import org.example.primitives.exceptions.InvalidData;

public abstract class Field<T> {
    
    protected T value;

    public Field(byte[] _data) throws InvalidData{
        setBytes(_data);
    }

    public Field(T _value){
        value = _value;
    }

    public final T getValue(){
        return value;
    }
    
    public int getLength(){
        return getBytes().length;
    }

    public abstract byte[] getBytes();    
    public abstract void setBytes(byte[] data) throws InvalidData;

}
