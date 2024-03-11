package org.example.primitives.exceptions;

public class VarIntAboveCapacity extends Exception{
    
    public VarIntAboveCapacity(){}

    @Override
    public String getMessage() {
        return "Maximum length of 5 bytes was exceeded.";
    }

}
