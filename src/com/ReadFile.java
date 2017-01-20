package com;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;

public class ReadFile{
	static double alpha=0;
	static double beta=0;
	static int ShareOrNot=0;
	static String dataStoredFolderName = "./simulation result";
	static File outputPath = new File(dataStoredFolderName);
	static public List<File> fileList = new ArrayList<File>(); // ファイル名をリストとして保存する
	static String key = "";
	
	public ReadFile(){
		
	}
	
	
	public static String NextFolder(){
		String NextFolder=dataStoredFolderName;
		NextParameter();
		makeKey();
		NextFolder=NextFolder+"/alpha"+String.valueOf(alpha)+"beta"+String.valueOf(beta)+"ShareOrNot"+String.valueOf(ShareOrNot);
		return  NextFolder;
	}
	
	public static void NextParameter(){
		if(ShareOrNot==1){
			if(beta==1.0){
				alpha=alpha+0.5;
				beta=0.25;
				ShareOrNot=0;
			}else{
				beta=beta+0.25;
				ShareOrNot=0;
			}
		}else{
			if(beta==0){
				if(alpha==0){
					alpha=0.5;
					beta=0.25;
				}
			}else{
				ShareOrNot=1;	
			}
		}
	}
	
	public static boolean EndParameter(){
		if(alpha==2.5 && beta == 1.0 && ShareOrNot==1 ){
			alpha=0;
			beta=0;
			ShareOrNot=0;
			return true;
		}else{
			return false;
		}
	}
	public static void makeKey(){
		key="alpha"+alpha+"beta"+beta+"ShareOrNot"+ShareOrNot;
	}
	
	
}