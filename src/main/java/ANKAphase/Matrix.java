package ANKAphase;

/*
Program: Java-Matrix-Class (JMC)
This program provides arithmetical functions for solving matrix-problems

Copyright (C) 2006 Karsten Bettray
Version: v0.1f

This program is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License
as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License along with this library;
if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110, USA *
*/

/**
 * <u>Klasse zur Berechnung und R&uuml;ckgabe von Matrizen mit Java</u><br>
 * Dieser Quelltext ist vollst&auml;ndig f&uuml;r jeden frei verf&uuml;gbar<br>
 * und steht der pers&ouml;nlichen Weiterverwendung zur Verf&uuml;gung.
 * @author Karsten Bettray - 31.10.2005<br>
 * Universit&auml;t Duisburg-Essen<br>
 * @version 0.2
 */
public class Matrix
{
	private double[][] matrixArray = null;
	private Complex[][] complexArray = null;
	
	/**
	 * <u>Konstruktor - f&uuml;r reele Zahlen</u>
	 * @param matrixArray
	 */
	public Matrix(double[][] matrixArray)
	{
		this.matrixArray = matrixArray;
	}
	/**
	 * <u>Konstruktor - f&uuml;r komplexe Zahlen</u>
	 * @param complexArray
	 */
	public Matrix(Complex[][] complexArray)
	{
		this.complexArray = complexArray;
	}
	
	/**
	 * <u>Addieren mit Matrix</u>
	 * @param matrix2
	 * @return
	 */
	public Matrix add(Matrix matrix2)
	{
		if(matrixArray!=null && complexArray==null) {
			// Reelle Matrizen addieren
			if(matrixArray.length==matrix2.getMatrixArray().length && matrixArray[0].length==matrix2.getMatrixArray()[0].length) {
				double[][] swap = new double[matrixArray.length][matrixArray[0].length];		// Dimension: Zeilen * Spalten
				
				for(int i=0; i<=matrixArray.length-1; i++) {					// Spalten array
					for(int j=0; j<=matrixArray[i].length-1; j++) {			// Zeilen array
						swap[i][j] = matrixArray[i][j] + matrix2.getMatrixArray()[i][j];
					}
				}
				return new Matrix(swap);
			} else {
				System.out.println("Matrizen-Dimensionen stimmen nicht Ueberein!");
				return null;
			}
		} else if(matrixArray==null && complexArray!=null) {
			// Komplexe Matrizen addieren
			if(complexArray.length==matrix2.getComplexArray().length && complexArray[0].length==matrix2.getComplexArray()[0].length) {
				Complex[][] swap = new Complex[complexArray.length][complexArray[0].length];		// Dimension: Zeilen * Spalten
				
				for(int i=0; i<=complexArray.length-1; i++) {					// Spalten array
					for(int j=0; j<=complexArray[i].length-1; j++) {			// Zeilen array
						swap[i][j] = complexArray[i][j].add(matrix2.getComplexArray()[i][j]);
					}
				}
				return new Matrix(swap);
			} else {
				System.out.println("Matrizen-Dimensionen stimmen nicht Ueberein!");
				return null;
			}			
		}
		return null;
	}

	/**
	 * <u>Subtraktion mit Matrix</u>
	 * @param matrix2
	 * @return
	 */
	public Matrix sub(Matrix matrix2)
	{
		if(matrixArray!=null && complexArray==null) {
			// Reelle Matrizen subtrahieren
			if(matrixArray.length==matrix2.getMatrixArray().length && matrixArray[0].length==matrix2.getMatrixArray()[0].length) {
				double[][] swap = new double[matrixArray.length][matrixArray[0].length];		// Dimension: Zeilen * Spalten
				
				for(int i=0; i<=matrixArray.length-1; i++) {					// Spalten array
					for(int j=0; j<=matrixArray[i].length-1; j++) {			// Zeilen array
						swap[i][j] = matrixArray[i][j] - matrix2.getMatrixArray()[i][j];
					}
				}
				return new Matrix(swap);
			} else {
				System.out.println("Matrizen-Dimensionen stimmen nicht Ueberein!");
				return null;
			}
		} else if(matrixArray==null && complexArray!=null) {
			// Komplexe Matrizen subtrahieren
			if(complexArray.length==matrix2.getComplexArray().length && complexArray[0].length==matrix2.getComplexArray()[0].length) {
				Complex[][] swap = new Complex[complexArray.length][complexArray[0].length];		// Dimension: Zeilen * Spalten
				
				for(int i=0; i<=complexArray.length-1; i++) {					// Spalten array
					for(int j=0; j<=complexArray[i].length-1; j++) {			// Zeilen array
						swap[i][j] = complexArray[i][j].sub(matrix2.getComplexArray()[i][j]);
					}
				}
				return new Matrix(swap);
			} else {
				System.out.println("Matrizen-Dimensionen stimmen nicht Ueberein!");
				return null;
			}			
		}
		return null;
	}

	/**
	 * <u>Multiplizierem mit Matrix</u>
	 * @param matrix2
	 * @return
	 */
	public Matrix mul(Matrix matrix2)
	{
		if(matrixArray!=null && complexArray==null) {
			// Reelle Matrizen multiplizieren
			if(matrixArray[0].length==matrix2.getMatrixArray().length) {
			double[][] swap = new double[matrixArray.length][matrix2.getMatrixArray()[0].length];		// Dimension: Zeilen * Spalten
			double sum = 0d;
			
			for(int i=0; i<=matrixArray.length-1; i++) {					// Spalten array
				for(int j=0; j<=matrix2.getMatrixArray()[0].length-1; j++) {			// Zeilen array
					sum = 0d;
					for(int k=0; k<=matrix2.getMatrixArray().length-1; k++) {
						sum += matrixArray[i][k] * matrix2.getMatrixArray()[k][j];
					}
					swap[i][j] = sum;
				}
			}
			return new Matrix(swap);
			}
			else {
				System.out.println("Matrizen-Dimensionen nicht korrekt!");
				return null;
			}
		} else if(matrixArray==null && complexArray!=null) {
			// Komplexe Matrizen multiplizieren
			if(complexArray[0].length==matrix2.getComplexArray().length) {
				Complex[][] swap = new Complex[complexArray.length][matrix2.getComplexArray()[0].length];		// Dimension: Zeilen * Spalten
				Complex sum, mulComp;
				
				for(int i=0; i<=complexArray.length-1; i++) {					// Spalten array
					for(int j=0; j<=matrix2.getComplexArray()[0].length-1; j++) {			// Zeilen array
						sum = Complex.ZERO;
						for(int k=0; k<=matrix2.getComplexArray().length-1; k++) {
							mulComp = (complexArray[i][k].mul(matrix2.getComplexArray()[k][j]));
//							System.out.print(" - complexArray: "); complexArray[i][k].printComplexNumber();
//							System.out.print(" - matrix2: "); matrix2.getComplexArray()[k][j].printComplexNumber();
//							System.out.print(" - MUL of complexArray & matrix2: "); mulComp.printComplexNumber();
//							System.out.print(" - sum: "); sum.printComplexNumber();
							sum = sum.add(mulComp);
//							System.out.print(" -> "); sum.printComplexNumber();
						}
						swap[i][j] = sum;
					}
				}
				return new Matrix(swap);
				}
				else {
					System.out.println("Matrizen-Dimensionen nicht korrekt!");
					return null;
				}

		}
		return null;
	}
	
	/**
	 * <u>Matrix potenzieren</u>
	 * @param expon
	 */
	public Matrix pow(int expon)
	{
		if(matrixArray!=null && complexArray==null) {
			if(matrixArray.length==matrixArray[0].length) {
				Matrix swap = new Matrix(this.getMatrixArray());
				
				for(int i=1; i<=expon-1; i++) {
					swap = swap.mul(this);
				}
				return swap;
			} else
				return null;
		} else {
			if(matrixArray==null && complexArray!=null) {
				if(complexArray.length==complexArray[0].length) {
					Matrix swap = new Matrix(this.getComplexArray());
					
					for(int i=1; i<=expon-1; i++) {
						swap = swap.mul(this);
					}
					return swap;
				} else
					return null;
			}
		}
		return null;
	}
	
	/**
	 * @return Returns the matrixArray.
	 */
	public double[][] getMatrixArray() {
		return matrixArray;
	}

	/**
	 * <u>Ausgeben der Matrix</u>
	 *
	 */
	public void printMatrix()
	{
		if(matrixArray!=null && complexArray==null) {
			for(int i=0; i<=matrixArray.length-1; i++) {
				for(int j=0; j<=matrixArray[i].length-1; j++) {
					System.out.println("ergebnis["+i+"]["+j+"]="+matrixArray[i][j]);
				}
			}
		} else if(matrixArray==null && complexArray!=null) {
			for(int i=0; i<=complexArray.length-1; i++) {
				for(int j=0; j<=complexArray[i].length-1; j++) {
					System.out.println("ergebnis["+i+"]["+j+"]="); complexArray[i][j].printComplexNumber();
				}
			}
		}
			
	}
	/**
	 * @return Returns the complexArray.
	 */
	public Complex[][] getComplexArray() {
		return complexArray;
	}
}
