package org.example.primitives;

import java.util.UUID;

import org.example.binary.TypeSizes;
import org.example.primitives.exceptions.InvalidData;
import org.example.structures.OffsetByteArray;

public class UUIDField {

    private UUID value;

    public UUIDField(OffsetByteArray _data) throws InvalidData{
        setBytes(_data);
    }

    public UUIDField(UUID _value){
        this.value = _value;
    }

    public byte[] getBytes() {
        byte[] data = new byte[16];

        long high = value.getMostSignificantBits();

        for(int i = 7; i >= 0; i--){
            data[i] = (byte) high;
            high >>>= 8;
        }

        long low = value.getLeastSignificantBits();

        for(int i = 15; i >= 8; i--){
            data[i] = (byte) low;
            low >>>= 8;
        }

        return data;
    }

    public void setBytes(OffsetByteArray data) throws InvalidData {
        LongField l1 = new LongField(data);
        LongField l2 = new LongField(data.offset(8));

        value = new UUID(l1.getValue(), l2.getValue());   
    }

    public UUID getValue() {
        return value;
    }

    public int getLength() {
        return TypeSizes.SIZE_OF_UUID;
    }
    
}
