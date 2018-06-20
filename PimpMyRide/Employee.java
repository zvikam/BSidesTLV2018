// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Employee.java

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Random;

public class Employee
    implements Serializable
{

    Employee()
    {
        name = NameGenerator.getName();
        age = (new Random()).nextInt(99);
    }

    public void setLogger(Logger logger)
    {
        this.logger = logger;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void doWork()
    {
        System.out.println((new StringBuilder("Worker ")).append(name).append(" is working.").toString());
    }

    protected Logger logger;
    protected String name;
    protected int age;
}
