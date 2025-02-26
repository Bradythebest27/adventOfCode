#include <bits/stdc++.h>
using namespace std;
//g++ day10.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }

char nums[100][100];
int dir[4][2] = {{1,0},{-1,0},{0,1},{0,-1}};
bool valid(int i , int j, int size){
    return (i>=0&&j>=0&&i<size&&j<size);
}
int findSol(int i, int j, int size, set<pair<int,int>> &sol){
    if (nums[i][j] == '9' && sol.find({i,j}) == sol.end()){
        sol.insert({i,j});
        return 1;
    }
    if (nums[i][j] == '9' && sol.find({i,j}) != sol.end()){
        return 0;
    }
    int total = 0;
    for (int k = 0; k < 4; k++){
        if (valid(i+dir[k][0],j+dir[k][1],size) &&nums[i+dir[k][0]][j+dir[k][1]] - nums[i][j] == 1){
            total+=findSol(i+dir[k][0],j+dir[k][1],size,sol);
        }
    }
    return total;
}
void solve1(string txt){
    ifstream cin(txt);
    string s;
    cin>>s;
    for (int i = 0; i < s.length(); i++){
        nums[0][i] = s[i];
    }
    for (int i = 1; i < s.length(); i++){
        cin>>s;
        for (int j = 0; j < s.length(); j++){
            nums[i][j] = s[j];
        }
    }
    int count = 0;
    for (int i = 0; i < s.length(); i++){
        for (int j = 0; j < s.length(); j++){
            if (nums[i][j] == '0'){
                set<pair<int,int>> sol;
                count+=findSol(i,j,s.length(), sol);
            }
        }
    }
    cout<<count<<endl;
}

int findSol2(int i, int j, int size){
    if (nums[i][j] == '9'){
        return 1;
    }
    int total = 0;
    for (int k = 0; k < 4; k++){
        if (valid(i+dir[k][0],j+dir[k][1],size) &&nums[i+dir[k][0]][j+dir[k][1]] - nums[i][j] == 1){
            total+=findSol2(i+dir[k][0],j+dir[k][1],size);
        }
    }
    return total;
}
void solve2(string txt){
    ifstream cin(txt);
    string s;
    cin>>s;
    for (int i = 0; i < s.length(); i++){
        nums[0][i] = s[i];
    }
    for (int i = 1; i < s.length(); i++){
        cin>>s;
        for (int j = 0; j < s.length(); j++){
            nums[i][j] = s[j];
        }
    }
    int count = 0;
    for (int i = 0; i < s.length(); i++){
        for (int j = 0; j < s.length(); j++){
            if (nums[i][j] == '0'){
                count+=findSol2(i,j,s.length());
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