<?php

namespace temple\data\persistence\model;

use temple\util\fs\Directory ;

/**
 * Description of ProxyGenerator
 *
 * @author florent
 */
final class ProxyGenerator {

	use \temple\Singleton ;
	
	const PROXY_NAMESPACE = '\\temple\\_generated\\' ;
	
	private static $methodProxyFormat = <<<'EOD'
	public function %1$s(%2$s) {
		$this->lazyLoad() ;
		return $this->instance->%1$s(%2$s);
	}

EOD;

	private static $classProxyFormat = <<<'EOD'
<?php

namespace temple\_generated\%1$s ;

/**
 * Generated class, dot not modify
 */
class %2$sProxy extends %1$s\%2$s {

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
		$this->dir = new \temple\util\fs\Directory(TEMPLE_CLASS_PATH . 'temple/_generated') ;
	}

	public function generate(\ReflectionClass $c) {
		$ns = $c->getNamespaceName() ;
		$cn = $c->getShortName() ;
		
		$proxyDir = new \temple\util\fs\Directory(str_replace('\\', DIRECTORY_SEPARATOR , $ns), $this->dir) ;
		$proxyFile = new \temple\util\fs\File($cn . 'Proxy' . \temple\ClassLoader::SUFFIX, $proxyDir) ;
		
		$classFile = new \temple\util\fs\File($c->getFileName()) ;
		
		$pft = $proxyFile->getLastModificationTime() ;
		if(!$pft || $classFile->getLastModificationTime()->getTimestamp() > $pft->getTimestamp()) {
			$methods = '' ;
			foreach($c->getMethods(\ReflectionMethod::IS_PUBLIC) as $m) {
				if(!($m->isFinal() || $m->isStatic() || $m->isConstructor() || $m->isDestructor())) {
					$parameters = [] ;
					foreach($m->getParameters() as $p) {
						$parameters[] = $p->getName() ;
					}
					$methods .= sprintf(self::$methodProxyFormat, $m->getName(), implode(', ', $parameters)) ;
				}
			}
			$class = sprintf(self::$classProxyFormat, $ns, $cn, $methods) ;
			$proxyFile->putContent($class, false) ;
		}

	}
	
}
