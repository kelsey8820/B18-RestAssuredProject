package day07;

import org.junit.jupiter.api.Test;

public class WhatIsVarArgs {

    // variable number of arguments
    // Create a method that accept N numbers and add all of them
    // and print the result


    public static void addAllNumbers(int[] nums){

        int sum = 0 ;
        for(int eachNum : nums){
            sum += eachNum ;
        }
        System.out.println("sum = " + sum);

    }

    @Test
    public void testAdd(){

        addAllNumbers(  new int[]{1,2,3,4,5,6}  );

        addAllNumbersVarArgs( 1,2,3,4,5);
        addAllNumbersVarArgs( 1,2,3,4,5,3,4,5,6,7,8,9,90,4,5,6);
        addAllNumbersVarArgs(1,2);

    }


    // this method parameter int... nums means
    // when you call the method , it can accept any number of arguments
    public static void addAllNumbersVarArgs(int... nums){

        int sum = 0 ;
        for(int eachNum : nums){
            sum += eachNum ;
        }
        System.out.println("sum = " + sum);

    }






}
