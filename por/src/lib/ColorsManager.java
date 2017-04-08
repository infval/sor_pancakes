/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Gil
 */
public class ColorsManager {    
    private Palette palette;
    private long paletteAddress;
    private String fileName;
    private boolean modified;
    
    public ColorsManager(String romFile) throws FileNotFoundException{
        fileName = romFile;        
    }   
    
    public void closePalette() throws IOException{
        if (modified){
            save();
        }        
    }
    
    public void openPalette(long address, int type) throws IOException{
        if (address == paletteAddress) return;
        closePalette();
        Rom rom = new Rom(fileName);
        palette = rom.readPalette(address, type);
        paletteAddress = address;
        rom.close();
    }
    
    public long findPalette(long address, int type) throws IOException{
        if (address == paletteAddress) address+=4;
        closePalette();
        Rom rom = new Rom(fileName);
        long newAddress = rom.find(address);
        rom.close();
        System.out.println("got: " + newAddress);                
        return newAddress;
    }
    
    public Color getColor(int offset){
        if (palette == null) return null;
        return new Color(palette.getColor(offset));
    }   
    
    public void setColor(int offset, Color color){
        
        // modify color
        Color oldColor = getColor(offset);
        if (oldColor.equals(color)) return;
        palette.setColor(offset, color.getRGB());
        modified = true;                
    }
               
    public boolean hasPendingModifications(){
        return modified;
    }
    

    
    public void save() throws IOException{
        Rom rom = new Rom(fileName);
        rom.writePalette(palette, paletteAddress);
        rom.close();
        modified = false;
    }

    
}
