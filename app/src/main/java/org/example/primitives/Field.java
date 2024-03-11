package org.example.primitives;

import java.io.IOException;
import java.io.InputStream;

public abstract class Field<T> {
    
    protected T value;

    public Field(){}

    public Field(T _value){
        value = _value;
    }

    public final T getValue(){
        return value;
    }

    public abstract byte[] getBytes();


}
