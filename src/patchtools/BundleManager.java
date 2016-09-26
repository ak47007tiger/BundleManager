package patchtools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

public class BundleManager {
	
	public File bundleFile() {
		return new File("patch/appfolder/bundle.bundle");
	}
	
	public String getFileText() {
		File bundleFile = bundleFile();
		try (InputStream in = new FileInputStream(bundleFile)) {
			byte[] buf = new byte[(int) bundleFile.length()];
			in.read(buf);
			return new String(buf, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void backUp(String in, String out) throws IOException {
		InputStream input = new FileInputStream(new File(in));
		OutputStream output = new FileOutputStream(new File(out));
		IOUtils.copy(input, output);
		IOUtils.closeQuietly(input);
		IOUtils.closeQuietly(output);
	}
}
