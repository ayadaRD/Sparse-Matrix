package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static com.company.controller.backToNormal;
import static com.company.controller.transpose;

public class Main {

    static Scanner cin = new Scanner(System.in);
    static Scanner cinSTR = new Scanner(System.in);

    public static void getSparse() throws IOException {


        File file = new File("C:\\Users\\USER\\Desktop\\sparseProject\\matrix1.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String str;
        String sum = "";

        while ((str = br.readLine()) != null){
            sum += str;
        }


        String[] array1 = sum.split("/");

        String[][] array2 = new String[array1.length][];

        for(int i=0 ; i<array1.length ; i++){
            array2[i] = array1[i].trim().split(" ");
        }

        int[][] array3 = new int[array2.length][array2[0].length];

        for(int i=0 ; i<array2.length ; i++){
            for(int j=0 ; j< array2[0].length ; j++){
                array3[i][j] = Integer.parseInt(array2[i][j]);
            }
        }

        Sparse result = controller.createSparse(array3);

        System.out.println("\nRESULT:");
        controller.print(result.elements,result.elements.length,result.elements[0].length);

        System.out.println("\n\n");
    }
//-----------------------------------------------------------------------------------------
    public static void add() {

        try {


            File file1 = new File("C:\\Users\\USER\\Desktop\\sparseProject\\matrix1.txt");

            BufferedReader br = new BufferedReader(new FileReader(file1));

            String str1;
            String sum1 = "";

            while ((str1 = br.readLine()) != null){
                sum1 += str1;
            }

           // System.out.println("\n    Matrix1     ");

            String[] array1 = sum1.split("/");

            String[][] array2 = new String[array1.length][];

            for(int i=0 ; i<array1.length ; i++){
                array2[i] = array1[i].trim().split(" ");
            }

            int[][] matrix1 = new int[array2.length][array2[0].length];

            for(int i=0 ; i<array2.length ; i++){
                for(int j=0 ; j< array2[0].length ; j++){
                    matrix1[i][j] = Integer.parseInt(array2[i][j]);
                }
            }
            System.out.println("\nSparse form of first matrix ");
            controller.printSparse(controller.createSparse(matrix1));




            File file2 = new File("C:\\Users\\USER\\Desktop\\sparseProject\\matrix2.txt");

            BufferedReader br2 = new BufferedReader(new FileReader(file2));

            String str2;
            String sum2 = "";

            while ((str2 = br2.readLine()) != null){
                sum2 += str2;
            }


            String[] array10 = sum2.split("/");

            String[][] array20 = new String[array10.length][];

            for(int i=0 ; i<array10.length ; i++){
                array20[i] = array10[i].trim().split(" ");
            }

            int[][] matrix2 = new int[array20.length][array20[0].length];

            for(int i=0 ; i<array20.length ; i++){
                for(int j=0 ; j< array20[0].length ; j++){
                    matrix2[i][j] = Integer.parseInt(array20[i][j]);
                }
            }

            System.out.println("\nSparse form of second matrix ");
            controller.printSparse(controller.createSparse(matrix2));

            if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) throw new Exception();

            ArrayList result = controller.management(matrix1, matrix1.length, matrix1[0].length, matrix2, matrix2.length, matrix2[0].length, op);

            System.out.println("\nRESULT:");

            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i) + " ");
                if (i % 3 == 2)
                    System.out.println();
            }

            System.out.println("\n\n");
        }catch (Exception e){
            System.out.println("\nsomething went wrong!");
            System.out.println("check the sizes witch you entered...\n");
        }
    }
//--------------------------------------------------------------------------------
    public static void transpose_management() throws IOException {


        File file = new File("C:\\Users\\USER\\Desktop\\sparseProject\\matrix1.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String str;
        String sum = "";

        while ((str = br.readLine()) != null){
            sum += str;
        }

        //String str = cinSTR.nextLine();

        String[] array1 = sum.split("/");

        String[][] array2 = new String[array1.length][];

        for(int i=0 ; i<array1.length ; i++){
            array2[i] = array1[i].trim().split(" ");
        }

        int[][] array3 = new int[array2.length][array2[0].length];

        for(int i=0 ; i<array2.length ; i++){
            for(int j=0 ; j< array2[0].length ; j++){
                array3[i][j] = Integer.parseInt(array2[i][j]);
            }
        }

        Sparse sparse = controller.createSparse(array3);

        sparse.setMainMatrixRow(array3.length);
        sparse.setMainMatrixColumn(array3[0].length);

        Sparse transpose = controller.transpose(sparse);

        System.out.println("\nRESULT:");
        for (int i=0 ; i<transpose.elements.length ; i++) {
            for (int j=0 ; j <transpose.elements[0].length ; j++) {
                System.out.print(transpose.elements[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }
//-----------------------------------------------------------------------------
    public static void multiply_matrix(){

        try {



            File file1 = new File("C:\\Users\\USER\\Desktop\\sparseProject\\matrix1.txt");

            BufferedReader br = new BufferedReader(new FileReader(file1));

            String str1;
            String sum1 = "";

            while ((str1 = br.readLine()) != null){
                sum1 += str1;
            }

            String[] array1 = sum1.split("/");

            String[][] array2 = new String[array1.length][];

            for(int i=0 ; i<array1.length ; i++){
                array2[i] = array1[i].trim().split(" ");
            }

            int[][] matrix1 = new int[array2.length][array2[0].length];

            for(int i=0 ; i<array2.length ; i++){
                for(int j=0 ; j< array2[0].length ; j++){
                    matrix1[i][j] = Integer.parseInt(array2[i][j]);
                }
            }


            //System.out.println("\n    Matrix2 ");

            //String str2 = cinSTR.nextLine();


            File file2 = new File("C:\\Users\\USER\\Desktop\\sparseProject\\matrix2.txt");

            BufferedReader br2 = new BufferedReader(new FileReader(file2));

            String str2;
            String sum2 = "";

            while ((str2 = br2.readLine()) != null){
                sum2 += str2;
            }

            String[] array10 = sum2.split("/");

            String[][] array20 = new String[array10.length][];

            for(int i=0 ; i<array10.length ; i++){
                array20[i] = array10[i].trim().split(" ");
            }

            int[][] matrix2 = new int[array20.length][array20[0].length];

            for(int i=0 ; i<array20.length ; i++){
                for(int j=0 ; j< array20[0].length ; j++){
                    matrix2[i][j] = Integer.parseInt(array20[i][j]);
                }
            }


            System.out.println("\nRESULT");

            ArrayList<Integer> result = controller.management(matrix1, matrix1.length, matrix1[0].length, matrix2, matrix2.length, matrix2[0].length, op);

            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i) + " ");
                if (i % 3 == 2)
                    System.out.println();
            }
            System.out.println("\n\n");
        }
        catch (Exception e){
            System.out.println("\nsomething went wrong!");
            System.out.println("check the sizes witch you entered...\n");
        }
    }

    static String op;

    public static void main(String[] args) throws Exception {

        int n;

        while(true){
            System.out.println("  *  Sparse Matrix Calculator  *\n");

            System.out.println("1. get sparse");
            System.out.println("2. add");
            System.out.println("3. subtract");
            System.out.println("4. transpose");
            System.out.println("5. multiply");
            System.out.println("6. EXIT");

            System.out.print("\nChoose your process : ");
            n = cin.nextInt();

            switch(n){
                case 1:
                    getSparse();
                    break;

                case 2:
                    op = "+";
                    add();
                    break;

                case 3:
                    op = "-";
                    add();
                    break;

                case 4:
                    transpose_management();
                    break;

                case 5:
                    op = "*";
                    multiply_matrix();
                    break;

                case 6:
                    System.exit(0);
                    break;

                default:
                    System.out.println("not in options!\n\n");
            }
        }
    }
}