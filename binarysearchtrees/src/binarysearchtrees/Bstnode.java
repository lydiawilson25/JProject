package binarysearchtrees;

public class Bstnode {
    Comparable data;

    public Bstnode getLeft() {
        return left;
    }

    public void setLeft(Bstnode left) {
        this.left = left;
    }

    public Bstnode getRight() {
        return right;
    }

    public void setRight(Bstnode right) {
        this.right = right;
    }

    Bstnode left;
    Bstnode right;
    public Bstnode(Comparable d){
        data = d;
        left = right = null;
    }



    public static void main(String[] args){
        Bstnode root =new Bstnode(35);
        root.setLeft(new Bstnode(22));
    }


}
