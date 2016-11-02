package Project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import HelperPackage.*;

@MultipartConfig
@WebServlet("/upload")
public class  Upload extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
	   
	    InputStream fileContent = filePart.getInputStream();
	    
	    //Create Array of Alphabet Class using Helper Class CreateAlphabetClass to store all letter properties
	  	Alphabet[] newAtoZ = CreateAlphabetClass.aToZed(); 
	  		
	  	//Initialize database to clear values in Alphabet Table
	  		try {
	  			AlphabetDB.initializeAlphabet();
	  		} catch (Exception e) {
	  			System.out.println(e.toString());
	  		}

	  		
	  		try {
	  				  			
	  			//Construct BufferedReader from InputStreamReader
	  			BufferedReader br = new BufferedReader(
	  					   new InputStreamReader(
	  			                      fileContent, StandardCharsets.UTF_8));
	  			String line = null;
	  			while ((line = br.readLine()) != null) {
	  				char[] charText =line.toLowerCase().toCharArray();
	  				
	  				for(int i = 0; i<newAtoZ.length; i++){
	  				      
	  				      for(int i2 =0; i2 < charText.length; i2++){
	  				        //letter is found in text
	  				        if(charText[i2] == newAtoZ[i].getLetter()){
	  				        	//Increment Letter occurence and clearText size;
	  				        	newAtoZ[i].setOccurrence(newAtoZ[i].getOccurrence()+1);
	  				        }
	  				      }
	  				}
	  			}
	  			br.close();
	  			
	  			//declare variable to count total sum of letter occurrence
	  		  	int totalSum=0;
	  		    for(int i = 0; i< newAtoZ.length; i++){
	  		    	totalSum += newAtoZ[i].getOccurrence();
	  		    }
	  		    
	  		   //Calculate Frequency  for each character
	  		   for(int i = 0; i< newAtoZ.length; i++){
	  		       newAtoZ[i].setFrequency((float)newAtoZ[i].getOccurrence()/totalSum);
	  		    }
	  		    		    
	  		   //Sort array by occurrence using icomparable interface in Alphabet class  
	  		    Arrays.sort(newAtoZ);
	  		    
	  		    //Populate order to each letters
	  		    for(int i = 0; i< newAtoZ.length; i++){
	  			       newAtoZ[i].setOrder(i+1);
	  			    }
	  		    
	  		    //Populate huffmanCode to each letter
	  		    String[] huffmanCode = {"100","0010","0011","1111","1110","1100","1011","1010","0110","0101","11011",
	  		            "01111","01001","01000","00011","00010","00001","00000","110101","011101",
	  		            "011100","1101001","110100011","110100001","110100000","1101000101","1101000100"}; 
	  		    for(int i = 0; i< newAtoZ.length; i++){
	  			       newAtoZ[i].setHuffmanCode(huffmanCode[i]);
	  			    }
	  		    //Update Database using Helper class AlphabetDB methods
	  		    for(int b = 0; b< newAtoZ.length; b++){
	  		    	System.out.println(newAtoZ[b].getLetter()+"\t"+newAtoZ[b].getAscii()+"\t"+newAtoZ[b].getOccurrence()+"\t"
	  		    			+newAtoZ[b].getFrequency()+"\t\t"+ newAtoZ[b].getOrder()+"\t"+newAtoZ[b].getHuffmanCode());
	  		    	try {
	  					AlphabetDB.UpdateAlphabet(newAtoZ[b]);
	  				} catch (Exception e) {
	  				
	  					System.out.println(e.toString());
	  				}
	  		    }
	  		    
	  		    //Helper method to encrypt text to huffman
	  		    HuffmanEncoder.WriteHuffmanCode(filePart);
	  		    //Clear text size for Calculation of Compression Rate
	  		    int clearTextSize = totalSum*8;	  
	  		    System.out.println("Clear Text Size: "+ clearTextSize );

	  		   //End of Servlet process. Return to GUI 
	  		   response.sendRedirect("http://www.google.com");
	  		} 
	  		catch (FileNotFoundException ex)
	          {
	              System.out.println(ex.getMessage()); //prints exception
	          } 
	          //Input/Output exception
	          catch (IOException ex)
	          {
	              System.out.println(ex.getMessage()); //prints exception
	          } catch (Exception e) {
	  			
	          	System.out.println(e.getMessage()); //prints exception
	  		}
	    	
	  		
	    }
}
	

