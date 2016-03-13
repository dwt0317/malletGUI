package Function;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import util.RunKit;

public class TopicModeling {
	private HashMap<String,String> advOptionMap;   //全部当作高级option
	private String action=null;
	private String inputPath=null;
	private String outputPath=null;
	private static TopicModeling instance = null;
	
	
	private TopicModeling(){
		this.advOptionMap = new HashMap<String,String>(); 
	}
	
	/**
	 * 是为了不重复new 实例，如果已有实例就用以前的
	 * @return
	 */
	public static TopicModeling getInstance(){
		if(instance == null){
			instance = new TopicModeling();
		}
		return instance;
	}
	
	
	public String[] run(){
		String[] result=null;
		ArrayList<String> cmd=new ArrayList<String>();
		cmd.add("--input");
		cmd.add(this.inputPath);
		Iterator iter;
		if(!advOptionMap.isEmpty()){
			iter=advOptionMap.entrySet().iterator();
			while (iter.hasNext()) {		
				Map.Entry entry = (Map.Entry) iter.next();
				if(entry.getValue()!=null&&!entry.getValue().equals("")){
					cmd.add("--"+(String)entry.getKey());
					cmd.add((String)entry.getValue());
				}
			}	
		}
	
		String [] args=(String[]) cmd.toArray(new String[cmd.size()]);
		result=RunKit.run(args,action);
		return result;
	}


	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public HashMap<String,String> getAdvOptionMap() {
		return advOptionMap;
	}

	public void setAdvOptionMap(HashMap<String,String> advOptionMap) {
		this.advOptionMap = advOptionMap;
	}
	
}