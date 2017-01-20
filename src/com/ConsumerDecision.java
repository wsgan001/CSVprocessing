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

public class ConsumerDecision{
	int step = 0;
	static String dataStoredFolderName = "";
	static int fileNumber =0;
	double number0=0;
	double number1=0;
	double number2=0;
	double numberN2=0;
	double number3=0;
	double numberN3=0;
	
	public static List<List<List<String>>> DecisionUtilityList = new ArrayList<>();
	
	double UsedTime;
	double ShareSucceeded;
	double ShareFailed;
	double BorrowingSucceeded;
	double BorrowingFailed;
	
	double TotalUsedTime;
	double TotalShareSucceeded;
	double TotalShareFailed;
	double TotalBorrowingSucceeded;
	double TotalBorrowingFailed;
	
	double AverageUsedTime;
	double AverageShareSucceeded;
	double AverageShareFailed;
	double AverageBorrowingSucceeded;
	double AverageBorrowingFailed;
	
	double SetAverageUsedTime;
	double SetAverageShareSucceeded;
	double SetAverageShareFailed;
	double SetAverageBorrowingSucceeded;
	double SetAverageBorrowingFailed;
	public static HashMap<String,List<Double>>allConsumerList = new HashMap<String,List<Double>>();
	
	public ConsumerDecision(){
		
		}
	
	public void ConsumerDecisionMain() throws IOException{
		//まずファイルの読み込み
		while(ReadFile.EndParameter()==false){
			List<Double> consumerList = new ArrayList<>();
			dataStoredFolderName=ReadFile.NextFolder();
			//5セット回す
			for(int i=0;i<5;i++){ 
				//各セットについて
				fileNumber=i;
				FileReader fru = new FileReader(dataStoredFolderName+"/utility"+fileNumber+".csv");
				FileReader frd = new FileReader(dataStoredFolderName+"/consumer_decision"+fileNumber+".csv");
				BufferedReader bru = new BufferedReader(fru);
				BufferedReader brd = new BufferedReader(frd);
				String lined;
				String lineu;
		        StringTokenizer tokenu;
		        StringTokenizer tokend;
		        bru.readLine();
		        brd.readLine();
		        for(int a = 0;a<50;a++){
		        	List<List<String>> EachConsumerDecisionUtility = new ArrayList<>();
		        	DecisionUtilityList.add(EachConsumerDecisionUtility);
		        }
		        
		        
		        //まず全消費者のDecisionを保存する
		        while ((lined = brd.readLine()) != null) {
		        	
		            //区切り文字","で分割する
		            tokend = new StringTokenizer(lined, ",");
		
		            //分割した文字を合計に保存していく
		            tokend.nextToken();
		            int consumer=0;
		            while (tokend.hasMoreTokens()) {
		            	List<String> EachTimeDecisionUtility = new ArrayList<>();
		            	EachTimeDecisionUtility.add(tokend.nextToken());
		            	DecisionUtilityList.get(consumer).add(EachTimeDecisionUtility);
		            	consumer++;
		            } 
                }
		        //次に全消費者のUtilityを保存する
		        int t=0;
	        	while ((lineu = bru.readLine()) != null) {
		        	
		            //区切り文字","で分割する
		            tokenu = new StringTokenizer(lineu, ",");
		
		            //分割した文字を合計に保存していく
		            tokenu.nextToken();
		            int consumer=0;
		            while (tokenu.hasMoreTokens()) {
		            	DecisionUtilityList.get(consumer).get(t).add(tokenu.nextToken());
		            	consumer++;
		            } 
		            t++;
                }
	        	
		        
		        
		        //消費者ごとの平均を出す
	        	int consumerCountUsedTime=0;
		        for (int consumer=0;consumer<50;consumer++){
		        	for(List<String> decisionUtility:DecisionUtilityList.get(consumer)){
		        		String decision=decisionUtility.get(0);
		        		String utility=decisionUtility.get(1);
		        		addNumber(decision,utility);
		        	}
		        	if(number0==0){
		        		//除く
	        			UsedTime=0;
	        		}else {
	        			UsedTime=(number0+number1)/number0;
	        			consumerCountUsedTime++;
	        		}
	        		ShareSucceeded=number3;
	        		ShareFailed=numberN3;
	        		BorrowingSucceeded=number2;
	        		BorrowingFailed=numberN2;
	        		
	        		
		        	TotalUsedTime+=UsedTime;
		        	TotalShareSucceeded+=ShareSucceeded;
		        	TotalShareFailed+=ShareFailed;
		        	TotalBorrowingSucceeded+=BorrowingSucceeded;
		        	TotalBorrowingFailed+=BorrowingFailed;
		        	
		        	//値のクリア
		        	resetNumber();
		        	UsedTime=0;
	        		ShareSucceeded=0;
	        		ShareFailed=0;
	        		BorrowingSucceeded=0;
	        		BorrowingFailed=0;	 
	        		
	        		
		        	
		        }
		        brd.close();
		        
		        AverageUsedTime=TotalUsedTime/consumerCountUsedTime;
	        	AverageShareSucceeded=TotalShareSucceeded/50;
	        	AverageShareFailed=TotalShareFailed/50;
	        	AverageBorrowingSucceeded=TotalBorrowingSucceeded/50;
	        	AverageBorrowingFailed=TotalBorrowingFailed/50;
		        
		        SetAverageUsedTime+=AverageUsedTime/5;
		        SetAverageShareSucceeded+=AverageShareSucceeded/5;
		        SetAverageShareFailed+=AverageShareFailed/5;
		        SetAverageBorrowingSucceeded+=AverageBorrowingSucceeded/5;
		        SetAverageBorrowingFailed+=AverageBorrowingFailed/5;
		        
		        DecisionUtilityList.clear();
		        
		        TotalUsedTime=0;
	        	TotalShareSucceeded=0;
	        	TotalShareFailed=0;
	        	TotalBorrowingSucceeded=0;
	        	TotalBorrowingFailed=0;
		        
		        AverageUsedTime=0;
	        	AverageShareSucceeded=0;
	        	AverageShareFailed=0;
	        	AverageBorrowingSucceeded=0;
	        	AverageBorrowingFailed=0;
	        }
	        
			consumerList.add(SetAverageUsedTime);
			consumerList.add(SetAverageShareSucceeded);
			consumerList.add(SetAverageShareFailed);
			consumerList.add(SetAverageBorrowingSucceeded);
			consumerList.add(SetAverageBorrowingFailed);
			allConsumerList.put(ReadFile.key, consumerList);
			
			SetAverageUsedTime=0;
	        SetAverageShareSucceeded=0;
	        SetAverageShareFailed=0;
	        SetAverageBorrowingSucceeded=0;
	        SetAverageBorrowingFailed=0;
			
		}
		System.out.println(allConsumerList);
		//出力用HashMapに保存

	}
	
	private void addNumber(String decision,String utility){
		if(decision.equals("0")){
//    		System.out.println("一致");
			number0++;
		}else if(decision.equals("1")){
			//財を持ってないとき
			if(utility.equals("0.0")){
				//財を持ってないとき
			}else{
				//財を持ってるとき
				number1++;
			}
		}else if(decision.equals("2")){
			number2++;
		}else if(decision.equals("-2")){
			numberN2++;
		}else if(decision.equals("3")){
			number3++;
		}else if(decision.equals("-3")){
			numberN3++;
		}
	}
	private void resetNumber(){
		number0=0;
		number1=0;
		number2=0;
		numberN2=0;
		number3=0;
		numberN3=0;
	}
	
	
}