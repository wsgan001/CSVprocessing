package com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class OutputCSV {
	static String dataStoredFolderName = "./result";
	static File outputPath = new File(dataStoredFolderName);
	static public List<File> fileList = new ArrayList<File>(); // ファイル名をリストとして保存する
	static OutputCSV wrteCSV = new OutputCSV(); // 自分自身を作成

	private OutputCSV() { // コンストラクタをprivateに
		if (!outputPath.exists()) { // まずフォルダがなければ作成
			outputPath.mkdir();
		}
	}
	

	static public void writeMap(int ShareOrNot) {
		try {

			String filename="";
			if(ShareOrNot==0){
				filename ="notShare.csv";
			}else if(ShareOrNot==1){
				filename ="Share.csv";
			}
			FileWriter fw = new FileWriter(outputPath + "/" + filename); // 追記モード
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			
			//alpha列(1行目)
			pw.write("" + ",");
			for(double alpha=0.5;alpha<=2.5;alpha+=0.5){
				pw.write("alpha" + ",");
				pw.write(alpha + ",");
				pw.write("" + ",");
			}
			pw.println();
			
			//以下各betaについて
			for(double beta=0.25;beta<=1.0;beta+=0.25){
				//初行
				pw.write("beta" + ",");
				for(double alpha=0.5;alpha<=2.5;alpha+=0.5){
					pw.write("Firm" + ",");
					pw.write("firm0" + ",");
					pw.write("firm1" + ",");
				}
				pw.println();
				//AverageQualityの行
				pw.write(beta + ",");
				for(double alpha=0.5;alpha<=2.5;alpha+=0.5){
						pw.write("Quality" + ",");
						pw.write(FirmQuality.allQualityList.get("alpha"+alpha+"beta"+beta+"ShareOrNot"+ShareOrNot).get(0)+",");
						pw.write(FirmQuality.allQualityList.get("alpha"+alpha+"beta"+beta+"ShareOrNot"+ShareOrNot).get(1)+",");
				}
				pw.println();
				//AveragePriceの行
				pw.write(",");
				for(double alpha=0.5;alpha<=2.5;alpha+=0.5){
						pw.write("Price" + ",");
						pw.write(FirmQuality.allPriceList.get("alpha"+alpha+"beta"+beta+"ShareOrNot"+ShareOrNot).get(0)+",");
						pw.write(FirmQuality.allPriceList.get("alpha"+alpha+"beta"+beta+"ShareOrNot"+ShareOrNot).get(1)+",");
				}
				pw.println();
				//AverageProfitの行
				pw.write(",");
				for(double alpha=0.5;alpha<=2.5;alpha+=0.5){
						pw.write("Profit" + ",");
						pw.write(FirmProfit.allProfitList.get("alpha"+alpha+"beta"+beta+"ShareOrNot"+ShareOrNot).get(0)+",");
						pw.write(FirmProfit.allProfitList.get("alpha"+alpha+"beta"+beta+"ShareOrNot"+ShareOrNot).get(1)+",");
				}
				pw.println();
				//以下Consumerのやつ
				pw.write(",");
				for(double alpha=0.5;alpha<=2.5;alpha+=0.5){
					pw.write("Consumer" + ",");
					pw.write(",");
					pw.write("allConsumer" + ",");
				}
				pw.println();
				//AvereageUsedTime
				pw.write(",");
				for(double alpha=0.5;alpha<=2.5;alpha+=0.5){
					pw.write("UsedTime" + ",");
					pw.write(",");
					pw.write(ConsumerDecision.allConsumerList.get("alpha"+alpha+"beta"+beta+"ShareOrNot"+ShareOrNot).get(0)+",");
				}
				pw.println();
				//AvereageShareSucceeded
				pw.write(",");
				for(double alpha=0.5;alpha<=2.5;alpha+=0.5){
					pw.write("ShareSucceeded" + ",");
					pw.write(",");
					pw.write(ConsumerDecision.allConsumerList.get("alpha"+alpha+"beta"+beta+"ShareOrNot"+ShareOrNot).get(1)+",");
				}
				pw.println();
				//AvereageShareFailed
				pw.write(",");
				for(double alpha=0.5;alpha<=2.5;alpha+=0.5){
					pw.write("ShareFailed" + ",");
					pw.write(",");
					pw.write(ConsumerDecision.allConsumerList.get("alpha"+alpha+"beta"+beta+"ShareOrNot"+ShareOrNot).get(2)+",");
				}
				pw.println();
				//AvereageBorrowingSucceeded
				pw.write(",");
				for(double alpha=0.5;alpha<=2.5;alpha+=0.5){
					pw.write("BorrowingSucceeded" + ",");
					pw.write(",");
					pw.write(ConsumerDecision.allConsumerList.get("alpha"+alpha+"beta"+beta+"ShareOrNot"+ShareOrNot).get(3)+",");
				}
				pw.println();
				//AvereageBorrowingFailed
				pw.write(",");
				for(double alpha=0.5;alpha<=2.5;alpha+=0.5){
					pw.write("BorrowingFailed" + ",");
					pw.write(",");
					pw.write(ConsumerDecision.allConsumerList.get("alpha"+alpha+"beta"+beta+"ShareOrNot"+ShareOrNot).get(4)+",");
				}
				pw.println();
				//AverageTatalUtility
				pw.write(",");
				for(double alpha=0.5;alpha<=2.5;alpha+=0.5){
					pw.write("TotalUtility" + ",");
					pw.write(",");
					pw.write(ConsumerUtility.allUtilityList.get("alpha"+alpha+"beta"+beta+"ShareOrNot"+ShareOrNot).get(0)+",");
				}
				pw.println();	
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static public void createNewFile(String filename, List<String> header) {
		fileList.add(new File(filename));
		try {
			FileWriter fw = new FileWriter(outputPath + "/" + filename, false);
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			pw.write(String.join(",", header));
			pw.println();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static public void createNewFile(String subFolderName, String filename, List<String> header) {
		File filePath = new File(outputPath, subFolderName);
		if (!filePath.exists())
			filePath.mkdir();
		fileList.add(new File(subFolderName, filename));
		try {
			FileWriter fw = new FileWriter(filePath + "/" + filename, false);
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			pw.write(String.join(",", header));
			pw.println();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static public void writeHashMap(String filename, HashMap<String,HashMap<String,Double>> Qmap) {
		try {
			FileWriter fw = new FileWriter(outputPath + "/" + filename, true); // 追記モード
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			for(String st:Qmap.keySet()){
				for (String at: Qmap.get(st).keySet() ){
					pw.write(st+ ",");
					pw.write(at+ ",");
					pw.write(Qmap.get(st).get(at) + ",");
					pw.println();
				}
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String putCommaString(String string){
		String putCommaString = "";
		for (int i=0;i<string.length();i++){
			putCommaString += string.substring(i, i+1)+",";
		}
		return putCommaString;
	}
	

}
