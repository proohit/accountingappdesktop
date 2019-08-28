package gui.config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Configurator {

    public Configurator() {
    }

    private File openConfigFile() {
        File file = new File("config.cfg");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /*
        @returns the value of a given key from the config file. If the key is not found, null is returned
     */
    public String getValue(String key) {
        try {
            File file = openConfigFile();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String lineString;
            while ((lineString = br.readLine()) != null) {
                if (lineString.contains(key)) {
                    return lineString.split("=")[1];
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeEntry(String entry) {
        try {
            String[] entryStrings = entry.split("=");
            String key = entryStrings[0];
            File file = openConfigFile();
            String[] content = new String(Files.readAllBytes(Paths.get("config.cfg"))).split("\n");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            String newFile = "";
            for (String line : content) {
                if (line.contains(key)) {
                    continue;
                } else {
                    newFile += line + "\n";
                }
            }
            newFile += entry + "\n";
            bw.write(newFile);
            bw.close();
            fw.close();
        } catch (Exception e) {
        }

    }
    public String getDefaultDb() {
        return getValue("defaultDb");
    }
    public boolean isDefault(String path) {
        String value = getValue("defaultDb");
        if (value == null) return false;
        return value.equals(path);
    }
}
