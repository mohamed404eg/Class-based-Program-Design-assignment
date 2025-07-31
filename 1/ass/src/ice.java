import tester.*;

interface IIceCream {
	
}

class  EmptyServing implements IIceCream{
	EmptyServing(){}
}

class Scooped implements IIceCream{
	IIceCream more;
	String flaover;
	
	Scooped(IIceCream more, String flaover){
		this.more = more;
		this.flaover = flaover;
	}
}

class ExamplesIIceCream{
	ExamplesIIceCream(){}
	
	IIceCream order1 = new Scooped(
				new Scooped(
				new Scooped(
				new Scooped(
			    new EmptyServing(),
				"caramel swirl"),
			    "black raspberry"),
				"coffee"),
				"mint chip");
	
	
	IIceCream order2 = new Scooped(
				new Scooped(
				new Scooped(
			    new EmptyServing(),
			    "chocolate"),
				"vanilla"),
				"strawberry");
}
