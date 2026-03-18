package com.ontariotechu.sofe3980U;


import java.io.FileReader; 
import java.util.List;
import com.opencsv.*;

/**
 * Evaluate Single Variable Continuous Regression
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		int[][] matrix = new int[5][5];
		float CE = 0;

		String filePath="model.csv";
		FileReader filereader;
		List<String[]> allData;
		try{
			filereader = new FileReader(filePath); 
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build(); 
			allData = csvReader.readAll();
		}
		catch(Exception e){
			System.out.println( "Error reading the CSV file" );
			return;
		}
		
		int count=0;
		float[] y_predicted=new float[5];
		for (String[] row : allData) { 
			int y_true=Integer.parseInt(row[0]);
			float[] predictions = new float[5];
			predictions[0] = Float.parseFloat(row[1]);
			predictions[1] = Float.parseFloat(row[2]);
			predictions[2] = Float.parseFloat(row[3]);
			predictions[3] = Float.parseFloat(row[4]);
			predictions[4] = Float.parseFloat(row[5]);

			//get index of largest value
			int index = 0;
			for (int i = 0; i < 5; i++) {
				if (predictions[i] > predictions[index]) {
					index = i;
				}
			}

			matrix[y_true - 1][index] += 1;

			CE += CE(y_true, predictions);

			count++;
		} 
		//print confusion matrix
		System.out.println("CE = " + (CE / count * -1));
		System.out.println("Confusion matrix");
		System.out.println("\t\ty=1\ty=2\ty=3\ty=4\ty=5");

		for (int i = 0; i < 5; i++) {
			System.out.print("\ty^=" + i + "\t");
			for (int j = 0; j < 5; j++) {
				System.out.print(matrix[j][i] + "\t");
			}
			System.out.println();
		}
	}
	public static float CE(int y_true, float[] predictions) {
		double ce = Math.log(predictions[y_true - 1]);
		return (float)ce;
	}
}
