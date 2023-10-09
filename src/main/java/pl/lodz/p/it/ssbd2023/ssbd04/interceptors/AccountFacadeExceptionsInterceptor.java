package pl.lodz.p.it.ssbd2023.ssbd04.interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.NoResultException;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import pl.lodz.p.it.ssbd2023.ssbd04.exceptions.AccountNotFoundException;
import pl.lodz.p.it.ssbd2023.ssbd04.exceptions.AppOptimisticLockException;
import pl.lodz.p.it.ssbd2023.ssbd04.exceptions.BaseApplicationException;
import pl.lodz.p.it.ssbd2023.ssbd04.exceptions.account.AccountAlreadyExistsException;

public class AccountFacadeExceptionsInterceptor {

    @AroundInvoke
    public Object intercept(final InvocationContext ctx) {
        try {
            return ctx.proceed();
        } catch (OptimisticLockException ole) {
            throw AppOptimisticLockException.createOptimisticLockException();
        } catch (jakarta.validation.ConstraintViolationException e) {
            throw e;
        }

        catch (NoResultException e){
                throw new AccountNotFoundException();
        }
        catch (PersistenceException | java.sql.SQLException e) {
            if (e.getCause() instanceof ConstraintViolationException constraintViolationException) {
                constraintViolationException = (ConstraintViolationException) e.getCause();
                String key = getViolatedConstraintName(constraintViolationException.getCause().getMessage());
                if (key.equals("login")) {
                    throw AccountAlreadyExistsException.loginTaken();
                } else if (key.equals("email")) {
                    throw AccountAlreadyExistsException.emailTaken();
                }
            }

            throw BaseApplicationException.createGeneralPersistenceException(e);
        }
        catch (BaseApplicationException abe) {
            throw abe;
        }

        catch (Exception e) {
            throw BaseApplicationException.createGeneralErrorException(e);
        }
    }

    private String getViolatedConstraintName(String message) {
        String key = message.substring(message.indexOf("Key (") + 5, message.indexOf(")"));
        return key;
    }
}


