// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Car.java

import java.io.Serializable;

public class Car
    implements Serializable
{

    public Car(String manufacturerName, String licenseNumber, String manufacturingYear)
    {
        setLicenseNumber(licenseNumber);
        setManufacturerName(manufacturerName);
        setManufacturingYear(manufacturingYear);
    }

    public String getLicenseNumber()
    {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber)
    {
        this.licenseNumber = licenseNumber;
    }

    public String getManufacturerName()
    {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName)
    {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturingYear()
    {
        return manufacturingYear;
    }

    public void setManufacturingYear(String manufacturingYear)
    {
        this.manufacturingYear = manufacturingYear;
    }

    private String licenseNumber;
    private String manufacturerName;
    private String manufacturingYear;
}
