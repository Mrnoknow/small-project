package student;
class Operate implements java.io.Serializable{
	Student head;
	class Student implements java.io.Serializable{
	    String id;
		int age;
		String name;
		String sex;
		Student next;
		Student(String id,int age,String name,String sex){
			this.id=id;
			this.age=age;
			this.name=name;
			this.sex=sex;
		}
		public void add(Student student){
			if(this.next==null){
				this.next=student;
			}else{
				this.next.add(student);
			}
		}
		public Student view(String id){
			if(this.id.equals(id)){				
			     return this;
			}else{
				if(this.next!=null){
					 this.next.view(id);
				}
			}return this.next.view(id);
		}
		public void delete(Student student,String id){
	    	if(this.id.equals(id)){	
	    		student.next=this.next;
	    	}else{
	    		if(this.next!=null){
	    			this.next.delete(this,id);
	    		}
	    	}
	    }
	    public void update( String id,int age,String name,String sex ){
	    	if(this.id.equals(id)){
	    		this.id=id;
	    		this.age=age;
	    		this.name=name;
	    		this.sex=sex;
	    	}else{
	    		if(this.next!=null){
	    			this.next.update(id,age,name,sex);
	    		}
	    	}
	    }
    }
	public void add(String id,int age,String name,String sex){
		Student student=new Student(id,age,name,sex);
		if(this.head==null){
			this.head=student;
		}else{
			this.head.add(student);
		}
	}
	public Student view(String id){	
			return this.head.view(id);
	}
	public void delete(String id){
			if(this.head.id.equals(id)){
				this.head=this.head.next;
			}else{
				this.head.delete(head,id);
			}			
	}
	public void update(String id,int age,String name,String sex){
			this.head.update(id,age,name,sex);
	}
	public boolean compare(String id){
	    while(this.head!=null){
	    	if(this.head.id.equals(id)){
	    		return true;
	    	}
	    	this.head=this.head.next;
	    }
	    return false;
    }
}
