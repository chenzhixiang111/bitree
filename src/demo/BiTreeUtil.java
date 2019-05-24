package demo;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BiTreeUtil {
	/**
	 * 二叉树前序遍历，递归
	 */
	public static void preOrder(BiTNode node){
		if (node == null) {
			return;
		}
		System.out.println(node.data);
		preOrder(node.lchild);
		preOrder(node.rchild);
	}
	/**
	 * 二叉树前序遍历，非递归
	 * 非递归的核心思想就是先将自己和自己的左孩子推入栈中，等左孩子弹出栈之后再将右孩子推入栈中
	 * @param node
	 */
	public static void preOrderLoop(BiTNode node) {
		Stack<BiTNode> stack = new Stack<>();
		BiTNode cur = node;
		while (cur !=null || !stack.isEmpty()) {
			while (cur != null) {
				System.out.println(cur.data);
				stack.push(cur);
				cur = cur.lchild;
			}
			BiTNode top = stack.pop();
			cur = top.rchild;
		}
	}
	/**
	 * 二叉树中序遍历，递归代码
	 * @param node
	 */
	public static void inOrder(BiTNode node) {
		if (node == null) {
			return;
		}
		inOrder(node.lchild);
		System.out.println(node.data);
		inOrder(node.rchild);
	}
	/**
	 * 二叉树中序遍历，非递归代码。这个时间复杂度应该是O[n*n]，n是树的节点数
	 * @param node
	 */
	public static void inOrderLoop(BiTNode node) {
		Stack<BiTNode> stack = new Stack<>();
		BiTNode cur = node;
		while (cur!=null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.lchild;
			}
			BiTNode top = stack.pop();
			System.out.println(top.data);
			cur = top.rchild;
		}
	}
	/**
	 * 二叉树后序遍历，递归代码.根节点最后被打印
	 * @param node
	 */
	public static void postOrder(BiTNode node) {
		if (node == null) {
			return;
		}
		postOrder(node.lchild);
		postOrder(node.rchild);
		System.out.println(node.data);
	}
	/**
	 * 后序遍历，非递归。
	 * 后序遍历与前序中序的区别在于，它取栈定元素的时候并不会直接选择弹栈，而是先观察它有没有右节点，有的话把右节点压栈，没有的话就弹栈
	 * @param node
	 */
	public static void postOrderLoop(BiTNode node) {
		Stack<BiTNode> stack = new Stack<>();
		BiTNode cur = node;
		BiTNode last = null;
		while(cur!=null || !stack.isEmpty()) {
			while(cur != null) {
				stack.push(cur);
				cur = cur.lchild;
			}
			//获取栈顶元素但是不把它弹出
			BiTNode top = stack.peek();
			if (top.rchild==null || top.rchild == last) {
				System.out.println(top.data);
				stack.pop();
				last = top;
			}else {
				cur = top.rchild;
			}
		}
	}
	
	/**
	 * 层序遍历
	 * @param node
	 */
	public static void levelOrder(BiTNode root) {
		Queue<BiTNode> q = new ConcurrentLinkedQueue<>();
		BiTNode front = null;
		if (root == null) {
			return;
		}
		q.offer(root);
		while (!q.isEmpty()) {
			front = q.poll();
			if (front.lchild != null) {
				q.offer(front.lchild);
			}
			if (front.rchild != null) {
				q.offer(front.rchild);
			}
			System.out.println(front.data);
		}
		
	}
	
	
	/**
	 *      a
	 *     / \
	 *    b   c
	 *   / \   \
	 *  d   e   f
	 * 
	 */
	public static void main(String[] args) {
		BiTNode<String> a = new BiTNode<>("a");
		BiTNode<String> b = new BiTNode<>("b");
		BiTNode<String> c = new BiTNode<>("c");
		BiTNode<String> d = new BiTNode<>("d");
		BiTNode<String> e = new BiTNode<>("e");
		BiTNode<String> f = new BiTNode<>("f");
		a.lchild = b;
		a.rchild = c;
		b.lchild = d;
		b.rchild = e;
		c.rchild = f;
		
		levelOrder(a);
	}
}
