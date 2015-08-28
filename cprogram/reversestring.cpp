#include<iostream>


int main() {
	char *str="lydia";
	//char *rev = reverse(str);
}

char reverse(char *str){
	char *end =str;
	char tmp;
	if(str){
		while(*end){
			++end;
			print("value of end %c" end);
		}
		--end;
	}
	return end;
}
