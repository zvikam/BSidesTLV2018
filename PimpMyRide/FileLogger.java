// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileLogger.java

import java.io.*;

public class FileLogger
    implements Logger
{

    public FileLogger(String fileName)
        throws IOException
    {
        logFile = new File(fileName);
        logFile.createNewFile();
        fileWriter = new FileOutputStream(logFile, false);
    }

    public void writeToLog(String entry)
    {
        PrintWriter printer = new PrintWriter(fileWriter);
        printer.println(entry);
    }

    private FileOutputStream fileWriter;
    private File logFile;
}
