//Name: Jehad Hamayel
//Id: 1200348
//Sec: 4 labSec: 8

package assignment1;
import java.util.*;
public class Hospital {
	
	public static void main(String[] args) {
		double [][] patient ;         //Defining Arrays
		double [] calculator;
		int numOfDays,numOfReadings;  //Define variables 
		Scanner input = new Scanner(System.in);		//Create a scanner, followed by the print and input sentences
		System.out.print("Enter the number of days that a patient has entered the hospital:");
		numOfDays = input.nextInt();
		while(numOfDays<1) {		//loop to enter a valid number of days
			System.out.println("There is no data please Enter right number of days");
			numOfDays = input.nextInt();
		}
			
		calculator = new double[6];		//Reservation of Arrays with their sizes
		patient = new double[numOfDays][];
		for(int day =0 ; day < patient.length ; day++) {		//Loop for entering information on days and readings
			System.out.print("Enter the number of times that nurse read the temperature of the patient in Celsius in day"+(day+1)+" : ");	
				numOfReadings = input.nextInt();
				while(numOfReadings<0) {		//loop to enter a valid number of readings
					System.out.println("Pleas Enter right number of readings");
					numOfReadings = input.nextInt();
				}
				patient[day] = new double[(numOfReadings+2)];
				patient[day][0]= day+1;
				patient[day][1]=numOfReadings;
				if(numOfReadings > 0) {		
					System.out.println("Enter Reading in Celsius.");
				int red=2;
				while(red<patient[day].length) {	//Special loop for entering temperature values	
					System.out.print((red-1)+"_reding:");
					patient[day][red]= input.nextDouble();
					if(patient[day][red] < 30 || patient[day][red] > 45) {	//Checking the validity of the temperature value entered for the patient
						System.out.println("Enter the right value please.");
						continue;
					}
					red++;
			}
		}		
		}
		input.close();
		Summary(patient,calculator);	//Make a call to the function of the Summary It contains the following operations
			System.out.println("The Average, Maximum, and Minimum of temperatures in day: ");
			System.out.printf("The Average is: %.2f\n",calculator[0]);
			System.out.println("The maximum temperature is: "+calculator[1]);
			System.out.println("The minimum tempreture is: "+calculator[2]);
		countBelowAboveAverage(patient,calculator);   //Make a call to the function of the count Below and Above Average of temperatures
			System.out.println("The count Below Or Equal , count Above temperatures: ");//print values
			System.out.println("Total Number of reading Below or Equal average is "+calculator[3]);
			System.out.println("Total Number of reading Above average is "+calculator[4]);
		sortArray(patient);  //Calling the specialized function in ascending order
		printArray(patient); //Calling the specialized function for printing
		boolean leaveOrNot = leaveHospital(patient,calculator); //Calling the function responsible for the patient's ability to leave the hospital
		if(leaveOrNot)  //Sentence of the condition of leaving the hospital and printing the result
			System.out.printf("Your average is Stable and it's:%.2f C, You can leave",calculator[5]);
		else
			System.out.printf("Your average is not Stable and it's:%.2f C, You can't leave",calculator[5]);
	}
	public static void Summary(double[][] patient , double[] calculator) {
		//The function for outputting some calculations, such as the average, maximum and minimum values
		double max=0,min=0,average=0,sum=0;  //Define variables
		int count = 0;
		int k =0;
		while(k<patient.length) {
			if(patient[k][1]!=0) {//Give an initial value With the condition that the correct value is taken
				max = patient[k][2];	
				min = patient[k][2];
				k=patient.length;
			}
		k++;
	}
		for(int i= 0 ;i<patient.length;i++) { //loop for average
			if(patient[i][1]!=0) {
			for(int u =2;u< patient[i].length;u++) {
				sum += patient[i][u];
				count++;
			}
			for(int p=2;p<patient[i].length;p++) { //loop for Maximum Value
				if(patient[i][p]>max)
					max = patient[i][p];
			}	
			for(int j=2;j<patient[i].length;j++) { //loop for the minimum value
				if(patient[i][j]<min)
					min = patient[i][j];
			}
		}	
	}
		if(count != 0)//A condition in order not to produce a false value
			average = sum/count;
		calculator[0]=average;
		calculator[1]=max;
		calculator[2]=min;
	}
	public static void countBelowAboveAverage(double[][] patient,double[] calculator) {
		//the function of the count Below and Above Average of temperatures
		int countBelowOrEqual =0,countAbove=0;
		for(int e=0;e<patient.length;e++) {  //Loop for valuable examination
			for(int r=2;r<patient[e].length;r++) {
				if(calculator[0]>=patient[e][r])
					countBelowOrEqual++;
				if(calculator[0]<patient[e][r])
					countAbove++;		
			}	
		}
		calculator[3]=countBelowOrEqual;
		calculator[4]=countAbove;
	}
	public static void sortArray(double[][] patient) {  //Function to arrange ascending
		double tem;
		for(int k=0;k<patient.length;k++) { //loop to do ascending array
			for(int j=2 ; j<patient[k].length-1;j++) {
				for(int m=2 ; m<patient[k].length-1;m++) {
					if(patient[k][m]>patient[k][m+1]) {
						tem=patient[k][m+1];
						patient[k][m+1]=patient[k][m];
						patient[k][m]=tem;
					}
				}
			}
		}
	}
	public static void printArray(double[][] patient) {  //Function of Array printing, followed by print sentences
		System.out.println("After Sort array");
		System.out.println("Days   #of Readings Actual Reading Per Day (in Celsius)");
		for(int a = 0 ; a<patient.length;a++) {  //Array print loop
			for(int n=0;n<2;n++) {
				System.out.printf("%.0f      ",patient[a][n]);
			}
			for(int n=2;n<patient[a].length;n++) {
				System.out.printf("      %.1f  ",patient[a][n]);
			}
			System.out.println("");
		}
	}
	public static boolean leaveHospital(double[][] patient,double[] calculator) { //Function to check if sickness can leave the hospital
		double avgForLastTemps = 0,sumOfTemps=0;						//Define variables
		int counterOfTemp=0,numberOfDays=0,counterOfTempInOneDay=0;
		for(int i=(patient.length-1);i>=0;i--) {	//Loop to check if the patient can leave the hospital
			if(patient[i][1]!=0) {		//A condition in order to prevent problems in operations on heat
			for(int j=patient[i].length-1;j>=2;j--) { 
				sumOfTemps += patient[i][j];
				counterOfTempInOneDay++;
				if(counterOfTempInOneDay==2 )
					break;	
			}
			counterOfTemp+=counterOfTempInOneDay;
			counterOfTempInOneDay=0;
				numberOfDays++;
				if(numberOfDays ==2)  //A condition to prevent reading more than two days
					break;
		}
		}
		if(counterOfTemp > 0) 
			avgForLastTemps=sumOfTemps/counterOfTemp;
		calculator[5]=avgForLastTemps;
			return avgForLastTemps >= 35.5 && avgForLastTemps <= 36.5;	
	}
}

