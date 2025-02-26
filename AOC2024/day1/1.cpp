#include <bits/stdc++.h>
using namespace std;
int main(){
    int l,r;
    priority_queue<int> pq1;
    priority_queue<int> pq2;
    ifstream in("1b.txt");
    while (in>>l>>r){
        pq1.push(l);
        pq2.push(r);
    }
    int total = 0;
    while(pq1.size() != 0){
        total+=abs(pq1.top() - pq2.top());
        pq1.pop();
        pq2.pop();
    }
    printf("%d\n",total);
    return 0;
}