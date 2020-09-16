//
// Created by Asorn on 2020-09-15.
//

#include <bits/stdc++.h>
using namespace std;

int N, K;
const int MAXN = 3001;
int tri[MAXN][MAXN];

void run(int curtri){
    if (curtri == 1){
        return;
    }
    if (curtri == 2){
        for (int i = 0; i <= (N - curtri); i++){
            for (int j = 0; j <= i; j++){
                tri[i][j] = max(tri[i][j], max(tri[curtri + i - 1][j], tri[curtri + i - 1][curtri + j - 1]));
            }
        }
        return;
    }
    else{
        int newcurtri = (2*curtri+2)/3;
        run(newcurtri);
        for (int i = 0; i <= (N - curtri); i++){
            for (int j = 0; j <= i; j++){
                tri[i][j] = max(max(tri[curtri + i - newcurtri][j], tri[curtri + i - newcurtri][curtri + j - newcurtri]), tri[i][j]);
            }
        }
        return;
    }
}

int main(){
    scanf("%d%d", &N, &K);
    long long ans = 0;
    for (int i = 0; i < N; i++){
        for (int j = 0; j <= i; j++){
            scanf("%d", &tri[i][j]);
        }
    }
    run(K);
    for (int i = 0; i <= N - K; i++){
        for (int j = 0; j <= i; j++){
            ans+=tri[i][j];
        }
    }
    printf("%lld\n", ans);
}
