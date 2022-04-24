package hotial;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

//import extra.selectroom;
public class Room extends JFrame
{
	 private JTextField name,id,zno,allmoney,time,room;
	 private JButton ok,cancel;
	 private JRadioButton k1,k2;
	 private JPanel kind;
	public Room()
	{
		super();
		this.setSize(350,300);
		this.setTitle("�ͻ���ס");
		this.setLocationRelativeTo(getFocusOwner());//����
		//���ò���
		Container cont =getContentPane();
		cont.setLayout(new GridLayout(8,2));
		//������
		cont.add(new JLabel("���"));
		zno=new JTextField(10);
		cont.add(zno);
		//�û���
		cont.add(new JLabel("�û���"));
		name=new JTextField(10);
		cont.add(name);
		//���֤��
		cont.add(new JLabel("���֤��"));
		id=new JTextField(10);
		cont.add(id);
		//����
		cont.add(new JLabel("����"));
		time=new JTextField(10);
		cont.add(time);
		//��������
		cont.add(new JLabel("��������"));
		k1=new JRadioButton("���");
		k2=new JRadioButton("�����׷�");
		ButtonGroup bg=new ButtonGroup();
		bg.add(k1);
		bg.add(k2);
		kind= new JPanel(new GridLayout(1,2));
		kind.add(k1);
		kind.add(k2);
		cont.add(kind);
		//�����
		cont.add(new JLabel("�����"));
		room = new JTextField();
		cont.add(room);
		//��Ǯ��
		cont.add(new JLabel("��Ǯ��"));
		allmoney=new JTextField(10);
		allmoney.setText("200");
		cont.add(allmoney);
		//��ť
		ok=new JButton("��ס");
		cancel=new JButton("ȡ��");
		cont.add(ok);
		cont.add(cancel);
		//�����¼�
		k1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) 
			{
				int i=Integer.parseInt(time.getText());
				 String str;
				if(k1.isSelected()?true:false)
				   {
					 i=i*150;
					 str=""+i;
					 allmoney.setText(str);
				   }
			}
		});
		k2.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent evt) {
				 try
				 {
					 int i=Integer.parseInt(time.getText());
					 String str;
					 if(k2.isSelected()?true:false)
					   {
						 i=i*300;
						 str=""+i;
						 allmoney.setText(str);
					   }
				 }
				 catch(Exception ex)
				 {
					 JOptionPane.showMessageDialog(null, "������ס������");
				 }
			 }
		});
		ok.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent evt) {
				   Db dbcon=new Db();
				   try {
					   String sql="insert into resiger values(?,?,?,?,?,?,?)";
					   PreparedStatement prestate=dbcon.PreparedStatement(sql);
					   prestate.setString(1, zno.getText());
					   prestate.setString(2, name.getText());
					   prestate.setString(3, id.getText());
					   prestate.setString(4, time.getText());
					   prestate.setString(5,allmoney.getText());
					   prestate.setString(6,(k1.isSelected()?k1.getText():k2.getText()));
					   prestate.setString(7,room.getText());
					   prestate.executeUpdate();
					   JOptionPane.showMessageDialog(null, "����ɹ�!");
					   dispose();
				   
			   }
				   catch(SQLException e) {
					   System.out.println(e.toString());
				   }
		   }
		   });
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Admintor a=new Admintor();
				a.setVisible(true);
				dispose();
			}
		});
	}
	public static void main(String[] args) 
	{
		Room r=new Room();
		r.setVisible(true);
	}
}
