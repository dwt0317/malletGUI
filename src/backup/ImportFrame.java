package backup;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTable;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JToggleButton;
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.awt.Component;

import javax.swing.JRadioButton;








import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import util.*;

public class ImportFrame extends JFrame {

	private JPanel contentPane;
	private String[] tableHeader=new String[]{"Directory"};
	private String[] opTableHeader = new String[]{"Options","Value"};
	private JFileChooser fileChooser = new JFileChooser();
	private JTextField txInput;
	private JTextField txtOutput;
	private JTextField txtOutPath;
	private JTextField txtOptions;
	private JTable optionTable;
	private JScrollPane scrollPane;
	private JRadioButton rdbtnFile;
	private JRadioButton rdbtnDirectory;
	private JButton runBtn;
	private JTextField txtInPath;
	private JTextArea outputArea;
	private JPanel outputTab;
	private JButton clrBtn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportFrame frame = new ImportFrame();
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
	public ImportFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				readOptionsFile();
			}
		});
		setTitle("Mallet");
		setFont(new Font("Cambria", Font.PLAIN, 18));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 638);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabMenu = new JTabbedPane(JTabbedPane.TOP);
		tabMenu.setFont(new Font("Cambria", Font.PLAIN, 20));
		tabMenu.setBounds(53, 53, 804, 516);
		contentPane.add(tabMenu);
		
		JPanel importTab = new JPanel();
		tabMenu.addTab("Importing Data", null, importTab, null);
		
		
		
//		Import directory btn
		JButton selectBtn = new JButton("Select");
		selectBtn.setBackground(UIManager.getColor("Button.background"));
		selectBtn.setBounds(642, 34, 130, 36);
		selectBtn.setFont(new Font("Cambria", Font.PLAIN, 18));
		selectBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		        String fileBeRead = getSelectedResult();
		        txtInPath.setText(fileBeRead);
			}
		});
		importTab.setLayout(null);
		importTab.add(selectBtn);
		        
				txInput = new JTextField();
				txInput.setBounds(91, 38, 118, 28);
				txInput.setBorder(null);
				txInput.setFont(new Font("Cambria", Font.PLAIN, 18));
				txInput.setEditable(false);
				txInput.setBackground(UIManager.getColor("Button.background"));
				txInput.setText("Input path:");
				importTab.add(txInput);
				txInput.setColumns(10);
				
				txtOutput = new JTextField();
				txtOutput.setBounds(91, 95, 118, 28);
				txtOutput.setText("Output path:");
				txtOutput.setFont(new Font("Cambria", Font.PLAIN, 18));
				txtOutput.setEditable(false);
				txtOutput.setColumns(10);
				txtOutput.setBorder(null);
				txtOutput.setBackground(SystemColor.menu);
				importTab.add(txtOutput);
				
				txtOutPath = new JTextField();
				txtOutPath.setFont(new Font("微软雅黑", Font.PLAIN, 17));
				txtOutPath.setToolTipText("Default path is '/mallet_files' if not set");
				txtOutPath.setBorder(new LineBorder(new Color(171, 173, 179)));
				txtOutPath.setBounds(212, 91, 415, 36);
				importTab.add(txtOutPath);
				txtOutPath.setColumns(10);
				
				txtOptions = new JTextField();
				txtOptions.setBounds(91, 166, 79, 28);
				txtOptions.setText("Options");
				txtOptions.setFont(new Font("Cambria", Font.PLAIN, 18));
				txtOptions.setEditable(false);
				txtOptions.setColumns(10);
				txtOptions.setBorder(null);
				txtOptions.setBackground(SystemColor.menu);
				importTab.add(txtOptions);
				
				scrollPane = new JScrollPane();
				scrollPane.setBackground(Color.WHITE);
				scrollPane.setBounds(91, 208, 387, 242);
				importTab.add(scrollPane);
				
				optionTable = new JTable(){};
				optionTable.setRowMargin(4);
				optionTable.setRowHeight(25);
				optionTable.setFont(new Font("Cambria", Font.PLAIN, 18));
				scrollPane.setViewportView(optionTable);
				optionTable.setBorder(new LineBorder(Color.GRAY));
				
				rdbtnDirectory = new JRadioButton("Directory",true);
				rdbtnDirectory.setFont(new Font("Cambria", Font.PLAIN, 18));
				rdbtnDirectory.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						rdbtnFile.setSelected(false);
						readOptionsFile();
					}
				});
				rdbtnDirectory.setBounds(517, 228, 110, 28);
				importTab.add(rdbtnDirectory);
				
				rdbtnFile = new JRadioButton("File");
				rdbtnFile.setFont(new Font("Cambria", Font.PLAIN, 18));
				rdbtnFile.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {		
						rdbtnDirectory.setSelected(false);  //set another radio button to unselected
						readOptionsFile();
					}
				});
				rdbtnFile.setBounds(517, 273, 67, 28);
				importTab.add(rdbtnFile);
				
				runBtn = new JButton("Run");

				runBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						runBtnClicked();
					}
				});
				runBtn.setFont(new Font("Cambria", Font.PLAIN, 18));
				
						runBtn.setBounds(642, 406, 130, 44);
						importTab.add(runBtn);
						
						txtInPath = new JTextField();
						txtInPath.setToolTipText("Default path is '/mallet_files' if not set");
						txtInPath.setFont(new Font("微软雅黑", Font.PLAIN, 17));
						txtInPath.setColumns(10);
						txtInPath.setBorder(new LineBorder(new Color(171, 173, 179)));
						txtInPath.setBounds(212, 34, 415, 35);
						importTab.add(txtInPath);
						
						outputTab = new JPanel();
						outputTab.setForeground(UIManager.getColor("Button.background"));
						outputTab.setBorder(null);
						tabMenu.addTab("Output", null, outputTab, null);
						outputTab.setLayout(null);
						
						JScrollPane outputScroll = new JScrollPane();
						outputScroll.setBounds(0, 0, 658, 478);
						outputTab.add(outputScroll);
						
						outputArea = new JTextArea();
						outputScroll.setViewportView(outputArea);
						outputArea.setLineWrap(true);
						outputArea.setEditable(false);
						outputArea.setFont(new Font("微软雅黑", Font.PLAIN, 17));
						
						clrBtn = new JButton("Clear");
						clrBtn.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								outputArea.setText("");
							}
						});
						clrBtn.setFont(new Font("Cambria", Font.PLAIN, 18));
						clrBtn.setBounds(672, 424, 113, 40);
						outputTab.add(clrBtn);
	}
	

	public void runBtnClicked(){
		String outputPath=txtOutPath.getText();
		if(outputPath!=null){
			if(outputPath.equals("")){
				JOptionPane.showMessageDialog(null, "Output path is empty!", "Message", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
				
		}else{
			JOptionPane.showMessageDialog(null, "Output path is empty!", "Message", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
			
//		获取选项，拼接命令
		ArrayList<String> optionList=getOptions();
		String importType = null;
		String cmd=null;
		if(rdbtnFile.isSelected()==true)
			importType="import-file";
		else 
			importType="import-dir";
		cmd="mallet "+importType+" --input";
		cmd+=" "+txtInPath.getText();
		for(String option:optionList){
			cmd+=" "+option;
		}
		cmd+=" --output "+outputPath;
		String[] helpcmd = new String[] { "cmd.exe", "/C", "mallet import-file --help" }; //for debug
		
		String[] exec_cmd=new String[] { "cmd.exe", "/C", cmd };
		System.out.println(cmd);
		String[][] result=exec(exec_cmd);
		
		
		for (String lines:result[0] ) {  //input
			System.out.println(lines); 
            outputArea.append(lines+"\n");
            //滚动条自动滚至底部
            outputArea.setSelectionStart(outputArea.getText().length());
        }  
		for (String lines:result[1] ) {  //error
			System.out.println(lines); 
			outputArea.append(lines+"\n");
			outputArea.setSelectionStart(outputArea.getText().length());
        }  
		
	}
	
	
	
	
	//[0]存储 inputStream [1]储存errorStream； 每个都是String[]的形式
	public String[][] exec(String[] cmd){
//		String projectPath="D://Dvlp_workspace/Eclipse_workspace/Mallet_UI";
		String projectPath=System.getProperty("user.dir");
		String[][] info= new String[2][];
		StreamDrainer input = null;
		StreamDrainer error=null;
        try {
        	//new file: 在指定文件夹中运行命令
            Process process = Runtime.getRuntime().exec(cmd,null,new File(projectPath));
//        	Process process = Runtime.getRuntime().exec(cmd);
            input = new StreamDrainer(process.getInputStream());
            error = new StreamDrainer(process.getErrorStream());
            new Thread(input).start();
            new Thread(error).start();
            
            process.getOutputStream().close();

            int exitValue = process.waitFor();
            System.out.println("Exit " + exitValue);
        } catch (Exception e) {
            e.printStackTrace();
            outputArea.append(e.toString());
        }
        info[0]=input.getOutInfo();
        info[1]=error.getOutInfo();
        return info;
	}
	
	
	public ArrayList<String> getOptions(){
		TableModel tm =optionTable.getModel();
		ArrayList<String> optionList = new ArrayList<String>();
		String optionName=null;
		String optionValue=null;
		String cmd=null;
		for(int i=0;i<tm.getRowCount();i++){
			 optionName=(String) tm.getValueAt(i, 0);
			 optionValue=(String) tm.getValueAt(i, 1);
			 if(optionValue!=null){
				 if(!optionValue.equals("")){
					 cmd = optionName+" "+optionValue;
				     optionList.add(cmd);
				 }
			 }			 
		}
		return optionList;
	}
	
	
	public ArrayList<String> getFiles(){
		TableModel tm =optionTable.getModel();
		ArrayList<String> fileList = new ArrayList<String>();
		String fileName=null;
		String cmd=null;
		for(int i=0;i<tm.getRowCount();i++){
			 fileName=(String) tm.getValueAt(i, 0);
		     fileList.add(fileName);		 
		}
		return fileList;
	}
	
    
    //获取文件/文件夹列表
    private String getSelectedResult() {
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//        FileNameExtensionFilter filter = new FileNameExtensionFilter(
//                "(*.xls)", "xls");
//        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Select path");

        int result = fileChooser.showDialog(null, "OK");
    	if (result == JFileChooser.APPROVE_OPTION) {
        	String path=fileChooser.getSelectedFile().getPath();
        	File f = new File(path);
        	if(f.isDirectory())   		
        		return fileChooser.getSelectedFile().getPath()+"\\*";
        	else 
        		return fileChooser.getSelectedFile().getPath();
        }
        return null;
    }
	
	
	public void readOptionsFile(){
		File file = null;
		if(rdbtnFile.isSelected())
			file = new File("options/import_file_options");  //relative path no /
		else
			file = new File("options/import_dir_options");
		
		BufferedReader reader = null;
		ArrayList<String> optionList= new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	optionList.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		
        //push options into table
        String[][] objectArray = new String[optionList.size()][];
		int m=0;
		for(String option:optionList){
			objectArray[m++] = new String[]{option};
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(objectArray,opTableHeader){
//			重写table model,使第一列不可编辑，第二列可编辑
				public boolean isCellEditable(int rowIndex, int columnIndex) 	//重写方法改编可编辑性
        		{
        			if( columnIndex == getColumnCount() - 1 )
        				return true;
        			return false;
        	    }
		};
        optionTable.setModel(tableModel);
        TableColumn column= optionTable.getColumnModel().getColumn(0);
        //没有禁用resizable,所以实际仍可拖动
        column.setPreferredWidth(150);
	}
}
