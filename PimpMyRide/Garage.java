// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Garage.java

import java.io.*;
import java.util.ArrayList;

public class Garage
    implements Serializable
{

    public Garage()
    {
        carLimit = 5;
        isOpen = true;
        carArray = new ArrayList();
        garageEmployees = new ArrayList();
    }

    public boolean addCar(Car car)
    {
        if(isOpen)
        {
            carArray.add(car);
            checkGarageStatus();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean removeCarByLicenseNumber(String licenseNumber)
    {
        for(int i = 0; i < carArray.size(); i++)
            if(((Car)carArray.get(i)).getLicenseNumber().equals(licenseNumber))
            {
                carArray.remove(i);
                return true;
            }

        return false;
    }

    public String printGarage()
    {
        String garageContent = "";
        for(int i = 0; i < carArray.size(); i++)
            garageContent = (new StringBuilder(String.valueOf(garageContent))).append("Car Manufacturer: ").append(((Car)carArray.get(i)).getManufacturerName()).append("\r\n").append("Car License Number: ").append(((Car)carArray.get(i)).getLicenseNumber()).append("\r\n").append("Car Manufacturing Year: ").append(((Car)carArray.get(i)).getManufacturingYear()).append("\r\n").toString();

        return garageContent;
    }

    public boolean checkGarageStatus()
    {
        if(carArray.size() == carLimit)
        {
            garageManager.doWork();
            isOpen = false;
            return false;
        } else
        {
            return true;
        }
    }

    private void readObject(ObjectInputStream in)
        throws ClassNotFoundException, IOException
    {
        in.defaultReadObject();
        checkGarageStatus();
    }

    public void setManager(Employee manager)
    {
        garageManager = manager;
    }

    public byte[] toByteArray()
        throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(this);
        return out.toByteArray();
    }

    private ArrayList carArray;
    private int carLimit;
    private Employee garageManager;
    private ArrayList garageEmployees;
    private boolean isOpen;
}
