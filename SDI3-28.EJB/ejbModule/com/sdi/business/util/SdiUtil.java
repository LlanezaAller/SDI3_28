package com.sdi.business.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.regex.Pattern;

import com.sdi.infraestructure.factories.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;

public class SdiUtil {

	public static boolean assertCampos(String... o) {
		for (int i = 0; i < o.length; i++) {
			if (o[i] == null || o[i].isEmpty())
				return false;
		}
		return true;
	}

	public static boolean isEmail(String e) {
		String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(email_pattern);
		return pattern.matcher(e).matches();
	}

	public static Date getDate(String s) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		Date limitDatetime = null;
		try {
			limitDatetime = simpleDateFormat.parse(s.replace('T', ' '));
			return limitDatetime;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void generateDatabaseElements() {
		// Factories.persistence.createSystemGateway().deleteAll();
	}

	public static boolean assertHasApplication(User user, Trip trip) {
		boolean result = false;
		if (user != null) {
			Set<User> apps = trip.getApplications();
			for (User u : apps) {
				if (user.getId().equals(u.getId())) {
					result = true;
				}
			}
			Factories.business.getUsuariosService().aplicar(apps, trip);
		}
		return result;
	}

	public static boolean assertHasSeat(User user, Trip trip) {
		if (user != null) {
			for (Seat s : trip.getSeats()) {
				if (s.getUser().getId().equals(user.getId())) {
					return true;
				}
			}
		}
		return false;
	}
}
