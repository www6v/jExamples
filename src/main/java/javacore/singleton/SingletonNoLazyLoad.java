package javacore.singleton;

public class SingletonNoLazyLoad {
	private static SingletonNoLazyLoad instance = new SingletonNoLazyLoad();

	private SingletonNoLazyLoad() {
	}

	public static SingletonNoLazyLoad getInstance() {
		return instance;
	}
}