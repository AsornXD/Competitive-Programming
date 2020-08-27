

#include <bits/stdc++.h>
using namespace std;




int main(){
    int N, W;
    int dp[101][100001];
    int weights[110];
    int values[110];
    scanf("%d%d", &N, &W);
    for (int i = 1; i <= N; i++){
        scanf("%d%d", &weights[i], &values[i]);
    }
    for (int i = 0; i < 102; i++){
        for (int j = 0; j < 100002; j++){
            dp[i][j] = -99999;
        }
    }
    dp[1][0] = 0;
    dp[1][weights[1]] = values[1];
    for (int i = 2; i <= N; i++){
        for (int j = 1; j <= W; j++) {
            if ((j - weights[i]) > 0) {
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weights[i]] == -99999 ? -99999:dp[i - 1][j - weights[i]] + values[i]);
            }
            else{
                dp[i][j] = dp[i-1][j];
            }
        }

    }


//    printf("%d", max((recurse(N, W)), (values[N] + recurse(N, W - weights[N]))));
}

