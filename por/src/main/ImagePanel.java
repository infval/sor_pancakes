/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gil
 */
public class ImagePanel extends javax.swing.JLabel {

    float getScale() {
        return scale;
    }
    
    class Point{
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point() {
            x = y = 0;
        }
        
    }

    private BufferedImage image;
    private float scale = 1.f;
    private ArrayList<ArrayList<Point>> mapping;

    
    private static BufferedImage deepCopy(BufferedImage bi) {
//        ColorModel cm = bi.getColorModel();
//        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
//        WritableRaster raster = bi.copyData(null);
//        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        BufferedImage res = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0 ; x < bi.getWidth() ; ++x){
            for (int y = 0 ; y < bi.getHeight() ; ++y){
                res.setRGB(x, y, bi.getRGB(x, y));
            }
        }
        return res;
    }

    
    private void generateMap(){
        if (image == null || image.getWidth() < 16 || image.getHeight() == 0){
            mapping = null;
            return;
        }
        mapping = new ArrayList<ArrayList<Point>>(16);
        int[] originalColors = new int[16];
        for (int x = 0 ; x < 16 ; ++x){
            originalColors[x] = image.getRGB(x, 0);
            mapping.add(new ArrayList<Point>());
        }
        
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0 ; y < height ; ++y){
            for (int x = 0 ; x < width ; ++x){
                int color = image.getRGB(x, y);
                for (int i = 0 ; i < 16 ; ++i){
                    if (color == originalColors[i]){
                        mapping.get(i).add(new Point(x,y));
                        break;
                    }
                }
            }
        }
        
    }
    
    
    public void setImage(BufferedImage image) {
        if (image == null)
            this.image = null;
        else{
            this.image = deepCopy(image);
            setScale(scale);
            generateMap();
        }
        invalidate();
        repaint();
    }
    
    public void scale(float ammount){
        if (ammount+scale > 0)
            setScale(scale + ammount);
    }
    
    public void setScale(float scale){
        if (scale >0 && image != null){
            this.scale = scale;
            this.setBounds(0, 0, (int)(image.getWidth()*scale), (int)(image.getHeight()*scale));
            repaint();
        }
    }
    
    public void replaceColor(int id, Color color){
        if (image == null || color == null) return;
        ArrayList<Point> map = mapping.get(id);
        int newColor = color.getRGB();
        for(Point p: map){
            image.setRGB(p.x, p.y, newColor);
        }
        repaint();
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    /**
     * Creates new form ImagePanel
     */
    public ImagePanel() {
        initComponents();
    }

    @Override
    public void paint(Graphics g) {
        if (image != null)
            g.drawImage(image, 0, 0, (int)(image.getWidth()*scale), (int)(image.getHeight()*scale), null);
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        if (image != null)
            return new Dimension((int)(image.getWidth()*scale), (int)(image.getHeight()*scale));
        else return super.getPreferredSize();
    }

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
