<?php

namespace temple\data\persistence\db ;

/**
 * Exception class used whenever a query fails because of a database foreign key constraint.
 *
 * @author florent
 */
final class ForeignKeyConstraintException extends QueryException {

}
