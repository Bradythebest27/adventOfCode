#include <bits/stdc++.h>
using namespace std;
//g++ day.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
set<pair<long long,long long>> seenA;
set<pair<long long,long long>> seenP;

char nums[200][200];
pair<long long,long long> dir[4] = {{1,0},{0,1},{-1,0},{0,-1}};
bool valid(long long i, long long j, long long s){
    return i>=0&&j>=0&&i<s&&j<s;
}
long long pdfs(long long i, long long j, long long size){
    seenP.insert({i,j});
    long long c=0;
    for (auto d : dir){
        if (!valid(i+d.first,j+d.second,size)){
            c++;
        }else if (nums[i][j] != nums[i+d.first][j+d.second]){
            c++;
        }else if (seenP.find({i+d.first,j+d.second}) == seenP.end()){
            c+=pdfs(i+d.first,j+d.second,size);
        }
    }
    return c;
}
long long adfs(long long i, long long j, long long size){
    seenA.insert({i,j});
    long long c = 1;
    for (auto d : dir){
        if (valid(i+d.first,j+d.second,size) && nums[i][j] == nums[i+d.first][j+d.second] && seenA.find({i+d.first,j+d.second}) ==seenA.end()){
            c+=adfs(i+d.first,j+d.second,size);
        }
    }
    return c;
}

void solve1(string txt){
    seenA.clear();
    seenP.clear();
    ifstream cin(txt);
    long long count = 0;
    string s;
    cin>>s;
    long long size = s.length();
    for (long long i = 0; i < size; i++){
        nums[0][i] = s[i];
    }
    for (long long i = 1; i < size; i++){
        cin>>s;
        for (long long j = 0; j < size; j++){
            nums[i][j] = s[j];
        }
    }
    for (long long i = 0; i < size; i++){
        for (long long j = 0; j < size; j++){
            if (seenA.find({i,j}) == seenA.end() && seenP.find({i,j}) == seenP.end()){
                long long area = adfs(i,j,size);
                long long perim = pdfs(i,j,size);
                count+=area*perim;
            }
        }
    }
    cout<<count<<endl;
}
set<tuple<int,int,int,int>> foundS;

long long sides(int i, int j, int size){
    seenP.insert({i,j});
    long long c=0;
    for (int p = 0; p <4; p++){
        pair<int,int> d = dir[p];
        if ((!valid(i+d.first,j+d.second,size)) || (nums[i][j] != nums[i+d.first][j+d.second])){
            foundS.insert({i,j,i+d.first,j+d.second});
            foundS.insert({i+d.first,j+d.second,i,j});
            int ti = dir[(p+1)%4].first;
            int tj = dir[(p+1)%4].second;
            int si = dir[(p+3)%4].first;
            int sj = dir[(p+3)%4].second;
            cout<<endl<<d.first<<" "<<d.second<<endl;
            cout<<ti<<" "<<tj<<" "<<si<<" "<<sj<<" "<<endl;
            if (foundS.find({i+ti,j+tj,i+d.first+ti,j+d.second+tj}) == foundS.end() && 
                foundS.find({i+d.first+ti,j+d.second+tj,i+ti,j+tj}) == foundS.end())
                c++;
        }else if (seenP.find({i+d.first,j+d.second}) == seenP.end()){
            c+=sides(i+d.first,j+d.second,size);
        }
    }
    return c;
}
void solve2(string txt){
    seenA.clear();
    seenP.clear();
    ifstream cin(txt);
    long long count = 0;
    string s;
    cin>>s;
    long long size = s.length();
    for (long long i = 0; i < size; i++){
        nums[0][i] = s[i];
    }
    for (long long i = 1; i < size; i++){
        cin>>s;
        for (long long j = 0; j < size; j++){
            nums[i][j] = s[j];
        }
    }
    for (long long i = 0; i < size; i++){
        for (long long j = 0; j < size; j++){
            if (seenA.find({i,j}) == seenA.end() && seenP.find({i,j}) == seenP.end()){
                long long area = adfs(i,j,size);
                long long sid = sides(i,j,size);
                cout<<nums[i][j]<<" "<<area<<" "<<sid<<endl;
                count+=area*sid;
            }
        }
    }
    cout<<count<<endl;
    
}

int main(){
    cout<<"\npart 1 sample"<<endl;
    solve1("a.txt");
    cout<<"\npart 1 full"<<endl;
    solve1("b.txt");

    cout<<"\npart 2 sample"<<endl;
    solve2("a.txt");
    cout<<"part 2 full"<<endl;
    // solve2("\nb.txt");
    return 0;
}