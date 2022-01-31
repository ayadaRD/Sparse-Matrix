package com.company;

import java.util.ArrayList;

public class controller {

//----------------------------------------------------------------
//this function convert a simple matrix to sparse matrix
    public static Sparse createSparse(int[][] simpleMatrix){

        //this variable counts the number of none-zero elements
        int valueNum = 0;

        //counting none-zero elements
        for(int i=0 ; i<simpleMatrix.length ; i++)
            for(int j=0 ; j<simpleMatrix[0].length ; j++)
                if(simpleMatrix[i][j] != 0)
                    valueNum++;

        //elements of sparse matrix would save in this array
        int[][] sparse = new int[valueNum][3];

        //just a little counter
        int sparseRow = 0;

        //converting to sparse
        // sparse[0] = row
        // sparse[1] = column
        // sparse[2] = value

        for (int i=0 ; i<simpleMatrix.length ; i++) {
            for(int j=0 ; j<simpleMatrix[0].length ; j++){
                if(simpleMatrix[i][j] != 0){
                    sparse[sparseRow][0] = i;
                    sparse[sparseRow][1] = j;
                    sparse[sparseRow][2] = simpleMatrix[i][j];
                    sparseRow++;
                }
            }
        }
        Sparse s = new Sparse(sparse);
        s.setMainMatrixRow(simpleMatrix.length);  //saving original row
        s.setMainMatrixColumn(simpleMatrix[0].length); //saving original column

        return s;
    }

//-------------------------------------------------------------------------------
    public static int[][] backToNormal(Sparse A){

        int[][] normal = new int[A.mainMatrixRow][A.mainMatrixColumn];

        int k=0;

        for (int i = 0; i < A.mainMatrixRow; i++) {
            for (int j = 0; j < A.mainMatrixColumn; j++) {
                if(i == A.elements[k][0] && j == A.elements[k][1])
                    normal[i][j] = A.elements[k++][2];
                else
                    normal[i][j] = 0;
            }

        }

        return normal;

    }

//for printing array
    public static void print(int[][] array, int row, int col){

        for(int i=0 ; i<row ; i++){
            for(int j=0 ; j<col ; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
//for printing sparse
    public static void printSparse(Sparse mat){
        for(int i=0 ; i<mat.elements.length ; i++){
            for(int j=0 ; j<3 ; j++){
                System.out.print(mat.elements[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
//-------------------------------------------------------------------------------------
//for managing the entered amounts

    public static ArrayList<Integer> management(int[][] A, int n1, int m1, int[][] B, int n2, int m2, String operation){

        Sparse sparse1;
        Sparse sparse2;

        if(B == null){
            sparse1 = createSparse(A);
            sparse2 = null;
        }
        else{
            sparse1 = createSparse(A);
            sparse2 = createSparse(B);
        }

        sparse1.setMainMatrixRow(n1);
        sparse1.setMainMatrixColumn(m1);

        sparse2.setMainMatrixRow(n2);
        sparse2.setMainMatrixColumn(m2);

        ArrayList answer = new ArrayList<>();

        if(operation.equals("+")) {
            sparse1.setShouldAdd(true);
            answer = addAndSubtract(sparse1, sparse2);
        }

        if(operation.equals("-")) {
            sparse1.setShouldAdd(false);
            answer = addAndSubtract(sparse1, sparse2);
        }

        if(operation.equals("*"))
            answer = multiply(sparse1,sparse2);

        return answer;
    }

//-----------------------------------------------------------------------------------------ADD OR SUBTRACT
    public static ArrayList<Integer> addAndSubtract(Sparse A, Sparse B){

        int i=0;  //A counter
        int j=0;  //B counter

        ArrayList <Integer> answer = new ArrayList<>();

        //to start adding/subtracting we need to enter to a loop
        // the condition of break is for when i & j reach to end of A & B rows

        for( ; i != A.elements.length && j!=B.elements.length ; ){

            //row with smaller index saves sooner
            if( A.elements[i][0] < B.elements[j][0]){
                answer.add(A.elements[i][0]);
                answer.add(A.elements[i][1]);
                answer.add(A.elements[i][2]);
                i++;
            }

            //row with bigger index saves later
            else if(A.elements[i][0] > B.elements[j][0]){
                answer.add(B.elements[j][0]);
                answer.add(B.elements[j][1]);
                if(A.shouldAdd)
                    answer.add(B.elements[j][2]);
                else
                    answer.add(-B.elements[j][2]);
                j++;
            }

            //if rows were the same
            //let's check the number of column
            else{

                //column with smaller index saves sooner
                if( A.elements[i][1] < B.elements[j][1]){
                    answer.add(A.elements[i][0]);
                    answer.add(A.elements[i][1]);
                    answer.add(A.elements[i][2]);
                    i++;
                }

                //column with bigger index saves later
                else if(A.elements[i][1] > B.elements[j][1]){
                    answer.add(B.elements[j][0]);
                    answer.add(B.elements[j][1]);
                    if(A.shouldAdd)
                        answer.add(B.elements[j][2]);
                    else
                        answer.add(-B.elements[j][2]);
                    j++;
                }

                //and if both column and row were the same, we would add or subtract the values
                else{
                    answer.add(A.elements[i][0]);
                    answer.add(A.elements[i][1]);
                    if(A.shouldAdd)
                        answer.add(A.elements[i][2] + B.elements[j][2]);
                    else
                        answer.add(A.elements[i][2] - B.elements[j][2]);
                    i++;
                    j++;
                }
            }
        }

        //after getting out of loop it might be some values left in A and B
        //we add them to answer array using these two while loop

        while(i < A.elements.length){
            answer.add(A.elements[i][0]);
            answer.add(A.elements[i][1]);
            answer.add(A.elements[i][2]);
            i++;
        }

        while(j < B.elements.length){
            answer.add(B.elements[j][0]);
            answer.add(B.elements[j][1]);
            if(A.shouldAdd)
                answer.add(B.elements[j][2]);
            else
                answer.add(-B.elements[j][2]);
            j++;
        }
        //and returning the final result
        return answer;
    }
//----------------------------------------------------------------TRANSPOSE
    public static Sparse transpose(Sparse A){

        Sparse B = new Sparse();
        B.setMainMatrixRow(A.mainMatrixColumn);
        B.setMainMatrixColumn(A.mainMatrixRow);
        B.elements = new int[A.elements.length][3];

        int originalRow = 0;

        for(int i=0 ; i<A.elements.length ; i++){
            if(A.elements[i][1] > originalRow){
                originalRow = A.elements[i][1];
            }
        }
        originalRow++;

        if(B.elements.length>0) {
            int[] counter = new int[originalRow];

            for (int i = 0; i < originalRow; i++)
                counter[i] = 0;

            for (int i = 0; i < A.elements.length; i++)
                counter[A.elements[i][1]]++;

            int[] index = new int[originalRow];
            index[0] = 0;

            for (int i = 1; i < originalRow; i++)
                index[i] = index[i - 1] + counter[i - 1];

            for (int i = 0; i < A.elements.length; i++) {

                int x = index[A.elements[i][1]]++;
                B.elements[x][0] = A.elements[i][1];
                B.elements[x][1] = A.elements[i][0];
                B.elements[x][2] = A.elements[i][2];
            }
        }
    return B;
    }
//----------------------------------------------------------------
    public static ArrayList<Integer> multiply(Sparse A, Sparse B) {

        ArrayList <Integer> result = new ArrayList<>();

        int i; //counter for A
        int j; //counter for B

        Sparse C = transpose(B);  //we are going to work with the transpose of matrix B instead of B

        //start multiplying
        for(i =0 ; i < A.elements.length ;){

            //checking from the first row in first column
            int row = A.elements[i][0];

            for(j=0 ; j<C.elements.length ; ) {

                //matching with second matrix
                int column = C.elements[j][0];

                //we save these to sum all of same i & j
                int temp1 = i;
                int temp2 = j;
                int sum = 0;

                //in this loop we multiply the elements of two matrix if their [tempx][1] was equal ,else continue and tempx++
                while (temp1 < A.elements.length && A.elements[temp1][0] == row &&
                        temp2 < C.elements.length && C.elements[temp2][0] == column) {

                    if (A.elements[temp1][1] < C.elements[temp2][1])
                        temp1++;
                    else if (A.elements[temp1][1] > C.elements[temp2][1])
                        temp2++;
                    else
                        sum += A.elements[temp1++][2] * C.elements[temp2++][2];
                }

                //for making sure that sum is not empty
                if (sum != 0) {
                    result.add(row);
                    result.add(column);
                    result.add(sum);
                }

                //for getting out of inner for loop
                while (j < C.elements.length && C.elements[j][0] == column)
                    j++;

            }
                //for getting out of outer for loop
                while(i < A.elements.length && A.elements[i][0] == row)
                    i++;

        }
        //returning result
        return result;
    }
}