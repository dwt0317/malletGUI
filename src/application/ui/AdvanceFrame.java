package application.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import Function.trainerClassifier;
import util.OptionHandler;
import util.WrapLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdvanceFrame extends JFrame {

	private JPanel contentPane;
	private JPanel optionPanel;
	private JScrollPane scrollPane;
	
	//optionsMap只储存了有值的option，用于传递给main frame 进行进一步操作，
	//lastOptions 储存了该action的所有option，用于记忆用户每次的选择
	private HashMap<String,String> optionsMap = new HashMap<String,String>();
	private HashMap<String,String> lastOptions = new HashMap<String,String>();
	private String action="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdvanceFrame frame = new AdvanceFrame("seq_tagging");
					//setVisible之后lastOptions就被清空了。。
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdvanceFrame(String action) {
		setResizable(false);
		setTitle("Advanced Options");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		optionPanel = new JPanel();
		
		scrollPane = new JScrollPane(optionPanel);
//		optionPanel.setLayout(null);
		scrollPane.setBounds(0, 14, 518, 425);
		WrapLayout wl_optionPanel = new WrapLayout();
		wl_optionPanel.setVgap(10);
		wl_optionPanel.setAlignment(FlowLayout.LEFT);
		optionPanel.setLayout(wl_optionPanel);

		contentPane.add(scrollPane);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				confirmClicked();
			}
		});
		btnConfirm.setFont(new Font("Cambria", Font.PLAIN, 16));
		btnConfirm.setBounds(268, 443, 113, 27);
		contentPane.add(btnConfirm);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clearOptions();
			}
		});
		btnClear.setFont(new Font("Cambria", Font.PLAIN, 16));
		btnClear.setBounds(408, 443, 100, 27);
		contentPane.add(btnClear);
		
		
		//从文件中读取option
		this.action=action;
		OptionHandler optH=OptionHandler.getIntance(action);
		optH.setAction(action);
		optH.readOptionsFile();	
		optH.readLastOptions();
		createOptionComp(optH.getOptionList());
		renderOptionValue(optH.getLastOptions());
		
		//scroll panel的高度太高会导致格式混乱
		int adjustHeight = 30 * optH.getOptionList().size();   
		if(adjustHeight<500){
			scrollPane.setBounds(0, 14, 518, adjustHeight);
			btnConfirm.setBounds(268, adjustHeight+20, 113, 27);
			btnClear.setBounds(408, adjustHeight+20, 100, 27);
			setBounds(100, 100, 524, adjustHeight+90);
		}
	}
	
	
	
	
	/**
	 * 根据optionMap来生成面板，integer,decimal 为填数字，boolean为填t/f, text为填文本, filename 为选择文件
	 * @param optionList
	 */
	public void createOptionComp(HashMap<String,String> optionList){
		Iterator iter = optionList.entrySet().iterator();
		String key=null;
		String val=null;
		
		String[] tf_combo={"false","true"};
		
		int x=20,y=5;
		int lw=210,lh=30;
		int comboW=80,comboH=25;
		int spinW=80,spinH=25;
		int textW=200,textH=25;
		
		while (iter.hasNext()) {		
			Map.Entry entry = (Map.Entry) iter.next();
			key = (String) entry.getKey();
			val = (String) entry.getValue();
//			System.out.println(key+" "+val);
			
			//create label of option
			JTextField optionLabel = new JTextField();
			optionLabel.setBorder(BorderFactory.createEmptyBorder(y,x,0,20));
			optionLabel.setPreferredSize(new Dimension(lw,lh));
			optionLabel.setText(key);
			optionLabel.setFont(new Font("Cambria", Font.PLAIN, 16));
//			optionLabel.setBorder(null);
			optionLabel.setBackground(SystemColor.menu);
			optionPanel.add(optionLabel);
			
			if(val.equals("boolean")){
				JComboBox comboBox = new JComboBox(tf_combo);
				comboBox.setFont(new Font("Cambria", Font.PLAIN, 16));
				//setBounds 不好使，必须用setPreferredSize
				comboBox.setPreferredSize(new Dimension(comboW,comboH));
				comboBox.setSelectedItem(null);
				optionPanel.add(comboBox);
			}else if(val.equals("integer")||val.equals("decimal")){	
				JSpinner spinner=null;
				if(val.equals("integer")){
					SpinnerNumberModel snmInte = new SpinnerNumberModel(0, 0, 1000, 1);				
					spinner = new JSpinner(snmInte);
				}
					
				else{
					SpinnerNumberModel snmdecimal = new SpinnerNumberModel(0.0, 0.0, 1.0, 0.1);
					spinner = new JSpinner(snmdecimal);
				}
					
				spinner.setPreferredSize(new Dimension(spinW,spinH));
				spinner.setFont(new Font("Cambria", Font.PLAIN, 16));
				optionPanel.add(spinner);
			}else if(val.equals("filename")){
				final JTextField filePath = new JTextField();
				JButton btnBrowse = new JButton("Browse");
				btnBrowse.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						String fileBeRead = getPath();
						filePath.setText(fileBeRead+"\n");
					}
				});
				filePath.setColumns(15);
				filePath.setFont(new Font("Cambria", Font.PLAIN, 15));
				optionPanel.add(filePath);
				optionPanel.add(btnBrowse);
			}
			else{
				JTextField jtext = new JTextField();
				jtext.setPreferredSize(new Dimension(textW,textH));
				jtext.setFont(new Font("Cambria", Font.PLAIN, 16));
				optionPanel.add(jtext);
			}
	    }
	}
	
	
	/**
	 * 读取上次记忆的option的值并填充
	 * @param lastOptions
	 */
	public void renderOptionValue(HashMap<String,String> lastOptions){
		Iterator iter = lastOptions.entrySet().iterator();
		String key=null;
		String val=null;
		int i=1;
		while (iter.hasNext()) {		
			Map.Entry entry = (Map.Entry) iter.next();
			if(entry.getValue()==null){
				System.out.println(entry.getKey());
				if(i<optionPanel.getComponentCount()-1&&optionPanel.getComponent(i+1) instanceof JButton)
					i+=1;
				i+=2;
				continue;
			}				
			key = (String) entry.getKey();
			val = (String) entry.getValue();
			
			Object obj = optionPanel.getComponent(i);
			if (obj instanceof JComboBox) {
				JComboBox combo = (JComboBox) obj;
				combo.setSelectedItem(val);

			}else if(obj instanceof JSpinner){			
				JSpinner spinner =(JSpinner)obj;
				SpinnerNumberModel snm = (SpinnerNumberModel) spinner.getModel();
				snm.setValue(Double.valueOf(val));
			}else if(obj instanceof JButton){
				continue;
			}else{
				JTextField jtext =(JTextField)obj;
				jtext.setText(val);
			}
			if(i<optionPanel.getComponentCount()-1&&optionPanel.getComponent(i+1) instanceof JButton)
				i+=1;
			i+=2;
		}

		
	}
	

	/**
	 * 两项操作 1.获取有值的option 2.记忆用户选择的option
	 */
	public void getOptions(){
		int count = optionPanel.getComponentCount();
		for (int i = 1; i < count; i+=2) {
			Object obj = optionPanel.getComponent(i);
			//获取选项标签
			JTextField optionLabel=(JTextField)optionPanel.getComponent(i-1);
			//判断选项类型
			if (obj instanceof JComboBox) {
				JComboBox combo = (JComboBox) obj;
				if(combo.getSelectedItem()!=null)	{
					if(!combo.getSelectedItem().toString().equals("")){
						optionsMap.put(optionLabel.getText(),combo.getSelectedItem().toString());
						lastOptions.put(optionLabel.getText(),combo.getSelectedItem().toString());
					}
				}else
					lastOptions.put(optionLabel.getText(),null);

				

			}else if(obj instanceof JSpinner){			
				JSpinner spinner =(JSpinner)obj;
				SpinnerNumberModel snm = (SpinnerNumberModel) spinner.getModel();
				if(snm.getNumber().doubleValue()!=0.0){
					optionsMap.put(optionLabel.getText(), snm.getNumber().toString());
					lastOptions.put(optionLabel.getText(), snm.getNumber().toString());
				}else
					lastOptions.put(optionLabel.getText(), null);
					
			}else if(obj instanceof JButton)
				continue;			
			else{
				JTextField jtext =(JTextField)obj;
				if(jtext.getText()!=null&&!jtext.getText().equals("")){
					System.out.println(jtext.getText());
					optionsMap.put(optionLabel.getText(),jtext.getText());	
					lastOptions.put(optionLabel.getText(),jtext.getText());
				}else
					lastOptions.put(optionLabel.getText(),null);
			}
			if(i!=count-1&&optionPanel.getComponent(i+1) instanceof JButton)
				i+=1;
		}
	}
	
	/**
	 * 获取文件（夹）路径
	 * @return 文件（夹）path
	 */
	private String getPath() {
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Select path");
        fileChooser.setCurrentDirectory(new File("."));
        
        int result = fileChooser.showDialog(null, "OK");
    	if (result == JFileChooser.FILES_ONLY) {
        	String path=fileChooser.getSelectedFile().getPath();
        	return path;     		
        }else{
        	JOptionPane.showMessageDialog(null, "Please select a file!", "Alert", JOptionPane.ERROR_MESSAGE);
    		return null;
        }  
    }
	
	
	
	/**
	 * 点击confirm按钮时调用此方法
	 */
	private void confirmClicked(){
		getOptions();
		System.out.println(optionsMap.toString());
		OptionHandler optH=OptionHandler.getIntance();
		
		//
		optH.writeLastOptions(lastOptions);
		this.setVisible(false);
	}

	/**
	 * 清空options列表
	 */
	public void clearOptions(){
		int count = optionPanel.getComponentCount();
		for (int i = 1; i < count; i+=2) {
			//	获取选项标签
			JTextField optionLabel=(JTextField)optionPanel.getComponent(i-1);
			lastOptions.put(optionLabel.getText(), null);
			Object obj = optionPanel.getComponent(i);
			//	判断选项类型		
			if (obj instanceof JComboBox) {
				JComboBox combo = (JComboBox) obj;
				combo.setSelectedItem(null);

			}else if(obj instanceof JSpinner){			
				JSpinner spinner =(JSpinner)obj;
				SpinnerNumberModel snm = (SpinnerNumberModel) spinner.getModel();
				snm.setValue(0.0);
			}else if(obj instanceof JButton){
				continue;
			}
			else{
				JTextField jtext =(JTextField)obj;
				jtext.setText(null);
			}
		
			if(i!=count-1&&optionPanel.getComponent(i+1) instanceof JButton)
				i+=1;
		}
		OptionHandler optH=OptionHandler.getIntance();
		optH.writeLastOptions(lastOptions);
	}
	
	/**
	 * option map getter
	 */
	public HashMap<String,String> getOptionMap() {
		return optionsMap;
	}

	public void setOptionMap(HashMap<String,String> optionMap) {
		this.optionsMap = optionMap;
	}
	
	public String getAction() {
		return action;
	}

}
