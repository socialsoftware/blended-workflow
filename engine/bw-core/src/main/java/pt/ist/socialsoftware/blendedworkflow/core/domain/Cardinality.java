package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class Cardinality extends Cardinality_Base {
	private static Logger logger = LoggerFactory.getLogger(Cardinality.class);

	public final static String ONE = "1";
	public final static String ZERO_OR_ONE = "0..1";
	public final static String ZERO_MANY = "*";
	public final static String ONE_MANY = "1..*";

	private final static String WILDCARD = "\\*";
	private final static String ZERO = "0";
	private final static String POSITIVE = "(?!0)\\d+";
	private final static String INTERVAL = "(" + ZERO + "|" + POSITIVE + ")" + ".." + POSITIVE;
	private final static String INTERVAL_MAX = "(" + ZERO + "|" + POSITIVE + ")" + ".." + WILDCARD;
	private final static String CARDINALITY = "(" + POSITIVE + "|" + WILDCARD + "|" + INTERVAL + "|" + INTERVAL_MAX
			+ ")";

	public static Cardinality parseCardinality(String exp) {
		if (!Pattern.matches(CARDINALITY, exp)) {
			throw new BWException(BWErrorType.INVALID_CARDINALITY, exp);
		}

		String[] cards = exp.split("\\.\\.");

		if (cards.length == 1) {
			int card = cards[0].equals("*") ? Integer.MAX_VALUE : Integer.parseInt(exp);
			if (card == 0) {
				throw new BWException(BWErrorType.INVALID_CARDINALITY, exp);
			}
			return new Cardinality(exp, card, card);
		} else if (cards.length == 2) {
			int minCard = cards[0].equals("*") ? Integer.MAX_VALUE : Integer.parseInt(cards[0]);
			int maxCard = cards[1].equals("*") ? Integer.MAX_VALUE : Integer.parseInt(cards[1]);
			if (maxCard == 0 || minCard > maxCard) {
				throw new BWException(BWErrorType.INVALID_CARDINALITY, exp);
			}
			return new Cardinality(exp, minCard, maxCard);
		} else {
			assert false : exp + " cardinality expression is not specified";
			return null;
		}
	}

	private Cardinality(String exp, int minValue, int maxValue) {
		setExp(exp);
		setMinValue(minValue);
		setMaxValue(maxValue);
		logger.debug("Cardinality min:{}, max:{}", getMinValue(), getMaxValue());
	}

	public void delete() {
		setRelationOfOne(null);
		setRelationOfTwo(null);

		deleteDomainObject();
	}

}
