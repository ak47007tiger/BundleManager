package patchtools;

import java.io.IOException;

import patchtools.server.ServerPatchManager;

public class App {

	public static void main(String[] args) {
//		testServer();
		
		System.out.println("===========================");
		
//		testClient();
		
		testReleaseAndroid();
		testReleaseIos();
		
	}
	
	public static void testReleaseAndroid(){
		String bundlesDir = "patchtools/serverfolder/bundles/v1.0/android";
		String outputDir = "patchtools/serverfolder/patches/v1.0/android";
		ServerPatchManager serverPatchManager = new ServerPatchManager();
		try {
			serverPatchManager.work(bundlesDir, outputDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testReleaseIos(){
		String bundlesDir = "patchtools/serverfolder/bundles/v1.0/ios";
		String outputDir = "patchtools/serverfolder/patches/v1.0/ios";
		ServerPatchManager serverPatchManager = new ServerPatchManager();
		try {
			serverPatchManager.work(bundlesDir, outputDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
