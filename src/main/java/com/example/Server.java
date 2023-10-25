package com.example;

import java.io.*;
import java.net.*;

public class Server {

    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    int x ;
    int y;
    int operazione;

    public Socket attendi(){
       
        try{
            System.out.println("1 SERVER partito in esecuzione");
            //creo un server sulla porta 6789
            if(server == null){
                server = new ServerSocket(6789);
            }
            //rimane in attesa di un client
            client = server.accept();
            //non va chiuso il server cosi da poter inibire altri client

            //associo due oggetti al coket del client per effetuare la scrittura e la lettura , ossia gli stream
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }

    public void comunica() throws Exception{
        for(;;){
        stringaRicevuta = inDalClient.readLine();
        x = Integer.parseInt(stringaRicevuta);
        System.out.println("ricevuto primo = " + x);

        outVersoClient.writeBytes("ricevuto primo" + "\n");
        System.out.println("aspetto secondo");

        stringaRicevuta = inDalClient.readLine();
        y = Integer.parseInt(stringaRicevuta);
         System.out.println("ricevuto  secondo = " + y);

        System.out.println("aspetto scelta operazione");

        stringaRicevuta = inDalClient.readLine();
        if(stringaRicevuta.equals("+")){
              operazione = x + y;
        }
        else if(stringaRicevuta.equals("-")){
            operazione = x - y;
        }
        else if(stringaRicevuta.equals("/")){
            operazione = x/ y;
        }
        else if(stringaRicevuta.equals("*")){
            operazione = x*y;
        }
        else{
            System.out.println("operazione non esistente");
            outVersoClient.writeBytes("operazione non esistente" + "\n");
            break;
        }
        System.out.println("Risultato = " +operazione);
        outVersoClient.writeBytes( operazione + "\n");

          stringaRicevuta = inDalClient.readLine();
          if(stringaRicevuta.equals("BYE")){
            System.out.println("Chiusura scoket");
            client.close();
          }
            
          }
    }
        
}
