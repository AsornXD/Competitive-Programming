//
// Created by Asorn on 2020-11-01.
//

#include <bits/stdc++.h>
using namespace std;
int dp[300][300][300];
int N, K;

int check(int cur,int mn,int pl){
    if (dp[pl][cur][mn] != -1){
        return dp[pl][cur][mn];
    }
    if (cur == K-1 && pl >= mn){
        return 1;
    }
    if (cur != K){
        int num = 0;
        for (int i = mn; i <= pl; i++){
            num+=check(cur+1,i,pl-i);
        }
        dp[pl][cur][mn] = num;
        return num;
    }
    return 0;
}

int main(){
    scanf("%d%d", &N, &K);
    memset(dp, -1, sizeof(dp));
    printf("%d", check(0,1,N));
    return 0;
}
