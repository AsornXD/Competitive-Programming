//
// Created by Asorn on 2020-09-19.
//

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
const int MAXN = 2 * 1e5;
const ll INF = -1000000000000000000;
struct node{
    ll left, right, sumlr, maxlr;
};

ll N, Q, arr[MAXN];
//pii seginds[2 * MAXN + 1];
node seg[2 * MAXN + 1];

void initialize(int l, int r, int ind){
    if (l == r){
        seg[ind].left = arr[l];
        seg[ind].right= arr[l];
        seg[ind].maxlr = arr[l];
        seg[ind].sumlr = arr[l];
        return;
    }
    int mid = (l + r)/2;
    int l1 = 2 * ind + 1;
    int r1 = 2 * ind + 2;
    initialize(l, mid, l1);
    initialize(mid + 1, r, r1);
    seg[ind].sumlr = seg[l1].sumlr + seg[r1].sumlr;
    seg[ind].left = max(seg[l1].left, seg[l1].sumlr + seg[r1].left);
    seg[ind].right = max(seg[r1].right, seg[r1].sumlr + seg[l1].right);
    seg[ind].maxlr = max(max(seg[l1].maxlr, seg[r1].maxlr), seg[l1].right + seg[r1].left);
    return;
}

void update(int uind, int l, int r, int ind, ll val){
    if (l == uind && r == uind){
        arr[l] = val;
        seg[ind].left = arr[l];
        seg[ind].right= arr[l];
        seg[ind].maxlr = arr[l];
        seg[ind].sumlr = arr[l];
    }
    else{
        int mid = (l + r)/2;
        int l1 = 2 * ind + 1, r1 = 2 * ind + 2;
        uind <= mid ? update(uind, l, mid, l1, val) : update(uind, mid + 1, r, r1, val);
        seg[ind].sumlr = seg[l1].sumlr + seg[r1].sumlr;
        seg[ind].left = max(seg[l1].left, seg[l1].sumlr + seg[r1].left);
        seg[ind].right = max(seg[r1].right, seg[r1].sumlr + seg[l1].right);
        seg[ind].maxlr = max(max(seg[l1].maxlr, seg[r1].maxlr), seg[l1].right + seg[r1].left);
    }
}

node query(int ql, int qr, int l, int r, int ind){
    if (ql <= l && qr >= r){
        return seg[ind];
    }
    if (ql > r || qr < l){
        return {INF, INF, INF, INF};
    }
    int mid = (l + r)/2;
    int l1 = 2 * ind + 1, r1 = 2 * ind + 2;
    node q1 = query(ql, qr, l, mid, l1), q2 = query(ql, qr, mid + 1, r, r1);
    if (q1.left == INF){
        return q2;
    }
    if (q2.left == INF){
        return q1;
    }
    node curnode;
    curnode.sumlr = q1.sumlr + q2.sumlr;
    curnode.left = max(q1.left, q1.sumlr + q2.left);
    curnode.right = max(q2.right, q2.sumlr + q1.right);
    curnode.maxlr = max(max(q1.maxlr, q2.maxlr), q1.right + q2.left);
    return curnode;
}

int main(){
   scanf("%lld%lld", &N, &Q);
   for (int i = 0; i < N; i++){
       scanf("%lld", &arr[i]);
   }
   initialize(0, N-1, 0);
   char cur;
   for (int i = 0; i < Q; i++){
       scanf("%s", &cur);
       if (cur == 'S'){
           int ii;
           ll xx;
           scanf("%d%lld", &ii, &xx);
           update(ii-1, 0, N-1, 0, xx);
//           printf("a");
       }
       else{
           int cl, cr;
           scanf("%d%d", &cl, &cr);
//           printf("%d %d\n", seginds[3].first, seginds[3].second);
           printf("%lld\n", query(cl-1, cr-1, 0, N-1, 0).maxlr);
       }
   }
}


//void initialize(int l, int r, int ind){
//    if (l == r){
//        seg[ind] = arr[l];
//        seginds[ind] = make_pair(l, r);
//        return;
//    }
//    int mid = (l + r)/2;
//    initialize(l, mid, 2 * ind  + 1);
//    initialize(mid+1, r, 2 * ind + 2);
//    pii p1 = seginds[2 * ind + 1];
//    ll v1 = seg[2 * ind + 1];
//    pii p2 = seginds[2 * ind + 2];
//    ll v2 = seg[2 * ind + 2];
//    if (p1.second == p2.first-1){
//        ll maxcheck = max(max(v2 + v1, v2), v1);
//        if (maxcheck == v1 + v2){
//            seg[ind] = v1 + v2;
//            seginds[ind] = make_pair(p1.first, p2.second);
//        }
//        else{
//            ((v1 > v2) ? (seg[ind] = v1, seginds[ind] = make_pair(p1.first, p1.second)) : (seg[ind] = v2, seginds[ind] = make_pair(p2.first, p2.second)));
//        }
//    }
//    else{
//        ((v1 > v2) ? (seg[ind] = v1, seginds[ind] = make_pair(p1.first, p1.second)) : (seg[ind] = v2, seginds[ind] = make_pair(p2.first, p2.second)));
//    }
//}
//
//void update(int uind, int l, int r, int ind, ll val){
//    if (l > r){
//        return;
//    }
//    if (uind > r || uind < l){
//        return;
//    }
//    if (l == uind && r == uind){
//        seg[ind] = val;
//        seginds[ind] = make_pair(l, r);
//        return;
//    }
//    int mid = (l + r)/2;
//    update(uind, l, mid, 2 * ind + 1, val);
//    update(uind, mid + 1, r, 2 * ind + 2, val);
//    pii p1 = seginds[2 * ind + 1];
//    ll v1 = seg[2 * ind + 1];
//    pii p2 = seginds[2 * ind + 2];
//    ll v2 = seg[2 * ind + 2];
//    if (p1.second == p2.first-1){
//        ll maxcheck = max(max(v2 + v1, v2), v1);
//        if (maxcheck == v1 + v2){
//            seg[ind] = v1 + v2;
//            seginds[ind] = make_pair(p1.first, p2.second);
//        }
//        else{
//            ((v1 > v2) ? (seg[ind] = v1, seginds[ind] = make_pair(p1.first, p1.second)) : (seg[ind] = v2, seginds[ind] = make_pair(p2.first, p2.second)));
//        }
//    }
//    else{
//        ((v1 > v2) ? (seg[ind] = v1, seginds[ind] = make_pair(p1.first, p1.second)) : (seg[ind] = v2, seginds[ind] = make_pair(p2.first, p2.second)));
//    }
//}
//
//pair<ll, pii> query(int ql, int qr, int l, int r, int ind){
//    if (ql <= l && qr >= r){
//        return make_pair(seg[ind], make_pair(seginds[ind].first, seginds[ind].second));
//    }
//    if (ql > r || qr < l){
//        return make_pair(INF, make_pair(l, r));
//    }
//    int mid = (l + r)/2;
//    pair<ll, pii> q1 = query(ql, qr, l, mid, 2 * ind + 1);
//    pair<ll, pii> q2 = query(ql, qr, mid + 1, r, 2 * ind + 2);
//    if (q1.first == INF){
//        return q2;
//    }
//    if (q2.first == INF){
//        return q1;
//    }
//    ll v1 = q1.first;
//    pii p1 = q1.second;
//    ll v2 = q2.first;
//    pii p2 = q2.second;
//    if (p1.second == p2.first-1){
//        int maxcheck = max(max(v1 + v2, v2), v1);
//        if (maxcheck == v1 + v2){
//            return make_pair(v1 + v2, make_pair(p1.first, p2.second));
//        }
//        else{
//            return v1 > v2 ? make_pair(v1, make_pair(p1.first, p1.second)) : make_pair(v2, make_pair(p2.first, p2.second));
//        }
//    }
//    else{
//        return v1 > v2 ? make_pair(v1, make_pair(p1.first, p1.second)) : make_pair(v2, make_pair(p2.first, p2.second));
//    }
//}
