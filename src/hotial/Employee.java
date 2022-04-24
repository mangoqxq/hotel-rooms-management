package hotial;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import extra.*;
import java.util.ArrayList;
public class Employee extends JFrame
{
	private JTable table;
	private MyTableMode tablemodel;
	private JButton b1,b5;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JToolBar tool;
	private JTextField name;
	public Employee()
	{
		this.setSize(600,300);
		this.setTitle("Ա����Ϣ��");
		this.setLocationRelativeTo(getOwner());
		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		b1 =new JButton("���Ա��"); 
		b1.setFocusable(false);
		b1.setHorizontalTextPosition(SwingConstants.CENTER);
		b1.setVerticalTextPosition(SwingConstants.BOTTOM);
		  
		b2 =new JButton("�޸�Ա����Ϣ");
		b2.setFocusable(false);
		b2.setHorizontalTextPosition(SwingConstants.CENTER);
		b2.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		b3=new JButton("����Ա��");
		b3.setFocusable(false);
		b3.setHorizontalTextPosition(SwingConstants.CENTER);
		b3.setVerticalTextPosition(SwingConstants.BOTTOM); //��������������Ӱ�ť
		
		b5=new JButton("����");
		b5.setFocusable(false);
		b5.setHorizontalTextPosition(SwingConstants.CENTER);
		b5.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		b4 =new JButton("������ѯ");
		b4.setFocusable(false);
		b4.setHorizontalTextPosition(SwingConstants.CENTER);
		b4.setVerticalTextPosition(SwingConstants.BOTTOM);
		name=new JTextField();
		tool=new JToolBar();
		tool=new JToolBar();
		tool.add(b1); 
		tool.add(b2);
		tool.add(b3);
		tool.add(b5);
		tool.add(b4);
		tool.add(name);
		tool.setRollover(true);//��ӹ�����
		getContentPane().add(tool,BorderLayout.NORTH);
		b1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Guestadd gadd= new Guestadd();
				gadd.setVisible(true); 
				dispose();
			}
			});
		b2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				int i,index=0,count; 
				Db dbcon= new Db();
				if(table.getCellEditor()!=null) {
					table.getCellEditor().stopCellEditing(); 
				}
				try 
				{
					String sql="update Employee set name=?,e_phone=?,professor=?,home_no=? where no=?";
					PreparedStatement presta=dbcon.PreparedStatement(sql);//���JTable���޸ĵ�����
					count=tablemodel.getEditedIndex().size();//���JTable�����޸ĵ��е����ݣ��������ݿ�
					if(count>0) 
					{ 
						for(i=0;i<count;i++) 
						{
							index=tablemodel.getEditedIndex().get(i); 
							presta.setString(1,table.getValueAt(index,1).toString());
							presta.setString(2,table.getValueAt(index,2).toString());
							presta.setString(3,table.getValueAt(index,3).toString());
							presta.setString(4,table.getValueAt(index,4).toString()); 
							presta.setString(5,table.getValueAt(index,0).toString());
							presta.addBatch();
						}
					}
					presta.executeBatch(); 
				}
				catch(SQLException sqle) 
				{
					System.out.println(sqle.toString()); 
					} 
				} 
			});
		b3.addActionListener(new  ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{ 
				Db dbcon=new Db(); 
				try
				{
					if(table.getSelectedRows().length>0) 
					{ //���JTable��ѡ�е�����
						int[] selRowIndexs=table.getSelectedRows(); 
						java.sql.PreparedStatement presta=  dbcon.PreparedStatement("delete from Employee where no=?"); 
						for(int i=0;i<selRowIndexs.length ;i++)
						{
							presta.setString(1,table.getValueAt(selRowIndexs[i],0).toString()); 
							presta.addBatch(); 
						}
						presta.executeBatch(); //ɾ�����ݿ�����Ӧ��¼
						tablemodel=getModel();
						table.setModel(tablemodel); 
					} 
				}
				catch(SQLException sqle) 
				{ 
					System.out.println(sqle.toString());
				}
				}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				Admintor ad=new Admintor();
				ad.setVisible(true);
				dispose();
			}
		});
		b4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				MyTableMode tablemodel2=new MyTableMode();
				Db dbcon;
				try 
				{
					dbcon=new Db();
					ResultSet rs=dbcon.executeQuery("select no ��ˮ��,name ����,e_phone Ա���ֻ���,professor ְ��,home_no �ֻ��� from Employee where name="+"'"+name.getText()+"'");
					ResultSetMetaData rsmd=rs.getMetaData();
					int Colnum=rsmd.getColumnCount();
					for(int i=1;i<=Colnum;i++)
						tablemodel2.addColumn(rsmd.getColumnName(i));
					ArrayList<Esee> V=new ArrayList<Esee>();
					while(rs.next()) 
					{
							Esee person=new Esee();
							person.setNo(rs.getString("��ˮ��"));
							person.setName(rs.getString("����"));
							person.setE_phone(rs.getString("�ֻ���"));
							person.setProfessor(rs.getString("ְ��"));
							person.setHome_no(rs.getString("�ֻ���"));
							V.add(person);
					}
					rs.close();
					for(int i=0;i<V.size();i++) 
					{
						tablemodel2.addRow(new Object[] {
								V.get(i).getNo(),V.get(i).getName(),V.get(i).getE_phone(),
								V.get(i).getProfessor(),V.get(i).getHome_no()});
					}
					dbcon.closeConn();
					table.setModel(tablemodel2);
				}
				catch (SQLException sqle) 
				{
					System.out.println(sqle.toString());
				}
				catch(Exception e) 
				{
					System.out.println(e.getMessage());
				}
			}
		});
	}
		

	private MyTableMode getModel() 
	{
		MyTableMode tableModel=new MyTableMode();
		Db dbcon;
		try 
		{
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select no ��ˮ��,name ����,e_phone �ֻ���,professor ְ��,home_no ����� from employee");
			ResultSetMetaData rsmd=rs.getMetaData();
			int Colnum=rsmd.getColumnCount();
			int i;
			for(i=1;i<=Colnum;i++)
				tableModel.addColumn(rsmd.getColumnName(i));
			ArrayList<Esee> v=new ArrayList<Esee>();
			while(rs.next()) 
			{
				Esee person=new Esee();
				person.setNo(rs.getString("��ˮ��"));
				person.setName(rs.getString("����"));
				person.setE_phone(rs.getString("�ֻ���"));
				person.setProfessor(rs.getString("ְ��"));
				person.setHome_no(rs.getString("�����"));
				v.add(person);	
			}
			rs.close();
			for(i=0;i<=v.size();i++) 
			{
				tableModel.addRow(new Object[] {
						v.get(i).getNo(),v.get(i).getName(),v.get(i).getE_phone(),
						v.get(i).getProfessor(),v.get(i).getHome_no()
						});
				}
			dbcon.closeConn();
		}
		catch(SQLException sqle) {
			System.out.println(sqle.toString());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());}
		return tableModel;
	}
	public static void main(String[] args) 
	{
		Employee e=new Employee();
		e.setVisible(true);
	}
}
