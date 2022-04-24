package hotial;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class Guestadd extends JFrame{
   private JTextField name,id,money,gno;
   private JRadioButton mtime1,mtime2,rk1,rk2;
   private JPanel mtimetoo,rk;
   private JButton ok,cancel;
   public Guestadd() {
	   super();
	   this.setSize(350,300);
	   this.setTitle("住房预约");
	   this.setLocationRelativeTo(getFocusOwner());//居中
	   //设置布局
	   Container cont =getContentPane();
	   cont.setLayout(new GridLayout(7,2));
	   //添加组件
	   cont.add(new JLabel("序号"));
	   gno=new JTextField(10);
	   cont.add(gno);
	   //用户名
	   cont.add(new JLabel("用户名"));
	   name=new JTextField(10);
	   cont.add(name);
	   //身份证号
	   cont.add(new JLabel("身份证号"));
	   id=new JTextField(10);
	   cont.add(id);
	 //房间类型
	   cont.add(new JLabel("房间类型"));
	   rk1=new JRadioButton("标间",true);
	   rk2=new JRadioButton("豪华套房");
	   ButtonGroup bg1=new ButtonGroup();
	   bg1.add(rk1);
	   bg1.add(rk2);
	   rk= new JPanel(new GridLayout(1,2));
	   rk.add(rk1);
	   rk.add(rk2);
	   cont.add(rk);
	   //预计天数
	   cont.add(new JLabel("预计天数"));
	   mtime1=new JRadioButton("3",true);
	   mtime2=new JRadioButton("7");
	   ButtonGroup bg=new ButtonGroup();
	   bg.add(mtime1);
	   bg.add(mtime2);
	   mtimetoo= new JPanel(new GridLayout(1,2));
	   mtimetoo.add(mtime1);
	   mtimetoo.add(mtime2);
	   cont.add(mtimetoo);
	   //钱数
	   cont.add(new JLabel("押金"));
	   money=new JTextField(10);
	   cont.add(money);
	   money.setText("300");
	   //按钮
	   ok=new JButton("预定");
	   cancel=new JButton("取消");
	   cont.add(ok);
	   cont.add(cancel);
	   //监听事件
	   rk2.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent evt) {
			   if(rk2.isSelected()?true:false)
			   {
				   if(mtime1.isSelected()?true:false) 
				   {
					   money.setText("900");
				   }
				   else
				   {
					   money.setText("2100");
				   }
			   }
			   }
		   });
	   mtime2.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent evt) {
			   if(rk1.isSelected()?true:false) 
			   {
				   money.setText("700");
			   }
			   else
			   {
				   money.setText("2100");
			   }
		   }});
	   ///注册监听器
	   ok.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent evt) {
			   Db dbcon=new Db();
			   try {
				   String sql="insert into resever values(?,?,?,?,?,?)";
				   PreparedStatement prestate=dbcon.PreparedStatement(sql);
				   prestate.setString(1, gno.getText());
				   prestate.setString(2, name.getText());
				   prestate.setString(3, id.getText());
				   prestate.setString(4, (rk1.isSelected()?rk1.getText():rk2.getText()));
				   prestate.setString(5,money.getText());
				   prestate.setString(6,(mtime1.isSelected()?mtime1.getText():mtime2.getText()));
				   prestate.executeUpdate();
				   JOptionPane.showMessageDialog(null, "插入成功!");
				   dispose();
				   Employsee esee=new Employsee();
				   esee.setVisible(true);   
			   
		   }
			   catch(SQLException e) {
				   System.out.println(e.toString());
			   }
	   }
	   });
	   cancel.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent evt) {
			   Employsee esee=new Employsee();
			   esee.setVisible(true); 
				dispose();
		   }});
	   }
	   public static void main(String[] args) {
		   
	   Guestadd g=new Guestadd();
	   g.setVisible(true);
	   
	   
   }
}