package base.numerik;

public class Num {
	/**
	 * 
	 * @param M size of the base set
	 * @param n number of draws
	 * @param hold if we can draw one elemnt only once
	 * @return number of  draws 
	 */
	public static double DRAW(int M, int ntimes, boolean hold){
		double size=1.;
		if(!hold){
			size =   Math.pow(M,ntimes);
		}
		else{
			for(int index = M; index > M - ntimes; index--){
				size *= index;
			}
		}
		return size;
	}
	/**
	 * 
	 * calcs the number of sets with n elements that can be drawn out of M
	 */
}
