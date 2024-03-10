package org.example.primitives;

import java.io.IOException;
import java.io.InputStream;

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

        for(int i = 0; i < length.getValue(); i++){
            char c = getChar(is);

            if(c > '\uFFFF'){
                i++;
            }

            string += c;
        }

    }

    public String getValue(){
        return string;
    }

    private static final byte TWO_BYTES_UTF8 = (byte) 0b1100_0000;
    private static final byte THREE_BYTES_UTF8 = (byte) 0b1110_0000;
    private static final byte FOUR_BYTES_UTF8 = (byte) 0b1111_0000;

    private static char getChar(InputStream is) throws IOException{
        byte[] data = is.readNBytes(1);

        if(data.length == 0){
            throw new IOException();
        }

        byte firstByte = data[0];

        int numberOfCharsLeft;

        if((firstByte & Masks.THREE_MOST_SIGNIFICANT_BITS_OF_BYTE) == TWO_BYTES_UTF8){
            
        }

        return 0;
    }

    private static char getTheRestOfChars(InputStream is, int numberOfChars){

    }

}
