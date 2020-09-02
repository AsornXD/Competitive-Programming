////
//// Created by Asorn on 2020-08-29.
////
//
//#include <bits/stdc++.h>
//typedef long long int ll;
//using namespace std;
//const int MAXN = 100010;
//
//ll bit[MAXN];
//ll bit2[MAXN];
//ll arr[MAXN];
//int N, M, j;
//
//void add(ll bittemp[], ll ind, ll val){
//    while (ind <= MAXN){
//        bittemp[ind] += val;
//        ind += ind & -ind;
//    }
//}
//
//ll sum(ll bittemp[], ll ind){
//    ll ans = 0;
//    while (ind > 0){
//        ans+=bittemp[ind];
//        ind -=ind & -ind;
//    }
//    return ans;
//}
//
//
//int main(){
//    scanf("%d%d", &N, &M);
//    for (int i = 1; i <= N; i++) {
//        scanf("%lld", &arr[i]);
//        add(bit, i, arr[i]);
//        add(bit2, arr[i], 1);
//    }
//    j = 0;
//    while (j < M){
//        char query;
//        scanf("%s", &query);
//        if (query == 'C'){
//            int x;
//            ll v;
//            scanf("%d%lld", &x, &v);
//            add(bit, x, v-arr[x]);
//            add(bit2, arr[x], -1);
//            arr[x] = v;
//            add(bit2, arr[x], 1);
//        }
//        else if (query == 'S'){
//            int l, r;
//            scanf("%d%d", &l, &r);
//            printf("%lld\n", (sum(bit, r) - sum(bit, l-1)));
//        }
//        else{
//            ll v;
//            scanf("%lld", &v);
//            printf("%lld\n", sum(bit2, v));
//        }
//        j++;
//    }
//}