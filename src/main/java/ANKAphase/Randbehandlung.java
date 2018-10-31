package ANKAphase;

import ij.process.ImageProcessor;

public class Randbehandlung {

	private int width;
	private int height;
	
	
	public float[] correctBorder(ImageProcessor ip, int size, int nmargin){
		
		width = ip.getWidth();
		height = ip.getHeight();
		
		int margin2w = (int)(size-width-(2*nmargin))/2;
		int margin2h = (int)(size-height-(2*nmargin))/2;
	
		
		//Bild erstellen++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		float[][] pic = new float[size][size];

		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){

				//obere linke Ecke
				if((i <= (nmargin+margin2w)) && (j < (nmargin+margin2h))){
					pic[i][j]=ip.getPixelValue(0, 0);
				}
				//obere rechte Ecke
				if((i >= (nmargin+width+margin2w)) && (j < (nmargin+margin2h))){
					pic[i][j]=ip.getPixelValue(width-1, 0);
				}
				//untere linke Ecke
				if((i <= (nmargin+margin2w)) && (j >= (nmargin+height+margin2h))){
					pic[i][j]=ip.getPixelValue(0, height-1);
				}
				//untere rechte Ecke
				if((i >= (nmargin+width+margin2w)) && (j >= (nmargin+height+margin2h))){
					pic[i][j]=ip.getPixelValue(width-1, height-1);
				}
				//oben
				if(i > (nmargin+margin2w) && i < (nmargin+width+margin2w) && j < (nmargin+margin2h)){
					pic[i][j]=ip.getPixelValue(i-nmargin-margin2w, 0);
				}
				//unten
				if(i > (nmargin+margin2w) && i < (nmargin+width+margin2w) && j >= (nmargin+height+margin2h)){
					pic[i][j]=ip.getPixelValue(i-nmargin-margin2w, height-1);
				}
				//links
				if(i < (nmargin+margin2w) && j >= (nmargin+margin2h) && j < (nmargin+height+margin2h)){
					pic[i][j]=ip.getPixelValue(0, j-nmargin-margin2h);
				}
				//rechts
				if(i >= (nmargin+width+margin2w) && j >= (nmargin+margin2h) && j < (nmargin+height+margin2h)){
					pic[i][j]=ip.getPixelValue(width-1, j-nmargin-margin2h);
				}
				//Mitte, das eigentliche Bild
				if(i >= (nmargin+margin2w) && i < (nmargin+width+margin2w) && j >= (nmargin+margin2h) && j < (nmargin+height+margin2h)){
					pic[i][j]=ip.getPixelValue(i-nmargin-margin2w, j-nmargin-margin2h);
				}
			}
		}
		
//		//Bild abspeichern++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//		
//		try{
//		File file = new File("/home/haas-d/Randbehandlung.txt");
//		FileWriter fw = new FileWriter(file);
//		
//		for(int i = 0; i < size; i++){
//			for(int j = 0; j < size; j++){
//				fw.write(pic[i][j]+" ");
//			}
//			fw.write("\n");
//		}
//		
//		fw.flush();
//		fw.close();
//		}
//		catch( IOException e )
//		{
//			e.printStackTrace();
//		}
		
		//Bild in 1D Array konvertieren++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		float[] pic1D = new float[(size * size) * 2];
		for(int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				pic1D[j*size*2 + 2*i] = pic[i][j];
				pic1D[j*size*2 + 2*i + 1] = 0;	
			}
		}
				
		return pic1D;
		
	}
	
}
