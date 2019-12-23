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
					if (d.getEdge(e.getDest(), e.getSrc()) != null) {
						d.getEdge(e.getDest(), e.getSrc()).setTag(1);
						e.setTag(1);
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
		this.changeTagNode();
		Iterator it = this.algo.getV().iterator();
		while (it.hasNext()){
			node_data n = (node_data)it.next();
			n.setWeight(Integer.MAX_VALUE);
		}
		this.algo.getNode(src).setWeight(0);
		shortestPathDistRec(this.algo.getNode(src),this.algo.getNode(dest));
		return this.algo.getNode(dest).getWeight();
	}

	public void shortestPathDistRec(node_data n,node_data dest) {
		if (n.getTag() == 1 || n.getKey() == dest.getKey()) {
			return;
		}
		if (this.algo.getE(n.getKey()) != null) {
			Iterator it = this.algo.getE(n.getKey()).iterator();
			while (it.hasNext()) {
				edge_data e = (edge_data) it.next();
				node_data d = this.algo.getNode(e.getDest());
				if (n.getWeight() + e.getWeight() < d.getWeight()) {
					d.setWeight(n.getWeight() + e.getWeight());
					n.setTag(1);
					shortestPathDistRec(this.algo.getNode(e.getDest()), dest);
				}
			}
		}
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
//		Graph_Algo G = new Graph_Algo();
//		Point3D x = new Point3D(1,4,0);
//		Point3D y = new Point3D(2,5,0);
//		Point3D q = new Point3D(3,6,0);
//		Point3D z = new Point3D(4,7,0);
//		Point3D s = new Point3D(5,8,0);
//		Point3D t = new Point3D(6,9,0);
//		node_data a = new Node(1,2,3, "asf", x);
//		node_data b =new Node(3,4,6,"gik",y);
//		node_data c = new Node(5,50,50,"sf",q);
//		node_data d = new Node(6,50,50,"sf",z);
//		node_data e = new Node(7,50,50,"sf",s);
//		node_data f = new Node(8,50,50,"sf",t);
//		DGraph g =new DGraph();
//		g.addNode(a);
//		g.addNode(b);
//		g.addNode(c);
//		g.addNode(d);
//		g.addNode(e);
//		g.addNode(f);
//		g.connect(a.getKey(),b.getKey(),5);
//		g.connect(b.getKey(),f.getKey(),3);
//		g.connect(a.getKey(),c.getKey(),4);
//		g.connect(c.getKey(),d.getKey(),5);
//		g.connect(c.getKey(),e.getKey(),1);
//		g.connect(e.getKey(),f.getKey(),1);
//		G.algo=g;
//		boolean be =G.isConnected();
//		System.out.println(G.shortestPathDist(a.getKey(),c.getKey()));
//		System.out.println(G.shortestPathDist(a.getKey(),f.getKey()));
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
