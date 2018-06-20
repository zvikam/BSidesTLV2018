// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Manager.java

import java.io.*;

public class Manager extends Employee
    implements Serializable
{

    public Manager()
        throws IOException
    {
        closeMessageFile = "close.txt";
        logger = new FileLogger("log.txt");
        closeMessage = null;
    }

    public void setCloseMessage(String closeMessage)
    {
        this.closeMessage = closeMessage;
    }

    public void doWork()
    {
        logger.writeToLog(closeMessage);
    }

    public void setCloseMessageFile(String closeMessageFile)
    {
        this.closeMessageFile = closeMessageFile;
    }

    private void readObject(ObjectInputStream in)
        throws ClassNotFoundException, IOException
    {
        in.defaultReadObject();
        try
        {
            if(closeMessage == null)
            {
                File closeMessageFile = new File(this.closeMessageFile);
                FileInputStream fis = new FileInputStream(closeMessageFile);
                byte data[] = new byte[(int)closeMessageFile.length()];
                fis.read(data);
                fis.close();
                closeMessage = new String(data, "UTF-8");
            }
        }
        catch(IOException ioexception) { }
    }

    private String closeMessageFile;
    private String closeMessage;
}
