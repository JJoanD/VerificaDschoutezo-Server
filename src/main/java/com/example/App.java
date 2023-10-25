package com.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Server servente = new Server();
        servente.attendi();
        try{
            servente.comunica();
        }catch(Exception exception){}
    }
}
