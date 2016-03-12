#include<iostream>
#include<string>
#include<fstream>
#include<math.h>
using namespace std;
struct HashMe{     //哈希表
	string word;
	int firstnumber;	
	int secondnumber;
};
void Operate();    //操作类
class HashClass{
private:
	HashMe hash[1000];   //哈希大小
	double DifferenceOfSquares(double number);//距离计算
	void InsertFirst(string word);//标识符频率哈希表的建立
	void InsertSecond(string word);//标识符频率哈希表的建立
	string DocumentHand(string file);//文件预处理，注释处理
public:
	void View();
	void Compare();   //单个单词频率比较
	void DocumentFirst();//第一个文件的标识符处理
	void DocumentSecond();//第二个文件的标识符处理
};
void HashClass::Compare(){
	double num=0;
	for(int i=0;i<1000;i++){
		num=num+DifferenceOfSquares(hash[i].firstnumber-hash[i].secondnumber);
	}
	View();
	cout<<num<<endl;
	cout<<"距离是:"<<sqrt(num)<<endl;
	if(num<=900){
		cout<<"有明显抄袭"<<endl;
	}else if(num>900&&num<=2500){
		cout<<"有抄袭痕迹"<<endl;
	}else cout<<"没有抄袭"<<endl;
}

void HashClass::View(){
	for(int i=0;i<1000;i++){
		if(hash[i].word!=""){
			cout<<hash[i].word<<"       "<<hash[i].firstnumber<<"       "<<hash[i].secondnumber<<endl;
		}
	}
}

void HashClass::DocumentFirst(){
	string onefile;
	cout<<"请输入第一个文件地址："<<endl;
	cin>>onefile;
	string String="";
	char buffer;
	string message=DocumentHand(onefile);
	int i=0;
	while(message[i]!='\0'){   //提取标识符
		buffer=message[i];
		
		
		
		/*	if(buffer=='	'||buffer==47||buffer==40||buffer==41||buffer==32||buffer==10||buffer==59||buffer==60||buffer==62||buffer==123||buffer==125){
		if(String!=""){
		hash.InsertFirst(String);
		}
		String="";
		}else{
		String=String+buffer;
		}
		*/
		
		if((buffer>=97&&buffer<=122)||(buffer>=65&&buffer<=90)){
			String=String+buffer;
		}
		else{
			if(String!=""){
				InsertFirst(String);
			}
			String="";
		}
		i++;
		
	}
	
	
}

void HashClass::DocumentSecond(){
	string twofile;
	cout<<"请输入第二个文件地址："<<endl;
	cin>>twofile;
	char buffer;
	string String="";
	string message=DocumentHand(twofile);
	int i=0;
	while(message[i]!='\0'){
		buffer=message[i];
		
		/*	if(buffer=='	'||buffer==47||buffer==40||buffer==41||buffer==32||buffer==10||buffer==59||buffer==60||buffer==62||buffer==123||buffer==125){
		
		  if(String!=""){
		  hash.InsertSecond(String);
		  }
		  String="";
		  }else{
		  String=String+buffer;
	}*/
		
		
		if((buffer>=97&&buffer<=122)||(buffer>=65&&buffer<=90)){
			String=String+buffer;
		}
		else{
			if(String!=""){
				InsertSecond(String);
			}
			String="";
		}	
		i++;
		
	}
	
}


string HashClass::DocumentHand(string file){
	ifstream out;
	out.open(&file[0]);
	while(!out.is_open()){
		string onefile;
		out.clear();
		cout<<"没有该文件,请重新输入:"<<endl;
		cin>>onefile;
		out.open(&onefile[0]);
	}
	char buffertwo;
	string String="";
	char buffer;
	buffertwo=out.get();
	while(!out.eof()){	//去除注释
		buffer=buffertwo;
		buffertwo=out.get();
		if(buffer==34){
			buffer=buffertwo;
			while(buffer!=34&&!out.eof()){
				String=String+buffer;
				buffer=out.get();
			}
			buffer=' ';
			buffertwo=' ';
		}
		if(buffer=='/'&&buffertwo=='/'){
			while(buffer!=10&&!out.eof()){
				buffer=out.get();
			}
			buffer=' ';
			buffertwo=' ';
		}
		if(buffer=='/'&&buffertwo=='*'){
			buffer=out.get();
			buffertwo=out.get();
			while(buffer!='*'&&buffertwo!='/'){
				buffer=buffertwo;
			    buffertwo=out.get();
			}
			buffer=' ';
			buffertwo=' ';
		}
		String=String+buffer;
	}
	out.close();
	return String;
}


double HashClass::DifferenceOfSquares(double number){
	return number*number;
}
void HashClass::InsertFirst(string word){
	int length=word.length();//映射函数
	int number=0;
	while(length){
		length--;
		number=number+word[length];
	}
	number=number*19%1000;
	if(hash[number].word[0]!='\0'){ //标识符的频率添加与建立
		while(hash[number].word!=word&&hash[number].word[0]!='\0'){
			number++;
		}
		if(hash[number].word==word)
			hash[number].firstnumber++;
		else {
			hash[number].word=word;
			hash[number].firstnumber=1;
			hash[number].secondnumber=0;
		}
	}else{
		hash[number].word=word;
		hash[number].firstnumber=1;
		hash[number].secondnumber=0;
		
	}
	//cout<<number<<"   ";
	//cout<<hash[number].firstnumber<<"   ";
//cout<<hash[number].word<<endl;
}
void HashClass::InsertSecond(string word){
	int length=word.length();
	int number=0;
	while(length){
		length--;
		number=number+word[length];
	}
	number=number*19%1000;
	if(hash[number].word[0]!='\0'){
		while(hash[number].word!=word&&hash[number].word[0]!='\0'){
			number++;
		}
		if(hash[number].word==word)
			hash[number].secondnumber++;
		else {
			hash[number].word=word;
			hash[number].secondnumber=1;
			hash[number].firstnumber=0;
		}
	}else{
		hash[number].word=word;
		hash[number].secondnumber=1;
		hash[number].firstnumber=0;
	}
	//cout<<number<<"  ";
//	cout<<hash[number].secondnumber<<"  ";
	//cout<<hash[number].word<<endl;
	
}
	
int main(){
	Operate();	
	return 0;
}
void Operate(){
	HashClass hash;
	hash.DocumentFirst();
	hash.DocumentSecond();
	hash.Compare();
}