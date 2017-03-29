package com.ss.page;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainApp extends JFrame implements ActionListener{
	JPanel p_north, p_center; //p_center���� ���������� �������� 
	URL[] url=new URL[3];
	String[] path={
		"/login.png",
		"/content.png",
		"/etc.png"			
	};
	JButton[] menu= new JButton[3]; // bt_login, bt_content, bt_etc;	
	//LoginForm loginForm;
	//Content content;
	//Etc etc;
		
	JPanel[] page= new JPanel[3];
	//ArrayList<JPanel> pages;
	
	public MainApp() {
		p_north=new JPanel();		
		for (int i = 0; i < path.length; i++) {
			url[i]=this.getClass().getResource(path[i]);
			menu[i]=new JButton(new ImageIcon(url[i]));
			p_north.add(menu[i]);			
			menu[i].addActionListener(this);//��ư�� ������ ����
		}
		//ImageIcon icon=new ImageIcon(this.getClass().getResource("/login.png");)
		//bt_login=new JButton("�α���");
		//bt_content=new JButton("����");
		//bt_etc=new JButton("��Ÿ");
		//�迭�ΰ���!				
		
		
		//��ư �����鼭 �г��� ��������� ���������� ������ �޸� ����� �� �ִ�.
		//��ư�� ���������� �гε��� �ö���ְ� ��ư������ �����ִ°� �ٲ�� �Ѵ�.
		/*p_center=new JPanel();
		loginForm=new LoginForm();
		content=new Content();
		etc=new Etc();	
				
		p_center.add(loginForm);	//�α��� �� ����		
		p_center.add(content); //����Ʈ ����boderlayout�̸� ���� �α������� �Ⱥ��̰� �ȴ�. 
		p_center.add(etc);//�׷��Ƿ� ���� jpanel�� ����� �ű⿡ ����.
		*/		
			
		p_center=new JPanel();
		page[0]=new LoginForm();
		page[1]=new Content();
		page[2]=new Etc();
		
		for (int i = 0; i < page.length; i++) {
			p_center.add(page[i]);
		}
		
		/*p_center.add(page[0]);		
		p_center.add(page[1]);
		p_center.add(page[2]);*/

		/*pages=new ArrayList<JPanel>();
		pages.add(loginForm);
		pages.add(content);
		pages.add(etc);*/
		
		add(p_north, BorderLayout.NORTH);	
		add(p_center);
		
		setSize(700, 680);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}

	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		/*if (obj==menu[0]) {
			//�α����� o
			//������ x
			//��Ÿ x
			loginForm.setVisible(true);
			content.setVisible(false);
			etc.setVisible(false);
			
		}else if (obj==menu[1]) {
			//�α����� x
			//������ o
			//��Ÿ x	
			loginForm.setVisible(false);
			content.setVisible(true);
			etc.setVisible(false);
			
		}else if (obj==menu[2]) {
			//�α����� x
			//������ x
			//��Ÿ o
			loginForm.setVisible(false);
			content.setVisible(false);
			etc.setVisible(true);
		}*/
		
		/*if (obj==menu[0]) {
			//�α����� o
			//������ x
			//��Ÿ x
			page[0].setVisible(true);
			page[1].setVisible(false);
			page[2].setVisible(false);
			
		}else if (obj==menu[1]) {
			//�α����� x
			//������ o
			//��Ÿ x	
			page[0].setVisible(false);
			page[1].setVisible(true);
			page[2].setVisible(false);
			
		}else if (obj==menu[2]) {
			//�α����� x
			//������ x
			//��Ÿ o
			page[0].setVisible(false);
			page[1].setVisible(false);
			page[2].setVisible(true);
		}*/
		
		for (int i = 0; i < page.length; i++) {
			if(obj==menu[i]){			
				page[i].setVisible(true);
			}else {
				page[i].setVisible(false);
			}
		}
		
		/*for (int i = 0; i < pages.size(); i++) {
			if(obj==menu[i]){			
				pages.get(i).setVisible(true);
			}else {
				pages.get(i).setVisible(false);
			}
		}*/		
		
	}
	
	public static void main(String[] args) {
		new MainApp();
	}


}
