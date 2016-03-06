package Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import util.ExecKit;
import util.RunKit;

public class trainerClassifier {
	private static trainerClassifier tc = null;
	private String trainer = null;
	private String InputFile = null;
	private String cross = null;
	private String OutputFile = "tmp.classifier";
	private String Classifer = null;
	private String portion = null;
	private String trials = null;
	private HashMap<String,String> optionMap= new HashMap<String,String>();
	private HashMap<String,String> advOptionMap= new HashMap<String,String>();
	
	private String classify_type = null;
	private String classify_path = null;
	private trainerClassifier() {
		// TODO Auto-generated constructor stub
	}
	public static trainerClassifier getInstance(){
		if (tc == null){
			tc = new trainerClassifier();
		}
		return tc;
	}
	public void setClassify_type(String string){
		if (string == null || string.equals(""));
		else{
			classify_type = string;
		}
	}
	public void setClassPath(String string){
		if (string == null || string.equals(""));
		else{
			classify_path = string;
		}
	}
	//	--input
	public void setImportFile(String importFile){
		InputFile = importFile;
	}
	
	//	--trainer
	public void setTrainer(String s){
		trainer = s;
	}
	private void getTrainer(ArrayList<String> cmd){
		if (trainer == null);
		else{
			cmd.add("--trainer");
			cmd.add(trainer);
		}
	}
	
	//	--output-classifier
	public void setOuputFile(String fileName){
		OutputFile = fileName;
	}
	public String getOutputFile(){
		return " --output-classifier "+OutputFile;
	}
	
	//	--training-portion
	public void setPortition(Double p){
		if (p == null) portion = null;
		else portion = p.toString();
	}
	private void getPortition(ArrayList<String> cmd){
		if (portion == null);
		else{
			cmd.add("--training-portion");
			cmd.add(portion);
		}
	}
	
	//	--num-trials
	public void setTrials(Integer p){
		if (p == null) trials = null;
		else trials = p.toString();
	}
	
	public void setCross(Integer p){
		if (p == null) cross = null;
		else cross = p.toString();
	}
	private void getCross(ArrayList<String> cmd){
		if (cross == null);
		else{
			cmd.add("--cross-validation");
			cmd.add(cross);
		}
	}

	private void getTrials(ArrayList<String> cmd){
		if (trials == null);
		else{
			cmd.add("--num-trials");
			cmd.add(trials);
		}
	}
	
	public String[] run(){
		ArrayList<String> cmd=new ArrayList<String>();
		cmd.add("--input");
		cmd.add(InputFile);
		getTrainer(cmd);
		cmd.add("--output-classifier");
		cmd.add(OutputFile);
		getPortition(cmd);
		getTrials(cmd);
		getCross(cmd);
		System.out.println(cmd.toString());
		
		String [] args=(String[]) cmd.toArray(new String[cmd.size()]);
		String[] result=RunKit.run(args,"trainer_classify");
		System.out.println("ddd:"+result.toString());
		return result;
	}
	public void setClassifier(String classifer){
		Classifer = classifer;
	}
	public String[] classify(){
		ArrayList<String> cmd=new ArrayList<String>();
		cmd.add("--input");
		cmd.add(classify_path);
		cmd.add("--output");
		cmd.add("-");
		cmd.add("--classifier");
		cmd.add(Classifer);
		String [] args=(String[]) cmd.toArray(new String[cmd.size()]);

		String[] result=RunKit.run(args,"classifiy"+classify_type);
		return result;
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
	
}
