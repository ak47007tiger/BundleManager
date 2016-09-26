package patchtools.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import patchtools.Update;
import patchtools.UpdateInfo;
import patchtools.utils.diff_match_patch;

import com.alibaba.fastjson.JSON;

public class ServerPatchManager {

	private diff_match_patch tools = new diff_match_patch();

	private Update update;

	public ServerPatchManager() {

	}

	public String getFileText(File file) {
		byte[] buf = new byte[(int) file.length()];
		try (InputStream in = new FileInputStream(file)) {
			in.read(buf);
			return new String(buf, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Properties loadConfig(File file) throws IOException {
		Properties config = new Properties();
		FileInputStream input = new FileInputStream(file);
		config.load(input);
		input.close();
		return config;
	}

	public void work(String bundlesPath, String outputDir) throws IOException {
		File bundleFilesDir = new File(bundlesPath, "bundleFiles");
		Properties config = loadConfig(new File(bundlesPath,
				"config.properties"));
		update = new Update();
		update.setLastInstallUrl(config.getProperty("lastInstallUrl"));
		update.setLastApkVersion(config.getProperty("lastApkVersion"));
		update.setLastApkVersionCode(Integer.parseInt(config
				.getProperty("lastApkVersionCode")));
		update.setLastBundleVersion(config.getProperty("lastBundleVersion"));
		update.setLastBundleVersionCode(Integer.parseInt(config
				.getProperty("lastBundleCodeVersion")));

		Map<String, UpdateInfo> updateInfos = new HashMap<String, UpdateInfo>();
		update.setUpdateInfos(updateInfos);

		String patchesBaseUrl = config.getProperty("patchesBaseUrl");

		String lastBundleText = getFileText(new File(bundleFilesDir,
				Integer.toString(update.getLastBundleVersionCode())));

		File[] bundleFiles = bundleFilesDir.listFiles();
		
		for (File file : bundleFiles) {
			String key = file.getName();
			UpdateInfo updateInfo = new UpdateInfo();
			if (Integer.parseInt(key) == update.getLastBundleVersionCode()) {
				updateInfo.setPatchUrl("");
			} else {
				String oldBundleText = getFileText(new File(bundleFilesDir, key));
				String patchText = buildPatch(oldBundleText, lastBundleText);
				storeText(patchText, "utf-8", new File(outputDir, key));
				updateInfo.setPatchUrl(patchesBaseUrl + key);
			}
			updateInfos.put(key, updateInfo);
		}

		String updateJson = JSON.toJSONString(update);
		storeText(updateJson, "utf-8", new File(outputDir, "update.json"));
	}

	public void storeText(String text, String charset, File file)
			throws IOException {
		OutputStream outputStream = new FileOutputStream(file);
		outputStream.write(text.getBytes("utf-8"));
		outputStream.close();
	}

	public String buildPatch(String oldBundleText, String lastBundleText) {
		return tools.patch_toText(tools.patch_make(oldBundleText,
				lastBundleText));
	}

}
