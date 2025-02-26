#include <bits/stdc++.h>
using namespace std;
//g++ day8.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
int valid(int x, int y, int size){
    return (x>=0&&x<size&&y>=0&&y<size);
}
void solve1(string txt){
    ifstream cin(txt);
    string s;
    cin>>s;
    int size = s.length();
    char nums[size][size];
    vector<char> flags;
    unordered_map<char,vector<pair<int,int>>> m;
    for (int i = 0; i < size; i++){
        nums[0][i] = s[i];
        if (s[i] != '.' && m.find(s[i]) == m.end()){
            flags.push_back(s[i]);
        }
        if (s[i] != '.')
            m[s[i]].push_back({0,i});
    }
    for (int i = 1; i < size; i++){
        cin>>s;
        for (int j = 0; j < size; j++){
            nums[i][j] = s[j];
            if (s[j] != '.' && m.find(s[j]) == m.end()){
                flags.push_back(s[j]);
            }
            if (s[j] != '.')
                m[s[j]].push_back({i,j});
        }
    }
    int count = 0;
    set<pair<int,int>> sol;
    for (char curr: flags){
        vector<pair<int,int>> p = m[curr];
        for (int i = 0; i < p.size(); i++){
            for (int j = 0; j < p.size() && i != j; j++){
                int x1 = p[i].first;
                int y1 = p[i].second;
                int x2 = p[j].first;
                int y2 = p[j].second;
                int rx = 2*(x2 - x1) + x1;
                int ry = 2*(y2 - y1) + y1;
                int tx = 2 * (x1 - x2) + x2;
                int ty = 2 * (y1 - y2) + y2;
                if (valid(rx, ry,size) && sol.find({rx, ry}) == sol.end()){
                    count++;
                    sol.insert({rx, ry});
                    nums[rx][ry] = '#';
                }
                if (valid(tx,ty, size) && sol.find({tx,ty}) == sol.end()){
                    count++;
                    sol.insert({tx,ty});
                }
            }
        }
        
    }
    cout<<count<<endl;
}

void solve2(string txt){
    ifstream cin(txt);
    string s;
    cin>>s;
    int size = s.length();
    char nums[size][size];
    vector<char> flags;
    unordered_map<char,vector<pair<int,int>>> m;
    for (int i = 0; i < size; i++){
        nums[0][i] = s[i];
        if (s[i] != '.' && m.find(s[i]) == m.end()){
            flags.push_back(s[i]);
        }
        if (s[i] != '.')
            m[s[i]].push_back({0,i});
    }
    for (int i = 1; i < size; i++){
        cin>>s;
        for (int j = 0; j < size; j++){
            nums[i][j] = s[j];
            if (s[j] != '.' && m.find(s[j]) == m.end()){
                flags.push_back(s[j]);
            }
            if (s[j] != '.')
                m[s[j]].push_back({i,j});
        }
    }
    int count = 0;
    set<pair<int,int>> sol;
    for (char curr: flags){
        vector<pair<int,int>> p = m[curr];
        for (int i = 0; i < p.size(); i++){
            for (int j = 0; j < p.size() && i != j; j++){
                int x1 = p[i].first;
                int y1 = p[i].second;
                int x2 = p[j].first;
                int y2 = p[j].second;
                bool oneWorks = true;
                int mul = 2;
                while (oneWorks){
                    oneWorks = false;
                    int rx = mul*(x2 - x1) + x1;
                    int ry = mul*(y2 - y1) + y1;
                    int tx = mul * (x1 - x2) + x2;
                    int ty = mul * (y1 - y2) + y2;
                    if (valid(rx, ry,size) && sol.find({rx, ry}) == sol.end()){
                        oneWorks = true;   
                        nums[rx][ry] = '#';
                    }
                    if (valid(tx,ty, size) && sol.find({tx,ty}) == sol.end()){
                        oneWorks = true;
                        nums[tx][ty] = '#';
                    }
                    mul++;
                }
            }
        }
        
    }
    for (int i = 0; i < size; i++){
        for (int j = 0; j < size; j++){
            if (nums[i][j] != '.'){
                count++;
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