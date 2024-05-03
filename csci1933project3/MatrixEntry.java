public class MatrixEntry {

    private int col, row, data;
    private MatrixEntry nextRow, nextCol;

    public MatrixEntry(int irow, int icol, int idata) {
        col = icol;
        row = irow;
        data = idata;
    }

    public int getColumn() {
        return col;
    }

    public void setColumn(int icol) {
        col = icol;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int irow) {
        row = irow;
    }

    public int getData() {
        return data;
    }

    public void setData(int idata) {
        data = idata;
    }

    public MatrixEntry getNextRow() {
        return nextRow;
    }

    public void setNextRow(MatrixEntry next) {
        nextRow = next;
    }

    public MatrixEntry getNextCol() {
        return nextCol;
    }

    public void setNextCol(MatrixEntry next) {
        nextCol = next;
    }
}