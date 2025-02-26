#include <bits/stdc++.h>
using namespace std;
//g++ day 13.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
char nums[200][200];
int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
int r(string t){ifstream cin(t);string s;cin>>s;for(int i=0;i<s.length();i++){nums[0][i]=s[i];}for(int i=1;i<s.length();i++){cin>>s;for(int j=0;j<s.length();j++){nums[i][j]=s[j];}}return s.length();}
void sort(vector<int>v){sort(v.begin(),v.end());}void sort(vector<long long>v){sort(v.begin(),v.end());}

// typedef struct node{
//     int x;
//     int y;
//     node* prev;
// } node_t;
void solve1(string txt){
    ifstream cin(txt);
    string s1,s2,s3,s4;
    long long total = 0;
    while (getline(cin,s1), getline(cin,s2), getline(cin,s3),getline(cin,s4)){
        long long aX = stoll(s1.substr(12,2));
        long long aY = stoll(s1.substr(18,2));
        long long bX = stoll(s2.substr(12,2));
        long long bY = stoll(s2.substr(18,2));
        long long px = stoll(s3.substr(9, s3.find_first_of(",") - 9));
        long long py = stoll(s3.substr(s3.find_first_of(",")+4));
        map<pair<int,int>, long long> sol;
        for (long long i = 0; i < 200; i++){
            if (px - aX*i < 0 || py - aY*i < 0){
                break;
            }
            sol[{px - aX*i, py - aY*i}] = 3*i;
        }
        priority_queue<long long> pq;
        long long tempX = 0;
        long long tempY = 0;
        for (long long i = 0; i < 200; i++){
            if (sol.find({tempX,tempY}) != sol.end()){
                pq.push(i+sol[{tempX,tempY}]);
            }
            tempX+=bX;
            tempY+=bY;
            if (tempX > px || tempY > py){
                break;
            }
        }
        if (!pq.empty())
            total+=pq.top();
    }
    cout<<total<<endl;
}

void solve2(string txt){
    ifstream cin(txt);
    string s1,s2,s3,s4;
    long long total = 0;
    while (getline(cin,s1), getline(cin,s2), getline(cin,s3),getline(cin,s4)){
        long long ax = stoll(s1.substr(12,2));
        long long ay = stoll(s1.substr(18,2));
        long long bx = stoll(s2.substr(12,2));
        long long by = stoll(s2.substr(18,2));
        long long px = 10000000000000 + stoll(s3.substr(9, s3.find_first_of(",") - 9));
        long long py = 10000000000000 + stoll(s3.substr(s3.find_first_of(",")+4));
        if ((ax*by - ay*bx) != 0){
            long long a = (px*by - py*bx) / (ax*by - ay*bx);
            long long b = (py*ax - px*ay) / (ax*by - ay*bx);
            if ((px*by - py*bx) % (ax*by - ay*bx) == 0 && (py*ax - px*ay)%(ax*by - ay*bx)== 0){
                total+=3*a+b;
            }
        }
    }
    cout<<total<<endl;
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