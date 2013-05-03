package jp.gr.java_conf.genzo.blog.java.collection;

import java.util.TreeSet;

public class SetTreeSet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		for (int i = 1; i < 10; i++) {
			treeSet.add((int) Math.pow(2, i));
		}

		// [2, 4, 8, 16, 32, 64, 128, 256, 512]
		System.out.println("floor 10 -> " + treeSet.floor(10));
		System.out.println("floor 20 -> " + treeSet.floor(20));
		System.out.println("floor 64 -> " + treeSet.floor(64));
		System.out.println("ceiling 10 -> " + treeSet.ceiling(10));
		System.out.println("ceiling 20 -> " + treeSet.ceiling(20));
		System.out.println("ceiling 64 -> " + treeSet.ceiling(64));

	}

}
