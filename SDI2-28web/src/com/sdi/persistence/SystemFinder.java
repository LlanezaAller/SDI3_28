package com.sdi.persistence;

public interface SystemFinder {

	void deleteAll();

	void createElements();

	void resetIDs();

	void unlink(Object o);

}
