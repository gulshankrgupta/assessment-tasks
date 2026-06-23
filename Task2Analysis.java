public class Task2Analysis {

    /*
     * Q1. ConcurrentModificationException occurs when a collection is
     * structurally modified while it is being iterated.
     *
     * Q2. Example:
     * for (Transaction txn : transactions) {
     *     if (txn.isInvalid()) {
     *         transactions.remove(txn);
     *     }
     * }
     *
     * Q3. Use Iterator.remove() while iterating.
     */
}