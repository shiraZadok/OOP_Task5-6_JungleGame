package dataStructure;

import utils.Point3D;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class DGraph implements graph, Serializable {

	private HashMap<Integer, node_data> nodes = new HashMap<>();                   //integer for key, node for value
	private HashMap<Integer, HashMap<Integer,edge_data>> edges = new HashMap<>();  //integer for src, hashmap for dest
	private int nodesCount = 0;
	private int edgesCount = 0;
	private int modeCount = 0;

	@Override
	public node_data getNode(int key) {
		return this.nodes.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		try{
			return this.edges.get(src).get(dest);
		}
		catch (NullPointerException e){
			return null;
		}
	}

	@Override
	public void addNode(node_data n) {
		this.nodes.put(n.getKey(),n);
		nodesCount++;
		modeCount++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		edge_data temp = new Edge(src, dest, 0, w, "");
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
			edgesCount++;
			modeCount++;
		}
		else{
			System.err.println("The src and dest must be exist");
		}
	}


	@Override
	public Collection<node_data> getV() {
		return this.nodes.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		try {
			return this.edges.get(node_id).values();
		}
		catch (NullPointerException e){
			return null;
		}
		}

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

	@Override
	public int nodeSize() {
		return this.nodesCount;
	}

	@Override
	public int edgeSize() {
		return this.edgesCount;
	}

	@Override
	public int getMC() {
		return this.modeCount;
	}

	public static void main(String[] args) {
		Point3D x = new Point3D(1,4,0);
		Point3D y = new Point3D(2,5,0);
		Point3D q = new Point3D(4,3,0);
		node_data a = new Node(1,2,3, "asf", x);
		node_data b =new Node(3,4,6,"gik",y);
		node_data c = new Node(5,50,50,"sf",q);
		DGraph d =new DGraph();
		d.addNode(a);
		d.addNode(b);
		d.addNode(c);
		d.connect(a.getKey(),b.getKey(),4);
		d.connect(a.getKey(),c.getKey(),50);
		System.out.println(d.getEdge(1,5).getWeight());
		System.out.println(d.edges);
		d.removeEdge(5,1);
		System.out.println(d.nodes);
		System.out.println(d.edges);

	}

}
