// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RsrcURLStreamHandler.java

package org.eclipse.jdt.internal.jarinjarloader;

import java.io.IOException;
import java.net.*;

// Referenced classes of package org.eclipse.jdt.internal.jarinjarloader:
//            RsrcURLConnection

public class RsrcURLStreamHandler extends URLStreamHandler
{

    public RsrcURLStreamHandler(ClassLoader classLoader)
    {
        this.classLoader = classLoader;
    }

    protected URLConnection openConnection(URL u)
        throws IOException
    {
        return new RsrcURLConnection(u, classLoader);
    }

    protected void parseURL(URL url, String spec, int start, int limit)
    {
        String file;
        if(spec.startsWith("rsrc:"))
            file = spec.substring(5);
        else
        if(url.getFile().equals("./"))
            file = spec;
        else
        if(url.getFile().endsWith("/"))
            file = url.getFile() + spec;
        else
        if("#runtime".equals(spec))
            file = url.getFile();
        else
            file = spec;
        setURL(url, "rsrc", "", -1, null, null, file, null, null);
    }

    private ClassLoader classLoader;
}
