package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OptionHandler {
	private static OptionHandler optH = null;
	private String action=null;
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	private HashMap<String,String> optionList= new HashMap<String,String>();
	private HashMap<String,String> lastOptions= new HashMap<String,String>();
	public HashMap<String, String> getLastOptions() {
		return lastOptions;
	}

	public void setLastOptions(HashMap<String, String> lastOptions) {
		this.lastOptions = lastOptions;
	}

	private OptionHandler(String action) {
		this.action=action;
	}	
	
	private OptionHandler() {

	}	
	
	public static OptionHandler getIntance(String action){
		if (optH == null){
			optH = new OptionHandler(action);
		}
		return optH;
	}
	
	public static OptionHandler getIntance(){
		if (optH == null){
			optH = new OptionHandler();
		}
		return optH;
	}


	/**
	 * 从文件中读取option
	 */
	public void readOptionsFile(){
		File file = null;
		System.out.println("read options:"+this.action);
		String filePath="options/"+this.action+"_options";
		file=new File(filePath);
		
		BufferedReader reader = null;
		HashMap<String,String> optionList= new HashMap<String,String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	String [] kv=tempString.split(" ");
            	optionList.put(kv[0], kv[1]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        this.optionList=optionList;
	}

	
	/**
	 * 从文件中读取上次记忆的option
	 */
	public void readLastOptions(){
		File file = null;
		System.out.println("last options:"+this.action);
		String filePath="lastOptions/"+this.action+"_options";
		file=new File(filePath);
		
		BufferedReader reader = null;
		HashMap<String,String> lastOptions= new HashMap<String,String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	String [] kv=tempString.split(" ");
            	if(kv.length<2||kv[1]==null)
            		lastOptions.put(kv[0], null);
            	else{
            		lastOptions.put(kv[0], kv[1]);
//            		System.out.println(kv[0]+" "+kv[1]);
            	}
            		
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
//        System.out.println(lastOptions.toString());
        this.lastOptions=lastOptions;
	}

	

	/**
	 * 将用户本次的option存储到本地
	 * @param lastOptions
	 */
	public void writeLastOptions(HashMap<String,String> lastOptions){
		File file = null;
		System.out.println("last options:"+this.action);
		String filePath="lastOptions/"+this.action+"_options";
		file=new File(filePath);
		
		OutputStreamWriter writer = null;
	
        try {
        	writer = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
            String tempString = null;
            Iterator iter = lastOptions.entrySet().iterator();
    		String key=null;
    		String val=null;
    		int i=1;
    		while (iter.hasNext()) {		
    			Map.Entry entry = (Map.Entry) iter.next();
    			if(entry.getValue()==null)
    				writer.write((String)entry.getKey());
    			else{
    				writer.write((String)entry.getKey());
    				writer.write(" ");
    				writer.write((String)entry.getValue());
    			}
    			writer.write("\r\n");
    		}
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                	writer.close();
                } catch (IOException e1) {
                }
            }
        }
	}
	
	public HashMap<String,String> getOptionList() {
		return optionList;
	}

	public void setOptionList(HashMap<String,String> optionList) {
		this.optionList = optionList;
	}

}
