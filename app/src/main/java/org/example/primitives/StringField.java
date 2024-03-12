package org.example.primitives;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.example.binary.Masks;
import org.example.primitives.exceptions.InvalidData;
import org.example.primitives.exceptions.InvalidStringFieldFormat;
import org.example.primitives.exceptions.StringFieldLengthAboveCapacity;
import org.example.primitives.exceptions.VarIntAboveCapacity;

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
        return value.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public void setBytes(byte[] data) throws InvalidData {
        VarIntField length = new VarIntField(Arrays.copyOfRange(data, 0, 3));

        if(length.getValue() > MAX_MAX_STRING_LENGTH){
            throw new StringFieldLengthAboveCapacity(length.getValue(), MAX_MAX_STRING_LENGTH);
        }

        byte[] temp = Arrays.copyOfRange(data, length.getLength(), data.length - length.getLength());

        value = new String(temp, StandardCharsets.UTF_8);
    }

}
