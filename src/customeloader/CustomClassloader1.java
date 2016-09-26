package customeloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class CustomClassloader1 extends ClassLoader{
	HashMap<String, Class<?>> map = new HashMap<String, Class<?>>();
	HashSet<String> set = new HashSet<String>();
	public Class<?> loadClass(String basePath, String name) throws ClassNotFoundException {
		Class<?> clazz;
		if(map.containsKey(basePath)){
			return map.get(basePath);
		}
		File clazzFile = new File(basePath + "/" + name);
		byte[] b = new byte[(int) clazzFile.length()];
		clazz = defineClass(name, b, 0, b.length);
		map.put(basePath, clazz);
		return clazz;
	}
	public Class<?> loadClass(File clazzFile, String name) throws ClassNotFoundException, IOException {
		Class<?> clazz;
		String path = clazzFile.getPath();
		/*if(map.containsKey(path)){
			return map.get(path);
		}*/
		if(null != (clazz = findLoadedClass(name))){
			return clazz;
		}
		byte[] b = new byte[(int) clazzFile.length()];
		FileInputStream in = new FileInputStream(clazzFile);
		in.read(b);
		in.close();
		clazz = defineClass(name, b, 0, b.length);
		
		map.put(path, clazz);
		set.add(path);
		return clazz;
	}
	
}
