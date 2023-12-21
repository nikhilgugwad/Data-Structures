import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

class Node<T> {
    int data; // linked list contains a node, which itself contains data and memory address of the next node and hence two parameters 
    Node next;

    Node(int data) {  // a constructor to initialize data in a node when a object of this class is created 
        this.data = data;
        next = null;
    }
}

public class SinglyLinkedList {

    public static void print(Node<Integer> head) {
        while (head != null) { // to print data from all linked nodes
            System.out.print(head.data + " ");
            head = head.next; // now pointing the head variable to the memory address of next node
        }
    }

    public static Node<Integer> takeInput() { // the input is taken by the user, the user will only provide data for the nodes only until the user provides -1 (made as a criteria in this function) as the data to end the list 
        Node<Integer> head = null, tail = null; // taking a head and tail variable and making it null
        Scanner s = new Scanner(System.in);
        int data = s.nextInt(); // the first data provided by the user will be stored in data

        while (data != -1) { // in order to enter this loop the first data should not be -1
            Node<Integer> newNode = new Node<Integer>(data); // so we will create our first node and add our input data (the newNode.next will remain empty as per constructor defined)
            if (head == null) { // for the first input data head will be equal to null, so this condition will be true
                head = newNode; // now the head variable will ne pointing to the newNode
                tail = newNode; // when there is only one node then head & tail variable will be pointing to the same node
            } else { 
                tail.next = newNode; // currently tail is pointing to the same node as the head is pointing, so we are establishing a link with the new node added
                tail = newNode; // to point the tail variable to the new node
            }
            data = s.nextInt(); // to accept the next input value
        }

        return head;
    }

    public static Node<Integer> insert(Node<Integer> head,int data,int pos) { // we need to pass a linked list,a new node & specify its position
        Node<Integer> newNode = new Node<Integer>(data); // creating a new node & inserting the input data
        if (pos == 0) { // when specified position is at the starting point 0 
            newNode.next = head; // as head will be having the initial node, hence establishing link b/w new node and the earlier first node of the linked list, now the new node becomes the first node in a linked list
            return newNode;
        }
        int i = 0; // for traversing purposes
        Node<Integer> temp = head;
        while (i < pos-1) { // we need to traverse at a point until we reach one position behind the specified postion
            temp = temp.next;
            i++;
        }
        newNode.next = temp.next; // establishing link b/w the new node(input data) with the next node of the node(pos-1 node) which we have traversed so far
        temp.next = newNode; // now establishing link b/w traversed node(pos-1 node) & new node
        return temp;
    }

    public static Node<Integer> delete(Node<Integer> head, int pos) {
        if (pos == 0) { // in case of pos=0 which is an exception, the head reference variable of Node<Integer> type is always pointing to the first node
            return head.next; // so after returning the head.next, the head now will point to the second node and will now print the list of nodes  
        } // starting from the second node(will be considered first) by omitting the first node
        int i = 0; // for traversing purposes
        Node<Integer> temp = head;
        while (i < pos-1) { // we need to traverse at a point until we reach one position behind the specified postion
            temp = temp.next;
            i++;
        }
        temp.next = temp.next.next; // establishing the link b/w the traversed node(pos-1 node) and the next node of a node which is marked for deletion
        return temp;
    }

    public static Node<Integer> findMidPoint(Node<Integer> head) {
        if (head == null) {
            return null;
        }

        Node<Integer> slow = head; // creating two pointers (objects of Node<Integer> type) slow and fast
        Node<Integer> fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow pointer one step at a time
            fast = fast.next.next; // Move fast pointer two steps at a time
        }

        return slow; // at last if there is no node ahead of fast pointer i.e fast.next the condition will failed and will not enter inside the loop, the slow variable will be at midpoint at this situation(only if odd no. of elements)
    }

    public static Node<Integer> mergeSortedLists(Node<Integer> head1, Node<Integer> head2) {
        if (head1 == null) {
            return head1;
        }
        if (head2 == null) {
            return head1;
        }

        Node<Integer> mergedHead; // creating mergedHead variable that will point to the first node of the mergedlist
        Node<Integer> mergedTail; // creating mergedTail variable that will point to the last node of the mergedlist

        if (head1.data <= head2.data) { // comparing the first nodes of both head1 and head2 lists 
            mergedHead = head1; // mergedHead will point to the one with lowest data
            mergedTail = head1; // currently mergeTail will also point to the same lowest data
            head1 = head1.next; // now the lowest data is already been added to the mergedlist(mergedHead knows lowest data), now the head1 will point to the next node in its list
        } else { // vice versa
            mergedHead = head2;
            mergedTail = head2;
            head2 = head2.next;
        }

        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) { // comparing with the updated nodes again
                mergedTail.next = head1; // currently the mergedTail is pointing towards the first node to which mergedHead is pointing, so updating the mergedTail.next memory address of new lowest head1 node 
                mergedTail = head1; // now the mergedTail is pointing at the last node of merged list
                head1 = head1.next; // now the head1 will point to the next node in its list
            } else { // vice versa
                mergedTail.next = head2;
                mergedTail = head2;
                head2 = head2.next;
            }
        }

        if (head1 != null) {
            mergedTail.next = head1; // if any more nodes are available in the head1 list(head2 has become null), then the mergedTail will have the address of the first node from the remaining node, so the merged list would be completed 
        } else {
            mergedTail.next = head2;
        }

        return mergedHead; // returning mergedHead containing/pointing only towards the first node 


    }

    // using fast & slow pointer approach
    public static Node<Integer> findMid(Node<Integer> head) {
        Node<Integer> slow = head; // slow var is pointing towards the first node of head var
        Node<Integer> fast = head.next; // fast var is pointing towards the second node of head var

        while (fast != null && fast.next != null) {
            slow = slow.next; // slow var moves one step at a time
            fast = fast.next.next; // fast var moves two steps at a time
        }

        return slow; // when while condition satisfies slow var will be pointing towards the mid node of linked list

    }

    public static Node<Integer> mergeSort(Node<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }

        // break linkedlist into two halves, after finding mid
        Node<Integer> mid = findMid(head);

        Node<Integer> left = head;
        Node<Integer> right = mid.next;
        mid.next = null; // breaking existing link b/w the two halves 

        // recursive calls to sort both halves
        left = mergeSort(left);
        right = mergeSort(right);

        return mergeSortedLists(left, right);
    }

    // using recursion
    public static Node<Integer> reverseLL(Node<Integer> head) {
        /*
             Procedure:
            1) by using recursion we need to solve only one case and the rest of the cases will be handled by it i.e we need to perform some operations on first node itself
            2) the reversell(head.next) will reverse the entire list from the next node of the first node i.e head.data 
            3) the finalHead will be containing the head (last node of the list given as input) of the reversed list
            4) now we will break the link b/w the second node (head.next.next) and third node and establish link b/w second node and first node
            5) at last we will establish the link b/w the first node and null meaning the first node now has become the last node and doesn't contain any further nodes
         */

        // base case
        if (head == null || head.next == null) {
            return head;
        }

        Node<Integer> finalHead = reverseLL(head.next);
        head.next.next = head;
        head.next = null;
        return finalHead;
    }

    public static Node<Integer> insertRecursively(Node<Integer> head, int pos, int elem) {

        // base case
        if (pos == 0) {
            Node<Integer> newNode = new Node<Integer>(elem); // creating a new node to insert elem value
            newNode.next = head; // the newly created node now points to the first node of the input list or to the recursively traversed node
            return newNode;
        }

        // if head has become null 
        if (head == null) { // if a position is given in which it exceeds the range of the given list, because the head will now be pointing towards null
            return head; // returning the list as it is
        }

        head.next = insertRecursively(head.next, pos - 1, elem); // the recursion starts from the second node, simultaneously the pos is reduced by -1 till it becomes 0 so that the base can be executed and the required elem node can be created and attached to the recursively traversed node
        // the head.next will make the current headnode at the time of recursive call link to the newly inserted node 
        return head; // the head will be returned back to the previous recursive call, this will continue till all the previous recursive calls finish executing and finally it will return completed list
    }

    public static Node<Integer> deleteRecursively(Node<Integer> head, int pos) {
        
        if(head == null) { // if a position is given in which it exceeds the range of the given list, because the head will now be pointing towards null
            return head; // returning the list as it is
        }
        
        if (pos == 0) {
            return head.next;// if the position of the node to delete is the first node, just simply return the next node it is pointing to
        }

        head.next = deleteRecursively(head.next, pos - 1); // after recursively traversing to the node which we wanted to delete by then pos would have become 0 and it will return the next node of the node which is about to be deleted i.e head.next , this recursive call will return and will link to the previous recursive sublist
        return head; // the head will be returned back to the previous recursive call, this will continue till all the previous recursive calls finish executing and finally it will return completed list 
    }
    

    public static void main(String[] args) {
        // System.out.println("waiting for input");
        // Node<Integer> head = takeInput();
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.addFirst(30);
        list.addLast(40);
        
        // iterating using for loop
        for (int i=0;i<list.size();i++) {
            System.out.println(list.get(i));
        }

        //iterating using list iterator

        ListIterator<Integer> listIterator = list.listIterator(); // Create a `ListIterator` for the `LinkedList`
        // Use the `ListIterator` to iterate through the list
        while (listIterator.hasNext()) { // You can use methods like `hasNext()` to check if there's a next element and `next()` to get the next element.
            int elem = listIterator.next();
            System.out.println("forward: " + elem);
        }

        while (listIterator.hasPrevious()) { // You can also use methods like `hasPrevious()` to check if there's a previous element and `previous()` to get the previous element.
            int elem = listIterator.previous();
            System.out.println("backward: " + elem);
        }
    }
}


