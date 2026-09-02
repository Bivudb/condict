package condict;

public class Entry {
	String translation;
	String word;
	String pos;
	String ipa;
	String compoundInfo;
	String Description;
	String Etymology;
	String exampleSentence;

	Entry(String word, String ipa, String translation, String pos, String compoundInfo, String Etymology,
			String Description, String exampleSentence) {
		this.word = word;
		this.ipa = ipa;
		this.translation = translation;
		this.pos = pos;
		this.compoundInfo = compoundInfo;
		this.Description = Description;
		this.Etymology = Etymology;
		this.exampleSentence = exampleSentence;
	}

	public String toString() {
		String posPart = pos.equals("") ? "" : " (" + pos + ")";
		String compoundPart = compoundInfo.equals("") ? "" : compoundInfo;
		String descPart = Description.equals("") ? "" : "(" + Description + ")";
		String etymologyPart = Etymology.equals("") ? "" : Etymology;
		String examplePart = exampleSentence.equals("") ? "" : "[" + exampleSentence + "]";

		return word + " [" + ipa + "] " + translation + posPart + compoundPart + descPart + etymologyPart + examplePart;
	}
}
