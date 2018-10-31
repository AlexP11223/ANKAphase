package ReadEDF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class ReadHeader {
	
	private String path;
	private String fileName;
	private String directory;
	private int countLine = 0;
	
	private String angabe;
	private String wert;
	private String offset;
	private String zeile = "";
	
	public String byteOrder;
	public String width;
	public String height;
	public String typeIm;
	public int headSize;
	
	private File f;
	private int count = 0;
	
	
	public ReadHeader(String fileName, String directory){
		this.fileName = fileName;
		this.directory = directory;
	}
	
	public void runReadEDF(){
		f = new File((directory + fileName));
	
		readlength();
		
		path = f.getAbsolutePath();
	
		String text = "";
		try {
			BufferedReader input = new BufferedReader(new FileReader(path));
			String line;

			if ((line = input.readLine()) != null)
				text = line;
			
			while ((line = input.readLine()) != null) {
				text = text + '\n' + line;
				countLine++;
				if (countLine == 30){
					break;
				}
			}
			if (text.endsWith("\n"))
				text = text + "\n";

			input.close();
		}
		catch (FileNotFoundException exc1) {
		} 
		catch (IOException exc2) {
		}
		
		
		StringTokenizer st1;
		st1 = new StringTokenizer(text, "\n");
		
		
		for(int i= 0; i < countLine; i++){
//		while(zeile.equals("}")){
			zeile = st1.nextToken();
			String testEnd = zeile.trim();
			if(testEnd.equals("}")){
				break;
			}
			{
				try{
					StringTokenizer st2 = new StringTokenizer(zeile,"=");
						angabe = st2.nextToken();
						wert = st2.nextToken();
				}
				catch(NoSuchElementException nee){}
				try{
					angabe = angabe.trim();
				}
				catch(NullPointerException npe){};
				try{
					wert = wert.replaceAll(";", " ");
					wert = wert.trim();
				}
				catch(NullPointerException npe){};
				
				if(angabe.equals("ByteOrder"))
					byteOrder = wert;
				if(angabe.equals("Dim_1"))
					width = wert;
				if(angabe.equals("Dim_2"))
					height = wert;
				if(angabe.equals("DataType"))
					typeIm = wert;	
			}
		}
	}
	
	private void readlength(){
		try{
			FileReader leser = new FileReader(f);
		
			 // erzeugen einer Dauerschleife:
		   for(;;){
		     int gelesenInt = leser.read();
				 
				 // Wenn kein Zeichen mehr zur�ckgegeben wird (= -1),
				 // dann ist das Dateienende ereicht,
				 // daher aufh�ren
		     if(gelesenInt == -1){break;}
				 
				 // ein char wird als integer eingelesen!
				 //Daher als char umwandeln
		     char gelesenChar = (char) gelesenInt;
				 
				 //Jedes Zeichen ausgeben
//		     System.out.print(gelesenChar);
		     if(gelesenChar == '}')
		    	 break;
		     count++;
		   }
		}
		catch(FileNotFoundException fnfe){	
		}
		catch(IOException ioe){	
		}
		headSize = count+2;
//		System.out.print("Header count: " + headSize);

	}
}
