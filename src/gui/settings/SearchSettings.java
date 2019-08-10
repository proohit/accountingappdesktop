package gui.settings;

public class SearchSettings {
	static boolean isGlobalSearch = false;
	
	
	public static void setGlobalSearch(boolean isTrue) {
		isGlobalSearch=isTrue;
	}
	
	public static boolean isGlobalSearch() {
		return isGlobalSearch;
	}
}
