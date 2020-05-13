<?php

namespace temple\data\persistence\model;

use temple\util\io\Directory ;

final class ProxyGenerator {

	use \temple\Singleton ;

	const PROXY_NAMESPACE = '\\temple\\_generated\\' ;

	private static $methodProxyFormat = <<<'EOD'
	public function %1$s(%2$s) {
		$this->lazyLoad() ;
		return $this->instance->%1$s(%3$s);
	}

EOD;

	private static $classProxyFormat = <<<'EOD'
<?php

namespace temple\_generated\%1$s ;

/**
 * Generated class, dot not modify
 */
class %2$sProxy extends \%1$s\%2$s implements \temple\data\persistence\model\proxy\_Proxy {

	use \temple\data\persistence\model\ModelProxy ;

	// Overridden methods
%3$s
}
EOD;

	/**
	 * @var Directory ;
	 */
	private $dir ;

	private function __construct() {
		$this->dir = new \temple\util\io\Directory(TEMPLE_CLASS_PATH . 'temple/_generated') ;
	}

	public function generate(\ReflectionClass $c) {
		$ns = $c->getNamespaceName() ;
		$cn = $c->getShortName() ;

		$proxyDir = new \temple\util\io\Directory(str_replace('\\', DIRECTORY_SEPARATOR , $ns), $this->dir) ;
		$proxyFile = new \temple\util\io\File($cn . 'Proxy' . \temple\ClassLoader::SUFFIX, $proxyDir) ;

		$classFile = new \temple\util\io\File($c->getFileName()) ;

		$pft = $proxyFile->getLastModificationTime() ;
		if(!$pft || $classFile->getLastModificationTime()->getTimestamp() > $pft->getTimestamp()) {
			$methods = '' ;
			foreach($c->getMethods(\ReflectionMethod::IS_PUBLIC) as $m) {
				if(!($m->isFinal() || $m->isStatic() || $m->isConstructor() || $m->isDestructor() || $m->getName() == '__clone')) {
					$parameters = [] ;
					$params = [] ;
					foreach($m->getParameters() as $p) {
						$name = '$' . $p->getName();
						$params[] = $name ;
						$opt = $p->isOptional() ? ' = ' . $this->toDefault($p->getDefaultValue()) : '';
						if($p->hasType()) {
							$type	= $p->getType();
							switch($type) {
								case 'array':
									$type .= ' ';
									break;
								default:
									$type = '\\' . $type . ' ';
							}
						} else {
							$type = '';
						}
						$parameters[] = $type . $name . $opt;
					}
					$methods .= sprintf(self::$methodProxyFormat, $m->getName(), implode(', ', $parameters), implode(', ', $params)) ;
				}
			}
			$class = sprintf(self::$classProxyFormat, $ns, $cn, $methods) ;
			$proxyFile->putContent($class, false) ;
		}

	}

	private function toDefault($defaultValue) {
		$type = gettype($defaultValue);
		switch($type) {
			case 'string':
				return '\'' . $defaultValue . '\'';
			case 'NULL':
				return 'NULL';
			default:
				return $defaultValue;
		}
	}

}
