/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AUTONOMA
 */
public class Servidor extends javax.swing.JApplet implements Runnable{
    
    static final int puerto=5000;
    ServerSocket skServidor;
    DataInputStream flujoEntrada;
    DataOutputStream flujoSalida;
    Socket skCliente;
    Thread hiloServidor;
    
    @Override
    public void init() {
        try {
            initComponents();
            
            
            skServidor =new ServerSocket(puerto);
                
            }
        catch (Exception ex) {
            
        }
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOn.setText("Iniciar Sesión");
        btnOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnActionPerformed(evt);
            }
        });
        getContentPane().add(btnOn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 38, 331, -1));

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 67, 331, 186));
    }// </editor-fold>//GEN-END:initComponents

    private void btnOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnActionPerformed
        // TODO add your handling code here:
        
        try {
            txtArea.append("\nEstoy escuchando ... "+puerto);
            skCliente=skServidor.accept();
            
            flujoEntrada=new DataInputStream(skCliente.getInputStream());
            flujoSalida=new DataOutputStream(skCliente.getOutputStream());
            hiloServidor=new Thread(this);
            hiloServidor.start();
            
            
        } catch (Exception ex) {
            
        }
        
    }//GEN-LAST:event_btnOnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try
        {
            while(true)
            {
                String msg=flujoEntrada.readUTF();
                txtArea.append(""+msg);
                
            }
        }catch(Exception e)
        {
            try
            {    
            txtArea.append("\nCliente desconectado...");
            skCliente.close();
            }catch(Exception a){}        
        }
    }
}
