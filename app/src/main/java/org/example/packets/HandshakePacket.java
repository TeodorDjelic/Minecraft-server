package org.example.packets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.example.primitives.StringField;
import org.example.primitives.UnsignedShortField;
import org.example.primitives.VarIntField;
import org.example.primitives.exceptions.InvalidData;
import org.example.primitives.exceptions.InvalidStringFieldFormat;
import org.example.primitives.exceptions.StringFieldLengthAboveCapacity;
import org.example.primitives.exceptions.VarIntAboveCapacity;
import org.json.JSONObject;

import com.google.common.primitives.Shorts;
import com.ibm.icu.impl.Punycode;
import com.ibm.icu.text.IDNA;
import com.ibm.icu.text.StringPrepParseException;
import com.ibm.icu.text.IDNA.Info;

import java.util.Arrays;
import java.util.Scanner;

public class HandshakePacket extends Packet {

    private VarIntField protocolVersion;
    private StringField serverAddress;
    private UnsignedShortField serverPort;
    private VarIntField nextState;
    
    public HandshakePacket(PacketData _packetData) throws InvalidData {
        super(_packetData);   
    }

    @Override
    public PacketData getPacketData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPacketData'");
    }

    @Override
    protected void setPacketData(PacketData packetData) throws InvalidData {
        byte[] data = packetData.getData();

        int dataLength = data.length;
        int current = 0;

        this.protocolVersion = new VarIntField(
            Arrays.copyOfRange(data, current, current + 5)
        );

        current += protocolVersion.getLength();

        this.serverAddress = new StringField(
            Arrays.copyOfRange(data, current, dataLength),
            255);

        current += serverAddress.getLength();

        this.serverPort = new UnsignedShortField(
            Arrays.copyOfRange(data, current, current + 2)
        );

        current += serverPort.getLength();

        this.nextState = new VarIntField(
            Arrays.copyOfRange(data, current, dataLength)
        );
    }

    public int getProtocolVersion() {
        return protocolVersion.getValue();
    }

    public String getServerAddress() {
        return serverAddress.getValue();
    }

    public short getServerPort() {
        return serverPort.getValue();
    }

    public int getNextState() {
        return nextState.getValue();
    }

    
}
