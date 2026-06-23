import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentValidator {

    // FIX: Use SLF4J logger instead of printStackTrace()
    private static final Logger logger =
            LoggerFactory.getLogger(DocumentValidator.class);

    public ValidationResult validate(Document doc) {

        try {
            // FIX: Expected validation failure should use IllegalArgumentException
            if (doc == null) {
                throw new IllegalArgumentException("Document is null");
            }

            String content = doc.extractContent();

            // FIX: Expected validation failure should use IllegalArgumentException
            if (content == null || content.isEmpty()) {
                throw new IllegalArgumentException("Empty content");
            }

            return runValidationRules(content);

        } catch (IllegalArgumentException e) {

            // FIX: Replace printStackTrace() with structured logging
            logger.warn("Validation failed: {}", e.getMessage());
            throw e;

        } catch (Exception e) {

            // FIX: Log unexpected runtime errors separately
            logger.error("Unexpected validation error", e);
            throw e;
        }
    }

    public void validateBatch(List<Document> docs) {

        for (Document doc : docs) {
            try {
                ValidationResult r = validate(doc);

                // FIX: Prevent NullPointerException
                if (r != null && r.isValid()) {
                    saveResult(r);
                }

            } catch (IllegalArgumentException e) {

                // FIX: Do not silently swallow expected validation failures
                logger.warn("Skipping invalid document: {}", e.getMessage());

            } catch (Exception e) {

                // FIX: Do not silently swallow unexpected exceptions
                logger.error("Error processing document", e);
            }
        }
    }
}