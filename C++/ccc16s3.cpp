//
// Created by Asorn on 2020-09-05.
//

#include <bits/stdc++.h>
using namespace std;
const int MAXN = 100010;
vector<int> g[MAXN];
vector<int> phos;
int dis[MAXN];
int N, M, curpho, cura, curb, tedges = 0;

int get_subtree(int depth, int curnode, int prev){
//    printf("curnodcheck %d\n", curnode);
//    printf("check 0: %d\n", g[1][1]);
//    if (g[curnode].size() == 0){
//        return (find(phos.begin(), phos.end(), curnode) != phos.end() ? 0 : 1);
//    }
    if (find(phos.begin(), phos.end(), curnode) != phos.end()){
        int gsize = g[curnode].size();
        for (int i = 0; i < gsize; i++){
            int checknode = g[curnode][i];
            if (checknode != prev){
                int run = get_subtree(depth + 1, checknode, curnode);
                if (run == 0){
//                    printf("%d\n", checknode);
                    g[curnode].erase(g[curnode].begin() + distance(g[curnode].begin(), find(g[curnode].begin(), g[curnode].end(), checknode)));
                    g[checknode].clear();
                    i--;
                    gsize--;
                }
            }
        }
//        printf("curnod %d\n", curnode);
        return 1;
    }
    else{
        int isuseless = 0;
        int gsize = g[curnode].size();
        for (int i = 0; i < gsize; i++){
            int checknode = g[curnode][i];
            if (curnode == 1 && i == 0){
//                printf("check: %d %d\n", checknode, prev);
            }
            if (checknode != prev){
                int run = get_subtree(depth + 1, checknode, curnode);
                if (run == 0){
//                    printf("a %d\n", checknode);
                    g[curnode].erase(g[curnode].begin() + distance(g[curnode].begin(), find(g[curnode].begin(), g[curnode].end(), checknode)));
                    g[checknode].clear();
                    i--;
                    gsize--;
                }
                else if (run == 1){
                    isuseless = 1;
                }
            }
        }
//        printf("curnod2 %d\n", curnode);
        return isuseless;
    }
}

bool a = true;

void dfs (int depth, int curnode, int prev){
    dis[curnode] = depth;
    for (int checknode : g[curnode]){
        if (checknode != prev){
            if (a) {
                tedges += 2;
            }
            dfs(depth+1, checknode, curnode);
        }
    }
}

int main(){
    scanf("%d%d", &N, &M);
    for (int i = 0; i < M; i++){
        scanf("%d", &curpho);
        phos.push_back(curpho);
    }
    for (int i = 0; i < N-1; i++){
        scanf("%d%d", &cura, &curb);
        g[cura].push_back(curb);
        g[curb].push_back(cura);
    }
//    for (int i = 0; i < g[1].size(); i++){
//        printf("checkind: %d\n", g[1][i]);
//    }
    get_subtree(0, phos[0], -1);
    memset(dis, 0, sizeof(dis));
    dfs(0, phos[0], -1);
    int curmax = 0, maxind = 0;
    for (int i = 0; i < MAXN; i++){
        if (dis[i] > curmax){
//            printf("%d\n", dis[i]);
            curmax = dis[i];
            maxind = i;
        }
    }
    a = false;
    memset(dis, 0, sizeof(dis));
    dfs(0, maxind, -1);
    curmax = 0;
    for (int i = 0; i < MAXN; i++){
        curmax = max(curmax, dis[i]);
    }
//    printf("first pho %d\n", phos[0]);
    printf("%d", tedges - curmax);
}