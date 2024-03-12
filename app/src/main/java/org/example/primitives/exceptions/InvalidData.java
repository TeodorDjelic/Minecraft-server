package org.example.primitives.exceptions;

public class InvalidData extends PrimitiveException {

    public InvalidData(){}

    @Override
    public String getMessage() {
        return "Number of bytes does not match with the required number of bytes for this data type.";
    }

}
