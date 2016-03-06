package Function;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import util.RunKit;
import cc.mallet.fst.SimpleTagger;

public class SeqTagging {
	//以下是sequence tagging 中常用的option
	private String train="false";
	private String model_file="nouncrf";
	private String sampleFile="sample";
	private HashMap<String,String> optionMap= new HashMap<String,String>();
	private HashMap<String,String> advOptionMap= new HashMap<String,String>();
	private static SeqTagging instance = null;
	
	public SeqTagging(){
		
	}
	
	
	public static SeqTagging getInstance(){
		if(instance == null){
			instance = new SeqTagging();
		}
		return instance;
	}
	

	public String getSampleFile() {
		return sampleFile;
	}
	public void setSampleFile(String sampleFile) {
		this.sampleFile = sampleFile;
	}
	public HashMap<String,String> getOptionMap() {
		return optionMap;
	}
	public void setOptionMap(HashMap<String,String> optionMap) {
		this.optionMap = optionMap;
	}
	public HashMap<String,String> getAdvOptionMap() {
		return advOptionMap;
	}
	public void setAdvOptionMap(HashMap<String,String> advOptionMap) {
		this.advOptionMap = advOptionMap;
	}



	public String getTrain() {
		return train;
	}


	public void setTrain(String train) {
		this.train = train;
	}



	public String getModel_file() {
		return model_file;
	}


	public void setModel_file(String model_file) {
		this.model_file = model_file;
	}
	
	/**
	 * sequence tagging的运行方法
	 * @return 运行结果
	 */
	public String[] run(){
		ArrayList<String> cmd=new ArrayList<String>();
		cmd.add("--train");
		cmd.add(this.train);
		Iterator iter = optionMap.entrySet().iterator();
		while (iter.hasNext()) {		
			Map.Entry entry = (Map.Entry) iter.next();
			if(entry.getValue()!=null&&!entry.getValue().equals("")){
				cmd.add("--"+(String)entry.getKey());
				cmd.add((String)entry.getValue());
			}				
				
		}
		iter=advOptionMap.entrySet().iterator();
		while (iter.hasNext()) {		
			Map.Entry entry = (Map.Entry) iter.next();
			if(entry.getValue()!=null&&!entry.getValue().equals("")){
				cmd.add("--"+(String)entry.getKey());
				cmd.add((String)entry.getValue());
			}
		}	
		cmd.add("--model-file");
		cmd.add(this.model_file);
		cmd.add(this.sampleFile);
		System.out.println(cmd.toString());
		
		String [] args=(String[]) cmd.toArray(new String[cmd.size()]);

		String[] result=RunKit.run(args,"Sequence Tagging");
		return result;
	}
}
