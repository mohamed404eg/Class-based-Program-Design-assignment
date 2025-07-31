
interface IHousing {

}

class Hut implements IHousing {
	int population;
	int capacity;

	Hut(int population, int capacity) {
		this.population = population;
		this.capacity = capacity;
	}
}

class Inn implements IHousing {
	String name;
	int population;
	int capacity;
	int stalls;

	Inn(String name, int population, int capacity, int stalls) {
		this.name = name;
		this.population = population;
		this.capacity = capacity;
		this.stalls = stalls;
	}
}

class Castle implements IHousing {
	String name;
	String familyName;
	int population;
	int carriages;

	Castle(String name, String familyName, int population, int carriages) {
		this.name = name;
		this.familyName = familyName;
		this.population = population;
		this.carriages = carriages;
	}
}

interface ITraveling {
}

class Carriage implements ITraveling {
	IHousing from;
	IHousing to;
	int tonnage;

	Carriage(IHousing from, IHousing to, int tonnage) {
		this.from = from;
		this.to = to;
		this.tonnage = tonnage;
	}
}

class Horse implements ITraveling {
	IHousing from;
	IHousing to;
	String name;
	String color;

	Horse(IHousing from, IHousing to, String name, String color) {
		this.from = from;
		this.to = to;
		this.name = name;
		this.color = color;
	}
}

class ExamplesTravel {
	ExamplesTravel() {
	}

	IHousing hovel = new Hut(1, 5);
	IHousing winterfell = new Castle("Winterfell", "Stark", 500, 6);
	IHousing crossroads = new Inn("Inn At The Crossroads", 20, 40, 12);

	IHousing yourHut = new Hut(2, 6);
	IHousing yourCastle = new Castle("YourCastle", "Castle", 1000, 7);
	IHousing yourInn = new Inn("Inn At The yourInn", 1, 5, 2);

	ITraveling horse1 = new Horse(winterfell, yourCastle, "horse1", "black");
	ITraveling Carriage1 = new Carriage(crossroads, winterfell, 100);

}
