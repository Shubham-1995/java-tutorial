import java.util.ArrayList;

public class deleteArray {
	public int[] deleteMethod(int[] arr,int matchValue){
		int arg[] = arr;
		ArrayList<Integer> list = new ArrayList<Integer>();
		 
		// Delete the Array Element position
		int getVal = matchValue;
		for (int i = 0; i < arg.length; i++) {
		// Delete Function
		if (getVal == arg[i]) {
		for (int j = i + 1; i < arg.length - 1; j++) {
		arg[i] = arg[j];
		i++;
		}
		}
		}
		// After delete the array element Output
		//System.out.println("The output of Array After Delete");
		for (int i = 0; i < arg.length - 1; i++) {
			list.add(arg[i]);
		}
		//System.out.println(list.size());
		int[] result = new int[list.size()];
		 int i = 0;
		    for (Integer e : list) {
		    	//System.out.println(e.intValue());
		        result[i] = e.intValue();
		        //System.out.print(result[i]);
		        i++;
		    }
		return result;    
	}
	

}
