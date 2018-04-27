package pt.ist.socialsoftware.blendedworkflow.resources.domain;

public abstract class RALExprHistory extends RALExprHistory_Base {
    public enum Quantifier {
        LEAST("LEAST"),
        MOST("MOST");

        private final String code;

        private Quantifier(String code) {
            this.code = code;
        }

        public String toString() {
            return code;
        }

        public static Quantifier fromString(String code) throws IllegalArgumentException {
            switch (code) {
                case "LEAST":
                    return Quantifier.LEAST;
                case "MOST":
                    return Quantifier.MOST;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void delete() {
        setQuantifier(null);
        setDataField(null);
        super.delete();
    }
    
}
