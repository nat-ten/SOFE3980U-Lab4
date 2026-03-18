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
		String filePath1="model_1.csv";
		String filePath2="model_2.csv";
		String filePath3="model_3.csv";

		FileReader filereader;
		List<String[]> allData;
		float mse1, mse2, mse3, mae1, mae2, mae3, mare1, mare2, mare3;
		mse1 = mse2 = mse3 = mae1 = mae2 = mae3 = mare1 = mare2 = mare3 = 0;

		//csv 1
		try {
			filereader = new FileReader(filePath1); 
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build(); 
			allData = csvReader.readAll();
		}
		catch(Exception e) {
			System.out.println( "Error reading the CSV file" );
			return;
		}
		
		int count=0;
		for (String[] row : allData) { 
			float y_true=Float.parseFloat(row[0]);
			float y_predicted=Float.parseFloat(row[1]);
			mse1 += MSE(y_true, y_predicted);
			mae1 += MAE(y_true, y_predicted);
			mare1 += MARE(y_true, y_predicted);
			count++;
		}

		//csv 2
		try {
			filereader = new FileReader(filePath2); 
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build(); 
			allData = csvReader.readAll();
		}
		catch(Exception e) {
			System.out.println( "Error reading the CSV file" );
			return;
		}
		
		count=0;
		for (String[] row : allData) { 
			float y_true=Float.parseFloat(row[0]);
			float y_predicted=Float.parseFloat(row[1]);
			mse2 += MSE(y_true, y_predicted);
			mae2 += MAE(y_true, y_predicted);
			mare2 += MARE(y_true, y_predicted);
			count++;
		}

		//csv3
		try {
			filereader = new FileReader(filePath3); 
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build(); 
			allData = csvReader.readAll();
		}
		catch(Exception e) {
			System.out.println( "Error reading the CSV file" );
			return;
		}
		
		count=0;
		for (String[] row : allData) { 
			float y_true=Float.parseFloat(row[0]);
			float y_predicted=Float.parseFloat(row[1]);
			mse3 += MSE(y_true, y_predicted);
			mae3 += MAE(y_true, y_predicted);
			mare3 += MARE(y_true, y_predicted);
			count++;
		}
		System.out.println("for model_1.csv");
		System.out.println("\tMSE:" + mse1 / count);
		System.out.println("\tMSE: " + mae1 / count);
		System.out.println("\tMARE: " + mare1 / count);

		System.out.println("for model_2.csv");
		System.out.println("\tMSE:" + mse2 / count);
		System.out.println("\tMSE: " + mae2 / count);
		System.out.println("\tMARE: " + mare2 / count);

		System.out.println("for model_3.csv");
		System.out.println("\tMSE:" + mse3 / count);
		System.out.println("\tMSE: " + mae3 / count);
		System.out.println("\tMARE: " + mare3 / count);

		System.out.print("According to MSE, the best model is ");
		float min_mse = min(mse1, mse2, mse3);
		if (min_mse == mse1) {
			System.out.println("model_1.csv");
		}
		else if (min_mse == mse2) {
			System.out.println("model_2.csv");
		}
		else if (min_mse == mse3) {
			System.out.println("model_3.csv");
		}

		System.out.print("According to MAE, the best model is ");
		float min_mae = min(mae1, mae2, mae3);
		if (min_mae == mae1) {
			System.out.println("model_1.csv");
		}
		else if (min_mae == mae2) {
			System.out.println("model_2.csv");
		}
		else if (min_mae == mae3) {
			System.out.println("model_3.csv");
		}

		System.out.print("According to MARE, the best model is ");
		float min_mare = min(mare1, mare2, mare3);
		if (min_mare == mare1) {
			System.out.println("model_1.csv");
		}
		else if (min_mare == mare2) {
			System.out.println("model_2.csv");
		}
		else if (min_mare == mare3) {
			System.out.println("model_3.csv");
		}
    }

	public static float MSE(float y1, float y2) {
		double mse = Math.pow((y1 - y2), 2);
		return (float)mse;
	}

	public static float MAE(float y1, float y2) {
		float mae = Math.abs(y1 - y2);
		return mae;
	}

	public static float MARE(float y1, float y2) {
		float mare = (Math.abs(y1 - y2) / (Math.abs(y1) + Float.MIN_VALUE));
		return mare;
	}

	public static float min(float x, float y, float z) {
		float min = x;
		if (y < min) {
			min = y;
		}
		if (z < min) {
			min = z;
		}
		return min;
	}
}
