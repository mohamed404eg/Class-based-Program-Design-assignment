import tester.*;

public class Dog {
	String name;
	String breed;
	int yob;
	String state;
	boolean hypoallergenic;

	Dog(String name, String breed, int yob, String state, boolean hypoallergenic) {
		this.name = name;
		this.breed = breed;
		this.yob = yob;
		this.state = state;
		this.hypoallergenic = hypoallergenic;
	}

}

class ExamplesDog {
	ExamplesDog() {
	}

	Dog hufflepuff = new Dog("Hufflepuff", "Wheaten Terrier", 2012, "TX", true);
	Dog pearl = new Dog("Labrador Retriever", "Wheaten Terrier", 2016, "MA", false);

	Dog bby = new Dog("Bby", "German Shepherd", 2020, "EG", true);

}