#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;
//g++ day21.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
// char nums[200][200];
// int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
// int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
// bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
// int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
// int r(string t){ifstream cin(t);string s;cin>>s;for(int i=0;i<s.length();i++){nums[0][i]=s[i];}for(int i=1;i<s.length();i++){cin>>s;for(int j=0;j<s.length();j++){nums[i][j]=s[j];}}return s.length();}
// void sort(vector<int>v){sort(v.begin(),v.end());}void sort(vector<long long>v){sort(v.begin(),v.end());}
string mapTwo(vector<string> v, map<char,pair<int,int>>m, bool end);

string mapOne(string s, map<char,pair<int,int>> m, map<char,pair<int,int>> m1){
    vector<string> pos;
    int tempI1 = 3;
    int tempJ1 = 2;
    pos.push_back("");
    for (char c : s){
        int size = pos.size();
        for (int q = 0; q < size; q++){
            string curr = pos[q];
            pair<int,int> currPos= m1[c];
            int i = currPos.first, j = currPos.second;
            int tempI = tempI1, tempJ = tempJ1;
            if (i != tempI && j != tempJ){
                bool works = true;
                string currO = curr.substr(0);
                int tI = tempI, tJ = tempJ;
                while (tI != i){
                    if (tI < i){
                        tI++;
                        currO+='v';
                    }
                    if (tI > i){
                        tI--;
                        currO+='^';
                    }
                    if (tI == 3 && tJ == 0){
                        works = false;
                    }
                }
                while (tJ != j){
                    if (tJ < j){
                        tJ++;
                        currO+='>';
                    }
                    if (tJ > j){
                        tJ--;
                        currO+='<';
                    }
                    
                    if (tI == 3 && tJ == 0){
                        works = false;
                    }
                }
                currO+='A';
                if (works)
                pos.push_back(currO);
            }
            bool works = true;
            while (tempJ != j){
                if (tempJ < j){
                    tempJ++;
                    curr+='>';
                }
                if (tempJ > j){
                    tempJ--;
                    curr+='<';
                }
                if (tempI == 3 && tempJ == 0){
                    works = false;
                }
            }
            while (tempI != i){
                if (tempI < i){
                    tempI++;
                    curr+='v';
                }
                if (tempI > i){
                    tempI--;
                    curr+='^';
                }
                
                if (tempI == 3 && tempJ == 0){
                    works = false;
                }
            }
            curr+='A';
            if (works){
                pos[q] = curr;
            } else{
                pos.erase(pos.begin() + q);
                size--;
                q--;
            }
        }
        tempI1 = m1[c].first;
        tempJ1 = m1[c].second;
    }
    return mapTwo(pos, m, false);
}
string mapTwo(vector<string> v, map<char,pair<int,int>>m, bool end){
    vector<string> pos1;
    for (string s : v){
        vector<string> pos;
        pos.push_back("");
        int tempI1 = 0;
        int tempJ1 = 2;
        for (char c : s){
            int size = pos.size();
            for (int q = 0; q < size; q++){
                string curr = pos[q];
                pair<int,int> currPos= m[c];
                int i = currPos.first, j = currPos.second;
                int tempI = tempI1, tempJ = tempJ1;
                if (i != tempI && j != tempJ){
                    string currO = curr.substr(0);
                    int tI = tempI, tJ = tempJ;
                    bool works = true;
                    while (tI != i){
                        if (tI < i){
                            tI++;
                            currO+='v';
                            if (tI == 0 && tJ == 0){
                                works = false;
                            }
                        }
                        if (tI > i){
                            tI--;
                            currO+='^';
                            if (tI == 0 && tJ == 0){
                                works = false;
                            }
                        }
                    }
                    
                    if (tI == 0 && tJ == 0){
                        works = false;
                    }
                    while (tJ != j){
                        
                        if (tJ < j){
                            tJ++;
                            currO+='>';
                        }
                        if (tJ > j){
                            tJ--;
                            currO+='<';
                        }
                        
                        if (tI == 0 && tJ == 0){
                            works = false;
                        }
                    }
                    currO+='A';
                    if (works)
                    pos.push_back(currO);
                }
                bool works = true;
                while (tempJ != j){
                    if (tempJ < j){
                        tempJ++;
                        curr+='>';
                    }
                    if (tempJ > j){
                        tempJ--;
                        curr+='<';
                    }
                    if (tempI == 0 && tempJ == 0){
                        works = false;
                    }
                }
                while (tempI != i){
                    if (tempI < i){
                        tempI++;
                        curr+='v';
                    }
                    if (tempI > i){
                        tempI--;
                        curr+='^';
                    }
                    if (tempI == 0 && tempJ == 0){
                        works = false;
                    }
                }
                curr+='A';
                if (works){
                    pos[q] = curr;
                } else{
                    pos.erase(pos.begin() + q);
                    size--;
                    q--;
                }
            }
            tempI1 = m[c].first;
            tempJ1 = m[c].second;
        }
        for (string curr : pos){
            pos1.push_back(curr);
        }
    }
    
    if (end){
        int minm = INT_MAX;
        for (string curr : pos1){
            minm = min(minm,(int)curr.length());
        }
        for (string curr : pos1){
            if ((int)curr.length() == minm){
                return curr;
            }
        }
        cout<<"error";
        return "";
    } else
    return mapTwo(pos1, m, true);
}
void solve1(string txt){
    ifstream cin(txt);
    map<char,pair<int,int>> m1;
    m1['<']={1,0};m1['^']={0,1};m1['v']={1,1};m1['>']={1,2};m1['A']={0,2};
    map<char,pair<int,int>> m;
    m['7']={0,0};m['8']={0,1};m['9']={0,2};
    m['4']={1,0};m['5']={1,1};m['6']={1,2};
    m['1']={2,0};m['2']={2,1};m['3']={2,2};
    m['0']={3,1};m['A']={3,2};
    long long count = 0;
    string s;
    while (cin>>s){
        string r = mapOne(s,m1,m);
        stringstream ss(s);
        int num;
        ss>>num;
        count+=num*r.length();
    }
    cout<<count<<endl;
}


string dkeys = "0123456789A", akeys = "^<>VA";
typedef map<char, pair<long long, long long>> keypad;
keypad dpad = {
    {'7', {0, 3}}, {'8', {1, 3}}, {'9', {2, 3}},
    {'4', {0, 2}}, {'5', {1, 2}}, {'6', {2, 2}},
    {'1', {0, 1}}, {'2', {1, 1}}, {'3', {2, 1}},
    {'0', {1, 0}}, {'A', {2, 0}},
};
keypad apad = {
    {'^', {1, 1}}, {'<', {0, 0}}, {'>', {2, 0}}, {'V', {1, 0}}, {'A', {2, 1}}
};
map<char, int> xdir = {{'<', -1}, {'>', 1}};
map<char, int> ydir = {{'V', -1}, {'^', 1}};
typedef pair<char, char> node;
typedef map<pair<node, node>, long long> edgemat;

#define D(m, x, y) (m.count(make_pair(x, y)) ? m[make_pair(x, y)] : 1000000000000000000LL)

edgemat floyd_warshall(edgemat M) {
    set<node> N;
    for (auto x : M) N.insert(x.first.first);
    for (auto k : N) for (auto i : N) for (auto j : N) 
        M[{i, j}] = min(D(M, i, j), D(M, i, k) + D(M, k, j));
    return M;
}

edgemat add_layer(edgemat M, keypad P, string keys) {
    edgemat ans;
    for (char c1 : keys) for (char c2 : keys) for (char d1 : akeys) for (char d2 : akeys) {
        if (P[c2].first == P[c1].first + xdir[d2] && P[c2].second == P[c1].second + ydir[d2]) {
            ans[{{c1, d1}, {c2, d2}}] = M[{{d1,'A'},{d2,'A'}}];
        }
    }
    return floyd_warshall(ans);
}
void solve2(string txt){
    edgemat M;
    for (char c1 : akeys) for (char c2 : akeys) for (char d1 : akeys) M[{{c1, d1}, {c2, 'A'}}] = 1;
    for (int i = 0; i < 25; i++) M = add_layer(M, apad, akeys); // Change 25 to 2 for part 1
    M = add_layer(M, dpad, dkeys);

    ifstream fin(txt);
    string s;
    long long ans = 0;
    while (fin >> s) {
        for (int i = 0, lastch = 'A'; i < s.length(); lastch = s[i++]) {
            ans += atoi(s.c_str()) * M[{{lastch, 'A'}, {s[i], 'A'}}];
        }
    }
    cout << ans << "\n";
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