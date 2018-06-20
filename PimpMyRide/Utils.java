// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Utils.java

import java.io.*;
import java.net.Socket;

public class Utils
{

    public Utils()
    {
    }

    public static String readFromSocket(Socket sock)
        throws IOException
    {
        DataInputStream dataInputStream = new DataInputStream(sock.getInputStream());
        int length = dataInputStream.readInt();
        byte b[] = new byte[length];
        dataInputStream.readFully(b);
        return new String(b);
    }

    public static void writeToSocket(Socket sock, String message)
        throws IOException
    {
        DataOutputStream outToServer = new DataOutputStream(sock.getOutputStream());
        outToServer.writeInt(message.length());
        outToServer.writeBytes(message);
        outToServer.flush();
    }

    public static void sendGarage(Socket sock, byte garageBytes[])
        throws IOException
    {
        DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
        dos.writeInt(garageBytes.length);
        dos.write(garageBytes);
        dos.flush();
    }

    public static byte[] receiveGarage(Socket sock)
        throws IOException
    {
        java.io.FileOutputStream fos = null;
        java.io.BufferedOutputStream bos = null;
        DataInputStream dataInputStream = new DataInputStream(sock.getInputStream());
        int byteArrSize = dataInputStream.readInt();
        byte garageByteArray[] = new byte[byteArrSize];
        dataInputStream.read(garageByteArray, 0, byteArrSize);
        return garageByteArray;
    }
}
