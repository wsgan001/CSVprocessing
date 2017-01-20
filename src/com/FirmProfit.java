package com;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;

public class FirmProfit{
	static String fileName ="/profit";
	int step = 0;
	static String dataStoredFolderName = "";
	static int fileNumber =0;
	double firm0TotalProfit;
	double firm1TotalProfit;
	double firm0AverageProfit;
	double firm1AverageProfit;
	double firm0SetAverageProfit;
	double firm1SetAverageProfit;
	public static HashMap<String,List<Double>>allProfitList = new HashMap<String,List<Double>>();


	public FirmProfit(){
		
	}
	
	//このメソッドでFirmの利益の平均を出す
	public void FirmProfitMain() throws IOException{
		//まずファイルの読み込み
		while(ReadFile.EndParameter()==false){
			List<Double> profitList = new ArrayList<>();
			dataStoredFolderName=ReadFile.NextFolder();
			for(int i=0;i<5;i++){ 
				fileNumber=i;
				FileReader fr = new FileReader(dataStoredFolderName+fileName+fileNumber+".csv");
				BufferedReader br = new BufferedReader(fr);
				String line;
		        StringTokenizer token;
		        br.readLine();
		        while ((line = br.readLine()) != null) {
		        	
		            //区切り文字","で分割する
		            token = new StringTokenizer(line, ",");
		
		            //分割した文字を合計に保存していく
		            token.nextToken();
		            firm0TotalProfit+=Double.parseDouble(token.nextToken());
//		            System.out.println("i"+i+"firm0TotalProfit"+firm0TotalProfit);
		            firm1TotalProfit+=Double.parseDouble(token.nextToken());
//		            System.out.println("i"+i+"firm1TotalProfit"+firm1TotalProfit);
		        }
		        br.close();
				
		        //1000回平均を出す
		        firm0AverageProfit+=firm0TotalProfit/1000;
		        firm1AverageProfit+=firm1TotalProfit/1000;
		        //値のクリア
		        firm0TotalProfit=0;
		        firm1TotalProfit=0;
		        
			}
			firm0SetAverageProfit=firm0AverageProfit/5;
			firm1SetAverageProfit=firm1AverageProfit/5;
			
			
			//出力用HashMapに保存
			profitList.add(firm0SetAverageProfit);
			profitList.add(firm1SetAverageProfit);
			allProfitList.put(ReadFile.key, profitList);
			
//			System.out.println(profitList);
			
			//値のクリア
			firm0AverageProfit=0;
			firm1AverageProfit=0;
			firm0SetAverageProfit=0;
			firm1SetAverageProfit=0;
		}
		System.out.println(allProfitList);
	}
	
	
}