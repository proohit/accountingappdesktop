package gui.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configurator {

	public Configurator() {
	}
	private File openConfigFile() {
		File file = new File("config.cfg");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
	public void writeEntry(String entry) {
		try {
			String[] entryStrings = entry.split("=");
			String key = entryStrings[0];
			File file = openConfigFile();
			FileReader fr = new FileReader(file);
			FileWriter fw = new FileWriter(file);
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			String lineString;
			String newFile = "";
			while ((lineString = br.readLine()) != null) {
				if (lineString.contains(key))
					continue;
				else {
					newFile+=lineString+"\n";
				}
			}
			newFile+=entry+"\n";
			fw.write(newFile);
			bw.close();
			br.close();
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
