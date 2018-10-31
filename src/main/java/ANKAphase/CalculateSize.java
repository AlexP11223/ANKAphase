package ANKAphase;

public class CalculateSize {

	public int size;									//Gr��e des zu kalkulierenden Bildes
	
	void calSize(int width, int height){
		
		
		if (width >= height){
			size = width;
			if(width%2 > 0)
				size = width +1;
		}
		else{
			size = height;
			if(height%2 > 0)
				size = height +1;
		}
	}
	
	public int calSize2n(int width, int height){
		
		int localSize = 0;
		int returnSize = 0;
		if (width >= height){
			localSize = width;
		}
		else{
			localSize = height;
		}
		if(localSize<=2)
			returnSize = 2;
		if(localSize > 2 && localSize<=4)
			returnSize = 4;
		if(localSize > 4 && localSize<=8)
			returnSize = 8;
		if(localSize > 8 && localSize<=16)
			returnSize = 16;
		if(localSize > 16 && localSize<=32)
			returnSize = 32;
		if(localSize > 32 && localSize<=64)
			returnSize = 64;
		if(localSize > 64 && localSize<=128)
			returnSize = 128;
		if(localSize > 128 && localSize<=256)
			returnSize = 256;
		if(localSize > 256 && localSize<=512)
			returnSize = 512;
		if(localSize > 512 && localSize<=1024)
			returnSize = 1024;
		if(localSize > 1024 && localSize<=2048)
			returnSize = 2048;
		if(localSize > 2048 && localSize<=4096)
			returnSize = 4096;
		if(localSize > 4096 && localSize<=8192)
			returnSize = 8192;
		if(localSize > 8192 && localSize<=16384)
			returnSize = 16384;
		
		
		return returnSize;
	}
	
//	public static void main(String[] args){
//		calnmargin(20, 200, 2.5);
//	}
//	
	public static int calnmargin(double energy,double z,double dx, boolean edgeExtension){
		
		double h = 6.6260755*Math.pow(10, -34);		//Placksches Wirkungsquantum J/s
		double c = 2.99792458*Math.pow(10, 8);		//Lichtgeschwindigkeit		m/s
		
//		System.out.println("E:\t"+energy+"\tdistance:\t"+z+"\tpixSize:\t"+dx);
		
		double lambda;
		lambda = (c*h)/(energy*Math.pow(10, 3)*1.6022*Math.pow(10, -19));

//		System.out.println("lambda:\t"+lambda);

		double nmargin;
		int nmarginInt = 0;
//		nmargin=(5*Math.sqrt(lambda*z*Math.pow(10,-3)))/(dx*Math.pow(10,-3));
		nmargin=(3*lambda*z)/Math.pow(dx,2);
		
		if(nmargin%2 != 0){
			nmarginInt = (int)nmargin+1;
		}
		else{
			nmarginInt = (int)nmargin;
		}
		
//		System.out.println("nmargin:\t"+nmargin+"\tnmarginInt:\t"+nmarginInt);
		
		if(edgeExtension==true){
			nmarginInt=0;
		}
		
		return nmarginInt;
	}
	
	public int calSizenmargin(int length, int nmargin){
		
		int total;
		
		if(length%2 != 0)
			total = 2*nmargin+length+1;
		else
			total = 2*nmargin+length;

		return total;
	}
}
