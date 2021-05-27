import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        int startRow = in.nextInt();
        int startCol = in.nextInt();
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        Integer bestMap = null;
        MapResult bestResult = null;
        for (int i = 0; i < n; i++) {
            char[][] map = new char[h][w];
            for (int j = 0; j < h; j++) {
                String mapRow = in.nextLine();
                map[j] = mapRow.toCharArray();
                System.err.println(mapRow);
            }
            MapResult result = runMap(map, startRow, startCol, 0);
            if (result.foundTreasure) {
                if ((bestMap == null && bestResult == null) || result.pathLength < bestResult.pathLength) {
                    bestResult = result;
                    bestMap = i;
                }
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(bestMap != null ? bestMap : "TRAP");
    }

    private static MapResult runMap(char[][] map, int row, int col, int pathLength) {
        System.err.println("Exploring map ("+ map.length + "x" + map[0].length + ") at row=" + row + ", col=" + col);
        if (row >= map.length || col >= map[0].length ) {
            return new MapResult(false);
        }
        char ch = map[row][col];
        map[row][col] = '.';
        switch (ch) {
            case '^':
                row--;
                break;
            case 'v':
                row++;
                break;
            case '<':
                col--;
                break;
            case '>':
                col++;
                break;
            case 'T':
                pathLength++;
                return new MapResult(true, pathLength);
            default:
                return new MapResult(false);
        }
        pathLength++;
        return runMap(map, row, col, pathLength);
    }


}

class MapResult {
    boolean foundTreasure;
    int pathLength;

    public MapResult(boolean foundTreasure) {
        this.foundTreasure = foundTreasure;
    }

    public MapResult(boolean foundTreasure, int pathLength) {
        this.foundTreasure = foundTreasure;
        this.pathLength = pathLength;
    }
}
