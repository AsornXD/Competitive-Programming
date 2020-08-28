//
// Created by Asorn on 2020-08-27.
//

#include <bits/stdc++.h>
using namespace std;
const int MAXN = 3e4 + 10;


vector<int> doges[MAXN];
int start, finish, N, M, b, p;
int dis[MAXN];
bool v[MAXN];
priority_queue<pair<int, int>,vector<pair<int, int>>, greater<pair<int, int>>> q;


int main(){
    scanf("%d%d", &N, &M);
    fill(dis, dis + N +1,  2147483647);
    for (int i = 0; i < M; i++){
        scanf("%d%d", &b, &p);
        if (i == 0){
            start = b;
        }
        else if ( i == 1){
            finish = b;
        }
        doges[b].push_back(p);
    }
    q.push({0, start});
    dis[start] = 0;

    while (!q.empty()){
        pair<int, int> cur = q.top();
        q.pop();
        int from = cur.second;
        if(!v[from]){
            for (int cur2 : doges[from]){
                for (int j = 1; from + (cur2 * j) < N; j++){
                    int dest = from + (cur2 * j);
                    if (dis[from] + j < dis[dest]){
                        dis[dest] = dis[from] + j;
                        q.push({dis[dest], dest});
                    }
                }
                for (int j = -1; from + (j * cur2) > -1; j--){
                    int dest = from + (j * cur2);
                    if (dis[from] + abs(j) < dis[dest]){
                        dis[dest] = dis[from] + abs(j);
                        q.push({dis[dest], dest});
                    }
                }
            }
            v[from] = 1;
        }
    }
    printf("%d", dis[finish] == 2147483647 ? -1 : dis[finish]);
    return 0;
}
