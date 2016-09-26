package customeloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class CustomClassloader2 extends ClassLoader{
	HashMap<String, Class<?>> map = new HashMap<String, Class<?>>();
	public Class<?> loadClass(File clazzFile, String name) throws ClassNotFoundException, IOException {
		Class<?> clazz;
		String path = clazzFile.getPath();
		if(map.containsKey(path)){
			return map.get(path);
		}
		byte[] b = new byte[(int) clazzFile.length()];
		FileInputStream in = new FileInputStream(clazzFile);
		in.read(b);
		in.close();
		clazz = defineClass(name, b, 0, b.length);
		map.put(path, clazz);
		return clazz;
	}
	public Class<?> loadClass(File clazzFile) throws ClassNotFoundException, IOException {
		Class<?> clazz;
		String path = clazzFile.getPath();
		if(map.containsKey(path)){
			return map.get(path);
		}
		byte[] b = new byte[(int) clazzFile.length()];
		FileInputStream in = new FileInputStream(clazzFile);
		in.read(b);
		in.close();
		clazz = defineClass(null, b, 0, b.length);
		map.put(path, clazz);
		return clazz;
	}
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		return super.findClass(name);
	}
	public Class<?> findLoadedClass1(String name){
		return findLoadedClass(name);
	}
}
