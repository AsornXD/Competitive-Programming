//
// Created by Asorn on 2020-09-04.
//

#include <bits/stdc++.h>
#include <string>
using namespace std;

int N, C;
int i;

int main(){
    scanf("%d%d", &N, &C);
    string cur;
    int curc;
    for (i = 0; i < N; i++){
        cin >> cur >> curc;
        if (curc > C){
            cout << cur << " will advance\n";
        }
        else{
            cout << cur << " will not advance\n";
        }
    }
}