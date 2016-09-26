package patchtools.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import patchtools.utils.MD5Helper;
import patchtools.utils.diff_match_patch;
import patchtools.utils.diff_match_patch.Patch;

/**
 * Created by Mars.
 */
public class AppPatchManager {

	private diff_match_patch tools = new diff_match_patch();

	private MD5Helper md5Helper = new MD5Helper();

	public String bundleMd5() {
		return md5Helper.md5(bundleFile());
	}

	public File bundleFile() {
		return new File("patch/appfolder/bundle.bundle");
	}


	public void update(File patchFile) {
		byte[] buf = new byte[(int) patchFile.length()];
		try (InputStream in = new FileInputStream(patchFile)) {
			in.read(buf);
			update(new String(buf, "utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String bundleBaseDir = "patch/appfolder/";
	private String backUpBaseDir = bundleBaseDir + "backup/";
	private String bundleFilePath = bundleBaseDir + "bundle.bundle";

	public void backUp() throws IOException {
		InputStream input = new FileInputStream(new File(bundleFilePath));
		OutputStream output = new FileOutputStream(new File(backUpBaseDir
				+ System.currentTimeMillis() + ".bundle"));
		IOUtils.copy(input, output);
		IOUtils.closeQuietly(input);
		IOUtils.closeQuietly(output);
	}

	public void update(String patchText) {
		List<Patch> patchesFromText = tools.patch_fromText(patchText);
		if (patchesFromText instanceof LinkedList) {
			update((LinkedList<Patch>) patchesFromText);
		} else {
			LinkedList<Patch> patches = new LinkedList<Patch>();
			for (Patch patch : patchesFromText) {
				patches.add(patch);
			}
			update(patches);
		}
	}

	public void update(LinkedList<Patch> patches) {
		Object[] result = tools.patch_apply(patches, bundleText());
		String newBundleText = (String) result[0];
		try(FileOutputStream output = new FileOutputStream(bundleFilePath)){
			IOUtils.write(newBundleText.getBytes("utf-8"), output);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public String bundleText() {
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

}
