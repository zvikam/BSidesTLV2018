// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JarRsrcLoader.java

package org.eclipse.jdt.internal.jarinjarloader;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

// Referenced classes of package org.eclipse.jdt.internal.jarinjarloader:
//            RsrcURLStreamHandlerFactory

public class JarRsrcLoader
{
    private static class ManifestInfo
    {

        String rsrcMainClass;
        String rsrcClassPath[];

        private ManifestInfo()
        {
        }

        ManifestInfo(ManifestInfo manifestinfo)
        {
            this();
        }
    }


    public JarRsrcLoader()
    {
    }

    public static void main(String args[])
        throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, IOException
    {
        ManifestInfo mi = getManifestInfo();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL.setURLStreamHandlerFactory(new RsrcURLStreamHandlerFactory(cl));
        URL rsrcUrls[] = new URL[mi.rsrcClassPath.length];
        for(int i = 0; i < mi.rsrcClassPath.length; i++)
        {
            String rsrcPath = mi.rsrcClassPath[i];
            if(rsrcPath.endsWith("/"))
                rsrcUrls[i] = new URL("rsrc:" + rsrcPath);
            else
                rsrcUrls[i] = new URL("jar:rsrc:" + rsrcPath + "!/");
        }

        ClassLoader jceClassLoader = new URLClassLoader(rsrcUrls, getParentClassLoader());
        Thread.currentThread().setContextClassLoader(jceClassLoader);
        Class c = Class.forName(mi.rsrcMainClass, true, jceClassLoader);
        Method main = c.getMethod("main", new Class[] {
            args.getClass()
        });
        main.invoke(null, new Object[] {
            args
        });
    }

    private static ClassLoader getParentClassLoader()
        throws InvocationTargetException, IllegalAccessException
    {
        Method platformClassLoader = java.lang.ClassLoader.class.getMethod("getPlatformClassLoader", null);
        return (ClassLoader)platformClassLoader.invoke(null, null);
        JVM INSTR pop ;
        return null;
    }

    private static ManifestInfo getManifestInfo()
        throws IOException
    {
        Enumeration resEnum = Thread.currentThread().getContextClassLoader().getResources("META-INF/MANIFEST.MF");
          goto _L1
_L3:
        ManifestInfo result;
        URL url = (URL)resEnum.nextElement();
        java.io.InputStream is = url.openStream();
        if(is == null)
            continue; /* Loop/switch isn't completed */
        result = new ManifestInfo(null);
        Manifest manifest = new Manifest(is);
        Attributes mainAttribs = manifest.getMainAttributes();
        result.rsrcMainClass = mainAttribs.getValue("Rsrc-Main-Class");
        String rsrcCP = mainAttribs.getValue("Rsrc-Class-Path");
        if(rsrcCP == null)
            rsrcCP = "";
        result.rsrcClassPath = splitSpaces(rsrcCP);
        if(result.rsrcMainClass != null && !result.rsrcMainClass.trim().equals(""))
            return result;
        continue; /* Loop/switch isn't completed */
        JVM INSTR pop ;
_L1:
        if(resEnum.hasMoreElements()) goto _L3; else goto _L2
_L2:
        System.err.println("Missing attributes for JarRsrcLoader in Manifest (Rsrc-Main-Class, Rsrc-Class-Path)");
        return null;
    }

    private static String[] splitSpaces(String line)
    {
        if(line == null)
            return null;
        List result = new ArrayList();
        int lastPos;
        for(int firstPos = 0; firstPos < line.length(); firstPos = lastPos + 1)
        {
            lastPos = line.indexOf(' ', firstPos);
            if(lastPos == -1)
                lastPos = line.length();
            if(lastPos > firstPos)
                result.add(line.substring(firstPos, lastPos));
        }

        return (String[])result.toArray(new String[result.size()]);
    }
}
