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

public class FirmQuality{
	int step = 0;
	static String dataStoredFolderName = "";
	static int fileNumber =0;
	double firm0TotalQuality;
	double firm1TotalQuality;
	double firm0AverageQuality;
	double firm1AverageQuality;
	double firm0SetAverageQuality;
	double firm1SetAverageQuality;
	public static HashMap<String,List<Double>>allQualityList = new HashMap<String,List<Double>>();
	double firm0TotalPrice;
	double firm1TotalPrice;
	double firm0AveragePrice;
	double firm1AveragePrice;
	double firm0SetAveragePrice;
	double firm1SetAveragePrice;
	public static HashMap<String,List<Double>>allPriceList = new HashMap<String,List<Double>>();

	public FirmQuality(){
		
	}
	
	//このメソッドでFirmの利益の平均を出す？
	public void FirmQualityMain() throws IOException{
		//まずファイルの読み込み
		while(ReadFile.EndParameter()==false){
			List<Double> qualityList = new ArrayList<>();
			List<Double> priceList = new ArrayList<>();
			dataStoredFolderName=ReadFile.NextFolder();
			
			
			//まず企業ごとの
			int activeFirm0=0;
			int activeFirm1=0;
			for(int i=0;i<5;i++){ 
				fileNumber=i;
				FileReader fr = new FileReader(dataStoredFolderName+"/firm_quality"+fileNumber+".csv");
				BufferedReader br = new BufferedReader(fr);
				String line;
		        StringTokenizer token;
		        br.readLine();
		        
		      //まず企業ごとの質平均の保存
		        while ((line = br.readLine()) != null) {
		        	
		            //区切り文字","で分割する
		            token = new StringTokenizer(line, ",");
		
		            //分割した文字を合計に保存していく
		            token.nextToken();
		            firm0TotalQuality+=Double.parseDouble(token.nextToken());
//		            System.out.println("i"+i+"firm0TotalQuality"+firm0TotalQuality);
		            firm1TotalQuality+=Double.parseDouble(token.nextToken());
//		            System.out.println("i"+i+"firm1TotalQuality"+firm1TotalQuality);
		        }
		        br.close();
		        
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
		        //質がゼロでなければ
		        if(firm0TotalQuality!=0){
		        	firm0AverageQuality+=firm0TotalQuality/1000;
		        	firm0AveragePrice+=firm0TotalPrice/1000;
		        	activeFirm0++;
		        }
		        if(firm1TotalQuality!=0){
		        	firm1AverageQuality+=firm1TotalQuality/1000;
		        	firm1AveragePrice+=firm1TotalPrice/1000;
		        	activeFirm1++;
		        }
		        
		        //値のクリア
		        firm0TotalPrice=0;
		        firm1TotalPrice=0;
		        firm0TotalQuality=0;
		        firm1TotalQuality=0;
		        
			}
			firm0SetAverageQuality=firm0AverageQuality/activeFirm0;
			firm1SetAverageQuality=firm1AverageQuality/activeFirm1;
			firm0SetAveragePrice=firm0AveragePrice/activeFirm0;
			firm1SetAveragePrice=firm1AveragePrice/activeFirm1;
			
			
			//出力用HashMapに保存
			qualityList.add(firm0SetAverageQuality);
			qualityList.add(firm1SetAverageQuality);
			allQualityList.put(ReadFile.key, qualityList);
			
			//出力用HashMapに保存
			priceList.add(firm0SetAveragePrice);
			priceList.add(firm1SetAveragePrice);
			allPriceList.put(ReadFile.key, priceList);
			
//			System.out.println(qualityList);
			
			//値のクリア
			firm0AverageQuality=0;
			firm1AverageQuality=0;
			firm0SetAverageQuality=0;
			firm1SetAverageQuality=0;
			firm0AveragePrice=0;
			firm1AveragePrice=0;
			firm0SetAveragePrice=0;
			firm1SetAveragePrice=0;
		}
		System.out.println(allQualityList);
	}
	
	
}