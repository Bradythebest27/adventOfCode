#include <bits/stdc++.h>
using namespace std;

void solve1(string txt){
    ifstream cin(txt);
    string s;
    int count = 0;
    while(getline(cin,s)){
        stringstream l(s);
        int prev,curr;
        l>>curr;
        bool works = true;
        bool p = false;
        bool m = false;
        while(l>>prev){
            if (curr > prev){
                p = true;
            } else{
                m = true;
            }
            if (abs(prev - curr) > 3 || prev == curr || !(p^m)){
                works = false;
            }
            curr = prev;
        }
        if (works){
            count++;
        }
        
    }
    cout<<count<<endl;
}

void solve2(string txt){
    ifstream cin(txt);
    string s;
    int count = 0;
    while(getline(cin,s)){
        stringstream l(s);
        vector<int> temp;
        int x;
        while(l>>x){
            temp.push_back(x);
        }
        for (int rem = 0; rem < temp.size(); rem++){
            stringstream removed;
            for (int i = 0; i < temp.size(); i++){
                if (rem != i){
                    removed<<temp[i]<<" ";
                }
            }
            int prev,curr;
            removed>>curr;
            bool w = true, p = false, m = false;
            while(removed>>prev){
                if (curr > prev){
                    p = true;
                } else{
                    m = true;
                }
                if (abs(prev - curr) > 3 || prev == curr || !(p^m)){
                    w = false;
                }
                curr = prev;
            }
            if (w){
                count++;
                break;
            }
        }
    }
    cout<<count<<endl;
}

int main(){
    cout<<"part a"<<endl;
    solve2("a.txt");
    cout<<"part b"<<endl;
    solve2("b.txt");
    return 0;
}