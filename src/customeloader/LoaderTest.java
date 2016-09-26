package customeloader;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

public class LoaderTest {

	public static void testCl(){
		try {
			CustomCL cl = new CustomCL(System.getProperty("user.dir") + "/customeloader", new String[]{"A"});
			Class<?> clazz = cl.loadClass("A");
			System.out.println(null != clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testLoadASingle(){
		CustomClassloader1 loader1 = new CustomClassloader1();
		CustomClassloader2 loader2 = new CustomClassloader2();
		Method say;
		String methodName = "say";
		File baseDir = new File(System.getProperty("user.dir"), "customeloader");
		System.out.println(baseDir.length());
		String basePath = baseDir.getPath();
		try {
			System.out.println(basePath);
			Class<?> clazz1 = loader1.loadClass(new File(baseDir,"A.class"), "A");
			say = clazz1.getDeclaredMethod(methodName, new Class<?>[] {});
			Object obj1 = clazz1.newInstance();
			say.invoke(obj1, new Object[] {});
			
			Class<?> clazz2 = loader2.loadClass(new File(baseDir,"A.class"), "A");
			say = clazz2.getDeclaredMethod(methodName, new Class<?>[] {});
			Object obj2 = clazz2.newInstance();
			say.invoke(obj2, new Object[] {});
			
			System.out.println(clazz1 == clazz2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void testLoadB(){
		CustomClassloader1 loader1 = new CustomClassloader1();
		Method say;
		String methodName = "say";
		File baseDir = new File(System.getProperty("user.dir"), "customeloader");
		System.out.println(baseDir.length());
		String basePath = baseDir.getPath();
		try {
			System.out.println(basePath);
			Class<?> clazzA = loader1.loadClass(new File(baseDir,"A.class"), "A");
			say = clazzA.getDeclaredMethod(methodName, new Class<?>[] {});
			Object obj1 = clazzA.newInstance();
			say.invoke(obj1, new Object[] {});
			
			Class<?> clazzB = loader1.loadClass(new File(baseDir,"B.class"), "B");
			say = clazzB.getDeclaredMethod(methodName, new Class<?>[] {});
			Object obj2 = clazzB.newInstance();
			say.invoke(obj2, new Object[] {});
			
			System.out.println(clazzA == clazzB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		CustomClassloader2 loader2 = new CustomClassloader2();
		File clazzFile = new File(System.getProperty("user.dir"),"/customeloader/A.class");
		Class<?> clazz = loader2.loadClass(clazzFile);
		System.out.println(clazz.getName());
		System.out.println(loader2.findLoadedClass1("A"));
		System.out.println(loader2.findLoadedClass1(FileInputStream.class.getName()));
	}
}
