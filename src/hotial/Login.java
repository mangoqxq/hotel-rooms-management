package hotial;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Login extends JFrame
{
	private JTextField username;
	private JPasswordField password;
	private JButton login,register;
	public Login()
	{
		super();
		this.setSize(300,200);
		this.setTitle("Login");
		this.setLocationRelativeTo(getOwner());
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(3,2));
		cont.add(new JLabel("username"));
		username=new JTextField(10);
		cont.add(username);
		cont.add(new JLabel("password"));
		password=new JPasswordField(10);
		cont.add(password);
		login=new JButton("��¼");
		register=new JButton("ע��");
		cont.add(login);
		login.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String pass=new String(password.getPassword());
				if(username.getText().equals("aaa")&&pass.equals("123456789")) 
				{
					Admintor a=new Admintor();
					a.setVisible(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"�����������û��ͨ����֤");
				}
			}
		});
		cont.add(register);
	}
	
	public static void main(String[] args) 
	{
		Login w=new Login();
		w.setVisible(true);
	}

}