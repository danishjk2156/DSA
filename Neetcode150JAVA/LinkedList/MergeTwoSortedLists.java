class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {

                tail.next = list1;
                list1 = list1.next;

            } else {

                tail.next = list2;
                list2 = list2.next;
            }

            tail = tail.next;
        }

        if (list1 != null)
            tail.next = list1;
        else
            tail.next = list2;

        return dummy.next;
    }
}

public class Main {

    static ListNode create(int arr[]) {

        if(arr.length==0)
            return null;

        ListNode head=new ListNode(arr[0]);

        ListNode temp=head;

        for(int i=1;i<arr.length;i++){

            temp.next=new ListNode(arr[i]);
            temp=temp.next;
        }

        return head;
    }

    static void print(ListNode head){

        while(head!=null){

            System.out.print(head.val);

            if(head.next!=null)
                System.out.print(" -> ");

            head=head.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        int a[]={1,2,4};
        int b[]={1,3,4};

        ListNode list1=create(a);
        ListNode list2=create(b);

        Solution obj=new Solution();

        ListNode ans=obj.mergeTwoLists(list1,list2);

        print(ans);
    }
}
