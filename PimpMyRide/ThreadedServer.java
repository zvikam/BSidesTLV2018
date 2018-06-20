// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ThreadedServer.java

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer
{

    public ThreadedServer()
    {
        serverSocket = null;
        isStopped = false;
        runningThread = null;
    }

    private synchronized boolean isStopped()
    {
        return isStopped;
    }

    private void openServerSocket()
    {
        try
        {
            serverSocket = new ServerSocket(port);
        }
        catch(IOException e)
        {
            throw new RuntimeException((new StringBuilder("Cannot listen on port ")).append(port).toString(), e);
        }
    }

    public synchronized void stop()
    {
        isStopped = true;
        try
        {
            serverSocket.close();
        }
        catch(IOException e)
        {
            throw new RuntimeException("Error closing server", e);
        }
    }

    public ThreadedServer(int port)
    {
        serverSocket = null;
        isStopped = false;
        runningThread = null;
        this.port = port;
    }

    public void startServer()
    {
        openServerSocket();
        Socket clientSocket;
        for(; !isStopped(); (new Thread(new ClientHandler(clientSocket))).start())
        {
            clientSocket = null;
            try
            {
                clientSocket = serverSocket.accept();
            }
            catch(IOException e)
            {
                if(isStopped())
                {
                    System.out.println("Server Stopped.");
                    return;
                } else
                {
                    throw new RuntimeException("Error accepting client connection", e);
                }
            }
        }

    }

    private int port;
    protected ServerSocket serverSocket;
    protected boolean isStopped;
    protected Thread runningThread;
}
