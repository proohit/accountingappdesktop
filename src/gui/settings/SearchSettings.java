package gui.settings;

import gui.Ui;

public class SearchSettings {
	static boolean isGlobalSearch = true;
	
	
	public static void setGlobalSearch(boolean isTrue) {
		isGlobalSearch=isTrue;
		Ui.configurator.writeEntry("globalSearch=" + isTrue);
	}
	
	public static boolean isGlobalSearch() {
		return isGlobalSearch;
	}
}
