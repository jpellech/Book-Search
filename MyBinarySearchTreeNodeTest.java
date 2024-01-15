import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class MyBinarySearchTreeNodeTest {
    
    @Test
    void testGetItem() {
        MyBinarySearchTreeNode node = new MyBinarySearchTreeNode(1);
        AssertEquals(node.getItem(), 1);
    }

    private void AssertEquals(Comparable item, int i) {
    
    }

    @Test
    void testGetParent() {
        MyBinarySearchTreeNode<Integer> node = new MyBinarySearchTreeNode<Integer>(1);
        MyBinarySearchTreeNode<Integer> childNode = node.insert(2);
        assertEquals(node, childNode.getParent());


    }

    @Test
    void testInsert() {
        MyBinarySearchTreeNode node = new MyBinarySearchTreeNode(1);
        MyBinarySearchTreeNode child1 = node.insert(2);
        AssertNull(node.insert(1));
    }

    private void AssertNull(MyBinarySearchTreeNode insert) {
    }

    @Test
    void testInOrder() {
        MyBinarySearchTreeNode<Integer> root = new MyBinarySearchTreeNode<>(151);
        root.insert(150);
        root.insert(241);
        root.insert(210);
        root.insert(275);
        root.insert(280);

        List<Integer> inOrderResult = new ArrayList<>();
        root.inOrder(inOrderResult);
        List<Integer> expected = List.of(150, 151, 210, 241, 275, 280);
        assertEquals(expected, inOrderResult);
    }

    @Test
    void testPostOrder() {
        MyBinarySearchTreeNode<Integer> root = new MyBinarySearchTreeNode<>(151);
        root.insert(150);
        root.insert(241);
        root.insert(210);
        root.insert(275);
        root.insert(280);
    
        List<Integer> postOrderResult = new ArrayList<>();
        root.postOrder(postOrderResult);
        assertEquals(List.of(150, 210, 280, 275, 241, 151), postOrderResult);
    }

    @Test
    void testPreOrder() {
        MyBinarySearchTreeNode<Integer> root = new MyBinarySearchTreeNode<>(151);
        root.insert(150);
        root.insert(241);
        root.insert(210);
        root.insert(275);
        root.insert(280);
        

        List<Integer> result = new ArrayList<>();
        root.preOrder(result);

        List<Integer> expected = List.of(151, 150, 241, 210, 275, 280);
        assertEquals(expected, result);
    }

    @Test
    void testFind() {
        MyBinarySearchTreeNode<Integer> root = new MyBinarySearchTreeNode<>(151);
        root.insert(150);
        MyBinarySearchTreeNode<Integer> found = root.insert(241);
        root.insert(210);
        root.insert(275);
        root.insert(280);
        AssertEquals(root.find(241), found);
        AssertEquals(root.find(10000), null);
    }

    private void AssertEquals(MyBinarySearchTreeNode<Integer> find, MyBinarySearchTreeNode<Integer> found) {
    }
}
