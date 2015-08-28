/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codebyter;

/**
 *
 * @author Wilson
 */
public class isArith {
    public static void main(String[] args){
        int[] arr = {5,10,15,20};
        for(int i=0;i<arr.length-2;i++){
            if(arr[i+1]-arr[i]!=arr[i+1]-arr[i+2])
                System.out.println("False");;
        }
        System.out.println("True");
    }
    
}
