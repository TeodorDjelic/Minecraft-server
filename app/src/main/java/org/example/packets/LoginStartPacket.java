package org.example.packets;

import java.util.UUID;

import org.example.primitives.StringField;
import org.example.primitives.UUIDField;
import org.example.primitives.exceptions.InvalidData;

public class LoginStartPacket extends Packet{

    private StringField name;
    private UUIDField uuid;


    public LoginStartPacket(PacketData packetData) throws InvalidData {
        super(packetData);
    }

    @Override
    public PacketData getPacketData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPacketData'");
    }

    @Override
    protected void setPacketData(PacketData packetData) throws InvalidData {
        int offset = 0;
        
        name = new StringField(packetData.getData(), 16);

        offset += name.getLength();

        uuid = new UUIDField(packetData.getData().offset(offset));

        offset += uuid.getLength();

        if(offset != packetData.getData().length()){
            throw new InvalidData();
        }

    }

    public String getName() {
        return name.getValue();
    }

    public UUID getUUID() {
        return uuid.getValue();
    }

    
    
}
