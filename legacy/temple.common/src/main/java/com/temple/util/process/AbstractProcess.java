package com.temple.util.process;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.temple.util.AbstractLogger;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractProcess<P extends Progress, RESULT> extends AbstractLogger implements DataProcessor<RESULT> {

	public static final Redirect DEV_NULL_REDIRECT = Redirect.to(new File("/dev/null"));

	public static final int DEFAULT_TIMEOUT_IN_SECONDS = 20;

	protected final P progress;

	protected Process process;

	protected int startTimeout;

	/** For JAX-B */
	protected AbstractProcess() {
		this(null);
	}

	protected AbstractProcess(P progress) {
		this.progress = progress;
		this.process = null;
		this.startTimeout = AbstractProcess.DEFAULT_TIMEOUT_IN_SECONDS;
	}

	@Override
	public P getProgress() {
		return this.progress;
	}

	protected abstract String[] createCommand() throws Exception;

	@Override
	public RESULT call() throws Exception {
		if (this.startProcess()) {
			try {
				// wait for process to start started
				this.process.waitFor(this.startTimeout, TimeUnit.SECONDS);
				if (this.progress.hasStartedProcessing()) {
					this.process.waitFor();
				}
			} catch (final InterruptedException e) {
				this.warning("%s interrupted", this);
			} finally {
				this.endProcess();
			}
		}
		return null;
	}

	protected boolean startProcess() throws Exception {
		final String[] command = this.createCommand();
		if (command != null && command.length > 0) {
			final ProcessBuilder pb = new ProcessBuilder(command);
			pb.redirectErrorStream(true);
			pb.redirectOutput(AbstractProcess.DEV_NULL_REDIRECT);
			try {
				synchronized (this) {
					if (!this.progress.isDone()) {
						this.process = pb.start();
					}
				}
			} catch (final IOException e) {
				this.thrown("Failed to start process ...", e);
			}
		}
		return this.process != null;
	}

	protected void endProcess() {
		synchronized (this) {
			if (this.process != null && this.process.isAlive() && this.progress.hasStartedProcessing()) {
				this.process.destroyForcibly();
				try {
					this.process.waitFor(AbstractProcess.DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
				} catch (final InterruptedException e) {
					this.ignored(e);
				}
			}
			this.progress.done();
		}
		if (this.process != null) {
			if (this.process.isAlive()) {
				this.info("%s process not ended yet", this);
			} else {
				final int termination = this.process.exitValue();
				if (termination != 0) {
					this.error("%s failed with exit code %d", this, termination);
				}
			}
		}
	}

	@Override
	public void cancel() {
		this.endProcess();
	}

}
