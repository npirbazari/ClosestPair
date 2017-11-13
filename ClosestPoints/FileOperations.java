package ClosestPoints;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOperations {

    public static String readTextFromFile(String path) {

        String text = "";
        File file = new File(path);

        if(file.exists()) {
            try {
                text = new String(Files.readAllBytes(Paths.get(path)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return text;
    }

    public static boolean writeTextToFile(String text,String fileName) {
        boolean result = false;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(text);
            bw.close();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static File[] findAllFiles( String location, String extension){
        File files = new File(location);

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(extension);
            }
        };

        return files.listFiles(filter);
    }
    
    public static File[] getAllTestFiles( String location, String extension){
        File files = new File(location);

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains("test") && name.endsWith(extension);
            }
        };

        return files.listFiles(filter);
    }
}
