package ANKAphase;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ImageProcessor;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ausgabe{

	private static double maxVal;
	private static double minVal;
	private static double maxValue;
	private static double minValue;
	
	private static ImagePlus out;

	private static String header = "";
	private static String format;
	private static String bit;
	private static String scaleList;
	private static String scaleListFlat;
	
	synchronized static public void OutputWindow(float[]Data,int widthPic, int heightPic, int size,
						double graustufen, boolean show, float mu, ImageProcessor ip2,
						String path, String name, String formatGiven, int count,
						String scaleOut, String scaleOutFrom, String scaleOutTo
						,int imCounter, double scaleOutMin, double scaleOutMax, int nmargin, int DBbool, float deltabeta
						)throws FileNotFoundException , IOException {

		
		minVal = 999999999;
		maxVal = -999999999;
		
		int margin2w = (int)(size-widthPic-(2*nmargin))/2;
		int margin2h = (int)(size-heightPic-(2*nmargin))/2;
		
		if(widthPic < heightPic){
			ip2 = ip2.rotateLeft();
		}
		
		String countS = "" + (count+1);
		if (countS.length() == 1)
			countS = "000" + (count+1);
		else if (countS.length() == 2)
			countS = "00" + (count+1);
		else if (countS.length() == 3)
			countS = "0" + (count+1);
		else if (countS.length() == 4)
			countS = "" + (count+1);
	
		if (formatGiven.equals("TIFF 8 bit")){
			format = "tif";
			bit = "8 bit";
		}
		if (formatGiven.equals("TIFF 16 bit")){
			format = "tif";
			bit = "16 bit";
		}
		if (formatGiven.equals("JPEG 8 bit")){
			format = "jpeg";
			bit = "8 bit";
		}
		if (formatGiven.equals("PNG 8 bit")){
			format = "png";
			bit = "8 bit";
		}
		if (formatGiven.equals("BMP 8 bit")){
			format = "bmp";
			bit = "8 bit";
		}
		if (formatGiven.equals("EDF float")){
			format = "edf";
			bit = "32 bit";
		}
		if (formatGiven.equals("TIFF float")){
			format = "tif";
			bit = "32 bit";
		}
		
		if (bit.equals("8 bit"))
			graustufen = 255;
		if (bit.equals("16 bit"))
			graustufen = 65535;
		
		//System.out.println("DeltaBetaBool =" + DBbool + " " + deltabeta);
		
		for (int x = 0; x < size; x++){
			for (int y = 0; y < size; y++){
				if (DBbool == 0) {	
					Data[y*size*2 + 2*x] = (float)((-1) * Math.log(Data[y*size*2 + 2*x]) / mu);
				}
				if (DBbool == 1) {
					Data[y*size*2 + 2*x] = (float)(deltabeta*Math.log(Data[y*size*2 + 2*x]) / 2);
									}
				if (minVal > Data[y*size*2 + 2*x]){
					minVal = Data[y*size*2 + 2*x];
				}
				if (maxVal < Data[y*size*2 + 2*x]){
					maxVal = Data[y*size*2 + 2*x];
				}
			}
		}
		
//		//Bild abspeichern++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//		
//		float[][] bla = new float[size][size]; 
//		
//		for (int x = 0; x < size; x++){
//			for(int y = 0; y < size; y++){
//				bla[x][y] = Data[y*size*2 + 2*x];
//			}
//		}	
//		
//		try{
//		File file = new File("/home/haas-d/out.txt");
//		FileWriter fw = new FileWriter(file);
//		
//		for(int i = 0; i < size; i++){
//			for(int j = 0; j < size; j++){
//				fw.write(bla[i][j]+" ");
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
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		int xcount = widthPic;
		int ycount = heightPic;
		if(widthPic < heightPic){
			xcount = heightPic;
			ycount = widthPic;
		}
		
		if(formatGiven.equals("TIFF float") || formatGiven.equals("EDF float")){
			for (int x = 0; x < xcount; x++){
				for(int y = 0; y < ycount; y++){
					if(widthPic < heightPic){
						ip2.putPixelValue(x, y, Data[(x+nmargin+margin2h)*size*2 + 2*(y+nmargin+margin2w)]);
					}
					else{
						ip2.putPixelValue(x, y, Data[(y+nmargin+margin2h)*size*2 + 2*(x+nmargin+margin2w)]);
					}
				}
			}	
		}		
		
//		Skalierung-------------------------------------------------
		if(formatGiven.equals("TIFF 8 bit") || formatGiven.equals("TIFF 16 bit") || formatGiven.equals("JPEG 8 bit") ||
				formatGiven.equals("PNG 8 bit") || formatGiven.equals("BMP 8 bit")){

		if (scaleOut.equals("Scale to user-specified value range")){
			double UsrMin = Float.valueOf(scaleOutFrom).floatValue();
			double UsrMax = Float.valueOf(scaleOutTo).floatValue();
			for (int x = 0; x < xcount; x++){
				for(int y = 0; y < ycount; y++){
					if(widthPic < heightPic){
						ip2.putPixelValue(x, y, Data[(x+nmargin+margin2h)*size*2 + 2*(y+nmargin+margin2w)]);
						ip2.putPixelValue(x, y, (float)(((ip2.getPixelValue(x, y)-UsrMin) / 
							(UsrMax - UsrMin)) * graustufen));
					}
					else{
						ip2.putPixelValue(x, y, Data[(y+nmargin+margin2h)*size*2 + 2*(x+nmargin+margin2w)]);
						ip2.putPixelValue(x, y, (float)(((ip2.getPixelValue(x, y)-UsrMin) / 
							(UsrMax - UsrMin)) * graustufen));
					}
				}
			}
		}
		if (scaleOut.equals("Scale each image to its min/max")){	
			for (int x = 0; x < xcount; x++){
				for(int y = 0; y < ycount; y++){
					if(widthPic < heightPic){
						Data[(x+nmargin+margin2h)*size*2 + 2*(y+nmargin+margin2w)] = (float)(((Data[(x+nmargin+margin2h)*size*2 + 2*(y+nmargin+margin2w)]-minVal) / (maxVal-minVal)) * graustufen);
						ip2.putPixelValue(x, y, Data[(x+nmargin+margin2h)*size*2 + 2*(y+nmargin+margin2w)]);
					}
					else{
						Data[(y+nmargin+margin2h)*size*2 + 2*(x+nmargin+margin2w)] = (float)(((Data[(y+nmargin+margin2h)*size*2 + 2*(x+nmargin+margin2w)]-minVal) / (maxVal-minVal)) * graustufen);
						ip2.putPixelValue(x, y, Data[(y+nmargin+margin2h)*size*2 + 2*(x+nmargin+margin2w)]);
					}
				}
			}
		}

		if (imCounter == 0){
			scaleOutMin = minVal;
			scaleOutMax = maxVal;
		}
		if (scaleOut.equals("Scale to 3*(max-min) of first image")){
			for (int x = 0; x < xcount; x++){
				for(int y = 0; y < ycount; y++){
					if(widthPic < heightPic){
						ip2.putPixelValue(x, y, Data[(x+nmargin+margin2h)*size*2 + 2*(y+nmargin+margin2w)]);
						ip2.putPixelValue(x, y, (float)(((ip2.getPixelValue(x, y)-scaleOutMin) / 
							(3*(scaleOutMax - scaleOutMin))) * graustufen));
					}
					else{
						ip2.putPixelValue(x, y, Data[(y+nmargin+margin2h)*size*2 + 2*(x+nmargin+margin2w)]);
						ip2.putPixelValue(x, y, (float)(((ip2.getPixelValue(x, y)-scaleOutMin) / 
							(3*(scaleOutMax - scaleOutMin))) * graustufen));
					}
				}
			}
		}		
		}
		
		

		if (widthPic < heightPic){
			ip2 = ip2.rotateRight();
		}
		
		if (bit.equals("16 bit"))
			ip2 = ip2.convertToShort(false);
		if (bit.equals("8 bit"))
			ip2 = ip2.convertToByte(false);
			
		out = new ImagePlus("Output...", ip2);
		
		//Ausgabe des rekonstruierten Bildes-----
		
		FileSaver sv2 = new FileSaver(out);
		if(format.equals("jpeg"))
			sv2.saveAsJpeg(path + name + "_" + countS + "." + format);
		if(format.equals("bmp"))
			sv2.saveAsBmp(path + name + "_" + countS + "." + format);
		if(format.equals("tif"))
			sv2.saveAsTiff(path + name + "_" + countS + "." + format);
		if(format.equals("png"))
			sv2.saveAsPng(path + name + "_" + countS + "." + format);
		if(format.equals("edf")){
			DataOutputStream outEdf = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path + name + "_" + countS + "." + format)));
			header = "{\nHeaderID       = EH:000001:000000:000000 ;\nImage          = 1 ;\nByteOrder  = LowByteFirst ;\nDataType       = Float ;\nDim_1          = "+ ip2.getWidth() +
					" ;\nDim_2          = " + ip2.getHeight() +" ;\nSize       = " + ip2.getWidth()*ip2.getHeight()*4 + " ;\nDate           =29-Feb-2003 ;\n";
			for (int i = header.length(); i < 1022; i++){
				header += " ";
			}
			header += "}\n";
			outEdf.writeBytes(header);
			for (int y = 0; y < ip2.getHeight(); y++){
				for (int x = 0; x < ip2.getWidth(); x++){
					float value = ip2.getPixelValue(x, y);
					LittleByteEndianOutputStream lb = new LittleByteEndianOutputStream(outEdf);
					lb.writeFloat(value);
				}
			}
			outEdf.close();
		}
		
		if (show == true){
			out.show();	
		}
	}
	
	synchronized static public void OutputFlat(ImageProcessor ipFlat2, String formatGiven, String path,
					String name, int count, int heightPic, int widthPic, double graustufen,
					boolean checkShowFlat, boolean checkSaveFlat,
					String scaleFlat, String scaleFlatFrom, String scaleFlatTo,
					int imCounter, double scaleFlatMin, double scaleFlatMax
					)throws FileNotFoundException , IOException{
			
			ImageProcessor ipFlat = ipFlat2.duplicate();
			
			minValue = 999999999;
			maxValue = -999999999;
		
			String countS = "" + (count+1);
			if (countS.length() == 1)
				countS = "000" + (count+1);
			else if (countS.length() == 2)
				countS = "00" + (count+1);
			else if (countS.length() == 3)
				countS = "0" + (count+1);
			else if (countS.length() == 4)
				countS = "" + (count+1);
		
			if (formatGiven.equals("TIFF 8 bit")){
				format = "tif";
				bit = "8 bit";
			}
			if (formatGiven.equals("TIFF 16 bit")){
				format = "tif";
				bit = "16 bit";
			}
			if (formatGiven.equals("JPEG 8 bit")){
				format = "jpeg";
				bit = "8 bit";
			}
			if (formatGiven.equals("PNG 8 bit")){
				format = "png";
				bit = "8 bit";
			}
			if (formatGiven.equals("BMP 8 bit")){
				format = "bmp";
				bit = "8 bit";
			}
			if (formatGiven.equals("EDF float")){
				format = "edf";
				bit = "32 bit";
			}
			if (formatGiven.equals("TIFF float")){
				format = "tif";
				bit = "32 bit";
			}
			
			if (bit.equals("8 bit"))
				graustufen = 255;
			if (bit.equals("16 bit"))
				graustufen = 65535;
			
			for (int x = 0; x < widthPic; x++){
				for (int y = 0; y < heightPic; y++){
					if (minValue > ipFlat.getPixelValue(x, y)){
						minValue = ipFlat.getPixelValue(x, y);
					}
					if (maxValue < ipFlat.getPixelValue(x, y)){
						maxValue = ipFlat.getPixelValue(x, y);
					}
				}
			}
			
//			Skalierung-------------------------------------------------			
			if(formatGiven.equals("TIFF 8 bit") || formatGiven.equals("TIFF 16 bit") || formatGiven.equals("JPEG 8 bit") ||
					formatGiven.equals("PNG 8 bit") || formatGiven.equals("BMP 8 bit")){

				if (scaleFlat.equals("Scale to user-specified value range")){
					double UsrMin = Float.valueOf(scaleFlatFrom).floatValue();
					double UsrMax = Float.valueOf(scaleFlatTo).floatValue();
					for (int x = 0; x < widthPic; x++){
						for(int y = 0; y < heightPic; y++){
							ipFlat.putPixelValue(x, y, (float)(((ipFlat.getPixelValue(x, y)-UsrMin) / 
									(UsrMax - UsrMin)) * graustufen));
						}
					}
				}
				if (scaleFlat.equals("Scale each image to its min/max")){	
					for (int x = 0; x < widthPic; x++){
						for(int y = 0; y < heightPic; y++){
							ipFlat.putPixelValue(x, y, (float)(((ipFlat.getPixelValue(x, y)-minValue) / 
									(maxValue - minValue)) * graustufen));
						}
					}
				}
			
				if (imCounter == 0){
					scaleFlatMin = minValue;
					scaleFlatMax = maxValue;
				}
				if (scaleFlat.equals("Scale to 3*(max-min) of first image")){	
					for (int x = 0; x < widthPic; x++){
						for(int y = 0; y < heightPic; y++){
							ipFlat.putPixelValue(x, y, (float)(((ipFlat.getPixelValue(x, y)-scaleFlatMin) / 
									(3*(scaleFlatMax - scaleFlatMin))) * graustufen));
						}
					}
				}
			
			if (bit.equals("16 bit")){
				ipFlat = ipFlat.convertToShort(false);
			}
			if (bit.equals("8 bit")){
				ipFlat = ipFlat.convertToByte(false);
			}
			}
			
			if (checkShowFlat == true){
				ImagePlus outFlat = new ImagePlus("Flat corrected...", ipFlat);
				outFlat.show();
			}
			
			if (checkSaveFlat == true){
				
				ImagePlus outFlat = new ImagePlus("Flat corrected...", ipFlat);
				FileSaver sv3 = new FileSaver(outFlat);
			
				if(format.equals("jpeg"))
					sv3.saveAsJpeg(path + name + "_" + countS + "." + format);
				if(format.equals("bmp"))
					sv3.saveAsBmp(path + name + "_" + countS + "." + format);
				if(format.equals("tif"))
					sv3.saveAsTiff(path + name + "_" + countS + "." + format);
				if(format.equals("png"))
					sv3.saveAsPng(path + name + "_" + countS + "." + format);
				if(format.equals("edf")){
					
					DataOutputStream outEdf = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path + name + "_" + countS + "." + format)));
					header = "{\nHeaderID       = EH:000001:000000:000000 ;\nImage          = 1 ;\nByteOrder  = LowByteFirst ;\nDataType       = Float ;\nDim_1          = "+ outFlat.getWidth() +
					" ;\nDim_2          = " + outFlat.getHeight() +" ;\nSize       = " + outFlat.getWidth()*outFlat.getHeight()*4 + " ;\nDate           =29-Feb-2003 ;\n";
					for (int i = header.length(); i < 1022; i++){
						header += " ";
					}
					header += "}\n";
					outEdf.writeBytes(header);
					for (int y = 0; y < outFlat.getHeight(); y++){
						for (int x = 0; x < outFlat.getWidth(); x++){
							float value = ipFlat.getPixelValue(x, y);
							LittleByteEndianOutputStream lb = new LittleByteEndianOutputStream(outEdf);
							lb.writeFloat(value);
						}
					}
					outEdf.close();
				}
			}
			
	}
	
	public String outputScale (String path, String name, int count){
		
		String countS = "" + (count+1);
		if (countS.length() == 1)
			countS = "000" + (count+1);
		else if (countS.length() == 2)
			countS = "00" + (count+1);
		else if (countS.length() == 3)
			countS = "0" + (count+1);
		else if (countS.length() == 4)
			countS = "" + (count+1);
		
		String minValStr = "" + minVal;
		for (int i = 0; i < 10; i++){
			minValStr += "0";
		}
		if (minValStr.substring(0,1).equals("-"))
			minValStr = minValStr.substring(0, 9);
		else
			minValStr = minValStr.substring(0, 8);
		
		String maxValStr = "" + maxVal;
		for (int i = 0; i < 10; i++){
			maxValStr += "0";
		}
		if (maxValStr.substring(0,1).equals("-"))
			maxValStr = maxValStr.substring(0, 9);
		else
			maxValStr = maxValStr.substring(0, 8);
		
		scaleList = name + "_" + countS + "." + format + "\t" + minValStr +"\t" + maxValStr + "\n";
	return scaleList;
	}
	
	public String outputScaleFlat (String path, String name, int count){
		
		String countS = "" + (count+1);
		if (countS.length() == 1)
			countS = "000" + (count+1);
		else if (countS.length() == 2)
			countS = "00" + (count+1);
		else if (countS.length() == 3)
			countS = "0" + (count+1);
		else if (countS.length() == 4)
			countS = "" + (count+1);
		
		String minValueStr = "" + minValue;
		for (int i = 0; i < 10; i++){
			minValueStr += "0";
		}
		if (minValueStr.substring(0,1).equals("-"))
			minValueStr = minValueStr.substring(0, 9);
		else
			minValueStr = minValueStr.substring(0, 8);
		
		String maxValueStr = "" + maxValue;
		for (int i = 0; i < 10; i++){
			maxValueStr += "0";
		}
		if (maxValueStr.substring(0,1).equals("-"))
			maxValueStr = maxValueStr.substring(0, 9);
		else
			maxValueStr = maxValueStr.substring(0, 8);
		
		scaleListFlat = name + "_" + countS + "." + format + "\t" + minValueStr +"\t" + maxValueStr + "\n";
	return scaleListFlat;
	}
	
	public double getMinOut(){
		return minVal;
	}
	
	 public double getMaxOut(){
		return maxVal;
	}
	
	public double getMinFlat(){
		return minValue;
	}
	
	public double getMaxFlat(){
		return maxValue;
	}
}
