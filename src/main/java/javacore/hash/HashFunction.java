package javacore.hash;

public interface HashFunction {

	public int hash32(Object data);

	public long hash64(Object data);
}

