package util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Function.OptimizerExample;
import cc.mallet.fst.SimpleTagger;
import cc.mallet.optimize.LimitedMemoryBFGS;
import cc.mallet.optimize.Optimizer;
import cc.mallet.topics.tui.InferTopics;
import cc.mallet.topics.tui.TopicTrainer;
import cc.mallet.util.MalletLogger;
import cc.mallet.classify.tui.Text2Vectors;
import cc.mallet.classify.tui.Csv2Classify;
import cc.mallet.classify.tui.Csv2Vectors;
import cc.mallet.classify.tui.SvmLight2Classify;
import cc.mallet.classify.tui.SvmLight2Vectors;
import cc.mallet.classify.tui.Text2Classify;
import cc.mallet.classify.tui.Vectors2Classify;



//直接调用接口方式



public class RunKit {
	
	//将程序的log重定向到另外一个流  ,即截取控制台输出
	public static ByteArrayOutputStream bos = new ByteArrayOutputStream();   //out暂时不需要
	public static ByteArrayOutputStream bosErr = new ByteArrayOutputStream(); 
	
	
	static {
        System.setOut(new PrintStream(bos));         //设置新的out   
        System.setErr(new PrintStream(bosErr));         //设置新的error
	}
	
	
	/**
	 * 调用mallet方法，执行命令
	 * @param args
	 * @param function
	 * @return 运行结果
	 */
	public static String[] run(String[] args,String function){
		String cmd=null;
		//前两个存储out和error信息，第三个存储命令,第四执行情况
		String[] info= new String[4];
		
		for(String arg:args)
			cmd+=arg+" ";
		info[2] = cmd;
		info[3] = "0";
		String msg = "";
		
//		PrintStream oldPrintStream = System.out;        //将原来的System.out交给printStream 对象保存
//		PrintStream oldErrorStream= System.err;
		
		try {
	        
	        //有些logger的信息不能直接用System.err获取到，需要添加handler
//			ConsoleHandler handler = new ConsoleHandler();
//			handler.setFormatter(new SimpleFormatter());
	        if(function.equals("Sequence Tagging")){
	        	//获取类所在logger
//	        	Logger log=MalletLogger.getLogger(SimpleTagger.class.getName());
//				log.addHandler(handler);
	        	SimpleTagger.main(args);
	        }
	        	
	        else if(function.equals("import_dir")){
				Text2Vectors.main(args);

	        }
	        	
	        else if(function.equals("import_file")){
				Csv2Vectors.main(args);
	        }
	        	
	        else if(function.equals("import_svm")){
	        	SvmLight2Vectors.main(args);
	        }
	        
	        else if(function.equals("trainer_classify")){
	        	Vectors2Classify.main(args);
	        }
	        else if(function.equals("classifiyFile")){
	        	Csv2Classify.main(args);
	        }
	        else if(function.equals("classifiyDirectory")){
	        	Text2Classify.main(args);
	        }
	        else if(function.equals("classifiySvmlight")){
	        	SvmLight2Classify.main(args);
	        }	
	        else if(function.equals("train_topics")){
	        	TopicTrainer.main(args);
	        }		        
	        else if(function.equals("infer_topics")){
	        	InferTopics.main(args);
	        }
	        else if(function.equals("Optimization")){
	        	OptimizerExample optimizable = new OptimizerExample(args);
	            Optimizer optimizer = new LimitedMemoryBFGS(optimizable);
	            boolean converged = false;

	            try {
	                converged = optimizer.optimize();
	            } catch (IllegalArgumentException e) {
	                        
	            }
	            msg = "Parameter:\n("+optimizable.getParameter(0)+","+optimizable.getParameter(1)+")\n";
	        }
	        
	        
	        info[0]=msg+bos.toString(); //暂时为空
	        info[1]=bosErr.toString();
	        bosErr.reset();
	        bos.reset();

		} catch (Exception e) {
			// TODO Auto-generated catch block 
			info[3]="1";
			info[1]=e.toString();
			e.printStackTrace();
		}
		
		return info;
		
	}
}
