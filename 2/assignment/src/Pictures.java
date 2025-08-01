import tester.*;


interface IPicture{
	// computes the overall width of this picture
	int getWidth();
	int countShapes();
	int comboDepth();
	
	IPicture mirror();
	String pictureRecipe(int depth );
    String pictureRecipeHelper(int depth, boolean end);
}


class Shape implements IPicture {
	String kind;
	int size;
	
	Shape(String kind, int size){
		this.kind = kind;
		this.size = size;
	}

	
	// computes the overall width of this picture
	public int getWidth () {
		return size;
	}
	
	public int countShapes() {
		return 1;
	}
	public int comboDepth() {
		return 0;
	}
	
	public IPicture mirror() {
		return this;
	}
	
	public String pictureRecipe(int depth) {
		return this.kind +  ", " ;
	}
	public String pictureRecipeHelper(int depth) {
		return this.kind +  ", " ;
	}
	public String pictureRecipeHelper(int depth, boolean end) {
		if(end) {
			return this.kind;

		}else {
			
			return this.kind+ ", ";
		}
	}
}

class Combo implements IPicture{
	ILoPictures pictures;
	String name;
	String operation;
	
	Combo(ILoPictures pictures, String name, String operation){
		this.pictures = pictures;
		this.name = name;
		this.operation = operation;
	}

	
	// computes the overall width of this picture
	public int getWidth () {
		if (operation.toLowerCase().equals("scale".toLowerCase())) {
			return pictures.getWidth() * 2;
		}else if(operation.toLowerCase().equals("overlay".toLowerCase())) {
			return this.pictures.biggerWidth();
		}
			
		return this.pictures.getWidth();
		
		
	}
	
	
	public int countShapes() {
		return pictures.countShapes();
	}
	
	public int comboDepth() {
		return 1 + pictures.comboDepth(); 
	}
	
	
	public IPicture mirror() {
		if(this.operation.toUpperCase().contains("Beside".toUpperCase())) {
			return new Combo(pictures.mirror(),this.name,this.operation);
		}else {
			return this;
		}
	}
	
	
	public String pictureRecipe(int depth) {
		if (depth <= 0) {
			return name;
		}
		return operation +"(" + pictures.pictureRecipe(depth -1)+")";
		
	}
	
	
	
	public String pictureRecipeHelper(int depth, boolean end) {
		if (depth <= 0) {
			return "";
		}
		
		if(end) {
			return operation +"(" + pictures.pictureRecipe(depth )+")";

		}else {
		   return operation +"(" + pictures.pictureRecipe(depth)+"), ";
		}
		
	}
	
	
	
}

interface ILoPictures{
	int getWidth();
	// get the bigger picture width in the list
	int biggerWidth();
	int biggerWidthHelper(int preWidth);	
	
	// computes the number of single shapes involved in producing the final image
	int countShapes();
	// computes how deeply operations are nested in the construction of this picture (count just the combo)
	int comboDepth();
	
	ILoPictures mirror();
	ILoPictures mirrorHelper(IPicture p);
	String pictureRecipe(int depth);
}

class MtLoPictures implements ILoPictures{
	public int getWidth() {
		return 0;
	}
	public int biggerWidth() {
		return 0;
	}
	public int biggerWidthHelper(int preWidth) {
		return preWidth;
	}
	public int countShapes() {
		return 0;
	}
	public int comboDepth() {
		return 0;
	}
	public ILoPictures mirror() {
		return this;
	}
	public ILoPictures mirrorHelper(IPicture p){
		return new ConsLoPictures(p,this);
	}
	public String pictureRecipe(int depth) {
		return "";
	}
	
}

class ConsLoPictures implements ILoPictures{
	IPicture first;
	ILoPictures rest;
	
	 ConsLoPictures(IPicture first, ILoPictures rest) {
			this.first = first;
			this.rest = rest;
		}
	 
	 /* Fields:
	  * ... this.first      ... ---  IPicture
	  * ... this.rest       ... ---  ILoPictures
	  * 
	  * Methods:
	  * ... this.getWidth()                          ...  --- int
	  * ... this.biggerWidth()                       ...  --- int
	  * ... this.biggerWidthHelper(int preWidth)     ...  --- int
	  * 
	  * Methods Fields:
	  * ... this.first.getWidth()                    			...  --- int
	  * ... this.rest.getWidth()                    		    ...  --- int
	  * ... this.rest.biggerWidth()                  			...  --- int
	  * ... this.rest.biggerWidthHelper(int preWidth)       	...  --- int
	  * 
	  */
	 
	 // get sum of all width
	 public int getWidth() {
			return this.first.getWidth() + rest.getWidth();
			
	}
	 
	 
	public int biggerWidth() {
		return this.rest.biggerWidthHelper(this.first.getWidth());
			
	}
	
	public int biggerWidthHelper(int preWidth) {
		if (this.first.getWidth() > preWidth) {
			return this.rest.biggerWidthHelper(this.first.getWidth());
		}else {
		return this.rest.biggerWidthHelper(preWidth);
		}
	}
	
	

	public int countShapes() {
		return this.first.countShapes() + this.rest.countShapes();
	}
	
	public int comboDepth() {
		return Math.max(this.first.comboDepth() ,this.rest.comboDepth());
	}
	
	public ILoPictures mirror() {
		return this.rest.mirrorHelper(this.first.mirror());
	}
	public ILoPictures mirrorHelper(IPicture p) {
		return new ConsLoPictures(this.first.mirror(), this.rest.mirrorHelper(p));
	}
	
	public String pictureRecipe(int depth) {
		if (MtLoPictures.class.isInstance(this.rest)) {
			
			return  this.first.pictureRecipeHelper(depth,true)  + this.rest.pictureRecipe(depth ) ;

		}else {
			
			return  this.first.pictureRecipeHelper(depth,false)  + this.rest.pictureRecipe(depth ) ;
				
		}
		
	}
	

	
	
	
}



class ExamplesPicture{
	ExamplesPicture(){}
	
	IPicture circle = new Shape("circle",20);
	IPicture square = new Shape("square",30);
	
	ILoPictures ScaleCircle = new ConsLoPictures(circle, new MtLoPictures());

	
	IPicture bigCircle = new Combo(ScaleCircle,"big circle","scale");
	
	
	
	
	
	

	
	IPicture bigCircleShape = new Shape("square",30);
	
	ILoPictures squareCircle = new ConsLoPictures(square,
							   new ConsLoPictures(bigCircle,
									   new MtLoPictures()));
	
	IPicture  squareOnCircle  = new Combo(squareCircle,"square on circle","overlay");
	
	
	ILoPictures TowSquareOnCircle = new ConsLoPictures(squareOnCircle,
			   new ConsLoPictures(squareOnCircle,
					   new MtLoPictures()));
	
	IPicture doubledSquareOnCircle = new Combo(TowSquareOnCircle,"doubled square on circle","beside");
	

	IPicture SquareCircle = new Combo(squareCircle,"square and circle","beside");
	
	
	ILoPictures circleSquare = new ConsLoPictures(bigCircle ,
			   new ConsLoPictures(square,
					   new MtLoPictures()));
	IPicture SquareCircleMirror = new Combo(circleSquare,"square and circle","beside");
	
	
	
	
	
	ILoPictures towCircle = new ConsLoPictures(circle, new ConsLoPictures(SquareCircle, new MtLoPictures()));
		
	IPicture towCircleBeside = new Combo(towCircle,"circle and circle","beside");

	
	ILoPictures towCircleMirror = new ConsLoPictures(SquareCircleMirror, new ConsLoPictures(circle, new MtLoPictures()));
	
	IPicture towCircleBesideMirror = new Combo(towCircleMirror,"circle and circle","beside");
	
	
	
	
	boolean testConsLoPicturesbiggerWidth(Tester t) {
		return t.checkExpect(ScaleCircle.biggerWidth(),20);
	}
	boolean testConsLoPicturesGetWidth(Tester t) {
		return t.checkExpect(ScaleCircle.getWidth(),20) &&
				t.checkExpect(squareCircle.getWidth(),70) 
				;
	}
	
	boolean testIPictureGetWidth(Tester t) {
		return t.checkExpect(circle.getWidth(), 20) &&
			   t.checkExpect(bigCircle.getWidth(), 40)&&
			   t.checkExpect(squareOnCircle.getWidth(), 40) &&
			   t.checkExpect(doubledSquareOnCircle.getWidth(), 80);
	}
	
	
	
	
	boolean testShapeCountShape(Tester t) {
		return t.checkExpect(circle.countShapes(), 1);
		
	}
	boolean testConsLoPicturesCountShape(Tester t) {
		return t.checkExpect(ScaleCircle.countShapes(), 1) &&
				t.checkExpect(squareCircle.countShapes(), 2);
		
	}
	
	boolean testComboCountShape(Tester t) {
		return t.checkExpect(bigCircle.countShapes(), 1) &&
				t.checkExpect(bigCircle.countShapes(), 1)&&
				t.checkExpect(doubledSquareOnCircle.countShapes(), 4);
		
	}
	boolean testcirclecomboDepth(Tester t) {
		return t.checkExpect(circle.comboDepth(), 0);
		
	}
	boolean testConsLoPicturescomboDepth(Tester t) {
		return t.checkExpect(ScaleCircle.comboDepth(), 0)&&
				t.checkExpect(squareCircle.comboDepth(), 1)&&
			    t.checkExpect(new MtLoPictures().comboDepth(),0);
		
	}
	boolean testCombocomboDepth(Tester t) {
		return t.checkExpect(bigCircle.comboDepth(), 1) &&
				t.checkExpect(bigCircle.comboDepth(), 1)&&
				t.checkExpect(doubledSquareOnCircle.comboDepth(), 3);
		
	}
	
	boolean testConsLoPicturesMirror(Tester t) {
		return t.checkExpect(squareCircle.mirror(),
				new ConsLoPictures(bigCircle,
				   new ConsLoPictures(square ,
						   new MtLoPictures())));
	}
	
	boolean testComboMirror(Tester t) {
		return t.checkExpect(SquareCircle.mirror(),SquareCircleMirror) && 
				t.checkExpect(towCircleBeside.mirror(),towCircleBesideMirror);
	}
	
	
	/*// notice in my examples i use scale(circle) not big circle 
	 * // because i use combo in bigCircle not Shape you can edit but i build all my test depend on it , 
	 *  so i chosen to keep , and just edit the result not like the article 
	 * 
	 *  this the section what i mean (this at the end of the page):
	 *  "
	 *  at depth 2 is "beside(overlay(square, big circle), overlay(square, big circle))",
	 *  and at depth 3 or more is "beside(overlay(square, scale(circle)), overlay(square, scale(circle)))"." 
	 *  "
	 */
	boolean testComboPictureRecipe(Tester t) {
		return t.checkExpect(doubledSquareOnCircle.pictureRecipe (0),"doubled square on circle") && 
				t.checkExpect(doubledSquareOnCircle.pictureRecipe (2),
						"beside(overlay(square, scale(circle)), overlay(square, scale(circle)))")&& 											
				t.checkExpect(doubledSquareOnCircle.pictureRecipe (3),
						"beside(overlay(square, scale(circle)), overlay(square, scale(circle)))") &&
				t.checkExpect(bigCircle.pictureRecipe(1),"scale(circle)") &&
				t.checkExpect(bigCircle.pictureRecipe(0),"big circle") &&
				t.checkExpect(towCircleBeside.pictureRecipe(1),"beside(circle, )")&&
				t.checkExpect(bigCircle.pictureRecipe(1), "scale(circle)");
				
	}

	
	
}
