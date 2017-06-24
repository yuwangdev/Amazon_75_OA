import java.util.Arrays;

public class FindDistanceBetweenTwoNods {

    public static int solve(int[] array, int size, int n1, int n2) {
        if (array == null) return -1;
        if (size <= 0) return -1;
        if (array.length != size) return -1;
        if (n1 == n2) return 0;
        boolean existedN1 = false;
        boolean existedN2 = false;
        for (int i : array) {
            if (i == n1) existedN1 = true;
            if (i == n2) existedN2 = true;
        }
        if (existedN1 == false || existedN2 == false) return -1;

        Arrays.sort(array);
        Node root = convertArrayToBST(array, 0, size - 1);
        return findDistanceInBST(root, n1, n2);
    }

    public static Node convertArrayToBST(int[] num, int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        Node root = new Node(num[mid]);
        root.left = convertArrayToBST(num, start, mid - 1);
        root.right = convertArrayToBST(num, mid + 1, end);
        return root;
    }


    public static int findDistanceInBST(Node root, int n1, int n2) {
        int x = findDistanceToRoot(root, n1) - 1;
        int y = findDistanceToRoot(root, n2) - 1;
        int lcaNodeValue = findLowestCommonAncestor(root, n1, n2).data;
        int lcaDistance = findDistanceToRoot(root, lcaNodeValue) - 1;
        return (x + y) - 2 * lcaDistance;
    }

    public static int findDistanceToRoot(Node root, int n1) {
        if (root != null) {
            int x = 0;
            if ((root.data == n1) || (x = findDistanceToRoot(root.left, n1)) > 0
                    || (x = findDistanceToRoot(root.right, n1)) > 0) {
                return x + 1;
            }
            return 0;
        }
        return 0;
    }

    public static Node findLowestCommonAncestor(Node root, int n1, int n2) {
        if (root != null) {
            if (root.data == n1 || root.data == n2) {
                return root;
            }
            Node left = findLowestCommonAncestor(root.left, n1, n2);
            Node right = findLowestCommonAncestor(root.right, n1, n2);

            if (left != null && right != null) {
                return root;
            }
            if (left != null) {
                return left;
            }
            if (right != null) {
                return right;
            }
        }
        return null;
    }

    public static void main(String[] args) throws java.lang.Exception {
        Node root = new Node(5);
        root.left = new Node(10);
        root.right = new Node(15);
        root.left.left = new Node(20);
        root.left.right = new Node(25);
        root.right.left = new Node(30);
        root.right.right = new Node(35);
        root.left.right.right = new Node(45);

        int[] arrayTest1 = {5, 6, 3, 1, 2, 4};
        System.out.println("Distance between 2 and 4 is : "
                + solve(arrayTest1, 6, 2, 4));

        System.out.println("Distance between 2 and 1 is : "
                + solve(arrayTest1, 6, 2, 1));

        System.out.println("Distance between 45 and 20 is : "
                + findDistanceInBST(root, 45, 20) + " == 3");


        System.out.println("Distance between 45 and 10 is : "
                + findDistanceInBST(root, 45, 10) + " == 2");


        System.out.println("Distance between 5 and 30 is : "
                + findDistanceInBST(root, 5, 30) + " == 2");


        int[] array = {5, 10, 20, 25, 15, 30, 35, 45};

        System.out.println("Distance between 45 and 20 is : "
                + solve(array, 8, 45, 20));

        System.out.println("Distance between 45 and 10 is : "
                + solve(array, 8, 45, 10));

        System.out.println("Distance between 35 and 30 is : "
                + solve(array, 8, 35, 30));


    }

    public static boolean isValidBST(Node root) {
        return isValidBST(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public static boolean isValidBST(Node p, double min, double max) {
        if (p == null)
            return true;
        if (p.data <= min || p.data >= max)
            return false;
        return isValidBST(p.left, min, p.data) && isValidBST(p.right, p.data, max);
    }
}

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}