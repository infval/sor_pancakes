/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import lib.map.Tile;
import lib.map.Sprite;
import lib.map.Piece;
import lib.map.Palette;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import lib.anim.AnimFrame;
import lib.anim.Animation;




/**
 *
 * @author Gil
 */
public class TilesTest extends javax.swing.JFrame {
    Palette palette;
    Rom rom;
    
    private void loadRom(){
        try {
            rom = new Rom(new File("sor2.bin"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TilesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadPalette(){
        try {
            palette = rom.readPalette(42464);
        } catch (IOException ex) {
            Logger.getLogger(TilesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    //        palette.setColor(0, fromHex("0000"));
    //        palette.setColor(1, fromHex("0eee"));
    //        palette.setColor(2, fromHex("0222"));
    //        palette.setColor(3, fromHex("06ae"));
    //        palette.setColor(4, fromHex("026a"));
    //        palette.setColor(5, fromHex("0026"));
    //        palette.setColor(6, fromHex("002e"));
    //        palette.setColor(7, fromHex("0008"));
    //        
    //        palette.setColor(8, fromHex("0eaa"));        
    //        palette.setColor(9, fromHex("0c66"));
    //        palette.setColor(10, fromHex("0842"));
    //        palette.setColor(11, fromHex("0aaa"));
    //        palette.setColor(12, fromHex("0666"));
    //        palette.setColor(13, fromHex("00ee"));
    //        palette.setColor(fromHex("0000"));
    //        palette.setColor(fromHex("0000"));
    }
    
    /**
     * Creates new form TilesTest
     */
    public TilesTest() {
        initComponents();
        label.setText("");
        loadRom();
        loadPalette();
        try {
//            Tile tile = rom.readTile(530288);
//            Piece piece = rom.readPiece(530288, 2, 4);
//            Sprite sprite = rom.readSprite(524288, 530288);
//            AnimFrame animFrame = rom.readAnimFrame(0x0F9D4E); // max standing
//            AnimFrame animFrame = rom.readAnimFrame(0xfb09a); // blaze being shocked
            
//            AnimFrame animFrame = rom.readAnimFrame(0xfbc32); // skate jumpdown -> fail
//            AnimFrame animFrame = rom.readAnimFrame(0xfbaaa);   // front grab back attack -> fail
            
//            AnimFrame animFrame = rom.readAnimFrame(0xfba5c);  // skate being front slammed by blaze
//            AnimFrame animFrame = rom.readAnimFrame(0xfba82);  // skate being thrown by max -> fail
            
            
//            AnimFrame animFrame = rom.readAnimFrame(0xFA026);
            
            lib.anim.Character character = rom.readCharacter(0x20EAC, 0x4940, 0x11024, 1, 6,0);
            AnimFrame animFrame = character.getAnimation(3).getFrame(2);
            
//            AnimFrame animFrame = rom.readAnimFrame(0xfbc32); // skate jumpdown -> fail
            System.out.println(Long.toHexString(animFrame.artAddress/2)+ "*2 = " + Long.toHexString(animFrame.artAddress));
            Sprite sprite = rom.readSprite(animFrame.mapAddress, animFrame.artAddress);
            
            
//              Animation anim = rom.readAnimation(0xfaf00);
//              AnimFrame animFrame = anim.getFrame(3);
//              Sprite sprite = rom.readSprite(animFrame.mapAddress, animFrame.artAddress);
              
              //label.setIcon( new ImageIcon( tile.asImage(palette) ));
              //label.setIcon( new ImageIcon( piece.asImage(palette) ));
              label.setIcon( new ImageIcon( sprite.asImage(palette) ));

        } catch (Exception ex) {
            Logger.getLogger(TilesTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TilesTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TilesTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TilesTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TilesTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TilesTest().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables
}
