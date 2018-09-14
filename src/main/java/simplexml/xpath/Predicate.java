package simplexml.xpath;

import simplexml.error.InvalidXPath;
import simplexml.model.XmlElement;

import static simplexml.model.XmlElement.findChildForName;
import static simplexml.utils.Constants.*;
import static simplexml.utils.Validator.hasExactLength;
import static simplexml.utils.Validator.partsAreNotEmpty;

/**
 * Adapted from xml-lif (https://github.com/liflab/xml-lif) by Sylvain Hallé
 */
public interface Predicate {

	boolean evaluate(XmlElement root);

	static Predicate parsePredicate(final String predicate) throws InvalidXPath {
		if (predicate.contains(PREDICATE_EQUAL_SYMBOL)) return newEqualityPredicate(predicate);
		throw new InvalidXPath("Could not parse predicate " + predicate);
	}

	static Predicate newEqualityPredicate(final String s) throws InvalidXPath {
		final String[] parts = partsAreNotEmpty(hasExactLength(s.split(PREDICATE_EQUAL_SYMBOL), 2, ERROR_EQUALITY_WITHOUT_TWO_COMPONENTS), ERROR_EQUALITY_WITH_EMPTY_PARTS);
		return root -> {
			final XmlElement el = findChildForName(root, parts[0], null);
			return el != null && parts[1].equals(el.getText());
		};
	}

}
