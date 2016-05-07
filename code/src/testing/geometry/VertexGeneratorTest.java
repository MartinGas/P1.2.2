package testing.geometry;

import static org.junit.Assert.*;
import geometry.Box;
import geometry.Rectangle;
import geometry.VertexGenerator;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.badlogic.gdx.math.Vector3;

public class VertexGeneratorTest 
{
	/**
	 * performs verification for rectangles
	 * @param offset offset vector
	 * @param dirs 2 direction vectors
	 * @return true if the generated vertices match the hard coded ones
	 */
	public static ArrayList<Vector3> expected2d (Vector3 offset, Vector3[] dirs)
	{
		ArrayList<Vector3> expectedVertices = new ArrayList<>();
		expectedVertices.add (offset);
		expectedVertices.add (offset.cpy().add (dirs[0]));
		expectedVertices.add (offset.cpy().add (dirs[1]));
		expectedVertices.add (offset.cpy().add (dirs[0]).add(dirs[1]));
		
		return expectedVertices;
	}
	
	/**
	 * performs verification for octahedra
	 * @param offset offset vector
	 * @param directions 3 direction vectors
	 * @return
	 */
	public static ArrayList<Vector3> expected3d (Vector3 offset, Vector3 ... directions)
	{
		ArrayList<Vector3> expectedVertices = new ArrayList<>();
		expectedVertices.add (offset);
		expectedVertices.add (offset.cpy().add (directions[0]));
		expectedVertices.add (offset.cpy().add (directions[1]));
		expectedVertices.add (offset.cpy().add (directions[2]));
		expectedVertices.add (offset.cpy().add (directions[0]).add (directions[1]));
		expectedVertices.add (offset.cpy().add (directions[0]).add (directions[2]));
		expectedVertices.add (offset.cpy().add (directions[1]).add (directions[2]));
		expectedVertices.add (offset.cpy().add (directions[0]).add (directions[1]).add (directions[2]));
		
		return expectedVertices;
	}
	
	/**
	 * @param expected list of expected vertices
	 * @param offset offset vector
	 * @param directions direction vectors
	 * @return true if the vertices generated by offset + direction contain expected
	 */
	public static boolean test (ArrayList<Vector3> expected, Vector3 offset, Vector3 ... directions)
	{
		VertexGenerator gen = new VertexGenerator (offset, directions);
		ArrayList<Vector3> generated = new ArrayList<> (Arrays.asList (gen.generate()));
		return generated.containsAll (expected);
	}
	
	@Test
	/**
	 * tests vertices for rectangle, standard basis
	 */
	public void test2dStandard()
	{
		Vector3 offset = new Vector3 (5, 1, 7);
		
		Vector3[] dirStandard = new Vector3[Rectangle.INDEPENDENT_VECTORS];
		dirStandard[0] = new Vector3 (2, 0, 0);
		dirStandard[1] = new Vector3 (0, 0, 5);
		
		ArrayList<Vector3> expected = expected2d (offset, dirStandard);
		assertTrue (test (expected, offset, dirStandard));
	}
	
	@Test
	/**
	 * tests vertices for rectangle, non standard basis
	 */
	public void test2dRotated()
	{
		Vector3 offset = new Vector3 (5, 1, 7);
		
		Vector3[] dirRotated = new Vector3[Rectangle.INDEPENDENT_VECTORS];
		dirRotated[0] = new Vector3 (-3, 5, 2);
		dirRotated[1] = new Vector3 (6, 2, 4);
		
		ArrayList<Vector3> expected = expected2d (offset, dirRotated);
		assertTrue (test (expected, offset, dirRotated));
	}
	
	@Test
	/**
	 * tests vertices for box, standard basis
	 */
	public void test3dStandard()
	{
		Vector3 offset = new Vector3 (1, 9, 4);
		Vector3 xDir = new Vector3 (4, 0, 0);
		Vector3 yDir = new Vector3 (0, 7, 0);
		Vector3 zDir = new Vector3 (0, 0, 1);
		
		ArrayList<Vector3> expected = expected3d (offset, xDir, yDir, zDir);
		assertTrue (test (expected, offset, xDir, yDir, zDir));
	}
	
	@Test
	/**
	 * tests vertices for box, non standard basis
	 */
	public void test3dRotated()
	{
		Vector3 offset = new Vector3 (7, 1, 8);
		Vector3 d1 = new Vector3 (1, 7, 0);
		Vector3 d2 = new Vector3 (0, 0, 4);
		Vector3 d3 = new Vector3 (-7, 1, 0);
		
		ArrayList<Vector3> expected = expected3d (offset, d1, d2, d3);
		assertTrue (test (expected, offset, d1, d2, d3));
	}
	
}
