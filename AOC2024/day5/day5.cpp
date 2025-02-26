#include <bits/stdc++.h>
using namespace std;

void solve1(string txt){
    ifstream cin(txt);
    set<pair<int,int>> num;
    string s;
    while (getline(cin, s) && s.length() > 0){
        num.emplace(make_pair(stoi(s.substr(0,2)), stoi(s.substr(3,2))));
    }
    int count = 0;
    while(cin>>s){
        s+=',';
        vector<int> test;
        while(s.length() >= 2){
            test.push_back(stoi(s.substr(0,2)));
            s = s.substr(3);
        }

        bool works = true;
        for (int i = 0; i < test.size()&&works; i++){
            for (int j = i + 1; works&&j < test.size(); j++){
                if (num.find(make_pair(test[i],test[j])) == num.end()){
                    works = false;
                }
            }
        }
        if (works){
            count+=test[test.size()/2];
        }
    }
    printf("%d\n", count);
}

void solve2(string txt){
    ifstream cin(txt);
    set<pair<int,int>> num;
    string s;
    
    while (getline(cin, s) && s.length() > 0){
        num.emplace(make_pair(stoi(s.substr(0,2)), stoi(s.substr(3,2))));
    }
    int count = 0;
    while(cin>>s){
        s+=',';
        vector<int> test;
        while(s.length() >= 2){
            test.push_back(stoi(s.substr(0,2)));
            s = s.substr(3);
        }

        bool works = true;
        for (int i = 0; i < test.size(); i++){
            for (int j = i + 1; j < test.size(); j++){
                if (num.find(make_pair(test[i],test[j])) == num.end()){
                    works = false;
                    swap(test[i],test[j]);
                    j=i;
                }
            }
        }
        if (!works){
            count+=test[test.size()/2];
        }
    }
    printf("%d\n", count);
}

int main(){
    cout<<"part 1a"<<endl;
    solve1("a.txt");
    cout<<"part 1b"<<endl;
    solve1("b.txt");

    cout<<"part 2a"<<endl;
    solve2("a.txt");
    cout<<"part 2b"<<endl;
    solve2("b.txt");
    return 0;
}