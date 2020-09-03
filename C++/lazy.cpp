//
// Created by Asorn on 2020-09-02.
//

#include <bits/stdc++.h>

typedef unsigned long long int ll;

using namespace std;

const int MAXN = 1e5;
const ll INF = 0xffffffffffffffff;
ll segtree[131072 * 2 - 1], in[MAXN], lazysegtree[131072 * 2 - 1], lazysegtree2[131072 * 2 - 1];
int N, Q;

void initialize_min(int l, int r, int ind){
    if (l == r){
        segtree[ind] = in[l];
        return;
    }
    int mid = (l + r)/2;
    initialize_min(l, mid, 2 * ind + 1);
    initialize_min(mid+1, r, 2*ind + 2);
    segtree[ind] = min(segtree[2*ind+1], segtree[2*ind+2]);
}

void update_min_lazy(int ql, int qr, int l, int r, int ind, int val){
    if (l > r) {
        return;
    }

    if (lazysegtree2[ind] != 0){
        segtree[ind] = lazysegtree2[ind];
        if (l != r){
            lazysegtree2[2*ind + 1] = lazysegtree2[ind];
            lazysegtree2[2*ind + 2] = lazysegtree2[ind];
            lazysegtree[2*ind + 1] = 0;
            lazysegtree[2*ind + 2] = 0;
        }
        lazysegtree2[ind] = 0;
    }

    if (lazysegtree[ind] != 0){
        segtree[ind] += lazysegtree[ind];
        if (l != r){
            lazysegtree[2*ind + 1]+=lazysegtree[ind];
            lazysegtree[2*ind + 2]+=lazysegtree[ind];

        }
        lazysegtree[ind] = 0;
    }

    if (ql > r || qr < l){
        return;
    }

    if (ql <= l && qr >= r){
        segtree[ind] += val;
        if (l != r){
            lazysegtree[2*ind + 1] += val;
            lazysegtree[2*ind + 2] += val;
        }
        return;
    }

    int mid = (l + r)/2;
    update_min_lazy(ql, qr, l, mid, 2*ind + 1, val);
    update_min_lazy(ql, qr, mid+1, r, 2*ind + 2, val);
    segtree[ind] = min(segtree[2*ind + 1], segtree[2*ind + 2]);
}

void update_min(int ql, int qr, int l, int r, int ind, int val){
    if (l > r){
        return;
    }

    if (lazysegtree2[ind] != 0){
        segtree[ind] = lazysegtree2[ind];
        if (l != r){
            lazysegtree2[2*ind + 1] = lazysegtree2[ind];
            lazysegtree2[2*ind + 2] = lazysegtree2[ind];
            lazysegtree[2*ind + 1] = 0;
            lazysegtree[2*ind + 2] = 0;
        }
        lazysegtree2[ind] = 0;
    }

    if (lazysegtree[ind] != 0){
        segtree[ind] += lazysegtree[ind];
        if (l != r){
            lazysegtree[2*ind + 1]+=lazysegtree[ind];
            lazysegtree[2*ind + 2]+=lazysegtree[ind];
        }
        lazysegtree[ind] = 0;
    }

    if (ql > r || qr < l){
        return;
    }

    if (ql <= l && qr >= r){
        segtree[ind] = val;
        if (l != r){
            lazysegtree2[2*ind + 1] = val;
            lazysegtree2[2*ind + 2] = val;
            lazysegtree[2*ind + 1] = 0;
            lazysegtree[2*ind + 2] = 0;
        }
        return;
    }
    int mid = (l + r)/2;
    update_min(ql, qr, l, mid, 2*ind + 1, val);
    update_min(ql, qr, mid+1, r, 2*ind+2, val);
    segtree[ind] = min(segtree[2*ind + 1], segtree[2*ind + 2]);
    return;
}

ll querymin(int ql, int qr, int l, int r, int ind){
    if (l > r){
        return INF;
    }

    if (lazysegtree2[ind] != 0){
        segtree[ind] = lazysegtree2[ind];
        if (l != r){
            lazysegtree2[2*ind + 1] = lazysegtree2[ind];
            lazysegtree2[2*ind + 2] = lazysegtree2[ind];
            lazysegtree[2*ind + 1] = 0;
            lazysegtree[2*ind + 2] = 0;
        }
        lazysegtree2[ind] = 0;
    }

    if (lazysegtree[ind] != 0){
        segtree[ind] += lazysegtree[ind];
        if (l != r){
            lazysegtree[2*ind + 1]+=lazysegtree[ind];
            lazysegtree[2*ind + 2]+=lazysegtree[ind];
        }
        lazysegtree[ind] = 0;
    }

    if (ql > r || qr < l){
        return INF;
    }

    if (ql <= l && qr >= r){
        return segtree[ind];
    }

    int mid = (l + r)/2;
    return min(querymin(ql, qr, l, mid, 2*ind+1), querymin(ql, qr, mid+1, r, 2*ind+2));
}

int main(){
    scanf("%d%d", &N, &Q);
    for (int i = 0; i < N; i++){
        scanf("%lld", &in[i]);
    }
    memset(lazysegtree, 0, sizeof(lazysegtree));
    memset(lazysegtree2, 0, sizeof(lazysegtree2));
    fill(segtree, segtree + (131072 * 2 - 1), 0xffffffffffffffff);
    initialize_min(0, N-1, 0);
    int a, l, r, v;
    for (int i = 0; i < Q; i++){
        scanf("%d", &a);

        if(a == 1){
            scanf("%d%d%d", &l, &r, &v);
            update_min_lazy(l-1, r-1, 0, N-1, 0, v);
        }
        if (a == 2){
            scanf("%d%d%d", &l, &r, &v);
            update_min(l-1, r-1, 0, N-1, 0, v);
        }
        else if (a == 3){
            scanf("%d%d", &l, &r);
            printf("%lld\n", querymin(l-1, r-1, 0, N-1, 0));
        }
    }
}
