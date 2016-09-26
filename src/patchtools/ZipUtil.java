package patchtools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	public void compress(File src, File out) throws IOException{
		ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(src),StandardCharsets.UTF_8);
		put(zipOut,src);
	}
	
	public void put(ZipOutputStream out, File dir) throws IOException{
		File[] children = dir.listFiles();
		for(File child : children){
			if(child.isDirectory()){
				put(out, child);
			}
			if(child.isFile()){
				out.putNextEntry(new ZipEntry(child.getName()));
			}
		}
	}
	
	public void decompress(File src, File out) throws IOException{
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(src),StandardCharsets.UTF_8);
		ZipEntry zipEntry = zipIn.getNextEntry();
	}
}
