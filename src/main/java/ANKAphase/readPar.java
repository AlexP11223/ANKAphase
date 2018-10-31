package ANKAphase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class readPar {
	
	public String pathnameProj;
	public String pathnameDark;
	public String pathnameFlat1;
	public String pathnameFlat2;
	public String DarkCheckBox;
	public String Flat1CheckBox;
	public String Flat2CheckBox;
	
	public String SaveFlatCheckBox;
	public String pathnameFlatOut;
	public String SaveNameFlat;
	public String FormatFlat;
	public String ScalingFlat;
	public String ScalingFlatFromText;
	public String ScalingFlatToText;
	
	public String SavePhaseCheckBox;
	public String DeltaBetaCheckBox;
	public String ImageRestoreCheckBox;
	public String beta;
	public String delta;
	public String deltabeta;
	public String gauss;
	public String offset;
	public String distance;
	public String energy;
	public String pixSize;
	public String pathnamePhaseOut;
	public String SaveNamePhase;
	public String FormatPhase;
	public String ScalingPhase;
	public String ScalingPhaseFromText;
	public String ScalingPhaseToText;
	
	public String checkCalImFromTo;
	public String calculateImFrom;
	public String calculateImTo;
	public String showImages;
	public String CPUs;
	public String countStart;
	public String interpolate;
	public String EdgeExtension;
	
	public String ColorDarkPath;
	public String ColorFlat1Path;
	public String ColorFlat2Path;
	public String ColorFlatPathOut;
	public String ColorSaveNameFlat;
	public String ColorFormatFlat;
	public String ColorScalingFlat;
	public String ColorScalingFlatFrom;
	public String ColorScalingFlatTo;
	public String ColorPhasePathOut;
	public String ColorBeta;
	public String ColorDelta;
	public String ColorDeltaBeta;
	public String ColorGauss;
	public String ColorOffset;
	public String ColorDistance;
	public String ColorEnergy;
	public String ColorPixSize;
	public String ColorSaveNamePhase;
	public String ColorFormatPhase;
	public String ColorScalingPhase;
	public String ColorScalingPhaseFrom;
	public String ColorScalingPhaseTo;
	public String ColorCalculateImagesFrom;
	public String ColorCalculateImagesTo;
	public String ColorCPUs;
	
	private String path;
	private File f;
	
	public readPar(){
		f = new File("parameter21.txt");
	}
	
	public readPar(String pathIn){
		f = new File(pathIn);
	}
	
	public void runReadPar(){
	
	if (f.exists()== true){
		path = f.getAbsolutePath();
				
		String leerzeile;
		String text = "";
		try {
			BufferedReader input = new BufferedReader(new FileReader(path));
			String line;

			if ((line = input.readLine()) != null)
				text = line;

			while ((line = input.readLine()) != null) {
				text = text + '\n' + line;
			}
			if (text.endsWith("\n"))
				text = text + "\n";

			input.close();
		
		} catch (FileNotFoundException exc1) {
		} catch (IOException exc2) {
		}
		StringTokenizer st1;
		st1 = new StringTokenizer(text, "\n");
		
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		pathnameProj = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(pathnameProj,"=");
				leerzeile = st2.nextToken();
				pathnameProj = st2.nextToken();
		}
		pathnameDark = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(pathnameDark,"=");
				leerzeile = st2.nextToken();
				pathnameDark = st2.nextToken();
		}
		pathnameFlat1 = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(pathnameFlat1,"=");
				leerzeile = st2.nextToken();
				pathnameFlat1 = st2.nextToken();
		}
		pathnameFlat2 = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(pathnameFlat2,"=");
				leerzeile = st2.nextToken();
				pathnameFlat2 = st2.nextToken();
		}
		DarkCheckBox = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(DarkCheckBox,"=");
				leerzeile = st2.nextToken();
				DarkCheckBox = st2.nextToken();
		}
		Flat1CheckBox = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(Flat1CheckBox,"=");
				leerzeile = st2.nextToken();
				Flat1CheckBox = st2.nextToken();
		}
		Flat2CheckBox = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(Flat2CheckBox,"=");
				leerzeile = st2.nextToken();
				Flat2CheckBox = st2.nextToken();
		}
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		SaveFlatCheckBox = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(SaveFlatCheckBox,"=");
				leerzeile = st2.nextToken();
				SaveFlatCheckBox = st2.nextToken();
		}
		pathnameFlatOut = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(pathnameFlatOut,"=");
				leerzeile = st2.nextToken();
				pathnameFlatOut = st2.nextToken();
		}
		SaveNameFlat = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(SaveNameFlat,"=");
				leerzeile = st2.nextToken();
				SaveNameFlat = st2.nextToken();
		}
		FormatFlat = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(FormatFlat,"=");
				leerzeile = st2.nextToken();
				FormatFlat = st2.nextToken();
		}
		ScalingFlat = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ScalingFlat,"=");
				leerzeile = st2.nextToken();
				ScalingFlat = st2.nextToken();
		}
		ScalingFlatFromText = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ScalingFlatFromText,"=");
				leerzeile = st2.nextToken();
				ScalingFlatFromText = st2.nextToken();
		}
		ScalingFlatToText = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ScalingFlatToText,"=");
				leerzeile = st2.nextToken();
				ScalingFlatToText = st2.nextToken();
		}
		interpolate = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(interpolate,"=");
				leerzeile = st2.nextToken();
				interpolate = st2.nextToken();
		}
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		SavePhaseCheckBox = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(SavePhaseCheckBox,"=");
				leerzeile = st2.nextToken();
				SavePhaseCheckBox = st2.nextToken();
		}		
		DeltaBetaCheckBox = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(DeltaBetaCheckBox,"=");
				leerzeile = st2.nextToken();
				DeltaBetaCheckBox = st2.nextToken();
				//System.out.println("test=" + DeltaBetaCheckBox);
		}
		ImageRestoreCheckBox = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ImageRestoreCheckBox,"=");
				leerzeile = st2.nextToken();
				ImageRestoreCheckBox = st2.nextToken();
				//System.out.println("test=" + DeltaBetaCheckBox);
		}
		beta = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(beta,"=");
				leerzeile = st2.nextToken();
				beta = st2.nextToken();
		}
		delta = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(delta,"=");
				leerzeile = st2.nextToken();
				delta = st2.nextToken();
		}
		deltabeta = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(deltabeta,"=");
				leerzeile = st2.nextToken();
				deltabeta = st2.nextToken();
				//System.out.println("test=" + deltabeta);
		}		
		gauss = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(gauss,"=");
				leerzeile = st2.nextToken();
				gauss = st2.nextToken();
		}
		offset = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(offset,"=");
				leerzeile = st2.nextToken();
				offset = st2.nextToken();
		}
		distance = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(distance,"=");
				leerzeile = st2.nextToken();
				distance = st2.nextToken();
		}
		energy = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(energy,"=");
				leerzeile = st2.nextToken();
				energy = st2.nextToken();
		}
		pixSize = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(pixSize,"=");
				leerzeile = st2.nextToken();
				pixSize = st2.nextToken();
		}
		pathnamePhaseOut = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(pathnamePhaseOut,"=");
				leerzeile = st2.nextToken();
				pathnamePhaseOut = st2.nextToken();
		}
		SaveNamePhase = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(SaveNamePhase,"=");
				leerzeile = st2.nextToken();
				SaveNamePhase = st2.nextToken();
		}
		FormatPhase = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(FormatPhase,"=");
				leerzeile = st2.nextToken();
				FormatPhase = st2.nextToken();
		}
		ScalingPhase = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ScalingPhase,"=");
				leerzeile = st2.nextToken();
				ScalingPhase = st2.nextToken();
		}
		ScalingPhaseFromText = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ScalingPhaseFromText,"=");
				leerzeile = st2.nextToken();
				ScalingPhaseFromText = st2.nextToken();
		}
		ScalingPhaseToText = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ScalingPhaseToText,"=");
				leerzeile = st2.nextToken();
				ScalingPhaseToText = st2.nextToken();
		}
		EdgeExtension = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(EdgeExtension,"=");
				leerzeile = st2.nextToken();
				EdgeExtension = st2.nextToken();
		}
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		checkCalImFromTo = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(checkCalImFromTo,"=");
				leerzeile = st2.nextToken();
				checkCalImFromTo = st2.nextToken();
		}
		calculateImFrom = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(calculateImFrom,"=");
				leerzeile = st2.nextToken();
				calculateImFrom = st2.nextToken();
		}
		calculateImTo = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(calculateImTo,"=");
				leerzeile = st2.nextToken();
				calculateImTo = st2.nextToken();
		}
		showImages = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(showImages,"=");
				leerzeile = st2.nextToken();
				showImages = st2.nextToken();
		}
		CPUs = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(CPUs,"=");
				leerzeile = st2.nextToken();
				CPUs = st2.nextToken();
		}
		countStart = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(countStart,"=");
				leerzeile = st2.nextToken();
				countStart = st2.nextToken();
		}
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		leerzeile = st1.nextToken();
		ColorDarkPath = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorDarkPath,":");
				leerzeile = st2.nextToken();
				ColorDarkPath = st2.nextToken();
				//System.out.println("test=" + ColorDarkPath);
		}
		ColorFlat1Path = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorFlat1Path,":");
				leerzeile = st2.nextToken();
				ColorFlat1Path = st2.nextToken();
		}
		ColorFlat2Path = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorFlat2Path,":");
				leerzeile = st2.nextToken();
				ColorFlat2Path = st2.nextToken();
		}
		ColorFlatPathOut = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorFlatPathOut,":");
				leerzeile = st2.nextToken();
				ColorFlatPathOut = st2.nextToken();
		}
		ColorSaveNameFlat = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorSaveNameFlat,":");
				leerzeile = st2.nextToken();
				ColorSaveNameFlat = st2.nextToken();
		}
		ColorFormatFlat = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorFormatFlat,":");
				leerzeile = st2.nextToken();
				ColorFormatFlat = st2.nextToken();
		}
		ColorScalingFlat = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorScalingFlat,":");
				leerzeile = st2.nextToken();
				ColorScalingFlat = st2.nextToken();
		}
		ColorScalingFlatFrom = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorScalingFlatFrom,":");
				leerzeile = st2.nextToken();
				ColorScalingFlatFrom = st2.nextToken();
		}
		ColorScalingFlatTo = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorScalingFlatTo,":");
				leerzeile = st2.nextToken();
				ColorScalingFlatTo = st2.nextToken();
		}
		ColorPhasePathOut = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorPhasePathOut,":");
				leerzeile = st2.nextToken();
				ColorPhasePathOut = st2.nextToken();
		}
		ColorBeta = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorBeta,":");
				leerzeile = st2.nextToken();
				ColorBeta = st2.nextToken();
		}
		ColorDelta = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorDelta,":");
				leerzeile = st2.nextToken();
				ColorDelta = st2.nextToken();
		}
		ColorDeltaBeta = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorDeltaBeta,":");
				leerzeile = st2.nextToken();
				ColorDeltaBeta = st2.nextToken();
				//System.out.println("test=" + ColorDeltaBeta);
		}
		ColorGauss = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorGauss,":");
				leerzeile = st2.nextToken();
				ColorGauss = st2.nextToken();
				//System.out.println("test=" + ColorDeltaBeta);
		}
		ColorOffset = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorOffset,":");
				leerzeile = st2.nextToken();
				ColorOffset = st2.nextToken();
				//System.out.println("test=" + ColorDeltaBeta);
		}
		ColorDistance = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorDistance,":");
				leerzeile = st2.nextToken();
				ColorDistance = st2.nextToken();
		}
		ColorEnergy = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorEnergy,":");
				leerzeile = st2.nextToken();
				ColorEnergy = st2.nextToken();
		}
		ColorPixSize = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorPixSize,":");
				leerzeile = st2.nextToken();
				ColorPixSize = st2.nextToken();
		}
		ColorSaveNamePhase = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorSaveNamePhase,":");
				leerzeile = st2.nextToken();
				ColorSaveNamePhase = st2.nextToken();
		}
		ColorFormatPhase = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorFormatPhase,":");
				leerzeile = st2.nextToken();
				ColorFormatPhase = st2.nextToken();
		}
		ColorScalingPhase = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorScalingPhase,":");
				leerzeile = st2.nextToken();
				ColorScalingPhase = st2.nextToken();
		}
		ColorScalingPhaseFrom = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorScalingPhaseFrom,":");
				leerzeile = st2.nextToken();
				ColorScalingPhaseFrom = st2.nextToken();
		}
		ColorScalingPhaseTo = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorScalingPhaseTo,":");
				leerzeile = st2.nextToken();
				ColorScalingPhaseTo = st2.nextToken();
		}
		ColorCalculateImagesFrom = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorCalculateImagesFrom,":");
				leerzeile = st2.nextToken();
				ColorCalculateImagesFrom = st2.nextToken();
		}
		ColorCalculateImagesTo = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorCalculateImagesTo,":");
				leerzeile = st2.nextToken();
				ColorCalculateImagesTo = st2.nextToken();
		}
		ColorCPUs = st1.nextToken();
		{
			StringTokenizer st2 = new StringTokenizer(ColorCPUs,":");
				leerzeile = st2.nextToken();
				ColorCPUs = st2.nextToken();
		}

	}
	else{
		String path = f.getAbsolutePath();
		//System.out.println("test=" + path);
		writePar wp = new writePar();
		int lengthStr = path.length()-15;
		path = path.substring(0, lengthStr);
		wp.runWritePar(path, path, path, path,"false","false","false",
						"false",path,"outflat","TIFF 16 bit","Scale each image to its min/max","-1","1",
						"false","false","false", "1","1","0","1","0.2","1","1","1",path,"outphase","TIFF 16 bit","Scale each image to its min/max","-1","1",
						"false","1","2","false","1","1",
						"java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]",
						"java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]",
						"java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]","java.awt.Color[r=192,g=192,b=192]", "false","false");
		runReadPar();
	}
	}
}
