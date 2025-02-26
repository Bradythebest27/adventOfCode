#include <bits/stdc++.h>
using namespace std;

int dir[8][2] = {{1,0},{1,-1},{1,1},{0,-1},{0,1},{-1,0},{-1,1},{-1,-1}};


void solve1(string txt){
    ifstream cin(txt);
    string s;
    cin>>s;
    int size = s.length();
    char num[size][size];
    for (int i = 0; i < size; i++){
        num[0][i] = s[i];
    }
    for (int i = 1; i < size; i++){
        cin>>s;
        for (int j = 0; j < size; j++){
            num[i][j] = s[j];
        }
    }
    int count = 0;
    

    for (int i = 0; i < size; i++){
        for (int j = 0; j < size; j++){
            if (num[i][j] == 'X')
            for (int d = 0; d < 8; d++){
                int tempI = i + dir[d][0];
                int tempJ = j + dir[d][1];
                
                if (tempI < 0 || tempI >= size || tempJ < 0 || tempJ>=size){
                    continue;
                }
                
                if(num[tempI][tempJ] == 'M'){
                    tempI = tempI + dir[d][0];
                    tempJ = tempJ + dir[d][1];
                    // cout<<num[tempI][tempJ];
                    if (tempI < 0 || tempI >= size || tempJ < 0 || tempJ>=size){
                        continue;
                    }
                    
                    if(num[tempI][tempJ] == 'A'){
                        tempI = tempI + dir[d][0];
                        tempJ = tempJ + dir[d][1];
                        // cout<<"D";
                        if (tempI < 0 || tempI >= size || tempJ < 0 || tempJ>=size){
                            continue;
                        }
                        if (num[tempI][tempJ] == 'S'){
                            count++;
                            // cout<<"i "<<i<<" j "<<j<<endl;
                        }
                    }
                }
            }
        }
    }
    printf("%d\n",count);
}

string xmas[4]= {"MaMaAaSaS","SaSaAaMaM","MaSaAaMaS", "SaMaAaSaM"};

void solve2(string txt){
    ifstream cin(txt);
    string s;
    cin>>s;
    int size = s.length();
    char num[size][size];
    for (int i = 0; i < size; i++){
        num[0][i] = s[i];
    }
    for (int i = 1; i < size; i++){
        cin>>s;
        for (int j = 0; j < size; j++){
            num[i][j] = s[j];
        }
    }
    int count = 0;
    for (int i = 0; i < size - 2; i++){
        for (int j = 0; j < size - 2; j++){
            string s = "";
            for (int r = 0; r < 3; r++){
                for (int c = 0; c < 3; c++){
                    s= s+num[i+r][j+c];
                }
            }
            for (string curr: xmas){
                bool works = true;
                for (int i = 0; works&&i < 9; i++){
                    if (curr[i] != 'a' && curr[i]!=s[i]){
                        works=false;
                    }
                }
                if(works){
                    count++;
                    break;
                }
            }
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