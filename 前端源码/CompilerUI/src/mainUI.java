import javax.swing.JFrame;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class mainUI {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainUI window = new mainUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("编译原理课程设计_Compiler");
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("window.ico"));
		frame.setBounds(100, 100, 1040, 712);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea_1 = new JTextArea();
		JTextArea textArea_2 = new JTextArea();
	    
	    
		//以下实现UI上部编码区
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 37, 1040, 369);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
				
		textArea_2.setFont(new Font("仿宋", Font.PLAIN, 24));
		textArea_2.setBounds(1, 1, 1010, 352);
		panel_3.add(textArea_2);
				
		JScrollPane scrollPane = new JScrollPane(textArea_2);//实现滚动条
		scrollPane.setBounds(0, 0, 1015, 352);
		panel_3.add(scrollPane);
		
		//以下实现顶部操作按钮
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1040, 42);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("打开文件");
		btnNewButton.setBounds(15, 5, 105, 29);
		panel.add(btnNewButton);
		//打开文件按钮鼠标事件监听
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FileDialog  openFile=new FileDialog(frame, "打开文件", FileDialog.LOAD);
                openFile.setVisible(true);
				String dirName=openFile.getDirectory();
                String fileName=openFile.getFile();
                //System.out.println(dirName);
                
                //读取展示文件
                if(dirName==null || fileName==null){
                    return;
                }
                File file=new File(dirName,fileName);
                try {
                    //BufferedReader br=new BufferedReader(new FileReader(file));
                	BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
                    String line;
                    StringBuilder text = new StringBuilder();
                    while((line=br.readLine()) != null){
                        text.append(line);
                        text.append("\r\n");
                    }
                    textArea_2.setText(text.toString());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
			}
		});
		
		JButton btnNewButton_1 = new JButton("保存文件");	
		btnNewButton_1.setBounds(195, 5, 105, 29);
		panel.add(btnNewButton_1);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
               if(textArea_2.getText().length()==0||textArea_2.getText().equals("")) {
     				       textArea_1.setText("未编辑或未打开任何文件！");		  
               }
               else {
                   FileDialog saveDia = new FileDialog(frame, "保存文件", FileDialog.SAVE);
                      saveDia.setVisible(true);//显示保存文件对话框
                      String dirpath = saveDia.getDirectory();//获取保存文件路径并保存到字符串中。
                      String fileName = saveDia.getFile();////获取打保存文件名称并保存到字符串中 
                        if (dirpath == null || fileName == null) {//判断路径和文件是否为空
                            return;//空操作
                         }
                   File file=new File(dirpath,fileName);//文件不为空，新建一个路径和名称
                     try {		
                    	 PrintWriter bufw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"GBK")));
                    	//BufferedWriter bufw = new BufferedWriter(new FileWriter(file));  导致文件读出乱码
                        String text = textArea_2.getText();//获取文本内容
                        bufw.write(text);//将获取文本内容写入到字符输出流
                        bufw.close();//关闭文件
                        textArea_1.setText("文件保存成功！");
                        }
                    catch (IOException e1) {
                        //抛出IO异常
                        e1.printStackTrace();
                    }
                }
            }
        });
		
		JButton btnNewButton_2 = new JButton("编译");
		btnNewButton_2.setBounds(388, 5, 69, 29);
		panel.add(btnNewButton_2);
		//编译按钮鼠标侦听
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  Runtime runtime = Runtime.getRuntime();   
				  try {
					  if(textArea_2.getText().length()==0||textArea_2.getText().equals("")) {
				       textArea_1.setText("未找到工程文件！");		  
				  } else {
					  Process process_0 = runtime.exec("cmd /k Compiler_.c.exe"+" >> log.txt");		     
				      Thread.sleep(2000);
				      textArea_1.setText("Compile Success!");
				  }
				  }
				  catch (Exception e1) {
				  System.out.println("找不到编译器");
				  textArea_1.setText("CompilerNotFound");
				  }
				  }
		});
		
		JButton btnNewButton_3 = new JButton("自定义语法");
		btnNewButton_3.setBounds(555, 5, 123, 29);
		panel.add(btnNewButton_3);
		//自定义语法按钮监听
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
                	BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("R1_RG.data"),"GBK"));
                    String line;
                    StringBuilder text = new StringBuilder();
                    while((line=br.readLine()) != null){
                        text.append(line);
                        text.append("\r\n");
                    }
                    textArea_2.setText(text.toString());
                } catch (Exception e1) {
                    e1.printStackTrace();
                    textArea_1.setText("Unknown Source");
                }
			}
		});
		
		JButton btnNewButton_4 = new JButton("帮助");
		btnNewButton_4.setBounds(762, 5, 69, 29);
		panel.add(btnNewButton_4);
		//帮助按钮事件监听
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
                	BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("R2_WT.data"),"GBK"));
                    String line;
                    StringBuilder text = new StringBuilder();
                    while((line=br.readLine()) != null){
                        text.append(line);
                        text.append("\r\n");
                    }
                    textArea_2.setText(text.toString());
                    textArea_1.setText("QUT © 2018 王飞 崔书彬");
                } catch (Exception e1) {
                    e1.printStackTrace();
                    textArea_1.setText("Unknown Source");
                }
				
			}
		});
		
		JButton btnNewButton_5 = new JButton("清除退出");
		btnNewButton_5.setBounds(907, 6, 116, 29);
		panel.add(btnNewButton_5);
		//退出按钮鼠标事件监听
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Runtime runtime = Runtime.getRuntime();   
				  try {
					  Process process_0 = runtime.exec("Clean_Cache.bat");		     				      
				      System.exit(0);
				  }
				  catch (Exception e1) {
				  System.out.println("找不到文件");
				  textArea_1.setText("Clean_CacheFileNotFoundError");
				  }
				  }
		});
			
		
		 //以下实现UI下部编译区
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 460, 1040, 199);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		//JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("等线", Font.PLAIN, 14));
		textArea_1.setBounds(0, 0, 1010, 200);
		panel_1.add(textArea_1);
		
		JScrollPane scrollPane_1 = new JScrollPane(textArea_1);//实现滚动条
		scrollPane_1.setBounds(0, 0, 1015, 200);
		panel_1.add(scrollPane_1);
		
		//以下实现下部UI单选按钮
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 405, 1040, 50);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);				
						
		ButtonGroup buttonGroup = new ButtonGroup();//实现单选，构造ButtonGroup
						
		JRadioButton rdbtnNewRadioButton = new JRadioButton("实时");
		rdbtnNewRadioButton.setBounds(15, 21, 69, 29);
		panel_2.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnNewRadioButton);
		//实时按钮添加鼠标点击侦听，显示实时内容
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 //File file_now=new File("C:\\User_Data\\Eclipse_WorkSpace\\CompilerUI\\exe\\log.txt");
	                try {
	                	BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("log.txt"),"GBK"));
	                    String line;
	                    StringBuilder text = new StringBuilder();
	                    while((line=br.readLine()) != null){
	                        text.append(line);
	                        text.append("\r\n");
	                    }
	                    textArea_1.setText(text.toString());
	                } catch (Exception e1) {
	                    e1.printStackTrace();
	                    textArea_1.setText("Unknown Source");
	                }
			}
		});
			
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("代码预处理");
		rdbtnNewRadioButton_3.setBounds(195, 21, 123, 29);
		panel_2.add(rdbtnNewRadioButton_3);
		buttonGroup.add(rdbtnNewRadioButton_3);
		//代码预处理事件侦听
		rdbtnNewRadioButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
                	BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("c2_EB.data"),"GBK"));
                    String line;
                    StringBuilder text = new StringBuilder();
                    while((line=br.readLine()) != null){
                        text.append(line);
                        text.append("\r\n");
                    }
                    textArea_1.setText(text.toString());
                } catch (Exception e1) {
                    e1.printStackTrace();
                    textArea_1.setText("Unknown Source");
                }
			}
		});
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("词法分析");
		rdbtnNewRadioButton_1.setBounds(375, 21, 105, 29);
		panel_2.add(rdbtnNewRadioButton_1);
		buttonGroup.add(rdbtnNewRadioButton_1);
		//词法分析按钮侦听
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			try {
            	BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("c3_LA.data"),"GBK"));
                String line;
                StringBuilder text = new StringBuilder();
                while((line=br.readLine()) != null){
                    text.append(line);
                    text.append("\r\n");
                }
                textArea_1.setText(text.toString());
            } catch (Exception e1) {
                e1.printStackTrace();
                textArea_1.setText("Unknown Source");
            }
		}
	});
	
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("语法分析");
		rdbtnNewRadioButton_2.setBounds(555, 21, 105, 29);
		panel_2.add(rdbtnNewRadioButton_2);
		buttonGroup.add(rdbtnNewRadioButton_2);	
		//语法分析按钮侦听
		rdbtnNewRadioButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
	            	BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("c4_GA.data"),"GBK"));
	                String line;
	                StringBuilder text = new StringBuilder();
	                while((line=br.readLine()) != null){
	                    text.append(line);
	                    text.append("\r\n");
	                }
	                textArea_1.setText(text.toString());
	            } catch (Exception e1) {
	                e1.printStackTrace();
	                textArea_1.setText("Unknown Source");
	            }
			}
		});
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("中间代码生成");
		rdbtnNewRadioButton_4.setBounds(735, 21, 141, 29);
		panel_2.add(rdbtnNewRadioButton_4);
		buttonGroup.add(rdbtnNewRadioButton_4);
		//中间代码生成按钮侦听
		rdbtnNewRadioButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
	            	BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("c6_IC.data"),"GBK"));
	                String line;
	                StringBuilder text = new StringBuilder();
	                while((line=br.readLine()) != null){
	                    text.append(line);
	                    text.append("\r\n");
	                }
	                textArea_1.setText(text.toString());
	            } catch (Exception e1) {
	                e1.printStackTrace();
	                textArea_1.setText("Unknown Source");
	            }
			}
		});
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("代码优化");
		rdbtnNewRadioButton_5.setBounds(915, 21, 105, 29);
		panel_2.add(rdbtnNewRadioButton_5);
		buttonGroup.add(rdbtnNewRadioButton_5);	
		//代码优化按钮侦听
		rdbtnNewRadioButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
	            	BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("c7_ICO.data"),"GBK"));
	                String line;
	                StringBuilder text = new StringBuilder();
	                while((line=br.readLine()) != null){
	                    text.append(line);
	                    text.append("\r\n");
	                }
	                textArea_1.setText(text.toString());
	            } catch (Exception e1) {
	                e1.printStackTrace();
	                textArea_1.setText("Unknown Source");
	            }
			}
		});
	}
}
