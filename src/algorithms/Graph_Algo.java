package algorithms;

import dataStructure.*;
import utils.Point3D;
import java.io.*;
import java.util.*;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms, Serializable {

	public graph algo = new DGraph();

	@Override
	public void init(graph g) {
		this.algo=g;
	}

	@Override
	public void init(String file_name) {
		try {
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);
			this.algo = (graph) in.readObject();
			in.close();
			file.close();

			System.out.println("Object has been deserialized");
		}
		catch(IOException ex) {
			System.out.println("IOException is caught");
		}
		catch(ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}
	}

	@Override
	public void save(String file_name) {
		try {
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(this.algo);
			out.close();
			file.close();

			System.out.println("Object has been serialized");
		}
		catch (IOException ex) {
			System.out.println("IOException is caught");
		}
	}

	@Override
	public boolean isConnected() {
		graph ans = this.copy();
		Iterator it = ans.getV().iterator();
		dfs((node_data) it.next());
		it = ans.getV().iterator();
		while (it.hasNext()) {
			node_data temp = (node_data) it.next();
			if (temp.getTag() == 0) return false;
		}
		this.changeTagEdge();
		this.changeTagNode();
		oppositeDest(ans);
		it = ans.getV().iterator();
		dfs((node_data) it.next());
		it = ans.getV().iterator();
		while (it.hasNext()) {
			node_data temp = (node_data) it.next();
			if (temp.getTag() == 0) return false;
		}
		return true;
	}

	public void dfs(node_data n) {
		if (this.algo.getE(n.getKey()) != null) {
			Iterator it = this.algo.getE(n.getKey()).iterator();
			while (it.hasNext()) {
				edge_data e = (edge_data) it.next();
				node_data dest = this.algo.getNode(e.getDest());
				if (dest.getTag() == 0) {
					dest.setTag(1);
					dfs(dest);
				}
			}
		}
	}

	public void oppositeDest(graph d) {
		Iterator it = d.getV().iterator();
		while (it.hasNext()) {
			node_data n = (node_data) it.next();
			if (d.getE(n.getKey()) != null) {
				Iterator it1 = d.getE(n.getKey()).iterator();
				while (it1.hasNext()) {
					edge_data e = (edge_data) it1.next();
					if (e.getTag() == 0) {
						if (d.getEdge(e.getDest(), e.getSrc()) != null) {
							Edge temps = new Edge((Edge) d.getEdge(e.getSrc(), e.getDest()));
							double tempWeight1 = d.getEdge(e.getSrc(), e.getDest()).getWeight();
							double tempWeight2 = d.getEdge(e.getDest(), e.getSrc()).getWeight();
							d.connect(e.getSrc(), e.getDest(), tempWeight2);
							d.connect(temps.getDest(), temps.getSrc(), tempWeight1);
							d.getEdge(temps.getDest(), temps.getSrc()).setTag(1);
							d.getEdge(temps.getSrc(), temps.getDest()).setTag(1);
							it1 = d.getE(n.getKey()).iterator();
						}
						else {
							d.connect(e.getDest(), e.getSrc(), e.getWeight());
							d.removeEdge(e.getSrc(), e.getDest());
							d.getEdge(e.getDest(), e.getSrc()).setTag(1);
							it1 = d.getE(n.getKey()).iterator();
						}
					}
				}
			}
		}
	}



	public void changeTagNode() {
		Iterator it = this.algo.getV().iterator();
		while (it.hasNext()) {
			node_data n = (node_data) it.next();
			n.setTag(0);
		}
	}

	public void changeTagEdge() {
		Iterator it = this.algo.getV().iterator();
		while (it.hasNext()) {
			node_data n = (node_data) it.next();
			if (this.algo.getE(n.getKey()) != null) {
				Iterator itEdge = this.algo.getE(n.getKey()).iterator();
				while (itEdge.hasNext()) {
					edge_data e = (edge_data) itEdge.next();
					e.setTag(0);
				}
			}
		}
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		if(src==dest) return 0;
		if(this.algo.getNode(src)==null || this.algo.getNode(dest)==null){
			throw new RuntimeException("This nodes are not exist");
		}
		changeTagNode();
		Iterator it = this.algo.getV().iterator();
		while (it.hasNext()) {
			node_data n = (node_data) it.next();
			n.setWeight(Integer.MAX_VALUE);
		}
		node_data temp = this.algo.getNode(src);
		temp.setWeight(0);
		shortestPathDistRec(temp, this.algo.getNode(dest));
		return this.algo.getNode(dest).getWeight();
	}

	public void shortestPathDistRec(node_data n, node_data dest) {
		if (n.getTag() == 1 && n.getKey() == dest.getKey()) {
			return;
		}
		if (this.algo.getE(n.getKey()) != null) {
			Iterator it = this.algo.getE(n.getKey()).iterator();
			while (it.hasNext()) {
				edge_data e = (edge_data) it.next();
				node_data d = this.algo.getNode(e.getDest());
				if (this.algo.getNode(e.getSrc()).getWeight() + e.getWeight() < d.getWeight()) {
					d.setWeight(this.algo.getNode(e.getSrc()).getWeight() + e.getWeight());
					n.setTag(1);
					shortestPathDistRec(this.algo.getNode(e.getDest()), dest);
				}
			}
		}
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		List<node_data> ans = new LinkedList<>();
		this.shortestPathDist(src,dest);
		if(this.algo.getNode(src).getWeight()==Integer.MAX_VALUE  || this.algo.getNode(dest).getWeight()==Integer.MAX_VALUE){
			return null;
		}
		graph tempGraph = this.copy();
		node_data min = tempGraph.getNode(dest);
		oppositeDest(tempGraph);
		ans.add(min);
		while (min.getKey()!=src){
			Collection<edge_data> coll =tempGraph.getE(min.getKey());
			if(coll!=null) {
				for (edge_data e : coll) {
					node_data temp = tempGraph.getNode(e.getDest());
					if (temp.getWeight() + e.getWeight() == min.getWeight()) {
						min = temp;
					}
				}
			}
			ans.add(min);
		}
		List<node_data> temp = new LinkedList<>();
		for (int i = ans.size()-1; i >=0 ; i--) {
			temp.add(ans.get(i));
		}
		ans=temp;
		return ans;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		List<node_data> ans = new LinkedList<>();
		if(targets.size()==1){
			ans.add(this.algo.getNode(targets.get(0)));
			return ans;
		}
		double tempshortestPath = 0;
		int tempk1 = 0;
		int tempk2 = 0;
		int k1 = targets.get(0);
		while (targets.size()!=0) {
			for (int j = 0; j < targets.size(); j++) {
				double minshortestPath = Integer.MAX_VALUE;
				int k2 = targets.get(j);
				if (k1 != k2) {
					tempshortestPath = minshortestPath;
					if (this.shortestPathDist(k1, k2) == Integer.MAX_VALUE) return null;
					minshortestPath = Math.min(minshortestPath, this.shortestPathDist(k1, k2));
					if (tempshortestPath != minshortestPath) {
						tempk1 = k1;
						tempk2 = k2;
					}
				}
			}
			List<node_data> add = this.shortestPath(tempk1, tempk2);
			for (int j = 0; j < add.size(); j++) {
				node_data n = add.get(j);
				if (ans.size() == 0 || ans.get(ans.size() - 1).getKey()!=n.getKey()) {
					ans.add(n);
				}
				for (int k = 0; k < targets.size(); k++) {
					int k4 = targets.get(k);
					if (n.getKey() == k4) {
						targets.remove(k);
					}
				}
			}
			k1 = tempk2;
		}
		return ans;
	}

	@Override
	public graph copy() {
		graph ans = new DGraph();
		for (node_data n : this.algo.getV()) {
			node_data temp = new Node((Node) n);
			ans.addNode(temp);
		}
		for (node_data n : ans.getV()) {
			Collection<edge_data> coll = this.algo.getE(n.getKey());
			if(coll!=null) {
				for (edge_data e : this.algo.getE(n.getKey())) {
					edge_data temp = new Edge((Edge) e);
					ans.connect(temp.getSrc(), temp.getDest(), temp.getWeight());
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		Graph_Algo G = new Graph_Algo();
		Point3D p00 = new Point3D(1, 6, 0);
		Point3D p11 = new Point3D(0, 2, 3);
		Point3D p22 = new Point3D(1, 4, 0);
		Point3D p33 = new Point3D(5, 2, 0);
		Point3D p44 = new Point3D(6,5, 0);
		Point3D p55 = new Point3D(4,6, 0);
		Point3D p66 = new Point3D(3,5, 0);
		Point3D p77 = new Point3D(4,10,0);
		Point3D p88 = new Point3D(4.10,0);
		Point3D p99 = new Point3D(1,30);
		Point3D p10 = new Point3D(10,40);
		node_data node1 = new Node(p00);
		node_data node2 = new Node(p11);
		node_data node3 = new Node(p22);
		node_data node4 = new Node(p33);
		node_data node5 = new Node(p44);
		node_data node6 = new Node(p55);
		node_data node7 = new Node(p66);
		node_data node8 = new Node(p77);
		node_data node9 =new Node(p88);
		node_data node10 = new Node(p99);
		node_data node11 = new Node(p10);

		DGraph Dg = new DGraph();
		Dg.addNode(node1);
		Dg.addNode(node2);
		Dg.addNode(node3);
		Dg.addNode(node4);
		Dg.addNode(node5);
		Dg.addNode(node6);
		Dg.addNode(node7);
		Dg.addNode(node8);
		Dg.addNode(node9);
		Dg.addNode(node10);
		Dg.addNode(node11);


		Dg.connect(node1.getKey(), node2.getKey(), 5);
		Dg.connect(node1.getKey(), node3.getKey(), 3);
		Dg.connect(node1.getKey(), node4.getKey(), 2);
		Dg.connect(node2.getKey(), node5.getKey(), 2);
		Dg.connect(node3.getKey(), node6.getKey(), 4);
		Dg.connect(node3.getKey(),node1.getKey(),2);
		Dg.connect(node4.getKey(), node6.getKey(), 4);
		Dg.connect(node4.getKey(), node7.getKey(), 2);
		Dg.connect(node5.getKey(), node8.getKey(), 6);
		Dg.connect(node5.getKey(), node7.getKey(), 1);
		Dg.connect(node5.getKey(),node2.getKey(),4);
		Dg.connect(node6.getKey(),node11.getKey(),3);
		Dg.connect(node7.getKey(),node8.getKey(),4);
		Dg.connect(node7.getKey(),node6.getKey(),1);
		Dg.connect(node7.getKey(),node11.getKey(),9);
		Dg.connect(node8.getKey(),node7.getKey(),1);
		Dg.connect(node8.getKey(),node9.getKey(),9);
		Dg.connect(node9.getKey(),node8.getKey(),3);
		Dg.connect(node9.getKey(),node10.getKey(),5);
		Dg.connect(node10.getKey(),node9.getKey(),2);
		Dg.connect(node10.getKey(),node11.getKey(),1);
		Dg.connect(node11.getKey(),node10.getKey(),2);

		G.init(Dg);
		System.out.println("Distance betwenn 1-6 is :" + G.shortestPathDist(node1.getKey(),node6.getKey()));
		System.out.println("Distance between 6-7 is : " + G.shortestPathDist(node6.getKey(),node7.getKey()));
		System.out.println("Distance between 4-1 is : " + G.shortestPathDist(node4.getKey(),node1.getKey()));
		System.out.println("Distance between 7-9 is : " + G.shortestPathDist(node7.getKey(),node9.getKey()));
		System.out.println("Distance between 3-2 is : " + G.shortestPathDist(node3.getKey(),node2.getKey()));

		System.out.println("The graph is Connected :" + G.isConnected());
		System.out.println("The shortest path between 5-10 is :" + G.shortestPath(node5.getKey(),node10.getKey()));
		System.out.println("The shortest path between 10-1 is :" + G.shortestPath(node10.getKey(),node1.getKey()));
		System.out.println("The shortest path between 7-2 is :" + G.shortestPath(node7.getKey(),node2.getKey()));
		System.out.println("The shortest path between 1-9 is :" + G.shortestPath(node1.getKey(),node9.getKey()));

		List<Integer> ans = new LinkedList<>();
		ans.add(1);
		ans.add(7);
		ans.add(3);
		ans.add(10);
		List<Integer> ans2 = new LinkedList<>();
		ans2.add(1);
		ans2.add(10);
		ans2.add(4);
		ans2.add(5);
		G.TSP(ans);
		System.out.println("TSP[1,7,3,10] is: " +  G.TSP(ans));
		System.out.println("TSP[10,1,4,5] is : " + G.TSP(ans2));
	}
}

