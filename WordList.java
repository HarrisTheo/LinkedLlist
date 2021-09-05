//AM4646 PANAGIOTIS GEORGIADIS 
//AM4674 CHARALAMPOS THEODORIDIS
import java.io.*;





public class WordList {

    private class Node {
        String str;
        int count;   // occurrences of str in input file
        Node next;   // next node in linked list

        Node(String str) {
            this.str = str;
            this.next = null;
            this.count = 1;
        }
    }

    private Node first;     // first node of the linked list
    private int nodeCount;  // number of nodes

    public WordList() {
        this.nodeCount = 0;
    }

    int nodeCount() {
        return nodeCount;
    }

    // return the number of occurrences of word s in the input file
    public int contains(String s) {
        /* enter you code! */
        Node mynode=first;
        while(mynode!=null){
            if(mynode.str.equals(s)){
                return mynode.count;
            }
            mynode=mynode.next;
        }
        // s not found
        return 0;
    }

    // add one more occurence of word s; insert new node if s is not in the linked list
    public void insert(String s) {
        /* enter you code! */
        Node ranNode=first;
        boolean flag=false;
        while(ranNode!=null) {
            if(ranNode.str.equals(s)){
                ranNode.count++;
                flag=true;
                break;
            }
            ranNode=ranNode.next;
        }
        if(flag==false){
            Node insertNode=new Node(s);
            insertNode.next=first;
            first=insertNode;
            this.nodeCount++;
        }
    }

    // delete word s from the linked list
    public void delete(String s) {
        /* enter you code! */
        Node temp=first;
        Node prev=null;
        if(temp!=null && temp.str.equals(s)){
            first=temp.next;
            return;
        }
        while(temp!=null && temp.str!=s){
            prev=temp;
            temp=temp.next;
        }
        if (temp == null) return;
        prev.next = temp.next;
   }

    // sort linked list in decreasing word frequencies 
    public void freqOrder() { 
        /* enter you code! */ 
            Node current = first;
            Node index = null;  
            int temp;    
            String temp_str;
            while(current != null) {    
                index = current.next;  
                while(index != null) {   
                 if(current.count < index.count) {  
                     temp = current.count;  
                     temp_str=current.str;
                     current.count= index.count; 
                     current.str=index.str; 
                     index.count = temp;
                     index.str=temp_str;  
                    }  
                    index = index.next;  
                }  
                current = current.next;  
             }       
    }
    
    // sort linked list in lexicographic order of words 
    public void lexOrder() {
        /* enter you code! */
          Node current = first;
          Node index = null;  
          int temp;
          String temp_str;     
          while(current != null) {    
                index = current.next;  
                while(index != null) {   
                    if(current.str.compareTo(index.str)>0) {  
                        temp = current.count;  
                        temp_str=current.str;
                        current.count= index.count; 
                        current.str=index.str; 
                        index.count = temp;
                        index.str=temp_str; 
                    }  
                    index = index.next;  
                }  
                current = current.next;  
            }      
            
   }
    
    // find the k-th word in the linked list
    public String select(int k) {
        /* enter you code! */
        Node selectNode=first;
         if(k<nodeCount) {
             for(int index=0; index<k-1; index++){
                 selectNode=selectNode.next;              
             }
             return selectNode.str;
            }
        return null; // change appropriately
    }
    
    // print first k strings of linked list
    public void print(int k) {
        /* enter you code! */
        Node indexNode=first;
        for (int index=0; index<k; index++){
            System.out.println(indexNode.str +" "+ indexNode.count);
            indexNode=indexNode.next;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Test WordList");

        WordList L = new WordList();

        In.init();
        long startTime = System.currentTimeMillis();
        while (!In.empty()) {
            String s = In.getString();
            if (s.isEmpty()) continue;
            L.insert(s);
        }
        long endTime = System.currentTimeMillis();
        long listTime = endTime - startTime;
        System.out.println("linked list construction time = " + listTime);
        System.out.println("number of linked list nodes = " + L.nodeCount());
        System.out.println("");

        System.out.println("contains 'and' " + L.contains("and") + " times");
        System.out.println("contains 'astonished' " + L.contains("astonished") + " times");
        System.out.println("contains 'boat' " + L.contains("boat") + " times");
        System.out.println("contains 'path' " + L.contains("path") + " times");
        System.out.println("contains 'the' " + L.contains("the") + " times");
        System.out.println("contains 'train' " + L.contains("train") + " times");
        System.out.println("contains 'tom' " + L.contains("tom") + " times");
        System.out.println("contains 'wondered' " + L.contains("wondered") + " times");
        System.out.println("");

        System.out.println("sorting words in lexicographical order");
        L.lexOrder();
        System.out.println("first 10 words in lexicographical order are:");
        L.print(10);
        String s = L.select(100);
        System.out.println("100th word in lexicographical order = " + s + "(" + L.contains(s) + ")");
        System.out.println("");
        
        System.out.println("sorting list in decreasing word frequency order");
        L.freqOrder();
        System.out.println("the 10 most frequent words are:");
        L.print(100);
        s = L.select(100);
        System.out.println("100th most frequent word = " + s + "(" + L.contains(s) + ")");
        System.out.println("");
        
        System.out.println("deleting '" + s +"'");
        L.delete(s);
        String t = L.select(100);
        System.out.println("next most frequent word after '" + s + "' = " + t + "(" + L.contains(t) + ")");
        System.out.println("");
        
        endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total running time = " + totalTime);
    }
}
