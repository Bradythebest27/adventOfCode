#include <bits/stdc++.h>
using namespace std;
//g++ day.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
char nums[500][500];
int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
int r(string t)
{
    ifstream cin(t);
    string s;
    cin >> s;
    for (int i = 0; i < s.length(); i++)
    {
        nums[0][i] = s[i];
    }
    for (int i = 1; i < s.length(); i++)
    {
        cin >> s;
        for (int j = 0; j < s.length(); j++)
        {
            nums[i][j] = s[j];
        }
    }
    return s.length();
}
void sort(vector<int>v){sort(v.begin(),v.end());}void sort(vector<long long>v){sort(v.begin(),v.end());}

long long val(int x, int y){
    return 100*x+y;
}

void solve1(string txt, string asd){
    ifstream cin(txt);
    int size = r(txt);
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
        for (int j = 0; j < size; j++){
            if (nums[i][j] == '@'){
                currI = i;
                currJ = j;
                nums[i][j] ='.';
            }
        }
    }
    map<char,pair<int,int>> m;
    
    m['<'] = {0,-1};
    m['>'] = {0,1};
    m['v'] = {1,0};
    m['^'] = {-1,0};
    
    for (char c : s){
        pair<int,int> d = m[c];
        if (nums[currI+d.first][currJ+d.second] == '.'){
            currI = currI+d.first;
            currJ = currJ+d.second;
        } else if (nums[currI+d.first][currJ+d.second] == '#'){
            continue;
        } else{
            int tI = currI+d.first+d.first;
            int tJ = currJ+d.second+d.second;
            while (nums[tI][tJ] == 'O'){
                tI+=d.first;
                tJ+=d.second;
            }
            if (nums[tI][tJ] == '#'){
                continue;
            } else{
                nums[tI][tJ] = 'O';
                nums[currI+d.first][currJ+d.second] = '.';
                currI = currI+d.first;
                currJ = currJ+d.second;
            }
        }
    }
    for (int i = 0; i < size; i++){
        for (int j = 0; j < size; j++){
            if (nums[i][j] == 'O'){
                count+=val(i,j);
            }
        }
    }

    cout<<count<<endl;
}


int main(){
    cout<<"part 1 sample"<<endl;
    solve1("a.txt", "d.txt");
    cout<<"part 1 full"<<endl;
    solve1("b.txt", "c.txt");
    return 0;
}