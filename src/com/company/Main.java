package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    // Dijkstras algorithm
        //shortes path from one point (source) to all other points

        //array of unvisited nodes
        //array of visited nodes
        //array of  distances from source to all others
        //array of edge lengths
        //loop, choose node, loop through paths to other nodes, if there is a shorter path to a node than already exists, update the path, choose the next node that hasnt been visited that is the shortest

        Scanner s = new Scanner(System.in);

        int nodes;

        String sourceNode;
        System.out.println("How many nodes will your graph have?");
        nodes = s.nextInt();
        System.out.println("What is the source node of the algorithm?");
        sourceNode = s.next();
        char unvisitedArr[] = new char[nodes];
        char visitedArr[] = new char[nodes];
        String nodeWeights[] = new String[nodes];




        System.out.println("How many edges do you have?");
        int edgeNum = s.nextInt();
        System.out.println("What are the edges and weights of your graph? Enter them according to this format, if there is an edge from a to b with a weight of 4 --> ab, 4 : Press ");
        String edgeList[] = new String[edgeNum];
        int i = 0;
        while (i < edgeNum) {
            System.out.println("Enter next edge");
            String e = s.next();
            edgeList[i] = e;
            i++;
        }
        for(String k : edgeList)
            System.out.println(k);

        char temp = 'a';
        for(int j  = 0; i < nodes; i++) {
            System.out.println(temp);
            unvisitedArr[i] = temp;
            temp++;
        }
        Boolean algo = true;
        int alpTrack = 0;
        for(char alp = 'a'; alp<= 'z'; alp++){
            if(alpTrack == nodeWeights.length)
                break;
            if(alp == sourceNode.toLowerCase().charAt(0)) {
                System.out.println("Source is a");
                nodeWeights[alpTrack] = edgeList[alpTrack].charAt(0) + ",0";
            } else {
                nodeWeights[alpTrack] = alp + "," + Integer.toString(9999999);
            }
            alpTrack++;
        }

        ArrayList<String> setNodes = new ArrayList<String>();
        while(algo){
            String selectedNode = "";
            String selectedWeight = "";
            for(String e : nodeWeights){
                String min = Integer.toString(9999999);
                int weight = Integer.parseInt(e.split(",")[1]);
                if(weight < Double.parseDouble(min) && !setNodes.contains(e.split(",")[0]))
                    selectedWeight = Integer.toString(weight);
            }
            for(String e : nodeWeights)
                if(e.contains(selectedWeight))
                    selectedNode = e.split(",")[0];

            //add selected node to set node list
             setNodes.add(selectedNode);

             for (String e : edgeList) {
                 if(String.valueOf(e.charAt(0)).equals(selectedNode)){
                     String destNode = String.valueOf(e.charAt(1));
                     int destNodeWeight = 9999999;
                     for(String l : nodeWeights) {
                         if(l.contains(destNode)) {
                             destNodeWeight = Integer.parseInt(l.split(",")[1]);
                         }
                     }
                     int edgeWeight = Integer.parseInt(e.split(",")[1]);

                     if(Integer.parseInt(selectedWeight) + edgeWeight < destNodeWeight) {
                         int count = 0;
                         for(String c : nodeWeights) {
                             if(c.contains(destNode)) {
                                 String node = c.split(",")[0];
                                 int newWeight = Integer.parseInt(selectedWeight)+edgeWeight;
                                 nodeWeights[count] = node +  "," + newWeight ;

                             }
                             count++;
                         }
                     }

                 }
             }
             if(setNodes.size() == nodeWeights.length)
                 break;

        }
        for(String e : nodeWeights)
            System.out.println("Final weight : " + e);

    }

    public int getWeight(String s){
        return Integer.parseInt(s.split(",")[1]);
    }
}
