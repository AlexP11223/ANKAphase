package ANKAphase;

import javax.swing.JCheckBox;


public class getKSq {

	private float[][] kSq;
	private float[] kSq1D;
	private JCheckBox DeltaBetaCheckBox;
	private JCheckBox ImageRestoreCheckBox;
	private double deltabeta;
	private double gauss;
	private double offset;
	public double mu;											//Schwaechngskoeffizient des Objekts
	
	
	float[] calculateKSq(int size, double pixSize, double beta, double delta, double distance, double kev, double deltabetaAA, JCheckBox DeltaBetaCheckBoxAA, double gaussAA, double offsetAA, JCheckBox ImageRestoreCheckBoxAA){
		
		//pixSize = Hoehe und Breite des Detektorpixels
		//beta = Imaginaerteil des Brechungsindexes des Objekts
		//delta = Realteil des Brechungsindexes des Objekts
		//distance = Distanz vom Objekt zum Detektor
		//kev = Energie des Strahls
	
		DeltaBetaCheckBox = DeltaBetaCheckBoxAA;
		deltabeta = deltabetaAA;
		gauss =  gaussAA;
		offset = offsetAA;
		ImageRestoreCheckBox = ImageRestoreCheckBoxAA;
		
		//Variablen_______________________________________________________________________________________________
		kSq = new float[size][size];							//Feld der Fourierkoordinaten
		double[] lut = new double[(size/2)+1];					//LUT fuer die Berechnung der Fourierkoordinaten
		double[] lut2 = new double[(size/2)+1];					//LUT fuer die negativen Quadraten der Fourierkoordinaten
		double Pi = Math.PI;									//die Zahl Pi
		double lambda;											//Wellenlaenge des Strahls
				
		//Berechnen von lambda und mu_____________________________________________________________________________
		lambda = (float)((12.398424 * Math.pow(10, -7)) / kev);
		mu = (float)(((4 * Math.PI) / lambda) * beta);
		
		//Erstellen der LUTs fuer kSq______________________________________________________________________________
		for (int i = 0; i <= (size/2); i++){
				lut[i] = ((2*i*Pi)/(size*pixSize)) * ((2*i*Pi)/(size*pixSize));
		}
		int countLut = (size/2);			
		for (int i = 0; i < (size/2); i++){
			lut2[i] = ((2*countLut*Pi)/(size*pixSize)) * ((2*countLut*Pi)/(size*pixSize));
			countLut--;
		}
		
		if (DeltaBetaCheckBox.isSelected() == true){
			//System.out.println("test2 = " + deltabeta + " " + lambda + " " + pixSize + " " + (-1) /mu);
			//System.out.println("ImageRestoreDeltaBeta" + gauss + " " + offset);
			
			// Quadrant 1 (links oben)-----------------------------------------------------------------
			for (int i = 0; i < (size/2); i++){
				for (int j = 0; j < (size/2); j++){
					if (ImageRestoreCheckBox.isSelected() == true){
						kSq[i][j] = (float) ((float)((1 + offset)/ ( 1 + deltabeta * distance * lambda * (lut[i] + lut[j]) / (4*Pi))) / (offset + Math.exp((-Pi) * (lut[i] + lut[j]) * gauss * gauss * pixSize * pixSize) ));					
					}
					else {
						kSq[i][j] = (float)(1 / ( 1 + deltabeta * distance * lambda * (lut[i] + lut[j]) / (4*Pi)));
					}
				}
			}
			
			// Quadrant 2 (links unten)----------------------------------------------------------------
			for (int i = 0; i < (size/2); i++){
				for (int j = (size/2); j < size; j++){
					if (ImageRestoreCheckBox.isSelected() == true){
						kSq[i][j] = (float) ((float)((1 + offset)/ ( 1 + deltabeta * distance * lambda * (lut[i] + lut2[j-(size/2)]) / (4*Pi))) / (offset + Math.exp((-Pi) * (lut[i] + lut2[j-(size/2)]) * gauss * gauss * pixSize * pixSize) ));					
					}
					else {
						kSq[i][j] = (float)(1 / ( 1 + deltabeta * distance * lambda * (lut[i] + lut2[j-(size/2)]) / (4*Pi) ) );
					}
				}
			}
			
			// Quadrant 3 (rechts oben)----------------------------------------------------------------
			for (int i = (size/2); i < size; i++){
				for (int j = 0; j < (size/2); j++){
					if (ImageRestoreCheckBox.isSelected() == true){
						kSq[i][j] = (float) ((float)((1 + offset)/ ( 1 + deltabeta * distance * lambda * (lut2[i-(size/2)] + lut[j]) / (4*Pi))) / (offset + Math.exp((-Pi) * (lut2[i-(size/2)] + lut[j]) * gauss * gauss * pixSize * pixSize) ));					
					}
					else {
						kSq[i][j] = (float)(1 / ( 1 + deltabeta * distance * lambda * (lut2[i-(size/2)] + lut[j]) / (4*Pi)));
					}
				}
			}
			
			// Quadrant 4 (rechts unten)---------------------------------------------------------------
			for (int i = (size/2); i < size; i++){
				for (int j = (size/2); j < size; j++){
					if (ImageRestoreCheckBox.isSelected() == true){
						kSq[i][j] = (float) ((float)((1 + offset)/ ( 1 + deltabeta * distance * lambda * (lut2[i-(size/2)] + lut2[j-(size/2)]) / (4*Pi))) / (offset + Math.exp((-Pi) * (lut2[i-(size/2)] + lut2[j-(size/2)]) * gauss * gauss * pixSize * pixSize) ));					
					}
					else {
						kSq[i][j] = (float)(1 / ( 1 + deltabeta * distance * lambda * (lut2[i-(size/2)] + lut2[j-(size/2)]) / (4*Pi)));
					}
				}
			}
			
		}
		else{
			//System.out.println("2Delta" + delta + " " + (-2) * Pi * delta / lambda);
			//System.out.println("ImageRestore" + gauss + " " + offset);
			
			// Quadrant 1 (links oben)-----------------------------------------------------------------
			for (int i = 0; i < (size/2); i++){
				for (int j = 0; j < (size/2); j++){
					if (ImageRestoreCheckBox.isSelected() == true){
						kSq[i][j] = (float) ((float)((1 + offset)/ ( 1 + distance * delta * (lut[i] + lut[j]) / mu)) / (offset + Math.exp((-Pi) * (lut[i] + lut[j]) * gauss * gauss * pixSize * pixSize) ));					
					}
					else {
						kSq[i][j] = (float)(1 / ( 1 + distance * delta * (lut[i] + lut[j]) / mu));
					}
				}
			}
			
			// Quadrant 2 (links unten)----------------------------------------------------------------
			for (int i = 0; i < (size/2); i++){
				for (int j = (size/2); j < size; j++){
					if (ImageRestoreCheckBox.isSelected() == true){
						kSq[i][j] = (float) ((float)((1 + offset)/ ( 1 + distance * delta * (lut[i] + lut2[j-(size/2)]) / mu)) / (offset + Math.exp((-Pi) * (lut[i] + lut2[j-(size/2)]) * gauss * gauss * pixSize * pixSize) ));					
					}
					else {
						kSq[i][j] = (float)(1 / ( 1 + distance * delta * (lut[i] + lut2[j-(size/2)]) / mu));
					}
				}
			}
			
			// Quadrant 3 (rechts oben)----------------------------------------------------------------
			for (int i = (size/2); i < size; i++){
				for (int j = 0; j < (size/2); j++){
					if (ImageRestoreCheckBox.isSelected() == true){
						kSq[i][j] = (float) ((float)((1 + offset)/ ( 1 + distance * delta * (lut2[i-(size/2)] + lut[j]) / mu)) / (offset + Math.exp((-Pi) * (lut2[i-(size/2)] + lut[j]) * gauss * gauss * pixSize * pixSize) ));					
					}
					else {
						kSq[i][j] = (float)(1 / ( 1 + distance * delta * (lut2[i-(size/2)] + lut[j]) / mu));
					}
				}
			}
			
			// Quadrant 4 (rechts unten)---------------------------------------------------------------
			for (int i = (size/2); i < size; i++){
				for (int j = (size/2); j < size; j++){
					if (ImageRestoreCheckBox.isSelected() == true){
						kSq[i][j] = (float) ((float)((1 + offset)/ ( 1 + distance * delta * (lut2[i-(size/2)] + lut2[j-(size/2)]) / mu)) / (offset + Math.exp((-Pi) * (lut2[i-(size/2)] + lut2[j-(size/2)]) * gauss * gauss * pixSize * pixSize) ));					
					}
					else {
						kSq[i][j] = (float)(1 / ( 1 + distance * delta * (lut2[i-(size/2)] + lut2[j-(size/2)]) / mu));
					}
				}
			}
		}

		//Endimensional machen---------------------------------------------------------------------
		kSq1D = new float[size*size*2];
		int count = 0;
		for (int y = 0; y < size; y++){
			for(int x = 0; x < size; x++){
				kSq1D[count] = kSq[x][y];
				kSq1D[count+1] = kSq[x][y];
				count += 2;
			}
		}
		//System.out.println("Hello world!");
		return kSq1D;

	}
}
