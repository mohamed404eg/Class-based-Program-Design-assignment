import tester.*;



 interface IMotif {

	 double averageDifficulty();
	 int count();
	 String description();
	 String type();
	 String embroideryInfo();
}


class CrossStitchMotif implements IMotif {
	String description;
	double    difficulty;
	
	
	CrossStitchMotif(String description, double difficulty){
		this.description = description;
		this.difficulty  = difficulty;
	}
	
	public double averageDifficulty() {
		return difficulty;
	}
	
	public int count() {
		return 1;
	}
	
	public String description() {
		return this.description;
	}
	public String type() {
		return "(cross stitch)";
	}
	
	public String embroideryInfo() {
		return this.description()+ " " + this.type();
	}
}

class ChainStitchMotif implements IMotif {
	String description;
	double    difficulty;
	
	
	ChainStitchMotif(String description, double difficulty){
		this.description = description;
		this.difficulty  = difficulty;
	}
	
	
	public double averageDifficulty() {
		return difficulty;
	}
	
	public int count() {
		return 1;
	}
	public String description() {
		return this.description;
	}
	
	public String type() {
		return "(chain stitch)";
	}
	public String embroideryInfo() {
		return this.description()+ " " + this.type();
	}
}


class GroupMotif  implements IMotif {
	String description;
	ILoMotif    motifs;
	
	GroupMotif(String description, ILoMotif motifs){
		this.description = description;
		this.motifs  = motifs;
	}
	
	public double averageDifficulty() {
		return motifs.averageDifficulty();
	}
	
	public int count() {
		return motifs.count();
	}
	
	
	public String embroideryInfo() {
		return motifs.LoMotifInfo();
	}
	
	
	public String description() {
		return this.description;
	}
	
	public String type() {
		return "GroupMotif";
	}
	
}

interface ILoMotif {
	double averageDifficulty();
	int count();
	double averageDifficultyHelp();
	String LoMotifInfo();
}

class MtLoMotif implements ILoMotif{
	MtLoMotif(){}
	
	public double averageDifficulty() {
		return 0;
	}
	public int count() {
		return 0;
	};
	
	public double averageDifficultyHelp() {
		return 0;
	}
	public String LoMotifInfo() {
		return "";
	}
}

class ConLoMotif implements ILoMotif{
	IMotif first;
	ILoMotif rest;
	
	
	/*Template:
	 * Fields:
	 * ... this.first  ...    --- IMotif
	 * ... this.rest   ...    --- ILoMotif
	 * 
	 * Methods Fields:
	 *  ... this.first.averageDifficulty()     ...    --- double
	 *  ... this.rest.averageDifficulty() 	   ...    --- double
	 *  ... this.first.count() 			       ...    --- int
	 *  ... this.rest.count()                  ...    --- int
	 *  ... this.rest.averageDifficultyHelp()  ...    --- double
	 *  ... this.first.embroideryInfo()        ...    --- String
	 *  ... this.first.description()           ...    --- String
	 *  ... this.first.description()           ...    --- String
	 *  ... this.rest.LoMotifInfo()            ...    --- String
	 */
	
	ConLoMotif(IMotif first, ILoMotif rest){
		this.first = first;
		this.rest = rest;
		
	}
	
	
	
	
	
	/*Template:
	 * Fields:
	 * ... this.first  ...    --- IMotif
	 * ... this.rest   ...    --- ILoMotif
	 * 
	 * Methods Fields:
	 * ... this.first.averageDifficulty()  ...    --- double
	 *  ... this.rest.averageDifficulty()  ...    --- double
	 */
	
	public double averageDifficulty() {
		return this.averageDifficultyHelp() / this.count();
	}
	
	
	
	/*Template:
	 * Fields:
	 * ... this.first  ...    --- IMotif
	 * ... this.rest   ...    --- ILoMotif
	 * 
	 * Methods Fields:
	 * ... this.first.averageDifficulty()      ...    --- double
	 *  ... this.rest.averageDifficulty() 	   ...    --- double
	 * ... this.first.count() 			       ...    --- int
	 *  ... this.rest.count()                  ...    --- int
	 *  ... this.rest.averageDifficultyHelp()  ...    --- double
	 */
	public double averageDifficultyHelp() {
		return  first.averageDifficulty() + rest.averageDifficultyHelp() ;
	}
	
	
	
	/*Template:
	 * Fields:
	 * ... this.first  ...    --- IMotif
	 * ... this.rest   ...    --- ILoMotif
	 * 
	 * Methods Fields:
	 * ... this.first.averageDifficulty()  ...    --- double
	 *  ... this.rest.averageDifficulty()  ...    --- double
	 * ... this.first.count() 			   ...    --- int
	 *  ... this.rest.count()              ...    --- int
	 */
	
	public int count() {
		return this.first.count() + this.rest.count();
	};
	
	
	
	/*Template:
	 * Fields:
	 * ... this.first  ...    --- IMotif
	 * ... this.rest   ...    --- ILoMotif
	 * 
	 * Methods Fields:
	 *  ... this.first.averageDifficulty()     ...    --- double
	 *  ... this.rest.averageDifficulty() 	   ...    --- double
	 *  ... this.first.count() 			       ...    --- int
	 *  ... this.rest.count()                  ...    --- int
	 *  ... this.rest.averageDifficultyHelp()  ...    --- double
	 *  ... this.first.embroideryInfo()        ...    --- String
	 *  ... this.first.description()           ...    --- String
	 *  ... this.first.description()           ...    --- String
	 *  ... this.rest.LoMotifInfo()            ...    --- String
	 */
	
	
	public String LoMotifInfo() {
		if (MtLoMotif.class.isInstance(this.rest)) {
			return this.first.embroideryInfo();
		}else {
			
		return this.first.embroideryInfo() + ", " + this.rest.LoMotifInfo();
		}
	}
	
	
}




class EmbroideryPiece {
	String name;
	IMotif motif;
	
	EmbroideryPiece(String name, IMotif motif){
		this.name  = name;
		this.motif = motif;
	}
	
	//  computes the average difficulty of all motif
	double averageDifficulty() {
		return motif.averageDifficulty();
	}
	
	int count() {
		return motif.count();
	}
	
	String embroideryInfo() {
		return this.name +": " + this.motif.embroideryInfo()+".";
	}
}


 class ExamplesEmbroidery {
	ExamplesEmbroidery(){}
	
	IMotif bird = new CrossStitchMotif("bird",4.5);
	IMotif tree = new ChainStitchMotif("tree",3.0);
	
	IMotif rose = new CrossStitchMotif("rose",5.0);
	IMotif poppy = new ChainStitchMotif("poppy",4.75);
	IMotif daisy = new CrossStitchMotif("daisy",3.2);
	
	ILoMotif lomFlowers = new ConLoMotif(rose,new ConLoMotif(poppy,new ConLoMotif(daisy,new MtLoMotif())));
	
	IMotif gFlowers = new GroupMotif("flowers",lomFlowers);

	
	ILoMotif lomNature = new ConLoMotif(bird,new ConLoMotif(tree, lomFlowers));
	
	IMotif gNature = new GroupMotif("nature", lomNature);
	
	EmbroideryPiece pillowCover = new EmbroideryPiece("Pillow Cover",gNature);
	
	
	
	boolean testIMotifCount(Tester t) {
		return t.checkExpect(bird.count(), 1) &&
				t.checkExpect(tree.count(), 1) && 
				t.checkExpect(gFlowers.count(), 3);
	}
	
	boolean testILoMotifCount(Tester t) {
		return t.checkExpect(lomFlowers.count(), 3) &&
				t.checkExpect(new MtLoMotif().count(), 0);
	}
	

	boolean testEmbroideryPieceCount(Tester t) {
		return t.checkExpect(pillowCover.count(), 5);
	}


	
	
	boolean testIMotifAverageDifficulty(Tester t) {
		return t.checkInexact(bird.averageDifficulty(), 4.5,0.01) &&
				t.checkInexact(tree.averageDifficulty(), 3.0,0.01) && 
				t.checkInexact(gFlowers.averageDifficulty(), 4.31,0.01);
	}
	
	boolean testILoMotifAverageDifficulty(Tester t) {
		return t.checkInexact(lomFlowers.averageDifficulty(), 4.31,0.01) &&
				t.checkInexact(new MtLoMotif().averageDifficulty(), 0.0,0.1);
	}
	

	boolean testEmbroideryPieceAverageDifficulty(Tester t) {
		return t.checkInexact(pillowCover.averageDifficulty(), 4.09 ,0.1);
	}
	
	
	boolean testIMotifEmbroideryInfo(Tester t) {
		return t.checkExpect(bird.description().concat(" " +bird.type()),
				"bird (cross stitch)") &&
				t.checkExpect(tree.description().concat(" " +tree.type()),
						"tree (chain stitch)")		&&
				t.checkExpect(gFlowers.embroideryInfo(),
						"rose (cross stitch), poppy (chain stitch), daisy (cross stitch)");
	}
	
	
  boolean testILoMotifLoMotifInfo(Tester t) {
	  return t.checkExpect(new MtLoMotif().LoMotifInfo(), "") &&
			  t.checkExpect(lomFlowers.LoMotifInfo(), 
					  "rose (cross stitch), poppy (chain stitch), daisy (cross stitch)")
			  ;
		
	}
  

  boolean testEmbroideryPieceEmbroideryInfo(Tester t) {
	  return t.checkExpect(pillowCover.embroideryInfo(), 
"Pillow Cover: bird (cross stitch), tree (chain stitch), rose (cross stitch), poppy (chain stitch), daisy (cross stitch).");
  }

	
	
}
