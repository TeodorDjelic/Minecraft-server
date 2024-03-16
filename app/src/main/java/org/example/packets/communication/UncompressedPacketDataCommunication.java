package org.example.packets.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.example.packets.PacketData;
import org.example.primitives.VarIntField;
import org.example.primitives.exceptions.InvalidData;
import org.example.sessions.ClientConnection;

public class UncompressedPacketDataCommunication implements PacketDataCommunication {

    private ClientConnection clientConnection;

    public UncompressedPacketDataCommunication(ClientConnection _clientConnection) {
        this.clientConnection = _clientConnection;
    }

    @Override
    public PacketData receivePacketData() throws IOException {        
        try {
            return receivePacketDataInternal();
        } catch (InvalidData e) {
            return null;
        }
    }
    
    @Override
    public void sendPacketData(PacketData packet) throws IOException {
        VarIntField packetID = new VarIntField(packet.getPacketID());
        byte[] data = packet.getData();
        VarIntField length = new VarIntField(data.length + packetID.getLength());
        
        OutputStream outputStream = clientConnection.getOutputStream();
        
        outputStream.write(length.getBytes());
        outputStream.write(packetID.getBytes());
        outputStream.write(data);
    }

    private PacketData receivePacketDataInternal() throws IOException, InvalidData {
        InputStream inputStream = clientConnection.getInputStream();
        VarIntField length = new VarIntField(inputStream);
        VarIntField packetID = new VarIntField(inputStream);;
        byte[] data = inputStream.readNBytes(length.getValue() - packetID.getLength());

        return new PacketData(packetID.getValue(), data);
    }
    
}
