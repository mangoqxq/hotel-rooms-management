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
	   this.setTitle("ס��ԤԼ");
	   this.setLocationRelativeTo(getFocusOwner());//����
	   //���ò���
	   Container cont =getContentPane();
	   cont.setLayout(new GridLayout(7,2));
	   //������
	   cont.add(new JLabel("���"));
	   gno=new JTextField(10);
	   cont.add(gno);
	   //�û���
	   cont.add(new JLabel("�û���"));
	   name=new JTextField(10);
	   cont.add(name);
	   //���֤��
	   cont.add(new JLabel("���֤��"));
	   id=new JTextField(10);
	   cont.add(id);
	 //��������
	   cont.add(new JLabel("��������"));
	   rk1=new JRadioButton("���",true);
	   rk2=new JRadioButton("�����׷�");
	   ButtonGroup bg1=new ButtonGroup();
	   bg1.add(rk1);
	   bg1.add(rk2);
	   rk= new JPanel(new GridLayout(1,2));
	   rk.add(rk1);
	   rk.add(rk2);
	   cont.add(rk);
	   //Ԥ������
	   cont.add(new JLabel("Ԥ������"));
	   mtime1=new JRadioButton("3",true);
	   mtime2=new JRadioButton("7");
	   ButtonGroup bg=new ButtonGroup();
	   bg.add(mtime1);
	   bg.add(mtime2);
	   mtimetoo= new JPanel(new GridLayout(1,2));
	   mtimetoo.add(mtime1);
	   mtimetoo.add(mtime2);
	   cont.add(mtimetoo);
	   //Ǯ��
	   cont.add(new JLabel("Ѻ��"));
	   money=new JTextField(10);
	   cont.add(money);
	   money.setText("300");
	   //��ť
	   ok=new JButton("Ԥ��");
	   cancel=new JButton("ȡ��");
	   cont.add(ok);
	   cont.add(cancel);
	   //�����¼�
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
	   ///ע�������
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
				   JOptionPane.showMessageDialog(null, "����ɹ�!");
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