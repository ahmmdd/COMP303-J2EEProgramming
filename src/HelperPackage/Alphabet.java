package HelperPackage;

public class Alphabet implements Comparable<Alphabet> {
	
	char letter;
	String ascii;
	int order;
	int occurrence;
	float frequency;
	String huffmanCode;
	
	

	public char getLetter() {
		return letter;
	}



	public void setLetter(char letter) {
		this.letter = letter;
	}



	public String getAscii() {
		return ascii;
	}



	public void setAscii(String ascii) {
		this.ascii = ascii;
	}



	public int getOrder() {
		return order;
	}



	public void setOrder(int order) {
		this.order = order;
	}



	public int getOccurrence() {
		return occurrence;
	}



	public void setOccurrence(int occurrence) {
		this.occurrence = occurrence;
	}



	public float getFrequency() {
		return frequency;
	}



	public void setFrequency(float frequency) {
		this.frequency = frequency;
	}



	public String getHuffmanCode() {
		return huffmanCode;
	}



	public void setHuffmanCode(String huffmanCode) {
		this.huffmanCode = huffmanCode;
	}



	public Alphabet(char letter,String ascii ) {
		this.letter = letter;
		this.ascii =ascii;

	}
	


	public Alphabet() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public int compareTo(Alphabet o) {
		// TODO Auto-generated method stub
		return o.getOccurrence()-this.getOccurrence();
	}  

}
