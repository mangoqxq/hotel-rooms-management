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
		this.setTitle("客户入住");
		this.setLocationRelativeTo(getFocusOwner());//居中
		//设置布局
		Container cont =getContentPane();
		cont.setLayout(new GridLayout(8,2));
		//添加组件
		cont.add(new JLabel("序号"));
		zno=new JTextField(10);
		cont.add(zno);
		//用户名
		cont.add(new JLabel("用户名"));
		name=new JTextField(10);
		cont.add(name);
		//身份证号
		cont.add(new JLabel("身份证号"));
		id=new JTextField(10);
		cont.add(id);
		//天数
		cont.add(new JLabel("天数"));
		time=new JTextField(10);
		cont.add(time);
		//房间类型
		cont.add(new JLabel("房间类型"));
		k1=new JRadioButton("标间");
		k2=new JRadioButton("豪华套房");
		ButtonGroup bg=new ButtonGroup();
		bg.add(k1);
		bg.add(k2);
		kind= new JPanel(new GridLayout(1,2));
		kind.add(k1);
		kind.add(k2);
		cont.add(kind);
		//房间号
		cont.add(new JLabel("房间号"));
		room = new JTextField();
		cont.add(room);
		//总钱数
		cont.add(new JLabel("总钱数"));
		allmoney=new JTextField(10);
		allmoney.setText("200");
		cont.add(allmoney);
		//按钮
		ok=new JButton("入住");
		cancel=new JButton("取消");
		cont.add(ok);
		cont.add(cancel);
		//监听事件
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
					 JOptionPane.showMessageDialog(null, "请输入住宿天数");
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
					   JOptionPane.showMessageDialog(null, "插入成功!");
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
