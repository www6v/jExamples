
#include"person.h"
#include<stdio.h>
#include<iostream>
#include<fstream>
using namespace std;
Person::Person(){

}
void Person::init(int age, const char* name_str){
	this->age=age;
	name=name_str;
}
void Person::say_info(){
	printf("name:%s \n",name);
	printf("age: %d \n",age);
}
void  Person::writeFile(const char* path_name,const char* content){
	ofstream outfile(path_name,ios::out);
		if(!outfile)
		{
			cerr<<"open error!"<<endl;
			//abort();
		}
		outfile<<content;
		outfile.close();
}


