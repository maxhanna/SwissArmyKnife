import java.net.*;
import java.awt.Desktop;
import java.io.*;
import java.util.Scanner;


public class SwissArmyKnifeControls{

	Scanner keyboard;
	public float getWeather() {
		URL oracle = null;
		try {
			oracle = new URL("http://api.openweathermap.org/data/2.5/weather?q=Montreal&mode=xml");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(oracle.openStream()));

			String inputLine;
			int counter = 0;
			while ((inputLine = in.readLine()) != null) {
				counter++;
				if (counter == 8) {
					String split[] = inputLine.split("");
					String weatherText = (split[22] + split[23] + split[24]+split[25]+split[26]);
					float weather = (Float.parseFloat(weatherText) - 273.15f);
					return weather;
				}				
			}
			in.close();		
		}
		catch(MalformedURLException e) {
			System.out.println("You must be connected to the internet to use this application");
		}
		catch(IOException e) {
			System.out.println("You must be connected to the internet to use this application");
		}


		return -0;
	}
	public void getTW() {
		if(Desktop.isDesktopSupported()) {
			try { 
				Desktop.getDesktop().browse(new URI("http://www.tribalwars.net"));
			}
			catch(URISyntaxException e) {
				System.out.println("URI Syntax Exception");
			}
			catch(IOException e) {
				System.out.println("IOException");
			}
		}
	}
	public void getMail() {
		if(Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI("http://www.hotmail.com"));
			}
			catch(URISyntaxException e) {
				System.out.println("URI Syntax Exception");
			}
			catch(IOException e) {
				System.out.println("IOException");
			}
		}

	}
	public String setLocation() {
		String addr = "";
		String latlon = "";
		keyboard = new Scanner(System.in);
		addr = keyboard.nextLine();
		URL google = null;
		try {
			google = new URL("https://maps.googleapis.com/maps/api/geocode/xml?address="+addr+"&key=AIzaSyANH-gRNpDsoei_FXQHEMsCMuXKwc1Iabs");
			BufferedReader in = new BufferedReader(new InputStreamReader(google.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains("   <location>")) {
					inputLine = in.readLine();
					String split2[] = inputLine.split("");
					latlon = split2[9]+split2[10]+split2[11]+split2[12]+split2[13]+split2[14]+split2[15]+split2[16]+split2[17]+",";
					inputLine = in.readLine();
					String split3[] = inputLine.split("");
					latlon = latlon + split3[9]+split3[10]+split3[11]+split3[12]+split3[13]+split3[14]+split3[15]+split3[16]+split3[17]+split3[18];

					System.out.println("location set to : " + latlon);
				}                       
			}
			in.close();
			return (latlon);
		}
		catch(MalformedURLException e) {
			System.out.println("Cannot find webpage" + google);
		}
		catch(IOException e) {
			System.out.println("Cannot read from webpage " + google);
		}

		return (latlon);
	}
	public void getLandmarks(String address) {
		System.out.println("current coordinates: " + address);
		URL google = null;
		try {
			google = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?key=AIzaSyANH-gRNpDsoei_FXQHEMsCMuXKwc1Iabs&location="+address+"&radius=400&rankBy=distance&types=accounting|airport|amusement_park|aquarium|art_gallery|atm|bakery|bank|bar&language=en&sensor=false");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(google.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains("<name>")) {
					System.out.println(inputLine.substring(inputLine.lastIndexOf("<name>"),inputLine.lastIndexOf("</name>")));
				}
			}
			in.close();
		}
		catch(MalformedURLException e) {
			System.out.println("Cannot find webpage" + google);
		}
		catch(IOException e) {
			System.out.println("Cannot read from webpage " + google);
		}
	}


	public void getEvents(String address) {
		System.out.println("current coordinates: " + address);
		URL eventful = null;
		try {
			eventful = new URL("http://api.eventful.com/rest/events/search?..&app_key=6Cc4TDpPcQGwmXDh&where="+address+"&within=15");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(eventful.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains("<title>")) {
					inputLine = inputLine.replace("<title>", "");
					inputLine = inputLine.replace("</title>", "");
					System.out.println("\n"+inputLine);
				}
				if (inputLine.contains("<start_time>")) {
					inputLine = inputLine.replace("<start_time>", "");
					inputLine = inputLine.replace("</start_time>", "");
					System.out.println(inputLine);
				}
				if (inputLine.contains("<venue_address>")) {
					inputLine = inputLine.replace("<venue_address>", "");
					inputLine = inputLine.replace("</venue_address>", "");
					System.out.println(inputLine);
				}

			}
			in.close();
		}
		catch(MalformedURLException e) {
			System.out.println("Cannot find webpage" + eventful);
		}
		catch(IOException e) {
			System.out.println("Cannot read from webpage " + eventful);
		}
	}
	public void getDirections(String address, String origin) {
		System.out.println("current coordinates: " + address);
		URL google = null;
		try {
			google = new URL("https://maps.googleapis.com/maps/api/directions/xml?origin="+origin+"&destination="+address+"&key=AIzaSyANH-gRNpDsoei_FXQHEMsCMuXKwc1Iabs");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(google.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains("<summary>")) {
					inputLine = inputLine.replace("<summary>", "");
					inputLine = inputLine.replace("</summary>", "");
					System.out.println("\n"+inputLine);
				}
				if (inputLine.contains("<html_instructions>")) {
					inputLine = inputLine.replace("<html_instructions>", "");
					inputLine = inputLine.replace("</html_instructions>", "");
					inputLine = inputLine.replace(";b&gt;", "");
					inputLine = inputLine.replace(";/b&gt;", "");
					inputLine = inputLine.replace("&lt", "");
					inputLine = inputLine.replace("/div&gt;", "");
					inputLine = inputLine.replace(";div style=&quot;font-size:0.9em&quot;&gt;","\n");
					System.out.println(inputLine);
				}

			}
			in.close();
		}
		catch(MalformedURLException e) {
			System.out.println("Cannot find webpage" + google);
		}
		catch(IOException e) {
			System.out.println("Cannot read from webpage " + google);
		}
	}
	public void ask(String query) {
		System.out.println("Searching for: " + query);
		URL wolfram = null;
		try {
			wolfram = new URL("http://api.wolframalpha.com/v2/query?input="+query+"&appid=28KJV7-V3XXKYT8T5");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(wolfram.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains("<plaintext>")) {
					inputLine = inputLine.replace("<plaintext>","");
					inputLine = inputLine.replace("</plaintext>","");
					System.out.println(inputLine);
				}
			}
			in.close();
		}
		catch(MalformedURLException e) {
			System.out.println("Cannot find webpage" + wolfram);
		}
		catch(IOException e) {
			System.out.println("Cannot read from webpage " + wolfram);
		}
	}
	public void translate() {
		System.out.println("base language is set to English");
		System.out.println("Please enter what you would like to translate");
		keyboard = new Scanner(System.in);
		String what = keyboard.nextLine();
		System.out.println("Enter the language you would like this translated to");
		String in = keyboard.nextLine();
		System.out.println("Translation of "+what+" in "+in + " is not implemented yet");

	}



}
