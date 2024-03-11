package org.example.primitives;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.example.binary.Masks;
import org.example.primitives.exceptions.InvalidStringFieldFormat;
import org.example.primitives.exceptions.StringFieldLengthAboveCapacity;
import org.example.primitives.exceptions.VarIntAboveCapacity;

public class StringField extends Field<String>{
    
    private static final int MAX_MAX_STRING_LENGTH = 32767;

    public StringField(InputStream is, int maxLength) throws IOException, StringFieldLengthAboveCapacity, InvalidStringFieldFormat{

        VarIntField length;
        try {
            length = new VarIntField(is);
        } catch (VarIntAboveCapacity e) {
            throw new InvalidStringFieldFormat();
        }

        if(length.getValue() > maxLength || maxLength > MAX_MAX_STRING_LENGTH){
            throw new StringFieldLengthAboveCapacity(length.getValue(), maxLength);
        }

        byte[] data = is.readNBytes(length.getValue());

        value = new String(data, StandardCharsets.UTF_8);

    }

    public StringField(String _value){
        super(_value);
    }

    @Override
    public byte[] getBytes() {
        return value.getBytes(StandardCharsets.UTF_8);
    }

}
