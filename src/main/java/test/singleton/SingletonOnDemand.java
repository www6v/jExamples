package test.singleton;

// initialization-on-demand holder idiom(IODH)
// 延时加载和线程安全
public class SingletonOnDemand {

	private SingletonOnDemand() {
	};

	private static class SingletonHolder {
		private static final SingletonOnDemand INSTANCE = new SingletonOnDemand();
	}

	public static SingletonOnDemand getInstance() {
		return SingletonHolder.INSTANCE;
	}
}