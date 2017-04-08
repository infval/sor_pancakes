/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.map;

import java.io.*;
import lib.RandomDataStream;



/**
 *
 * @author Gil
 */
public class Decompressor {
    private static final String TEMP_FILE_NAME = "temp.bin";
    
    public static RandomDataStream run(String romName, long artAddress) throws Exception{
        String[] env = new String[4];
        env[0] = "data\\nemdec.exe";
        env[1] = romName;
        env[2] = TEMP_FILE_NAME;
        env[3] = Long.toHexString(artAddress);
        Process p = Runtime.getRuntime().exec(env);
        try {
            p.waitFor();
        } catch (InterruptedException ex) {
            throw new Exception("Art decompression failed");
        }
        File tempFile = new File(TEMP_FILE_NAME);
        FileInputStream fis = new FileInputStream(tempFile);
        RandomDataStream out = new RandomDataStream();
        while (fis.available() > 0){
            out.write((byte)fis.read());
        }
        fis.close();
        tempFile.delete();        
        return out;
    }
        
}
