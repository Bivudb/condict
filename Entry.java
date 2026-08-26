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
	
	Entry(String word, String ipa, String translation, String pos, String compoundInfo, String Etymology, String Description, String exampleSentence) {
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
	    return word + " [" + ipa + "] - " + translation + " (" + pos + ")" + compoundInfo + "(" + Description + ")" + Etymology + "[" + exampleSentence + "]";
	}
}
