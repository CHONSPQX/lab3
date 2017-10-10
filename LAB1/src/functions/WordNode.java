/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

/**
 *
 * @author Administrator
 */
class WordNode implements Comparable<WordNode>
{
	String label;//节点名称
	int weight;//距离（权重）
	WordNode(String str)//构造函数
	{
		this.label=str;
		this.weight=1;
	}
	WordNode(String str,int dis)//构造函数
	{
		this.label=str;
		this.weight=dis;
	}
	public void setDistance(int distance) //设置距离（权重）
	{
		this.weight=distance;
	}
	@Override
	public boolean equals(Object obj)//覆盖equals方法，定义节点相等仅为节点名称相等 
        {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WordNode other = (WordNode) obj;
        if( this.label.equals(other.label))
        return true;
        else
        return false;
    }	
	public void addWeight()//增加距离（权重）
	{
		this.weight++;
	}	
	public int compareTo(WordNode other) //自定义比较方法，仅比较距离（权重）
        {
		// TODO Auto-generated method stub
		return this.weight-other.weight;
	}
}
