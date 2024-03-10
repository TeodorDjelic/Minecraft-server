package org.example.primitives;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.example.binary.Masks;
import org.example.primitives.exceptions.StringFieldLengthAboveCapacity;

public class StringField {
    
    private static final int MAX_MAX_STRING_LENGTH = 32767;

    private String string = "";

    public StringField(InputStream is, int maxLength) throws IOException, StringFieldLengthAboveCapacity{
        VarIntField length = new VarIntField(is);

        if(length.getValue() > maxLength || maxLength > MAX_MAX_STRING_LENGTH){
            throw new StringFieldLengthAboveCapacity(length.getValue(), maxLength);
        }

        byte[] data = is.readNBytes(length.getValue());

        string = new String(data, StandardCharsets.UTF_8);

        /*for(int i = 0; i < length.getValue(); i++){
            char c = getChar(is);

            if(c > '\uFFFF'){
                i++;
            }

            string = c + string;
        }*/

    }

    public String getValue(){
        return string;
    }

    /*private static final byte HAS_MORE_BYTES = (byte) 0b1000_0000;

    private static char getChar(InputStream is) throws IOException{
        byte[] data;

        do{
            data = is.readNBytes(1);

            if(data.length == 0){
                throw new IOException();
            }

        }while((data[0] & Masks.TWO_MOST_SIGNIFICANT_BITS_OF_BYTE) == HAS_MORE_BYTES);

        return 0;
    }*/

}
