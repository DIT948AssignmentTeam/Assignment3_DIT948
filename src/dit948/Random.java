package dit948;

/**
 *  Seventies style random generating functions.
 *  @author Cezar Ionescu <cezar@chalmers.se>
 *  @version 0.1
 *  @since 2014-08-28
 */
public class Random {
    /**
     *  The java.util.Random number generator that does all the work.
     */
    private static java.util.Random rand = new java.util.Random();

    /**
     *  Generates a random byte from a uniform distribution.
     */
    public static byte randomByte() {
        return (byte) rand.nextInt(256);
    }

    /**
     *  Generates a random ASCII char from a uniform distribution.
     */
    public static char randomChar() {
        return (char) rand.nextInt(256);
    }

    /**
     *  Generates a random short from a uniform distribution.
     */
    public static short randomShort() {
        return (short) rand.nextInt(65536);
    }

    /**
     *  Generates a random int from a uniform distribution.
     */
    public static int randomInt() {
        return rand.nextInt();
    }


    /**
     *  Generates one of n possible results (0, 1, ..., n-1) from a uniform distribution.
     *  @parameter n the number of results generated
     */
    public static int randomInt(int n) {
        return rand.nextInt(n);
    }

    /**
     *  Generates a random long from a uniform distribution.
     */
    public static long randomLong() {
        return rand.nextLong();
    }

    /**
     *  Generates a random float from a uniform distribution.
     */
    public static float randomFloat() {
        return rand.nextFloat();
    }

    /**
     *  Generates a random double from a uniform distribution.
     */
    public static double randomDouble() {
        return rand.nextDouble();
    }

    /**
     *  Generates a random double from a uniform distribution.
     */
    public static double randomNormal() {
        return rand.nextGaussian();
    }

    /**
     *  Generates a random boolean from a uniform distribution.
     */
    public static boolean randomBoolean() {
        return rand.nextBoolean();
    }
}
