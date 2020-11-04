//
// Created by Asorn on 2020-10-20.
//

#include <bits/stdc++.h>

using namespace std;
const int MAXN = 5010;

typedef pair<int, int> pii;

vector<pii> arr;
vector<int> g[MAXN];
int S, N;
int p[MAXN];
bool vis[MAXN];
bool vis2[MAXN];

void setfurthest(int curnode, int curdis){
    arr[curnode] = make_pair(curdis++, curnode);
    for (int e : g[curnode]){
        if (e != p[curnode]){
            p[e] = curnode;
            setfurthest(e, curdis);
        }
    }
}

int findfurthest(int curnode, int curdis){
    if (curdis == 0){
        return curnode;
    }
    return findfurthest(p[curnode], curdis-1);
}

void setvis(int curnode, int curdis){
    vis[curnode] = 1, vis2[curnode] = 1;
    if (curdis == 0){
        return;
    }
    for (int e : g[curnode]){
        if (!vis2[e]){
            setvis(e, curdis-1);
        }
    }

}

int findmin(int dis){
    int minans = 0;
    memset(vis, 0, sizeof(vis));
    for (pii curpii : arr){
        if (!vis[curpii.second]){
            int furthest = findfurthest(curpii.second, dis);
            minans++;
            memset(vis2, 0, sizeof(vis2));
            setvis(furthest, dis);
        }
    }
    return minans;
}


int main(){
    scanf("%d%d", &N, &S);
    for (int i = 0; i < N; i++){
        arr.push_back(make_pair(0, i));
    }
//    printf("%d\n", N);
    for (int i = 0; i < N-1; i++){
        int u,v;
        scanf("%d%d", &u, &v);
        u--, v--;
        g[u].push_back(v);
        g[v].push_back(u);
    }
    setfurthest(0, 1);
    sort(arr.begin(), arr.end(), greater<pii>());
//    printf("%d\n", arr[3].first);
//    printf("%d\n", arr[3].second);
    int l = 0, r = MAXN;
    int mid;
    while (l <= r){
        mid = (l + r) / 2;
        if (findmin(mid) <= S){
            r = mid - 1;
        }
        else{
            l = mid + 1;
        }
    }
    printf("%d", l);
}

