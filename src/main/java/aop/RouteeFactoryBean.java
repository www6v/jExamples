/**
 * 
 */
package aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.remoting.support.RemotingSupport;




public class RouteeFactoryBean extends RemotingSupport // extends HedwigEventInterceptor 
implements FactoryBean, MethodInterceptor, InitializingBean {
		
	private Object serviceProxy; ///
	private String serviceAppName;
	private String domainName;
	private String serviceName;
	private String serviceVersion;
//	private String target;
	private String clientAppName;
	private String clientVersion;
	private String groupName;
	private Long timeout;
	private boolean autoRedo = false;
	private Set<String> noRetryMethods;
//	private boolean clientThrottle = ProperitesContainer.client().getBoolean(PropKeyConstants.HEDWIG_CLIENT_THROTTLE, true);
	private boolean useBroadCast = false;

	protected Class serviceInterface; ///
	private Object target; ///
	
	public RouteeFactoryBean() {
		super();
	}

	public RouteeFactoryBean(Class<?> clazz) throws Exception {
		super();
		this.serviceInterface = clazz;
		afterPropertiesSet();
	}
	
//	public RouteeFactoryBean(Class<?> clazz, ClientProfile profile) throws Exception {
//		super();
//		this.serviceInterface = clazz;
//		this.clientProfile = profile;
//		afterPropertiesSet();
//	}
//
//	public RouteeFactoryBean(Class<?> clazz, String domainName, String serviceAppName, String serviceName, String serviceVersion,
//			String clientAppName, Long timeout) throws Exception {
//		super();
//		this.serviceInterface = clazz;
//		setServiceAppName(serviceAppName);
//		setDomainName(domainName);
//		setServiceName(serviceName);
//		setServiceVersion(serviceVersion);
//		setClientAppName(clientAppName);
//		setTimeout(timeout);
//		afterPropertiesSet();
//	}
//
//	public RouteeFactoryBean(Class<?> clazz, String domainName, String serviceAppName, String serviceName, String serviceVersion,
//			String clientAppName, String user, String password, Long timeout) throws Exception {
//		super();
//		this.serviceInterface = clazz;
//		setServiceAppName(serviceAppName);
//		setDomainName(domainName);
//		setServiceName(serviceName);
//		setServiceVersion(serviceVersion);
//		setClientAppName(clientAppName);
//		setTimeout(timeout);
//		setUser(user);
//		setPassword(password);
//		afterPropertiesSet();
//	}
//
//	public RouteeFactoryBean(Class<?> clazz, String target, String serviceAppName, String clientAppName, Long timeout, String user,
//			String password) throws Exception {
//		this.serviceInterface = clazz;
//		setServiceAppName(serviceAppName);
//		setTarget(target);
//		setClientAppName(clientAppName);
//		setTimeout(timeout);
//		setUser(user);
//		setPassword(password);
//		afterPropertiesSet();
//	}
//
//	public RouteeFactoryBean(Class<?> clazz, String target, String serviceAppName, String clientAppName, Long timeout)
//			throws Exception {
//		super();
//		this.serviceInterface = clazz;
//		setServiceAppName(serviceAppName);
//		setTarget(target);
//		setClientAppName(clientAppName);
//		setTimeout(timeout);
//		afterPropertiesSet();
//	}

	@Override
	public Object getObject() throws Exception {
		return serviceProxy;
	}

	@Override
	public Class getObjectType() {
		return getServiceInterface();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void bind(Object target) {
		this.target = target;
//		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	public void afterPropertiesSet() throws Exception {
//		if(clientProfile.getServiceAppName()!=null&&clientProfile.getServiceAppName().equals("defaultLaserAppName")){
//			clientProfile.setServiceAppName("defaultAppName");
//		}
//		validate(clientProfile);
//		super.afterPropertiesSet();
		this.serviceProxy = new ProxyFactory(getServiceInterface(), this).getProxy(getBeanClassLoader());
	}

//	private void validate(ClientProfile clientProfile) throws InvalidParamException {
//		if (HedwigUtil.isBlankString(clientProfile.getClientAppName())) {
//			throw new InvalidParamException("clientAppName must not blank!!!");
//		}
//		if (HedwigUtil.isBlankString(target)) {
//			if (HedwigUtil.isBlankString(clientProfile.getDomainName())) {
//				throw new InvalidParamException("domainName must not blank!!!");
//			}
//			if (HedwigUtil.isBlankString(clientProfile.getServiceName())) {
//				throw new InvalidParamException("serviceName must not blank!!!");
//			}
//			if (HedwigUtil.isBlankString(clientProfile.getServiceVersion())) {
//				throw new InvalidParamException("serviceVersion must not blank!!!");
//			}
//		}
//	}
//
//	public void setServiceAppName(String serviceAppName) {
//		clientProfile.setServiceAppName(serviceAppName);
//		this.serviceAppName = clientProfile.getServiceAppName();
//	}
//
//	public void setServiceName(String serviceName) {
//		clientProfile.setServiceName(serviceName);
//		this.serviceName = clientProfile.getServiceName();
//	}
//
//	public void setServiceVersion(String serviceVersion) {
//		clientProfile.setServiceVersion(serviceVersion);
//		this.serviceVersion = clientProfile.getServiceVersion();
//	}
//
//	public void setTarget(String target) {
//		this.target = target;
//		clientProfile.setTarget(target);
//	}
//
//	public void setTimeout(Long timeout) {
//		clientProfile.setTimeout(timeout);
//		this.timeout = clientProfile.getTimeout();
//	}
//
//	public void setDomainName(String domainName) {
//		clientProfile.setDomainName(domainName);
//		this.domainName = clientProfile.getDomainName();
//	}
//
//	public void setClientAppName(String clientAppName) {
//		clientProfile.setClientAppName(clientAppName);
//		this.clientAppName = clientProfile.getClientAppName();
//	}
//
//	public void setNoRetryMethods(Set<String> noRetryMethods) {
//		clientProfile.setNoRetryMethods(noRetryMethods);
//		this.noRetryMethods = noRetryMethods;
//
//	}
//
//	public void setGroupName(String groupName) {
//		clientProfile.setStrGroupName(groupName);
//		this.groupName = groupName;
//	}
//
//	public void setAutoRedo(boolean autoRedo) {
//		clientProfile.setRedoAble(autoRedo);
//		this.autoRedo = clientProfile.isRedoAble();
//	}
//
//	public void setClientVersion(String clientVersion) {
//		clientProfile.setClientVersion(clientVersion);
//		this.clientVersion = clientProfile.getClientVersion();
//	}
//
//	public void setClientThrottle(boolean clientThrottle) {
//		clientProfile.setClientThrottle(clientThrottle);
//		this.clientThrottle = clientProfile.isClientThrottle();
//	}
//
//	public void setUseBroadCast(boolean useBroadCast) {
//		clientProfile.setUseBroadcast(useBroadCast);
//		this.useBroadCast = useBroadCast;
//	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
//		if (target != null) {
//			try {
//				logger.info(
//						"The real invoked bean name[" + this.serviceName + "_HESSIAN@" + target.getClass() + "@" + target.toString() + "]");
//			} catch (Exception e) {
//				logger.info("The real invoked bean name[" + this.serviceName + "_AKKA@" + target.getClass() + "]");
//			}
//		}

		Object result = null;
		try {
			Method method = invocation.getMethod();
			Object[] arguments = invocation.getArguments();
			result = method.invoke(target, arguments);
			
//			result = invocation.getMethod().invoke(hessianProxy, invocation.getArguments());
//			result = invocation.getMethod().invoke(hessianProxy, invocation.getArguments());
		} 
//		catch (InvocationTargetException e) {
//			if (e.getTargetException() != null) {
//				throw e.getTargetException();
//			}
//		} catch (UndeclaredThrowableException e) {
//			throw new HedwigException(
//					"Unexpected exception has been threw, it will impact those functions that depend on exception hierarchy, please contact soa team.",
//					e);
//		} 
	   catch (Throwable ex) {
//			throw new HedwigException("Failed to invoke proxy, and the real bean name is: " + this.getRealBeanName() + "]", ex);
		   
		} finally {

		}

		return result;
	}

	public Class getServiceInterface() {
		return this.serviceInterface;
	}	
}
