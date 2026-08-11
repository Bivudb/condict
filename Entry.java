package condict;

public class Entry {
	String translation;
	String word;
	String pos;
	String ipa;

	public Entry(String word, String ipa, String translation, String pos) {
		this.word = word;
		this.ipa = ipa;
		this.translation = translation;
		this.pos = pos;
	}

}