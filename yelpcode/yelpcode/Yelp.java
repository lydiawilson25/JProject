
public class Yelp {
	private List<Restaurant> restaurants;
	private List<Rating> ratings;

	public Yelp(List<Restaurant> restaurants, List<Rating> ratings) {
		this.restaurants = restaurants;
		this.ratings = ratings;
	}

	/*
	 * Returns list of Restaurant within radius.
	 *
	 * latitude: double longitude: double radius: kilometer in int diningHour:
	 * If null, find any restaurant in radius. Otherwise return list of open
	 * restaurants at specified hour. sortByRating: If true, sort result in
	 * descending order, highest rated first.
	 */
	public List<Restaurant> find(double latitude, double longitude, int radius, int diningHour, boolean sortByRating) {
		
		throw new UnsupportedOperationException("please implement");
	}

	public static class Restaurant {
		private double latitude;
		private double longitude;
		private int id;
		private String name;
		private int openHour; /* in [0-23] */
		private int closeHour; /* in [0-23] */

	public Restaurant(double latitude, double longitude,int id, String name, int openhour, int closehour) { 
		this.latitude=latitude;
		this.longitude=longitude;
		this.id=id;
		this.name=name;
		this.openHour=openhour;
		this.closeHour=closehour;
	}  
  }

		public static class Rating {
			private int id;
			private int rating; /* in [1-5] */

	public Rating(int id,int rating) {
		this.id=id;
		this.rating=rating;
		}      /* Omitted */
	}
  }

	public static void main(String[] args) {
		Restaurant dennys = new Restaurant(-37.7, -122.6,101,"dennys",23,0);
		Restaurant shan = new Restaurant(-100, -200.6,102,"shan",20,21);
		Rating dennysrating = new Rating(101,2);
		Rating shanrating = new Rating(102,5);
		
		
		
    List<Restaurant> restaurants = {dennys,shan};  /* Omitted */
    List<Rating> ratings = {dennysrating,shanrating};          /* Omitted */

    Yelp y = new Yelp(restaurants, ratings);
    y.find(-37.7, -122.6, 5, null, false);
  }
}
