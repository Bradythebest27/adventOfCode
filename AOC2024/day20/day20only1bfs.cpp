#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;
//g++ day20.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
char nums[200][200];
int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
int r(string t){ifstream cin(t);string s;cin>>s;for(int i=0;i<s.length();i++){nums[0][i]=s[i];}for(int i=1;i<s.length();i++){cin>>s;for(int j=0;j<s.length();j++){nums[i][j]=s[j];}}return s.length();}
void sort(vector<int>v){sort(v.begin(),v.end());}void sort(vector<long long>v){sort(v.begin(),v.end());}
void solve1(string txt){
    ifstream cin(txt);
    long long count = 0;
    int size = r(txt);
    int startI = -1, startJ = -1;
    for (int i = 0; i < size; i++){
        for (int j = 0; j < size; j++){
            if (nums[i][j] == 'S'){
                startI = i;
                startJ = j;
                nums[i][j] = '.';
            }
        }
    }
    set<pair<int,int>> seen;
    priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> pq;
    pq.push({0,startI,startJ});
    int noCheat = -1;
    map<pair<int,int>, int> m;
    vector<pair<int,int>> toCheck;
    while (!pq.empty()){
        // cout<<"asd";
        tuple<int,int,int> t = pq.top();pq.pop();
        int i = get<1>(t), j = get<2>(t);
        m[{i,j}] = get<0>(t);
        if (nums[i][j] == 'E'){
            noCheat = get<0>(t);
            break;
        }
        if (seen.find({i,j}) != seen.end()){
            continue;
        }
        
        seen.insert({i,j});
        toCheck.push_back({i,j});
        for (int d = 0; d < 4; d++){
            if (valid(i+dir[d][0], j+dir[d][1], size)){
                if (nums[i+dir[d][0]][j+dir[d][1]] != '#'){
                    pq.push({get<0>(t) + 1, i+dir[d][0],j+dir[d][1]});
                }
            }
        }

    }
    assert(noCheat > 0);
    cout<<noCheat<<endl;
    for (pair<int,int> curr : toCheck){
        int i = curr.first;
        int j = curr.second;
        for (int d = 0; d  <4; d++){
            if (valid(i+2*dir[d][0], j+2*dir[d][1], size)){
                if (nums[i+2*dir[d][0]][j+2*dir[d][1]] == '.' || nums[i+2*dir[d][0]][j+2*dir[d][1]] == 'E'){
                    if ((noCheat - m[{i,j}]) - (noCheat - m[{i+2*dir[d][0],j+2*dir[d][1]}]) - 2 >=100){
                        count++;
                    }
                }
            }
        }


    }
    cout<<count<<endl;
}

void solve2(string txt){
    ifstream cin(txt);
    long long count = 0;
    int size = r(txt);
    int startI = -1, startJ = -1;
    for (int i = 0; i < size; i++){
        for (int j = 0; j < size; j++){
            if (nums[i][j] == 'E'){
                startI = i;
                startJ = j;
                nums[i][j] = '.';
            }
            if (nums[i][j] == 'S'){
                nums[i][j] == '.';
            }
        }
    }
    set<pair<int,int>> seen;
    priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> pq;
    pq.push({0,startI,startJ});
    map<pair<int,int>, int> m;
    while (!pq.empty()){
        tuple<int,int,int> t = pq.top();pq.pop();
        int i = get<1>(t), j = get<2>(t);
        if (seen.find({i,j}) != seen.end()){
            continue;
        }
        m[{i,j}] = get<0>(t);
        seen.insert({i,j});
        for (int d = 0; d < 4; d++){
            if (valid(i+dir[d][0], j+dir[d][1], size)){
                if (nums[i+dir[d][0]][j+dir[d][1]] != '#'){
                    pq.push({get<0>(t) + 1, i+dir[d][0],j+dir[d][1]});
                }
            }
        }
        for (int tempI = i - 20; tempI <=i + 20; tempI++){
            for (int tempJ = j- 20; tempJ <= j + 20; tempJ++){
                if (abs(tempI - i) + abs(tempJ - j) <= 20){
                    if (valid(tempI, tempJ, size) && nums[tempI][tempJ] == '.'){
                        if (seen.find({tempI,tempJ}) != seen.end()){
                            count+=m[{i,j}] - m[{tempI, tempJ}] - (abs(tempI - i) + abs(tempJ - j)) >= 100 ? 1 : 0;
                        }
                    }
                }
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

    cout<<"part 2 sample"<<endl;
    solve2("a.txt");
    cout<<"part 2 full"<<endl;
    solve2("b.txt");
    return 0;
}