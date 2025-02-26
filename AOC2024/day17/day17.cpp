#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;
//g++ day17.cpp -o out; if ($?) { ./out; Remove-Item ./out.exe }
//ctrl k + ctrl f to format
char nums[200][200];
int dir[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dir2[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
bool valid(int i,int j,int s){return i>=0&&i<s&&j>=0&&j<s;}
int dfsGrid(int i,int j,int s){for(int k=0;k<4;k++){if(valid(i+dir[k][0],j+dir[k][1],s)){return dfsGrid(i+dir[k][0],j+dir[k][1],s);}}return 0;}
int r(string t){ifstream cin(t);string s;cin>>s;for(int i=0;i<s.length();i++){nums[0][i]=s[i];}for(int i=1;i<s.length();i++){cin>>s;for(int j=0;j<s.length();j++){nums[i][j]=s[j];}}return s.length();}
void sort(vector<int>v){sort(v.begin(),v.end());}void sort(vector<long long>v){sort(v.begin(),v.end());}



void solve1(string txt, long long val){
    ifstream cin(txt);
    long long  a = val, b = 0, c = 0;
    long long x,y;
    vector<int> v;
    int temp;
    while (cin>>temp){
        v.push_back(temp);
    }
    for (int i = 0; i < v.size() - 1; i+=2){
        int x = v[i]; int y = v[i+1];
        switch(x){
            case 0:
                if (y < 4){
                    a = a/((int)(pow(2,y)));
                } else{
                    switch (y){
                        case 4:
                            a = a/((int)(pow(2,a)));
                            break;
                        case 5:
                            a = a/((int)(pow(2,b)));
                            break;
                        case 6:
                            a = a/((int)(pow(2,c)));
                            break;
                    }
                }
                break;
            case 1:
                b=b^y;
                break;
            case 2:
                if (y < 4){
                    b = y%8;
                } else{
                    switch (y){
                        case 4:
                            b = a%8;
                            break;
                        case 5:
                            b = b%8;
                            break;
                        case 6:
                            b = c%8;
                            break;
                    }
                }
                break;
            case 3:
                if (a != 0){
                    i = y - 2;
                }
                break;
            case 4:
                b = b^c;
                break;
            case 5:
                if (y < 4){
                    cout<<y<<",";
                } else{
                    switch (y){
                        case 4:
                            cout<<a%8<<",";
                            break;
                        case 5:
                            cout<<b%8<<",";
                            break;
                        case 6:
                            cout<<c%8<<",";
                            break;
                    }
                }
                break;
            case 6:
            if (y < 4){
                    b = a/((int)(pow(2,y)));
                } else{
                    switch (y){
                        case 4:
                            b = a/((int)(pow(2,a)));
                            break;
                        case 5:
                            b = a/((int)(pow(2,b)));
                            break;
                        case 6:
                            b = a/((int)(pow(2,c)));
                            break;
                    }
                }
                break;
            case 7:
                if (y < 4){
                    c = a/((int)(pow(2,y)));
                } else{
                    switch (y){
                        case 4:
                            c = a/((int)(pow(2,a)));
                            break;
                        case 5:
                            c = a/((int)(pow(2,b)));
                            break;
                        case 6:
                            c = a/((int)(pow(2,c)));
                            break;
                    }
                }
            
        }
    }
    cout<<endl;
}

string solve2(string txt, long long val){
    stringstream str;
    ifstream cin(txt);
    long long  a = val, b = 0, c = 0;
    long long x,y;
    vector<int> v;
    int temp;
    while (cin>>temp){
        v.push_back(temp);
    }
    for (int i = 0; i < v.size() - 1; i+=2){
        int x = v[i]; int y = v[i+1];
        switch(x){
            case 0:
                if (y < 4){
                    a = a/((int)(pow(2,y)));
                } else{
                    switch (y){
                        case 4:
                            a = a/((int)(pow(2,a)));
                            break;
                        case 5:
                            a = a/((int)(pow(2,b)));
                            break;
                        case 6:
                            a = a/((int)(pow(2,c)));
                            break;
                    }
                }
                break;
            case 1:
                b=b^y;
                break;
            case 2:
                if (y < 4){
                    b = y%8;
                } else{
                    switch (y){
                        case 4:
                            b = a%8;
                            break;
                        case 5:
                            b = b%8;
                            break;
                        case 6:
                            b = c%8;
                            break;
                    }
                }
                break;
            case 3:
                if (a != 0){
                    i = y - 2;
                }
                break;
            case 4:
                b = b^c;
                break;
            case 5:
                if (y < 4){
                    str<<to_string(y)<<",";
                } else{
                    switch (y){
                        case 4:
                            str<<to_string(a%8)<<",";
                            break;
                        case 5:
                            str<<to_string(b%8)<<",";
                            break;
                        case 6:
                            str<<to_string(c%8)<<",";
                            break;
                    }
                }
                break;
            case 6:
            if (y < 4){
                    b = a/((int)(pow(2,y)));
                } else{
                    switch (y){
                        case 4:
                            b = a/((int)(pow(2,a)));
                            break;
                        case 5:
                            b = a/((int)(pow(2,b)));
                            break;
                        case 6:
                            b = a/((int)(pow(2,c)));
                            break;
                    }
                }
                break;
            case 7:
                if (y < 4){
                    c = a/((int)(pow(2,y)));
                } else{
                    switch (y){
                        case 4:
                            c = a/((int)(pow(2,a)));
                            break;
                        case 5:
                            c = a/((int)(pow(2,b)));
                            break;
                        case 6:
                            c = a/((int)(pow(2,c)));
                            break;
                    }
                }
            
        }
    }
    return str.str();
}

int main(){
    cout<<"part 1 sample"<<endl;
    // solve1("a.txt", 729);
    cout<<"part 1 full"<<endl;
    string check = "2,4,1,5,7,5,1,6,4,2,5,5,0,3,3,0,";
    long long j = 3*pow(2,48) + 3*pow(2,42)+ 3*pow(2,39);
    j+=1*pow(2,30)+6*pow(2,27);
    cout<<j<<endl;exit(9);
    // long long i = 127943348822016;
                   //999999990000000
    //3*2^48
    //0*2^45
    //3*2^42
    //3*2^39
    //0*2^36
    //0*2^33
    //1*2^30
    //6*2^27
    //*2^24
    //*2^21
    //*2^18
    //*2^15
    //*2^12
    //*2^9
    //*2^6
    //*2^3
    //*2^0


    for (long long i = 0; i <= 8 * pow(2,24); i++){
        string tOut = solve2("b.txt", j+i);
        // cout<<tOut<<endl;
        if (i % 10000 == 0){
            cout<<i<<endl;
        }
        if (tOut == "2,4,1,5,7,5,1,6,4,2,5,5,0,3,3,0,"){
            cout<<i<<endl;
            break;
        }
    }
    // long long k = 107408777100000;
    // long long k = 127540348822016;
    // for (long long i = 107408777004000; true; i--){//10000000
        // string tOut = solve2("b.txt", i);
        // cout<<tOut<<endl;
        // cout<<i<<" "<<tOut<<endl;//.substr(tOut.length() - 16)<<endl;
        // if (tOut == "2,4,1,5,7,5,1,6,4,2,5,5,0,3,3,0,"){
            //                                  ,0,3,2,5,5,0,3,3,0,
            // cout<<i<<endl;
            // break;
        // }
        // break;
        // solve1();
    // }

    // cout<<"part 2 sample"<<endl;
    // solve2("a.txt");
    // cout<<"part 2 full"<<endl;
    // solve2("b.txt");
    return 0;
}