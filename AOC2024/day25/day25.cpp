#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;
//g++ day25.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
char nums[8][8];
int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
int r(string t){ifstream cin(t);string s;cin>>s;for(int i=0;i<s.length();i++){nums[0][i]=s[i];}for(int i=1;i<s.length();i++){cin>>s;for(int j=0;j<s.length();j++){nums[i][j]=s[j];}}return s.length();}
void sort(vector<int>v){sort(v.begin(),v.end());}void sort(vector<long long>v){sort(v.begin(),v.end());}
void solve1(string txt){
    ifstream cin(txt);
    long long count = 0;
    unordered_map<int,bool> m1;
    vector<tuple<int,int,int,int,int>> s;
    string s1;
    while(getline(cin,s1)){
        bool lock = (s1 == ".....");
        int a = 0,b = 0,c = 0,d = 0,e = 0;
        do{
            a += s1[0] == '#';
            b += s1[1] == '#';
            c += s1[2] == '#';
            d += s1[3] == '#';
            e += s1[4] == '#';
        } while (getline(cin,s1) && s1.length() > 0);
        s.push_back({a,b,c,d,e});
        m1[(s.size())-1] = lock;
    }
    for (int i = 0; i < s.size(); i++){
        for (int j = 0; j < s.size(); j++){
            if (i == j || m1[i] == m1[j] || m1[i]){
                continue;
            }
            int a = get<0>(s[i]),b = get<1>(s[i]),c = get<2>(s[i]),d = get<3>(s[i]),e = get<4>(s[i]);
            int a2 = get<0>(s[j]),b2 = get<1>(s[j]),c2 = get<2>(s[j]),d2 = get<3>(s[j]),e2 = get<4>(s[j]);
            if (a+a2 <= 7 && b+b2 <= 7 &&c+c2 <= 7 &&d+d2 <= 7 &&e+e2 <= 7){
                count++;
            }

        }
    }
    cout<<count<<endl;
}


int main(){
    cout<<"part 1 sample"<<endl;
    solve1("a.txt");
    cout<<"part 1 full"<<endl;
    solve1("b.txt");
    return 0;
}