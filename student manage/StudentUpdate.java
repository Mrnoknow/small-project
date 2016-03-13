package student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class StudentUpdate extends JDialog implements ActionListener{
	JPanel pan=new JPanel();
	JPanel pan2=new JPanel();
	JLabel labelid=new JLabel("id");
	JLabel label;
	JLabel labelname=new JLabel("name");
	JLabel labelage=new JLabel("age");
	JLabel labelsex=new JLabel("sex");
	JButton button=new JButton("Sure!");
	JTextField textname=new JTextField(10);
	JTextField textage=new JTextField(10);
	JComboBox combobox=new JComboBox();
	String name;
	String age;
	String sex;
	StudentUpdate(JFRame frame,String update,boolean model,String id){
		super(frame,update,model);
		combobox.addItem("boy");
		combobox.addItem("girl");
		label=new JLabel(id);

		pan.setLayout(new GridLayout(4,1));

		pan.add(labelid);
		pan.add(label);

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
	}
	public String getName(){
		return name;
	}
	public String getAge(){
		return age;
	}
	public String getSex(){
		return sex;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==button){
			name=textname.getText();
			age=textage.getText();
			sex=combobox.getSelectedItem().toString();
			if(("".equals(name)||"".equals(age))==false){this.dispose();}
		}
	}
}
