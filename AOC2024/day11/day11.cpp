#include <bits/stdc++.h>
using namespace std;
//g++ day11.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to formate
char nums[100][100];
int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
void r(ifstream cin){string s;cin>>s;for(int i=0;i<s.length();i++){nums[0][i]=s[i];}for(int i=1;i<s.length();i++){cin>>s;for(int j=0;j<s.length();j++){nums[i][j]=s[j];}}}
void sort(vector<int>v){sort(v.begin(),v.end());} void sort(vector<long long>v){sort(v.begin(),v.end());}
void solve1(string txt){
    ifstream cin(txt);
    vector<long long> v;
    long long temp;
    while(cin>>temp){
        v.push_back(temp);
    }
    for (int z = 0; z < 25; z++){
        for (long long i = 0; i < v.size(); i++){
            if (v[i] == 0){
                v[i] = 1;
            } else{
                string s = to_string(v[i]);
                if (s.length() % 2 == 0){
                    string one = s.substr(0,s.length()/2);
                    string two = s.substr(s.length()/2, s.length()-s.length()/2);
                    v[i] = stoll(one);
                    v.insert(v.begin()+i+1, stoll(two));
                    i++;
                } else{
                    v[i] *= 2024;
                }
            }
        }
    }
    cout<<v.size()<<endl;
}

map<pair<string,long long>, long long> m;
long long subProb(string s, long long i){
    if (m.find({s,i}) != m.end()){
        return m[{s,i}];
    }
    if (i == 0){
        return 1;
    }
    vector<string> nextStep;

    if (s == "0"){
        nextStep.push_back("1");
    } else if (s.length() % 2 == 0){
        nextStep.push_back(to_string(stoll(s.substr(0,s.length()/2))));
        nextStep.push_back(to_string(stoll(s.substr(s.length()/2, s.length()-s.length()/2))));
    } else{
        nextStep.push_back(to_string(stoll(s)*2024));
    }
    long long count = 0;
    for (string curr: nextStep){
        count += subProb(curr,i-1);
    }
    m[{s,i}] = count;

    return count;
}

void solve2(string txt){
    ifstream cin(txt);
    vector<string> v;
    string temp;
    while(cin>>temp){
        v.push_back(temp);
    }
    
    long long count = 0;
    for (string curr: v){
        count+= subProb(curr,75);
    }
    cout<<count<<endl;
}

int main(){
    cout<<"part 1 sample"<<endl;
    solve1("a.txt");
    cout<<"part 1 full"<<endl;
    solve1("b.txt");

    cout<<"part 2 sample"<<endl;
    solve2("a.txt");
    cout<<"part 2 full"<<endl;
    solve2("b.txt");
    return 0;
}