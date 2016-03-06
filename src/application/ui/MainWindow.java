package application.ui;


import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;


import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import Function.Importing;
import Function.SeqTagging;
import Function.trainerClassifier;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;
import javax.swing.JComboBox;

import util.OptionHandler;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Button;

import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.JTextArea;

import backup.ImportDir;
import backup.ImportFile;

import java.awt.SystemColor;
import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.SpinnerModel;




/**
 * @author huashuo
 *
 */
public class MainWindow extends JFrame{
	private boolean isFile = false;
	private JPanel contentPane;
	private JFileChooser fileChooser = new JFileChooser();
	private JTextField txtInputPath;
	private JTextField l_InputPath;
	private JTextField l_OutputPath;
	private JTextField txtInput;
	private JTextField txtOutput;
	private JTextField cl_txtClassifiertrainer;
	private JTextField cl_txtTrainingportion;
	private JTextField cl_txtTrialsnumber;
	private JTextArea txtrLala;
	private JTabbedPane tabbedPane;
	private JTextField txtImportType;
	private JTextField txtKeepSequence;
	private JTextField txtRemoveStopwords;
	
	private HashMap<String,HashMap> advancedOptionsMap= new HashMap<String,HashMap>();    //储存高级选项map的map
	private JComboBox imp_combo_imp_ty;
	private JComboBox imp_combo_kp_sq;
	private JComboBox imp_combo_rm_sw;
	private JTextArea outputArea;
	private JTextField textField;
	private JButton button_2;
	private JComboBox CcomboBox;
	private JTextField txtSamplePath;
	private JTextField txtClassifierPath;
	private JTextField textField_1;
	private JTextField textField_2;
	public MainWindow() {
		String[] tf_choice={"false","true"};
		String[] classifyTrainer = {"NaiveBayes","MaxEnt","C45","DecisionTree"};
		
		//	UI
		setTitle("Mallet");
		setFont(new Font("Cambria", Font.PLAIN, 18));
		setResizable(false);
		setBounds(100, 100, 591, 638);
		
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_dataInput = new JPanel();
		panel_dataInput.setBounds(10, 10, 565, 133);
		panel_dataInput.setLayout(null);
		contentPane.add(panel_dataInput);
		
		txtInputPath = new JTextField();
		txtInputPath.setFont(new Font("Cambria", Font.PLAIN, 18));
		txtInputPath.setText("Input Path:");
		panel_dataInput.add(txtInputPath);
		txtInputPath.setColumns(10);
		
		l_InputPath = new JTextField();
		l_InputPath.setBorder(null);
		l_InputPath.setEditable(false);
		l_InputPath.setBackground(UIManager.getColor("Button.background"));
		l_InputPath.setFont(new Font("Cambria", Font.PLAIN, 16));
		l_InputPath.setText("Input Path");
		l_InputPath.setBounds(10, 10, 115, 25);
		panel_dataInput.add(l_InputPath);
		l_InputPath.setColumns(10);
		
		l_OutputPath = new JTextField();
		l_OutputPath.setBorder(null);
		l_OutputPath.setEditable(false);
		l_OutputPath.setFont(new Font("Cambria", Font.PLAIN, 16));
		l_OutputPath.setBackground(UIManager.getColor("Button.background"));
		l_OutputPath.setText("OuptPut path");
		l_OutputPath.setBounds(10, 56, 115, 25);
		panel_dataInput.add(l_OutputPath);
		l_OutputPath.setColumns(10);
		
		txtInput = new JTextField();
		txtInput.setEditable(false);
		//		默锟斤拷锟斤拷锟斤拷路锟斤拷为锟斤拷前目录
//		txtInput.setText(System.getProperty("user.dir"));
		ImportDir.getInstance().setImportPath(System.getProperty("user.dir"));
		txtInput.setBounds(135, 10, 275, 25);
		panel_dataInput.add(txtInput);
		txtInput.setColumns(10);
		
		txtOutput = new JTextField();
		txtOutput.setEditable(false);
		txtOutput.setBounds(135, 56, 275, 25);
		//	
//		txtOutput.setText(System.getProperty("user.dir")+"\\tmp.classifier");
		panel_dataInput.add(txtOutput);
		txtOutput.setColumns(10);
		
		JButton btnSelect = new JButton("Browse");
		btnSelect.setBackground(UIManager.getColor("Button.background"));
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String fileBeRead = getPath();
				txtInput.setText(fileBeRead);
			}
		});
		btnSelect.setFont(new Font("Cambria", Font.PLAIN, 16));
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSelect.setBounds(420, 10, 93, 25);
		panel_dataInput.add(btnSelect);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String fileBeRead = getPath();
				txtOutput.setText(fileBeRead+"\n");
			}
		});
		btnBrowse.setBackground(UIManager.getColor("Button.background"));
		btnBrowse.setFont(new Font("Cambria", Font.PLAIN, 16));
		btnBrowse.setBounds(420, 56, 93, 25);
		panel_dataInput.add(btnBrowse);
		
		JButton btnAdvance_1 = new JButton("Advance");
		btnAdvance_1.setBackground(UIManager.getColor("Button.background"));
		btnAdvance_1.setFont(new Font("Cambria", Font.PLAIN, 16));
		btnAdvance_1.setBounds(420, 102, 93, 25);
		panel_dataInput.add(btnAdvance_1);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Cambria", Font.PLAIN, 16));
		tabbedPane.setBounds(10, 145, 565, 250);
		contentPane.add(tabbedPane);
		
		JPanel panel_import = new JPanel();
		tabbedPane.addTab("Importing", null, panel_import, null);
		panel_import.setLayout(null);
		
		txtImportType = new JTextField();
		txtImportType.setBounds(14, 14, 88, 21);
		txtImportType.setText("Import type");
		txtImportType.setFont(new Font("Cambria", Font.PLAIN, 16));
		txtImportType.setColumns(10);
		txtImportType.setBorder(null);
		txtImportType.setBackground(SystemColor.menu);
		panel_import.add(txtImportType);
		
		String[] imp_type={"Directory","File","SVMlight"};
		imp_combo_imp_ty = new JComboBox(imp_type);
		imp_combo_imp_ty.setFont(new Font("Cambria", Font.PLAIN, 16));
		imp_combo_imp_ty.setBounds(162, 13, 151, 23);
		panel_import.add(imp_combo_imp_ty);
		
		txtKeepSequence = new JTextField();
		txtKeepSequence.setText("Keep sequence");
		txtKeepSequence.setFont(new Font("Cambria", Font.PLAIN, 16));
		txtKeepSequence.setColumns(10);
		txtKeepSequence.setBorder(null);
		txtKeepSequence.setBackground(SystemColor.menu);
		txtKeepSequence.setBounds(14, 55, 112, 22);
		panel_import.add(txtKeepSequence);
	

		imp_combo_kp_sq = new JComboBox(tf_choice);
		imp_combo_kp_sq.setSelectedItem(null);
		imp_combo_kp_sq.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Importing.getInstance().getOptionMap().put("keep-sequence", imp_combo_kp_sq.getSelectedItem().toString());
			}
		});
		
		imp_combo_kp_sq.setFont(new Font("Cambria", Font.PLAIN, 16));
		imp_combo_kp_sq.setBounds(162, 55, 151, 23);
		panel_import.add(imp_combo_kp_sq);
		
		txtRemoveStopwords = new JTextField();
		txtRemoveStopwords.setText("Remove stopwords");
		txtRemoveStopwords.setFont(new Font("Cambria", Font.PLAIN, 16));
		txtRemoveStopwords.setColumns(10);
		txtRemoveStopwords.setBorder(null);
		txtRemoveStopwords.setBackground(SystemColor.menu);
		txtRemoveStopwords.setBounds(14, 103, 134, 21);
		panel_import.add(txtRemoveStopwords);
		
		imp_combo_rm_sw = new JComboBox(tf_choice);
		imp_combo_rm_sw.setSelectedItem(null);	
		imp_combo_rm_sw.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Importing.getInstance().getOptionMap().put("remove-stopwords", imp_combo_rm_sw.getSelectedItem().toString());
			}
		});
			
		imp_combo_rm_sw.setFont(new Font("Cambria", Font.PLAIN, 16));
		imp_combo_rm_sw.setBounds(162, 102, 151, 23);
		panel_import.add(imp_combo_rm_sw);
		
		JButton imp_advance_btn = new JButton("Advance");
		imp_advance_btn.addMouseListener(new MouseAdapter() {
			@Override
			//	鑾峰彇骞惰缃甶mporting鐨勯珮绾ч�夐」
			public void mouseClicked(MouseEvent arg0) {
				AdvanceFrame adf= new AdvanceFrame(getSelectedFunction());
				adf.setVisible(true);
				advancedOptionsMap.put(adf.getAction(), adf.getOptionMap());
			}
		});
		imp_advance_btn.setFont(new Font("Cambria", Font.PLAIN, 16));
		imp_advance_btn.setBounds(387, 52, 105, 29);
		panel_import.add(imp_advance_btn);
		
		JPanel panel_trainer = new JPanel();
		panel_trainer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		panel_trainer.setLayout(null);
		tabbedPane.addTab("Classify-trainer", null, panel_trainer, null);
		
		cl_txtClassifiertrainer = new JTextField();
		cl_txtClassifiertrainer.setBackground(UIManager.getColor("Button.background"));
		cl_txtClassifiertrainer.setBorder(null);
		cl_txtClassifiertrainer.setFont(new Font("Cambria", Font.PLAIN, 16));
		cl_txtClassifiertrainer.setText("Classifier-trainer");
		cl_txtClassifiertrainer.setBounds(10, 10, 122, 25);
		panel_trainer.add(cl_txtClassifiertrainer);
		cl_txtClassifiertrainer.setColumns(10);
		
		//	閫夋嫨classifyTrainer
		final JComboBox cl_trainer_combo = new JComboBox(classifyTrainer);
		cl_trainer_combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
					System.out.println(cl_trainer_combo.getSelectedItem().toString());
					trainerClassifier.getInstance().setTrainer(cl_trainer_combo.getSelectedItem().toString());
				}
			}
		});
		cl_trainer_combo.setFont(new Font("Cambria", Font.PLAIN, 13));
		cl_trainer_combo.setBounds(142, 12, 151, 23);
		panel_trainer.add(cl_trainer_combo);
		
		cl_txtTrainingportion = new JTextField();
		cl_txtTrainingportion.setBorder(null);
		cl_txtTrainingportion.setFont(new Font("Cambria", Font.PLAIN, 16));
		cl_txtTrainingportion.setBackground(UIManager.getColor("Button.background"));
		cl_txtTrainingportion.setText("training-portion");
		cl_txtTrainingportion.setBounds(10, 50, 122, 25);
		panel_trainer.add(cl_txtTrainingportion);
		cl_txtTrainingportion.setColumns(10);
		
		final SpinnerNumberModel cl_tp_snm = new SpinnerNumberModel(0.0, 0.0, 1.0, 0.1);
		JSpinner cl_pro_spin = new JSpinner(cl_tp_snm);
		cl_pro_spin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println(cl_tp_snm.getValue());
				if ((Double)cl_tp_snm.getNumber() == 0.0) trainerClassifier.getInstance().setPortition(null);
				else trainerClassifier.getInstance().setPortition((Double)cl_tp_snm.getNumber());
			}
		});
		cl_pro_spin.setBounds(142, 51, 81, 22);
		panel_trainer.add(cl_pro_spin);
		
		cl_txtTrialsnumber = new JTextField();
		cl_txtTrialsnumber.setBackground(UIManager.getColor("Button.background"));
		cl_txtTrialsnumber.setBorder(null);
		cl_txtTrialsnumber.setFont(new Font("Cambria", Font.PLAIN, 16));
		cl_txtTrialsnumber.setText("trials-number");
		cl_txtTrialsnumber.setBounds(10, 90, 122, 25);
		panel_trainer.add(cl_txtTrialsnumber);
		cl_txtTrialsnumber.setColumns(10);
		
		String[] classify_type = {"","File","Directory","Svmlight"};
		
		final SpinnerNumberModel cl_tr_snm = new SpinnerNumberModel(0,0,null,1);
		JSpinner cl_trials_spin = new JSpinner(cl_tr_snm);
		cl_trials_spin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				System.out.println(cl_tr_snm.getNumber());
				if ((Integer)cl_tr_snm.getNumber() == 0) trainerClassifier.getInstance().setTrials(null);
				else trainerClassifier.getInstance().setTrials((Integer)cl_tr_snm.getNumber());
			}
		});
		cl_trials_spin.setBounds(142, 91, 81, 22);
		panel_trainer.add(cl_trials_spin);
		
		textField_2 = new JTextField();
		textField_2.setText("cross-validation");
		textField_2.setFont(new Font("Cambria", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBorder(null);
		textField_2.setBackground(SystemColor.menu);
		textField_2.setBounds(10, 125, 122, 25);
		panel_trainer.add(textField_2);
		
		final SpinnerNumberModel spinner = new SpinnerNumberModel(0,0,null,1);
		JSpinner cl_trials_spin_1 = new JSpinner(spinner);
		cl_trials_spin_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				System.out.println(spinner.getNumber());
				if ((Integer) spinner.getNumber() == 0) trainerClassifier.getInstance().setCross(null);
				else trainerClassifier.getInstance().setCross((Integer)spinner.getNumber());
			}
		});
		cl_trials_spin_1.setBounds(142, 128, 81, 22);
		panel_trainer.add(cl_trials_spin_1);
		
		
		
		
	
		
		
		
		JScrollPane panel_output = new JScrollPane();
		panel_output.addComponentListener(new ComponentAdapter() {
			//	褰搊utput閫夐」鍗℃墦寮�鏃讹紝text area 鎷夊ぇ
			@Override
			public void componentShown(ComponentEvent arg0) {
				tabbedPane.setBounds(10, 145, 565, 420);
//				outputArea.setText("1");
			}
			@Override
			public void componentHidden(ComponentEvent arg0) {
				tabbedPane.setBounds(10, 145, 565, 250);
			}
		});
		//	璁剧疆iteration
		final SpinnerNumberModel seq_iter_snm = new SpinnerNumberModel(0, 0, 1000, 10);
		//	璁剧疆train_proportion
		final SpinnerNumberModel seq_tr_snm = new SpinnerNumberModel(0.0, 0.0, 1, 0.1);
		
		JPanel panel_classify = new JPanel();
		panel_classify.setLayout(null);
		tabbedPane.addTab("Classifer", null, panel_classify, null);
		
		txtSamplePath = new JTextField();
		txtSamplePath.setFont(new Font("Cambria", Font.PLAIN, 16));
		txtSamplePath.setBorder(null);
		txtSamplePath.setBackground(UIManager.getColor("Button.background"));
		txtSamplePath.setText("Sample Path:");
		txtSamplePath.setBounds(10, 10, 122, 25);
		panel_classify.add(txtSamplePath);
		txtSamplePath.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(142, 10, 151, 25);
		panel_classify.add(textField);
		textField.setEditable(false);
		textField.setColumns(10);
		
		CcomboBox = new JComboBox(classify_type);
		CcomboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		CcomboBox.setFont(new Font("Cambria", Font.PLAIN, 13));
		CcomboBox.setBounds(303, 10, 91, 25);
		panel_classify.add(CcomboBox);
		
		button_2 = new JButton("Browse");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fileBeRead = getPath();
				textField.setText(fileBeRead+"\n");
			}
		});
		button_2.setFont(new Font("Cambria", Font.PLAIN, 16));
		button_2.setBackground(SystemColor.menu);
		button_2.setBounds(404, 10, 91, 25);
		panel_classify.add(button_2);
		
		txtClassifierPath = new JTextField();
		txtClassifierPath.setBorder(null);
		txtClassifierPath.setFont(new Font("Cambria", Font.PLAIN, 16));
		txtClassifierPath.setBackground(UIManager.getColor("Button.background"));
		txtClassifierPath.setText("Classifier Path:");
		txtClassifierPath.setBounds(10, 56, 122, 25);
		panel_classify.add(txtClassifierPath);
		txtClassifierPath.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(142, 56, 151, 25);
		panel_classify.add(textField_1);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JButton button = new JButton("Browse");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fileBeRead = getPath();
				textField_1.setText(fileBeRead+"\n");
			}
		});
		button.setFont(new Font("Cambria", Font.PLAIN, 16));
		button.setBackground(SystemColor.menu);
		button.setBounds(404, 56, 91, 25);
		panel_classify.add(button);
		
		tabbedPane.addTab("Output", null, panel_output, null);
		
		outputArea = new JTextArea();
		outputArea.setFont(new Font("Dialog", Font.PLAIN, 14));
		outputArea.setEditable(false);
		panel_output.setViewportView(outputArea);
		
		JButton btnRun = new JButton("Run");
		btnRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Run();
			}
		});
		btnRun.setFont(new Font("Cambria", Font.PLAIN, 15));
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JScrollPane scrollPane_log = new JScrollPane();
		scrollPane_log.setBounds(10, 405, 565, 148);
		contentPane.add(scrollPane_log);
		
		txtrLala = new JTextArea();
		txtrLala.setEditable(false);
		txtrLala.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollPane_log.setViewportView(txtrLala);
		btnRun.setBounds(460, 569, 115, 30);
		contentPane.add(btnRun);
	}
	

	/**
	 * 鐐瑰嚮run button鏃惰皟鐢ㄨ鏂规硶
	 */
	private void Run(){
		String[] result=null;	
		
		//	濡傛灉閫夋嫨鐨勬槸import data
		if(tabbedPane.getSelectedIndex() == 0){
			if(txtInput.getText()!=null||!txtInput.getText().equals("")){
				Importing.getInstance().setInputPath(txtInput.getText());		
				Importing.getInstance().setOutputPath(txtOutput.getText());
			}else  //(Component parentComponent, Object message, String title, int messageType, Icon icon) 
				JOptionPane.showMessageDialog(null, "No data file selected!", "Alert", JOptionPane.ERROR_MESSAGE);
			if(imp_combo_imp_ty.getSelectedItem().toString().equals("File")){
				File f = new File(txtInput.getText());
				if(!f.isFile()){
					JOptionPane.showMessageDialog(null, "A file should be selected!", "Alert", JOptionPane.ERROR_MESSAGE);
					return;
				}					
				Importing.getInstance().setAction("import_file");
			}else if(imp_combo_imp_ty.getSelectedItem().toString().equals("Directory")){
				File f = new File(txtInput.getText());
				if(!f.isDirectory()){
					JOptionPane.showMessageDialog(null, "A directory should be selected!", "Alert", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Importing.getInstance().setAction("import_dir");
			}else{
				Importing.getInstance().setAction("import_svm");	
			}		

			if(advancedOptionsMap.get(Importing.getInstance().getAction())!=null)
				Importing.getInstance().setAdvOptionMap(advancedOptionsMap.get(Importing.getInstance().getAction()));
			result=Importing.getInstance().run();
			print2text(result, "Importing");

		}
		
		// classifier
		else if (tabbedPane.getSelectedIndex() == 1){	//		
			//鏆傛椂璁剧疆涓哄湪classifier妯″紡涓嬶紝input鍙兘涓簃allet鏂囦欢
			if(txtInput.getText()!=null||!txtInput.getText().equals("")){		
				trainerClassifier.getInstance().setImportFile(txtInput.getText());
				trainerClassifier.getInstance().setOuputFile(txtOutput.getText());
				result = trainerClassifier.getInstance().run();
				print2text(result, "Train Classifier");
							
			}
			else
				JOptionPane.showMessageDialog(null, "No mallet file selected!", "Alert", JOptionPane.ERROR_MESSAGE);	
		}
		else if(tabbedPane.getSelectedIndex() == 2){
			if(textField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "No sample file selected!", "Alert", JOptionPane.ERROR_MESSAGE);
			}
			else if(CcomboBox.getSelectedItem().equals("")){
				JOptionPane.showMessageDialog(null, "No sample type selected!", "Alert", JOptionPane.ERROR_MESSAGE);
			}
			else if(textField_1.getText().equals("")){
				JOptionPane.showMessageDialog(null, "No classifier selected!", "Alert", JOptionPane.ERROR_MESSAGE);
			}
			else{
				trainerClassifier.getInstance().setClassify_type(CcomboBox.getSelectedItem().toString());
				trainerClassifier.getInstance().setClassPath(textField.getText().toString());
				trainerClassifier.getInstance().setClassifier(textField_1.getText().toString());
				result = trainerClassifier.getInstance().classify();
				print2text(result, "Classifier");
			}
		}
			
		//sequence tagging

	}
	
	
	

	/**
	 * 灏嗙▼搴忔墽琛屼俊鎭墦鍗�
	 * @param result 锛堣繍琛岀粨鏋滐級
	 * @param action 锛堣繘琛岀殑鎿嶄綔锛�
	 */
	private void print2text(String[] result, String action){
		//命令执行的时间
		String time ="";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		time = df.format(new Date());
		outputArea.append(time+"\r\n");

		if (result[3].equals("1")){
			outputArea.append(action + " Failed.\n\n\n");
			outputArea.setSelectionStart(outputArea.getText().length());
		}
		else{
			outputArea.setSelectionStart(outputArea.getText().length());
			outputArea.append(result[1]+"\r\n");
			outputArea.setSelectionStart(outputArea.getText().length());	       
			outputArea.append(result[0]+"\r\n");
			outputArea.setSelectionStart(outputArea.getText().length());
			outputArea.append(action + " succeed.\n\n\n");
			outputArea.setSelectionStart(txtrLala.getText().length());
		}
	}
	
	
	/**
	 * 鑾峰彇鏂囦欢/鏂囦欢澶硅矾寰�
	 * @return 璺緞
	 */
	private String getPath() {
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setDialogTitle("Select path");
        fileChooser.setCurrentDirectory(new File("."));
        
        int result = fileChooser.showDialog(null, "OK");
    	if (result == JFileChooser.APPROVE_OPTION) {
        	String path=fileChooser.getSelectedFile().getPath();
        	File f = new File(path);
        	if(f.isDirectory()){
        		isFile = false;
        		txtrLala.append("Import Dir:\n"+path+"\n");
        	}
        		
        	else{
        		isFile = true;
        		txtrLala.append("Import File:\n"+path+"\n");
        	}
        	return path;
        		
        }
        return System.getProperty("user.dir");
    }

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//鏍规嵁tab鍒ゆ柇搴旇瀵煎叆鍝簺advanced options
	private String getSelectedFunction(){
		int functionIndex=tabbedPane.getSelectedIndex();
		String action=null;
		if(functionIndex==0){
			if(imp_combo_imp_ty.getSelectedItem().toString().equals("File")){
				action="import_file";
			}else if(imp_combo_imp_ty.getSelectedItem().toString().equals("Directory")){
				action="import_dir";
			}else
				action="import_svm";
		}else if(functionIndex==1){
			action="classifier";
		}else if(functionIndex==2)
			action="seq_tagging";
		return action;
	}
}