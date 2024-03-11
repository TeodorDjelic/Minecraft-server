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
import org.example.primitives.exceptions.InvalidStringFieldFormat;
import org.example.primitives.exceptions.StringFieldLengthAboveCapacity;
import org.example.primitives.exceptions.VarIntAboveCapacity;
import org.json.JSONObject;

import com.ibm.icu.impl.Punycode;
import com.ibm.icu.text.IDNA;
import com.ibm.icu.text.StringPrepParseException;
import com.ibm.icu.text.IDNA.Info;

import java.util.Arrays;
import java.util.Scanner;

public class HandshakePacket {

    private VarIntField protocolVersion;
    private String serverAddress = "";
    private UnsignedShortField serverPort;
    private VarIntField nextState;
    //private UnsignedShortField serverPort;

    private static byte[] intToVarInt(int value) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        while ((value & 0xFFFFFF80) != 0L) {
            outputStream.write((byte) ((value & 0x7F) | 0x80));
            value >>>= 7;
        }

        outputStream.write((byte) (value & 0x7F));

        return outputStream.toByteArray();
    }

    public HandshakePacket(InputStream is, OutputStream os, int dataLength) throws IOException, StringFieldLengthAboveCapacity, URISyntaxException, VarIntAboveCapacity, InvalidStringFieldFormat {
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

        if(nextState.getValue() == 1){
            VarIntField length = new VarIntField(is);
            VarIntField packetid = new VarIntField(is);

            String jsonResponseStringified =
                Files.readString(
                    Paths.get(Thread.currentThread().getContextClassLoader()
                        .getResource("server_status.json").toURI()), StandardCharsets.UTF_8);

            int duzina = jsonResponseStringified.getBytes().length + 3;


            byte[] byte1 = intToVarInt(duzina);
            byte[] byte2 = new byte[]{0};
            byte[] byte3 = intToVarInt(duzina - 1 - byte1.length);

            os.write(byte1);
            os.write(byte2);
            os.write(byte3);
            os.write(jsonResponseStringified.getBytes());

        }

    }
    
}
