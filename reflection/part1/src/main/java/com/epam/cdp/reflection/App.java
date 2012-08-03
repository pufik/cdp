package com.epam.cdp.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.epam.cdp.reflection.handler.CustomInvocationHandler;
import com.epam.cdp.reflection.sources.part1.LevelDispatcher;
import com.epam.cdp.reflection.sources.part1.impl.BaseLevelDispatcher;

public class App 
{
	public static void main( String[] args )
    {
        InvocationHandler invocationHandler = new CustomInvocationHandler(new BaseLevelDispatcher());
        LevelDispatcher dispatcher = (LevelDispatcher) Proxy.newProxyInstance(BaseLevelDispatcher.class.getClassLoader(), new Class[]{LevelDispatcher.class}, invocationHandler);
        
        try {
			dispatcher.start("LEVEL 1");
			dispatcher.stop("LEVEL 1");
	        dispatcher.pause("LEVEL 1", 2L);
		} catch (Exception e) {
			// TODO Add logger
			e.printStackTrace();
		}  
    }
}
