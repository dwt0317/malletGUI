package Function;

import util.RunKit;
import cc.mallet.optimize.LimitedMemoryBFGS;
import cc.mallet.optimize.Optimizer;


public class Optimizable {
	private static Optimizable instance = null;
	private Double[] params;
	private Optimizable(){
		params = new Double[2];
		params[0] = 0.0;
		params[1] = 0.0;
	}
	public static Optimizable getInstance(){
		if (instance == null) instance = new Optimizable();
		return instance;
	}
	public void setParam(Double x, int i){
		params[i] = x;
	}
	
	public String[] run(){
		String[] result =null;
		String[] s = new String[2];
        s[0] = params[0].toString();
        s[1] = params[1].toString();
		result=RunKit.run(s,"Optimization");
        
        return result;
        
	}

}
