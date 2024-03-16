package org.example.primitives;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.example.primitives.exceptions.InvalidData;
import org.example.primitives.exceptions.InvalidStringFieldFormat;
import org.example.primitives.exceptions.StringFieldLengthAboveCapacity;
import org.example.structures.OffsetByteArray;

public class StringField {
    
    private static final int MAX_MAX_STRING_LENGTH = 32767;

    private String value;

    public StringField(OffsetByteArray _data, int _maxLength) throws InvalidData {
        setBytes(_data, _maxLength);
    }

    public StringField(String _value){
        this.value = _value;
    }

    public byte[] getBytes() {
        VarIntField length = new VarIntField(value.length());
        byte[] a = length.getBytes();
        byte[] b = value.getBytes(StandardCharsets.UTF_8);

        byte[] result = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, result, a.length, b.length);

        return result;
    }

    public void setBytes(OffsetByteArray data, int maxLength) throws InvalidData {

        VarIntField length = new VarIntField(data);

        if(length.getValue() > maxLength || maxLength > MAX_MAX_STRING_LENGTH){
            throw new StringFieldLengthAboveCapacity(length.getValue(), MAX_MAX_STRING_LENGTH);
        }

        if(length.getLength() + length.getValue() > data.length()){
            throw new InvalidStringFieldFormat();
        }

        ByteArrayField temp = new ByteArrayField(data.offset(length.getLength()), length.getValue());

        value = new String(temp.getBytes(), StandardCharsets.UTF_8);
    }

    public int getLength(){
        return value.length() + new VarIntField(value.length()).getLength();
    }

    public String getValue(){
        return value;
    }

}
