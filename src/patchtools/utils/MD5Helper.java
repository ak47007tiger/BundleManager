package patchtools.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class MD5Helper {

	public String md5(File file) {
		try {
			FileInputStream input = new FileInputStream(file);
			String md5 = DigestUtils.md5Hex(IOUtils
					.toByteArray(input));
			IOUtils.closeQuietly(input);
			return md5;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
