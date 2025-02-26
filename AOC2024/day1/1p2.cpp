#include <bits/stdc++.h>
using namespace std;
int main(){
    int l,r;
    vector<int> ll;
    unordered_map<int,int> count;
    ifstream in("1a.txt");
    while (in>>l>>r){
        ll.push_back(l);
        count[r]++;
    }
    int total = 0;
    for(int curr: ll){
        total+=curr*count[curr];
    }
    printf("%d\n",total);
    return 0;
}