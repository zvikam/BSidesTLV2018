// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RsrcURLConnection.java

package org.eclipse.jdt.internal.jarinjarloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class RsrcURLConnection extends URLConnection
{

    public RsrcURLConnection(URL url, ClassLoader classLoader)
    {
        super(url);
        this.classLoader = classLoader;
    }

    public void connect()
        throws IOException
    {
    }

    public InputStream getInputStream()
        throws IOException
    {
        String file = URLDecoder.decode(super.url.getFile(), "UTF-8");
        InputStream result = classLoader.getResourceAsStream(file);
        if(result == null)
            throw new MalformedURLException("Could not open InputStream for URL '" + super.url + "'");
        else
            return result;
    }

    private ClassLoader classLoader;
}
