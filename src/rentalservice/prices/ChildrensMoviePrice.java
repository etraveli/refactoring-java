package rentalservice.prices;

public class ChildrensMoviePrice implements Price {

	@Override
	public double getPriceFor(int days) {
		double baseprice = 1.5;
		if (days > 3) {
			return ((days - 3) * 1.5) + baseprice;
		}
		return baseprice;
	}

}
