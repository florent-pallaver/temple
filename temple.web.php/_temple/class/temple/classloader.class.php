<?php

namespace temple;

/**
 *
 * TODOC namespace, etc ...
 * TODOC _init
 *
 * @author florent
 */
final class ClassLoader {

	const STATIC_INITIALIZER_METHOD_NAME = '_init';
	const PREFIX = '';
	const SUFFIX = '.class.php';

	private static $notRegistered = true;
	private static $resolvers = [];
	private static $loggerPrefix = '_ClassLoader';
	private static $index = 0;

	/**
	 * @var ClassLoader
	 */
	private static $current = null;
	private $logger;
	private $stack = [];
	private $toInit = [];

	private function __construct() {
		$this->logger = Logger::getInstance(self::$loggerPrefix . (self::$index++));
	}

	private function loadLast() {
		$className = end($this->stack);
		$paths = [];
		$loadedPath = null;
		foreach (self::$resolvers as $r) {
			$path = $r->getClassPath($className);
			$paths[] = $path;
			if (is_readable($path)) {
				$loadedPath = $path;
				$this->logger->finest('loading ' . $path);
				require_once $path;
				$this->logger->fine($className . ' auto loaded');
				break;
			}
		}
		array_pop($this->stack);
		try {
			$c = new \ReflectionClass($className);
			if (!$c->isTrait()) {
				$this->toInit[] = $c;
			}
			$this->initLoadedClasses();
		} catch (Exception $e) {
			$msg = $loadedPath ? "Unable to load $className\n$loadedPath has been loaded but it does not contain a definition of $className" : "Unable to load $className\nClass path does not contain any of [" . implode(', ', $paths)
					. "]\nCheck the namespace and/or the file name...";
			throw new \Exception($msg, 0, $e);
		}
	}

	private function initLoadedClasses() {
		if (!$this->stack) {
			self::$current = null;
			foreach ($this->toInit as $c) {
				foreach ($c->getTraits() as $t) {
					$this->runInitMethod($c, $t->getShortName());
				}
				$this->runInitMethod($c);
			}
		}
	}

	private function runInitMethod(\ReflectionClass $rc, $suffix = '') {
		$mn = self::STATIC_INITIALIZER_METHOD_NAME . $suffix;
		if ($rc->hasMethod($mn)) {
			$rm = $rc->getMethod($mn);
			if ($rm->getDeclaringClass()->getName() === $rc->getName() && $rm->isStatic() && $rm->isPrivate()) {
				$rm->setAccessible(true);
				$rm->invoke(null);
				$rm->setAccessible(false);
				$this->logger->fine($rc->getName() . '::' . $rm->getName() . ' executed');
			}
		}
	}

	public static function load($className) {
		if (self::$current == null) {
			self::$current = new ClassLoader();
		}
		self::$current->stack[] = $className;
		self::$current->loadLast();
	}

	public static function add($classPath, $toLowerCase = true, $suffix = self::SUFFIX, $prefix = self::PREFIX) {
		if (self::$notRegistered) {
			spl_autoload_register(__NAMESPACE__ . '\ClassLoader::load');
			self::$notRegistered = false;
		}
		self::$resolvers[] = new _private_ClassPathResolver($classPath, $toLowerCase, $suffix, $prefix);
		if (!is_readable($classPath)) {
			Logger::getInstance(self::$loggerPrefix)->warning($classPath . ' is not readable.');
		}
	}

}

final class _private_ClassPathResolver {

	private $prefix;
	private $suffix;
	private $folder;
	private $toLowerCase;

	public function __construct($folder, $toLowerCase = true, $suffix = ClassLoader::SUFFIX, $prefix = ClassLoader::PREFIX) {
		$this->folder = $folder{strlen($folder) - 1} === DIRECTORY_SEPARATOR ? $folder : ($folder . DIRECTORY_SEPARATOR);
		$this->toLowerCase = $toLowerCase;
		$this->prefix = $prefix;
		$this->suffix = $suffix;
	}

	public function getClassPath($className) {
		$cn = $this->toLowerCase ? strtolower($className) : $className;
		$p = $this->folder . str_replace('\\', DIRECTORY_SEPARATOR, $cn);
		$cp = dirname($p) . DIRECTORY_SEPARATOR . $this->prefix . basename($p) . $this->suffix;
		return $cp;
	}

}