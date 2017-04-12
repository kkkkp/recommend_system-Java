
public class Movie {
	private int id;
	
	public Movie(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public boolean equals(Movie m) {
		return id == m.getId();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
