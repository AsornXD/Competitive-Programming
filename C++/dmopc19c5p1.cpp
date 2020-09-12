//
// Created by Asorn on 2020-09-04.
//

#include <bits/stdc++.h>
using namespace std;
int N, M;

int main(){
    cin >> N >> M;
    vector<string> arr;
    string curstr;
    for (int i = 0; i< N; i++) {
        cin >> curstr;
        arr.push_back(curstr);
    }
    int c = 0;
    int t;
    bool check;
    for (int i = 0; i < M; i++){
        cin >> t;
        check = true;
        for (int j = 0; j < t; j++){
            cin >> curstr;
            if ((find(arr.begin(), arr.end(), curstr) == arr.end())){
                check = false;
            }
        }
//        cout << curstr << "\n";
        if (check){
            c++;
        }
    }
    cout << c;

}
