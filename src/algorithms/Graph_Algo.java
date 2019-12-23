package algorithms;

import java.util.Iterator;
import java.util.List;

import dataStructure.*;
import utils.Point3D;

import javax.swing.text.html.HTMLDocument;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms{

	private graph algo = null;

	@Override
	public void init(graph g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnected() {
		this.changeTagNode();
		Iterator it = this.algo.getV().iterator();
		dfs((node_data)it.next());
		it = this.algo.getV().iterator();
		while (it.hasNext()) {
			node_data temp = (node_data)it.next();
			if(temp.getTag()==0) return false;
		}
		this.changeTagEdge();
		this.changeTagNode();
		oppositeDest(this.algo);
		it = this.algo.getV().iterator();
		dfs((node_data)it.next());
		it = this.algo.getV().iterator();
		while (it.hasNext()) {
			node_data temp = (node_data)it.next();
			if(temp.getTag()==0) return false;
		}
		oppositeDest(this.algo);
		return true;
	}

	public void dfs(node_data n){
		if(this.algo.getE(n.getKey())!=null) {
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

	public void oppositeDest(graph d){
		Iterator it = d.getV().iterator();
		while (it.hasNext()){
			node_data n =(node_data)it.next();
			Iterator it1 = d.getE(n.getKey()).iterator();
			while (it1.hasNext()) {
				edge_data e = (edge_data) it1.next();
				if (e.getTag() == 0) {
					d.connect(e.getDest(), e.getSrc(), e.getWeight());
					d.removeEdge(e.getSrc(), e.getDest());
					d.getEdge(e.getDest(), e.getSrc()).setTag(1);
					it1 = d.getE(n.getKey()).iterator();
				}
			}
		}
	}
	public void changeTagNode(){
		Iterator it = this.algo.getV().iterator();
		while (it.hasNext()) {
			node_data n = (node_data)it.next();
			n.setTag(0);
		}
	}

	public void changeTagEdge() {
		Iterator it = this.algo.getV().iterator();
		while (it.hasNext()) {
			node_data n = (node_data)it.next();
			if(this.algo.getE(n.getKey())!=null) {
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



		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		Graph_Algo g = new Graph_Algo();
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
		d.connect(b.getKey(),c.getKey(),50);
		d.connect(b.getKey(),a.getKey(),4);
		d.connect(c.getKey(),b.getKey(),4);
		g.algo=d;
		boolean f =g.isConnected();
		System.out.println(f);
	}
}
