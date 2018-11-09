package com.example.josh.week3dailytest;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String data;
    Node parent;
    List<Node> leaves = new ArrayList<>();

    public Node(List<Node> leaves){
        this.leaves = leaves;

    }
    public Node(String data){
        this.parent = null;
        this.data = data;
    }

    public Node(Node parent, List<Node> leaves) {
        this.parent = parent;
        this.leaves = leaves;
    }

    public Node(Node parent) {

        this.parent = parent;
    }

    public Node(Node parent, String data) {
        this.parent = parent;
        this.data = data;
    }


    public Node findNode(Node input){

        if(leaves.contains(input)){
            return leaves.get(leaves.indexOf(input));
        }
        else
            return null;
    }



    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getLeaves() {
        return leaves;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setLeaves(List<Node> leaves) {
        this.leaves = leaves;
    }

    public void addLeave(Node node){
        leaves.add(node);
    }
}
