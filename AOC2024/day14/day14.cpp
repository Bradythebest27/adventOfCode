#include <bits/stdc++.h>
using namespace std;
//g++ day14.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
char nums[200][200];
int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
void solve1(string txt){
    ifstream cin(txt);
    string s;
    long long a=0,b=0,c=0,d=0;
    while(getline(cin,s)){
        stringstream temp(s);
        int px,py,vx,vy;
        char t;
        temp>>t>>t>>px>>t>>py>>t>>t>>vx>>t>>vy;
        px += (100 * vx + 100 * 101);
        py += (100 * vy + 100 * 103);
        px%=101;
        py%=103;
        if (px < 50 && py < 51){
            a++;
        } else if(px > 50 && py > 51){
            b++;
        } else if(px > 50 && py < 51){
            c++;
        } else if (px < 50 && py > 51){
            d++;
        }
    }
    cout<<a*b*c*d<<endl;
}

void solve2(string txt){
    ifstream cin(txt);
    string s;
    
    priority_queue<pair<long long,int>> pq;
    vector<tuple<int,int,int,int>> v;
    cin>>s;
    while(getline(cin,s)){
        stringstream temp(s);
        int px,py,vx,vy;
        char t;
        temp>>t>>t>>px>>t>>py>>t>>t>>vx>>t>>vy;
        v.push_back(make_tuple(px,py,vx,vy));
    }
    for (int i = 0; i < 10000; i++){
        long long a=0,b=0,c=0,d=0;
        for(auto curr : v){
            int px = get<0>(curr);
            int py = get<1>(curr);
            int vx = get<2>(curr);
            int vy = get<3>(curr);
            px += (i * vx + i * 101);
            py += (i * vy + i * 103);
            px%=101;
            py%=103;
            if (px < 50 && py < 51){
                a++;
            } else if(px > 50 && py > 51){
                b++;
            } else if(px > 50 && py < 51){
                c++;
            } else if (px < 50 && py > 51){
                d++;
            }
        }
        pq.push({-1*a*b*c*d, i});
    }
    
    long long loc = pq.top().second;
    cout<<loc<<endl;
    char nums[102][104];
    for (int i = 0; i < 101; i++){
        for (int j = 0; j < 103; j++){
            nums[i][j] = '.';
        }
    }
    for(auto curr : v){
        int px = get<0>(curr);
        int py = get<1>(curr);
        int vx = get<2>(curr);
        int vy = get<3>(curr);
        px += (loc * vx + loc * 101);
        py += (loc * vy + loc * 103);
        px%=101;
        py%=103;
        nums[px][py] = '#';
    }
    
    for (int i = 0; i < 103; i++){
        for (int j = 0; j < 101; j++){
            cout<<nums[j][i];
        }
        cout<<endl;
    }

    cout<<pq.top().second<<endl;
    
}

int main(){
    cout<<"part 1 full"<<endl;
    solve1("b.txt");

    cout<<"part 2 full"<<endl;
    solve2("b.txt");
    return 0;
}