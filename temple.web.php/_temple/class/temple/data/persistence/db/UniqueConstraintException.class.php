<?php

namespace temple\data\persistence\db ;

/**
 * Exception class used whenever a query fails because of a databse unique constraint.
 *
 * @author florent
 */
final class UniqueConstraintException extends QueryException {

}
