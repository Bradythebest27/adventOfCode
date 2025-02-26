#include <bits/stdc++.h>
using namespace std;
//g++ day.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
char nums[500][500];
int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
map<char,pair<int,int>> m;

int r2(string t){ifstream cin(t);string s;cin>>s;
    for (int i = 0; i < s.length(); i++){
        if (s[i] == 'O'){
            nums[0][2*i] = '[';
            nums[0][2*i+1] = ']';
        } else{
            nums[0][2*i] = s[i];
            nums[0][2*i+1] = s[i];
        }
    }
    for (int i = 1; i < s.length(); i++){
        cin>>s;
        for (int j = 0; j < s.length(); j++){
            if (s[j] == 'O'){
                nums[i][2*j] = '[';
                nums[i][2*j+1] = ']';
            }else if (s[j] =='@'){
                nums[i][2*j] = '@';
                nums[i][2*j+1] = '.';
            } else{
                nums[i][2*j] = s[j];
                nums[i][2*j+1] = s[j];
            }
        }
    }
    return s.length();
}

pair<int,int> moveLR(int i, int j, char d){
    int tempI = i + m[d].first;
    int tempJ = j + m[d].second;
    while (nums[tempI][tempJ] != '.' && nums[tempI][tempJ] != '#'){
        tempI += m[d].first;
        tempJ += m[d].second;
    }
    if (nums[tempI][tempJ] == '#'){
        return {i,j};
    }
    while (tempI != i || tempJ != j){
        swap(nums[tempI][tempJ],nums[tempI - m[d].first][tempJ - m[d].second]);
        tempI -= m[d].first;
        tempJ -= m[d].second;
    }
    return {i + m[d].first, j + m[d].second};
}

pair<int,int> moveUD(int i, int j, char d){
    if (nums[i + m[d].first][j + m[d].second] == '#'){
        return {i, j};
    }
    if (nums[i + m[d].first][j +m[d].second] == '.'){
        return {i+ m[d].first, j+m[d].second};
    }

    //pain
    vector<pair<int,int>> v;
    v.push_back({i+ m[d].first, j+m[d].second});
    if (nums[i + m[d].first][j +m[d].second] == '['){
        v.push_back({i+ m[d].first + m['>'].first, j+m[d].second + m['>'].second});
    } else{
        v.push_back({i+ m[d].first + m['<'].first, j+m[d].second + m['<'].second});
    }
    bool foundOne = true;
    set<pair<int,int>> f;
    f.insert(v[0]);
    f.insert(v[1]);
    for (int k = 0; k < v.size(); k++){
        pair<int,int> curr = v[k];
        if (nums[curr.first+m[d].first][curr.second+m[d].second] == '#'){
            return {i,j};
        }
        if (nums[curr.first+m[d].first][curr.second+m[d].second] == '.'){
            continue;
        }
        if (nums[curr.first+m[d].first][curr.second+m[d].second] == ']'){
            if (f.find({curr.first+m[d].first,curr.second+m[d].second}) == f.end()){
                
                v.push_back({curr.first+m[d].first, curr.second+m[d].second});
                v.push_back({curr.first+m[d].first + m['<'].first, curr.second+m[d].second+ m['<'].second});
                f.insert(v[v.size()-2]);
                f.insert(v[v.size()-1]);
            }
        }
        if (nums[curr.first+m[d].first][curr.second+m[d].second] == '['){
            if (f.find({curr.first+m[d].first,curr.second+m[d].second}) == f.end()){
                
                v.push_back({curr.first+m[d].first, curr.second+m[d].second});
                v.push_back({curr.first+m[d].first + m['>'].first, curr.second+m[d].second+ m['>'].second});
                f.insert(v[v.size()-2]);
                f.insert(v[v.size()-1]);
            }
        }
    }
    for (int p = v.size() - 1; p >=0; p--){
        pair<int,int> curr = v[p];
        swap(nums[curr.first][curr.second], nums[curr.first+m[d].first][curr.second+m[d].second]);
    }
    return {i+m[d].first,j+m[d].second};
}
long long val(int x, int y){
    return 100*x+y;
}

void solve2(string txt,string asd){
    m['<'] = {0,-1};
    m['>'] = {0,1};
    m['v'] = {1,0};
    m['^'] = {-1,0};
    ifstream cin(txt);
    int size = r2(txt);
    long long count = 0;
    string s;
    ifstream dirCin(asd);
    string temp;
    while (dirCin>>temp){
        s+=temp;
    }
    int currI = -1;
    int currJ = -1;
    for (int i = 0; i < size; i++){
        for (int j = 0; j < 2*size; j++){
            if (nums[i][j] == '@'){
                currI = i;
                currJ = j;
                nums[i][j] ='.';
            }
        }
    }
    
    for (char c : s){
        pair<int,int> d;
        if (c == '<' || c == '>')
            d = moveLR(currI, currJ, c);
        else
            d = moveUD(currI, currJ, c);
        currI = d.first;
        currJ = d.second;
    }

    for (int i = 0; i < size; i++){
        for (int j = 0; j < 2*size; j++){
            if (nums[i][j] == '['){
                count+=val(i,j);
            }
        }
    }
    cout<<count<<endl;
}

int main(){

    cout<<"part 2 sample"<<endl;
    solve2("a.txt", "d.txt");
    cout<<"part 2 full"<<endl;
    solve2("b.txt", "c.txt");
    return 0;
}