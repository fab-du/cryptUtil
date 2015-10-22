package de.hsmannheim;

import de.cryptone.crypto.RSACrypto;
import junit.framework.*;

public class TestRSA 
    extends TestCase
{
	public TestRSA( String testname ){
		super( testname );
	}

    public static Test suite()
    {
        return new TestSuite( TestRSA.class );
    }


	RSACrypto rsa = new RSACrypto();

	public void generatePairkey(){
		String key = rsa.genertateKey();
		System.out.println(key);
		assertNotNull("Generiere eine Schluesselpaare in JSON-Format", key );
	}


}
