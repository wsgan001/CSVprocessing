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

public class ConsumerUtility{
	int step = 0;
	static String dataStoredFolderName = "";
	static int fileNumber =0;
	double consumerTotalUtility;
	double consumerAverageUtility;
	double consumerSetAverageUtility;
	public static HashMap<String,List<Double>>allUtilityList = new HashMap<String,List<Double>>();


	public ConsumerUtility(){
		
	}
	
	//このメソッドでConsumerの利益の平均を出す
	public void ConsumerUtilityMain() throws IOException{
		//まずファイルの読み込み
		while(ReadFile.EndParameter()==false){
			List<Double> utilityList = new ArrayList<>();
			dataStoredFolderName=ReadFile.NextFolder();
			for(int i=0;i<5;i++){ 
				fileNumber=i;
				FileReader fru = new FileReader(dataStoredFolderName+"/utility"+fileNumber+".csv");
				BufferedReader bru = new BufferedReader(fru);
				String lineu;
		        StringTokenizer tokenu;
		        bru.readLine();
		        while ((lineu = bru.readLine()) != null) {
		        	
		            //区切り文字","で分割する
		            tokenu = new StringTokenizer(lineu, ",");
		
		            //分割した文字を合計に保存していく
		            tokenu.nextToken();
		            while (tokenu.hasMoreTokens()) {
		            	consumerTotalUtility+=Double.parseDouble(tokenu.nextToken());
		            } 
		        }
		        bru.close();
				
		        //1000回平均を出す
		        consumerAverageUtility+=consumerTotalUtility/1000;
		        //値のクリア
		        consumerTotalUtility=0;
		        
			}
			consumerSetAverageUtility=consumerAverageUtility/5;
			
			
			//出力用Listに保存
			utilityList.add(consumerSetAverageUtility);
			allUtilityList.put(ReadFile.key, utilityList);

			
//			System.out.println(utilityList);
			
			//値のクリア
			consumerAverageUtility=0;
			consumerSetAverageUtility=0;
		}
		System.out.println(allUtilityList);
	}
	
	
}