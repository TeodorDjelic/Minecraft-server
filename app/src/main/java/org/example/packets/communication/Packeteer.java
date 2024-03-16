package org.example.packets.communication;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.example.packets.Packet;
import org.example.packets.PacketData;

public class Packeteer {

    private Map<Integer, Class<? extends Packet>> packetTypes = new HashMap<>();

    public Packeteer(){}

    public void registerPackageType(int packageId, Class<? extends Packet> packetType){
        packetTypes.put(packageId, packetType);
    }

    public Packet receivePacket(PacketDataCommunication packetDataCommunication) throws IOException {
        PacketData packetData = packetDataCommunication.receivePacketData();
        
        if(packetData == null){
            return null;
        }

        int packetID = packetData.getPacketID();

        Class<? extends Packet> packetType = packetTypes.get(packetID);

        try {
            return packetType.getConstructor(PacketData.class).newInstance(packetData);
        } 
        catch (InstantiationException e){
            e.printStackTrace();
            throw new IOException("Corrupted packet was received.");
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new RuntimeException("Packet class " + packetType.getName() + " does not have the required constructor.");
        }
    }

    public void sendPacket(PacketDataCommunication packetDataCommunication, Packet packet) throws IOException {
        packetDataCommunication.sendPacketData(packet.getPacketData());
    }

}
