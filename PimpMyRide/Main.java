// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Main.java

import com.beust.jcommander.JCommander;
import com.beust.jcommander.internal.Lists;
import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;
import java.util.List;

public class Main
{

    public Main()
    {
        parameters = Lists.newArrayList();
        serverMode = false;
        port = 1337;
        remoteAddr = "127.0.0.1";
        help = false;
    }

    public static void main(String args[])
        throws UnknownHostException, IOException, InterruptedException, ClassNotFoundException
    {
        Main main = new Main();
        JCommander jCommander = JCommander.newBuilder().addObject(main).build();
        jCommander.parse(args);
        if(main.help)
        {
            jCommander.usage();
            return;
        }
        if(main.serverMode)
            main.startServer();
        else
            main.connectToServer();
    }

    public void startServer()
    {
        System.out.println((new StringBuilder("Starting server on port ")).append(port).toString());
        ThreadedServer threadedServer = new ThreadedServer(port);
        threadedServer.startServer();
    }

    public void connectToServer()
        throws UnknownHostException, IOException, InterruptedException, ClassNotFoundException
    {
        GarageClient garageClient = new GarageClient(remoteAddr, port);
        garageClient.connectToServer();
    }

    public List parameters;
    public boolean serverMode;
    public int port;
    public String remoteAddr;
    public boolean help;
}
