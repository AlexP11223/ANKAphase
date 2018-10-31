package ANKAphase;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.process.ImageProcessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import ReadEDF.ReadHeader;

public class HandlerThread extends Thread{

	private String scaleList;
	private String scaleListFlat;
	
	private int width;
	private int height;
	private int size;
	private float mu;
	private float[] kSq;
	private double graustufen;
	private double beta;
	private double delta;
	private double deltabeta;
	private double gauss;
	private double offset;
	private double distance;
	private double energy;
	private double pixSize;
	
	private int imCounter;
	private int prozZahl;
	private CalculationThread[] CalTh;
	private boolean checkThread = false;
	private int countThread;
	
	private double scaleFlatMin;
	private double scaleFlatMax;
	private double scalePhaseMin;
	private double scalePhaseMax;
	
	private String[] formatsArr = {"TIFF 8 bit", "TIFF 16 bit","TIFF float" , "JPEG 8 bit","PNG 8 bit","BMP 8 bit", "EDF float"};
	private String[] scaleOptArr = {"Scale each image to its min/max", "Scale to 3*(max-min) of first image", "Scale to user-specified value range"};
	
	private String pathnameProj;
	private String pathnameFlatOut;
	private String pathnamePhaseOut;
	private String[] listProj;
	
	private ImageProcessor ip1;
	private ImageProcessor dark;
	private ImageProcessor flat1;
	private ImageProcessor flat2;
	
	private JComboBox scalingFlatCombo;
	private JTextField scalingFlatFromText;
	private JTextField scalingFlatToText;
	private JComboBox scalingPhaseCombo;
	private JTextField scalingPhaseFromText;
	private JTextField scalingPhaseToText;
	
	private JCheckBox darkCheckBox;
	private JCheckBox flat1CheckBox;
	private JCheckBox flat2DirCheckBox;
	private JCheckBox saveFlatCheckBox;
	private JCheckBox savePhaseCheckBox;
	private JCheckBox deltaBetaCheckBox;
	private JCheckBox ImageRestoreCheckBox;
	
	private JTextField saveNameFlatText;
	private JTextField saveNamePhaseText;
	
	private JComboBox formatFlatCombo;
	private JComboBox formatPhaseCombo;
	
	private JCheckBox showImagesCheckBox;
	private JProgressBar ProgressBar;
	private JLabel statusLabel;
	
	private int calFrom;
	private int calTo;
	private boolean checkCalFromTo;
	private int threadCounter;
	private int threadCounterAll;
	private int countStartAt;
	private boolean checkInterpolate;
	private boolean checkEdgeExtension;
	
	private boolean heapFailure = false;
	
	public HandlerThread(String pathnameProjTh, String pathnameFlatOutTh, String pathnamePhaseOutTh, String[] listProjTh,
					double betaTh, double deltaTh, double deltabetaTh, double gaussTh, double offsetTh, double distanceTh, double energyTh, double pixSizeTh, int imCounterTh,
					ImageProcessor ip1Th, ImageProcessor darkTh, ImageProcessor flat1Th, ImageProcessor flat2Th,
					JComboBox scalingFlatComboTh, JTextField scalingFlatFromTextTh, JTextField scalingFlatToTextTh,
					JComboBox scalingPhaseComboTh,JTextField scalingPhaseFromTextTh, JTextField scalingPhaseToTextTh,
					JCheckBox darkCheckBoxTh, JCheckBox flat1CheckBoxTh, JCheckBox flat2DirCheckBoxTh,
					JCheckBox saveFlatCheckBoxTh, JCheckBox savePhaseCheckBoxTh, JCheckBox deltaBetaCheckBoxTh, JCheckBox ImageRestoreCheckBoxTh,
					JTextField saveNameFlatTextTh, JTextField saveNamePhaseTextTh,
					JComboBox formatFlatComboTh, JComboBox formatPhaseComboTh,
					JCheckBox showImagesCheckBoxTh, JProgressBar ProgressBarTh, JLabel statusLabelTh,
					int calFromTh, int calToTh, boolean checkCalFromToTh, int prozZahlTh, int countStartAtTh,
					boolean checkInterpolateTh, boolean checkEdgeExtensionTh){
		
		pathnameProj = pathnameProjTh;
		pathnameFlatOut = pathnameFlatOutTh;
		pathnamePhaseOut = pathnamePhaseOutTh;
		listProj = listProjTh;
		beta = betaTh;
		delta = deltaTh;
		deltabeta = deltabetaTh;
		gauss = gaussTh;
		offset = offsetTh;
		distance = distanceTh;
		energy = energyTh;
		pixSize = pixSizeTh;
		imCounter = imCounterTh;
		ip1 = ip1Th;
		dark = darkTh;
		flat1 = flat1Th;
		flat2 = flat2Th;
		scalingFlatCombo = scalingFlatComboTh;
		scalingFlatFromText = scalingFlatFromTextTh;
		scalingFlatToText = scalingFlatToTextTh;
		scalingPhaseCombo = scalingPhaseComboTh;
		scalingPhaseFromText = scalingPhaseFromTextTh;
		scalingPhaseToText = scalingPhaseToTextTh;
		darkCheckBox = darkCheckBoxTh;
		flat1CheckBox = flat1CheckBoxTh;
		flat2DirCheckBox = flat2DirCheckBoxTh;
		saveFlatCheckBox = saveFlatCheckBoxTh;
		savePhaseCheckBox = savePhaseCheckBoxTh;
		deltaBetaCheckBox = deltaBetaCheckBoxTh;
		ImageRestoreCheckBox = ImageRestoreCheckBoxTh; 
		saveNameFlatText = saveNameFlatTextTh;
		saveNamePhaseText = saveNamePhaseTextTh;
		formatFlatCombo = formatFlatComboTh;
		formatPhaseCombo = formatPhaseComboTh;
		showImagesCheckBox = showImagesCheckBoxTh;
		ProgressBar = ProgressBarTh;
		statusLabel = statusLabelTh;
		calFrom = calFromTh;
		calTo = calToTh;
		checkCalFromTo = checkCalFromToTh;
		prozZahl = prozZahlTh;
		countStartAt = countStartAtTh;
		checkInterpolate = checkInterpolateTh;
		checkEdgeExtension=checkEdgeExtensionTh;
		
	}
	
	
	public void run(){
		
		try{
		//String-Array der ScalingList erstellen__________________________________________________________________
		scaleList = "#\tScaling Option:\n" + "#\t" + scaleOptArr[scalingPhaseCombo.getSelectedIndex()] +"\n#\t\n";
		if (scalingPhaseCombo.getItemCount() == 2)
			scaleList += "#\tScaling numbers:\n#\tMin:\t" + scalingPhaseFromText.getText() + "\t\tMax:\t" + scalingPhaseToText.getText() + "\n#\t\n";
		scaleList += "#\t\n#\n#\tpicture\t\toriginal range\n";
		scaleListFlat = "#\tScaling Option:\n" + "#\t" + scaleOptArr[scalingFlatCombo.getSelectedIndex()] +"\n#\t\n";
		if (scalingFlatCombo.getItemCount() == 2)
			scaleListFlat += "#\tScaling numbers:\n#\tMin:\t" + scalingFlatFromText.getText() + "\t\tMax:\t" + scalingFlatToText.getText() + "\n#\t\n";
		scaleListFlat += "#\t\n#\n#\tpicture\t\toriginal range\n";
		File flf = new File(pathnameFlatOut + "Scaling_flat_images.txt");
		if(flf.exists())
			flf.delete();
		File flph = new File(pathnameFlatOut + "Scaling_phase_images.txt");
		if(flph.exists())
			flph.delete();
		if(saveFlatCheckBox.isSelected() == true){
			try{
				FileWriter fw = new FileWriter((pathnameFlatOut + "Scaling_flat_images.txt"), true);
				fw.write(scaleListFlat);
				fw.close();
			}
			catch(IOException ioe){
			}
		}
		if(savePhaseCheckBox.isSelected() == true){
			try{
				FileWriter fw = new FileWriter((pathnamePhaseOut + "Scaling_phase_images.txt"), true);
				fw.write(scaleList);
				fw.close();
			}
			catch(IOException ioe){
			}
		}
		//Berechnung des Kernels mit den Fourierkoordinaten_______________________________________________________
		if (savePhaseCheckBox.isSelected() == true){
			if(listProj[0].endsWith(".edf")){
				ReadHeader rh = new ReadHeader(listProj[0], pathnameProj);
				rh.runReadEDF();
				width = Integer.parseInt(rh.width);
				height = Integer.parseInt(rh.height);
			}
			else{
				ImagePlus impKernel = new ImagePlus(pathnameProj + listProj[0]);
				ImageProcessor ipKernel = impKernel.getProcessor();			//Eingabebild
				width = ipKernel.getWidth();								//Breite des Eingabebildes
				height = ipKernel.getHeight();
			}
				
			
		//Berechnung der Groesse des zu rekonstruierenden Bildes---------------------------
			
			int nmargin = new CalculateSize().calnmargin(energy, distance, pixSize, checkEdgeExtension);
			int sizetotalx = new CalculateSize().calSizenmargin(width, nmargin);
			int sizetotaly = new CalculateSize().calSizenmargin(height, nmargin);
			int size = new CalculateSize().calSize2n(sizetotalx, sizetotaly);
			
			kSq = new float[size*size*2];
			
			getKSq gK = new getKSq();
			kSq = gK.calculateKSq(size, pixSize, beta, delta, distance, energy, deltabeta, deltaBetaCheckBox, gauss, offset, ImageRestoreCheckBox);
			mu = (float)gK.mu;
		}
		
		//ProgresBar___________________________________________________
		ProgressBar.setMaximum(listProj.length);
		if(checkCalFromTo == true){
			ProgressBar.setMaximum((calTo -calFrom));
		}
		ProgressBar.setValue(0);
		statusLabel.setText("calculating images...");
		
		
		
		//imCounter initializieren________________________________________________________________________________
		if(checkCalFromTo == true){
			imCounter = calFrom;
		}
		
		// erster Thread__________________________________________________________________________________________
		
		//Treads aufmachen________________________________________________________________________________________
		CalTh = new CalculationThread[prozZahl];
		for (int j = 0; j < prozZahl; j++){
			CalTh[j] = new CalculationThread();
		}
		
		//Thread starten__________________________________________________________________________________________

	
		while(checkThread == false){
			for (countThread = 0; countThread < prozZahl; countThread++){
				if(CalTh[countThread].isAlive()==false){

					
		//Berechnung starten______________________________________________________________________________________
				
					CalTh[countThread] = new CalculationThread(); 
					CalTh[countThread].i = imCounter;
					CalTh[countThread].calFrom = calFrom;
					CalTh[countThread].pathnameProj = pathnameProj;
					CalTh[countThread].listProj = listProj;
					CalTh[countThread].ip1 = ip1;
					CalTh[countThread].graustufen = graustufen;
					CalTh[countThread].darkCheckBox = darkCheckBox.isSelected();
					CalTh[countThread].flat1CheckBox = flat1CheckBox.isSelected();
					CalTh[countThread].flat2CheckBox = flat2DirCheckBox.isSelected();
					CalTh[countThread].dark = dark;
					CalTh[countThread].flat1 = flat1;
					CalTh[countThread].flat2 = flat2;
					CalTh[countThread].width = width;
					CalTh[countThread].height = height;
					CalTh[countThread].stringScalingPhaseCombo = scaleOptArr[scalingPhaseCombo.getSelectedIndex()];
					CalTh[countThread].stringScalingPhaseFromText = scalingPhaseFromText.getText();
					CalTh[countThread].stringScalingPhaseToText = scalingPhaseToText.getText();
					CalTh[countThread].stringScalingFlatCombo = scaleOptArr[scalingFlatCombo.getSelectedIndex()];
					CalTh[countThread].stringScalingFlatFromText = scalingFlatFromText.getText();
					CalTh[countThread].stringScalingFlatToText = scalingFlatToText.getText();
					CalTh[countThread].SaveFlatCheckBox = saveFlatCheckBox.isSelected();
					CalTh[countThread].SavePhaseCheckBox = savePhaseCheckBox.isSelected();
					CalTh[countThread].size = size;
					CalTh[countThread].kSq = kSq;
					CalTh[countThread].mu = mu;
					CalTh[countThread].showImagesCheckBox = showImagesCheckBox.isSelected();
					CalTh[countThread].stringSavePhasePathText = pathnamePhaseOut;
					CalTh[countThread].stringSaveNamePhaseText = saveNamePhaseText.getText();
					CalTh[countThread].stringFormatPhaseCombo = formatsArr[formatPhaseCombo.getSelectedIndex()];
					CalTh[countThread].stringSaveFlatPathText = pathnameFlatOut;
					CalTh[countThread].stringSaveNameFlatText = saveNameFlatText.getText();
					CalTh[countThread].stringFormatFlatCombo = formatsArr[formatFlatCombo.getSelectedIndex()];
					CalTh[countThread].countStartAt = countStartAt;
					CalTh[countThread].checkInterpolate = checkInterpolate; 
					CalTh[countThread].energy = energy;
					CalTh[countThread].pixSize = pixSize;
					CalTh[countThread].distance = distance;
					CalTh[countThread].checkEdgeExtension = checkEdgeExtension; 
					CalTh[countThread].DeltaBetaCheckBox = deltaBetaCheckBox.isSelected();
					CalTh[countThread].deltabeta = (float) deltabeta;
					
					CalTh[countThread].setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
					      public void uncaughtException(Thread t, Throwable e) {
					        heapFailure = true;
							checkThread = true;
							if(saveFlatCheckBox.isSelected() == true && savePhaseCheckBox.isSelected() == true)
								threadCounterAll = threadCounterAll*2;
							GenericDialog gd = new GenericDialog("Failure");
							gd.addMessage( e.getMessage() + "\n" + threadCounterAll + "  images calculated!");
							gd.showDialog();
							ProgressBar.setValue(0);
							statusLabel.setText("status...");
							imCounter = 0;
							threadCounterAll = 0;
							return;
					      }
					    });
					

						CalTh[countThread].scaleFlatMin = scaleFlatMin;
						CalTh[countThread].scaleFlatMax = scaleFlatMax;
						CalTh[countThread].scalePhaseMin = scalePhaseMin;
						CalTh[countThread].scalePhaseMax = scalePhaseMax;

					
					CalTh[countThread].start();
					
					if(threadCounterAll == 0){
						while(CalTh[countThread].isAlive() == true){
							scaleFlatMax = CalTh[countThread].scaleFlatMax;
							scaleFlatMin = CalTh[countThread].scaleFlatMin;
							scalePhaseMax = CalTh[countThread].scalePhaseMax;
							scalePhaseMin = CalTh[countThread].scalePhaseMin;
							try{
								sleep(50);
							}
							catch(Exception e){}
						}
					}

					imCounter +=1;
					threadCounter += 1;
					threadCounterAll +=1;
					ProgressBar.setValue(threadCounter);

					if(checkCalFromTo == true){
						if(threadCounterAll == (calTo - calFrom + 1)){
							checkThread = true;
							break;
						}
					}
					if(threadCounterAll == listProj.length){
						checkThread = true;
						break;
					}
					
					
		//Thread beenden__________________________________________________________________________________________
				}
			}
			try{
				sleep(500);
			}
			catch(Exception e){}
		}
		if(saveFlatCheckBox.isSelected() == true && savePhaseCheckBox.isSelected() == true && heapFailure == false)
			threadCounterAll = threadCounterAll*2;
		GenericDialog gd = new GenericDialog("Finshed");
		gd.addMessage( threadCounterAll + "  images calculated!");
		gd.showDialog();
		ProgressBar.setValue(0);
		statusLabel.setText("status...");
		imCounter = 0;
		threadCounterAll = 0;
		}
		catch(java.lang.OutOfMemoryError ooe){
			if(saveFlatCheckBox.isSelected() == true && savePhaseCheckBox.isSelected() == true)
				threadCounterAll = threadCounterAll*2;
			GenericDialog gd = new GenericDialog("Failure");
			gd.addMessage( "Heap space out of memory!\n" + threadCounterAll + "  images calculated!");
			gd.showDialog();
			ProgressBar.setValue(0);
			statusLabel.setText("status...");
			imCounter = 0;
			threadCounterAll = 0;
		}
		
	}
}
