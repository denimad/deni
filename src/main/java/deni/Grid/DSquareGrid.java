/*
 * deni 2017
 */
package main.java.deni.Grid;

import main.java.deni.Util.DMathHelper;

/**
 * This class generates positions for a square grid.
 * The positions are generated based on a filling direction setup and
 * a initial corner position.
 * (TODO: set random position generator options).
 */
public class DSquareGrid 
{
	public DSquareGrid(int width, int height, int gridSizeX, int gridSizeY)
	{
		this(width, 
			 height, 
			 gridSizeX,
			 gridSizeY,
			 DSquareGrid.DEFAULT_FILLING);
	}
	
	public DSquareGrid(int width, int height, int gridSizeX, int gridSizeY, DSquareGridFillingMode direction)
	{
		this(width, 
			height, 
			gridSizeX,
			gridSizeY,
			direction, 
			DSquareGrid.DEFAULT_INITIALCORNER);
	}
	
	public DSquareGrid(int width, int height, int _gridSizeX, int _gridSizeY, DSquareGridFillingMode direction, DCorner _initialCorner)
	{
		this.calculateSizes(width, height, _gridSizeX, _gridSizeY);
		
		fillingDirection = direction;
		initialCorner = _initialCorner;
		
		this.initInitialPosition();
		this.initIncrementals();
	}
	
	/**
	 * Set border size and do all derived calculations.
	 * @param borderWidth 
	 */
	public void setBorder(int borderWidth)
	{
		this.calculateSizes
			(
				this.matrixWidth- (borderWidth * 3),
				this.matrixHeight- (borderWidth * 3),
				this.gridSizeX,
				this.gridSizeY
			);
		this.initialPosition[0] += borderWidth;
		this.initialPosition[1] += borderWidth;
	}
	
	private void calculateSizes(int width, int height, int _gridSizeX, int _gridSizeY)
	{
		matrixWidth = width;
		matrixHeight = height;
		
		gridSizeX = _gridSizeX;
		gridSizeY = _gridSizeY;
		
		if (gridSizeX > matrixWidth || 
				gridSizeY > matrixHeight )
		{
			throw new IllegalArgumentException(
				"grid size must be smaller than matrix size.");
		}
		
		// calculate the total number of cells on each axis
		totalNumberXCells = DMathHelper.floor(matrixWidth / gridSizeX);
		totalNumberYCells = DMathHelper.floor(matrixHeight / gridSizeY);
	}
	
	private void initInitialPosition()
	{
		
		switch(initialCorner)
		{
			case TOP_LEFT_CORNER: 
				initialPosition = new int[] {0,0};
				break;
			case TOP_RIGHT_CORNER: 
				initialPosition = new int[] {matrixWidth,0};
				break;
			case BOTON_LEFT_CORNER: 
				initialPosition = new int[] {0,matrixHeight};
				break;
			case BOTON_RIGHT_CORNER: 
				initialPosition = new int[] {matrixWidth,matrixHeight};
				break;
		}
		
		
	}
	
	private void initIncrementals()
	{
		this.incrementalX = 0;
		this.incrementalY = -1;
	}
	
	public int[] getNextPosition()
	{
		
		switch (this.fillingDirection)
		{
			case HORIZONTAL_FILLING:
				if (incrementalX <= -totalNumberXCells || 
					incrementalX >= totalNumberXCells)
				{
					incrementalX = 0;
					incrementalY += this.getYIncrementalValue();
				}
				else
				{
					incrementalX += this.getXIncrementalValue();
				}
				
				break;
			case VERTICAL_FILLING:
				if (incrementalY <= -totalNumberYCells ||
					incrementalY >= totalNumberYCells)
				{
					incrementalY = 0;
					incrementalX += this.getXIncrementalValue();
				}
				else
				{
					incrementalY += this.getYIncrementalValue();
				} 
				break;
		}
		
		return new int[]
			{this.initialPosition[0] + (this.incrementalX * this.gridSizeX),
			 this.initialPosition[1] + (this.incrementalY * this.gridSizeY)};
	}
	
	private int getXIncrementalValue()
	{
		int incrementalDirection = 0;
		switch(initialCorner)
		{
			case TOP_LEFT_CORNER: 
			case BOTON_LEFT_CORNER:
				incrementalDirection = 1;
				break;
			case TOP_RIGHT_CORNER:
			case BOTON_RIGHT_CORNER:
				incrementalDirection = -1;
				break;
		}
		return incrementalDirection;
	}
	
	

	private int getYIncrementalValue()
	{
		int incrementalDirection = 0;
		switch(initialCorner)
		{
			case TOP_LEFT_CORNER: 
			case TOP_RIGHT_CORNER:
				incrementalDirection = 1;
				break;
			case BOTON_LEFT_CORNER:
			case BOTON_RIGHT_CORNER:
				incrementalDirection = -1;
				break;
		}
		return incrementalDirection;
	}
	
	
	
	private int[] initialPosition;
	
	
	private int matrixWidth;
	private int matrixHeight;
	
	private int gridSizeX;
	private int gridSizeY;
	
	private int totalNumberXCells;
	private int totalNumberYCells;
	
	private int incrementalX;
	private int incrementalY;
	
	/**
	 * This fields tells to stop any calculation
	 */
	boolean stop;
	
	private DSquareGridFillingMode fillingDirection;
	private DCorner initialCorner;
	
	
	public enum DSquareGridFillingMode
	{
		HORIZONTAL_FILLING,
		VERTICAL_FILLING
	}

	public static final DSquareGridFillingMode DEFAULT_FILLING = 
		DSquareGridFillingMode.HORIZONTAL_FILLING;
	
	
	public enum DCorner
	{
		TOP_LEFT_CORNER,
		TOP_RIGHT_CORNER,
		BOTON_LEFT_CORNER,
		BOTON_RIGHT_CORNER
	}
	
	public static final DCorner DEFAULT_INITIALCORNER = 
		DCorner.TOP_LEFT_CORNER;
}
