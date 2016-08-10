package methods;

import java.io.File;
import java.util.TreeMap;

public interface ConfigWriter {
	void writeConfig( TreeMap<String, Config> configMap ); //Abstract writeConfig method to be overridden by ConfigHandler
}
