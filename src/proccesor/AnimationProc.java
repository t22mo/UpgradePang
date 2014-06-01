package proccesor;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.badlogic.gdx.math.Vector2;

public class AnimationProc {
	ArrayList< ArrayList<Vector2>> queue;
	
	public void Add(ArrayList<Vector2> targetList)
	{
		queue.add(targetList);
	}
	public void Remove()
	{
		queue.remove(0);
	}
}
