package backup;

import util.ExecKit;

public class ImportSvmFile {
	private String InputFile = null;
	private String OutputFile = "tmp.mallet";
	private static ImportSvmFile instance = null;
	private String[] options=null;
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	private ImportSvmFile() {
		// TODO Auto-generated constructor stub
	}
	public void setImportFile(String importFile){
		InputFile = "\""+ importFile +"\"";
	}
	public void setOuputFile(String fileName){
		OutputFile = fileName;
	}
	public String getOutputFile(){
		return OutputFile;
	}
	public static ImportSvmFile getInstance(){
		if(instance == null){
			instance = new ImportSvmFile();
		}
		return instance;
	}
	public String[][] run(){
		String cmd = "mallet import-svmlight --input " + InputFile + " --output " + OutputFile;
		String[] exec_cmd=new String[] { "cmd.exe", "/C", cmd };
		System.out.println(cmd);
		String[][] result=ExecKit.exec(exec_cmd);
		InputFile = null;
		return result;
	}
}
