
#include<string.h>
#ifndef PERSON_H_
#define PERSON_H_

class Person{
private:
	int age;
	const char* name;
public:
	Person();
	void init(int,const char*);
	void say_info();
	void writeFile(const char* path_name,const char* content);
};


#endif /* PERSON_H_ */
