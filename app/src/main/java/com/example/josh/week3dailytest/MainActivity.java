package com.example.josh.week3dailytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public static void main(String[] args) {
        Room[][] noInfection = new Room[][] {
                {new Room(true), new Room(false), new Room(true), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false) },
                {new Room(false), new Room(true), new Room(false), new Room(true), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false) },
                {new Room(true), new Room(false), new Room(true), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false) },
                {new Room(false), new Room(true), new Room(false), new Room(true), new
                        Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(false) },
                {new Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(true), new Room(false), new Room(false), new Room(false), new
                        Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(true), new Room(false), new
                        Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false) }
        };

        //System.out.println(isOutbreak(noInfection));
        String[] input = new String[]{"B2,E5,F6", "A1,B2,C3,D4", "D4,G7,I9", "G7,H8"};

        List<Node> output = populateList(input);
        printList(output);

    }

    public static List<Node> populateList(String[] input) {
        List<Node> tree = new ArrayList<>();
        List<String> data = new ArrayList<>();
        boolean first;
        Node parent;
        for (String i : input) {
            Node node = null;
            String[] segment = i.split(",");
            first = true;
            parent = null;
            for (String j : segment) {
                if (first) {
                    if (data.contains(j)) {
                        parent = tree.get(data.indexOf(j)).getParent();
                    } else {
                        node = new Node(j);
                        parent = node;
                    }
                    first = false;
                }
                else {
                    if (data.contains(j)) {
                        node = tree.get(data.indexOf(j));
                        node.setParent(parent);
                        parent.addLeave(node);
                    } else {
                        node = new Node(parent, j);
                        parent.addLeave(node);
                    }
                }
                tree.add(node);
                data.add(j);
            }
        }


        return tree;
    }

    public static void printList(List<Node> tree) {
        String spacing = "";
        Node root = tree.get(0);
        while (root.getParent() != null) {
            root = root.getParent();
        }
        printLeaves(root, "");


    }

    public static void printLeaves(Node node, String spacing) {
        System.out.println(spacing + node.getData());
        spacing = spacing.concat("  ");
        for (Node leaf : node.getLeaves()) {
            printLeaves(leaf, spacing);
        }

    }

    public static boolean isOutbreak(Room[][] floor){

        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[0].length; j++) {

                if (floor[i][j].isInfected() && !floor[i][j].isVisited()){
                    if(isConnected(i,j,floor,1)){
                        return true;
                    }
                }


            }
        }
        return false;

    }

    public static boolean isConnected(int x,int y,Room[][] floor,int count){
        if(count == 5){
            return true;
        }
        floor[x][y].setVisited(true);
        //Test left
        if(x != 0 && !floor[x-1][y].isVisited()){
            if (floor[x-1][y].isInfected())
                if(isConnected(x-1,y,floor,++count))
                    return true;
            else
                floor[x-1][y].setVisited(true);
        }
        //Test right
        if(x != floor.length - 1 && !floor[x+1][y].isVisited()){
            if(floor[x+1][y].isInfected())
                if(isConnected(x+1,y,floor,++count))
                    return true;

            else
                floor[x+1][y].setVisited(true);
        }
        //Test Down
        if(y != 0 && !floor[x][y-1].isVisited()){
            if(floor[x][y-1].isInfected())
                if(isConnected(x,y-1,floor,++count))
                    return true;
            else
                floor[x][y-1].setVisited(true);
        }
        if(y != floor[x].length && !floor[x][y+1].isVisited()){
            if(floor[x][y+1].isInfected())
                if(isConnected(x,y+1,floor,++count))
                    return true;
            else
                floor[x][y+1].setVisited(true);
        }

        return false;
    }


}
