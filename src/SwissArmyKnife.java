import java.util.Scanner;

public class SwissArmyKnife {
	public static void main(String[] args) {
		SwissArmyKnifeControls controls = new SwissArmyKnifeControls();
		Scanner keyboard;
		String choice = "1";
		String location = "";
		String commands = "Commands: commands, events, directions, help, location (sets location), landmarks, mail, quit, translate, tw,";
		System.out.println("Welcome back, the current weather is :"  + controls.getWeather());
		System.out.println("Input command");
		while (!"quit".equals(choice)) {
			keyboard = new Scanner(System.in);
			choice = keyboard.nextLine();
			if (choice.equals("tw")) {
				controls.getTW();
			}
			if (choice.equals("mail")) {
				controls.getMail();
			}
			if (choice.equals("translate")) {
				controls.translate();
			}
			if ((choice.equals("help") || (choice.equals("commands")))) {
				System.out.println(commands);
			}

			if (choice.equals("weather")) {
				System.out.println(controls.getWeather());
			}
			if (choice.equals("location")) {
				System.out.println("Enter your address");
				location = controls.setLocation();
			}
			if (choice.equals("landmarks")) {
				System.out.println("Finding landmarks based on location");
				if (location.equals("")) {
					System.out.println("There is no location set: \n please use command: location");
				}
				else {
					controls.getLandmarks(location);
					System.out.println("Displaying airport|amusement_park|aquarium|art_gallery|atm|bakery|bank|bar");
				}
			}
			if (choice.equals("events")) {
				System.out.println("Finding events based on location");
				if (location.equals("")) {
					System.out.println("There is no location set: \n please use command: location");
				}
				else { 
					controls.getEvents(location);
					System.out.println("Displaying all events");
				}
			}
			if (choice.equals("directions")) {
				System.out.println("Finding directions based on current location");
				if (location.equals("")) {
					System.out.println("There is no location set: \n please use command: location");
				}
				else {
					String destination = "";
					System.out.println("enter destination:");
					keyboard = new Scanner(System.in);
					destination = keyboard.nextLine();
					controls.getDirections(destination,location);
					System.out.println("Displaying directions");

				}
			}
			if (!commands.contains(choice)) {
				controls.ask(choice);
			}


		}
	}
}
