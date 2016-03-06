package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


//暂时不用
public class StreamDrainer implements Runnable {
    private InputStream ins;
    private String[] outInfo;
    private List<String> list; 
    public StreamDrainer(InputStream ins) {
        this.ins = ins;
        list=new ArrayList<String>();
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(ins));
            String line = null;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
//            outInfo=list.toArray(new String[1]);
            outInfo=new String[list.size()];
            list.toArray(outInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public String[] getOutInfo() {
		return outInfo;
	}

	public void setOutInfo(String[] outInfo) {
		this.outInfo = outInfo;
	}

}