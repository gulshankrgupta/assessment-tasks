import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task1 {

    public List<LoanAccount> getOverdueLoans(List<LoanAccount> accounts) {

        // FIX: Initialize result list to avoid NullPointerException
        List<LoanAccount> result = new ArrayList<>();

        // FIX: Handle null input list
        if (accounts == null) {
            return result;
        }

        for (LoanAccount account : accounts) {

            // FIX: Skip null account objects
            if (account == null) {
                continue;
            }

            // FIX: dueDate may be null for restructured accounts
            if (account.getDueDate() == null) {
                continue;
            }

            if (account.getDueDate().before(new Date())) {
                if (account.getOutstandingBalance() > 0) {
                    result.add(account);
                }
            }
        }

        return result;
    }
}