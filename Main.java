package condict;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Dictionary {
	HashMap<String, Entry> dict = new HashMap<>();

	String cLang;
	
	
	public void searchWord(String item) {
		Entry word = dict.get(item);
		if (word != null) {
			System.out.println("Word Found");	
			System.out.println(item);
		} else {
			System.out.println("Unable to find word with that translation");
		}
	}
	
	public HashMap<String, Entry> addEntry(String key, Entry entry) {
		dict.put(key, entry);
		return dict;
	}

	public boolean fileSaver() {
		String sep = File.separator;
		String home = System.getProperty("user.home");
		String filePath = home + sep + "Documents" + sep + "Dictionary" + sep + cLang + ".txt";
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			for (Entry e : dict.values()) {
				writer.println(e.word + "|" + e.ipa + "|" + e.translation + "|" + e.pos + "|" + e.Etymology + "|"
						+ e.Description + "|" + e.exampleSentence + "|" + e.compoundInfo);
			}
			System.out.println("Dictionary Saved");
			return true;
		} catch (IOException e) {
			System.out.println("Could not save");
			return false;
		}
	}

	public void fileLoader(File loadFiles, String Lang, Scanner sc, Dictionary dict) {
		String sep = File.separator;
		String home = System.getProperty("user.home");
		String filePath = home + sep + "Documents" + sep + "Dictionary" + sep + Lang + ".txt";

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = null;
				parts = line.split("\\|");
				String word = parts[0];
				String ipa = parts[1];
				String translation = parts[2];
				String pos = parts[3];
				String Etymology = parts[4];
				String Description = parts[5];
				String exampleSentence = parts[6];
				String compoundInfo = parts[7];
				Entry entry = new Entry(word, ipa, translation, pos, Etymology, Description, exampleSentence,
						compoundInfo);

				dict.addEntry(translation, entry);

			}
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		} catch (IOException e) {
			System.out.println("Unable to to read file");
		}
		System.out.println(
				"What you like to do with your file? 1. Add words 2. Edit words 3. Delete words 4. Search up words");
		int choice = 0;
		try {
			choice = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Not a number");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Number too large");
		}

		if (choice == 1) {
			Main.createDictionary(sc, dict);
		} else if (choice == 2) {
			
		} else if (choice == 3) {
		
		} else if (choice == 4) { 
			System.out.println("Type in the word you would like to search for (enter English translation)");
			String item = sc.nextLine();
			sc.nextLine();
			
			searchWord(item);
		}
	}
}
