import java.util.Scanner;
import java.io.File;


public class SparseIntMatrix {
    private int rows, cols;
    private boolean cont;
    private SparseIntMatrix Rows, Cols;
    private MatrixEntry Entry1, Entry2;
    private MatrixEntry[] matrixCol, matrixRow;
    private int[] checkRow,checkCol;


    public SparseIntMatrix(int numRows, int numCols){
        rows = numRows;
        cols = numCols;
        matrixCol = new MatrixEntry[cols];
        matrixRow = new MatrixEntry[rows];
    }

    public SparseIntMatrix(int numRows, int numCols, String inputFile){
        Scanner s;
        rows = numRows;
        cols = numCols;
        cont = true;
        matrixCol = new MatrixEntry[cols];
        matrixRow = new MatrixEntry[rows];
        try {
            File file = new File(inputFile);
            s = new Scanner(file);
        } catch(Exception e) {
            System.out.println("There was an error opening the file");
            s = null;
            cont = false;
        }
        if (cont){
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] triple;
                triple = line.split(",");
                int r = Integer.parseInt(triple[0]);
                int c = Integer.parseInt(triple[1]);
                int d = Integer.parseInt(triple[2]);
                if (d != 0 && r <= rows && c <= cols) {
                    setElement(r,c,d);
                }
            }
        }
    }

    public int getElement(int row, int col){
        MatrixEntry testMatrix = matrixRow[row];
        while (true) {
            if (testMatrix == null) {
                return 0;
            } else if (testMatrix.getColumn() > col) {
                return 0;
            } else if (testMatrix.getColumn() == col) {
                return testMatrix.getData();
            } else {
                testMatrix = testMatrix.getNextCol();
            }
        }
    }

    public boolean setElement(int row, int col, int data){
        if (row >= rows || col >= cols || row <= 0 || col <= 0){
            return false;
        } else {
            boolean rowbool = true;
            boolean colbool = true;
            MatrixEntry newEntry = new MatrixEntry(row,col,data);
            MatrixEntry currentRow = matrixRow[row];
            if (matrixRow[row] == null) {
                matrixRow[row] = newEntry;
            } else if (getElement(row,col) != 0){
                while (rowbool) {
                    if (currentRow.getColumn() == col) {
                        currentRow.setData(data);
                        rowbool = false;
                    }
                    else {
                        currentRow = currentRow.getNextCol();
                    }
                }
            } else {
                MatrixEntry Entry1, Entry2;
                Entry1 = matrixRow[row];
                if (Entry1.getColumn() > col) {
                    newEntry.setNextCol(Entry1);
                    matrixRow[row] = newEntry;
                } else {
                    Entry2 = Entry1.getNextCol();
                    rowbool = true;
                    while (rowbool) {
                        if (Entry2 == null) {
                            Entry1.setNextCol(newEntry);
                            rowbool = false;
                        } else if (Entry2.getColumn() > col) {
                            newEntry.setNextCol(Entry2);
                            Entry1.setNextCol(newEntry);

                            rowbool = false;
                        } else {
                            Entry1 = Entry2;
                            Entry2 = Entry2.getNextCol();
                        }
                    }
                }
            }
            MatrixEntry currentCol = matrixCol[col];
            if (matrixCol[col] == null) {
                matrixCol[col] = newEntry;
            } else {
                MatrixEntry Entry1, Entry2;
                Entry1 = matrixCol[col];
                if (Entry1.getRow() > row) {
                    newEntry.setNextRow(Entry1);
                    matrixCol[col] = newEntry;
                } else {
                    Entry2 = Entry1.getNextRow();
                    colbool = true;
                    while (colbool) {
                        if (Entry2 == null) {
                            Entry1.setNextRow(newEntry);
                            colbool = false;
                        } else if (Entry2.getRow() > row) {
                            newEntry.setNextRow(Entry2);
                            Entry1.setNextRow(newEntry);
                            colbool = false;
                        } else {
                            Entry1 = Entry2;
                            Entry2 = Entry2.getNextRow();
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean removeElement( int row, int col){
        MatrixEntry previousRow = null, currentRow = matrixRow[row];
        if (getElement(row,col) != 0){
            while (true) {
                if (currentRow.getColumn() == col) {
                    if (previousRow != null) {
                        previousRow.setNextCol(currentRow.getNextCol());
                    } else {
                        matrixRow[row] = currentRow.getNextCol();
                    }
                    return true;
                }
                else {
                    previousRow = currentRow;
                    currentRow = currentRow.getNextCol();
                }
            }
        } else {
            return false;
        }
    }

    public int getNumCols(){
        return cols;
    }

    public int getNumRows(){
        return rows;
    }

    public boolean plus(SparseIntMatrix otherMat){
        if (otherMat.getNumRows() == getNumRows() && otherMat.getNumCols() == getNumCols()) {
            for (int i = 0; i < otherMat.getNumRows(); i++) {
                for (int j = 0; j < otherMat.getNumCols(); j++) {
                    int currentData = getElement(i,j);
                    int otherData = otherMat.getElement(i,j);
                    if (otherData != 0) {
                        int sumData = currentData + otherData;
                        if (sumData == 0) {
                            removeElement(i,j);
                        } else {
                            setElement(i,j,sumData);
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean minus(SparseIntMatrix otherMat){
        if (otherMat.getNumRows() == getNumRows() && otherMat.getNumCols() == getNumCols()) {
            for (int i = 0; i < otherMat.getNumRows(); i++) {
                for (int j = 0; j < otherMat.getNumCols(); j++) {
                    int currentData = getElement(i,j);
                    int otherData = otherMat.getElement(i,j);
                    if (otherData != 0) {
                        int sumData = currentData - otherData;
                        if (sumData == 0) {
                            removeElement(i,j);
                        } else {
                            setElement(i,j,sumData);
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
