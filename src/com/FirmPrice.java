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

public class FirmPrice{
	int step = 0;
	static String dataStoredFolderName = "";
	static int fileNumber =0;
	double firm0TotalPrice;
	double firm1TotalPrice;
	double firm0AveragePrice;
	double firm1AveragePrice;
	double firm0SetAveragePrice;
	double firm1SetAveragePrice;
	public static HashMap<String,List<Double>>allPriceList = new HashMap<String,List<Double>>();


	public FirmPrice(){
		
	}
	
	//このメソッドでFirmの利益の平均を出す
	public void FirmPriceMain() throws IOException{
		//まずファイルの読み込み
		while(ReadFile.EndParameter()==false){
			List<Double> priceList = new ArrayList<>();
			dataStoredFolderName=ReadFile.NextFolder();
			for(int i=0;i<5;i++){ 
				fileNumber=i;
				FileReader frp = new FileReader(dataStoredFolderName+"/price"+fileNumber+".csv");
				BufferedReader brp = new BufferedReader(frp);
				String linep;
		        StringTokenizer tokenp;
		        brp.readLine();
		        while ((linep = brp.readLine()) != null) {
		        	
		            //区切り文字","で分割する
		            tokenp = new StringTokenizer(linep, ",");
		
		            //分割した文字を合計に保存していく
		            tokenp.nextToken();
		            firm0TotalPrice+=Double.parseDouble(tokenp.nextToken());
//		            System.out.println("i"+i+"firm0TotalPrice"+firm0TotalPrice);
		            firm1TotalPrice+=Double.parseDouble(tokenp.nextToken());
//		            System.out.println("i"+i+"firm1TotalPrice"+firm1TotalPrice);
		        }
		        brp.close();
				
		        //1000回平均を出す
		        firm0AveragePrice+=firm0TotalPrice/1000;
		        firm1AveragePrice+=firm1TotalPrice/1000;
		        //値のクリア
		        firm0TotalPrice=0;
		        firm1TotalPrice=0;
		        
			}
			firm0SetAveragePrice=firm0AveragePrice/5;
			firm1SetAveragePrice=firm1AveragePrice/5;
			
			
			//出力用HashMapに保存
			priceList.add(firm0SetAveragePrice);
			priceList.add(firm1SetAveragePrice);
			allPriceList.put(ReadFile.key, priceList);
			
//			System.out.println(priceList);
			
			//値のクリア
			firm0AveragePrice=0;
			firm1AveragePrice=0;
			firm0SetAveragePrice=0;
			firm1SetAveragePrice=0;
		}
		System.out.println(allPriceList);
	}
	
	
}