#include <iostream>
#include <fstream>
#include <vector>
using namespace std;
int startOfWord(string s, int i){
  static vector<string> nums={"one","two","three","four","five","six","seven","eight","nine"};
  for (int j=0; j<9; j++){
    if (s.length()-i>=nums[j].length()){
      bool same=true;
      for (int k=0; k<nums[j].length(); k++){
	if (nums[j][k]!=s[i+k]){
	  same=false;
	}
      }
      if (same){
	//cout<<s<<" "<<i<<nums[j]<<endl;
	return 10*(j+1);
      }
    } 
  }
  return 0;
}
int endOfWord(string s, int i){
  static vector<string> nums={"one","two","three","four","five","six","seven","eight","nine"};
  for (int j=0; j<9; j++){
    if (nums[j].length()<=s.length()-i){
      bool same=true;
      for (int k=i; k<i+nums[j].length();k++){
        if (nums[j][k-i]!=s[k]){
          same=false;
        }
      }
      if (same){
	//cout<<s<<" "<<i<<" "<<nums[j]<<endl;
        return j+1;
      }
    }
  }
  return 0;
}
int main(){
  ifstream input;
  input.open("input");
  string s;
  int first=-1;
  int last=-1;
  int total=0;
  while (input>>s){
    for (int i=0; i<s.length()&&first<0; i++){
      if (s[i]>='1'&&s[i]<='9'){
	first=(s[i]-'1'+1)*10;
      }
      int temp=startOfWord(s,i);
      if(temp>0){
	first=temp;
      }
    }
    for (int i=s.length()-1; i>=0&&last<0; i--){
      if (s[i]>='1'&&s[i]<='9'){
        last=s[i]-'1'+1;
      }
      int temp=endOfWord(s,i);
      if (temp>0){
	last=temp;
      }
    }
    if (first>=0&&last>=0){
      //cout<<first<<" "<<last<<" "<<s<<endl;
      total+=first+last;
    }else{
      cout<<"error "<<s<<" "<<first<<" "<<last<<endl;
    }
    first=-1;
    last=-1;
  }
  cout<<total<<endl;
  return 0;
}
