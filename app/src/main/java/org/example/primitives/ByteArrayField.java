package org.example.primitives;

import org.example.primitives.exceptions.InvalidData;
import org.example.structures.OffsetByteArray;

public class ByteArrayField {

    private byte[] bytes;

    public ByteArrayField(OffsetByteArray data, int length) throws InvalidData{
        setBytes(data, length);
    }

    public ByteArrayField(byte[] _value) {
        bytes = _value;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(OffsetByteArray data, int length) throws InvalidData {
        bytes = data.copyArray(length);
    }

    public byte[] getValue() {
        return bytes;
    }

    public int getLength() {
        return bytes.length;
    }    

}
