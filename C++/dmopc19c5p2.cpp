//
// Created by Asorn on 2020-09-04.
//

#include <bits/stdc++.h>

using namespace std;

int N, H;


int main(){
    scanf("%d%d", &N, &H);
    pair<char, int> marr[N];
    pair<char, int> oarr[N];
    int mH = H;
    int oH = H;
    for (int i = 0; i < N; i++){
        scanf("%s%d", &marr[i].first, &marr[i].second);
    }
    for (int i = 0; i < N; i++){
        scanf("%s%d", &oarr[i].first, &oarr[i].second);
    }
    if (marr[0].first == 'A'){
        oH-=marr[0].second;
        if (oH <= 0){
            printf("VICTORY");
            return 0;
        }
        if (oarr[0].first == 'A'){
            mH-=oarr[0].second;
        }
    }

    if (mH <= 0){
        printf("DEFEAT");
        return 0;
    }

    if (marr[0].first == 'D' && oarr[0].first == 'D'){
        mH-=marr[0].second;
    }

    if (mH <= 0){
        printf("DEFEAT");
        return 0;
    }
    for (int i = 1; i < N; i++){
        if (marr[i].first == 'A'){
            if (oarr[i-1].first == 'A'){
                oH-=marr[i].second;
            }
        }
        else if (marr[i].first == 'D'){
            if (oarr[i-1].first == 'D'){
                oH-=oarr[i-1].second;
            }
        }
        if (oH <= 0){
            printf("VICTORY");
            return 0;
        }

        if (oarr[i].first == 'A'){
            if (marr[i].first == 'A'){
                mH-=oarr[i].second;
            }
        }

        if (oarr[i].first == 'D'){
            if(marr[i].first == 'D'){
                mH-=marr[i].second;
            }
        }

        if (mH <= 0){
            printf("DEFEAT");
            return 0;
        }
    }

    if (oarr[N-1].first == 'D'){
        oH-=oarr[N-1].second;
    }
    if (oH <= 0){
        printf("VICTORY");
        return 0;
    }
    printf("TIE");
    return 0;
}

