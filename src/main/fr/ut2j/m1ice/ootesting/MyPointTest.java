package main.fr.ut2j.m1ice.ootesting;

import static org.junit.Assert.*;

import java.util.Random;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyPointTest {
	
	private MyPoint mypoint1;
	private MyPoint mypoint2;
	private MyPoint mypoint3;
	private MyPoint mypoint4;
	private MyPoint mypoint5;
	private Random r1;
	private Random r2;
	private ITranslation translation;
	private MyPoint point1M;
	private MyPoint point2M;

	@Before
	public void setUp() throws Exception {
		this.mypoint1 = new MyPoint();
		this.mypoint2 = new MyPoint(5.4d, 6.2d);
		this.mypoint3 = new MyPoint(mypoint1);
		this.mypoint4 = new MyPoint(mypoint2);
		this.mypoint5 = new MyPoint(null);
		this.point1M = new MyPoint();
		this.point2M = new MyPoint(2d, 4d); 
		
        r1 = EasyMock.createMock(Random.class);
        EasyMock.expect(r1.nextInt()).andReturn(3);
        EasyMock.replay(r1);
        r2 = EasyMock.createMock(Random.class);
        EasyMock.expect(r2.nextInt()).andReturn(5);
        EasyMock.replay(r2);
        
        translation = EasyMock.createMock(ITranslation.class);
        EasyMock.expect(translation.getTx()).andReturn(23);
        EasyMock.expect(translation.getTy()).andReturn(27);
        EasyMock.replay(translation);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testConstructeur1X() {
		assertEquals(0d, this.mypoint1.getX(), 0.0001);
	}
	
	@Test
	public void testConstructeur1Y() {
		assertEquals(0d, this.mypoint1.getY(), 0.0001);
	}
	
	@Test
	public void testConstructeur2X() {
		assertEquals(5.4d, this.mypoint2.getX(), 0.0001);
	}
	
	@Test
	public void testConstructeur2Y() {
		assertEquals(6.2d, this.mypoint2.getY(), 0.0001);
	}

	// test des accesseurs
	
	@Test
	public void testGetSetX() {
		this.mypoint1.setX(5d);
		assertEquals(5d, this.mypoint1.getX(), 0.0001);
	}
	
	@Test
	public void testGetSetY() {
		this.mypoint1.setY(4d);
		assertEquals(4d, this.mypoint1.getY(), 0.0001);
	}
	
	@Test
	public void testGetSetNaNX() {
		this.mypoint1.setX(Double.NaN);
		assertEquals(0d, this.mypoint1.getX(), 0.0001);
	}
	
	@Test
	public void testGetSetNaNY() {
		this.mypoint1.setY(Double.NaN);
		assertEquals(0d, this.mypoint1.getY(), 0.0001);
	}
	
	@Test
	public void testConstructeur3ptConstructeur1X() {
		assertEquals(0d, this.mypoint3.getX(), 0.0001);
	}
	
	@Test
	public void testConstructeur3ptConstructeur1Y() {
		assertEquals(0d, this.mypoint3.getY(), 0.0001);
	}
	
	@Test
	public void testConstructeur3ptConstructeur2X() {
		assertEquals(5.4d, this.mypoint4.getX(), 0.0001);
	}
	
	@Test
	public void testConstructeur3ptConstructeur2Y() {
		assertEquals(6.2d, this.mypoint4.getY(), 0.0001);
	}
	
	@Test
	public void testConstructeur3ptNullX() {
		assertEquals(0d, this.mypoint5.getX(), 0.0001);
	}
	
	@Test
	public void testConstructeur3ptNullY() {
		assertEquals(0d, this.mypoint5.getY(), 0.0001);
	}
	
	@Test
	public void testScaleX() {
		assertEquals(10.8d, this.mypoint2.scale(2d).getX(), 0.0001);
	}
	
	@Test
	public void testScaleY() {
		assertEquals(12.4d, this.mypoint2.scale(2d).getY(), 0.0001);
	}
	
	@Test
	public void testHorizontalSymmetryX() {
		assertEquals(0d, this.mypoint1.horizontalSymmetry(mypoint2).getX(), 0.0001);
	}
	
	@Test
	public void testHorizontalSymmetryY() {
		assertEquals(12.4d, this.mypoint1.horizontalSymmetry(mypoint2).getY(), 0.0001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHorizontalSymmetryException() {
		this.mypoint1.horizontalSymmetry(null);
	}
	
	@Test
	public void testMiddlePointX() {
		assertEquals(2.7d, this.mypoint1.getMiddlePoint(mypoint2).getX(), 0.0001);
	}
	
	@Test
	public void testMiddlePointY() {
		assertEquals(3.1d, this.mypoint1.getMiddlePoint(mypoint2).getY(), 0.0001);
	}
	
	@Test
	public void testTranslateX() {
		this.mypoint1.translate(2.5d,0d);
		assertEquals(2.5d, this.mypoint1.getX(), 0.0001);
	}
	
	@Test
	public void testTranslateY() {
		this.mypoint1.translate(0d,3.4d);
		assertEquals(3.4d, this.mypoint1.getY(), 0.0001);
	}
	
	@Test
	public void testTranslateXNaN() {
		this.mypoint1.translate(Double.NaN,0d);
		assertEquals(0d, this.mypoint1.getX(), 0.0001);
	}
	
	@Test
	public void testTranslateYNaN() {
		this.mypoint1.translate(0d, Double.NaN);
		assertEquals(0d, this.mypoint1.getY(), 0.0001);
	}
	
	
	@Test
	public void testComputeAngle1() {
		MyPoint point = new MyPoint(4d, 2d);
		double computeangle = this.mypoint2.computeAngle(point);
		double angle = Math.atan2(point.getY() - this.mypoint2.getY(), point.getX() - this.mypoint2.getX());
		while(angle < 0) {
			angle += Math.PI;
		}
		assertEquals((angle%Math.PI), (computeangle%Math.PI), 0.0001);
	}
	
	@Test
	public void testComputeAngle2() {
		MyPoint point = new MyPoint(8d, 2d);
		double computeangle = this.mypoint2.computeAngle(point);
		double angle = Math.atan2(point.getY() - this.mypoint2.getY(), point.getX() - this.mypoint2.getX());
		while(angle < 0) {
			angle += Math.PI;
		}
		while(computeangle < 0)
		{
			computeangle += Math.PI;
		}
		assertEquals((angle%Math.PI), (computeangle%Math.PI), 0.0001);
	}
	
	@Test
	public void testComputeAngle90() {
		MyPoint point = new MyPoint(5.4d, 8d);
		double computeangle = this.mypoint2.computeAngle(point);
		double angle = Math.atan2(point.getY() - this.mypoint2.getY(), point.getX() - this.mypoint2.getX());
		
		assertEquals((angle%Math.PI), (computeangle%Math.PI), 0.0001); 
	}
	
	@Test
	public void testComputeAngle90v2() {
		MyPoint point = new MyPoint(5.4d, 2d);
		double computeangle = this.mypoint2.computeAngle(point);
		double angle = Math.atan2(point.getY() - this.mypoint2.getY(), point.getX() - this.mypoint2.getX());
		while(angle < 0) {
			angle += Math.PI;
		}
		assertEquals((angle%Math.PI), (computeangle%Math.PI), 0.0001); 
	}
	
	@Test
	public void testRotateGravityNull() {
		assertEquals(null, this.mypoint1.rotatePoint(null, 2d));
	}
	
	@Test
	public void testRotateAnglePiGetX() {
		MyPoint point = new MyPoint(5d,2d);
		assertEquals(-5d, point.rotatePoint(this.mypoint1, Math.PI).getX(), 0.0001);
	}
	
	@Test
	public void testRotateAnglePiGetY() {
		MyPoint point = new MyPoint(5d,2d);
		assertEquals(-2d, point.rotatePoint(this.mypoint1, Math.PI).getY(), 0.0001);
	}
	
	@Test
	public void testRotateAnglePi2GetX() {
		MyPoint point = new MyPoint(5d,2d);
		assertEquals(-2d, point.rotatePoint(this.mypoint1, Math.PI/2).getX(), 0.0001);
	}
	
	@Test
	public void testRotateAnglePi2GetY() {
		MyPoint point = new MyPoint(5d,2d);
		assertEquals(5d, point.rotatePoint(this.mypoint1, Math.PI/2).getY(), 0.0001);
	}
	
	@Test
	public void testRotateAnglePi3GetX() {
		MyPoint point = new MyPoint(5d,2d);
		assertEquals(2d, point.rotatePoint(this.mypoint1, (Math.PI*3)/2).getX(), 0.0001);
	}
	
	@Test
	public void testRotateAnglePi3GetY() {
		MyPoint point = new MyPoint(5d,2d);
		assertEquals(-5d, point.rotatePoint(this.mypoint1, (Math.PI*3)/2).getY(), 0.0001);
	}
	
	@Test
	public void testRotateAnglePi4GetX() {
		MyPoint point = new MyPoint(5d,2d);
		assertEquals(2.12132034d, point.rotatePoint(this.mypoint1, Math.PI/4).getX(), 0.0001);
	}
	
	@Test
	public void testRotateAnglePi4GetY() {
		MyPoint point = new MyPoint(5d,2d);
		assertEquals(4.949747468d, point.rotatePoint(this.mypoint1, Math.PI/4).getY(), 0.0001);
	}
	
	@Test
	public void testRotateAngleInf0X() {
		MyPoint point = new MyPoint(5d,2d);
		assertEquals(-5d, point.rotatePoint(this.mypoint1, -Math.PI).getX(), 0.0001);
	}
	
	@Test
	public void testRotateAngleInf0Y() {
		MyPoint point = new MyPoint(5d,2d);
		assertEquals(-2d, point.rotatePoint(this.mypoint1, -Math.PI).getY(), 0.0001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCentralSymmetryException() {
		this.mypoint1.centralSymmetry(null);
	}
	
	@Test
	public void testRotateAngle0getX() {
		assertEquals(5.4d, this.mypoint2.rotatePoint(this.mypoint1, 0).getX(), 0.0001);
	}
	
	@Test
	public void testRotateAngle0getY() {
		assertEquals(6.2d, this.mypoint2.rotatePoint(this.mypoint1, 0).getY(), 0.0001);
	}
	
	@Test
	public void testCentralSymmetryX() {
		assertEquals(10.8d, this.mypoint1.centralSymmetry(this.mypoint2).getX(), 0.0001);
	}
	
	@Test
	public void testCentralSymmetryY() {
		assertEquals(12.4d, this.mypoint1.centralSymmetry(this.mypoint2).getY(), 0.0001);
	}
	
	@Test
    public void testSetPointX() {
        point1M.setPoint(r1, r2);
        assertEquals(3, point1M.getX(), 0.0001);
    }
    
    @Test
    public void testSetPointY() {
        point1M.setPoint(r1, r2);
        assertEquals(5, point1M.getY(), 0.0001);
    }
    
    @Test
    public void testTranslationX() {
        point2M.translate(translation);
        assertEquals(25, point2M.getX(), 0.0001);
    }
    
    @Test
    public void testTranslationY() {
        point2M.translate(translation);
        assertEquals(31, point2M.getY(), 0.0001);
    }
    
    @Test
    public void testTranslationNullX() {
        point2M.translate(null);
        assertEquals(2, point2M.getX(), 0.0001);
    }
    
    @Test
    public void testTranslationNullY() {
        point2M.translate(null);
        assertEquals(4, point2M.getY(), 0.0001);
    }

}
