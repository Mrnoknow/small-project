package student;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
class JFRame extends JFrame{
	
	JPanel pan1=new JPanel();
	JPanel pan2=new JPanel();
	JLabel label=new JLabel("scanf:");
	JButton buttonadd=new JButton("add");
	JButton buttonview=new JButton("view");
	JButton buttondelete=new JButton("delete");
	JButton buttonupdate=new JButton("update");
	JButton buttonback=new JButton("goback");
	JTable table=null;
	JScrollPane scrollpane=null;
	JTextField text=new JTextField(10);
	StudentModel model;
	Operate operate=new Operate();	
	public JFRame(){
	super("welcome");
	pan1.add(label);
	pan1.add(text);
	pan1.add(buttonview);
	buttonview.addActionListener(listener);

	pan1.add(buttonback);
	buttonback.addActionListener(listener);

	pan2.add(buttonadd);
	buttonadd.addActionListener(listener);

	pan2.add(buttonupdate);
	buttonupdate.addActionListener(listener);

	pan2.add(buttondelete);
	buttondelete.addActionListener(listener);

	model=operatecompare(operate,model);
	table=new JTable(model);
	scrollpane=new JScrollPane(table);

	this.add(scrollpane);
	this.add(pan1,"North");
	this.add(pan2,"South");

	this.pack();
	this.setVisible(true);

	this.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
			System.exit(1);
		}
	});

    }
    public static StudentModel operatecompare(Operate operate,StudentModel model){
    	operate=read();
    	if(operate.head==null){
    		model=new StudentModel();
    	}else{
    		model=new StudentModel(operate);
    	}
    	return model;
    }
    public static Operate operateread(Operate operate){
    	Operate operateread=new Operate();
    	operateread=read();
    	operateread.add(operate.head.id,operate.head.age,operate.head.name,operate.head.sex);
    	write(operateread);
    	return operateread;
    }
    ActionListener listener=new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource()==buttonadd){
    			StudentAdd studentadd=new StudentAdd(null,"Add",true);
    			operate=read();
    			if(("".equals(studentadd.getOperate().head.id)||
    				"".equals(studentadd.getOperate().head.name)||	
    				operate.compare(studentadd.getOperate().head.id))==false){
    				model=new StudentModel(operateread(studentadd.getOperate()));
    				table.setModel(model);
    			}
    		}
    		if(e.getSource()==buttonview){
    			String id=text.getText();
    			operate=read();
    			model =new StudentModel(operate,id);
    			table.setModel(model);
    		}
    		if(e.getSource()==buttondelete){
    			int rownum=table.getSelectedRow();
    			String id=(String)model.getValueAt(rownum,0);
    			operate=read();
    			operate.delete(id);
    			write(operate);
    			model=new StudentModel(operate);
    			table.setModel(model);

    		}

    		if(e.getSource()==buttonupdate){
    			int rownum=table.getSelectedRow();
    			String id=(String)model.getValueAt(rownum,0);
    			StudentUpdate studentupdate=new StudentUpdate(null,"Update",true,id);
    			operate=read();
    			if(("".equals(studentupdate.getName())||"".equals(studentupdate.getAge()))==false){
    				operate.update(id,Integer.parseInt(studentupdate.getAge()),studentupdate.getName(),studentupdate.getSex()); 
    				write(operate);
    			    model=new StudentModel(operate);
    			    table.setModel(model);
    			}  			
    		}

    		if(e.getSource()==buttonback){
    			operate=read();
    			model=new StudentModel(operate);
    			table.setModel(model);
    		}
    	}
    };
    public static Operate read(){
        Operate operate=new Operate();
     	try{
     		FileInputStream StudentFile=new FileInputStream("you.txt");
     		ObjectInputStream StudentRead=new ObjectInputStream(StudentFile);
     		operate=(Operate)StudentRead.readObject();
     		StudentRead.close();
     		StudentFile.close();	
     	}catch(IOException i){
     		
     	}catch(ClassNotFoundException c){
     		
     	}return operate;
     }
    public static void write(Operate operate){
		try{
			FileOutputStream StudentFile=new FileOutputStream("you.txt");
	        ObjectOutputStream StudentWrite=new ObjectOutputStream(StudentFile);
		    StudentWrite.writeObject(operate);
		    StudentWrite.close();
		    StudentFile.close();
			}catch(IOException i){
				
			}
     }

}
