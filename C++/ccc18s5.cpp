//
// Created by Asorn on 2020-08-26.
//

#include <bits/stdc++.h>
using namespace std;
pair<pair<int, int>, pair<int, int>> edges[100010 * 2];
long long int t = 0;
int N, M, P, Q, ec = 0;
int dsP[100010], dsC[100010], dscP[100010], dscC[100010];



int Find(int n, int ds[]){
    if (n!=ds[n]){
        ds[n] = Find(ds[n], ds);
    }
    return ds[n];
}


int Union(pair<pair<int, int>, pair<int, int>> x, int ds[], int dsc[]){
    int a = Find(x.second.first, ds);
    int b = Find(x.second.second, ds);
    if (a == b){
        return 0;
    }

    if (dsc[a] == dsc[b]){
        ds[b] = a; dsc[a]++;
    }
    else if (dsc[a] < dsc[b]){
         ds[a] = b;
    }
    else if (dsc[b] < dsc[a]){
        ds[b] = a;
    }
    return 1;
}


int main(){
    scanf("%d%d%d%d", &N, &M, &P, &Q);
    int a, b, c;
    long long int tt;
    for (int i = 1; i <= P; i++){
        scanf("%d%d%d", &a, &b, &c);
        t+= (long long int) N*c;
        edges[ec] = {{c,0}, {a, b}};
        ec++;
    }
    for (int i = 1; i <= Q; i++){
        scanf("%d%d%d", &a, &b, &c);
        t+= (long long int) M * c;
        edges[ec] = {{c, 1}, {a, b}};
        ec++;
    }
    for (int i = 1; i <= N; i++){
        dsP[i] = i;
    }
    for (int i = 1; i <= M; i++){
        dsC[i] = i;
    }
    sort(edges, edges + ec);
    long long int cost = 0;
    int pl = N, cl = M;
    for (int i = 0; i < ec; i++){
        if (edges[i].first.second == 1){
            if (Union(edges[i], dsP, dscP)){
                cost += (long long int) edges[i].first.first * cl;
                pl--;
            }
        }
        else{
            if (Union(edges[i], dsC, dscC)){
                cost += (long long int) edges[i].first.first * pl;
                cl--;
            }
        }
    }
    printf("%lld", t - cost);
    return 0;
}
