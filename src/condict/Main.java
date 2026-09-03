package condict;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

class Main {

	public static void createDictionary(Scanner sc, Dictionary dict) {
		System.out.println("Would you like to add words(1), go back to the main menu(2)");
		String choice = sc.nextLine();

		Boolean entriesDone = false;
		while (!entriesDone) {
			if (choice.equals("1")) {

				System.out.println(
						"For any fields labled optional just press enter and nothing else if you do not wish to fill in the field");
				System.out.println("If you are finished type STOP to exit and save");
				String strChoice = sc.nextLine();
				if (strChoice.equalsIgnoreCase("STOP")) {
					entriesDone = dict.fileSaver();
				} else {
					System.out.println("What is your word called in its language:");
					String word = sc.nextLine();
					System.out.println("Enter pronunciation: ");
					String ipa = sc.nextLine();
					System.out.println("Enter translation into English: ");
					String translation = sc.nextLine();
					String key = translation;
					System.out.println("Enter part of speech: ");
					String pos = sc.nextLine();
					System.out.println("If this is a compound word type the words it is a compound of: (Optional)");
					String compoundInfo = sc.nextLine();
					System.out.println("If needed type a description: (Optional)");
					String Description = sc.nextLine();
					System.out.println(
							"If this word derives from another word through evolution list the language and word with a | inbetween(Optional)");
					String Etymology = sc.nextLine();
					System.out.println("Include the word in an example sentence(Optional): ");
					String exampleSentence = sc.nextLine();

					Entry entry = new Entry(word, ipa, translation, pos, compoundInfo, Etymology, Description,
							exampleSentence);

					dict.addEntry(key, entry);
				}
			} else if (choice.equals("2")) {
				entriesDone = true;
			} else {
				System.out.println("Invalid response");
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome to Condict");

		Boolean validChoice = false;
		while (!validChoice) {
			System.out.println("Would you like to create a new dictonary (1) or load up a existing one(2)?");
			String choice = sc.nextLine();

			if (choice.equals("1")) {

				Dictionary dict = new Dictionary();
				System.out.println("What is the name of your language?");
				dict.cLang = sc.nextLine();
				createDictionary(sc, dict);
			} else if (choice.equals("2")) {
				String sep = File.separator;
				String home = System.getProperty("user.home");
				File folder = new File(home + sep + "Documents" + sep + "Dictionary");
				File[] files = folder.listFiles();
				if (files == null || files.length == 0) {
					System.out.println("No Saves found");
				} else {
					for (int i = 0; i < files.length; i++) {
						System.out.println((i + 1) + ". " + files[i].getName());
					}
					System.out.println("Which dictionary do you want to load");
					int ichoice = 0;
					boolean catchfound = false;
					try {
						ichoice = sc.nextInt();
						sc.nextLine();
					} catch (InputMismatchException e) {
						System.out.println("Not a number");
						sc.nextLine();
						catchfound = true;
					}

					if (catchfound == false) {
						int index = ichoice - 1;
						File loadFiles = null;
						try {
							loadFiles = files[index];
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("Invalid Answer");
						}

						if (!(loadFiles == null)) {
							String Lang = loadFiles.getName().replace(".txt", "");

							Dictionary dict = new Dictionary();
							dict.fileLoader(loadFiles, Lang, sc, dict);
						}
					}
				}
			} else {
				System.out.println("Invalid choice");
			}
		}
	}
}