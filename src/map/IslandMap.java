package map;

public class IslandMap {

    private static final IslandMap ISLAND_MAP = new IslandMap();

    private Cell[][] cells;

    private IslandMap() {}

    public void createMap(int length, int height) {

        generateCells(length, height);
        linkCells();

    }

    private void generateCells (int length, int height) {
        cells = new Cell[length][height];

        for (int i = 0; i < cells.length; i++) {

            for (int i1 = 0; i1 < cells[i].length; i1++) {

                cells[i][i1] = new Cell();

            }

        }

    }

    private void linkCells() {

        for (int i = 0; i < cells.length; i++) {

            for (int i1 = 0; i1 < cells[i].length; i1++) {

                if (i1 + 1 < cells[i].length) {
                    cells[i][i1].getAdjacentCells().add(cells[i][i1 + 1]);
                    cells[i][i1 + 1].getAdjacentCells().add(cells[i][i1]);
                }

                if (i + 1 < cells.length) {
                    cells[i][i1].getAdjacentCells().add(cells[i + 1][i1]);
                    cells[i + 1][i1].getAdjacentCells().add(cells[i][i1]);
                }

            }

        }

    }

    public Cell[][] getCells() {
        return cells;
    }

    public static IslandMap getIslandMap() {
        return ISLAND_MAP;
    }
}
