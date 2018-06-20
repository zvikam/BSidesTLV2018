// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RemoteLogger.java

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class RemoteLogger
    implements Logger, Serializable
{

    public RemoteLogger(String ipAddress, int port)
    {
        clientSocket = null;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public void writeToLog(String entry)
    {
        try
        {
            if(clientSocket == null)
                clientSocket = new Socket(ipAddress, port);
            Utils.writeToSocket(clientSocket, entry);
            clientSocket.close();
            clientSocket = null;
        }
        catch(UnknownHostException unknownhostexception) { }
        catch(IOException ioexception) { }
    }

    private String ipAddress;
    private int port;
    Socket clientSocket;
}
