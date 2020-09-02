//
// Created by Asorn on 2020-09-01.
//

#include <bits/stdc++.h>
using namespace std;
const int MAXN = 1e5;
const int INF = 0x3F3F3F3F;

int N, M;

int arr[MAXN], minsegtree[131072 * 2 - 1], gcdsegtree[131072 * 2 - 1], qsegtree[131072 * 2 - 1];

int gcd(int a, int b){
    if (a == 0){
        return b;
    }
    return gcd(b%a, a);
}

void initialize_min(int in[], int segtree[], int l, int r, int ind){
    if (l == r){
        segtree[ind] = in[l];
        return;
    }
    int mid = (l + r)/2;
    initialize_min(in, segtree, l, mid, 2 * ind + 1);
    initialize_min(in, segtree, mid+1, r, 2*ind + 2);
    segtree[ind] = min(segtree[2*ind+1], segtree[2*ind+2]);
}

void initialize_gcd(int in[], int segtree[], int l, int r, int ind){
    if (l == r){
        segtree[ind] = in[l];
        return;
    }
    int mid = (l + r)/2;
    initialize_gcd(in, segtree, l, mid, 2 * ind + 1);
    initialize_gcd(in, segtree, mid+1, r, 2 * ind + 2);
    segtree[ind] = gcd(segtree[2*ind+1], segtree[2*ind+2]);
}

void update_min(int segtree[], int uind, int l, int r, int ind, int val){
    if (l > r){
        return;
    }
    if (uind > r || uind < l){
        return;
    }

    if (l == uind && r == uind){
        segtree[ind] = val;
        return;
    }

    int mid = (l + r)/2;
    update_min(segtree, uind, l, mid, 2*ind + 1, val);
    update_min(segtree, uind, mid+1, r, 2*ind+2, val);
    segtree[ind] = min(segtree[2*ind + 1], segtree[2*ind + 2]);

}


void update_gcd(int segtree[], int uind, int l, int r, int ind, int val){
    if (l > r){
        return;
    }
    if (uind > r || uind < l){
        return;
    }

    if (l == uind && r == uind){
        segtree[ind] = val;
        return;
    }

    int mid = (l + r)/2;
    update_gcd(segtree, uind, l, mid, 2*ind + 1, val);
    update_gcd(segtree, uind, mid+1, r, 2*ind+2, val);
    segtree[ind] = gcd(segtree[2*ind + 1], segtree[2*ind + 2]);

}


int querygcd(int segtree[], int ql, int qr, int l, int r, int ind){
    if (ql <= l && qr >= r){
        return segtree[ind];
    }
    if (ql > r || qr < l){
        return INF;
    }
    int mid = (l + r)/2;
    int q1 = querygcd(segtree, ql, qr, l, mid, 2 * ind + 1);
    int q2 = querygcd(segtree, ql, qr, mid+1, r, 2 * ind + 2);
    if (q1 == INF && q2 == INF){
        return INF;
    }
    if (q1 == INF){
        return q2;
    }
    if (q2 == INF){
        return q1;
    }
    return gcd(q1, q2);
}

int querymin(int segtree[], int ql, int qr, int l, int r, int ind){
    if (ql <= l && qr >= r){
        return segtree[ind];
    }
    if (ql > r || qr < l){
        return INF;
    }
    int mid = (l + r)/2;
    return min(querymin(segtree, ql, qr, l, mid, 2*ind+1), querymin(segtree, ql, qr, mid+1, r, 2*ind+2));
}

int query_q(int ql, int qr, int l, int r, int ind){
    if (l == r){
        return 1;
    }
    if (ql > r || qr < l){
        return 0;
    }
    int mid = (l + r)/2;
    int q1 = querymin(minsegtree, ql, qr, l, mid, 2*ind+1);
    int q2 = querymin(minsegtree, ql, qr, mid + 1, r, 2 * ind + 2);
    int c1 = querygcd(gcdsegtree, ql, qr, l, mid, 2*ind+1);
    int c2 = querygcd(gcdsegtree, ql, qr, mid + 1, r, 2 * ind + 2);
    if (c1 == c2 && q1 == c1 && q2 == c2 && q2 == q1){
        return query_q(ql, qr, l, mid, 2 * ind + 1) + query_q(ql, qr, mid + 1, r, 2 * ind + 2);
    }
    if (q1 == c1 && q2 == c2){
        int a1 = query_q(ql, qr, l, mid, 2 * ind + 1);
        int a2 = query_q(ql, qr, mid + 1, r, 2 * ind + 2);
        if (c1 <= c2){
            return a1;
        }
        else{
            return a2;
        }
    }
    if(c1 == q1){
        return query_q(ql, qr, l, mid, 2 * ind + 1);
    }
    if(c2 == q2){
        return query_q(ql, qr, mid + 1, r, 2 * ind + 2);
    }
    return 0;

}

//int nextp(int a){
//    a--;
//    a |= a >> 1;
//    a |= a >> 2;
//    a |= a >> 4;
//    a |= a >> 8;
//    a |= a >> 16;
//    a++;
//    return a;
//}

int main(){
    scanf("%d%d", &N, &M);
    for (int i = 0; i < N; i++){
        scanf("%d", &arr[i]);
    }
    memset(minsegtree, INF, sizeof(minsegtree));
    memset(gcdsegtree, INF, sizeof(gcdsegtree));
    initialize_min(arr, minsegtree, 0, N-1, 0);
    initialize_gcd(arr, gcdsegtree, 0, N-1, 0);
    char cur;
    for (int i = 0; i < M; i++){
        scanf("%s", &cur);
        if (cur == 'M'){
            int cl, cr;
            scanf("%d%d", &cl, &cr);
            printf("%d\n", querymin(minsegtree, cl-1, cr-1, 0, N-1, 0));
        }
        if (cur == 'C'){
            int x, v;
            scanf("%d%d", &x, &v);
            update_gcd(gcdsegtree, x-1, 0, N-1, 0, v);
            update_min(minsegtree, x-1, 0, N-1, 0, v);
            arr[x-1] = v;
        }
        if (cur == 'G'){
            int cl, cr;
            scanf("%d%d", &cl, &cr);
            printf("%d\n", querygcd(gcdsegtree,cl-1, cr-1, 0, N-1, 0));
        }
        if (cur == 'Q'){
            int cl, cr;
            scanf("%d%d", &cl, &cr);
            printf("%d\n", querygcd(gcdsegtree, cl-1, cr-1, 0, N-1, 0) == querymin(minsegtree, cl-1, cr-1, 0, N-1, 0) ? query_q(cl-1, cr-1, 0, N-1, 0) : 0);
        }
    }
}
