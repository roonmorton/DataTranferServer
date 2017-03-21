/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTransfer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;

/**
 *
 * @author ron
 */
public class DataTransferServer implements Runnable{
    
    private static String HOST ="";
    private static int PUERTO = 8080;
    
    private int noClientes;
    
    private ServerSocket server;
    private Socket conexion;
    
    private DataOutputStream salida;
    
    byte[] datosRecibidos;
    int in;
    String nombreArchivo;
    private boolean estado;
    
    private JDesktopPane desk;
    
    public DataTransferServer(JDesktopPane desk) throws IOException{
        
        this.desk = desk;
        this.server = new ServerSocket(PUERTO);
        this.estado = true;
        noClientes = 0;
    }
    
    
    public int getNoClientes(){
        return this.noClientes;
    }
    
    public void escuchar() throws IOException, InterruptedException{
        while(estado){
            System.out.println("Escuchando...");
            conexion = server.accept();
            new Thread(new ManejadorCliente(conexion,desk)).start();
            
            Thread.sleep(100);
        }
    }
    
    
    public void detener(){
        estado = false;
    }

    @Override
    public void run() {
        try {
            escuchar();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            Logger.getLogger(DataTransferServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(DataTransferServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
