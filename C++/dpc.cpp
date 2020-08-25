
#include <iostream>
#include <cstring>
#include <algorithm>
using namespace std;

int dp[100010][3];
int h[100010][3];
int a, b, c, N;
int main(){
    scanf("%d", &N);
    for (int i = 1; i <= N; i++){
        scanf("%d%d%d", &h[i][0],&h[i][1],&h[i][2]);
    }

    memset(dp, -1, sizeof(dp));
    dp[1][0] = h[1][0], dp[1][1] = h[1][1], dp[1][2] = h[1][2];
    for (int i = 2; i <= N; i++){
        dp[i][0] = max(dp[i-1][1], dp[i-1][2]) + h[i][0];
        dp[i][1] = max(dp[i-1][0], dp[i-1][2]) + h[i][1];
        dp[i][2] = max(dp[i-1][1], dp[i-1][0]) + h[i][2];
    }
    printf("%d", max(max(dp[N][0], dp[N][1]), dp[N][2]));
    return 0;
}
