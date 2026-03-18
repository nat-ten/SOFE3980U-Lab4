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
		int tp1, tp2, tp3, fp1, fp2, fp3, fn1, fn2, fn3, tn1, tn2, tn3;
 		tp1 = tp2 = tp3 = fp1 = fp2 = fp3 = fn1 = fn2 = fn3 = tn1 = tn2 = tn3 = 0;

		float bce1, bce2, bce3;
		bce1 = bce2 = bce3 = 0;

		String filePath="model_1.csv";
		String filePath2="model_2.csv";
		String filePath3="model_3.csv";
		FileReader filereader;
		List<String[]> allData;

		//csv 1
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
		for (String[] row : allData) { 
			int y_true=Integer.parseInt(row[0]);
			float y_predicted=Float.parseFloat(row[1]);

			//confusion matrix
			if (y_true == 1) {
				if (y_predicted < 0.5) {
					fn1++;
				}
				else {
					tp1++;
				}
			}
			else if (y_true == 0) {
				if (y_predicted < 0.5) {
					tn1++;
				}
				else {
					fp1++;
				}
				
			}

			bce1 += BCE(y_true, y_predicted);
			count++;
		}

		System.out.println("for model_1.csv");
		System.out.println("\tBCE = " + bce1 / count * -1);

		System.out.println("\tConfusion matrix");
		System.out.println("\t\t\ty=1\ty=0");
		System.out.println("\t\ty^=1\t" + tp1 + "\t" + fp1);
		System.out.println("\t\ty^=0\t" + fn1 + "\t" + tp1);

		float accuracy = ACC(tp1, fp1, fn1, tn1);
		System.out.println("\tAccuracy = " + accuracy);

		float precision = PRS(tp1, fp1);
		System.out.println("\tPrecision = " + precision);

		float recall = REC(tp1, fn1);
		System.out.println("\tRecall = " + recall);

		float f1 = F1(precision, recall);
		System.out.println("\tf1 score = " + f1);

		//csv 2
		try{
			filereader = new FileReader(filePath2); 
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build(); 
			allData = csvReader.readAll();
		}
		catch(Exception e){
			System.out.println( "Error reading the CSV file" );
			return;
		}
		
		count=0;
		for (String[] row : allData) { 
			int y_true=Integer.parseInt(row[0]);
			float y_predicted=Float.parseFloat(row[1]);

			//confusion matrix
			if (y_true == 1) {
				if (y_predicted < 0.5) {
					fn2++;
				}
				else {
					tp2++;
				}
			}
			else if (y_true == 0) {
				if (y_predicted < 0.5) {
					tn2++;
				}
				else {
					fp2++;
				}
			}

			bce2 += BCE(y_true, y_predicted);
			count++;
		}

		System.out.println("for model_2.csv");
		System.out.println("\tBCE = " + bce2 / count * -1);

		System.out.println("\tConfusion matrix");
		System.out.println("\t\t\ty=1\ty=0");
		System.out.println("\t\ty^=1\t" + tp2 + "\t" + fp2);
		System.out.println("\t\ty^=0\t" + fn2 + "\t" + tp2);

		float accuracy2 = ACC(tp2, fp2, fn2, tn2);
		System.out.println("\tAccuracy = " + accuracy2);

		float precision2 = PRS(tp2, fp2);
		System.out.println("\tPrecision = " + precision2);

		float recall2 = REC(tp2, fn2);
		System.out.println("\tRecall = " + recall2);

		float f12 = F1(precision2, recall2);
		System.out.println("\tf1 score = " + f12);

		//csv 3
		try{
			filereader = new FileReader(filePath3); 
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build(); 
			allData = csvReader.readAll();
		}
		catch(Exception e){
			System.out.println( "Error reading the CSV file" );
			return;
		}
		
		count=0;
		for (String[] row : allData) { 
			int y_true=Integer.parseInt(row[0]);
			float y_predicted=Float.parseFloat(row[1]);

			//confusion matrix
			if (y_true == 1) {
				if (y_predicted < 0.5) {
					fn3++;
				}
				else {
					tp3++;
				}
			}
			else if (y_true == 0) {
				if (y_predicted < 0.5) {
					tn3++;
				}
				else {
					fp3++;
				}
			}

			bce3 += BCE(y_true, y_predicted);
			count++;
		}

		System.out.println("for model_3.csv");
		System.out.println("\tBCE = " + bce3 / count * -1);

		System.out.println("\tConfusion matrix");
		System.out.println("\t\t\ty=1\ty=0");
		System.out.println("\t\ty^=1\t" + tp3 + "\t" + fp3);
		System.out.println("\t\ty^=0\t" + fn3 + "\t" + tp3);

		float accuracy3 = ACC(tp3, fp3, fn3, tn3);
		System.out.println("\tAccuracy = " + accuracy3);

		float precision3 = PRS(tp3, fp3);
		System.out.println("\tPrecision = " + precision3);

		float recall3 = REC(tp3, fn3);
		System.out.println("\tRecall = " + recall3);

		float f13 = F1(precision3, recall3);
		System.out.println("\tf1 score = " + f13);

		//evaluation
		System.out.print("According to BCE, the best model is ");
		float min_bce = min(bce1, bce2, bce3);
		if (min_bce == bce1) {
			System.out.println("model_1.csv");
		}
		else if (min_bce == bce2) {
			System.out.println("model_2.csv");
		}
		else if (min_bce == bce3) {
			System.out.println("model_3.csv");
		}

		System.out.print("According to Accuracy, the best model is ");
		float max_acc = max(accuracy, accuracy2, accuracy3);
		if (max_acc == accuracy) {
			System.out.println("model_1.csv");
		}
		else if (max_acc == accuracy2) {
			System.out.println("model_2.csv");
		}
		else if (max_acc == accuracy3) {
			System.out.println("model_3.csv");
		}

		System.out.print("According to Precision, the best model is ");
		float max_pre = max(precision, precision2, precision3);
		if (max_pre == precision) {
			System.out.println("model_1.csv");
		}
		else if (max_pre == precision2) {
			System.out.println("model_2.csv");
		}
		else if (max_pre == precision3) {
			System.out.println("model_3.csv");
		}

		System.out.print("According to Recall, the best model is ");
		float max_rec = max(recall, recall2, recall3);
		if (max_rec == recall) {
			System.out.println("model_1.csv");
		}
		else if (max_rec == recall2) {
			System.out.println("model_2.csv");
		}
		else if (max_rec == recall3) {
			System.out.println("model_3.csv");
		}

		System.out.print("According to F1 score, the best model is ");
		float max_f1 = max(f1, f12, f13);
		if (max_f1 == f1) {
			System.out.println("model_1.csv");
		}
		else if (max_f1 == f12) {
			System.out.println("model_2.csv");
		}
		else if (max_f1 == f13) {
			System.out.println("model_3.csv");
		}
	}
	
	public static float BCE(int y1, float y2) {
		double bce = 0;
		if (y1 == 1) {
			bce = Math.log(y2);
		}
		else {
			bce = Math.log(1 - y2);
		}
		
		return (float)bce;
	}

	public static float ACC(float tp, float fp, float fn, float tn) {
		return (tp + tn) / (tp + fp + fn + tn);
	}

	public static float PRS(float tp, float fp) {
		return tp / (tp + fp);
	}

	public static float REC(float tp, float fn) {
		return tp / (tp + fn);
	}

	public static float F1(float precision, float recall) {
		return 2 * ((precision * recall) / (precision + recall));
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

	public static float max(float x, float y, float z) {
		float max = x;
		if (y > max) {
			max = y;
		}
		if (z > max) {
			max = z;
		}
		return max;
	}
}
