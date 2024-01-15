import java.util.ArrayList;
import java.util.List;

public class MyBinarySearchTreeNode<T extends Comparable> {

    private T item;
    private MyBinarySearchTreeNode<T> parent;
    private MyBinarySearchTreeNode<T> leftChild;
    private MyBinarySearchTreeNode<T> rightChild;

    private MyBinarySearchTreeNode(T itemInput, MyBinarySearchTreeNode<T> parentInput){
        this.parent = parentInput;
        this.item = itemInput;
    }

    public MyBinarySearchTreeNode(T itemInput){
        this(itemInput, null);
    }

    public MyBinarySearchTreeNode<T> getLeftChild(){
        return leftChild;
    }

    public MyBinarySearchTreeNode<T> getRightChild(){
        return rightChild;
    }

    public Integer getChildrenLength(){
        int x = 0;
        if (this.rightChild != null){
            x ++;
        }
        if (this.leftChild != null){
            x ++;
        }
        return x;
    }

    public T getItem(){
        return item;
    }

    public MyBinarySearchTreeNode<T> getParent(){
        return parent;
    }

    public boolean isLeaf(){
        if (this.getRightChild() == null && this.getLeftChild() == null){
            return true;
        }
        else{
            return false;
        }
    }

    public MyBinarySearchTreeNode<T> insert(T itemIn){
        if (itemIn.compareTo(this.item) == 0){
            return null;
        }
        if (itemIn.compareTo(this.item) < 0){
            if (leftChild == null){
                MyBinarySearchTreeNode<T> childNode = new MyBinarySearchTreeNode<T>(itemIn, this);
                this.leftChild = childNode;
                return childNode;
            }
            else{
                return leftChild.insert(itemIn);
            }
        }
        else{
            if (rightChild == null){
                MyBinarySearchTreeNode<T> childNode = new MyBinarySearchTreeNode<T>(itemIn, this);
                this.rightChild = childNode;
                return childNode;
            }
            else{
                return rightChild.insert(itemIn);
            }
        }
    }

    public void preOrder(List<T> list){
        list.add(this.item);
        if (leftChild != null){
            leftChild.preOrder(list);
        }
        if (rightChild != null){
            rightChild.preOrder(list);
        }
    }

    public void inOrder(List<T> list){
        if (leftChild != null){
            leftChild.inOrder(list);
        }
        list.add(this.item);
        if (rightChild != null){
            rightChild.inOrder(list);
        }
    }

    public void postOrder(List<T> list){
        if (leftChild != null){
            leftChild.postOrder(list);
        }
        if (rightChild != null){
            rightChild.postOrder(list);
        }
        list.add(this.item);
    }

    public MyBinarySearchTreeNode<T> find(Object obj){
        if (this.item.compareTo(obj) == 0){
            return this;
        }
        else if (this.item.compareTo(obj) > 0){
            if (leftChild != null){
                return leftChild.find(obj);
            }
            else{
                return null;
            }
        }
        else{
            if (rightChild != null){
                return rightChild.find(obj);
            }
            else{
                return null;
            }
        }
    }

    private MyBinarySearchTreeNode<T> findSuccessor(){
        if (rightChild != null){
            MyBinarySearchTreeNode<T> node = rightChild.leftChild;
            while (node.leftChild != null){
                node = node.leftChild;
            }
            return node;
        }
        else{
            return this;
        }
    }

    public void remove(T itemIn){
        if (this.item.compareTo(itemIn) == 0){
            if (this.isLeaf()){
                if (this.item.compareTo(this.getParent().getItem()) > 0){
                    this.getParent().rightChild = null;
                }
                else{
                    this.getParent().leftChild = null;
                }
            }
            if (this.getChildrenLength() == 1){
                if (this.leftChild != null){
                    MyBinarySearchTreeNode<T> childNode = this.leftChild;
                    if (this.item.compareTo(this.getParent().getItem()) > 0){
                    this.getParent().rightChild = childNode;
                }
                }
                else{
                    MyBinarySearchTreeNode<T> childNode = this.rightChild;
                    if (this.item.compareTo(this.getParent().getItem()) < 0){
                    this.getParent().leftChild = childNode;
                }
            if (this.getChildrenLength() == 2){
                T successor = this.findSuccessor().getItem();
                remove(successor);
                this.item = successor;
            }
            }
        }
    
        else if (this.item.compareTo(itemIn) < 0){
            if (this.leftChild != null){
                remove(itemIn);
            }
            else{
                return;
            }
        }
        else{
            if (this.rightChild != null){
                remove(itemIn);
            }
            else{
                return;
            }
        }
    }
    }
}

