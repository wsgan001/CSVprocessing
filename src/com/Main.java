package com;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException {
    	FirmProfit firmProfit = new FirmProfit();
    	FirmQuality firmQuality = new FirmQuality();
    	ConsumerUtility consumerUtility = new ConsumerUtility();
    	ConsumerDecision consumerDecision = new ConsumerDecision();
    	firmProfit.FirmProfitMain();
    	firmQuality.FirmQualityMain();
    	consumerUtility.ConsumerUtilityMain();
    	consumerDecision.ConsumerDecisionMain();
    	OutputCSV.writeMap(0);
    	OutputCSV.writeMap(1);
    	System.out.println("finished");
    	
    	
        
    }

}