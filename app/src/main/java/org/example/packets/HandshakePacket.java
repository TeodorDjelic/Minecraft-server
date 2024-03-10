package org.example.packets;

import java.io.IOException;
import java.io.InputStream;

import org.example.primitives.StringField;
import org.example.primitives.UnsignedShortField;
import org.example.primitives.VarIntField;
import org.example.primitives.exceptions.StringFieldLengthAboveCapacity;

import com.ibm.icu.impl.Punycode;
import com.ibm.icu.text.IDNA;
import com.ibm.icu.text.StringPrepParseException;
import com.ibm.icu.text.IDNA.Info;

import java.util.Arrays;

public class HandshakePacket {

    private VarIntField protocolVersion;
    private String serverAddress = "";
    private UnsignedShortField serverPort;
    private VarIntField nextState;
    //private UnsignedShortField serverPort;

    public HandshakePacket(InputStream is, int dataLength) throws IOException, StringFieldLengthAboveCapacity {
        protocolVersion = new VarIntField(is);

        StringField sf = new StringField(is, dataLength);
        StringBuilder sb = new StringBuilder();
        Info info = new Info();
        IDNA.getUTS46Instance(0).labelToUnicode(sf.getValue(), sb, info);
        serverAddress = sb.toString();

        serverPort = new UnsignedShortField(is);
        
        nextState = new VarIntField(is);

        System.out.println("Protocol version: "  + protocolVersion.getValue());
        System.out.println("Server address: "  + serverAddress);
        System.out.println("Server port: "  + serverPort.getValue());
        System.out.println("Next state: "  + nextState.getValue());

    }
    
}
