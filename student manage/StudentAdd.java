package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class StudentAdd extends JDialog implements ActionListener{
	JPanel pan=new JPanel();
	JPanel pan2=new JPanel();
	JLabel labelid=new JLabel("id");
	JLabel labelname=new JLabel("name");
	JLabel labelage=new JLabel("age");
	JLabel labelsex=new JLabel("sex");
	JButton button=new JButton("Sure!");
	JTextField textid=new JTextField(10);
	JTextField textname=new JTextField(10);
	JTextField textage=new JTextField(10);
	JComboBox combobox=new JComboBox();
	Operate operate=new Operate();
	//JFrame frame;
	StudentAdd(){

	};
	StudentAdd(JFRame frame,String add,boolean model){
		//this.frame=frame;
		super(frame,add,model);
		combobox.addItem("boy");
		combobox.addItem("girl");

		pan.setLayout(new GridLayout(4,1));

		pan.add(labelid);
		pan.add(textid);

		pan.add(labelname);
		pan.add(textname);

		pan.add(labelage);
		pan.add(textage);

        pan.add(labelsex);
        pan.add(combobox);

		pan2.add(button);
        button.addActionListener(this);

		this.add(pan);
		this.add(pan2,"South");
		this.pack();
		this.setVisible(true);
	};
	public Operate getOperate(){
		return operate;
	}
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==button){
				String id=textid.getText();
				String name=textname.getText();
				String age=textage.getText();
				int Age=Integer.parseInt(age);
				String sex=combobox.getSelectedItem().toString();
				operate.add(id,Age,name,sex);
				if(("".equals(name)||"".equals(id))==false)
				{this.dispose();}
		}
	}
}
