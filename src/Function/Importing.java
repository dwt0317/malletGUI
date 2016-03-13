package Function;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import util.ExecKit;
import util.RunKit;

public class Importing {
	//一个是基本option,一个是高级option,基本option是在主面板上的，高级是在新框里的
	private HashMap<String,String> optionMap;
	private HashMap<String,String> advOptionMap;   //高级option
	private String action=null;
	private String inputPath=null;
	private String outputPath=null;
	private static Importing instance = null;
	
	
	public HashMap<String, String> getOptionMap() {
		return optionMap;
	}

	public void setOptionMap(HashMap<String, String> optionMap) {
		this.optionMap = optionMap;
	}


	public Importing(HashMap<String,String> optionMap){
		this.optionMap=optionMap;
	}
	
	private Importing(){
		this.optionMap = new HashMap<String,String>();
		this.advOptionMap = new HashMap<String,String>(); 
	}
	
	/**
	 * 是为了不重复new 实例，如果已有实例就用以前的
	 * @return
	 */
	public static Importing getInstance(){
		if(instance == null){
			instance = new Importing();
		}
		return instance;
	}
	
	
	public String[] run(){
		String[] result=null;
		ArrayList<String> cmd=new ArrayList<String>();
		cmd.add("--input");
		if(action.equals("import_dir")){
			String[] directories= getDirectory(this.inputPath);
			for(String dir:directories){
				cmd.add(dir);
			}
		}else
			cmd.add(this.inputPath);

		Iterator iter = optionMap.entrySet().iterator();
		while (iter.hasNext()) {		
			Map.Entry entry = (Map.Entry) iter.next();
			if(entry.getValue()!=null&&!entry.getValue().equals("")){
				cmd.add("--"+(String)entry.getKey());
				cmd.add((String)entry.getValue());
			}				
				
		}
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
		
		cmd.add("--output");
		cmd.add(this.outputPath);
		System.out.println(cmd.toString());
	
		String [] args=(String[]) cmd.toArray(new String[cmd.size()]);
		result=RunKit.run(args,action);
		return result;
	}

	
	/**
	 * 将文件夹下的所有目录string []的形式打包
	 * @param path
	 * @return
	 */
	public String[] getDirectory(String path){
		ArrayList<String> dirs=new ArrayList<String>();
		File dir = new File(path);
		File[] tempList = dir.listFiles();
		for(File file:tempList){
			dirs.add(file.getAbsolutePath());
		}
		return (String[]) dirs.toArray(new String[dirs.size()]);

				
	}
	
	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
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
