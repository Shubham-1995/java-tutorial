import java.util.Scanner;  
public class Quadratic {
	

	
	public static void main(String[] args) {
		Scanner getUserObj = new Scanner(System .in);
		 System.out.println("Enter your cofficient of x^2");
		 int firstCofficientX = getUserObj.nextInt();
		 System.out.println("Enter your cofficient of x");
		 int secondCofficientX = getUserObj.nextInt();
		 System.out.println("Enter your constant");
		 int constantValue = getUserObj.nextInt();
		 int equationRoot = quadraticMethod(firstCofficientX,secondCofficientX,constantValue);
		 if(equationRoot == 1){
			 System.out.println("equation have more than one root or equal root");
		 }else{
			 System.out.println("equation have infinite many solution"); 
		 }
  
	}
	public static int quadraticMethod(int value1,int value2,int value3){
		Quadratic quadObj = new Quadratic();
		double x1,x2,rp,ip;
		double retValue = quadObj.Descriminant(value1,value2,value3);
		if(retValue >= 0){
			x1 = (-value2 + Math.sqrt(retValue))/2*value1;
			x2 = (-value2 - Math.sqrt(retValue))/2*value1;
			System.out.println("Display x1: "+x1+" & x2 : "+x2);
			return 1;
		}else{
			rp = value2/2*value1;
			ip = (Math.sqrt(-retValue))/2*value1;
			System.out.println("Display rp: "+rp+" & ip : "+ip);
			return 0;
		}
		
	}

	public double Descriminant(int getValue1,int getValue2,int getValue3){
		double D;
		int a = getValue1,b = getValue2,c = getValue3;
		D = Math.pow(b, 2) - 4*a*c;
		
		return D;
	}
}


