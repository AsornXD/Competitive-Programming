#include <bits/stdc++.h>
using namespace std;

struct Edge{
    int u, v, w; };

bool operator <(Edge e1, Edge e2){
    if (e1.w != e2.w){
        return e1.w > e2.w;
    }
    if (e1.u != e2.u){
        return e1.u <e2.u;
    }
    return e1.v < e2.v;
}

vector<pair<int, int>> graph[1100];
set<Edge> edges;
int ds[1100];
int u[5100], v[5100], w[5100];
int N, M, Q, t, x, m, a, b, c;

int find(int xx){
    if (ds[xx] == xx){
        return xx;
    }
    return ds[xx] = find(ds[xx]);
}

void kruskals(){
    for (int i = 1; i <= N; i++){
        ds[i] = i;
        graph[i].clear();
    }
    int counter = 0;
    for (Edge e : edges){
        int uu = find(e.u), vv = find(e.v);
        if (uu != vv){
            graph[e.u].push_back(make_pair(e.v,e.w));
            graph[e.v].push_back(make_pair(e.u,e.w));
            ds[uu] = vv;
            counter++;
        }
        if (counter == N-1){
            break;
        }
    }
}

bool dfs(int cura, int curc){
    if (cura == b){
        return true;
    }
    for (pair<int, int> ecur : graph[cura])
        if (ecur.first != curc && ecur.second >= c && dfs(ecur.first, cura))
            return true;
    return false;
}

int main(){
    scanf("%d%d", &N, &M);
    for (int i = 1; i <= M; i++){
        scanf("%d%d%d", &u[i], &v[i], &w[i]);
        edges.insert({u[i],v[i],w[i]});
    }
    kruskals();
    scanf("%d", &Q);
    for (int i = 1; i <= Q; i++){
        scanf("%d", &t);
        if (t == 1){
            scanf("%d%d", &m, &x);
            edges.erase({u[m], v[m], w[m]});
            w[m] = x;
            edges.insert({u[m], v[m], w[m]});
            kruskals();
        }
        else{
            scanf("%d%d%d", &a, &b, &c);
            if (find(a) == find(b)){
                printf("%d\n", dfs(a, 0));
            }
            else{
                printf("0\n");
            }
        }
    }
    return 0;
}