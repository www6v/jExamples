package proxy.factory.bean;

public class AServiceImpl implements AService{	 
    @Override

    public void fooA(String _msg) {

         System.out.println("AServiceImpl.fooA(msg:"+_msg+")");

    }
 
    @Override

    public void barA() {

         System.out.println("AServiceImpl.barA()");  

    }
 
}