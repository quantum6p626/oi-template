class Node {
    Node prev, next;
    int val;
    Node(int val) {
        this.val = val;
        prev = null;
        next = null;
    }
}
class DLL {
    Node head, tail;
    int sz;
    DLL() {
        head = null;
        tail = null;
        sz = 0;
    }
    Node getFirst() {
        if (sz == 0)
            return null;
        return head;
    }
    Node getLast() {
        if (sz == 0)
            return null;
        return tail;
    }
    Node addFirst(int val) {
        Node node = new Node(val);
        if (sz == 0) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        sz++;
        return node;
    }
    Node addLast(int val) {
        Node node = new Node(val);
        if (sz == 0) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        sz++;
        return node;
    }
    //add after node
    Node add(Node node, int val) {
        if (node == tail)
            return addLast(val);
        Node node2 = new Node(val), p = node.next;
        node.next = node2;
        node2.prev = node;
        node2.next = p;
        p.prev = node2;
        sz++;
        return node2;
    }
    Node removeFirst() {
        Node old = head;
        if (sz == 1) {
            head = null;
            tail = null;
        } else {
            Node p = head;
            head = head.next;
            head.prev = null;
            p.next = null;
        }
        sz--;
        return old;
    }
    Node removeLast() {
        Node old = tail;
        if (sz == 1) {
            head = null;
            tail = null;
        } else {
            Node p = tail;
            tail = tail.prev;
            tail.next = null;
            p.prev = null;
        }
        sz--;
        return old;
    }
    Node remove(Node node) {
        if (sz == 1) {
            head = null;
            tail = null;
            sz--;
        } else if (node == head)
            removeFirst();
        else if (node == tail)
            removeLast();
        else {
            Node p = node.prev, q = node.next;
            p.next = q;
            q.prev = p;
            sz--;
        }
        node.prev = null;
        node.next = null;
        return node;
    }
    int size() {
        return sz;
    }
}
