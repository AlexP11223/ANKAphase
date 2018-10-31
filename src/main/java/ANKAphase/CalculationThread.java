package ANKAphase;

import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import FFT.ComplexFloat2DFFT;
import ReadEDF.ImportDialog;
import ReadEDF.ReadHeader;

public class CalculationThread extends Thread{

	private int imCounter = 0;
	public String scaleList = "";
	public String scaleListFlat = "";
	public double scaleFlatMin;
	public double scaleFlatMax;
	public double scalePhaseMin;
	public double scalePhaseMax;
	public boolean checkscaleListFlat = false;
	public boolean checkscaleList = false;
	
	public int i;
	public int calFrom = 0;
	public String pathnameProj;
	public String[] listProj;
	public ImageProcessor ip1;
	public ImageProcessor ipFlat;
	public ImageProcessor ipFlat2;
	public double graustufen;
	public boolean darkCheckBox;
	public boolean flat1CheckBox;
	public boolean flat2CheckBox;
	public boolean checkInterpolate;
	public boolean checkEdgeExtension;
	public ImageProcessor dark;
	public ImageProcessor flat1;
	public ImageProcessor flat2;
	public int width;
	public int height;
	public String stringScalingPhaseCombo;
	public String stringScalingPhaseFromText;
	public String stringScalingPhaseToText;
	public String stringScalingFlatCombo;
	public String stringScalingFlatFromText;
	public String stringScalingFlatToText;
	public boolean SaveFlatCheckBox;
	public boolean SavePhaseCheckBox;
	public boolean DeltaBetaCheckBox;
	public int size;
	public float[] kSq;
	public float mu;
	public float deltabeta;
	public boolean showImagesCheckBox;
	public String stringSavePhasePathText;
	public String stringSaveNamePhaseText;
	public String stringFormatPhaseCombo;
	public String stringSaveFlatPathText;
	public String stringSaveNameFlatText;
	public String stringFormatFlatCombo;
	
	public double distance;
	public double energy;
	public double pixSize;
	
	private boolean byteOrder;
	public int countStartAt;
	
	private float[] Data1D;
	
	private int nmargin;
	private int sizetotalx;
	private int sizetotaly;
	
	
	public void run(){

		//Einlesen des Bildes_____________________________________________________________________________________
		if(listProj[i].toLowerCase().endsWith(".edf")){
			ReadHeader rh = new ReadHeader(listProj[i], pathnameProj);
			rh.runReadEDF();
			width = Integer.parseInt(rh.width);
			height = Integer.parseInt(rh.height);
			if(rh.byteOrder.equals("HighByteFirst")){
				byteOrder = false;
			}
			else
				byteOrder = true;
			ImportDialog d = new ImportDialog(listProj[i], pathnameProj, width, height, byteOrder,rh.typeIm, rh.headSize);
	        d.openImage();
	        ip1 = d.getIp();
		}
		else{
			ImagePlus imp1 = new ImagePlus(pathnameProj + listProj[i]);
			ip1 = imp1.getProcessor();			//Eingabebild
		}
		ip1 = ip1.convertToFloat();
		graustufen = ip1.maxValue();
		width = ip1.getWidth();
		height = ip1.getHeight();
		
		
		//Flatkorrektur durchfuehren_______________________________________________________________________________
		if (darkCheckBox == true || flat1CheckBox == true || flat2CheckBox == true ){
			FlatKorrektur fk = new FlatKorrektur();
			if(ip1.getWidth() != dark.getWidth() && ip1.getWidth() != flat1.getWidth() && ip1.getWidth() != flat2.getWidth() && 
					ip1.getHeight() != dark.getHeight() && ip1.getHeight() != flat1.getHeight() && ip1.getHeight() != flat2.getHeight()){
				try{
					throw new Exception("the size of the projection image isn't right");
				}
				catch(Exception e){}
			}
			else{
				ip1 = fk.runPicCorrect(dark, flat1, flat2, ip1, width, height, listProj.length, i,flat1CheckBox, flat2CheckBox, darkCheckBox, checkInterpolate);
			}
		}

		
		//Flat-Fielded-Bilder speichern_____________________________________________________________________________
		
		if (SaveFlatCheckBox == true){
			try{
			Ausgabe outF = new Ausgabe();
			outF.OutputFlat(ip1, stringFormatFlatCombo, stringSaveFlatPathText, stringSaveNameFlatText,
							(i+countStartAt-1-calFrom), height, width, graustufen, showImagesCheckBox,
							SaveFlatCheckBox, stringScalingFlatCombo, stringScalingFlatFromText,
							stringScalingFlatToText, imCounter, scaleFlatMin, scaleFlatMax);
			scaleListFlat += outF.outputScaleFlat(stringSaveFlatPathText,stringSaveNameFlatText,(i+countStartAt-1));
			FileWriter fw = new FileWriter((stringSaveFlatPathText + "Scaling_flat_images.txt"), true);
			fw.write(scaleListFlat);
			fw.close();
			if (imCounter == 0){	
				scaleFlatMin = outF.getMinFlat();
				scaleFlatMax = outF.getMaxFlat();
			}
			}
			catch(FileNotFoundException e1){
			}
			catch(IOException e2){
			}
			checkscaleListFlat = true;
			imCounter += 1;
		}
		
		
//		//Phaseretrieval durchfuehren______________________________________________________________________________
		if(SavePhaseCheckBox == true){
			
			nmargin = new CalculateSize().calnmargin(energy, distance, pixSize, checkEdgeExtension);
			sizetotalx = new CalculateSize().calSizenmargin(width, nmargin);
			sizetotaly = new CalculateSize().calSizenmargin(height, nmargin);
			size = new CalculateSize().calSize2n(sizetotalx, sizetotaly);
			
			try{
				Data1D = new float[(size * size) * 2];
				Data1D = new Randbehandlung().correctBorder(ip1,size,nmargin);
			}
			catch(java.lang.OutOfMemoryError ooe){
					throw new java.lang.OutOfMemoryError("Heap space out of memory");
			}
			
			//Image Fourier transformieren----------------------
			ComplexFloat2DFFT cfft = new ComplexFloat2DFFT(size, size);
			cfft.transform(Data1D);
			
			for (int i = 0; i < kSq.length; i++){
				Data1D[i] = kSq[i] * Data1D[i];
			}
			
			cfft.inverse(Data1D);
				
			
		//Bild anzeigen und speichern___________________________________________________________________________________________
			try{
				
			Ausgabe outF = new Ausgabe();
			
			if (DeltaBetaCheckBox == true){
				//System.out.println("xDeltaBetaRatio =" + 1);
				
				outF.OutputWindow(Data1D, width, height, size, graustufen, showImagesCheckBox,
						mu, ip1, stringSavePhasePathText, stringSaveNamePhaseText,
						stringFormatPhaseCombo, (i+countStartAt-1-calFrom), stringScalingPhaseCombo, stringScalingPhaseFromText,
						stringScalingPhaseToText, i, scalePhaseMin, scalePhaseMax, nmargin, 1, deltabeta);
			}
			else{
				//System.out.println("xDelta" + 2);
				//System.out.println("xBeta" + 2);
				
				outF.OutputWindow(Data1D, width, height, size, graustufen, showImagesCheckBox,
						mu, ip1, stringSavePhasePathText, stringSaveNamePhaseText,
						stringFormatPhaseCombo, (i+countStartAt-1-calFrom), stringScalingPhaseCombo, stringScalingPhaseFromText,
						stringScalingPhaseToText, i, scalePhaseMin, scalePhaseMax, nmargin, 0, deltabeta);
			}

			
			scaleList += outF.outputScale(stringSavePhasePathText,stringSaveNamePhaseText,(i+countStartAt-1));
			FileWriter fw = new FileWriter((stringSavePhasePathText + "Scaling_phase_images.txt"), true);
			fw.write(scaleList);
			fw.close();
			if (i == 0){	
				scalePhaseMin = outF.getMinOut();
				scalePhaseMax = outF.getMaxOut();
			}
			}
			catch(FileNotFoundException e1){
			}
			catch(IOException e2){
			}
			catch(java.lang.OutOfMemoryError ooe){
				throw new java.lang.OutOfMemoryError("Heap space out of memory");
			}
			checkscaleList = true;
			imCounter += 1;
			if(i == 0){
				try{
					sleep(100);
				}
				catch(InterruptedException ioe){}
			}
		}
		}
	
}
