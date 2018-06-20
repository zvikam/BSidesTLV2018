// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RsrcURLStreamHandlerFactory.java

package org.eclipse.jdt.internal.jarinjarloader;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

// Referenced classes of package org.eclipse.jdt.internal.jarinjarloader:
//            RsrcURLStreamHandler

public class RsrcURLStreamHandlerFactory
    implements URLStreamHandlerFactory
{

    public RsrcURLStreamHandlerFactory(ClassLoader cl)
    {
        classLoader = cl;
    }

    public URLStreamHandler createURLStreamHandler(String protocol)
    {
        if("rsrc".equals(protocol))
            return new RsrcURLStreamHandler(classLoader);
        if(chainFac != null)
            return chainFac.createURLStreamHandler(protocol);
        else
            return null;
    }

    public void setURLStreamHandlerFactory(URLStreamHandlerFactory fac)
    {
        chainFac = fac;
    }

    private ClassLoader classLoader;
    private URLStreamHandlerFactory chainFac;
}
