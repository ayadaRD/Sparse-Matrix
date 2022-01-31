package com.company;

public class Sparse {

    int elements[][];
    int mainMatrixColumn;
    int mainMatrixRow;
    boolean shouldAdd ;

    public Sparse(int[][] elements){
        this.elements = elements;
    }

    public Sparse(){}

    public void setMainMatrixColumn(int mainMatrixColumn) {
        this.mainMatrixColumn = mainMatrixColumn;
    }

    public void setMainMatrixRow(int mainMatrixRow) {
        this.mainMatrixRow = mainMatrixRow;
    }

    public void setShouldAdd(boolean shouldAdd) {
        this.shouldAdd = shouldAdd;
    }
}
