package io.nkcoder.alg.coding.tree;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import io.nkcoder.alg.basic.datastructure.TreeNode;

/**
 * 树的深度遍历和广度遍历, 深度遍历用栈，广度遍历用队列
 **/
public class TreeTraverse {

  /**
   * 
   * @param root
   */
  public void dfs(TreeNode root) {
    Stack<TreeNode> stack = new Stack<TreeNode>();
    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      printNode(node);

      if (node.right != null) {
        stack.push(node.right);
      }

      if (node.left != null) {
        stack.push(node.left);
      }

    }

  }
  
  /**
   * 广度优先遍历就是层次遍历
   * 
   * @param root
   */
  public void bfs(TreeNode root) {
    Queue<TreeNode> queue = new LinkedBlockingQueue<TreeNode>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      printNode(node);

      if (node.left != null) {
        queue.offer(node.left);
      }

      if (node.right != null) {
        queue.offer(node.right);
      }

    }

  }

  private void printNode(TreeNode node) {
    System.out.println(node.value);
  }

}
