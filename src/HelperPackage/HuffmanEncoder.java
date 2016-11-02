package HelperPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.Part;

public class HuffmanEncoder {

	public static void WriteHuffmanCode(Part  part) throws Exception{
		InputStream f= part.getInputStream();
		BufferedReader br = new BufferedReader(
				   new InputStreamReader(
		                      f, StandardCharsets.UTF_8));
		String line = null;
		File huffmanLog = new File("Huffman_Ciphered.txt");

        List<Alphabet> reference = AlphabetDB.GetHuffmanTable();
       
         // This will output the full path where the file will be written to...
        System.out.println(huffmanLog.getCanonicalPath());

        //Size of Cipher
        int cipherSize=0;
        FileWriter  fileWriter = new FileWriter(huffmanLog);
        BufferedWriter writer = new BufferedWriter(fileWriter);
		while ((line = br.readLine()) != null) {	
				char[] charText =line.toCharArray();			      
			    for(int i =0; i < charText.length; i++){
			  
			    	//Lookup Huffman code for character and write in text file 
			    	 for(Alphabet abc: reference ){
			         	if(charText[i]== abc.getLetter()){
			         		writer.write(abc.getHuffmanCode());
			         		//Count cipherSize
			         		cipherSize+= abc.getHuffmanCode().length();
			         	}
			         }      
			      }
		}
		br.close();
		writer.close();
		System.out.println("Cipher Size: "+cipherSize);
		for(Alphabet abc: reference ){
			System.out.println(abc.getLetter()+abc.getAscii()+abc.getFrequency()+abc.getOccurrence());
		}
		
	}
}