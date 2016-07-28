package test.singleton;

public class SingletonLazyLoad {
    private static SingletonLazyLoad instance;
    private SingletonLazyLoad (){}

    public static SingletonLazyLoad getInstance() {
	if (instance == null) {
	    instance = new SingletonLazyLoad();
	}
	return instance;
    }
}