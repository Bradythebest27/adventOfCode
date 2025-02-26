#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;
//g++ day18.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
char nums[200][200];
int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
int r(string t){ifstream cin(t);string s;cin>>s;for(int i=0;i<s.length();i++){nums[0][i]=s[i];}for(int i=1;i<s.length();i++){cin>>s;for(int j=0;j<s.length();j++){nums[i][j]=s[j];}}return s.length();}
void sort(vector<int>v){sort(v.begin(),v.end());}void sort(vector<long long>v){sort(v.begin(),v.end());}
void solve1(string txt, int size, int t){
    ifstream cin(txt);
    set<pair<int,int>> cor;
    //       dist i   j
    map<pair<int,int>,int> seen;
    //                     dist i j
    priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<>> pq;
    int x,y;
    char c;
    while(cin>>x>>c>>y && t--){
        cor.insert({x,y});
    }
    pq.push({0,0,0});
    while (!pq.empty()){
        tuple<int,int,int> t = pq.top(); pq.pop();
        int i = get<1>(t);
        int j = get<2>(t);
        int score = get<0>(t);
        if (i == size -1 && j == size -1){
            cout<<score<<endl;
            break;
        }
        for (int d = 0; d < 4; d++){
            if (valid(i+dir[d][0],j+dir[d][1],size)){
                if (cor.find({i+dir[d][0],j+dir[d][1]}) == cor.end()){
                    if (seen.find({i+dir[d][0],j+dir[d][1]}) == seen.end() || seen[{i+dir[d][0],j+dir[d][1]}] < score + 1){
                        pq.push({score+1,i+dir[d][0],j+dir[d][1]});
                        seen[{i+dir[d][0],j+dir[d][1]}] = score + 1;
                    }
                }
            }
        }
    }
}

void solve2(string txt, int size){
    ifstream cin(txt);
    vector<set<pair<int,int>>> corr(3450);
    vector<pair<int,int>> tat;
    for (int t = 0; t < 3450; t++){
        int x,y;
        char c;
        cin>>x>>c>>y;
        for (int p = t; p < 3450; p++){
            corr[p].insert({x,y});
        }
        tat.push_back({x,y});
    }
    int L = 1024;
    int R = 3450;
    while (L<R){
        int t = (L+R)/2;
        set<pair<int,int>> cor = corr[t];
        // cout<<L<<" "<<R<<" "<<t<<endl;
        //       dist i   j
        map<pair<int,int>,int> seen;
        //                     dist i j
        priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<>> pq;
        
        pq.push({0,0,0});
        while (!pq.empty()){
            tuple<int,int,int> t = pq.top(); pq.pop();
            int i = get<1>(t);
            int j = get<2>(t);
            int score = get<0>(t);
            if (i == size -1 && j == size -1){
                // cout<<score<<endl;
                break;
            }
            for (int d = 0; d < 4; d++){
                if (valid(i+dir[d][0],j+dir[d][1],size)){
                    if ((cor).find({i+dir[d][0],j+dir[d][1]}) == (cor).end()){
                        if (seen.find({i+dir[d][0],j+dir[d][1]}) == seen.end() || seen[{i+dir[d][0],j+dir[d][1]}] > score + 1){
                            pq.push({score+1,i+dir[d][0],j+dir[d][1]});
                            seen[{i+dir[d][0],j+dir[d][1]}] = score + 1;
                        }
                    }
                }
            }
        }
        if (pq.empty()){
            R = t;
        } else{
            L = t + 1;
        }
    }
    cout<<tat[L].first<<","<<tat[L].second<<endl;
}

int main(){
    cout<<"part 1 sample"<<endl;
    solve1("a.txt", 7, 12);
    cout<<"part 1 full"<<endl;
    solve1("b.txt", 71, 1025);

    cout<<"part 2 sample"<<endl;
    // solve2("a.txt", 7);
    cout<<"part 2 full"<<endl;
    solve2("b.txt", 71);
    return 0;
}