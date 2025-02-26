#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;
//g++ day23.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
char nums[200][200];
int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
int r(string t){ifstream cin(t);string s;cin>>s;for(int i=0;i<s.length();i++){nums[0][i]=s[i];}for(int i=1;i<s.length();i++){cin>>s;for(int j=0;j<s.length();j++){nums[i][j]=s[j];}}return s.length();}
void sort(vector<int>v){sort(v.begin(),v.end());}void sort(vector<long long>v){sort(v.begin(),v.end());}
struct node{
    string val;
    vector<string> point;
};
set<tuple<string, string, string>> maybe;
map<string, bool> t;
void solve1(string txt){
    ifstream cin(txt);
    long long count = 0;
    
    string s;
    map<string, node> v;
    set<string> seen;
    while(getline(cin,s)){
        string start = s.substr(0,2);
        t[start] = s[0] == 't';
        string end = s.substr(3,2);
        t[end] = s[3] == 't';
        seen.insert(start);
        if (v.find(start) != v.end()){
            v[start].point.push_back(end);
        } else{
            node temp;
            temp.val = start;
            temp.point.push_back(end);
            v[start] = temp;
        }
         start = s.substr(3,2);
         end = s.substr(0,2);
        seen.insert(start);
        if (v.find(start) != v.end()){
            v[start].point.push_back(end);
        } else{
            node temp;
            temp.val = start;
            temp.point.push_back(end);
            v[start] = temp;
        }
    }
    for (string s : seen){
        auto curr = v[s];
        for (string curr2: curr.point){
            for (string curr3: v[curr2].point){
                for (string curr4: v[curr3].point){
                    if (s == curr4 && (t[s] || t[curr2] || t[curr3])){
                        vector<string> m1 = {s,curr2,curr3};
                        sort(m1.begin(),m1.end());
                        tuple<string, string, string> m = {m1[0], m1[1], m1[2]};
                        if (maybe.find(m) == maybe.end()){
                            count++;
                            maybe.insert(m);
                        }
                    }
                }
            }
        }
    }
    cout<<t.size()<<endl;
    cout<<count<<endl;
}

void bk(unordered_set<string>&& R, unordered_set<string>&& P,
    unordered_set<string>&& X,
    unordered_map<string, unordered_set<string> >& graph,
    vector<unordered_set<string> >& cliques){
    
    if (P.empty() && X.empty()){
        cliques.push_back(R);
    }
    while (!P.empty()) {
        string v = *P.begin();
        unordered_set<string> newR = R;
        newR.insert(v);
        unordered_set<string> newP;
        for (string p : P) {
            if (graph[v].find(p) != graph[v].end()) {
                newP.insert(p);
            }
        }
        unordered_set<string> newX;
        for (string x : X) {
            if (graph[v].find(x) != graph[v].end()) {
                newX.insert(x);
            }
        }
        bk(move(newR), move(newP), move(newX),graph, cliques);
        P.erase(v);
        X.insert(v);
    }
}

void solve2(string txt){
    ifstream cin(txt);
    //networkx go burr
    unordered_map<string, unordered_set<string>> graph;
    unordered_set<string> vert; 
    string s;
    while(cin>>s){
        graph[s.substr(0,2)].insert(s.substr(3,2));
        graph[s.substr(3,2)].insert(s.substr(0,2));
    }
    for (auto curr: graph){
        vert.insert(curr.first);
    }
    vector<unordered_set<string>> cliqs;
    bk({},move(vert),{},graph,cliqs);
    int max2 = -1;
    for (unordered_set<string> c : cliqs){
        max2 = max(max2, (int)c.size());
    }
    for (unordered_set<string> c : cliqs){
        if ((int)c.size() == max2){
            vector<string> ans;
            for (auto curr : c){
                ans.push_back(curr);
            }
            sort(ans.begin(), ans.end());
            for (string curr : ans){
                cout<<curr<<",";
            }
            cout<<endl;
        }
    }
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