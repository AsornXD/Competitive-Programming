//
// Created by Asorn on 2020-09-24.
//

#include <bits/stdc++.h>
const int MAXHW = 251;
using namespace std;

int W, H, N;

int psa[MAXHW][MAXHW], arr[MAXHW][MAXHW];

//int getmaxsum(int curw, int curh){
////    printf("%d %d\n", curw, curh);
//    int maxsum = 0;
//    for (int j = 0; j < H - curh; j++){
//        for (int k = 0; k < W - curw; k++){
////            printf("%d %d\n", j + curw, k + curh);
//            if (j == 0 && k == 0){
//                maxsum = max(maxsum, psa[j + curh][k + curw]);
//            }
//            else if (j == 0){
////                printf("check: %d %d\n", psa[j + curw][k + curh], psa[j + curw][k]);
//                maxsum = max(maxsum, psa[j + curh][k + curw] - psa[j + curh][k]);
//            }
//            else if (k == 0){
//                maxsum = max(maxsum, psa[j + curh][k + curw] - psa[j][k + curw]);
//            }
//            else {
//                maxsum = max(maxsum, psa[j + curh][k + curw] - psa[j][k + curw] - psa[j + curh][k] + psa[j][k]);
////                printf("%d %d %d %d %d\n", maxsum, j, k, curw, curh);
//            }
//        }
//    }
//    return maxsum;
//}

//int run(int maxlen, int curh, int curw){
//    int ret = 0;
//    for (int i = 1; i <= maxlen; i++){
//        if (N % i == 0){
//
//        }
//    }
//     return ret;
//}

int main(){
    scanf("%d%d%d", &W, &H, &N);
    int maxsum = 0;
    for (int i = 1; i <= H; i++){
        for (int j = 1; j <= W; j++){
            scanf("%d", &psa[i][j]);
            psa[i][j] += psa[i-1][j] + psa[i][j-1] - psa[i-1][j-1];
            for (int k = 1; k <= i; k++){
                maxsum = max(maxsum, psa[i][j] - psa[i - k][j] - psa[i][j - min(N / k, j)] + psa[i - k][j - min(N / k, j)]);
            }
        }
    }
    printf("%d", maxsum);
//    printf("%d", psa[1][0]);
}
