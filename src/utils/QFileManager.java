package utils;

import java.io.*;
import java.nio.file.Paths;

/**
 * @Author Unsal Ozturk
 * @Version 20190501
 * File I/O wrapper for simple input-output to working directory
 * Allows concurrent access through a monitor
 */
public class QFileManager {
    public static boolean DEBUG = true;
    public static synchronized void write(Serializable obj, String directory, String fileName) {
        String path = Paths.get(".").toAbsolutePath().normalize().toString();
        try {
            File f = new File(fileName);
            File dir = new File(path + directory);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            FileOutputStream file = new FileOutputStream(path + directory + fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(obj);
            out.close();
            file.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized Object read(String directory, String fileName) {
        String path = Paths.get(".").toAbsolutePath().normalize().toString();
        try {
            FileInputStream file = new FileInputStream(path + directory + fileName);
            ObjectInputStream out = new ObjectInputStream(file);
            Object obj = out.readObject();
            out.close();
            file.close();
            return obj;

        } catch(Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static synchronized boolean delete(String directory, String fileName) {
        String path = Paths.get(".").toAbsolutePath().normalize().toString();
        File file = new File(path + directory + fileName);
        return file.delete();
    }
}
