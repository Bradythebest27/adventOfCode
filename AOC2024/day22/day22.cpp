#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;
//g++ day22.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
char nums[200][200];
int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
int r(string t){ifstream cin(t);string s;cin>>s;for(int i=0;i<s.length();i++){nums[0][i]=s[i];}for(int i=1;i<s.length();i++){cin>>s;for(int j=0;j<s.length();j++){nums[i][j]=s[j];}}return s.length();}
void sort(vector<int>v){sort(v.begin(),v.end());}void sort(vector<long long>v){sort(v.begin(),v.end());}

long long mix(long long giv, long long sec){
    return giv^sec;
}
long long prune(long long sec){
    return sec%16777216;
}
void solve1(string txt){
    ifstream cin(txt);
    long long count = 0;
    long long sec;
    while (cin>>sec){
        for (int i = 0; i < 2000; i++){
            sec = prune(mix(sec*64,sec));
            sec = prune(mix((int)(sec/32),sec));
            sec = prune(mix(2048*sec,sec));
        }
        count+=sec;
    }
    cout<<count<<endl;
}

void solve2(string txt){
    ifstream cin(txt);
    long long count = 0;
    long long l;
    vector<vector<int>> monk(1540);
    vector<vector<int>> dif(1540); //0 index is empty 
    while (cin>>l){
        vector<int> difTemp(2000, 0);
        vector<int> monkTemp;
        long long sec = l;
        for (int i = 0; i < 2000; i++) {
            sec = prune(mix(sec*64, sec));
            sec = prune(mix((int)(sec/32), sec));
            sec = prune(mix(2048*sec, sec));
            monkTemp.push_back(sec%10);
        }
        for (int i = 0; i < monkTemp.size() - 1; i++) {
            difTemp[i + 1] = monkTemp[i + 1] - monkTemp[i];
        }
        monk[count] = monkTemp;
        dif[count] = difTemp;
        count++;
    }
    map<pair<int,tuple<int,int,int,int>>,int> m;
    for (int i = 0; i < 1540; i++){
        set<tuple<int,int,int,int>> seen;
        for (int j = 5; j < dif[i].size(); j++){
            if (seen.find({dif[i][j], dif[i][j - 1], dif[i][j - 2], dif[i][j - 3]}) == seen.end()){
                m[{i, {dif[i][j], dif[i][j - 1], dif[i][j - 2], dif[i][j - 3]}}] = monk[i][j];
                seen.insert({dif[i][j], dif[i][j - 1], dif[i][j - 2], dif[i][j - 3]});
            }
        }
    }
    set<tuple<int,int,int,int>> tried;
    long long maximum = -1;
    for (int i = 0; i < 1540; i++){
        for (int j = 5; j < dif[i].size(); j++){
            if (tried.find({dif[i][j], dif[i][j - 1], dif[i][j - 2], dif[i][j - 3]}) != tried.end()){
                continue;
            }
            tried.insert({dif[i][j], dif[i][j - 1], dif[i][j - 2], dif[i][j - 3]});
            long long sum = 0;
            for (int i1 = 0; i1 < 1540; i1++){
                if (m.find({i1,{dif[i][j], dif[i][j - 1], dif[i][j - 2], dif[i][j - 3]}}) != m.end()){
                    sum+=m[{i1,{dif[i][j], dif[i][j - 1], dif[i][j - 2], dif[i][j - 3]}}];
                }
            }
            maximum = maximum > sum ? maximum : sum;
        }
    }
    cout<<maximum<<endl;
}

int main(){
    cout<<"part 1 sample"<<endl;
    solve1("a.txt");
    cout<<"part 1 full"<<endl;
    solve1("b.txt");

    cout<<"part 2 sample"<<endl;
    // solve2("a.txt");
    cout<<"part 2 full"<<endl;
    solve2("b.txt");
    return 0;
}