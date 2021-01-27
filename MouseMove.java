import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.logging.Logger;

class MouseMove
{
	private Robot robot;
	private final static Logger LOGGER = Logger.getLogger(MouseMove.class.getName());
	private int numOfIteration;

	public MouseMove()
	{
		this.numOfIteration = 60;
		changeCursorLocation();
	}

	public MouseMove(int numOfIteration)
	{
		this.numOfIteration = numOfIteration;
		changeCursorLocation();
	}

	public void changeCursorLocation()
	{
		try
		{
			LOGGER.info("===== start =====");
			
			robot = new Robot();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int)screenSize.getWidth();
			int height = (int)screenSize.getHeight();

			int oldX = MouseInfo.getPointerInfo().getLocation().x;
			int oldY = MouseInfo.getPointerInfo().getLocation().y; 

			for(int k = 1; k <= numOfIteration; k++)
			{
				Thread.sleep(60000);
        
				int x = MouseInfo.getPointerInfo().getLocation().x;
				int y = MouseInfo.getPointerInfo().getLocation().y;

				if (x+1 >= width) x = (int)width/2;
				if (y+1 >= height) y = (int)height/2;

				if (oldX == x && oldY == y)
					robot.mouseMove(++x, ++y);

				oldX = x;
				oldY = y;

				LOGGER.info(k + "\t " + x + "\t " + width + "\t " + y + "\t " + height);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			LOGGER.info("===== end =====");
		}
	}

	public static void main(String[] args){
		if (args.length == 0)
			new MouseMove();
		else if (args.length == 1) 
			new MouseMove(Integer.parseInt(args[0]));
	}
}
