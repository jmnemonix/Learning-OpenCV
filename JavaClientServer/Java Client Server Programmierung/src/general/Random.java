package general;

public class Random {


	public static int fromRage(int min, int max) {
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}


}
