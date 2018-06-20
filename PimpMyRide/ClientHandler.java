// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClientHandler.java

import java.io.*;
import java.net.Socket;

class ClientHandler
    implements Runnable
{

    public ClientHandler()
    {
        helloMessage = "      ___           ___           ___           ___           ___           ___     \r\n     /  /\\         /  /\\         /  /\\         /  /\\         /  /\\         /  /\\    \r\n    /  /::\\       /  /::\\       /  /::\\       /  /::\\       /  /::\\       /  /::\\   \r\n   /  /:/\\:\\     /  /:/\\:\\     /  /:/\\:\\     /  /:/\\:\\     /  /:/\\:\\     /  /:/\\:\\  \r\n  /  /:/  \\:\\   /  /::\\ \\:\\   /  /::\\ \\:\\   /  /::\\ \\:\\   /  /:/  \\:\\   /  /::\\ \\:\\ \r\n /__/:/_\\_ \\:\\ /__/:/\\:\\_\\:\\ /__/:/\\:\\_\\:\\ /__/:/\\:\\_\\:\\ /__/:/_\\_ \\:\\ /__/:/\\:\\ \\:\\\r\n \\  \\:\\__/\\_\\/ \\__\\/  \\:\\/:/ \\__\\/~|::\\/:/ \\__\\/  \\:\\/:/ \\  \\:\\__/\\_\\/ \\  \\:\\ \\:\\_\\/\r\n  \\  \\:\\ \\:\\        \\__\\::/     |  |:|::/       \\__\\::/   \\  \\:\\ \\:\\    \\  \\:\\ \\:\\  \r\n   \\  \\:\\/:/        /  /:/      |  |:|\\/        /  /:/     \\  \\:\\/:/     \\  \\:\\_\\/  \r\n    \\  \\::/        /__/:/       |__|:|~        /__/:/       \\  \\::/       \\  \\:\\    \r\n     \\__\\/         \\__\\/         \\__\\|         \\__\\/         \\__\\/         \\__\\/\r\n     \r\n     Hello and welcome to our virtual garage, it lets you store your cars and we will take care of them for you. \r\n     \r\n";
        mainOptions = "[1] Create new garage\r\n[2] Load existing garage\r\n[3] Exit\r\n";
        garageManagementOption = "[1] Add car\r\n[2] Remove car\r\n[3] Save garage\r\n[4] Print garage content\r\n[5] Exit";
        sessionActive = true;
        stateChanged = false;
        garage = null;
    }

    ClientHandler(Socket s)
    {
        helloMessage = "      ___           ___           ___           ___           ___           ___     \r\n     /  /\\         /  /\\         /  /\\         /  /\\         /  /\\         /  /\\    \r\n    /  /::\\       /  /::\\       /  /::\\       /  /::\\       /  /::\\       /  /::\\   \r\n   /  /:/\\:\\     /  /:/\\:\\     /  /:/\\:\\     /  /:/\\:\\     /  /:/\\:\\     /  /:/\\:\\  \r\n  /  /:/  \\:\\   /  /::\\ \\:\\   /  /::\\ \\:\\   /  /::\\ \\:\\   /  /:/  \\:\\   /  /::\\ \\:\\ \r\n /__/:/_\\_ \\:\\ /__/:/\\:\\_\\:\\ /__/:/\\:\\_\\:\\ /__/:/\\:\\_\\:\\ /__/:/_\\_ \\:\\ /__/:/\\:\\ \\:\\\r\n \\  \\:\\__/\\_\\/ \\__\\/  \\:\\/:/ \\__\\/~|::\\/:/ \\__\\/  \\:\\/:/ \\  \\:\\__/\\_\\/ \\  \\:\\ \\:\\_\\/\r\n  \\  \\:\\ \\:\\        \\__\\::/     |  |:|::/       \\__\\::/   \\  \\:\\ \\:\\    \\  \\:\\ \\:\\  \r\n   \\  \\:\\/:/        /  /:/      |  |:|\\/        /  /:/     \\  \\:\\/:/     \\  \\:\\_\\/  \r\n    \\  \\::/        /__/:/       |__|:|~        /__/:/       \\  \\::/       \\  \\:\\    \r\n     \\__\\/         \\__\\/         \\__\\|         \\__\\/         \\__\\/         \\__\\/\r\n     \r\n     Hello and welcome to our virtual garage, it lets you store your cars and we will take care of them for you. \r\n     \r\n";
        mainOptions = "[1] Create new garage\r\n[2] Load existing garage\r\n[3] Exit\r\n";
        garageManagementOption = "[1] Add car\r\n[2] Remove car\r\n[3] Save garage\r\n[4] Print garage content\r\n[5] Exit";
        sessionActive = true;
        stateChanged = false;
        garage = null;
        myClientSocket = s;
    }

    public void run()
    {
        try
        {
            Utils.writeToSocket(myClientSocket, (new StringBuilder(String.valueOf(helloMessage))).append("\r\n").append(mainOptions).toString());
            while(sessionActive) 
                if(garage == null)
                {
                    if(stateChanged)
                    {
                        Utils.writeToSocket(myClientSocket, mainOptions);
                        stateChanged = false;
                    }
                    String clientCommand = Utils.readFromSocket(myClientSocket);
                    if(clientCommand.equalsIgnoreCase("1"))
                        garage = new Garage();
                    else
                    if(clientCommand.equalsIgnoreCase("2"))
                    {
                        byte garageBytes[] = Utils.receiveGarage(myClientSocket);
                        ByteArrayInputStream in = new ByteArrayInputStream(garageBytes);
                        ObjectInputStream ois = new ObjectInputStream(in);
                        garage = (Garage)ois.readObject();
                        ois.close();
                        stateChanged = true;
                    } else
                    if(clientCommand.equalsIgnoreCase("3"))
                    {
                        Utils.writeToSocket(myClientSocket, "[end]");
                        sessionActive = false;
                    } else
                    {
                        Utils.writeToSocket(myClientSocket, "Unknown option");
                        stateChanged = true;
                    }
                } else
                {
                    Utils.writeToSocket(myClientSocket, garageManagementOption);
                    String clientCommand = Utils.readFromSocket(myClientSocket);
                    if(clientCommand.equalsIgnoreCase("1"))
                    {
                        Utils.writeToSocket(myClientSocket, "Car manufacturer:");
                        String carManufacturer = Utils.readFromSocket(myClientSocket);
                        Utils.writeToSocket(myClientSocket, "Car license number:");
                        String carLicenseNumber = Utils.readFromSocket(myClientSocket);
                        Utils.writeToSocket(myClientSocket, "Car manufacturing year:");
                        String carManufacturingYear = Utils.readFromSocket(myClientSocket);
                        if(garage.addCar(new Car(carManufacturer, carLicenseNumber, carManufacturingYear)))
                            Utils.writeToSocket(myClientSocket, "Car added successfully");
                        else
                            Utils.writeToSocket(myClientSocket, "Garage is full");
                    } else
                    if(clientCommand.equalsIgnoreCase("2"))
                    {
                        Utils.writeToSocket(myClientSocket, "Car license number to rmove:");
                        String carLicenseNumber = Utils.readFromSocket(myClientSocket);
                        boolean result = garage.removeCarByLicenseNumber(carLicenseNumber);
                        if(result)
                            Utils.writeToSocket(myClientSocket, "Successfully removed car");
                        else
                            Utils.writeToSocket(myClientSocket, "Failed to remove car, are you sure this is the correct license number?");
                    } else
                    if(clientCommand.equalsIgnoreCase("3"))
                        Utils.sendGarage(myClientSocket, garage.toByteArray());
                    else
                    if(clientCommand.equals("4"))
                    {
                        String garageContent = garage.printGarage();
                        if(!garageContent.equals(""))
                            Utils.writeToSocket(myClientSocket, garageContent);
                        else
                            Utils.writeToSocket(myClientSocket, "Garage is empty");
                    } else
                    if(clientCommand.equals("5"))
                    {
                        garage = null;
                        stateChanged = true;
                    } else
                    {
                        Utils.writeToSocket(myClientSocket, "Unknown option");
                    }
                }
            break MISSING_BLOCK_LABEL_545;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            myClientSocket.close();
        }
        catch(IOException ioexception) { }
        break MISSING_BLOCK_LABEL_557;
        Exception exception;
        exception;
        try
        {
            myClientSocket.close();
        }
        catch(IOException ioexception1) { }
        throw exception;
        try
        {
            myClientSocket.close();
        }
        catch(IOException ioexception2) { }
    }

    private Socket myClientSocket;
    private String helloMessage;
    private String mainOptions;
    private String garageManagementOption;
    boolean sessionActive;
    boolean stateChanged;
    Garage garage;
}
