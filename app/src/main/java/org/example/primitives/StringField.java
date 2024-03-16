package org.example.primitives;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.example.primitives.exceptions.InvalidData;
import org.example.primitives.exceptions.InvalidStringFieldFormat;
import org.example.primitives.exceptions.StringFieldLengthAboveCapacity;

public class StringField extends Field<String>{
    
    private static final int MAX_MAX_STRING_LENGTH = 32767;

    public StringField(byte[] _data, int _maxLength) throws InvalidData {
        super(_data);
    }

    public StringField(String _value){
        super(_value);
    }

    @Override
    public byte[] getBytes() {
        VarIntField length = new VarIntField(value.length());
        byte[] a = length.getBytes();
        byte[] b = value.getBytes(StandardCharsets.UTF_8);

        byte[] result = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, result, a.length, b.length);

        return result;
    }

    @Override
    public void setBytes(byte[] data) throws InvalidData {

        int min = Math.min(data.length, 3);

        VarIntField length = new VarIntField(Arrays.copyOfRange(data, 0, min));

        if(length.getValue() > MAX_MAX_STRING_LENGTH){
            throw new StringFieldLengthAboveCapacity(length.getValue(), MAX_MAX_STRING_LENGTH);
        }

        if(length.getLength() + length.getValue() > data.length){
            throw new InvalidStringFieldFormat();
        }

        byte[] temp = Arrays.copyOfRange(data,
            length.getLength(),
            length.getLength() + length.getValue());

        value = new String(temp, StandardCharsets.UTF_8);
    }

}
