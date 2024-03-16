package org.example.packets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.example.primitives.StringField;
import org.example.primitives.exceptions.InvalidData;

public class StatusPacket extends Packet {

    public StatusPacket(PacketData packetData) throws InvalidData {
        super(packetData);
    }

    @Override
    public PacketData getPacketData() {


        String jsonResponseStringified = "";

        try {
            jsonResponseStringified =
                Files.readString(
                    Paths.get(Thread.currentThread().getContextClassLoader()
                        .getResource("server_status.json").toURI()), StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }


        StringField sf = new StringField(jsonResponseStringified);

        return new PacketData(0, sf.getBytes());
    }

    @Override
    protected void setPacketData(PacketData packetData) throws InvalidData {}
    
}
