package ANKAphase;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.process.ImageProcessor;
import ReadEDF.ImportDialog;
import ReadEDF.ReadHeader;

public class FlatKorrektur {
	
	private ImageStack stack;
	private int nSlices;

//Stack erstellen---------------------------------------------------------------
	
	void createImageStack(String pathname, String[] list){
		
		int width;
		int height;
		boolean byteOrder;
		ImageProcessor ips;
		
		if(list[0].toLowerCase().endsWith(".edf")){
			ReadHeader rh = new ReadHeader(list[0], pathname);
			rh.runReadEDF();
			width = Integer.parseInt(rh.width);
			height = Integer.parseInt(rh.height);
		}
		else{
			ImagePlus imp = new ImagePlus(pathname + list[0]);
			width = imp.getWidth();
			height = imp.getHeight();
		}
		stack = new ImageStack(width, height);
		for (int i = 0; i < list.length; i++){
			if(list[0].endsWith(".edf")){
				ReadHeader rh = new ReadHeader(list[0], pathname);
				rh.runReadEDF();
				width = Integer.parseInt(rh.width);
				height = Integer.parseInt(rh.height);
				if(rh.byteOrder.equals("HighByteFirst"))
					byteOrder = false;
				else
					byteOrder = true;
				ImportDialog d = new ImportDialog(list[0], pathname, width, height, byteOrder, rh.typeIm, rh.headSize);
		        d.openImage();
		        ips = d.getIp();
			}
			else{
				ImagePlus impInS = new ImagePlus(pathname + list[i]);
				ips = impInS.getProcessor();
			}
			stack.addSlice("Slice", ips);
			nSlices++;	
		}
	}
	
//Median erstellen -------------------------------------------------------------
	
	float median(float[] a) {
		sort(a);
	    int length = a.length;
	    if ((length&1)==0)
	    	return (a[length/2-1]+a[length/2])/2f; // even
	    else
	    	return a[length/2]; // odd
	    }
	
// Sortieren -------------------------------------------------------------------
	
	void sort(float[] a) {
        if (!alreadySorted(a))
            sort(a, 0, a.length - 1);
    }
    
    void sort(float[] a, int from, int to) {
        int i = from, j = to;
        float center = a[ (from + to) / 2 ];
        do {
            while ( i < to && center>a[i] ) i++;
            while ( j > from && center<a[j] ) j--;
            if (i < j) {float temp = a[i]; a[i] = a[j]; a[j] = temp; }
            if (i <= j) { i++; j--; }
        } while(i <= j);
        if (from < j) sort(a, from, j);
        if (i < to) sort(a,  i, to);
    }
        
    boolean alreadySorted(float[] a) {
        for ( int i=1; i<a.length; i++ ) {
            if (a[i]<a[i-1] )
            return false;
        }
        return true;
    }

// Stack verarbeiten-------------------------------------------------------------
    
//	ImagePlus doMedianProjection(ProgressBar progressBar) {
	ImagePlus doMedianProjection() {	
        IJ.showStatus("Calculating median...");
        ImageProcessor[] slices = new ImageProcessor[nSlices];
        int index = 0;
        for (int slice=1; slice<=nSlices; slice++)
            slices[index++] = stack.getProcessor(slice);
        ImageProcessor ip2 = slices[0].duplicate();
        ip2 = ip2.convertToFloat();
        float[] values = new float[nSlices];
        int width = ip2.getWidth();
        int height = ip2.getHeight();
        int inc = Math.min(height/30, 1);
//        progressBar.setMaximum(height);
        for (int y=0; y<height; y++) {
            if (y%inc==0) IJ.showProgress(y, height-1);
//            progressBar.setSelection(y);
            for (int x=0; x<width; x++) {
                for (int i=0; i<nSlices; i++)
                    values[i] = slices[i].getPixelValue(x, y);
                ip2.putPixelValue(x, y, median(values));
            }
        }
        return new ImagePlus("Median", ip2);
    }
	
//2 Flatbilder Faktor berechnen-----------------------------------------------------------
	
	ImageProcessor correctFlat(ImageProcessor flat, ImageProcessor flat2){
		
		ImageProcessor facIp = flat.duplicate();
		for (int x = 0; x < flat.getWidth(); x++){
			for (int y = 0; y < flat.getHeight(); y++){
				facIp.putPixelValue(x, y, flat.getPixelValue(x, y) - flat2.getPixelValue(x, y));
			}
		}
		
		return facIp;
	}

//Korrektur berechnen--------------------------------------------------------------
	
	ImageProcessor runPicCorrect(ImageProcessor dark, ImageProcessor flat, ImageProcessor flat2,
									ImageProcessor pic,int width, int height, int length,
									int i, boolean checkFlatField1, boolean checkFlatField2, boolean checkDarkField, boolean checkInterpolate){

// interpoliert berechen -------------------------
//	ImageProcessor picNew = pic.duplicate();
	
// nur dark Korrektur----------------------------------

	if (checkDarkField == true && checkFlatField1 == false && checkFlatField2 == false){
		for (int x = 0; x < width; x++){
			for (int y = 0; y < height; y++){
				float zeahler;
				if ((pic.getPixelValue(x, y) - dark.getPixelValue(x, y))<= 1)
					zeahler = 1;
				else
					zeahler = pic.getPixelValue(x, y) - dark.getPixelValue(x, y);
				pic.putPixelValue(x, y,zeahler);
			}
		}	
	}
	
//mit flat --------------------------------------------
	
	if(checkFlatField1 == true && checkFlatField2 == false) {
	
//	if (checkInterpolate == true){
//		double fac;
//		fac = ((double)i)/((double)length);
//		if (checkFlatField2 == false){
//			for (int x = 0; x < width; x++){
//				for (int y = 0; y < height; y++){
//					float zeahler;
//					float nenner;
//					if ((pic.getPixelValue(x, y) - dark.getPixelValue(x, y))<= 0)
//						zeahler = 1;
//					else
//						zeahler = pic.getPixelValue(x, y) - dark.getPixelValue(x, y);
//					if ((flat2.getPixelValue(x, y) - dark.getPixelValue(x, y))<= 0)
//						nenner = 1;
//					else
//						nenner =(float)((flat.getPixelValue(x, y) - (fac * (flat.getPixelValue(x, y)-flat2.getPixelValue(x, y)))) - 
//							dark.getPixelValue(x, y));
//					pic.putPixelValue(x, y,(zeahler / nenner));
//				}
//			}
//		}
//		else{
			for (int x = 0; x < width; x++){
				for (int y = 0; y < height; y++){
					float zeahler;
					float nenner;
					if ((pic.getPixelValue(x, y) - dark.getPixelValue(x, y))<= 1)
						zeahler = 1;
					else
						zeahler = pic.getPixelValue(x, y) - dark.getPixelValue(x, y);
					if ((flat.getPixelValue(x, y) - dark.getPixelValue(x, y))<= 1)
						nenner = 1;
					else
						nenner = flat.getPixelValue(x, y) - dark.getPixelValue(x, y);
					pic.putPixelValue(x, y,(zeahler / nenner));
				}
			}
//		}
		
	}
	
	if(checkFlatField1 == false && checkFlatField2 == true) {
		
				for (int x = 0; x < width; x++){
					for (int y = 0; y < height; y++){
						float zeahler;
						float nenner;
						if ((pic.getPixelValue(x, y) - dark.getPixelValue(x, y))<= 1)
							zeahler = 1;
						else
							zeahler = pic.getPixelValue(x, y) - dark.getPixelValue(x, y);
						if ((flat2.getPixelValue(x, y) - dark.getPixelValue(x, y))<= 1)
							nenner = 1;
						else
							nenner = flat2.getPixelValue(x, y) - dark.getPixelValue(x, y);
						pic.putPixelValue(x, y,(zeahler / nenner));
					}
				}
			
	}
	
// 		Blockweise berechnen --------------------------------------------------------	
 
		if (checkFlatField2 == true && checkFlatField1 == true){
			
			if(checkInterpolate == false){
			int checkList;
			checkList = length / 2;	
				if (i < checkList){
					for (int x = 0; x < width; x++){
						for (int y = 0; y < height; y++){
							float zeahler;
							float nenner;
							if ((pic.getPixelValue(x, y) - dark.getPixelValue(x, y))<= 1)
								zeahler = 1;
							else
								zeahler = pic.getPixelValue(x, y) - dark.getPixelValue(x, y);
							if ((flat.getPixelValue(x, y) - dark.getPixelValue(x, y))<= 1)
								nenner = 1;
							else
								nenner = flat.getPixelValue(x, y) - dark.getPixelValue(x, y);
				
							pic.putPixelValue(x, y, (zeahler / nenner));
						}
					}
				}
				else{
					for (int x = 0; x < width; x++){
						for (int y = 0; y < height; y++){
							float zeahler;
							float nenner;
							if ((pic.getPixelValue(x, y) - dark.getPixelValue(x, y))<= 1)
								zeahler = 1;
							else
								zeahler = pic.getPixelValue(x, y) - dark.getPixelValue(x, y);
							if ((flat2.getPixelValue(x, y) - dark.getPixelValue(x, y))<= 1)
								nenner = 1;
							else
								nenner = flat2.getPixelValue(x, y) - dark.getPixelValue(x, y);
							
							pic.putPixelValue(x, y, (zeahler / nenner));

						}
					}
				}
			}
//			interplieren----------------------------------------------
			else{
				double fac;
				fac = ((double)i)/((double)length);
		
					for (int x = 0; x < width; x++){
						for (int y = 0; y < height; y++){
							float zeahler;
							float nenner;
							if ((pic.getPixelValue(x, y) - dark.getPixelValue(x, y))<= 0)
								zeahler = 1;
							else
								zeahler = pic.getPixelValue(x, y) - dark.getPixelValue(x, y);
							if ((flat2.getPixelValue(x, y) - dark.getPixelValue(x, y))<= 0)
								nenner = 1;
							else
								nenner =(float)((flat.getPixelValue(x, y) - (fac * (flat.getPixelValue(x, y)-flat2.getPixelValue(x, y)))) - 
									dark.getPixelValue(x, y));
							pic.putPixelValue(x, y,(zeahler / nenner));
						}
					}
				
			}
		}
	
	return pic;
		
	}
	
	
}
