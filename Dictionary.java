package condict;

import java.util.Map;
import java.util.HashMap;

public class Dictionary {
	Map<String, Entry> dict = new HashMap<>();

	String cLang;

	public HashMap<String, Entry> addEntry(String key, Entry entry) {
		dict.put(key, entry);
	}
}