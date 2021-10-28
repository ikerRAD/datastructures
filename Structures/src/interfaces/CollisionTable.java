package interfaces;

public interface CollisionTable<K, V> {
	/**
	 * Method that inserts the value in its corresponding class
	 * 
	 * @param k   -The key of the class
	 * @param val -the new value
	 */
	public void put(K k, V val);

}
