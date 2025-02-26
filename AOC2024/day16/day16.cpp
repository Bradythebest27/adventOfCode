#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;

//g++ day16.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
char nums[200][200];
int dir[4][2]={{0,-1},{-1,0},{0,1},{1,0}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
int r(string t){ifstream cin(t);string s;cin>>s;for(int i=0;i<s.length();i++){nums[0][i]=s[i];}for(int i=1;i<s.length();i++){cin>>s;for(int j=0;j<s.length();j++){nums[i][j]=s[j];}}return s.length();}
void sort(vector<int>v){sort(v.begin(),v.end());}void sort(vector<long long>v){sort(v.begin(),v.end());}
long long solve1(string txt){
    ifstream cin(txt);
    int size = r(txt);
    priority_queue<tuple<int, int, int, int>, vector<tuple<int, int, int, int>>, greater<tuple<int, int, int, int>>> pq;
    int startI = -1;
    int startJ = -1;
    for (int i = 0; i < size; i++){
        for (int j = 0; j < size; j++){
            if (nums[i][j] == 'S'){
                startI = i;
                startJ = j;
                nums[i][j] == '#';
            }
        }
    }
    pq.push({0, startI, startJ, 2});
    map<tuple<int,int, int>, long long> m;
    while (true){
        tuple<long long,int,int,int> curr = pq.top();
        pq.pop();
        int i = get<1>(curr);
        int j = get<2>(curr);
        int d = get<3>(curr);
        int score = get<0>(curr);
        if (m.find(make_tuple(i,j,d)) == m.end()){
            m[make_tuple(i,j,d)] = score;
        } else{
            if (m[make_tuple(i,j,d)] < score){
                continue;
            } else{
                m[make_tuple(i,j,d)] = score;
            }
        }
        if (nums[i+dir[d][0]][j+dir[d][1]] == 'E'){
            cout<<1+score<<endl;
            return 1+score;
        }
        if (nums[i+dir[d][0]][j+dir[d][1]] != '#'){
            pq.push({score+1, i+dir[d][0],j+dir[d][1], d});
        }
        d = (d + 1)%4;
        if (nums[i+dir[d][0]][j+dir[d][1]] == 'E'){
            cout<<1001+score<<endl;
            return 1001+score;
        }
        if (nums[i+dir[d][0]][j+dir[d][1]] != '#'){
            pq.push({score+1001, i+dir[d][0],j+dir[d][1], d});
        }
        d = (d - 2 + 4)%4;
        if (nums[i+dir[d][0]][j+dir[d][1]] == 'E'){
            cout<<1001+score<<endl;
            return 1001+score;
        }
        if (nums[i+dir[d][0]][j+dir[d][1]] != '#'){
            pq.push({score+1001, i+dir[d][0],j+dir[d][1], d});
        }
    }
}

void solve2(string txt, long long endScore){
    
    ifstream cin(txt);
    int size = r(txt);
    //pq by             score i     j   dir     path
    priority_queue<tuple<int, int, int, int, vector<pair<int,int>>>, vector<tuple<int, int, int, int, vector<pair<int,int>>>>, greater<tuple<int, int, int, int, vector<pair<int,int>>>>> pq;
    int startI = -1;
    int startJ = -1;
    for (int i = 0; i < size; i++){
        for (int j = 0; j < size; j++){
            if (nums[i][j] == 'S'){
                startI = i;
                startJ = j;
                nums[i][j] == '#';
            }
        }
    }
    vector<pair<int,int>> a;
    pq.push({0, startI, startJ, 2, a});
    map<tuple<int,int, int>, long long> m;
    set<pair<int,int>> s; //solution points
    long long count = 0;
    while (true){
        //if no more possible optimal solutions
        if (pq.empty()){
            break;
        }
        tuple<long long,int,int,int, vector<pair<int,int>>> curr = pq.top(); pq.pop();
        int i = get<1>(curr);int j = get<2>(curr);int d = get<3>(curr);int score = get<0>(curr);
        //prune if seen before with higher score
        if (m.find(make_tuple(i,j,d)) == m.end()){
            m[make_tuple(i,j,d)] = score;
        } else{
            //this is < instead of <= to keep other optimal solutions
            if (m[make_tuple(i,j,d)] < score){
                continue;
            } else{
                m[make_tuple(i,j,d)] = score;
            }
        }
        //check straight
        if (nums[i+dir[d][0]][j+dir[d][1]] == 'E'){
            if (score+1 > endScore){
                break;
            } else{
                get<4>(curr).push_back({i,j});
                for (pair<int,int> p : get<4>(curr)){
                    if (s.find(p)==s.end()){
                        s.insert(p);
                        count++;
                    }
                }
            }
        }
        //push straight
        if (nums[i+dir[d][0]][j+dir[d][1]] != '#'){
            vector<pair<int,int>> v(get<4>(curr));
            v.push_back({i,j});
            pq.push({score+1, i+dir[d][0],j+dir[d][1], d, v});
        }
        //turn right
        d = (d + 1)%4;
        if (nums[i+dir[d][0]][j+dir[d][1]] == 'E'){
            
            if (score+1001 > endScore){
                break;
            } else{
                get<4>(curr).push_back({i,j});
                for (pair<int,int> p : get<4>(curr)){
                    if (s.find(p)==s.end()){
                        s.insert(p);
                        count++;
                    }
                }
            }
        }
        //push right
        if (nums[i+dir[d][0]][j+dir[d][1]] != '#'){
            vector<pair<int,int>> v(get<4>(curr));
            v.push_back({i,j});
            pq.push({score+1001, i+dir[d][0],j+dir[d][1], d,v});
        }
        //turn left
        d = (d - 2 + 4)%4;
        if (nums[i+dir[d][0]][j+dir[d][1]] == 'E'){
            if (score+1001 > endScore){
                break;
            } else{
                get<4>(curr).push_back({i,j});
                for (pair<int,int> p : get<4>(curr)){
                    if (s.find(p)==s.end()){
                        s.insert(p);
                        count++;
                    }
                }
            }
        }
        //push left
        if (nums[i+dir[d][0]][j+dir[d][1]] != '#'){
            vector<pair<int,int>> v(get<4>(curr));
            v.push_back({i,j});
            pq.push({score+1001, i+dir[d][0],j+dir[d][1], d,v});
        }
    }
    // + 1 to include the E
    cout<<1+count<<endl;
}

int main(){
    
    
    
    cout<<"part 1 sample"<<endl;
    long long a = solve1("a.txt");
    cout<<"part 1 full"<<endl;
    long long b = solve1("b.txt");

    cout<<"part 2 sample"<<endl;
    solve2("a.txt", a);
    cout<<"part 2 full"<<endl;
    auto start = high_resolution_clock::now();
    solve2("b.txt", b);
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<microseconds>(stop - start);

    cout << "Time taken by function: "
         << duration.count() << " microseconds" << endl;
    return 0;
}