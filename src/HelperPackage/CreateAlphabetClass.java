package HelperPackage;

public class CreateAlphabetClass {

	public static Alphabet[] aToZed(){
		String[] ascii_value = {"32","97","98","99","100","101","102","103","104","105","106","107","108","109","110",
                "111","112","113","114","115","116","117","118","119","120","121","122"};
		String letters = " abcdefghijklmnopqrstuvwxyz";
		char[] letter = letters.toCharArray();
		Alphabet[] aToZ = new Alphabet[27];
		for(int i = 0; i<letter.length; i++){
			aToZ[i] = new Alphabet(letter[i], ascii_value[i]);
		}	
		return aToZ;
	}
	
	public static void main(String[] args) {
	
		Alphabet[] newAtoZ = CreateAlphabetClass.aToZed(); 
		for (int i =0; i<newAtoZ.length;i++){
		System.out.println(newAtoZ[i].letter + "\t"+newAtoZ[i].getAscii());
		}
		
	}

}