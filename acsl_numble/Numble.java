package acsl_numble;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Numble {
	private List<Integer> horizontal;
	private List<Integer> vertical;
	
	private String calcdHorizontal;
	private String calcdVertical;
	private int crossDigit;
	
	public Numble(String horiz, String vert) {
		List<Integer> tempHoriz = new ArrayList<>(horiz.length());
		this.horizontal = new ArrayList<>(horiz.length());
		for(int i=0;i<horiz.length();i++) {
			tempHoriz.add(Character.getNumericValue(horiz.charAt(i)));
		}
		this.vertical = new ArrayList<>(vert.length());
		List<Integer> tempVert = new ArrayList<>(vert.length());
		for(int i=0;i<vert.length();i++) {
			tempVert.add(Character.getNumericValue(vert.charAt(i)));
		}
		//sort lists in ascending order
		tempHoriz.sort(null);
		tempVert.sort(null);
		//invert order to greatest to least
		for(int i=0;i<tempHoriz.size();i++) {
			this.horizontal.add(tempHoriz.get(tempHoriz.size()-1-i));
		}
		for(int i=0;i<tempVert.size();i++) {
			this.vertical.add(tempVert.get(tempVert.size()-1-i));
		}
	}
	
	/**
	 * calculate in numerical order the character string of the stated length that produces the largest sum of the digits that is a multiple of 5 for the 2 given strings.
	 * @param nums - nums to choose from
	 * @param len - length 
	 */
	public void calculate(int firstLen, int secondLen, int crossDigit) {
		this.crossDigit=crossDigit;
		this.calcdHorizontal=listToString(calculate(this.horizontal,firstLen));
		this.calcdVertical=listToString(calculate(this.vertical,secondLen));
	}
	
	/**
	 * calculate a combination of len #of nums that is the largest, includes the crossing digit, and is a multiple of 5
	 * private helper for the public calculate method
	 * @param nums - nums to choose from
	 * @param len - stated length
	 */
	private List<Integer> calculate(List<Integer> nums, int len) {
		int indexOfCross = nums.indexOf(crossDigit);
		List<boolean[]> combinations = Combinations.generateBools(nums.size(),len);
		for(boolean[] arr : combinations) {
			if(arr[indexOfCross]==true) {
				List<Integer> chosenNums = new LinkedList<>();
				int sum = 0;
				for(int i=0;i<arr.length;i++) {
					if(arr[i]==true) {
						chosenNums.add(nums.get(i));
						sum+=nums.get(i);
					}
				}
				if(sum%5==0) return chosenNums;
			}
		}
		return null;
	}
	
	/**
	 * combines all integers from a list into one continuous string
	 * @param list - a list of integers to turn into a string
	 * @return - a string that is every element of the list combined without separation
	 */
	private String listToString(List<Integer> list) {
		String res = "";
		for(Integer n : list) 
			res+=n;
		return res;
	}
	
	public void print() {
		int indexOfCrossHoriz = calcdHorizontal.indexOf(Integer.toString(crossDigit));
		int indexOfCrossVert = calcdVertical.indexOf(Integer.toString(crossDigit));
		for(int i=0;i<calcdVertical.length();i++) {
			if(i==indexOfCrossVert) System.out.println(calcdHorizontal);
			else {
				for(int n=0;n<calcdHorizontal.length();n++) {
					if(n==indexOfCrossHoriz) System.out.print(calcdVertical.substring(i,i+1));
					else System.out.print(" ");
				}
				System.out.println();
			}
		}
	}
	
	public static void main(String args[]) {
		Numble instance = null;
		System.out.println("Numble ACSL thing type stuff below");
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		while (run) {
			String str = sc.nextLine();
			if(str.equals("exit")||str.equals("end")) run=false;
			else if(instance==null) {
				String[] values = str.split(", ");
				instance=new Numble(values[0],values[1]);
			}
			else if(str.equals("reset")) {
				instance=null;
				System.out.println("Numble ACSL thing type stuff below");
			}
			else{
				String[] values = str.split(", ");
				instance.calculate(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
				instance.print();
			}
		}
		sc.close();
	}
	
}
