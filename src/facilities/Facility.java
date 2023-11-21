package facilities;

/**
 * facility class will be basis for any facility
 */
public class Facility {
    String name;

    /**
     * initialises name
     * @param name
     * name of facility
     */
    public Facility(String name) {
        this.name = name;
    }

    /**
     * returns name
     * @return name
     */
    public String getName() {
        return name;
    }
}
