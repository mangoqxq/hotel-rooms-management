package hotial;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import extra.*;
import java.util.ArrayList;
public class Employsee extends JFrame
{
	private JTable table;
	private MyTableMode tablemodel;
	private JButton b1,b5;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JToolBar tool;
	private JTextField id;
	public Employsee() 
	{
		this.setSize(600,300);
		this.setTitle("预约信息表");
		this.setLocationRelativeTo(getOwner());
		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		b1 =new JButton("预约入住"); 
		b1.setFocusable(false);
		b1.setHorizontalTextPosition(SwingConstants.CENTER);
		b1.setVerticalTextPosition(SwingConstants.BOTTOM);
		  
		b2 =new JButton("修改预约");
		b2.setFocusable(false);
		b2.setHorizontalTextPosition(SwingConstants.CENTER);
		b2.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		b3=new JButton("取消预约");
		b3.setFocusable(false);
		b3.setHorizontalTextPosition(SwingConstants.CENTER);
		b3.setVerticalTextPosition(SwingConstants.BOTTOM); //创建工具栏，添加按钮
		
		b5=new JButton("退出预定");
		b5.setFocusable(false);
		b5.setHorizontalTextPosition(SwingConstants.CENTER);
		b5.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		b4 =new JButton("按身份证查询客户信息");
		b4.setFocusable(false);
		b4.setHorizontalTextPosition(SwingConstants.CENTER);
		b4.setVerticalTextPosition(SwingConstants.BOTTOM);
		id=new JTextField();
		tool=new JToolBar();
		tool=new JToolBar();
		tool.add(b1); 
		tool.add(b2);
		tool.add(b3);
		tool.add(b5);
		tool.add(b4);
		tool.add(id);
		tool.setRollover(true);//添加工具栏
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
					String sql="update resever set name=?,id=?,home_k=?,money=?,mtime=? where no=?";
					PreparedStatement presta=dbcon.PreparedStatement(sql);//获得JTable所修改的行数
					count=tablemodel.getEditedIndex().size();//获得JTable中所修改的行的数据，更新数据库
					if(count>0) 
					{ 
						for(i=0;i<count;i++) 
						{
							index=tablemodel.getEditedIndex().get(i); 
							presta.setString(1,table.getValueAt(index,1).toString());
							presta.setString(2,table.getValueAt(index,2).toString());
							presta.setString(3,table.getValueAt(index,3).toString());
							presta.setString(4,table.getValueAt(index,4).toString()); 
							presta.setString(5,table.getValueAt(index,5).toString()); 
							presta.setString(6,table.getValueAt(index,0).toString()); 
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
					{ //获得JTable中选中的序列
						int[] selRowIndexs=table.getSelectedRows(); 
						java.sql.PreparedStatement presta=  dbcon.PreparedStatement("delete from resever where no=?"); 
						for(int i=0;i<selRowIndexs.length ;i++)
						{
							presta.setString(1,table.getValueAt(selRowIndexs[i],0).toString()); 
							presta.addBatch(); 
						}
						presta.executeBatch(); //删除数据库中相应记录
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
					ResultSet rs=dbcon.executeQuery("select no 流水号,name 姓名,id 身份证,home_k 房间类型,money 钱数,mtime 可能天数  from resever where id="+"'"+id.getText()+"'");
					ResultSetMetaData rsmd=rs.getMetaData();
					int Colnum=rsmd.getColumnCount();
					for(int i=1;i<=Colnum;i++)
						tablemodel2.addColumn(rsmd.getColumnName(i));
					ArrayList<EmplyeeEnity> V=new ArrayList<EmplyeeEnity>();
					while(rs.next()) 
					{
						
							EmplyeeEnity person=new EmplyeeEnity();
							person.setNo(rs.getString("流水号"));
							person.setName(rs.getString("姓名"));
							person.setId(rs.getString("身份证"));
							person.setHome_k(rs.getString("房间类型"));
							person.setMoney(rs.getFloat("钱数"));
							person.setMtime(rs.getInt("可能天数"));
							V.add(person);
					}
					rs.close();
					for(int i=0;i<V.size();i++) 
					{
						tablemodel2.addRow(new Object[] {
								V.get(i).getNo(),V.get(i).getName(),V.get(i).getId(),
								V.get(i).getMoney(),V.get(i).getMtime()});
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
//		this.setLocationRelativeTo(getFocusOwner());
		}
	
	private MyTableMode getModel() 
	{
		MyTableMode tableModel=new MyTableMode();
		Db dbcon;
		try 
		{
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select no 流水号,name 姓名,id 身份证,home_k 房间类型,money 钱数,mtime 可能天数 from resever");
			ResultSetMetaData rsmd=rs.getMetaData();
			int Colnum=rsmd.getColumnCount();
			int i;
			for(i=1;i<=Colnum;i++)
				tableModel.addColumn(rsmd.getColumnName(i));
			ArrayList<EmplyeeEnity> v=new ArrayList<EmplyeeEnity>();
			while(rs.next()) 
			{
				EmplyeeEnity person=new EmplyeeEnity();
				person.setNo(rs.getString("流水号"));
				person.setName(rs.getString("姓名"));
				person.setId(rs.getString("身份证"));
				person.setHome_k(rs.getString("房间类型"));
				person.setMoney(rs.getFloat("钱数"));
				person.setMtime(rs.getInt("可能天数"));
				v.add(person);	
			}
			rs.close();
			for(i=0;i<=v.size();i++) 
			{
				tableModel.addRow(new Object[] {
						v.get(i).getNo(),v.get(i).getName(),v.get(i).getId(),
						v.get(i).getHome_k(),v.get(i).getMoney(),v.get(i).getMtime()
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
		Employsee w=new Employsee();
		w.setVisible(true);
	}

}