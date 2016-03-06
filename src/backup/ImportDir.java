package backup;

import java.util.HashMap;

import util.ExecKit;

public class ImportDir {
	private String InputDir = null;
	private String OutputFile = "tmp.mallet";
	private static ImportDir instance = null;
	private HashMap<String,String> optionMap = new HashMap<String,String>();
	private ImportDir() {
		// TODO Auto-generated constructor stub
	}
	public void setImportPath(String importPath){
		InputDir = "\""+importPath + "\"";
	}
	public void setOutputFile(String fileName){
		OutputFile = fileName;
	}
	public String getOutputFile(){
		return OutputFile;
	}
	public static ImportDir getInstance(){
		if(instance == null){
			instance = new ImportDir();
		}
		return instance;
	}
	public String[][] run(){
		String cmd = "mallet import-dir --input " + InputDir + " --output " + OutputFile;
		String[] exec_cmd = new String[] { "cmd.exe", "/C", cmd };
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
