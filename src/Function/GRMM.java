package Function;

import java.util.ArrayList;

import util.RunKit;

public class GRMM {
	private static GRMM instance = null;
	private String train=null;
	private String test=null;
	private String model = null;
	private GRMM(){	
	}
	public static GRMM getInstance(){
		if (instance == null) instance = new GRMM();
		return instance;
	}
	public void setTrain(String s){
		if (s==null || s.equals(""));
		else{
			train = s;
		}
	}
	public void setTest(String s){
		if (s==null || s.equals(""));
		else{
			test = s;
		}
	}
	public void setModel(String s){
		if (s==null || s.equals(""));
		else{
			model = s;
		}
	}
	private void getTrain(ArrayList<String> cmd){
		if (train == null);
		else{
			cmd.add("--training");
			cmd.add(train);
		}
	}
	private void getTest(ArrayList<String> cmd){
		if (test == null);
		else{
			cmd.add("--testing");
			cmd.add(test);
		}
	}
	private void getModel(ArrayList<String> cmd){
		if (model == null);
		else{
			cmd.add("--model-file");
			cmd.add(model);
		}
	}
	public String[] run(){
		ArrayList<String> cmd=new ArrayList<String>();
		getTrain(cmd);
		getTest(cmd);
		getModel(cmd);
		System.out.println(cmd.toString());
		
		String [] args=(String[]) cmd.toArray(new String[cmd.size()]);
		String[] result = null;
		try {
			result=RunKit.run(args,"GRMM");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
//		System.out.println("ddd:"+result.toString());
		return result;
	}
	
}
