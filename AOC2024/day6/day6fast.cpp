#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;
// g++ day6.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
bool valid(int x, int y, int size){
    return (x >= 0 && x < size && y >= 0 && y < size);
}
set<pair<int,int>> solve1(string txt){
    ifstream cin(txt);
    int count = 0;
    string s;
    cin>>s;
    int size = s.length();
    char nums[size][size];
    int startI = -1, startJ = -1;
    for (int i = 0; i < size; i++){
        nums[0][i] = s[i];
    }
    for (int i = 1; i < size; i++){
        cin>>s;
        for (int j = 0; j < size; j++){
            nums[i][j] = s[j];
            if (nums[i][j] == '^'){
                startI = i; startJ = j;
            }
        }
    }
    set<pair<int,int>> path;
    pair<int,int> dir[4] = {make_pair(-1,0), make_pair(0,1), make_pair(1,0), make_pair(0,-1)};
    int currDir = 0;
    nums[startI][startJ] = '?';
    count++;
    while(true){
        startI+=dir[currDir].first;
        startJ+=dir[currDir].second;
        if (startI < 0 || startI >= size || startJ < 0 || startJ >= size){
            break;
        }
        if (nums[startI][startJ] == '#'){
            startI-=dir[currDir].first;
            startJ-=dir[currDir].second;
            currDir = (currDir+1)%4;
            continue;
        }
        if (nums[startI][startJ] != '?'){
            path.insert({startI,startJ});
            count++;
            nums[startI][startJ] = '?';
        }
    }
    cout<<count<<endl;
    return path;
}



void solve2(string txt, set<pair<int,int>> path){
    ifstream cin(txt);
    int count = 0;
    string s;
    cin>>s;
    int size = s.length();
    char nums[size][size];
    int startI = -1, startJ = -1;
    for (int i = 0; i < size; i++){
        nums[0][i] = s[i];
    }
    for (int i = 1; i < size; i++){
        cin>>s;
        for (int j = 0; j < size; j++){
            nums[i][j] = s[j];
            if (nums[i][j] == '^'){
                startI = i; startJ = j;
            }
        }
    }
    int beginI = startI;
    int beginJ = startJ;
    pair<int,int> dir[4] = {make_pair(-1,0), make_pair(0,1), make_pair(1,0), make_pair(0,-1)};
    int currDir = 0;
    for (int i = 0; i < size; i++){
        for (int j = 0; j < size; j++){
            if (path.find({i,j}) == path.end()){
                continue;
            }
            if (nums[i][j] == '#' || nums[i][j] == '^'){
                continue;
            }
            startI = beginI;
            startJ = beginJ;
            currDir = 0;
            nums[i][j] = '#';
            set<pair<pair<int,int>,int>> seen;
            while(true){
                if (seen.find({{startI,startJ},currDir}) != seen.end()){
                    count++;
                    break;
                } else{
                    seen.insert({{startI,startJ},currDir});
                }
                startI+=dir[currDir].first;
                startJ+=dir[currDir].second;
                if (startI < 0 || startI >= size || startJ < 0 || startJ >= size){
                    break;
                }
                if (nums[startI][startJ] == '#'){
                    startI-=dir[currDir].first;
                    startJ-=dir[currDir].second;
                    currDir = (currDir+1)%4;
                    continue;
                }
            }
            nums[i][j] = '.';
        }
    }
    cout<<count<<endl;
}

int main(){
    cout<<"part 1a"<<endl;
    set<pair<int,int>> one = solve1("a.txt");
    cout<<"part 1b"<<endl;
    set<pair<int,int>> two = solve1("b.txt");

    cout<<"part 2a"<<endl;
    solve2("a.txt", one);
    cout<<"part 2b"<<endl;
    solve2("b.txt", two);
    auto start = high_resolution_clock::now();
    
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<microseconds>(stop - start);

    cout << "Time taken by function: "
         << duration.count() << " microseconds" << endl;
    return 0;
}