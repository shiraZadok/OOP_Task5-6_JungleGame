package dataStructure;

import utils.Point3D;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DGraph implements graph, Serializable {

	private HashMap<Integer, node_data> nodes = new HashMap<>();                   //integer for key, node for value
	private HashMap<Integer, HashMap<Integer,edge_data>> edges = new HashMap<>();  //integer for src, hashmap for dest
	private int nodesCount = 0;
	private int edgesCount = 0;
	private int modeCount = 0;

	/**
	 * @param key - the node_id
	 * @return a specific node identify by the key from the graph
	 */
	@Override
	public node_data getNode(int key) {
		try {
			return this.nodes.get(key);
		}
		catch (NullPointerException e){
			return null;
		}
	}

	/**
	 * @param src of the edge
	 * @param dest of the edge
	 * @return This method get Src and Dest that represented by the key of the desired nodes and bring the edge
	 */
	@Override
	public edge_data getEdge(int src, int dest) {
		try{
			return this.edges.get(src).get(dest);
		}
		catch (NullPointerException e){
			return null;
		}
	}

	/**
	 * This method get node from the user and add it to the grph.
	 * @param n - the new node
	 */
	@Override
	public void addNode(node_data n) {
		this.nodes.put(n.getKey(),n);
		nodesCount++;
		modeCount++;
	}

	/**
	 * This method get Src and Dest that represented by the key of the
	 * desired nodes and Weight of the edge and make a new edge in the graph.
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
	 */
	@Override
	public void connect(int src, int dest, double w) {
		edge_data temp = new Edge(src, dest, w);
		if (this.getNode(src) != null && this.getNode(dest) != null) {
			if (this.getEdge(src,dest)==null) {
				if (this.edges.get(src)== null) {
					HashMap<Integer,edge_data> add = new HashMap<>();
					this.edges.put(src,add);
					this.edges.get(src).put(dest,temp);
				}
				else{
					this.edges.get(src).put(dest,temp);
				}
			}
			else{
				this.removeEdge(src,dest);
				this.connect(src, dest, w);
			}
			edgesCount++;
			modeCount++;
		}
		else{
			System.err.println("ERR: The src and dest must be exist");
		}
	}

	/**
	 * @return the nodes collection of the graph
	 */
	@Override
	public Collection<node_data> getV() {
		return this.nodes.values();
	}

	/**
	 *
	 * @param node_id - the key of node
	 * @return edges collection tha connect to this node
	 */
	@Override
	public Collection<edge_data> getE(int node_id) {
		try {
			return this.edges.get(node_id).values();
		}
		catch (NullPointerException e){
			return null;
		}
	}

	/**
	 *
	 * @param key - of node for delete
	 * @return the deleted node
	 */
	@Override
	public node_data removeNode(int key) {
		node_data nd = this.nodes.get(key);
		if(nd!=null) {
			this.nodes.remove(key);
			if (this.edges.get(key) != null) {
				this.edges.remove(key);
			}
			Iterator it = this.edges.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry mp = (Map.Entry) it.next();           //give the key
				int temp = ((int) mp.getKey());                 //save the key
				if (this.edges.get(temp).get(key) != null) {
					removeEdge(temp, key);
				}
			}
			this.nodesCount--;
			this.modeCount++;
		}
		return nd;
	}

	/**
	 *
	 * @param src of the edge
	 * @param dest of the edge
	 * @return the deleted edge
	 */
	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data ed = this.getEdge(src,dest);
		if(ed!=null) {
			this.edges.get(src).remove(dest);
			this.edgesCount--;
			this.modeCount++;
		}
		return ed;
	}

	/**
	 * @return the nodesCount.
	 */
	@Override
	public int nodeSize() {
		return this.nodesCount;
	}

	/**
	 * @return the edgesCount.
	 */
	@Override
	public int edgeSize() {
		return this.edgesCount;
	}

	/**
	 * @return the modeCount
	 */
	@Override
	public int getMC() {
		return this.modeCount;
	}

	public static void main(String[] args) {
//		Point3D x = new Point3D(1,4,0);
//		Point3D y = new Point3D(2,5,0);
//		Point3D q = new Point3D(4,3,0);
//		node_data a = new Node( x);
//		node_data b =new Node(y);
//		node_data c = new Node(q);
//		DGraph d =new DGraph();
//		d.addNode(a);
//		d.addNode(b);
//		d.addNode(c);
//		d.connect(a.getKey(),b.getKey(),4);
//		d.connect(a.getKey(),c.getKey(),50);
//		System.out.println(d.getEdge(1,5).getWeight());
//		System.out.println(d.edges);
//		d.removeEdge(5,1);
//		System.out.println(d.nodes);
//		System.out.println(d.edges);
		////////////////////////TestForTheD-graph//////////////////////////

		DGraph g = new DGraph();
		Point3D p [] = new Point3D[8];
		Node n [] = new Node[8];

		p[0] = new Point3D(0,0,0);
		p[1] = new Point3D(1,4,8);
		p[2] = new Point3D(5,4,8);
		p[3] = new Point3D(5,0,0);
		p[4] = new Point3D(0,4,0);
		p[5] = new Point3D(0,0,3);
		p[6] = new Point3D(3,7,5);
		p[7] = new Point3D(9,1,4);

		n[0] = new Node(p[0]);
		n[1] = new Node(p[1]);
		n[2] = new Node(p[2]);
		n[3] = new Node(p[3]);
		n[4] = new Node(p[4]);
		n[5] = new Node(p[5]);
		n[6] = new Node(p[6]);
		n[7] = new Node(p[7]);


		for(int i=0; i<n.length; i++){
			g.addNode(n[i]);
		}
		System.out.println("11111111");
		//connection between all the nodes with the node (0,0,0)
		g.connect(1,2,3);
		System.out.println("1");
		g.connect(1,3,3);
		System.out.println("2");
		g.connect(1,4,3);
		System.out.println("3");
		g.connect(1,5,3);
		System.out.println("4");
		g.connect(1,6,3);
		System.out.println("5");
		g.connect(1,7,3);
		System.out.println("6");
		g.connect(1,8,3);
		System.out.println("7");

		//connection between some node to the other
		g.connect(2,8,5);
		System.out.println("8");
		g.connect(3,6,10);
		System.out.println("9");
		g.connect(4,3,1);
		System.out.println("10");
		g.connect(6,7,2);
		System.out.println("11");
		g.connect(5,7,4);
		System.out.println("12");
		g.connect(3,8,8);
		System.out.println("13");
		g.connect(7,8,5);
		System.out.println("14");
		g.connect(8,1,11);
		System.out.println("15");

		for(int i=0; i<n.length ; i++){
			g.getNode(i+1).getLocation().toString();
		}

	}

}
