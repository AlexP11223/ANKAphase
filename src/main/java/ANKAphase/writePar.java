package ANKAphase;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class writePar {
	
	String pathSave;
	
	public writePar(){
		pathSave = "" + "parameter21.txt";
	}
	
	public writePar(String pathSave){
		this.pathSave = pathSave;
	}
	
	void runWritePar(String pathnameProj, String pathnameDark, String pathnameFlat1, String pathnameFlat2,
						String DarkCheckBox, String Flat1CheckBox, String Flat2CheckBox,
						String SaveFlatCheckBox, String pathnameFlatOut, String SaveNameFlat, String FormatFlat,
						String ScalingFlat, String ScalingFlatFromText, String ScalingFlatToText,
						String SavePhaseCheckBox, String deltaBetaCheckBox, String ImageRestoreCheckBox, String beta, String delta, String deltabeta, String gauss, String offset, String distance, String energy, String pixSize,
						String pathnamePhaseOut, String SaveNamePhase, String FormatPhase,
						String ScalingPhase, String ScalingPhaseFromText, String ScalingPhaseToText,
						String checkCalculateImFromTo, String calculateImFrom, String calculateImTo,
						String showImages, String CPUs, String countStart,
						String ColorDarkPath, String ColorFlat1Path, String ColorFlat2Path,
						String ColorFlatPathOut, String ColorSaveNameFlat, String ColorFormatFlat,
						String ColorScalingFlat, String ColorScalingFlatFrom, String ColorScalingFlatTo,
						String ColorPhasePathOut,String ColorBeta, String ColorDelta, String ColorDeltaBeta, String ColorGauss, String ColorOffset, String ColorDistance, String ColorEnergy, String ColorPixSize,
						String ColorSaveNamePhase, String ColorFormatPhase,
						String ColorScalingPhase, String ColorScalingPhaseFrom, String ColorScalingPhaseTo,
						String ColorCalculateImagesFrom, String ColorCalculateImagesTo, String ColorCPUs, String Interpolate, String EdgeExtension){
	
		File fn = new File(pathSave);
		String path = fn.getAbsolutePath();
		try{
			DataOutputStream parF = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
			parF.writeBytes("#");
			parF.writeBytes("\n");
			parF.writeBytes("#\tgeneral settings");
			parF.writeBytes("\n");
			parF.writeBytes("#");
			parF.writeBytes("\n");
			parF.writeBytes("#############################################################################");
			parF.writeBytes("\n");
			parF.writeBytes("Projection images directory="+pathnameProj);
			parF.writeBytes("\n");
			parF.writeBytes("Dark-Field images directory="+pathnameDark);
			parF.writeBytes("\n");
			parF.writeBytes("Flat-Field images 1 directory="+pathnameFlat1);
			parF.writeBytes("\n");
			parF.writeBytes("Flat-Field images 2 directory="+pathnameFlat2);
			parF.writeBytes("\n");
			parF.writeBytes("CheckBox Dark-field images=" + DarkCheckBox);
			parF.writeBytes("\n");
			parF.writeBytes("CheckBox Flat1-field images=" + Flat1CheckBox);
			parF.writeBytes("\n");
			parF.writeBytes("CheckBox Flat2-field images=" + Flat2CheckBox);
			parF.writeBytes("\n");
			parF.writeBytes("#");
			parF.writeBytes("\n");
			parF.writeBytes("#\tSave flat-correction settings");
			parF.writeBytes("\n");
			parF.writeBytes("#");
			parF.writeBytes("\n");
			parF.writeBytes("#############################################################################");
			parF.writeBytes("\n");
			parF.writeBytes("Save flat corrected images=" + SaveFlatCheckBox);
			parF.writeBytes("\n");
			parF.writeBytes("Save flat corrected images directory=" + pathnameFlatOut);
			parF.writeBytes("\n");
			parF.writeBytes("Save name of flat corrected images=" + SaveNameFlat);
			parF.writeBytes("\n");
			parF.writeBytes("Format of flat corrected images=" + FormatFlat);
			parF.writeBytes("\n");
			parF.writeBytes("Scaling option of flat corrected images=" + ScalingFlat);
			parF.writeBytes("\n");
			parF.writeBytes("Scaling from label=" + ScalingFlatFromText);
			parF.writeBytes("\n");
			parF.writeBytes("Scaling to label=" + ScalingFlatToText);
			parF.writeBytes("\n");
			parF.writeBytes("Interpolate Flat-Fields=" + Interpolate);
			parF.writeBytes("\n");
			parF.writeBytes("#");
			parF.writeBytes("\n");
			parF.writeBytes("#\tPhaseretrieval settings");
			parF.writeBytes("\n");
			parF.writeBytes("#");
			parF.writeBytes("\n");
			parF.writeBytes("#############################################################################");
			parF.writeBytes("\n");
			parF.writeBytes("Save phaseretrieval images=" + SavePhaseCheckBox);
			parF.writeBytes("\n");
			parF.writeBytes("Use DeltaBeta ratio=" + deltaBetaCheckBox);
			parF.writeBytes("\n");
			parF.writeBytes("Apply image restoration=" + ImageRestoreCheckBox);
			//System.out.println("Use DeltaBeta ratio=" + deltaBetaCheckBox);
			parF.writeBytes("\n");
			parF.writeBytes("beta=" + beta);
			parF.writeBytes("\n");
			parF.writeBytes("delta=" + delta);
			parF.writeBytes("\n");
			parF.writeBytes("deltabeta=" + deltabeta);
			parF.writeBytes("\n");
			parF.writeBytes("gauss=" + gauss);
			parF.writeBytes("\n");
			parF.writeBytes("offset=" + offset);
			parF.writeBytes("\n");
			parF.writeBytes("distance=" + distance);
			parF.writeBytes("\n");
			parF.writeBytes("energy=" + energy);
			parF.writeBytes("\n");
			parF.writeBytes("pixel Size=" + pixSize);
			parF.writeBytes("\n");
			parF.writeBytes("Save flat corrected images directory=" + pathnamePhaseOut);
			parF.writeBytes("\n");
			parF.writeBytes("Save name of flat corrected images=" + SaveNamePhase);
			parF.writeBytes("\n");
			parF.writeBytes("Format of flat corrected images=" + FormatPhase);
			parF.writeBytes("\n");
			parF.writeBytes("Scaling option of flat corrected images=" + ScalingPhase);
			parF.writeBytes("\n");
			parF.writeBytes("Scaling from label=" + ScalingPhaseFromText);
			parF.writeBytes("\n");
			parF.writeBytes("Scaling to label=" + ScalingPhaseToText);
			parF.writeBytes("\n");
			parF.writeBytes("Automatic edge extension=" + EdgeExtension);
			parF.writeBytes("\n");
			parF.writeBytes("#");
			parF.writeBytes("\n");
			parF.writeBytes("#\trun settings");
			parF.writeBytes("\n");
			parF.writeBytes("#");
			parF.writeBytes("\n");
			parF.writeBytes("#############################################################################");
			parF.writeBytes("\n");
			parF.writeBytes("calculate images from to=" + checkCalculateImFromTo);
			parF.writeBytes("\n");
			parF.writeBytes("calculate images from=" + calculateImFrom);
			parF.writeBytes("\n");
			parF.writeBytes("calculate images to=" + calculateImTo);
			parF.writeBytes("\n");
			parF.writeBytes("show images=" + showImages);
			parF.writeBytes("\n");
			parF.writeBytes("CPUs=" + CPUs);
			parF.writeBytes("\n");
			parF.writeBytes("count starting at=" + countStart);
			parF.writeBytes("\n");
			parF.writeBytes("#");
			parF.writeBytes("\n");
			parF.writeBytes("#\tcolor settings");
			parF.writeBytes("\n");
			parF.writeBytes("#");
			parF.writeBytes("\n");
			parF.writeBytes("#############################################################################");
			parF.writeBytes("\n");
			parF.writeBytes("Color DarkPath:" + ColorDarkPath);
			parF.writeBytes("\n");
			parF.writeBytes("Color Flat1Path:" + ColorFlat1Path);
			parF.writeBytes("\n");
			parF.writeBytes("Color Flat2Path:" + ColorFlat2Path);
			parF.writeBytes("\n");
			parF.writeBytes("Color FlatPathOut:" + ColorFlatPathOut);
			parF.writeBytes("\n");
			parF.writeBytes("Color SaveNameFlat:" + ColorSaveNameFlat);
			parF.writeBytes("\n");
			parF.writeBytes("Color FormatFlat:" + ColorFormatFlat);
			parF.writeBytes("\n");
			parF.writeBytes("Color ScalingFlat:" + ColorScalingFlat);
			parF.writeBytes("\n");
			parF.writeBytes("Color ScalingFlatFrom:" + ColorScalingFlatFrom);
			parF.writeBytes("\n");
			parF.writeBytes("Color ScalingFlat:" + ColorScalingFlatTo);
			parF.writeBytes("\n");
			parF.writeBytes("Color PhasePathOut:" + ColorPhasePathOut);
			parF.writeBytes("\n");
			parF.writeBytes("Color Beta:" + ColorBeta);
			parF.writeBytes("\n");
			parF.writeBytes("Color Delta:" + ColorDelta);
			parF.writeBytes("\n");
			parF.writeBytes("Color DeltaBeta:" + ColorDeltaBeta);
			parF.writeBytes("\n");
			parF.writeBytes("Color Gauss:" + ColorGauss);
			parF.writeBytes("\n");
			parF.writeBytes("Color Offset:" + ColorOffset);
			parF.writeBytes("\n");
			parF.writeBytes("Color Distance:" + ColorDistance);
			parF.writeBytes("\n");
			parF.writeBytes("Color Energy:" + ColorEnergy);
			parF.writeBytes("\n");
			parF.writeBytes("Color PixSize:" + ColorPixSize);
			parF.writeBytes("\n");
			parF.writeBytes("Color SaveNamePhase:" + ColorSaveNamePhase);
			parF.writeBytes("\n");
			parF.writeBytes("Color FormatPhase:" + ColorFormatPhase);
			parF.writeBytes("\n");
			parF.writeBytes("Color ScalingPhase:" + ColorScalingPhase);
			parF.writeBytes("\n");
			parF.writeBytes("Color ScalingPhaseFrom:" + ColorScalingPhaseFrom);
			parF.writeBytes("\n");
			parF.writeBytes("Color ScalingPhase:" + ColorScalingPhaseTo);
			parF.writeBytes("\n");
			parF.writeBytes("Color Calculate images from:" + ColorCalculateImagesFrom);
			parF.writeBytes("\n");
			parF.writeBytes("Color Calculate images to:" + ColorCalculateImagesTo);
			parF.writeBytes("\n");
			parF.writeBytes("Color CPUs:" + ColorCPUs);
			parF.writeBytes("\n");
			parF.close();
		}
		catch (FileNotFoundException e){
		}
		catch (Exception e){
		}
	}	
}
