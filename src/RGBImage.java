/**
 * RGBImage Class represents an object which build an image using a grid of colors.
 * @author Ori Ben-Ezra
 * @version 27-02-2021
 */
public class RGBImage {
    private RGBColor[][] _image;

    /**
     * Constructor which create a new object of a black image
     * @param rows size of the rows
     * @param cols size of the columns
     */
    public RGBImage(int rows, int cols) {
        _image = new RGBColor[rows][cols];

        // initialize color table
        for (int i = 0; i < _image.length; i++) {
            for (int j = 0; j < _image[i].length; j++) {
                _image[i][j] = new RGBColor();
            }
        }
    }

    /**
     * Constructor which build an RGBImage object using a table of colors
     * @param pixels the table of colors which will build the desired image.
     */
    public RGBImage(RGBColor[][] pixels) {
        int rows = pixels.length;
        int cols = pixels[0].length;

        _image = new RGBColor[rows][cols];

        // initialize color table
        for (int i = 0; i < _image.length; i++) {
            for (int j = 0; j < _image[i].length; j++) {
                _image[i][j] = new RGBColor(pixels[i][j]); // clone color object
            }
        }
    }

    /**
     * Constructor which clone an RGBImage object.
     * @param other The image to clone
     */
    public RGBImage(RGBImage other) {
        this(other._image); // 'this' refers to the other constructor.
    }

    /**
     * Method that returns the height of this image
     * @return Number of pixels of this image [image height]
     */
    public int getHeight() {
        return _image.length;
    }

    /**
     * Method that returns the width of this image
     * @return Number of pixels of this image [image width]
     */
    public int getWidth() {
        return _image[0].length;
    }

    /**
     * Method which returns a specific pixel in this image
     * @param row the row index in this image
     * @param col the column index in this image
     * @return RGBColor object of the image. If coordinates out of bounds so the returned RGBColor is black by default.
     */
    public RGBColor getPixel (int row, int col) {
        // checks if the coordinates are in bounds.
        if (row >= 0 && row < _image.length) {
            if (col >= 0 && col < _image[0].length) {
                return new RGBColor(_image[row][col]);
            }
        }
        return new RGBColor();
    }
    /**
     * Method which set a given color to a specific give coordinate.
     * @param row the row index in this image
     * @param col the column index in this image
     * @param pixel the new pixel to change to.
     */
    public void setPixel (int row, int col, RGBColor pixel) {
        // checks if the coordinates are in bounds.
        if (row >= 0 && row < _image.length) {
            if (col >= 0 && col < _image[0].length) {
                _image[row][col] = new RGBColor(pixel);
            }
        }
    }

    /**
     * Method which compares between two RGBImage.
     * @param other the image to compare to
     * @return True if the images are equal in their pixels values and amount
     */
    public boolean equals (RGBImage other) {
        //check if images have the same width and height
        if (_image.length == other._image.length) { // check height
            if (_image[0].length == other._image[0].length) { // check width
            
                for (int i = 0; i < _image.length; i++) {
                    for (int j = 0; j < _image[i].length; j++) {
                        //checks every color in the image grid if they are equal
                        if (!_image[i][j].equals(other._image[i][j]))
                            return false; // False for the first unequal color
                    }
                }

                return true; // the images have compared and found to be equal
            }
        }
        return false; // the images aren't equal cause they don't have same sizes
    }

    /**
     * Method which flip Horizontally this image
     */
    public void flipHorizontal() {
        for (int i = 0; i < _image.length; i++) {

            // Run on half of the columns
            for (int j = 0; j < _image[i].length / 2; j++) {

                // color exchange
                RGBColor tempColor = _image[i][j];
                _image[i][j] = _image[i][_image[i].length - j - 1];
                _image[i][_image[i].length - j - 1] = tempColor;

            }
        }
    }
    /**
     * Method which flip Vertically this image
     */
    public void flipVertical() {
        for (int i = 0; i < _image.length / 2; i++) {

            // Run on half of the columns
            for (int j = 0; j < _image[i].length; j++) {

                // color exchange
                RGBColor tempColor = _image[i][j];
                _image[i][j] = _image[_image.length - i - 1][j];
                _image[_image.length - i - 1][j] = tempColor;

            }
        }
    }
    
    /**
     * Method which changes every color to it's complementary color
     */
    public void invertColors() {
        for (int i = 0; i < _image.length; i++) {
            for (int j = 0; j < _image[i].length; j++) {
                _image[i][j].setRed(255 - _image[i][j].getRed());
                _image[i][j].setGreen(255 - _image[i][j].getGreen());
                _image[i][j].setBlue(255 - _image[i][j].getBlue());
            }
        }
    }

    /**
     * Method which rotate this image clockwise
     */
    public void rotateClockwise() {
    	// original rows and columns of the image
        int rows = _image.length;
        int cols = _image[0].length;

        // the new rotated image grid
        RGBColor[][] newImgGrid = new RGBColor[cols][rows];

        // rotate 2D array clockwise
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newImgGrid[j][rows - i - 1] = _image[i][j];
            }
        }

        _image = newImgGrid;
    }

    /**
     * Method which rotate this image counter clockwise
     */
    public void rotateCounterClockwise() {
        // original rows and columns
        int originRows = _image.length;
        int originCols = _image[0].length;

        // the new rotated image grid
        RGBColor[][] newImgGrid = new RGBColor[originCols][originRows];

        // rotate 2D array counter clockwise
        for (int i = 0; i < originRows; i++) {
            for (int j = 0; j < originCols; j++) {
                newImgGrid[originCols - j - 1][i] = _image[i][j];
            }
        }

        _image = newImgGrid;
    }

    /**
     * Method which move image to the right/left side by a specific number.
     * positive offset will move the image to the right, negative offset will move the image to the left,
     * 0 offset will not move the image.
     * @param offset The number represents the offset that the image will move.
     */
    public void shiftCol(int offset) {
    	// original rows and columns of the image
        int rows = _image.length;
        int cols = _image[0].length;

        RGBColor[][] newImgGrid = new RGBColor[rows][cols];
        
        if (offset > 0) { // move image to the right
        	for (int i = 0; i < cols; i++) {
				for (int j = 0; j < rows; j++) {
					if (i < offset) {
						// place black cells
						newImgGrid[j][i] = new RGBColor();
					} else {
						// place image cells
						newImgGrid[j][i] = new RGBColor(_image[j][i - offset]);
					}
				}
			}

        } else if(offset < 0) { // move image to the left
        	offset *= -1; // offset correction for further calculations
        	
        	for (int i = cols - 1; i >= 0; i--) {
				for (int j = 0; j < rows; j++) {
					if (i > cols - offset - 1) {
						// place black cells
						newImgGrid[j][i] = new RGBColor();
					} else {
						// place image cells
						newImgGrid[j][i] = new RGBColor(_image[j][i + offset]);
					}
				}
			}
        }

        _image = newImgGrid; // assignment of the new image grid
    }
    
    /**
     * Method which move image to the up/down side by a specific number.
     * positive offset will move the image down, negative offset will move the image up,
     * 0 offset will not move the image.
     * @param offset The number represents the offset that the image will move.
     */
    public void shiftRow(int offset) {
    	// original rows and columns of the image
        int rows = _image.length;
        int cols = _image[0].length;

        RGBColor[][] newImgGrid = new RGBColor[rows][cols];
        
        if (offset > 0) { // move image down
        	for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (i < offset) {
						// place black cells
						newImgGrid[i][j] = new RGBColor();
					} else {
						// place image cells
						newImgGrid[i][j] = new RGBColor(_image[i - offset][j]);
					}
				}
			}

        } else if(offset < 0) { // move image up
        	offset *= -1; // offset correction for further calculations
        	
        	for (int i = rows - 1; i >= 0; i--) {
				for (int j = 0; j < cols; j++) {
					if (i > rows - offset - 1) {
						// place black cells
						newImgGrid[i][j] = new RGBColor();
					} else {
						// place image cells
						newImgGrid[i][j] = new RGBColor(_image[i + offset][j]);
					}
				}
			}
        }

        _image = newImgGrid; // assignment of the new image grid
    }
    

    /**
     * Method which returns the new GrayScaled image using this image
     * @return double, 2D array of the grayscaled image 
     */
    public double[][] toGrayscaleArray() {
    	// original rows and columns of the image
        int rows = _image.length;
        int cols = _image[0].length;
        
    	double[][] newGLImage = new double[rows][cols];
    	
    	// iterate through every color and change it to grayscaled
    	for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				newGLImage[i][j] = _image[i][j].convertToGrayscale();
			}
		}
    	return newGLImage;
    }
    
    /**
     * Method which returns a String that represents the image as a table numbers
     * @return String, table of the image values 
     */
    public String toString() {
    	// original rows and columns of the image
        int rows = _image.length;
        int cols = _image[0].length;
        
    	String strOutputString = "";
    	
    	
    	for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols - 1; j++) {
				strOutputString += _image[i][j].toString() + " ";
			}
			strOutputString += _image[i][cols - 1].toString() + "\n";
		}
    	
    	return strOutputString;
    }
    
    /**
     * Method which clone this RGBImage color array to a new one and returns it.
     * @return RGBColor 2D array, table of the color objects
     */
    public RGBColor[][] toRGBColorArray() { // original rows and columns of the image
        int rows = _image.length;
        int cols = _image[0].length;
    	
        RGBColor[][] newImgGrid = new RGBColor[rows][cols];

        // initialize new color table
        for (int i = 0; i < _image.length; i++) {
            for (int j = 0; j < _image[i].length; j++) {
            	newImgGrid[i][j] = new RGBColor(_image[i][j]);
            }
        }
        
        return newImgGrid;
    }
}






































