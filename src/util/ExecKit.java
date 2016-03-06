package util;

import java.io.File;
//模拟命令行方式，暂时不用
public class ExecKit {
	public static String[][] exec(String[] cmd){
		String projectPath=System.getProperty("user.dir");
		String[][] info= new String[3][];
		StreamDrainer input = null;
		StreamDrainer error=null;
		info[2] = new String[2];
		info[2][0] = cmd[2];
		info[2][1] = "0";
        try {
            Process process = Runtime.getRuntime().exec(cmd,null,new File(projectPath));
            input = new StreamDrainer(process.getInputStream());
            error = new StreamDrainer(process.getErrorStream());
            new Thread(input).start();
            new Thread(error).start();
            Thread.sleep(100);
            process.getOutputStream().close();

            Integer exitValue = process.waitFor();
//          System.out.println("Exit " + exitValue.toString());
            info[2][1] = exitValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Didn't Done.");
            System.exit(1);
        }
        info[0] = input.getOutInfo();
        info[1] = error.getOutInfo();
        return info;
	}
}
