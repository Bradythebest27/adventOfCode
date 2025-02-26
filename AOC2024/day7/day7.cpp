#include <bits/stdc++.h>
using namespace std;
//g++ day7.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }

bool help(vector<long long> v, long long targ){
    // cout<<v.size()<<endl;
    vector<long long> in;
    in.push_back(v[0]);
    vector<long long> out;
    for (int i = 1; i < v.size(); i++){
        for (int j = 0; j < in.size(); j++){
            out.push_back(in[j]+v[i]);
            out.push_back(in[j]*v[i]);
        }
        

        in.clear();
        for (long long curr : out){
            if (curr <= targ)
            in.push_back(curr);
        }
        out.clear();
    }
    
    for (long long curr : in){
        if (curr == targ){
            return true;
        }
    }
    return false;
}
bool help2(vector<long long> v, long long targ){
    // cout<<v.size()<<endl;
    vector<long long> in;
    in.push_back(v[0]);
    vector<long long> out;
    for (int i = 1; i < v.size(); i++){
        for (int j = 0; j < in.size(); j++){
            out.push_back(in[j]+v[i]);
            out.push_back(in[j]*v[i]);
            out.push_back(stoll(to_string(in[j])+to_string(v[i])));
        }
        

        in.clear();
        for (long long curr : out){
            if (curr <= targ)
            in.push_back(curr);
        }
        out.clear();
    }
    
    for (long long curr : in){
        if (curr == targ){
            return true;
        }
    }
    return false;
}

void solve1(string txt){
    ifstream cin(txt);
    string s;
    long long count = 0;
    while (getline(cin,s)){
        stringstream ss(s);
        long long targ;
        ss>>targ;
        char c;
        ss>>c;
        vector<long long > v;
        long long temp;
        while (ss>>temp)
            v.push_back(temp);
        if (help(v, targ)){
            count+=targ;
        }
    }
    cout<<count<<endl;
}

void solve2(string txt){
    ifstream cin(txt);
    string s;
    long long count = 0;
    while (getline(cin,s)){
        stringstream ss(s);
        long long targ;
        ss>>targ;
        char c;
        ss>>c;
        vector<long long > v;
        long long temp;
        while (ss>>temp)
            v.push_back(temp);
        if (help2(v, targ)){
            count+=targ;
        }
    }
    cout<<count<<endl;
    
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