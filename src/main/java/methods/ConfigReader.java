package methods;

import java.io.File;
import java.util.TreeMap;

public interface ConfigReader {
	 TreeMap<String, Config> readConfig(); //Abstract readConfig method to be overriden by ConfigHandler
}
