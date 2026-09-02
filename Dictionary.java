package condict;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Dictionary {
	HashMap<String, Entry> dict = new HashMap<>();

	String cLang;

	public void editWord(Scanner sc, Entry option) {
		System.out.println(
				"For any fields labled optional just press enter and nothing else if you do not wish to fill in the field");
		System.out.println("If you are finished type STOP to exit and save");
		String strChoice = sc.nextLine();
		if (strChoice.equalsIgnoreCase("STOP")) {
		} else {
			System.out.println("What is your word called in its language:");
			System.out.println(option.word);
			String word = sc.nextLine();
			System.out.println("Enter pronunciation: ");
			System.out.println(option.ipa);
			String ipa = sc.nextLine();
			System.out.println(
					"Enter translation into English(If you cahgne this instead of editing the existing word a new word will be created): ");
			System.out.println(option.translation);
			String translation = sc.nextLine();
			String key = translation;
			System.out.println("Enter part of speech: ");
			System.out.println(option.pos);
			String pos = sc.nextLine();
			System.out.println("If this is a compound word type the words it is a compound of (Optional):");
			System.out.println(option.compoundInfo);
			String compoundInfo = sc.nextLine();
			System.out.println("If needed type a description (Optional):");
			System.out.println(option.Description);
			String Description = sc.nextLine();
			System.out.println(
					"If this word derives from another word through evolution list the language and word (Optional): ");
			System.out.println(option.Etymology);
			String Etymology = sc.nextLine();
			System.out.println("Include the word in an example sentence(Optional): ");
			System.out.println(option.exampleSentence);
			String exampleSentence = sc.nextLine();

			Entry entry = new Entry(word, ipa, translation, pos, compoundInfo, Etymology, Description, exampleSentence);

			addEntry(key, entry);
		}
	}

	public void displayList(int choice, Scanner sc) {
		int displaySize = 50;
		boolean doneEditing = false;
		while (doneEditing != true) {
			ArrayList<Entry> wordList = new ArrayList<>();
			for (Entry entry : dict.values()) {
				wordList.add(entry);
			}

			for (int i = 0; i < wordList.size() && i < displaySize; i++) {
				Entry entry = wordList.get(i);
				System.out.println((i + 1) + ". " + entry);
			}

			String strchoice;
			int ichoice = 0;
			
			if (choice == 2) {
				
				System.out.println(
						"Type the number of the word you would like to delete or type MORE to display more entries or type EXIT to leave");
				strchoice = sc.nextLine();

				try {
					ichoice = Integer.parseInt(strchoice);
				} catch (NumberFormatException e) {
					if (strchoice.equalsIgnoreCase("More")) {
						displaySize = displaySize + 25;
					} else if (strchoice.equalsIgnoreCase("exit")) {
						doneEditing = true;
					} else {
						System.out.println("Invalid Answer");
					}
				}

				if (!strchoice.equalsIgnoreCase("more") && !strchoice.equalsIgnoreCase("exit")) {
					if (ichoice > displaySize || ichoice > wordList.size()) {
						System.out.println("Number too large");
					} else {

						ichoice = ichoice - 1;
						Entry option = wordList.get(ichoice);
						System.out.println("If you are finished editing type STOP to exit");
						strchoice = sc.nextLine();
						if (strchoice.equalsIgnoreCase("stop")) {
							doneEditing = fileSaver();
						} else {
							editWord(sc, option);
						}
					}
				} 
			}else {
					strchoice = "";
					ichoice = 0;
					System.out.println(
							"Type the number of the word you would like to delete or type MORE to display more entries or type EXIT to leave");
					strchoice = sc.nextLine();

					try {
						ichoice = Integer.parseInt(strchoice);
					} catch (NumberFormatException e) {
						if (strchoice.equalsIgnoreCase("More")) {
							displaySize = displaySize + 25;
						} else if (strchoice.equalsIgnoreCase("exit")) {
							doneEditing = true;
						} else {
							System.out.println("Invalid Answer");
						}
					}
					if (!strchoice.equalsIgnoreCase("more") && !strchoice.equalsIgnoreCase("exit")) {
						if (ichoice > displaySize || ichoice > wordList.size()) {
							System.out.println("Number too large");
						} else {
							boolean validAnswer = false;
							while (validAnswer == false) {
								System.out.println("Are you sure you want to delete this word?");
								strchoice = sc.nextLine();
								if (strchoice.equalsIgnoreCase("yes")) {
									validAnswer = true;
									Entry option = wordList.get(ichoice);
									System.out.println("If you are finished deleting words type STOP to exit");
									strchoice = sc.nextLine();
									if (strchoice.equalsIgnoreCase("stop")) {
										doneEditing = fileSaver();
									} else {
										String key = option.translation;
										dict.remove(key);
									}

								} else if (strchoice.equalsIgnoreCase("no")) {
									validAnswer = true;
								} else {
									System.out.println("Invalid Answer");
								}
							}
						}
					}
				}
		}
	}

	public void searchWord(String item) {
		Entry word = dict.get(item);
		if (word != null) {
			System.out.println("Word Found");
			System.out.println(word);
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
		boolean fileFinished = false;
		while (fileFinished == false) {
			try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = null;
					parts = line.split("\\|");
					String word = parts[0];
					String ipa = (parts.length >= 2) ? parts[1] : "";
					String translation = (parts.length >= 3) ? parts[2] : "";
					String pos = (parts.length >= 4) ? parts[3] : "";
					String Etymology = (parts.length >= 5) ? parts[4] : "";
					String Description = (parts.length >= 6) ? parts[5] : "";
					String exampleSentence = (parts.length >= 7) ? parts[6] : "";
					String compoundInfo = (parts.length >= 8) ? parts[7] : "";
					Entry entry = new Entry(word, ipa, translation, pos, compoundInfo, Etymology, Description,
							exampleSentence);

					dict.addEntry(translation, entry);

				}
			} catch (FileNotFoundException e) {
				System.out.println("File does not exist");
			} catch (IOException e) {
				System.out.println("Unable to to read file");
			}
			System.out.println(
					"What you like to do with your file? 1. Add words 2. Edit words 3. Delete words 4. Search up words 5. Go Back");
			String str = "";
			str = sc.nextLine();
			int choice = 0;
			try {
				choice = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println("Invalid Answer");
			}
			if (choice > 5) {
				System.out.println("Number too large");
			}
				if (choice == 1) {
					Main.createDictionary(sc, dict);
				} else if (choice == 2) {
					displayList(choice, sc);
				} else if (choice == 3) {
					displayList(choice, sc);
				} else if (choice == 4) {
					System.out.println("Type in the word you would like to search for (enter English translation)");
					String item = sc.nextLine();

					searchWord(item);
				} else if (choice == 5) {
					fileFinished = true;
				} else {
					System.out.println("Invalid Answer");
				}
		}
	}
} 
