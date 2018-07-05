
public class Converter {

    private static Converter instance;
    private        TypeMap   typeMap;

    private Converter() {
        typeMap = TypeMap.getInstance();
    }

    public static Converter getInstance() {
        if (instance == null) {
            instance = new Converter();
        }
        return instance;
    }

    public <F, T> void addMapping(Class<F> from, Class<T> to, boolean mapBothways, boolean findCommonSuper) {
        typeMap.addType(from, to, mapBothways, findCommonSuper);
    }

    public <F, T> void addMapping(Class<F> from, Class<T> to) {
        typeMap.addType(from, to, false, false);
    }

    @SuppressWarnings("unchecked")
    public <F, T> T map(F from) {
        Mapper<F, T> mapper = (Mapper<F, T>) typeMap.getMapper(from.getClass());
        if (mapper == null) {
            throw new UnmappedType();
        }
        mapper.emptyHistory();

        return mapper.map(from);
    }
}
