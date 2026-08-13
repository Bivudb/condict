package condict;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Dictionary {
	HashMap<String, Entry> dict = new HashMap<>();

	String cLang;

	public HashMap<String, Entry> addEntry(String key, Entry entry) {
		dict.put(key, entry);
		return dict;
	}
	

	public boolean fileSaver(boolean entriesDone) {
		String sep = File.separator;
		String home = System.getProperty("user.home");
		String filePath = home + sep + "Documents" + sep + "Dictionary" + sep + cLang + ".txt";
		try (PrintWriter writer = new PrintWriter (new FileWriter(filePath))){
			for (Entry e : dict.values()) {
				writer.println(e.word + "|" + e.ipa + "|" + e.translation + "|" + e.pos);
			}
			System.out.println("Dictionary Saved");
			return entriesDone;
		} catch (IOException e) {
			System.out.println("Could not save");
			return false;
		}
	}
	
}
