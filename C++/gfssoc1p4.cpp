//
// Created by Asorn on 2020-09-11.
//


#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
const int MAXN = 251;

ll bit[MAXN][MAXN][MAXN];

int N, Q;

ll query (int x, int y, int z){
    ll result = 0;
    int yt = y, xt = x, zt = z;
    for (x = xt; x > 0; x-= (x & -x)){
        for (y = yt; y > 0; y-= (y & -y)){
            for (z = zt; z > 0; z -= (z & -z)){
                result+=bit[x][y][z];
            }
        }
    }
    return result;
}

void update(int x, int y, int z, int val){
    int yt = y, xt = x, zt = z;
    for (x = xt; x <= N; x += (x & -x)){
        for (y = yt; y <= N; y += (y & -y)){
            for (z = zt; z <= N; z += (z & -z)){
                bit[x][y][z] += val;
            }
        }
    }
}
ll t = 0;
int i;
int main(){
    scanf("%d", &N);
    scanf("%d", &Q);

    for (i = 0; i < Q; i++){
        char check;
        scanf("%s", &check);
        if (check == 'C'){
            int x, y, z, l;
            scanf("%d%d%d%d", &x, &y, &z, &l);
            update(x, y, z, l - (query(x, y, z)
                                 - query(x-1, y, z) - query(x, y-1, z) - query(x, y, z-1)
                                 + query(x, y-1, z-1) + query(x-1, y, z-1) + query(x-1, y-1, z)
                                 - query(x-1, y-1, z-1)));
        }
        if (check == 'S'){
            int x1, y1, z1, x2, y2, z2;
            scanf("%d%d%d%d%d%d", &x1, &y1, &z1, &x2, &y2, &z2);
            t += (query(x2, y2, z2)
                    - query(x1-1, y2, z2) - query(x2, y1-1, z2) - query(x2, y2, z1-1)
                    + query(x2, y1-1, z1-1) + query(x1-1, y2, z1-1) + query(x1-1, y1-1, z2)
                    - query(x1-1, y1-1, z1-1));
        }
    }
    printf("%lld", t);
}
