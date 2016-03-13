package student;

import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;
class StudentModel extends AbstractTableModel{
	Vector rowDate,columnNames;
	StudentModel(){
		columnNames=new Vector();
		columnNames.add("id");
		columnNames.add("name");
		columnNames.add("age");
		columnNames.add("sex");
		rowDate =new Vector();
		Vector vector=new Vector();
		vector.add(" ");
		vector.add(" ");
		vector.add(" ");
		vector.add(" ");
		rowDate.add(vector);
	}
	StudentModel(Operate operate){
		columnNames=new Vector();
		columnNames.add("id");
		columnNames.add("name");
		columnNames.add("age");
		columnNames.add("sex");
		rowDate =new Vector();
		Vector vector;
		while(operate.head!=null){
			vector=new Vector();
			vector.add(operate.head.id);
			vector.add(operate.head.name);
			vector.add(operate.head.age);
			vector.add(operate.head.sex);
			rowDate.add(vector);
			operate.head=operate.head.next;
		}
	}
	StudentModel (Operate operate,String id){
		columnNames=new Vector();
		columnNames.add("id");
		columnNames.add("name");
		columnNames.add("age");
		columnNames.add("sex");
		rowDate =new Vector();
		Vector vector=new Vector();
		vector.add(operate.view(id).id);
		vector.add(operate.view(id).name);
		vector.add(operate.view(id).age);
		vector.add(operate.view(id).sex);
		rowDate.add(vector);
	}
	public Vector getColumnNames(){
		return this.columnNames;
	}
	public Vector getRowDate(){
		return this.rowDate;
	}
	public int getColumnCount(){
		return this.columnNames.size();
		
	}
	public int getRowCount(){
		return this.rowDate.size();
		
	}
	public String getColumnName(int clomn){
		return (String)this.columnNames.get(clomn);
		
	}
	public Object getValueAt(int row,int column){
		return ((Vector)this.rowDate.get(row)).get(column);

	}
}
