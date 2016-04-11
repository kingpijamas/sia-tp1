          #N,exposions,cost
greedy_h1=[3,4,3;4,115,14];
greedy_h2=[3,7,4;4,98,10];
greedy_h6=[3,53,5;4,5999,16];
a_star_h1=[3,22,3;4, 33935, 7];
a_star_h2=[3,31,3,4,47696, 8 ];
a_star_h6=[3,66,3;4,78973, 7 ];
iddfs=[3,278,3;4,475128, 7 ];
bfs=[3,107,3;4,199305, 8 ];
dfs=[3,130,8;4,120359, 23 ];


hold on;
scatter(greedy_h1(:,1),greedy_h1(:,2),[],'b','s');
scatter(greedy_h2(:,1),greedy_h2(:,2),[],'b','o');
scatter(greedy_h6(:,1),greedy_h6(:,2),[],'b','*');

scatter(a_star_h1(:,1),a_star_h1(:,2),[],'b','x');

scatter(a_star_h2(:,1),a_star_h2(:,2),[],'r','.');

scatter(a_star_h6(:,1),a_star_h6(:,2),[],'b','^');
scatter(iddfs(:,1),iddfs(:,2),[],'b','v');
scatter(bfs(:,1),bfs(:,2),[],'b','>');
scatter(dfs(:,1),dfs(:,2),[],'b','<');
hold off;


legend ("greedy-h1","greedy-h2","greedy-h6","aStarH1","aStarH2","aStarH6","iddfs","bfs","dfs");
set(gca, 'xtick', 3:1:4)