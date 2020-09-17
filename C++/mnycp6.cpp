//
// Created by Asorn on 2020-09-16.
//

#include <bits/stdc++.h>
#include <string>
#include <iostream>
using namespace std;

const int MAXN = 10010;
int bit[MAXN];
int C;
unordered_map<string, int> inds;
int arr[MAXN];

void add(int ind, int val){
    bit[ind] += val;
    while (ind < MAXN){
        ind += (ind & -ind);
        bit[ind] += val;
    }
}

int sum (int ind){
    int t = bit[ind];
    while (ind > 0){
        ind -= (ind & -ind);
        t += bit[ind];
    }
    return t;
}

int main(){
    cin.sync_with_stdio(0);
    cin.tie(0);
    cin >> C;
//    memset(bit, 0, sizeof(bit));
    int curind = 0;
    for (int i = 0; i < C; i++){
        string X, Y, q;
        cin >> q;
        if (q == "A"){
            int tot = 0;
            cin >> Y;
            for (char& curchar : Y){
                tot += (curchar - 'a' + 1);
            }
            if (inds.find(Y) != inds.end()){
                cout << "Can't add " << Y.c_str() << "\n";
            }
            else {
                curind++;
                inds[Y] = curind;
                arr[curind] = tot;
                add(curind, arr[curind]);
            }
        }
        else if (q == "N"){
            cout << curind << "\n";
        }
        else if (q == "R"){
            int v2 = 0;
            cin >> X >> Y;
            for (char& curchar : Y){
                v2 += (curchar - 'a' + 1);
            }
            int ind1 = inds[X];
            add(ind1, v2 - arr[ind1]);
            arr[ind1] = v2;
            inds.erase(X);
            inds[Y] = ind1;
        }
        else if (q == "S"){
            cin >> X >> Y;
            int ind1 = inds[X];
            int ind2 = inds[Y];
            add(ind1, arr[ind2] - arr[ind1]);
            add(ind2, arr[ind1] - arr[ind2]);
            swap(arr[ind1], arr[ind2]);
            inds[X] = ind2;
            inds[Y] = ind1;
        }
        else if (q == "M"){
            cin >> X >> Y;
            int ind1 = inds[X];
            int ind2 = inds[Y];
            if (ind2 > ind1){
                swap(ind1, ind2);
            }
            cout << (sum(ind1) - sum(ind2-1)) << "\n";
        }
    }
}