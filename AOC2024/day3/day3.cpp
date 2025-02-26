#include <bits/stdc++.h>
using namespace std;

void solve1(string txt){
    ifstream cin(txt);
    string s;
    getline(cin,s);
    int count = 0;
    for (int i = 0; i < s.length() - 7; i++){
        // cout<<s.substr(i,4)<<endl;
        if (s.substr(i,4) == "mul("){
            string one = "";
            string two = "";
            bool valid = true;
            int tempP = i + 4;
            while(valid){
                if (tempP >= s.length()){
                    valid = false;
                    break;
                }
                if (s[tempP] >= '0' && s[tempP] <= '9'){
                    one = one + s[tempP];
                } else if (s[tempP] == ','){
                    tempP++;
                    break;
                } else{
                    valid = false;
                    break;
                }
                tempP++;
            }
            while(valid){
                if (tempP >= s.length()){
                    valid = false;
                    break;
                }
                if (s[tempP] >= '0' && s[tempP] <= '9'){
                    two = two + s[tempP];
                } else if (s[tempP] == ')'){
                    tempP++;
                    break;
                } else{
                    valid = false;
                    break;
                }
                tempP++;
            }
            if (valid){
                count+=stoi(one) * stoi(two);
            }
        }
    }
    cout<<count<<endl;
}

void solve2(string txt){
    ifstream cin(txt);
    string s;
    getline(cin,s);
    int count = 0;
    bool on = true;
    for (int i = 0; i < s.length(); i++){
        if (s.substr(i,4) == "do()"){
            on=true;
        }
        if (s.substr(i,7) == "don't()"){
            on=false;
        }
        if (s.substr(i,4) == "mul("){
            string one = "";
            string two = "";
            bool valid = true;
            int tempP = i + 4;
            while(valid){
                if (tempP >= s.length()){
                    valid = false;
                    break;
                }
                if (s[tempP] >= '0' && s[tempP] <= '9'){
                    one = one + s[tempP];
                } else if (s[tempP] == ','){
                    tempP++;
                    break;
                } else{
                    valid = false;
                    break;
                }
                tempP++;
            }
            while(valid){
                if (tempP >= s.length()){
                    valid = false;
                    break;
                }
                if (s[tempP] >= '0' && s[tempP] <= '9'){
                    two = two + s[tempP];
                } else if (s[tempP] == ')'){
                    tempP++;
                    break;
                } else{
                    valid = false;
                    break;
                }
                tempP++;
            }
            if (valid && on){
                count+=stoi(one) * stoi(two);
            }
        }
    }
    cout<<count<<endl;
}

int main(){
    cout<<"part 1a"<<endl;
    solve1("a.txt");
    cout<<"part 1b"<<endl;
    solve1("b.txt");

    cout<<"part 2a"<<endl;
    solve2("a.txt");
    cout<<"part 2b"<<endl;
    solve2("b.txt");
    return 0;
}