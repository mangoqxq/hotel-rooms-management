package hotial;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Admintor extends JFrame 
{
	private JButton rhome,zhome,b3,b4;
	public Admintor()
	{
		super();
		setTitle("管理员界面");
		this.setSize(250,250);
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(4,2));
		this.setLocationRelativeTo(getOwner());
		this.setLocationRelativeTo(getFocusOwner());
		cont.add(new JLabel("权利一："));
		rhome=new JButton("预约客房");
		cont.add(rhome);
		cont.add(new JLabel("权利二："));
		zhome=new JButton("入住客房");
		cont.add(zhome);
		cont.add(new JLabel("权利三："));
		b3=new JButton("退房");
		cont.add(b3);
		cont.add(new JLabel("权利四："));
		b4=new JButton("员工信息修改");
		cont.add(b4);
		rhome.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				Employsee esee= new Employsee();
				esee.setVisible(true); 
				dispose();
			}
		});
		zhome.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) 
			{
				Room r=new Room();
				r.setVisible(true);
				dispose();
				}});
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) 
			{
				Droproom d=new Droproom();
				d.setVisible(true);
				dispose();
				}});
		b4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) 
			{
				Employee e=new Employee();
				e.setVisible(true);
				dispose();
				}});
	}
	public static void main(String[] args) 
	{
		Admintor a=new Admintor();
		a.setVisible(true);
	}
}
