<?php

namespace temple\controller;

/**
 * Description of AbstractController
 *
 * @author florent
 */
abstract class AbstractRequestControllerLocale {

	const FAIL_INVALID_REQUEST = 'Requête invalide.' ;
	
	const FAIL_INTERNAL_ERROR = 'Une erreur interne est survenue.' ;
	const INTERNAL_ERROR_HINT = 'Reessayez plus tard.' ;

	const INFO_FILES_LIMIT = 'Seulement %d fichiers peuvent être téléchargés à la fois, les fichiers supplémentaires ont été ignorés.';
	const INFO_INVALID_CHARS = 'Le champ %1$s \'%2$s\' contenait des caractères invalides qui ont été remplacés.';
	const INFO_MAX_LENGTH = 'Le champ %1$s excédait la limite de %2$d caractères et a été tronqué.';
	
	const WARN_AGE = '\'%1$s\' n\'est pas une date de naissance valide.\nSi vous n\'avez pas au moins %2$d ans, vous ne respectez pas les termes et conditions d\'utilisation de ce site web.';
	const WARN_INVALID_DATE = '\'%s\' n\'est pas une date valide.';
	const WARN_DELETE_FILE = 'Impossible d\'effacer le fichier.' ;

	const FAIL_INCORRECT_FIELD = 'Ce champ est incorrect.' ;
	const FAIL_EMPTY_FIELD = 'Ce champ ne peut pas être vide.' ;
	const FAIL_MIN_LENGTH = 'Ce champ doit minimum faire %1$s caractères.' ;
	const FAIL_MAX_LENGTH = 'Ce champ est limité à %1$s caractères.' ;

	const FAIL_MIN_AGE = 'L\'âge minimum requis est %s ans.' ;
	const FAIL_MAX_AGE = 'L\'âge maximum requis est %s ans.' ;
	
	const FAIL_PASSES = 'Les passes ne correspondent pas' ;
	
	const FAIL_UPLOAD_ERROR = 'Une erreur est survenue pendant le téléchargement du fichier.' ;
	const FAIL_FILE_TOO_BIG = 'Le fichier est trop gros, la taille maximale acceptée est %1$s.' ;
	
	const FAIL_ACCESS_DENIED = 'Accès refusé !' ;
	const FAIL_ACCESS_DENIED_HINT = 'Si vous avez été déconnecté, rafraîchir la page et se reconnecter peut résoudre ce problème.' ;

	const FAIL_PARAMETERS = 'Données envoyées incorrects.' ;
	const FAIL_PARAMETERS_HINT = 'Vérifiez les données que vous avez envoyées.' ;

}
