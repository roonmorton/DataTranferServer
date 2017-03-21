/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTransfer;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;

/**
 *
 * @author ron
 */
public class ManejadorCliente implements Runnable{
    
    private Socket cliente;
    private boolean estado; 
    
    BufferedInputStream bis;
    BufferedOutputStream bos;
    private byte[] datosRecibidos;
    int in;
    String nombreArchivo;
    JDesktopPane visor;
    Visor v;
    
    public ManejadorCliente(Socket cliente, JDesktopPane visor){
        this.cliente = cliente;
        this.estado = true;
        this.visor = visor;
        v = new Visor();
        visor.add(v);
        v.setVisible(true);
    }

    @Override
    public void run() {
        
        
        try {
            
            while(estado){
                
                //datosRecibidos = new byte[1024];
                //bis = new BufferedInputStream(cliente.getInputStream());
                
                DataInputStream dis = new DataInputStream(cliente.getInputStream());
                
                //nombreArchivo = dis.readUTF();
                //nombreArchivo = nombreArchivo.substring(nombreArchivo.indexOf('\\')+1,nombreArchivo.length());
                
                
                /*bos = new BufferedOutputStream(new FileOutputStream("img.jpg"));
                bis.read();
                while((in = bis.read(datosRecibidos)) != -1){
                    bos.write(datosRecibidos,0,in);
                }*/
                
                System.out.println("escuchando cliente");
                BufferedImage buff = ImageIO.read(dis);
                 //BufferedImage bufferedImage = ImageIO.read(socket.getInputStream());
                 //ImageIO.write(buff,"png", new FileOutputStream("image.png"));
            System.out.println("recibiendo imagen");
                
                //ImageIO.write(webcam.getImage(), "PNG", new File(String.format("test-%d.png", i)));
                
                v.setIconImage(buff);
                Thread.sleep(60);
                
                //cerrarConexion();
                //while((in = bis.read(datosRecibidos)) != -1){
                    // leer imagen
                    
               // }
                // setiar imagen al panel
                
                //bos.close();
                //dis.close();
                
            }
            cerrarConexion();            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            System.err.println(ex);
            //Logger.getLogger(ManejadorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ManejadorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cerrarConexion() throws IOException{
        if(cliente != null)
            cliente.close();
    }
    
    public void detener(){
        this.estado = false;
    }
    
    
    
    
    
}
