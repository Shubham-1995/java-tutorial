import java.util.Arrays;

public class deleteArray {
	public static void main(String args[]) {
		int[] arr = {19,18,10,23,54,33,25};
		deleteMethod(arr,23);
		
	}
public static void deleteMethod(int[] arr,int matchValue){
		int flag =1;
		int[] arg = new int [arr.length-1];
		int j=0;
	for(int i=0;i<arr.length;i++){
		if(matchValue != arr[i]){
			arg[j] = arr[i];
		j++;
		}else{
			continue;
		}
		
	}
	System.out.println("see array after delete "+Arrays.toString(arg));  		   
}
	
	

}
