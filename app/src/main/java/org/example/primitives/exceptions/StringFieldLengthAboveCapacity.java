package org.example.primitives.exceptions;

public class StringFieldLengthAboveCapacity extends PrimitiveException {
    
    private int length;
    private int maxLength;
    
    public StringFieldLengthAboveCapacity(int _length, int _maxLength){
        this.length = _length;
        this.maxLength = _maxLength;
    }

    @Override
    public String getMessage() {
        return "Maximum length of " + maxLength + " characters was exceeded (string was "
                + length + " characters long).";
    }

}
