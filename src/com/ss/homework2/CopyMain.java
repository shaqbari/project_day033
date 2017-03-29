package com.ss.homework2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class CopyMain extends JFrame implements ActionListener, Runnable{
	JProgressBar bar;
	JButton bt_open, bt_save, bt_copy;
	JTextField t_open, t_save;
	JFileChooser chooser;
	File file; //�о���� ����
	Thread thread; //���縦 ������ ���뾲����
	//���� �޼ҵ�� �츮�� �˰��ִ� �� ����ζ� �Ҹ��� ���ø����̼��� ��� ����ϴ� ������ �����Ѵ�.
	//���� ���� ���ѷ����� �����¿� ������ �ؼ��� �ȵȴ�.!!
	
	long total; //���������� ��ü �뷮
	
	public CopyMain() {
		setLayout(new FlowLayout());
		
		bar=new JProgressBar();
		bt_open=new JButton("����");
		bt_save=new JButton("����");
		bt_copy=new JButton("����");
		t_open=new JTextField(35);
		t_save=new JTextField(35);
		chooser=new JFileChooser("D:/git/java_workspace/project_day033/src/com/ss/res/");
		
		bar.setPreferredSize(new Dimension(450, 50));
		bar.setBackground(Color.YELLOW);
		bar.setStringPainted(true);
		bar.setString("0%"); //�˾Ƽ�ã������		
		
		add(bar);
		add(bt_open);
		add(t_open);
		add(bt_save);
		add(t_save);
		add(bt_copy);

		//��ư�� ������ ����
		bt_open.addActionListener(this);
		bt_save.addActionListener(this);
		bt_copy.addActionListener(this);
		
		setSize(500,200);
		setVisible(true);
		setLocationRelativeTo(null); //null�� ���� �ٸ� ��ü�� ��������� �ʰ� ���߾ӿ� ���.
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	public void actionPerformed(ActionEvent e) {
	    Object obj=e.getSource(); //�̺�Ʈ�� ����Ų �̺�Ʈ �ҽ�(�̺�Ʈ ��ü)
		if(obj==bt_open){
			open();			
		}else if(obj==bt_save){
			save();			
		}else if(obj==bt_copy){
			//copy();
			//������ ���� ���縦 �������� ���� �����忡�� ��Ű��!
			//������ �����ڿ� Runnable ������ü�� �μ��� ������,
			//Runnable��ü���� �������� run()�޼ҵ带 �����Ѵ�.
			//thread = new Thread();
			//thread.start();//�̷��� �ϸ� ��ݻ����� ������ ���۽�Ŵ
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void open(){
		int result=chooser.showOpenDialog(this);
		if (result==JFileChooser.APPROVE_OPTION) {
			file=chooser.getSelectedFile();
			t_open.setText(file.getAbsolutePath());
			total=file.length();
		}		
	}
	
	public void save(){
		int result=chooser.showOpenDialog(this);
		if (result==JFileChooser.APPROVE_OPTION) {
			File file=chooser.getSelectedFile();
			t_save.setText(file.getAbsolutePath());
		}		
	}
	public void copy(){
		FileInputStream fis=null;//���������� �ڵ��ʱ�ȭ���� �ʴ´�.
		FileOutputStream fos=null;
		
		try {
			fis=new FileInputStream(file); //���Ͽ� ���� �ȾҴ�.
			fos=new FileOutputStream(t_save.getText());
						
			//������ ��Ʈ���� ���� ������ �б�			
			int data;
			int count=0;
			while(true){ //main���� ���ѷ������� ���ξ����尡 �������� �ٸ��۾� ���Ѵ�. ���ѷ����� �ٸ�������� �����Ѵ�.
				data=fis.read(); //1byte �б�				
				if(data==-1) break;
				count++;
				fos.write(data); //1byte ���				
				int v=(int)getPercent(count); //bar.setValue�� ���ڷ� �ֱ����� ��������ȯ
				
				//���α׷����ٿ� ����
				bar.setValue(v);
				bar.setString(v+"%");				
			}
			JOptionPane.showMessageDialog(this, "����Ϸ�");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos!=null){
				try {
					fos.close(); //�۾� �� �������� �ݴ°��� ��Ģ
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis!=null){
				try {
					fis.close(); //fis�� �ݾ���� �Ѵ�.
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	//���� ������ ���ϱ� ����
	//������=100%*��/��üũ��
	public long getPercent(int currentRead){
		return (100*currentRead)/total; //��Ʈ�� long�̵Ǿ�� �ս��� ���� �ڵ�����ȯ�ȴ�.
	}
	
	public void run() {
		copy();
	}
	
	public static void main(String[] args) {
		new CopyMain();
	}

}
