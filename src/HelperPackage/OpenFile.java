package HelperPackage;

import java.io.IOException;

public class OpenFile {

	public static void OpenTextFile(String application,String fileName)
	{
		ProcessBuilder pb = new ProcessBuilder(application, fileName);
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		String s1= "notepad.exe";
		String s2="D:/Huffman_Ciphered.txt";
		OpenTextFile(s1, s2);
	}
}
