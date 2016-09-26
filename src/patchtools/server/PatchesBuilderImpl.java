package patchtools.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PatchesBuilderImpl implements PatchesBuilderI{
	
	@Override
	public void buildPatches(File bundlesDir, File outputDir) {
		Properties config = new Properties();
		try (FileInputStream input = new FileInputStream(new File(bundlesDir,"config.properties"))){
			config.load(input);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
