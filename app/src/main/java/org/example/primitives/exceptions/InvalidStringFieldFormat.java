package org.example.primitives.exceptions;

public class InvalidStringFieldFormat extends InvalidData {
    
    public InvalidStringFieldFormat(){}

    @Override
    public String getMessage() {
        return "String length of type VarIntField is invalid.";
    }

}
