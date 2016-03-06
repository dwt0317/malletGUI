package backup;

import java.util.HashMap;

import util.ExecKit;

public class ImportFile {
	private String InputFile = null;
	private String OutputFile = "tmp.mallet";
	private static ImportFile instance = null;
	private HashMap<String,String> optionMap = new HashMap<String,String>();
	private ImportFile() {
		// TODO Auto-generated constructor stub
	}
	public void setImportPath(String importFile){
		InputFile = "\""+ importFile +"\"";
	}
	public void setOuputFile(String fileName){
		OutputFile = fileName;
	}
	public String getOutputFile(){
		return OutputFile;
	}
	public static ImportFile getInstance(){
		if(instance == null){
			instance = new ImportFile();
		}
		return instance;
	}
	public String[][] run(){
		String cmd = "mallet import-file --input " + InputFile + " --output " + OutputFile;
		String[] exec_cmd=new String[] { "cmd.exe", "/C", cmd };
		System.out.println(cmd);
		String[][] result=ExecKit.exec(exec_cmd);
		return result;
	}
	public HashMap<String,String> getOptionMap() {
		return optionMap;
	}
	public void setOptionMap(HashMap<String,String> optionMap) {
		this.optionMap = optionMap;
	}
	
	
}
