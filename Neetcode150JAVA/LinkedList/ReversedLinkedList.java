public class ReverseLinkedList {

    // Definition of a Linked List Node
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // Function to reverse the linked list
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {

            // Store the next node
            ListNode next = curr.next;

            // Reverse the link
            curr.next = prev;

            // Move prev and curr one step forward
            prev = curr;
            curr = next;
        }

        // prev is the new head
        return prev;
    }

    // Function to print the linked list
    public static void printList(ListNode head) {
        ListNode temp = head;

        while (temp != null) {
            System.out.print(temp.val);

            if (temp.next != null) {
                System.out.print(" -> ");
            }

            temp = temp.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        // Create the linked list:
        // 1 -> 2 -> 3 -> 4 -> 5

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original Linked List:");
        printList(head);

        head = reverseList(head);

        System.out.println("Reversed Linked List:");
        printList(head);
    }
}