package com.daliu.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String seq = String.format("%06d", 1);
			
			FileWriter fw = new FileWriter("file.xml");
			BufferedWriter bw=new BufferedWriter(fw);
			
			FileReader fr = new FileReader(
					"E:/dpap/workspaces/crm2018/CRM20180420/crm-customer-data/src/main/java/com/deppon/crm/module/custdata/shared/domain/TCustCustbasedataBatisMapper.xml");
			FileReader fr1 = new FileReader(
					 "E:/dpap/workspaces/crm2018/CRM20180420/crm-customer-data/src/main/java/com/deppon/crm/module/custdata/shared/domain/TCustCustbasedataCzlMapper.xml");
			BufferedReader bf = new BufferedReader(fr);
			BufferedReader bf1 = new BufferedReader(fr1);

			String content = "";
			String content1 = "";
			StringBuilder sb = new StringBuilder();

			int i = 0;
			while (content != null) {
				i++;
				System.out.println(i);
				if(i==915){
					System.out.println(i);
				}
				
				content = bf.readLine();
				content1 = bf1.readLine();
				
				if(content == null){
					break;
				}
				
				String columnName = "";
				String columnName1 = "";
				if(content.indexOf("column") != -1 && content.indexOf("jdbcType") != -1){
					columnName = content.substring(content.indexOf("column")+7, content.indexOf("jdbcType"));
					columnName1 = content1.substring(content1.indexOf("column")+7, content1.indexOf("jdbcType"));
					content = content.replace(columnName, columnName1);
				}else if(content1.trim().contains(content.trim().replace("_", ""))){
					content=content1;
				}else{
					String[] arr = content.split(" = ");
					String[] arr1 = content1.split(" = ");
					for(int j=0;j<arr.length;j++){
						if(arr1[j].trim().contains(arr[j].trim().replace("_", ""))){
							content = content.replace(arr[j].trim(), arr1[j].trim());
						}
					}
				}
				
				bw.write(content);
				bw.newLine();
				sb.append(content.trim());
			}

			
			fr.close();
			fr1.close();
			bf.close();
			bf1.close();
			bw.close();
			fw.close();
			
			System.out.println(sb.toString());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static boolean isAcronym(String word) {
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!Character.isLowerCase(c)) {
				return false;
			}
		}
		return true;
	}

}
