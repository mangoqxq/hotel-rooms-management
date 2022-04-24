package hotial;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import extra.*;
import java.util.ArrayList;
public class Droproom extends JFrame
{
	private JTable table;
	private MyTableMode tablemodel;
	private JButton b1,b2;
	private JToolBar tool;
	public Droproom()
	{
		this.setSize(600,300);
		this.setTitle("�˷���Ϣ��");
		this.setLocationRelativeTo(getOwner());
		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		b1 =new JButton("�˷�"); 
		b1.setFocusable(false);
		b1.setHorizontalTextPosition(SwingConstants.CENTER);
		b1.setVerticalTextPosition(SwingConstants.BOTTOM);
		b2 =new JButton("�˳�"); 
		b2.setFocusable(false);
		b2.setHorizontalTextPosition(SwingConstants.CENTER);
		b2.setVerticalTextPosition(SwingConstants.BOTTOM);
		tool=new JToolBar();
		tool.add(b1);
		tool.add(b2);
		tool.setRollover(true);
		getContentPane().add(tool,BorderLayout.NORTH);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{ 
				Db dbcon=new Db(); 
				try
				{
					if(table.getSelectedRows().length>0) 
					{ //���JTable��ѡ�е�����
						int[] selRowIndexs=table.getSelectedRows(); 
						java.sql.PreparedStatement presta=  dbcon.PreparedStatement("delete from resiger where zno=?"); 
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
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Admintor a=new Admintor();
				a.setVisible(true);
				dispose();
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
			ResultSet rs=dbcon.executeQuery("select zno ��ˮ��,id ���֤,name ����,time ����,allmoney ��Ǯ��,home_k ��������,home_no ����� from resiger");
			ResultSetMetaData rsmd=rs.getMetaData();
			int Colnum=rsmd.getColumnCount();
			int i;
			for(i=1;i<=Colnum;i++)
				tableModel.addColumn(rsmd.getColumnName(i));
			ArrayList<ResigerE> v=new ArrayList<ResigerE>();
			while(rs.next()) 
			{
				ResigerE person=new ResigerE();
				person.setZno(rs.getString("��ˮ��"));
				person.setId(rs.getString("���֤"));
				person.setName(rs.getString("����"));
				person.setTime(rs.getInt("����"));
				person.setAllmoney(rs.getFloat("��Ǯ��"));
				person.setHome_k(rs.getString("��������"));
				person.setHome_no(rs.getString("�����"));
				v.add(person);	
			}
			rs.close();
			for(i=0;i<=v.size();i++) 
			{
				tableModel.addRow(new Object[] {
						v.get(i).getZno(),v.get(i).getId(),v.get(i).getName(),
						v.get(i).getTime(),v.get(i).getAllmoney(),v.get(i).getHome_k(),v.get(i).getHome_no()
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
		Droproom d=new Droproom();
		d.setVisible(true);
	}
}
