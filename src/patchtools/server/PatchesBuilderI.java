package patchtools.server;

import java.io.File;

public interface PatchesBuilderI {

	void buildPatches(File bundlesDir, File outputDir);
}
