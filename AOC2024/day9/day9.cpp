#include <bits/stdc++.h>
using namespace std;

using namespace std::chrono;
//g++ day9.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
void solve1(string txt){
    ifstream cin(txt);
    string s;
    cin>>s;
    vector<long long> nums;
    for (long long i = 0; i < s.length(); i+=2){
        for (int j = 0; j < s[i] - '0'; j++){
                nums.push_back(static_cast<int>(i/2));
        }
        if (i != s.length() - 1){  
            for (int j = 0; j < s[i+1] - '0'; j++){
                nums.push_back(-1);
            }
        }
    }
    for (int i = 0; i < nums.size(); i++){
        while (i < nums.size() && nums[i] != -1){
            i++;
        }
        while(i < nums.size() && nums[nums.size() - 1] == -1){
            nums.pop_back();
        }
        if (i < nums.size())
            swap(nums[i], nums[nums.size() - 1]);
    }
    long long total = 0;
    for (int i = 0; i < nums.size(); i++){
        total+=i*nums[i];
    }
    cout<<total<<endl;
}

void solve2(string txt){
    ifstream cin(txt);
    string input;
    cin>>input;
    input+='0';
    //          physical open space|amount left
    vector<pair<vector<long long>,long long>> actual(input.length() / 2);
    vector<bool> notMoved;
    //             val      first ind   len
    vector<tuple<long long, long long, long long>> movable; //all first half
    long long ind = 0;
    for (long long i = 0; i < input.length() / 2; i++){
        long long f = input[2 * i] - '0';
        long long s = input[2 * i + 1] - '0';
        actual[i].second = s;
        movable.push_back({i, ind, f});
        notMoved.push_back(true);
        ind+= f + s;
    }
    for (long long i = input.length() / 2 - 1; i >= 0; i--){
        long long findSize = get<2>(movable[i]);
        for (long long j = 0; j < i; j++){
            if (actual[j].second >= findSize){
                notMoved[i] = false;
                for (long long k = 0; k < get<2>(movable[i]); k++){
                    actual[j].first.push_back(get<0>(movable[i]));
                }
                actual[j].second-=get<2>(movable[i]);
                break;
            }
        }
    }
    long long total = 0;
    for (long long i = 0; i < input.length() / 2; i++){
        if (notMoved[i]){
            long long ind = get<1>(movable[i]);    
            for (long long k = 0; k < get<2>(movable[i]); k++){
                total+=get<0>(movable[i]) * ind;
                ind++;
            }
        }
        //filled/not filled empty space
        long long ind = get<1>(movable[i]) + get<2>(movable[i]);
        for (long long k = 0; k < actual[i].first.size(); k++){
            total+=ind * actual[i].first[k];
            ind++;
        }
    }
    cout<<total<<endl;
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
    // cout<<"part 2b"<<endl;
    // auto start = high_resolution_clock::now();
    // solve2("b.txt");
    // auto stop = high_resolution_clock::now();
    // auto duration = duration_cast<microseconds>(stop - start);

    // cout << "Time taken by function: "
    //      << duration.count() << " microseconds" << endl;
    
    return 0;
}