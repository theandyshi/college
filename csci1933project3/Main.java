public class Main {
    public static void main(String[] args) {
        //initializes and prints the first matrix data which is goldy
        SparseIntMatrix mat = new SparseIntMatrix(800, 800, "matrix1_data.txt");
        MatrixViewer.show(mat);


        //initializes and prints the second matrix data with noise
        SparseIntMatrix mat2 = new SparseIntMatrix(800, 800, "matrix2_data.txt");
        MatrixViewer.show(mat2);


        //initializes the second matrix data without the noise which prints Congrats-- you finished Project3! CSCI 1933
        SparseIntMatrix mat3 = new SparseIntMatrix(800, 800, "matrix2_noise.txt");
        mat2.minus(mat3);
        MatrixViewer.show(mat2);
    }
}
