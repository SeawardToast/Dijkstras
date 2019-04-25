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


        //declarations
        Scanner s = new Scanner(System.in);
        ArrayList<String> setNodes = new ArrayList<String>();
        int nodes;

        String sourceNode;
        System.out.println("How many nodes will your graph have?");
        nodes = s.nextInt();
        String nodeWeights[] = new String[nodes];
        System.out.println("What is the source node of the algorithm?");
        sourceNode = s.next();
        System.out.println("How many edges do you have?");
        int edgeNum = s.nextInt();
        System.out.println("What are the edges and weights of your graph? Enter them accordingly, if there is an edge from a to b, wih weight 4 --> ab,4");
        String edgeList[] = new String[edgeNum];
        int i = 0;
        while (i < edgeNum) {
            System.out.println("Enter next edge");
            String e = s.next();
            edgeList[i] = e;
            i++;
        }

        //initialize weight array
        initializeWeights(nodeWeights, edgeList, sourceNode);
        //print algorithm function and pass values needed
        printResults(algorithm(nodeWeights, edgeList, setNodes));
    }


    private static int getWeight(String s){
        return Integer.parseInt(s.split(",")[1]);
    }
    private static String getNode(String s){
        return s.split(",")[0];
    }

    private static void printResults(String[] nodeWeights){
        for(String e : nodeWeights)
            System.out.println("Shortest distance to node " + getNode(e) + " = " + getWeight(e));
    }

    private static void initializeWeights(String[] nodeWeights, String[] edgeList, String sourceNode){
        int alpTrack = 0;
        for(char alp = 'a'; alp<= 'z'; alp++){
            if(alpTrack == nodeWeights.length)
                break;
            if(alp == sourceNode.toLowerCase().charAt(0)) //if string contains source node set weight to 0, else 9999 (infinite)
                nodeWeights[alpTrack] = edgeList[alpTrack].charAt(0) + ",0";
            else
                nodeWeights[alpTrack] = alp + "," + 9999999;
            alpTrack++;
        }
    }

    private static void updateWeight(String[] nodeWeights, String destNode, int edgeWeight, int selectedWeight){

        //if the string contains destnode, than update the string to reflect the new weight
            int count = 0;
            for(String c : nodeWeights) {
                if(c.contains(destNode)) {
                    String node = getNode(c);
                    int newWeight = selectedWeight+edgeWeight;
                    nodeWeights[count] = node +  "," + newWeight ;
                }
                count++;
            }
    }

    private static String[] algorithm(String[] nodeWeights, String[] edgeList, ArrayList<String> setNodes){

        //loop until setNodes is full, indicating that every node is at it's shortest weight
        while(true){
            String selectedNode = "";
            String selectedWeight = "";

            //select node with smallest weight
            for(String e : nodeWeights){
                String min = Integer.toString(9999999);
                int weight = getWeight(e); //get weight from function taking in string e
                //if the node weight is smaller than current minimum and the node is not in the setNodes, select this node and weight
                if(weight < Double.parseDouble(min) && !setNodes.contains(getNode(e)))
                    selectedWeight = Integer.toString(weight);
            }
            for(String e : nodeWeights)
                if(e.contains(selectedWeight))
                    selectedNode = getNode(e);

            //add selected node to set node list
            setNodes.add(selectedNode);


            for (String e : edgeList) { //loop through list of edges
                int edgeWeight = getWeight(e); //weight of edge selected on this iteration

                if(String.valueOf(e.charAt(0)).equals(selectedNode)){ //if there is an edge leaving the node we just selected continue
                    String destNode = String.valueOf(e.charAt(1));  //set the destination node, aka a->b, b is destination node

                    //finding the destination node's current weight
                    int destNodeWeight = 9999999; //
                    for(String l : nodeWeights) {
                        if(l.contains(destNode)) { //if the current string contains our destination node continue and update the weight
                            destNodeWeight = Integer.parseInt(l.split(",")[1]);
                        }
                    }

                    //if the weight of the origin node plus the weight of the edge is smaller than the current weight of destination, update
                    if(Integer.parseInt(selectedWeight) + edgeWeight < destNodeWeight)
                        updateWeight(nodeWeights, destNode, edgeWeight, Integer.parseInt(selectedWeight));

                }
            }
            if(setNodes.size() == nodeWeights.length) //break when all nodes exist in setNodes
                break;
        }
        return nodeWeights;
    }
}
