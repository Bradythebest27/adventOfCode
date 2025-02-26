#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;
//g++ day24.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
char nums[200][200];
int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
int r(string t){ifstream cin(t);string s;cin>>s;for(int i=0;i<s.length();i++){nums[0][i]=s[i];}for(int i=1;i<s.length();i++){cin>>s;for(int j=0;j<s.length();j++){nums[i][j]=s[j];}}return s.length();}
void sort(vector<int>v){sort(v.begin(),v.end());}void sort(vector<long long>v){sort(v.begin(),v.end());}
void solve1(string txt1, string txt2){
    ifstream cin(txt1);
    unordered_map<string,bool> m;
    string s1;
    while(cin>>s1){
        cin>>m[s1.substr(0,3)];
    }
    ifstream fin(txt2);
    queue<tuple<string,string,string,string>> st;
    string l, op, r, trash, end;
    while(fin>>l>>op>>r>>trash>>end){
        st.push({l,op,r,end});
    }
    while (!st.empty()){
        tuple<string,string,string,string> n = st.front();
        string l = get<0>(n);string op = get<1>(n);string r = get<2>(n);string end = get<3>(n);
        if (m.find(l) == m.end() || m.find(r) == m.end()){
            st.push(n);
            st.pop();
            continue;
        }
        st.pop();
        if (op == "AND"){
            m[end] = m[l]&m[r];
        } else if (op =="OR"){
            m[end] = m[l]|m[r];
        } else if (op == "XOR"){
            m[end] = m[l]^m[r];
        }
    }
    string ans = "";
    vector<string> v;
    for (auto curr : m){
        if (curr.first[0] == 'z'){
            v.push_back(curr.first);
        }
    }
    sort(v.begin(),v.end());
    for (string curr : v){
        ans= (m[curr] ? '1' : '0') + ans;
    }
    bitset<64> bitset(ans);
    cout<<bitset.to_ullong()<<endl;
}

bool startsXYZ(char x){
    return (x=='x'||x=='y'||x=='z');
}

void solve2(string txt1, string txt2){
    unordered_map<string, int> wires;
    vector<tuple<string,string,string,string>> operations;
    ifstream cin(txt1);
    ifstream fin(txt2);
    string l, op, r, trash, end;
    string s1;
    while(cin>>s1){
        cin>>wires[s1.substr(0,3)];
    }
    while(fin>>l>>op>>r>>trash>>end){
        operations.push_back({l,op,r,end});
    }
    set<string> wrong;
    string largestZ = (txt1 == "c.txt") ? "z12" : "z45";
    for (auto curr : operations){
        string op1=get<0>(curr),op=get<1>(curr),op2=get<2>(curr),res=get<3>(curr);
        if (res[0] == 'z' && op != "XOR" && res != largestZ){
            wrong.insert(res);
        }
        if (op=="XOR" && !startsXYZ(res[0]) && !startsXYZ(op1[0])&& !startsXYZ(op2[0])){
            wrong.insert(res);
        }
        if (op == "AND" && op1 != "x00" && op2 != "x00"){
            for (auto curr2 : operations){
                string sop1=get<0>(curr2),sop=get<1>(curr2),sop2=get<2>(curr2),sres=get<3>(curr2);
                if ((res == sop1 || res == sop2) && sop != "OR"){
                    wrong.insert(res);
                }
            }
        }
        if (op == "XOR"){
            for (auto curr2 : operations){
                string sop1=get<0>(curr2),sop=get<1>(curr2),sop2=get<2>(curr2),sres=get<3>(curr2);
                if ((res == sop1 || res == sop2) && sop == "OR"){
                    wrong.insert(res);
                }
            }
        }
    }
    vector<string> ans;
    for (auto curr : wrong){
        ans.push_back(curr);
    }
    sort(ans.begin(),ans.end());
    for (string curr : ans){
        cout<<curr<<",";
    }
    cout<<endl;
}

int main(){
    cout<<"part 1 sample"<<endl;
    solve1("c.txt","d.txt");
    cout<<"part 1 full"<<endl;
    solve1("a.txt","b.txt");

    cout<<"part 2 sample"<<endl;
    solve2("c.txt","d.txt");
    cout<<"part 2 full"<<endl;
    solve2("a.txt","b.txt");
    return 0;
}