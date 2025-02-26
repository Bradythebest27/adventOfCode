#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;
//g++ day19.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
char nums[200][200];
int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
int r(string t){ifstream cin(t);string s;cin>>s;for(int i=0;i<s.length();i++){nums[0][i]=s[i];}for(int i=1;i<s.length();i++){cin>>s;for(int j=0;j<s.length();j++){nums[i][j]=s[j];}}return s.length();}
void sort(vector<int>v){sort(v.begin(),v.end());}void sort(vector<long long>v){sort(v.begin(),v.end());}
set<string> se;
map<string,long long> cache;
long long works(string s){
    if (s.length() == 0){
        return 1;
    }
    long long w = 0;
    if (cache.find(s) != cache.end()){
        return cache[s];
    }
    for (int i = 1; i < 10 && i <= s.length(); i++){
        if (se.find(s.substr(0,i)) != se.end()){
            if (i == s.length()){
                w+=1;
            } else
            w = w + works(s.substr(i));
        }
    }
    cache[s] = w;
    return w;
}

void solve1(string txt, string txt2){
    se.clear();
    cache.clear();
    ifstream cin1(txt);ifstream cin2(txt2);
    string s;
    while (cin1>>s){
        s.pop_back();
        se.insert(s);
    }
    long long count = 0;
    while (cin2>>s){
        count+=(works(s)>0);
    }
    cout<<count<<endl;
}

void solve2(string txt, string txt2){
    // ifstream cin(txt);
    se.clear();
    cache.clear();
    ifstream cin1(txt);ifstream cin2(txt2);
    string s;
    while (cin1>>s){
        s.pop_back();
        se.insert(s);
    }
    long long count = 0;
    while (cin2>>s){
        count+=works(s);
    }
    cout<<count<<endl;
}

int main(){
    cout<<"part 1 sample"<<endl;
    solve1("a.txt", "b.txt");
    cout<<"part 1 full"<<endl;
    solve1("c.txt", "d.txt");

    cout<<"part 2 sample"<<endl;
    solve2("a.txt", "b.txt");
    cout<<"part 2 full"<<endl;
    solve2("c.txt", "d.txt");
    return 0;
}