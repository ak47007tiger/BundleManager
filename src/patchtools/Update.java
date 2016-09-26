package patchtools;

import java.util.Map;

public class Update {

	private String lastApkVersion;
	private int lastApkVersionCode;
	private String lastBundleVersion;
	private int lastBundleVersionCode;
	private String lastInstallUrl;
	private Map<String,UpdateInfo> updateInfos;
	
	public String getLastApkVersion() {
		return lastApkVersion;
	}
	public void setLastApkVersion(String lastApkVersion) {
		this.lastApkVersion = lastApkVersion;
	}
	public int getLastApkVersionCode() {
		return lastApkVersionCode;
	}
	public void setLastApkVersionCode(int lastApkVersionCode) {
		this.lastApkVersionCode = lastApkVersionCode;
	}
	public String getLastBundleVersion() {
		return lastBundleVersion;
	}
	public void setLastBundleVersion(String lastBundleVersion) {
		this.lastBundleVersion = lastBundleVersion;
	}
	public int getLastBundleVersionCode() {
		return lastBundleVersionCode;
	}
	public void setLastBundleVersionCode(int lastBundleVersionCode) {
		this.lastBundleVersionCode = lastBundleVersionCode;
	}
	public String getLastInstallUrl() {
		return lastInstallUrl;
	}
	public void setLastInstallUrl(String lastInstallUrl) {
		this.lastInstallUrl = lastInstallUrl;
	}
	public Map<String, UpdateInfo> getUpdateInfos() {
		return updateInfos;
	}
	public void setUpdateInfos(Map<String, UpdateInfo> updateInfos) {
		this.updateInfos = updateInfos;
	}
	
}
