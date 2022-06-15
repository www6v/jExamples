package algorithm.company.intel;

/// 5*5的格子，从左下角走到右上角
public class Solution {
//    int num = 0;
//
//    public Integer pathNumber(char grid[][], int row ,int col,int step ) {
//        if(row==0 && col==5) {
//            if(step ==10) {
//                num ++;
//            }
//        }
//
//        grid[row][col] = "*";
//        step++;
//
//        pathNumber(grid, row, col+1, step);
//        pathNumber(grid, row+1, col, step);
////        pathNumber(grid, row, col-1, step);
////        pathNumber(grid, row-1, col, step);
//
//        grid[row][col] = ".";
//        step--;
//        return 0;
//    }

    public Integer pathNumber(int n, int m) {
        if(n > 1 && m > 1) {
           return  pathNumber(n-1, m) +  pathNumber(n, m-1);
        } else if( (n>=1 && m==1) || (n==1 && m>=1) ) {
            return n + m;
        }
        else {
            return 0;
        }
    }
}
