package com.sdi.model.keys;

import java.io.Serializable;

public class SeatKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long seatUser;
	Long seatTrip;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seatTrip == null) ? 0 : seatTrip.hashCode());
		result = prime * result + ((seatUser == null) ? 0 : seatUser.hashCode());
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
		SeatKey other = (SeatKey) obj;
		if (seatTrip == null) {
			if (other.seatTrip != null)
				return false;
		} else if (!seatTrip.equals(other.seatTrip))
			return false;
		if (seatUser == null) {
			if (other.seatUser != null)
				return false;
		} else if (!seatUser.equals(other.seatUser))
			return false;
		return true;
	}

}
