package rpg.system;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class Database {

	protected static String _URL = RpgSetup.MONGO_IP+":"+RpgSetup.MONGO_PORT;
	protected static String _USER = RpgSetup.MONGO_USER;
	protected static String _PASS = RpgSetup.MONGO_PASS;
	protected static String _DATABASE = RpgSetup.MONGO_DATABASE;
	private static MongoClient _CLIENT;
	private static boolean _ENABLE;
	
	public Database(String url, String user, String pass, String db){
		this._URL = url;
		this._USER = user;
		this._PASS = pass;
		this._DATABASE = db;
	}

	public static void start() {
		try {
			_CLIENT = new MongoClient(
					new MongoClientURI("mongodb://" + _USER + ":" + _PASS + "@" + _URL + "/" + _DATABASE));//

			_ENABLE = true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public static void stop() {
		if (_ENABLE) {
			_CLIENT.close();
		}
	}

	public static MongoDatabase getDataBase(String database) {
		return _CLIENT.getDatabase(database);
	}

}
