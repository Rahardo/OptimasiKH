/*
* TSP_GA.java
* Create a tour and evolve a solution
*/

package tsp;
import java.io.*;
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 import java.util.ArrayList;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TSP_GA {

    public static void main(String[] args) {
        
    long startTime = System.nanoTime();
 
        // -define .csv file in app
        String fileNameDefined = "E:/datasets/medium1.csv";
        // -File class needed to turn stringName to actual file
        File file = new File(fileNameDefined);

        try{
            // -read from filePooped with Scanner class
            Scanner inputStream = new Scanner(file);
            
            int array=inputStream.nextInt();
            City city[]=new City[array];
            
            // hashNext() loops line-by-line
            int index=0;
            while(inputStream.hasNext()){
                //read single line, put in string
                String data = inputStream.next();
                String coordinate[]= data.split(",");
//                System.out.println(data+ "***");
                
                city[index] = new City(index,Integer.parseInt(coordinate[0]),Integer.parseInt(coordinate[1]));
                TourManager.addCity(city[index] );
                index++;
            }
            // after loop, close scanner
            inputStream.close();


        }catch (FileNotFoundException e){

            e.printStackTrace();
        }

                 // Initialize population
        Population pop = new Population(100, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());
        System.out.println("Initial Solution:");
        System.out.println(pop.getFittest());

        // diganti untuk menentukan generations dan looping disamakan dengan algoritma ant
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 100; i++) {
            pop = GA.evolvePopulation(pop);
        }
        
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("running time: " + totalTime/1000000 +" miliseconds"); 

       
        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }
}   